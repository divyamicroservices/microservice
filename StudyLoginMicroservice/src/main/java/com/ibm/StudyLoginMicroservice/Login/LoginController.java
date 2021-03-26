package com.ibm.StudyLoginMicroservice.Login;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.StudyLoginMicroservice.Async.OrderQueueProducer;
import com.ibm.StudyLoginMicroservice.Async.QueueProducer;
import com.ibm.StudyLoginMicroservice.Service.AddProductInCartService;
import com.ibm.StudyLoginMicroservice.Service.GetOrderService;
import com.ibm.StudyLoginMicroservice.Service.GetProductListService;
import com.ibm.StudyLoginMicroservice.Service.GetUserCartService;
import com.ibm.StudyLoginMicroservice.Service.JwtUtil;
import com.ibm.StudyLoginMicroservice.Service.MyUserDetailsService;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginJPARepository repo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	JwtUtil jwtUtil;

	@GetMapping("/home")
	public String getLoginPage(Model model) {

		logger.info("Inside LoginController getLoginPage");

		Login newlogin = new Login();

		model.addAttribute("newlogin", newlogin);

		model.addAttribute("message", "HELLOWORLD");

		return "Login";
	}

	@Autowired
	QueueProducer queueProducer;

	@Autowired
	OrderQueueProducer orderQueueProducer;

	@GetMapping("/logout")
	public String logout(Model model, HttpServletRequest request) {

		request.getSession().invalidate();
		Login newlogin = new Login();

		model.addAttribute("newlogin", newlogin);
		return "Login";
	}

	@PostMapping("/authenticate")
	public String createAuthenticationToken(@ModelAttribute("newlogin") Login newlogin, Model model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(newlogin.getUsername(), newlogin.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Credentials");
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(newlogin.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		List<Product> listofProducts = new ArrayList<Product>();

		Date d1 = new Date();

		listofProducts = getListofProducts();

		try {

			String msg = "Success---" + newlogin.getUsername() + "---" + d1.toString();

			queueProducer.produce(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String bearerJwt = "Bearer " + jwt;
		request.getSession().setAttribute("Authorization", jwt);

		request.getSession().setAttribute("loggedInUsername", newlogin.getUsername());

		model.addAttribute("newProduct", new Product());
		model.addAttribute("listofProducts", listofProducts);

		Login loggedInUser = new Login();

		Optional<Login> ologgedInUser = repo.findById(newlogin.getUsername());
		if (ologgedInUser.isPresent()) {
			loggedInUser = ologgedInUser.get();
		}
		if (loggedInUser.getUsertype().equalsIgnoreCase("ADMIN")) {
			return "AllProducts";
		} else {

			String username = request.getSession().getAttribute("loggedInUsername").toString();

			List<Cart> tempCart = getUserCartService.getUserCart(username);

			List<CartProduct> listofCartProducts = new ArrayList<>();
			for (int cnt = 0; cnt < tempCart.size(); cnt++) {
				String productId = tempCart.get(cnt).getProductid();
				Product product = getProduct(productId);
				CartProduct cartProduct = new CartProduct();
				cartProduct.setCartId(tempCart.get(cnt).getCartid());
				cartProduct.setId(product.getId());
				cartProduct.setMrp(Integer.parseInt(product.getMrp()));
				cartProduct.setProductcategory(product.getProductcategory());
				cartProduct.setProductName(product.getProductName());
				listofCartProducts.add(cartProduct);
			}

			model.addAttribute("listofCartProducts", listofCartProducts);
			model.addAttribute("message", "");
			return "ManageCart";

		}
	}

	@GetMapping("/manageUsers")
	public String manageUsers(Model model) {

		List<Login> listofUsers = repo.findAll();

		model.addAttribute("newUser", new Login());
		model.addAttribute("listofUsers", listofUsers);
		return "ManageUsers";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("newUser") Login login, Model model) {

		Login temp = new Login();
		temp.setUsername(login.getUsername());
		temp.setPassword(login.getPassword());
		temp.setUsertype(login.getUsertype());

		repo.save(temp);

		List<Login> listofUsers = repo.findAll();

		model.addAttribute("newUser", new Login());
		model.addAttribute("listofUsers", listofUsers);
		return "ManageUsers";
	}

	@GetMapping(path = "/manageProducts")
	public String manageProducts(Model model) {

		List<Product> listofProducts = new ArrayList<Product>();

		listofProducts = getListofProducts();

		model.addAttribute("newProduct", new Product());
		model.addAttribute("listofProducts", listofProducts);
		return "AllProducts";
	}

	@GetMapping(path = "/showNewProductForm")
	public String showNewProductForm(Model model) {

		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);

		return "newProduct";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("newProduct") Product product, Model model) {

		List<Product> listofProducts = new ArrayList<Product>();
		listofProducts = addProduct(product.getProductcategory(), product.getProductName(), product.getMrp());

		model.addAttribute("newProduct", new Product());
		model.addAttribute("listofProducts", listofProducts);
		return "AllProducts";
	}

	@RequestMapping(path = "/addInCart", method = RequestMethod.POST)
	public ModelAndView addInCart(HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();

		List<Product> listofProducts = new ArrayList<Product>();

		listofProducts = getListofProducts();

		ModelAndView mv = new ModelAndView();
		mv.addObject("listofProducts", listofProducts);
		mv.addObject("username", username);
		mv.addObject("message", "");
		mv.setViewName("AllProducts");
		return mv;
	}

	@GetMapping(path = "/manageCart")
	public String manageCart(Model model, HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();

		List<Cart> tempCart = getUserCartService.getUserCart(username);

		List<CartProduct> listofCartProducts = new ArrayList<>();
		for (int cnt = 0; cnt < tempCart.size(); cnt++) {
			String productId = tempCart.get(cnt).getProductid();
			Product product = getProduct(productId);
			CartProduct cartProduct = new CartProduct();
			cartProduct.setCartId(tempCart.get(cnt).getCartid());
			cartProduct.setId(product.getId());
			cartProduct.setMrp(Integer.parseInt(product.getMrp()));
			cartProduct.setProductcategory(product.getProductcategory());
			cartProduct.setProductName(product.getProductName());
			listofCartProducts.add(cartProduct);
		}

		model.addAttribute("listofCartProducts", listofCartProducts);
		List<Product> listofProducts = new ArrayList<Product>();
		listofProducts = getListofProducts();
		model.addAttribute("listofProducts", listofProducts);
		model.addAttribute("message", "");
		return "ManageCart";
	}

	@GetMapping(path = "/addProductInCart/{id}")
	public String addProductInCart(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();
		addProductInUserCart(username, id);

		List<Cart> tempCart = getUserCartService.getUserCart(username);

		List<CartProduct> listofCartProducts = new ArrayList<>();
		for (int cnt = 0; cnt < tempCart.size(); cnt++) {
			String productId = tempCart.get(cnt).getProductid();
			Product product = getProduct(productId);
			CartProduct cartProduct = new CartProduct();
			cartProduct.setCartId(tempCart.get(cnt).getCartid());
			cartProduct.setId(product.getId());
			cartProduct.setMrp(Integer.parseInt(product.getMrp()));
			cartProduct.setProductcategory(product.getProductcategory());
			cartProduct.setProductName(product.getProductName());
			listofCartProducts.add(cartProduct);
		}

		model.addAttribute("listofCartProducts", listofCartProducts);
		List<Product> listofProducts = new ArrayList<Product>();
		listofProducts = getListofProducts();
		model.addAttribute("listofProducts", listofProducts);
		model.addAttribute("message", "");
		return "ManageCart";
	}

	@GetMapping(path = "/deleteProductInCart/{cartid}")
	public String deleteProductInCart(@PathVariable(value = "cartid") String cartid, Model model,
			HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();

		List<Cart> tempCart = deleteProductInUserCart(username, cartid);

		List<CartProduct> listofCartProducts = new ArrayList<>();
		for (int cnt = 0; cnt < tempCart.size(); cnt++) {
			String productId = tempCart.get(cnt).getProductid();
			Product product = getProduct(productId);
			CartProduct cartProduct = new CartProduct();
			cartProduct.setCartId(tempCart.get(cnt).getCartid());
			cartProduct.setId(product.getId());
			cartProduct.setMrp(Integer.parseInt(product.getMrp()));
			cartProduct.setProductcategory(product.getProductcategory());
			cartProduct.setProductName(product.getProductName());
			listofCartProducts.add(cartProduct);
		}

		model.addAttribute("listofCartProducts", listofCartProducts);
		List<Product> listofProducts = new ArrayList<Product>();
		listofProducts = getListofProducts();
		model.addAttribute("listofProducts", listofProducts);
		model.addAttribute("message", "");
		return "ManageCart";
	}

	@GetMapping(path = "/placeOrder")
	public String placeOrder(Model model, HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();

		List<Cart> tempCart = getUserCartService.getUserCart(username);

		List<CartProduct> listofCartProducts = new ArrayList<>();
		for (int cnt = 0; cnt < tempCart.size(); cnt++) {
			String productId = tempCart.get(cnt).getProductid();
			Product product = getProduct(productId);
			CartProduct cartProduct = new CartProduct();
			cartProduct.setCartId(tempCart.get(cnt).getCartid());
			if (null != product) {
				cartProduct.setId(product.getId());
				cartProduct.setMrp(Integer.parseInt(product.getMrp()));
				cartProduct.setProductcategory(product.getProductcategory());
				cartProduct.setProductName(product.getProductName());
			}
			listofCartProducts.add(cartProduct);
		}

		model.addAttribute("listofCartProducts", listofCartProducts);
		List<Product> listofProducts = new ArrayList<Product>();
		listofProducts = getListofProducts();
		model.addAttribute("listofProducts", listofProducts);
		return "PlaceOrder";
	}

	@GetMapping(path = "/checkoutCart")
	public String checkoutCart(Model model, HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();

		Date begindate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			begindate = fmt.parse("2020-12-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date date1 = new Date();
		String d1 = String.valueOf(date1.getTime() - begindate.getTime());
		String message = checkoutCart(username, d1.toString());

		List<Cart> tempCart = getUserCartService.deleteUserCart(username);

		List<CartProduct> listofCartProducts = new ArrayList<>();
		for (int cnt = 0; cnt < tempCart.size(); cnt++) {
			String productId = tempCart.get(cnt).getProductid();
			Product product = getProduct(productId);
			CartProduct cartProduct = new CartProduct();
			cartProduct.setCartId(tempCart.get(cnt).getCartid());
			cartProduct.setId(product.getId());
			cartProduct.setMrp(Integer.parseInt(product.getMrp()));
			cartProduct.setProductcategory(product.getProductcategory());
			cartProduct.setProductName(product.getProductName());
			listofCartProducts.add(cartProduct);
		}

		model.addAttribute("listofCartProducts", listofCartProducts);
		List<Product> listofProducts = new ArrayList<Product>();
		listofProducts = getListofProducts();
		model.addAttribute("listofProducts", listofProducts);
		model.addAttribute("message", "Your Order " + d1.toString() + " has been Placed");

		return "ManageCart";
	}

	@GetMapping(path = "/myOrders")
	public String myOrders(Model model, HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();
		List<Order> tempOrders = getListofOrders(username);

		List<OrderProduct> listOrderProducts = new ArrayList<>();
		List<OrderIds> listofOrders = new ArrayList<OrderIds>();

		Date begindate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			begindate = fmt.parse("2020-12-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int cnt = 0; cnt < tempOrders.size(); cnt++) {
			OrderIds tempOrderId = new OrderIds();
			OrderProduct orderProduct = new OrderProduct();
			tempOrderId.setOrderNo(tempOrders.get(cnt).getOrderno());
			Long timestampInMilli = begindate.getTime() + Long.parseLong(tempOrders.get(cnt).getOrderno());
			tempOrderId.setTimeStamp((new Date(timestampInMilli)).toString());

			boolean flagForAdd = true;
			for (int cntforAdd = 0; cntforAdd < listofOrders.size(); cntforAdd++) {

				if (listofOrders.get(cntforAdd).getOrderNo().equalsIgnoreCase(tempOrderId.getOrderNo())) {
					flagForAdd = false;
				}

			}
			if (flagForAdd) {

				listofOrders.add(tempOrderId);
			}

			orderProduct.setOrderno(tempOrders.get(cnt).getOrderno());
			String productId = tempOrders.get(cnt).getProductid();
			Product product = getProduct(productId);
			orderProduct.setMrp(Integer.parseInt(product.getMrp()));
			orderProduct.setId(product.getId());
			orderProduct.setProductcategory(product.getProductcategory());
			orderProduct.setProductName(product.getProductName());

			listOrderProducts.add(orderProduct);

		}

		model.addAttribute("listOrderProducts", new ArrayList<OrderProduct>());
		model.addAttribute("listofOrderIds", listofOrders);
		model.addAttribute("OrderNo", "-");
		return "MyOrders";

	}

	@GetMapping(path = "/getOrderDetails/{id}")
	public String getOrderDetails(@PathVariable(value = "id") String id, Model model, HttpServletRequest request) {

		String username = request.getSession().getAttribute("loggedInUsername").toString();
		List<Order> tempOrders = getListofOrders(username);

		List<OrderProduct> listOrderProducts = new ArrayList<>();
		List<OrderIds> listofOrders = new ArrayList<OrderIds>();

		Date begindate = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			begindate = fmt.parse("2020-12-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int cnt = 0; cnt < tempOrders.size(); cnt++) {
			OrderIds tempOrderId = new OrderIds();
			OrderProduct orderProduct = new OrderProduct();

			tempOrderId.setOrderNo(tempOrders.get(cnt).getOrderno());
			Long timestampInMilli = begindate.getTime() + Long.parseLong(tempOrders.get(cnt).getOrderno());

			tempOrderId.setTimeStamp((new Date(timestampInMilli)).toString());

			boolean flagForAdd = true;
			for (int cntforAdd = 0; cntforAdd < listofOrders.size(); cntforAdd++) {

				if (listofOrders.get(cntforAdd).getOrderNo().equalsIgnoreCase(tempOrderId.getOrderNo())) {
					flagForAdd = false;
				}

			}
			if (flagForAdd) {

				listofOrders.add(tempOrderId);
			}

			if (id.equalsIgnoreCase(tempOrders.get(cnt).getOrderno())) {
				orderProduct.setOrderno(tempOrders.get(cnt).getOrderno());
				String productId = tempOrders.get(cnt).getProductid();
				Product product = getProduct(productId);
				orderProduct.setMrp(Integer.parseInt(product.getMrp()));
				orderProduct.setId(product.getId());
				orderProduct.setProductcategory(product.getProductcategory());
				orderProduct.setProductName(product.getProductName());

				listOrderProducts.add(orderProduct);
			}
		}

		model.addAttribute("listOrderProducts", listOrderProducts);
		model.addAttribute("listofOrderIds", listofOrders);
		model.addAttribute("OrderNo", id);
		return "MyOrders";

	}

	@RequestMapping(path = "/validate", method = RequestMethod.GET)
	public ModelAndView getLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

		List<Product> listofProducts = new ArrayList<Product>();

		Login temp = new Login();
		Optional<Login> oLogin = repo.findById(username);
		if (oLogin.isPresent()) {
			temp = oLogin.get();
		}
		Date d1 = new Date();
		if (password.equalsIgnoreCase(temp.getPassword())) {

			listofProducts = getListofProducts();

			try {

				String msg = "Success---" + username + "---" + d1.toString();

				queueProducer.produce(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {
				String msg = "Failed---" + username + "---" + d1.toString();
				queueProducer.produce(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("listofProducts", listofProducts);
		mv.addObject("username", username);
		mv.addObject("message", "");
		mv.setViewName("AllProducts");
		return mv;
	}

	/*
	
	
	*/

	@RequestMapping(path = "/update/{userName}/{password}", method = RequestMethod.GET)
	public List<Login> updateLogin(@PathVariable String userName, @PathVariable String password) {

		Optional<Login> oLogin = repo.findById(userName);
		Login temp = oLogin.get();
		temp.setPassword(password);

		repo.save(temp);
		return repo.findAll();
	}

	@Autowired
	GetProductListService getProductListService;

	public List<Product> getListofProducts() {

		return getProductListService.getAllProducts();

	}

	public Product getProduct(String productid) {

		return getProductListService.getProduct(productid);

	}

	public List<Product> addProduct(String add_productcategory, String add_productname, String add_mrp) {

		return getProductListService.addProduct(add_productname, add_mrp, add_productcategory);

	}

	@Autowired
	AddProductInCartService addProductInCartService;

	public List<Cart> addProductInUserCart(String userName, String productid) {

		return addProductInCartService.addProductInCart(userName, productid);
	}

	public List<Cart> deleteProductInUserCart(String userName, String cartid) {

		return addProductInCartService.deleteProductInCart(userName, cartid);
	}

	@Autowired
	GetUserCartService getUserCartService;

	@Autowired
	GetOrderService getOrderService;

	String  checkoutCart(String userName , String d1){
		
		List<Cart> tempCart = getUserCartService.getUserCart(userName);
		
		
		for(int cnt=0;cnt< tempCart.size(); cnt++) {
			try {
			String message=tempCart.get(cnt).getUsername()+"---"+tempCart.get(cnt).getProductid()+"---"+d1;
				orderQueueProducer.produce(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	return"Order Placed";

	}

	public List<Order>  getListofOrders(String userName ){
		
		return getOrderService.getUserOrders(userName);
		
	}
}

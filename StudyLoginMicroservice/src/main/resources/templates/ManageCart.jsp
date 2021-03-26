<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.*"%>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<!DOCTYPE html>
<html>
<head>
<style>
.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

.button1 {width: 250px;}
.button2 {width: 50%;}
.button3 {width: 100%;}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="right">
<form action="/login/logout" id="logoutForm" name="logoutForm" method="post">
	<input  type="submit" name="logout" value="Logout" onclick="javascript: document.logoutForm.submit();"></input>
</form>
</div>
<div align="center">
<%
String usertype = request.getSession().getAttribute("usertype").toString();

%>
<table>
<tr>
<% if(usertype.equalsIgnoreCase("ADMIN")){%>
<td>

<form action="/login/manageUsers" id="manageUsersForm" name="manageUsersForm" method="post">
<input  type="submit" name="manageUsers" value="Manage Users" onclick="javascript: document.manageUsersForm.submit();"></input>
</form>

</td>

<td>
<form action="/login/manageProducts" id="manageProductsForm" name="manageProductsForm" method="post">
<input  type="submit" name="manageProducts" value="Manage Products" onclick="javascript: document.manageProductsForm.submit();"></input>
</form>

</td>
<%} %>
<% if(usertype.equalsIgnoreCase("NORMAL")){%>
<td>
<form action="/login/addInCart" id="addInCartForm" name="addInCartForm" method="post">
<input  type="submit" name="addInCart" value="Add in Cart" onclick="javascript: document.addInCartForm.submit();"></input>
</form>

</td>
<td>
<form action="/login/manageCart" id="manageCartForm" name="manageCartForm" method="post">
<input  type="submit" name="manageCart" value="My Cart" onclick="javascript: document.manageCartForm.submit();"></input>
</form>

</td>
<td>
<form action="/login/myOrders" id="myOrdersForm" name="myOrdersForm" method="post">
<input  type="submit" name="myOrdersForm" value="My Orders" onclick="javascript: document.myOrdersForm.submit();"></input>
</form>

</td>
<%} %>

</tr>
</table>
</div>
<div align="center">
<form action="/login/deleteProductInCart" id="deleteProductInCartForm" name="deleteProductInCartForm" method="post">
<input type="hidden" id="username" name="username" >
<input type="hidden" id="cartid" name="cartid" >
<% List<com.ibm.StudyLoginMicroservice.Login.CartProduct> listofCartProducts = (List<com.ibm.StudyLoginMicroservice.Login.CartProduct>)request.getAttribute("listofCartProducts");%>
<% String username = request.getAttribute("username").toString();
	
	
%>

<% if((null != listofCartProducts) && (listofCartProducts.size() > 0)){%>
<h1>My Cart </h1>
 <table border="1">
 
 <tr> <th>Product Category</th> <th>Product Name</th><th>MRP (Rs)</th><th>Delete from Cart</th> </tr>
 
 <%
 double total =0;
 for(int cnt=0 ; cnt < listofCartProducts.size(); cnt ++){
 total = total+ Double.parseDouble(listofCartProducts.get(cnt).getMrp() );
 Double price = Double.parseDouble(listofCartProducts.get(cnt).getMrp() );
 %>
 
 <tr>
  <td><%= listofCartProducts.get(cnt).getProductcategory() %></td> 
 <td><%= listofCartProducts.get(cnt).getProductName() %></td>
 <td><%= price %></td>
 <td><input  type="submit" name="Delete" value="Delete from Cart" onclick="javascript: deleteFromCart('<%= listofCartProducts.get(cnt).getCartId() %>');"></input>
 </tr>
 <%} %>
 <tr><td colspan="2" ><b>Total</b></td>
 	 <td><b><%= total %></b></td>
 	 <td></td>	
 </tr>
 </table>

<%} %>
</form>

<form action="/login/checkout" id="checkoutForm" name="checkoutForm" method="post">
<input type="hidden" id="usernameforCheckout" name="usernameforCheckout" >
<input  type="submit" name="Checkout" value="Checkout" onclick="javascript: checkoutCart('<%= username%>');"></input>
</form>
</div>
<script type="text/javascript">

function checkoutCart(username){
	
		
	document.getElementById("usernameforCheckout").value=username;
	
	document.checkoutForm.submit();
}


function deleteFromCart(cartid){
	
//	alert("cartid "+ cartid)
	
	document.getElementById("cartid").value=cartid;
	
	document.deleteProductInCartForm.submit();
}
</script>
</body>


</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*, java.util.*"%>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>



<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
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

<table>
<tr>

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


</tr>
</table>
</div>
<div align="center">
<form action="/login/addProductInCart" id="addProductInCartForm" name="addProductInCartForm" method="post">
<input type="hidden" id="username" name="username" value="">
<input type="hidden" id="productid" name="productid" value="0">



<h1>Add in Cart </h1>
 <table border="1">
 
 <tr><th>Product Category</th><th>Product Name</th><th>MRP</th><th>Add to cart</th> </tr>
 
<tr th:each= "listItem :${listofProducts}">
 <td th:text= "${listItem.getProductcategory()}" ></td>
 <td th:text= "${listItem.getProductName()}" ></td>
 <td th:text= "${listItem.getMrp()}" ></td>

 
 </tr>
 
 
 </table>


</form>


</div>
<script type="text/javascript">
function addProductInCart(username, productid){
	
	alert("Product is Added ");
	
	document.getElementById("username").value=username;
	document.getElementById("productid").value=productid;
	
	document.addProductInCartForm.submit();
}


</script>
</body>


</html>
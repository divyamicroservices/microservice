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
<form action="/login/editUser" id="editUserForm" name="editUserForm" method="post">
<input type="hidden" id="username" name="username" >
<input type="hidden" id="password" name="password" >
<input type="hidden" id="usertype" name="usertype" >
<input type="hidden" id="action" name="action" >
<% List<com.ibm.StudyLoginMicroservice.Login.Login> listofUsers = (List<com.ibm.StudyLoginMicroservice.Login.Login>)request.getAttribute("listofUsers");%>


<% if((null != listofUsers) && (listofUsers.size() > 0)){%>
<h1>Manage Users </h1>
 <table border="1">
 
 <tr><th>User Type </th><th>Username</th><th>Password</th> </tr>
 
 <% for(int cnt=0 ; cnt < listofUsers.size(); cnt ++){%>
 
 <tr>
 <td><%= listofUsers.get(cnt).getUsertype() %></td>
 <td><%= listofUsers.get(cnt).getUsername() %></td>
 <td><%= listofUsers.get(cnt).getPassword() %></td>
 <!-- 

  -->
 </tr>
 <%} %>
 
 </table>

<%} %>
<h1> Add User </h1>
<table>
<tr><th>Usertype</th><th>Username</th><th>Password</th></tr>
<tr>
<td><input type="text" id="add_usertype" name="add_usertype" ></td>
<td><input type="text" id="add_username" name="add_username" ></td>
<td><input type="text" id="add_password" name="add_password" ></td>

</tr>
<tr>
<td colspan="3" align="center"><input type="submit"  name="addUser" value="Add User" onclick="javascript: addUser(); "></td>
</tr>
</table>
 
</form>


</div>
<script type="text/javascript">


function addUser(){
				
		document.editUserForm.submit();	
	
}



</script>
</body>


</html>
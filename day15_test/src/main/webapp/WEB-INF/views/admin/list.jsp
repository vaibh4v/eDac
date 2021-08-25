<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h4>${sessionScope.message}</h4>
	<h5>${sessionScope.user_details}</h5>
	
	<hr>
	
	<h4>${requestScope.message}</h4>

	<table align = "center">
		<caption>Vendor List</caption>
		<c:forEach var="vendor" items="${requestScope.vendor_list}">
			<tr>
				<td>${vendor.name}</td>
				<td>${vendor.email}</td>
				<td>${vendor.regAmount}</td>
				<td>${vendor.regDate}</td>

				<td><a href="<spring:url value='/admin/update?vid=${vendor.id}'/>">Update</a></td>
				<td><a href="<spring:url value='/admin/delete?vid=${vendor.id}'/>">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="<spring:url value='/admin/add'/>">Add User</a>
	<a href="<spring:url value='/user/logout'/>">Log Me Out</a>
</body>
</html>
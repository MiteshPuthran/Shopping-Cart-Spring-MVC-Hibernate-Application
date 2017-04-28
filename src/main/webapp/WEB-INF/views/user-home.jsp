<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/advert/logout" method="post" commandName="advert">
<input type="submit" value="Logout">
</form:form>

<h1>Welcome Seller, ${user.firstName} </h1>

<a href="${contextPath}/category/add" >Add a Product Category</a> <br />
<a href="${contextPath}/advert/add" >Add the Product Description</a> <br />
<a href="${contextPath}/advert/sellerlist" >View All Products</a> <br />


</body>
</html>
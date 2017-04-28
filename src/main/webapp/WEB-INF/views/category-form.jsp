<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Category Form</title>
</head>
<body>


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Seller Home</a><br/>

	<h2>Add a New Category</h2>


	<form:form action="${contextPath}/category/add" method="post" commandName="category">

		<table>
			<tr>
				<td>Category Title:</td>
				<td><form:input path="title" size="30" required="required" /> <font color="red"><form:errors
							path="title" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Create Category" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>
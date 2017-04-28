<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Advert Form</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Seller Home</a><br/>

	<h2>Posting a New Advert</h2>


	<form:form action="${contextPath}/advert/add" method="post"
		commandName="advert" enctype="multipart/form-data">

		<table>
			<tr>
				<td>Poster Name:</td>
				<td>${sessionScope.user.username}</td>
				<td><form:hidden path="postedBy"
						value="${sessionScope.user.personID}" /></td>
			</tr>

			<tr>
				<td>Select a Category:</td>
				<td><form:select path="categories" items="${categories}"
						multiple="true" required="required" /></td>
			</tr>

			<tr>
				<td>Product Name:</td>
				<td><form:input type="text" path="title" size="30" required="required"/>
				<font color="red"><form:errors path="title" /></font></td>
			</tr>

			<tr>
				<td>Product Image:</td>
				<td><input type="file" name="photo" required="required"/>
				</td>
			</tr>
			
			<tr>
				<td>Image Name:</td>
				<td><form:input type="text" path="filename" required="required"/>
				<font color="red"><form:errors path="filename" /></font></td>
			</tr>
			
			<tr>
				<td>Product Description:</td>
				<td><form:input type="text" path="message" size="30" required="required"/>
				<font color="red"><form:errors path="message" /></font></td>
			</tr>
			
			<tr>
				<td>Product Price:</td>
				<td><form:input type="number" path="price" size="30" required="required"/>
				<font color="red"><form:errors path="price" /></font></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="Post Advert" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>
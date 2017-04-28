<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Created Successfully</title>
    </head>
    <body>
    	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	    <a href="${contextPath}/user/">Home</a><br/>
    
        <h2>New Category Created Successfully: ${category.title}</h2>
        
    </body>
</html>
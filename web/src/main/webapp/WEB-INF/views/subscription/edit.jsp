<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Subscription</h1>
<form:form action="update" method="post" modelAttribute="subscription">
    <input type="hidden" name="id" value="${id}"/>
    Expires Date:
    <form:input path="expiresDate"/>
    <form:errors path="expiresDate"/>
    <br/>
    <button type="submit">Submit</button>
</form:form>
<a href="<c:url value='/subscription'/>">Cancel</a>
</body>
</html>

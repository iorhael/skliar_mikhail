<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit User</h1>
<form:form action="update" method="post" modelAttribute="user">
    <input type="hidden" name="id" value="${id}"/>
    Name:
    <form:input path="username"/>
    <form:errors path="username"/>
    <br/>
    Email:
    <form:input path="email" type="email"/>
    <form:errors path="email"/>
    <br/>
    Password:
    <form:password path="password"/>
    <form:errors path="password"/>
    <br/>
    <button type="submit">Submit</button>
</form:form>
<a href="<c:url value='/user'/>">Cancel</a>
</body>
</html>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New User</h1>
<form:form action="insert" method="post" modelAttribute="user">
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
    <button type="submit">Create</button>
</form:form>
<a href="<c:url value='/user'/>">Cancel</a>
</body>
</html>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Poll</h1>
<form:form action="insert" method="post" modelAttribute="poll">
    Content:
    <form:input path="description" />
    <form:errors path="description" />
    <br/>
    Post:
    <form:select path="post.id">
        <form:options items="${posts}" itemValue="id" itemLabel="title"/>
    </form:select>
    <br/>
    Author:
    <form:select path="author.id">
        <form:options items="${authors}" itemValue="id" itemLabel="username"/>
    </form:select>
    <br/>
    <button type="submit">Create</button>
</form:form>
<a href="<c:url value='/poll'/>">Cancel</a>
</body>
</html>

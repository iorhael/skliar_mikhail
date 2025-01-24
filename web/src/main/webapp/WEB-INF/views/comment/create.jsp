<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Comment</h1>
<form:form action="insert" method="post" modelAttribute="comment">
    Content:
    <form:input path="content"/>
    <form:errors path="content"/>
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
    Parent Comment:
    <form:select path="parentComment.id">
        <form:option value="">&nbsp;</form:option>
        <form:options items="${comments}" itemValue="id" itemLabel="content"/>
    </form:select>
    <br/>
    <button type="submit">Create</button>
</form:form>
<a href="<c:url value='/comment'/>">Cancel</a>
</body>
</html>

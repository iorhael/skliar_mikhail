<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Post</h1>
<form:form action="insert" method="post" modelAttribute="post">
    Title:
    <form:input path="title" />
    <form:errors path="title" />
    <br/>
    Content:
    <form:input path="content" />
    <form:errors path="content" />
    <br/>
    Publication Date:
    <form:input path="publicationDate"/>
    <form:errors path="publicationDate"/>
    <br/>
    Author:
    <form:select path="author.id">
        <form:options items="${authors}" itemValue="id" itemLabel="username"/>
    </form:select>
    <br/>
    Subscription Plan:
    <form:select path="subscriptionPlan.id">
        <form:options items="${subscriptionPlans}" itemValue="id" itemLabel="name"/>
    </form:select>
    <br/>
    <button type="submit">Create</button>
</form:form>
<a href="<c:url value='/post'/>">Cancel</a>
</body>
</html>

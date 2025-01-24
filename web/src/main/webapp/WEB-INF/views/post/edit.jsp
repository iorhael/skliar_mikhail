<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Post</h1>
<form:form action="update" method="post" modelAttribute="post">
    <input type="hidden" name="id" value="${id}"/>
    Title:
    <form:input path="title"/>
    <form:errors path="title"/>
    <br/>
    Content:
    <form:input path="content"/>
    <form:errors path="content"/>
    <br/>
    Publication Date:
    <form:input path="publicationDate"/>
    <form:errors path="publicationDate"/>
    <br/>
    <button type="submit">Submit</button>
</form:form>
<a href="<c:url value='/post'/>">Cancel</a>
</body>
</html>

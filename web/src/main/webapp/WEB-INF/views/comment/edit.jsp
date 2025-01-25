<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Comment</h1>
<form:form action="update" method="post" modelAttribute="comment">
    <input type="hidden" name="id" value="${id}"/>
    Content:
    <form:input path="content"/>
    <form:errors path="content"/>
    <br/>
    <button type="submit">Submit</button>
</form:form>
<a href="<c:url value='/comment'/>">Cancel</a>
</body>
</html>

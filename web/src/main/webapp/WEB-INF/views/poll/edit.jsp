<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Poll</h1>
<form:form action="update" method="post" modelAttribute="poll">
    <input type="hidden" name="id" value="${id}"/>
    Description:
    <form:input path="description"/>
    <form:errors path="description"/>
    <br/>
    <button type="submit">Submit</button>
</form:form>
<a href="<c:url value='/poll'/>">Cancel</a>
</body>
</html>

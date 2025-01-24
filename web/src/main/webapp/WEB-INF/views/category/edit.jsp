<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Category</h1>
<form:form action="update" method="post" modelAttribute="category">
    <input type="hidden" name="id" value="${id}"/>
    Name:
    <form:input path="name" />
    <form:errors path="name" />
    <br/>
    Description:
    <form:input path="description" />
    <form:errors path="description" />
    <br/>
    Parent Category:
    <form:select path="parentCategory.id">
        <form:option value="">&nbsp;</form:option>
        <form:options items="${categories}" itemValue="id" itemLabel="name"/>
    </form:select>
    <br/>
    <button type="submit">Submit</button>
</form:form>
<a href="<c:url value='/category'/>">Cancel</a>
</body>
</html>

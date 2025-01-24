<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Create Category</title>
</head>
<body>
<h1>Create Category</h1>
<form:form action="insert" method="post" modelAttribute="category">
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
    <button type="submit">Create</button>
</form:form>
<a href="<c:url value='/category'/>">Cancel</a>
</body>
</html>

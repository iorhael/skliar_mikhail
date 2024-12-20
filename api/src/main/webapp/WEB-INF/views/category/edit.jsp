<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Category</h1>
<form action="update" method="post">
    <input type="hidden" name="id" value="${category.id}"/>
    <label for="name">Name</label>
    <input type="text" id="name" name="name" value="${category.name}" required/>
    <br/>
    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="${category.description}" required/>
    <br/>
    <label for="parentId">Parent Id</label>
    <input type="text" id="parentId" name="parentId"/>
    <br/>
    <button type="submit">Update</button>
</form>
<a href="<c:url value='/category'/>">Cancel</a>
</body>
</html>

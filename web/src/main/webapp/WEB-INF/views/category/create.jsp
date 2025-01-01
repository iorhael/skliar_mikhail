<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Category</h1>
<form action="insert" method="post">
    <label for="name">Name</label>
    <input type="text" id="name" name="name" required/>
    <br/>
    <label for="description">Description</label>
    <input type="text" id="description" name="description" required/>
    <br/>
    <label for="parentId">Parent Id</label>
    <input type="text" id="parentId" name="parentId"/>
    <br/>
    <button type="submit">Create</button>
</form>
<a href="<c:url value='/category'/>">Cancel</a>
</body>
</html>

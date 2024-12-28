<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Poll</h1>
<form action="insert" method="post">
    <label for="postId">Post Id</label>
    <input type="text" id="postId" name="postId" required/>
    <br/>
    <label for="authorId">Author Id</label>
    <input type="text" id="authorId" name="authorId" required/>
    <br/>
    <label for="description">Description</label>
    <input type="text" id="description" name="description" required/>
    <br/>
    <button type="submit">Create</button>
</form>
<a href="<c:url value='/poll'/>">Cancel</a>
</body>
</html>

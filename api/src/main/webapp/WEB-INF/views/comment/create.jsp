<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Comment</h1>
<form action="insert" method="post">
    <label for="postId">Post Id</label>
    <input type="text" id="postId" name="postId" required/>
    <br/>
    <label for="authorId">Author Id</label>
    <input type="text" id="authorId" name="authorId" required/>
    <br/>
    <label for="content">Content</label>
    <input type="text" id="content" name="content" required/>
    <br/>
    <label for="parentId">Parent Id</label>
    <input type="text" id="parentId" name="content"/>
    <br/>
    <button type="submit">Create</button>
</form>
<a href="<c:url value='/comment'/>">Cancel</a>
</body>
</html>

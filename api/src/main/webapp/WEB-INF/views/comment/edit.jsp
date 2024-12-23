<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Comment</h1>
<form action="update" method="post">
    <input type="hidden" name="id" value="${comment.id}"/>
    <label for="content">Content</label>
    <input type="text" id="content" name="content" value="${comment.content}" required/>
    <br/>
    <button type="submit">Update</button>
</form>
<a href="<c:url value='/comment'/>">Cancel</a>
</body>
</html>

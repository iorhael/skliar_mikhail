<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Poll</h1>
<form action="update" method="post">
    <input type="hidden" name="id" value="${poll.id}"/>
    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="${poll.description}" required/>
    <br/>
    <button type="submit">Update</button>
</form>
<a href="<c:url value='/poll'/>">Cancel</a>
</body>
</html>

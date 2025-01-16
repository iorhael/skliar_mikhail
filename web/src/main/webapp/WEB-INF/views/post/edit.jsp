<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Post</h1>
<form action="update" method="post">
    <input type="hidden" name="id" value="${post.id}"/>
    <label for="title">Title</label>
    <input type="text" id="title" name="title" value="${post.title}" required/>
    <br/>
    <label for="content">Content</label>
    <input type="text" id="content" name="content" value="${post.content}" required/>
    <br/>
    <label for="publicationDate">Publication Date</label>
    <input type="datetime-local" id="publicationDate" name="publicationDate" value="${post.publicationDate}" required/>
    <br/>
    <button type="submit">Update</button>
</form>
<a href="<c:url value='/post'/>">Cancel</a>
</body>
</html>

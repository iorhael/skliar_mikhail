<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Post</h1>
<form action="insert" method="post">
    <label for="authorId">Author Id</label>
    <input type="text" id="authorId" name="authorId" required/>
    <br/>
    <label for="title">Title</label>
    <input type="text" id="title" name="title" required/>
    <br/>
    <label for="content">Content</label>
    <input type="text" id="content" name="content" required/>
    <br/>
    <label for="publicationDate">Publication Date</label>
    <input type="datetime-local" id="publicationDate" name="publicationDate" required/>
    <br/>
    <label for="subscriptionPlanId">Subscription Plan Id</label>
    <input type="text" id="subscriptionPlanId" name="subscriptionPlanId" required/>
    <br/>
    <button type="submit">Create</button>
</form>
<a href="<c:url value='/post'/>">Cancel</a>
</body>
</html>

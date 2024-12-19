<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New User</h1>
<form action="insert" method="post">
    <label for="username">Name</label>
    <input type="text" id="username" name="username" required/>
    <br/>
    <label for="email">Email</label>
    <input type="email" id="email" name="email" required/>
    <br/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" required/>
    <br/>
    <button type="submit">Create</button>
</form>
<a href="<c:url value='/user'/>">Cancel</a>
</body>
</html>

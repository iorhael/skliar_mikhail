<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit User</h1>
<form action="update" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <label for="username">Name</label>
    <input type="text" id="username" name="username" value="${user.username}" required/>
    <br/>
    <label for="email">Email</label>
    <input type="email" id="email" name="email" value="${user.email}" required/>
    <br/>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" required/>
    <br/>
    <button type="submit">Update</button>
</form>
<a href="<c:url value='/user'/>">Cancel</a>
</body>
</html>

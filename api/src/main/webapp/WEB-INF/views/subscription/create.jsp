<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Subscription</h1>
<form action="insert" method="post">
    <label for="userId">User Id</label>
    <input type="text" id="userId" name="userId" required/>
    <br/>
    <label for="subscriptionPlanId">Subscription Plan Id</label>
    <input type="text" id="subscriptionPlanId" name="subscriptionPlanId" required/>
    <br/>
    <label for="expiresDate">Expires Date</label>
    <input type="datetime-local" id="expiresDate" name="expiresDate" required/>
    <br/>
    <button type="submit">Create</button>
</form>
<a href="<c:url value='/subscription'/>">Cancel</a>
</body>
</html>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Edit Subscription</h1>
<form action="update" method="post">
    <input type="hidden" name="id" value="${subscription.id}"/>
    <label for="subscriptionPlanId">Subscription Plan Id</label>
    <input type="text" id="subscriptionPlanId" name="subscriptionPlanId" required/>
    <br/>
    <label for="expiresDate">Expires Date</label>
    <input type="datetime-local" id="expiresDate" name="expiresDate" value="${subscription.expiresDate}" required/>
    <br/>
    <button type="submit">Update</button>
</form>
<a href="<c:url value='/subscription'/>">Cancel</a>
</body>
</html>

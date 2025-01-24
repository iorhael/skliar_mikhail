<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<h1>Create New Subscription</h1>
<form:form action="insert" method="post" modelAttribute="subscription">
    Expires Date:
    <form:input path="expiresDate"/>
    <form:errors path="expiresDate"/>
    <br/>
    User:
    <form:select path="user.id">
        <form:options items="${users}" itemValue="id" itemLabel="username"/>
    </form:select>
    <br/>
    Subscription Plan:
    <form:select path="subscriptionPlan.id">
        <form:options items="${subscriptionPlans}" itemValue="id" itemLabel="name"/>
    </form:select>
    <br/>
    <button type="submit">Create</button>
</form:form>
<a href="<c:url value='/subscription'/>">Cancel</a>
</body>
</html>

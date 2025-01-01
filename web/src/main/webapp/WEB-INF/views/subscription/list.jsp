<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Blog Platform</title>
</head>
<body>
<div>
    <table>
        <caption>
            <h2>List of Subscriptions</h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>Started Date</th>
            <th>Expires Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="subscription" items="${listSubscriptions}">
            <tr>
                <td><c:out value="${subscription.id}"/></td>
                <td><c:out value="${subscription.startedDate}"/></td>
                <td><c:out value="${subscription.expiresDate}"/></td>
                <td>
                    <a href="subscription/edit?id=<c:out value='${subscription.id}' />">Edit</a>
                    <a href="subscription/delete?id=<c:out value='${subscription.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="subscription/new">Add New Subscription</a>
</body>
</html>

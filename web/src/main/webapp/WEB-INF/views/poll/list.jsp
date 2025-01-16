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
            <h2>List of Polls</h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>Author Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="poll" items="${listPolls}">
            <tr>
                <td><c:out value="${poll.id}"/></td>
                <td><c:out value="${poll.authorName}"/></td>
                <td><c:out value="${poll.description}"/></td>
                <td>
                    <a href="poll/edit?id=<c:out value='${poll.id}' />">Edit</a>
                    <a href="poll/delete?id=<c:out value='${poll.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="poll/new">Add New Poll</a>
</body>
</html>

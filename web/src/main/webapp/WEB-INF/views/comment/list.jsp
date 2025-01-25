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
            <h2>List of Comments</h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>Author Name</th>
            <th>Content</th>
            <th>Created Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td><c:out value="${comment.id}"/></td>
                <td><c:out value="${comment.authorName}"/></td>
                <td><c:out value="${comment.content}"/></td>
                <td><c:out value="${comment.createdDate}"/></td>
                <td>
                    <a href="comment/edit?id=<c:out value='${comment.id}' />">Edit</a>
                    <a href="comment/delete?id=<c:out value='${comment.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="comment/new">Add New Comment</a>
</body>
</html>

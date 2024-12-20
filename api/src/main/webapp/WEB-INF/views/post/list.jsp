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
            <h2>List of Posts</h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>Author Id</th>
            <th>Title</th>
            <th>Content</th>
            <th>Publication Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="post" items="${listPosts}">
            <tr>
                <td><c:out value="${post.id}"/></td>
                <td><c:out value="${post.authorId}"/></td>
                <td><c:out value="${post.title}"/></td>
                <td><c:out value="${post.content}"/></td>
                <td><c:out value="${post.publicationDate}"/></td>
                <td>
                    <a href="post/edit?id=<c:out value='${post.id}' />">Edit</a>
                    <a href="post/delete?id=<c:out value='${post.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="post/new">Add New Post</a>
</body>
</html>

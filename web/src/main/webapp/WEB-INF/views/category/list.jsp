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
            <h2>List of Categories</h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><c:out value="${category.id}"/></td>
                <td><c:out value="${category.name}"/></td>
                <td><c:out value="${category.description}"/></td>
                <td>
                    <a href="category/edit?id=<c:out value='${category.id}' />">Edit</a>
                    <a href="category/delete?id=<c:out value='${category.id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="category/new">Add New Category</a>
</body>
</html>

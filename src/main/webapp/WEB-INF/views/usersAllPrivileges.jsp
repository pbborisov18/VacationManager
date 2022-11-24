<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users</title>
</head>

<body>

<form name = "form" action="/users" method="post">
    <button name="back" type="submit" value="Back" formaction="/users/back">Back</button>
    <button type="submit" name="addUser" value="Add User" formaction="/users/addUser">Add User</button>

    <table border="1">

        <thead>
        <tr>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role</th>
            <th>Team</th>
            <th>Project</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
            <td>${user.getUsername()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getRole().getName()}</td>
            <td>${user.getTeam().getName()}</td>
            <td>${user.getTeam().getProject().getName()}</td>
            </tr>
        </c:forEach>
        </tbody>



    </table>
</form>

</body>
</html>



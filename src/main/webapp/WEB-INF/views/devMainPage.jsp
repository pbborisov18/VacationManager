<html>
<head>
    <title>Developer Main Page</title>
</head>
<body>
    <p>Welcome ${username}!</p>

    <form action="/Dev" method="post">
        <button type="submit" name="leaves" value="Leaves" disabled>Leaves</button>
        <button type="submit" name="users" value="Users">Users</button>
        <button type="submit" name="teams" value="Teams">Teams</button>
        <button type="submit" name="projects" value="Projects" disabled>Projects</button>
        <button type="submit" name="logout" value="Logout">Log Out</button>
    </form>
</body>
</html>

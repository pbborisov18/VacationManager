<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Projects</title>
</head>
<body>
<form name = "form" action="/projects" method="post">
    <button name="back" type="submit" value="Back" formaction="/projects/back">Back</button>
    <button type="submit" name="addProjectForward" value="Add Project" formaction="/projects/addProject">Add Team</button>

    <table border="1">

        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Teams</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${projects}" var="project">
            <tr>
                <td>${project.getName()}</td>
                <td>${project.getDescription()}</td>
                <td>
                    <c:forEach items="${project.getTeamList()}" var="team">
                        ${team.getName()}
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>



    </table>
</form>
</body>
</html>
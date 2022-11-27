<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Team</title>
</head>
<body>

<form action = "/teams/addTeam" method="post">
    <input type="text" name="name" placeholder="Name">
    <br>
    <label for="projects">Choose a project:</label>
    <select name="projects" id="projects">
        <c:forEach items="${projects}" var="project">
            <option value=${project.getName()} > ${project.getName()}</option>
        </c:forEach>
    </select>
    <br>
    <!-- useless until I figure out how to send a post request with jsp using a value from js-->
    <button disabled name="cancel" value="Cancel">Add employees to the team</button>

    <br>
    <button type="submit" name="addTeamButton" value="Add Team Button">Add Team</button>
    <button type="submit" name="cancel" value="Cancel">Cancel</button>
</form>

</body>
</html>

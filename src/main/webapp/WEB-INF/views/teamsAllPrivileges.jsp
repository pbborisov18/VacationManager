<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Teams</title>
</head>
<body>

<form name = "form" action="/teams" method="post">
  <button name="back" type="submit" value="Back" formaction="/teams/back">Back</button>
  <button type="submit" name="addTeam" value="Add Team" formaction="/teams/addTeam">Add Team</button>

  <table border="1">

    <thead>
    <tr>
      <th>Name</th>
      <th>Project</th>
      <th>Developers</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${teams}" var="team">
      <tr>
        <td>${team.getName()}</td>
        <td>${team.getProject().getName()}</td>
        <td>
            <c:forEach items="${team.getUserList()}" var="user">
              ${user.getUsername()}
            </c:forEach>
        </td>
      </tr>
    </c:forEach>
    </tbody>



  </table>
</form>

</body>
</html>

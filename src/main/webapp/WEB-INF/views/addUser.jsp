<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>

<form action = "/users/addUser" method="post">
  <input type="text" name="username" placeholder="Username">
  <br>
  <input type="password" name="password" placeholder="Password">
  <br>
  <input type="text" name="firstName" placeholder="FirstName">
  <br>
  <input type="text" name="lastName" placeholder="LastName">
  <br>
  <label for="roles">Choose a role:</label>
  <select name="roles" id="roles">
    <c:forEach items="${roles}" var="role">
      <option value=${role.getName()}>${role.getName()}</option>
    </c:forEach>
  </select>
  <br>
  <label for="teams">Choose a team:</label>
  <select name="teams" id="teams">
    <c:forEach items="${teams}" var="team">
      <option value=${team.getName()}>${team.getName()}</option>
    </c:forEach>
  </select>
  <br>
  <label for="leadDev">Is the user Team Lead:</label>
  <select name="leadDev" id="leadDev">
      <option value="False">No</option>
      <option value="True">Yes</option>
  </select>
  <br>
  <button type="submit" name="addUserButton" value="Add User Button">Add User</button>
  <button type="submit" name="cancel" value="Cancel">Cancel</button>
</form>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xuehuasu
  Date: 25/4/2022
  Time: 下午1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1>User Update</h1>

<%
  User u=(User) session.getAttribute("user");
%>

<form method="post" action="updateUser"><!--action to link jsp  to servlet -->
  <input type="hidden" name="id" value="<%=u.getId()%>">
  Username <input type="text" name="username" value="<%=u.getUsername()%>"/><br/>
  password <input type="password" name="password" value="<%=u.getPassword()%>"/><br/>
  Email <input type="text" name="email" value="<%=u.getEmail()%>"/><br/>

  Gender : <input type="radio" name="gender" value="male" <%="male".equals(u.getGender())?"checked":""%>>male
  <input type="radio" name="gender" value="female" <%="female".equals(u.getGender())?"checked":""%>>Female <br/>

  Birth Date <input type="text" name="birthDate" value="<%=u.getBirthdate()%>"/>(yyyy-mm-dd)<br/>
  <input type="submit" value="Save Changes"/>

</form>

<%@include file="footer.jsp"%>

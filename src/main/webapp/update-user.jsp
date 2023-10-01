<!-- Marius Topor -->
<%-- 
    Document   : update-user
    Created on : 22 Nov 2022, 13:34:51
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<jsp:include page="admin-tasks.jsp"/>
<h1>Admin Area</h1>
<h2>Update Users</h2>

<div>
    <%if (session.getAttribute("success") != null) {%>
    <p style="color:green"><%= session.getAttribute("success")%></p>
    <% session.removeAttribute("success");
       } %>

    <% if (session.getAttribute("error") != null) {%>
    <p style="color: red"> <%= session.getAttribute("error")%></p>
    <% session.removeAttribute("error");
       }%>
</div>


<form action="UserServlet" method="post">
    <label>Enter name</label><br><!-- comment -->
    <input type="text" name="name" value="${user.name}" placeholder="Enter name">
    <br>
    <br>
    <label>Enter email</label><br><!-- comment -->
    <input type="email" name="email" value="${user.email}" placeholder="Enter email">
    <br>
    <br>
    <input type="text" name="id" value="${user.id}" hidden>
    <button name="update-user" type="submit" value="update-user">Update User</button> 
</form>

<jsp:include page="footer.jsp"/>
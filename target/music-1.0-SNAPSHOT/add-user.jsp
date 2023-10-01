<!-- Marius Topor -->
<%-- 
    Document   : add-user
    Created on : 24 Nov 2022, 13:56:43
    Author     : mariu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>


<div class="row d-flex justify-content-center pt-2 ">
    <div class="col-sm-2 card justify-content-center align-items-center rounded-0 border rounded-start bg-gray">
        <h2 class="d-flex fs-4 p-0 justify-content-center fw-light">Add User</h2>

    </div>
    <div class="col-sm-8 card p-4 border-start-0 rounded-0 rounded-end ">
        <div class="d-flex fs-4 p-0 justify-content-center fw-light">
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
            <div class="d-flex justify-content-between">
                <div class="mb-3 col-5 ">
                    <label>Enter name</label>
                    <input class="form-control" type="text" name="name" value="" placeholder="Enter name"><!-- comment -->
                </div>
                <div class="mb-3 col-5 ">
                    <label>Enter email</label>
                    <input class="form-control" type="email" name="email" value="" placeholder="Enter email"><!-- comment -->
                </div>
            </div>
            <div class="d-flex justify-content-between">
                <div class="mb-3 col-5 ">
                    <label>Enter password</label>
                    <input class="form-control" type="password" name="password" value="" placeholder="Enter password"><!-- comment -->
                </div>
                <div class="mb-3 col-5 ">
                    <label>Confirm password</label>
                    <input class="form-control" type="password" name="cpassword" value="" placeholder="Confirm password"><!-- comment -->
                </div>
                </div>
                <div class="p-3  mt-4 col-12 d-flex justify-content-center border">
                    <button class="btn btn-outline-primary w-50" name="save-user" type="submit" value="save-user">Add User</button> 
                </div>
            
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>
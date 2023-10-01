<!--Marius Topor-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<div class="container  bg-white vh-100">
    <div class=" container row d-flex justify-content-center pt-2  position-absolute top-50 start-50 translate-middle ">
        <div class="col-sm-2 card justify-content-center align-items-center rounded-0 border rounded-start bg-gray">
            <h2 class="d-flex fs-4 p-0 justify-content-center fw-light">Register</h2>

        </div>
        <div class="col-sm-6 card p-4 border-start-0 rounded-0 rounded-end ">
            <div >
                <%if (session.getAttribute("success") != null) {%>
                <p class="alert alert-success"><%= session.getAttribute("success")%></p>
                <% session.removeAttribute("success");
        } %>

                <% if (session.getAttribute("error") != null) {%>
                <p class="alert alert-danger"> <%= session.getAttribute("error")%></p>
                <% session.removeAttribute("error");
        }%>
            </div>

            <form action="RegisterServlet" method="post">
                <div class="mb-3 col-12 ">
                    <label>Enter name</label>
                    <input class="form-control" type="text" name="name" value="" placeholder="Enter name"><!-- comment -->
                </div>
                <div class="mb-3 col-12 ">
                    <label>Enter email</label>
                    <input class="form-control" type="email" name="email" value="" placeholder="Enter email"><!-- comment -->
                </div>
                <div class="mb-3 col-12 ">
                    <label>Enter password</label>
                    <input class="form-control" type="password" name="password" value="" placeholder="Enter password"><!-- comment -->
                </div>
                <div class="mb-3 col-12 ">
                    <label>Confirm password</label>
                    <input class="form-control" type="password" name="cpassword" value="" placeholder="Confirm password"><!-- comment -->
                </div>
                <div class="p-3 mb-3 col-12 d-flex justify-content-between  align-items-center">
                    <div class="">
                        <button class="btn btn-outline-primary me-2" type="submit">Register</button> 
                    </div>
                    <div>
                        Already have an account? <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

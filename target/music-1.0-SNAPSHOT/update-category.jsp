<%-- 
    Document   : add-user
    Created on : 27 Nov 2022, 07:56:13
    Author     : 44796
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>



<div class="row d-flex justify-content-center pt-2 ">
    <div class="col-sm-2 card justify-content-center align-items-center rounded-0 border rounded-start bg-gray">
        <h2 class="d-flex fs-4 p-0 justify-content-center fw-light">Update Category</h2>

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
        <form action="Category" method="post">
            <div class="d-flex justify-content-between">

                <div class="mb-3 col-5">
                    <input type="text" name="cat_id" value="${category[0].cat_id}" hidden ><!-- comment -->
                    <label>Enter category name</label>
                    <input class="form-control" type="text" name="name" value="${category[0].cat_name}" placeholder="Enter category name"><!-- comment -->
                </div>
            </div>
            <div class="b d-flex justify-content-end">
                <button class="btn btn-outline-primary " name="update-category" type="submit" value="update-category">Update Category</button> 
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>
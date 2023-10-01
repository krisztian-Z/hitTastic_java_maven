<%-- 
    Document   : add-user
    Created on : 24 Nov 2022, 12:16:03
    Author     : 44796
--%>

<%@page import="com.solent.shop.models.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>



<div class="row d-flex justify-content-center pt-2 ">
    <div class="col-sm-2 card justify-content-center align-items-center rounded-0 border rounded-start bg-gray">
        <h2 class="d-flex fs-4 p-0 justify-content-center fw-light">Add Category</h2>

    </div>
    <div class="col-sm-8 card p-4 border-start-0 rounded-0 rounded-end ">
        <div>
            <%if (session.getAttribute("success") != null) {%>
            <div class="alert alert-success d-flex align-items-center alert-dismissible fade show" role="alert">
                <%= session.getAttribute("success")%>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% session.removeAttribute("success");
                                } %>

            <% if (session.getAttribute("error") != null) {%>
            <div class="alert alert-danger d-flex align-items-center alert-dismissible fade show" role="alert">
                <%= session.getAttribute("error")%>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% session.removeAttribute("error");
                                }%>
        </div>
        <form action="Category" method="post">
            <div class="d-flex justify-content-between">

                <div class="mb-3 col-5">
                    <label>Enter category name</label>
                    <input class="form-control" type="text" name="name" value="" placeholder="Enter category name"><!-- comment -->
                </div>
            </div>
            <div class="b d-flex justify-content-end">
                <button class="btn btn-outline-primary " name="save-category" type="submit" value="save-category">Add Category</button> 
            </div>
        </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>
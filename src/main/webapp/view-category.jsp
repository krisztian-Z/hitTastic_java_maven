<%-- 
    Document   : view-products
    Created on : 6 Dec 2022, 20:48:29
    Author     : 44796 czgone
--%>



<%@page import="com.solent.shop.models.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

<div class="container">

    <%

        CategoryDao cdo = new CategoryDao();
        List<Category> catlist = cdo.getAllCategories();

    %>


    <div class="d-flex justify-content-center align-items-center">

        <div class="col-md-8 px-2">
            <div class="card " >
                <div class="row g-0">
                    <div class="col-md-4 d-flex justify-content-center align-items-center">
                        <img src="${pageContext.servletContext.contextPath}/resources/web-images/categories.png" class="img-thumbnail rounded-start border border-0 mw-40" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Categories</h5>
                            <p class="card-text">Best Music Genre</p>
                            <p class="card-text"><small class="text-muted">Buy now!</small></p>
                        </div>
                    </div>
                    <div class="col-md-12 border border-top px-5 py-2">
                        <div>
                            <%if ( session.getAttribute( "success" ) !=
                                        null ) {%>
                            <div class="alert alert-success d-flex align-items-center alert-dismissible fade show" role="alert">
                                <%= session.getAttribute( "success" )%>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <% session.removeAttribute( "success" );
                                } %>

                            <% if ( session.getAttribute( "error" ) !=
                                        null ) {%>
                            <div class="alert alert-danger d-flex align-items-center alert-dismissible fade show" role="alert">
                                <%= session.getAttribute( "error" )%>
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                            <% session.removeAttribute( "error" );
                                }%>
                        </div>
                        <% if ( catlist.size() >
                                    0 ) { %> 
                        <table  class="table scrollable">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th class="text-end mr-2">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%                                    for ( Category c
                                            : catlist ) {
                                %>
                                <tr>
                                    <td><%= c.getCat_name()%></td> 
                                    <td class="text-end">
                                        <a href="Category?action=update&id=<%= c.getCat_id()%>">Edit</a> | <!-- comment -->
                                        <a href="Category?action=delete&id=<%= c.getCat_id()%>">Delete</a><!-- comment -->
                                    </td>
                                </tr>
                                <%  }%>
                            </tbody>
                        </table>
                        <%} else { %>
                        <div class="col-md-12 d-flex justify-content-center align-items-center py-5">
                            <div class="alert alert-danger text-center">
                                <h5 class="card-title display-6">Whoops...!</h5>
                                <h1 class="card-text fw-lighter ">Apologies! We do not sell this category</h1>                           
                            </div>
                        </div>
                        <%}%>
                    </div>
                </div>
                <div class="card-footer text-muted">
                    <div class="d-flex justify-content-between align-items-center">
                        <form action="Category" method="POST" >
                            <button class="btn btn-outline-primary" name="add-categories" type="submit" value="add-categories">Add Another Category</button><!-- comment -->
                        </form>
                        <form action="Product" method="POST" >
                            <button class="btn btn-outline-primary" name="add-products" type="submit" value="add-products">Add Products</button><!-- comment -->
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>
<jsp:include page="footer.jsp"/>

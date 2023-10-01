<%-- 
    Document   : view-products
    Created on : 6 Dec 2022, 22:18:59
    Author     : 44796 c zgone
--%>

<%@page import="com.solent.shop.utils.Helper"%>
<%@page import="com.solent.shop.models.Product"%>
<%@page import="com.solent.shop.dao.ProductDao"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

<%
    ProductDao pdo = new ProductDao();
    String cat = request.getParameter("category");
    List<Product> plist = null;

    plist = pdo.getAllProducts();

    if (cat == null || cat.trim().equals("all")) {
        plist = pdo.getAllProducts();
    } else {
        plist = pdo.getProductByCategoryID(Integer.parseInt(cat));
    }

%>

<div class="container">
    <div class="d-flex justify-content-center align-items-center">
        <div class="card " >
            <div class="row g-0">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <img src="${pageContext.servletContext.contextPath}/resources/web-images/products-p.png" class="img-thumbnail rounded-start border border-0 mw-40" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Products</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">small text</small></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class=" text-primary">
                                View products for a specific category  
                            </div>
                            <div class="">
                                <button type="button" class="btn btn-outline-secondary" data-bs-toggle="modal" data-bs-target="#catModal">
                                    Choose Category
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
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
            </div>
            <!-- Products  start -->
            <%
                    if (plist.size() > 0) { %>
            <div class="px-2 overflow-scroll overflow-hidden pb-5" style="height: 50vh" >
                <%
                     for (Product c : plist) {
                %>


                <li class="col-md-12 list-group-item d-flex justify-content-between align-items-center mt-1 border bborder-bottom py-2">
                    <div class="ms-2  p-2 pe-5 col-sm-1 ">
                        <div class="ms-2">
                            <img class="cart-image px-0 py-0" src="${pageContext.servletContext.contextPath}/resources/images/products/<%= c.getImage()%>"  alt="<%= c.getImage()%>">

                        </div>
                    </div>
                    <div class="row d-flex justify-content-between col-sm-11 ">
                        <div class="row col-sm-2 ">
                            <span class="fw-semibold text-small">Name</span>
                            <span class="fw-light text-small"> <%= c.getName()%></span>
                        </div>
                        <div class="row col-sm-4 ">
                            <span class="fw-semibold text-small">Description</span>
                            <span class="fw-light text-small"> <%= Helper.reduceDescriptionToGivenSize(c.getProduct_description(), 10)%></span>
                        </div>
                        <div class="row col-sm-2 text-center">
                            <span class="fw-semibold text-small">Price</span>
                            <span class="fw-light text-small"> <%= Helper.priceFormaterTwoDecimalPlaces( 
                                                   c.getUnit_price())%></span>
                        </div>
                        <div class=" row col-sm-2 text-center">
                            <span class="fw-semibold text-small">Quantity</span>
                            <span class="fw-light text-small"><%= c.getQuantity()%> </span>
                        </div>
                        <div class=" row col-sm-2 text-center">
                            <span class="fw-semibold text-small">Actions</span>

                            <div class="d-flex justify-content-around ">
                                <a href="Products?action=update&id=<%= c.getProduct_id()%>">Edit</a> 
                                <a href="Products?action=delete&id=<%= c.getProduct_id()%>">Delete</a>
                            </div>
                        </div>

                        <!--                    <div class="d-flex justify-content-end align-items-center py-2">
                                                <div class="col-sm-2 text-center">
                                                    <a href="Products?action=update&id=<%= c.getProduct_id()%>">Edit</a> 
                                                </div>
                                                <div class="col-sm-2 text-center">
                                                    <a href="Products?action=delete&id=<%= c.getProduct_id()%>">Delete</a>
                                                </div>
                                            </div>-->
                    </div>
                </li>
                <%  }%>
                
            </div>
            <!-- Products  end -->
            <% } else { %>
                <div class="col-md-12 d-flex justify-content-center align-items-center py-5">
                    <div class="alert alert-danger text-center">
                        <h5 class="card-title display-6">Whoops...!</h5>
                        <h1 class="card-text fw-lighter ">No product found </h1>                           
                    </div>
                </div>
                <%}%>
        </div>
    </div>

</div>
<jsp:include page="/modal/cat-modal.jsp"/>
<jsp:include page="footer.jsp"/>

<%-- 
    Document   : admin-tasks
    Created on : 20 Nov 2022, 16:31:47
    Author     : 44796
--%>

<%@page import="com.solent.shop.models.User"%>
<%@page import="com.solent.shop.dao.UserDao"%>
<%@page import="com.solent.shop.models.Order"%>
<%@page import="com.solent.shop.dao.OrderDao"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page import="com.solent.shop.models.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.ProductDao"%>
<% 
    
// Category Count
CategoryDao cdao = new CategoryDao();
List<Category> clist = cdao.getAllCategories();
int total_cats = clist.size();

// Products Count
ProductDao pdao = new ProductDao();
List<Product> plist = pdao.getAllProducts();
int total_products = plist.size();

// Customers count
UserDao udao = new UserDao();
List<User> ulist = udao.getAllUsers();
int total_customers = ulist.size();

// Orders Count
OrderDao odao = new OrderDao();
List<Order> olist = odao.getOrders();
int total_orders = olist.size();



%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
    <div class="col-lg-12 d-flex justify-content-around mt-5">
        <div class="card mb-0 col-lg-5" >
            <div class="row g-0">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <img src="${pageContext.servletContext.contextPath}/resources/web-images/categories.png" class="img-fluid rounded px-1">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title d-flex justify-content-between align-items-center">
                            <span>Music Genre </span>
                            <span class="badge text-bg-primary"><%= total_cats %></span>
                        </h5>
                        <p class="card-text">Here you will find best music from 80's until present!</p>
                        <p class="card-text"><small class="text-muted">We have  <%= total_cats %> products  available.</small></p>
                    </div>
                </div>
            </div>
            <div class="card-footer text-muted">
                <div class="col-12 d-flex justify-content-between align-items-center">
                    <form action="Category" method="POST" >
                        <button class="btn btn-outline-primary" name="add-categories" type="submit" value="add-categories">Add Genre</button><!-- comment -->
                    </form>
                    <form action="Category" method="POST" >
                        <button class="btn btn-outline-primary" name="view-categories" type="submit" value="view-categories">View Genre</button><!-- comment -->
                    </form>

                </div>
            </div>
        </div>
        <div class="card mb-0 col-lg-5">
            <div class="row g-0">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    
                    <img src="${pageContext.servletContext.contextPath}/resources/web-images/product.png" class="img-fluid rounded px-1">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title d-flex justify-content-between align-items-center">
                            <span>Products </span>
                            <span class="badge text-bg-primary"><%= total_products %></span>
                        </h5>
                        <p class="card-text">Here you will find best music from 80's until present!</p>
                        <p class="card-text"><small class="text-muted">We have  <%= total_products %> products  available.</small></p>
                    </div>
                </div>
            </div>

            <div class="card-footer text-muted">
                <div class="col-12 d-flex justify-content-between align-items-center">

                    <form action="Products" method="POST" >
                        <button class="btn btn-outline-primary" name="add-products" type="submit" value="add-products">Add Products</button><!-- comment -->
                    </form>
                    <form action="Products" method="POST" >
                        <button class="btn btn-outline-primary" name="view-products" type="submit" value="view-products">View Products</button><!-- comment -->
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-12 d-flex justify-content-around mt-5">
        <div class="card mb-0 col-lg-5">
            <div class="row g-0">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <img src="${pageContext.servletContext.contextPath}/resources/web-images/customers.jpeg" class="img-fluid rounded px-1">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title d-flex justify-content-between align-items-center">
                            <span>Customers </span>
                            <span class="badge text-bg-primary"><%= total_customers %></span>
                        </h5>
                        <p class="card-text"> Here you will find our custemers list!</p>
                        <p class="card-text"><small class="text-muted">We have  <%= total_customers %> value customers.</small></p>
                    </div>
                </div>
            </div>
            <div class="card-footer text-muted">
                <div class="col-12 d-flex justify-content-between align-items-center">

                    <form action="UserServlet" method="POST">
                        <button class="btn btn-outline-primary" name="add-user" type="submit" value="add-user">Add User</button><!-- comment -->
                    </form>
                    <form action="UserServlet" method="POST" style="margin-right: 2rem;">
                        <button class="btn btn-outline-primary" name="view-users" type="submit" value="view-users">View Customers</button><!-- comment -->
                    </form>
                </div>
            </div>
        </div>

        <div class="card mb-0 col-lg-5" >
            <div class="row g-0">
                <div class="col-md-4 d-flex justify-content-center align-items-center ">
                    <img src="${pageContext.servletContext.contextPath}/resources/web-images/shopping.png" class="img-fluid rounded px-1">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title d-flex justify-content-between align-items-center">
                            <span>Orders </span>
                            <span class="badge text-bg-primary"><%= total_orders %></span>
                        </h5>
                        <p class="card-text">Here you will find all our customers orders!</p>
                        <p class="card-text"><small class="text-muted">we have  <%= total_orders %> pending  orders.</small></p>
                    </div>
                </div>
            </div>
            <div class="card-footer text-muted">
                <div class="col-12 d-flex justify-content-between align-items-center">

                    <form action="Order" method="POST">
                        <button class="btn btn-outline-primary" name="orders" type="submit" value="orders">View Orders</button><!-- comment -->
                    </form>
                    <form action="UserServlet" method="POST">
                        <button class="btn btn-outline-primary" name="view-users" type="submit" value="view-users">Order By Customer</button><!-- comment -->
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 
    Document   : top-nav
    Created on : 3 Jan 2023, 10:57:31
    Author     : 44796
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="p-1 pb-3 pt-3 border-bottom mb-2 sticky-top bg-gray">
    <div class="d-flex flex-wrap justify-content-between align-items-center justify-content-center ">
        <div class="d-flex justify-content-between">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.servletContext.contextPath}/" class="nav-link px-2 link-dark active text-danger">Hit Tastic</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/Products" class="nav-link px-2 link-info">Products</a></li>
                 <% 
                    if (session.getAttribute("name") != null) {
                       Object  user_role = session.getAttribute("user_role");
                    if ( user_role.toString().equals("1")) {
                 %>
                <li><a href="${pageContext.servletContext.contextPath}/Dashboard.jsp" class="nav-link px-2 link-info">Dashboard</a></li>
                <% }}%>
            </ul>
        </div>
            
        <div class="d-flex justify-content-between align-items-center">
            <form action="search" class="col-12 col-lg-auto mb-lg-0 me-lg-3 align-items-center" role="search" method="POST">
                <div class="input-group text-sm fw-dark ">
                    <input type="text" class="form-control" placeholder="Search" name="search" aria-label="Recipient's username" aria-describedby="button-addon2">
                    <button class="d-flex btn btn-outline-secondary justify-content-between align-items-center" type="submit" id="button-addon2">
                        <ion-icon name="search-outline"></ion-icon>
                    </button>
                </div>
            </form>

            <% if (session.getAttribute("name") != null) {%>

            <div class="dropdown text-end">
                <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.servletContext.contextPath}/resources/user/avatar.png" alt="user" width="32" height="32" class="rounded-circle">
                    <%= session.getAttribute("name") %>
                </a>
                    
                
                <ul class="dropdown-menu text-small">
                    <li class="dropdown-item credit-amount">Available Credit: <%= session.getAttribute( "balance" ) %></li>
                    <li><a class="dropdown-item" href="orders.jsp">My Orders</a></li>
<!--                    <li><a class="dropdown-item" href="#">Settings</a></li>
                    <li><a class="dropdown-item" href="#">Profile</a></li>-->
                    <li><hr class="dropdown-divider">  </li>
                    <li class="text-center">
                        <form action="Logout" method="POST">
                            <button class="btn btn-outline-primary  text-center" type="submit">LogOut</button><!-- comment -->
                        </form>
                    </li>
                </ul> 
            </div>

            <% } else { %>
            <div class="text-end">
                <a class="btn btn-outline-info mx-2" href="login.jsp">Login</a>
                <a class="btn btn-outline-info mx-2" href="register.jsp">Register</a>
            </div>
            <% }%>
            <div id="cart" data-bs-toggle="modal" data-bs-target="#product-cart" class="mx-4 d-flex flex-wrap align-items-center justify-content-center btn btn-outline-info " >
                <ion-icon name="cart-outline" class="fs-5" ></ion-icon>
                <span class="badge rounded-pill text-bg-warning" id="cart_items"></span>
            </div>

        </div>
    </div>
    <!--</div>-->
</nav>

<!-- Toast message Start -->
<div id="toast">
    <div id="img" class="bg-warning text-white">CART</div>
    <div id="desc"></div>    
</div>

<!-- cart modal Start -->
<!-- Button trigger modal -->


<!-- Cart Modal -->
<div class="modal fade" id="product-cart" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="d-flex modal-header bg-warning justify-content-between align-items-center">
                <h1 class="d-flex modal-title fs-4  align-items-center" id="staticBackdropLabel"> 
                    <span>Your Shopping Cart</span>
                </h1>
                <div class="d-flex justify-content-start align-items-center">
                    <ion-icon name="cart-outline" class="fs-3" ></ion-icon> 
                    <span class="badge rounded-pill fw-light  text-bg-dark mb-2 mr-2 " id="scart_items">10</span>
                </div>
            </div>
            <div class="modal-body cart-body" >

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-outline-success checkout" onclick="window.location = 'checkout.jsp'">Checkout</button>
            </div>
        </div>
    </div>
</div>
<!-- cart modal End -->



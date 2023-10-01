<%-- 
    Document   : orders
    Created on : 03 Dec 2022, 16:24:37
    Author     : 44796
--%>
<%@page import="com.solent.shop.models.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.solent.shop.models.OrdersDetail"%>
<%@page import="com.solent.shop.models.Order"%>
<%@page import="com.solent.shop.dao.OrderDao"%>
<%@page import="com.solent.shop.utils.Helper"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page import="com.solent.shop.models.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.ProductDao"%>
<%@page import="com.solent.shop.utils.Helper"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>

<% if ( session.getAttribute( "name" ) !=
            null ) {%>

<div>
    <%if ( session.getAttribute( "success" ) !=
                null ) {%>
    <p style="color:green"><%= session.getAttribute( "success" )%></p>
    <% session.removeAttribute( "success" );
        } %>

    <% if ( session.getAttribute( "error" ) !=
                null ) {%>
    <p style="color: red"> <%= session.getAttribute( "error" )%></p>
    <% session.removeAttribute( "error" );
        }%>
</div>
<div class="container  my-md-4  overflow-scroll overflow-hidden height-90">


    <%
        String email = null;
        String orderDetail_id = null;
        String order_id = null;
        String customer = null;
        OrderDao orderDao = new OrderDao();
        List<Order> olist = null;
        List<OrdersDetail> odlist = null;
        if ( session.getAttribute( "name" ) !=
                null ) {
            Object user_role = session.getAttribute( "user_role" );
            if ( user_role.toString().equals( "1" ) ) {
                email = null;
            } else {
                email = session.getAttribute( "email" ).toString();
            }
        }

        if ( email ==
                null ) {
            odlist = orderDao.getOrdersDetail();
            OrderDao oDao = new OrderDao();
            olist = oDao.getOrders();

        } else {
            odlist = orderDao.getOrdersDetailByUser( email );
            OrderDao oDao = new OrderDao();
            olist = oDao.getOrdersByEmail( email );
        }
        for ( Order ol
                : olist ) {
            double order_total = 0;
            order_id = ol.getOrder_id();
    %>

    <div class="row mt-1">
        <ul class="list-group ">
            <div class="card ">
                <div class="card-header d-flex justify-content-start card-title text-center py-4  ">
                    <span class="col-sm-2 fw-semibold  text-success">Order ID </span>
                    <span class="px-5 col-md-8 fw-semibold text-success  "><%= ol.getOrder_id()%>
                </div>
                <div class="card-body">
                    <%  for ( OrdersDetail od
                                : odlist ) {
                                
                            orderDetail_id = od.getOrder_id();
                            if ( orderDetail_id.equals( order_id ) ) {
                                
                                customer = od.getUser_name();
                    %>

                    <li class="col-md-12 list-group-item d-flex justify-content-between align-items-center mt-1 border bborder-bottom">
                        <div class="ms-2  p-2 pe-5 col-sm-2 ">
                            <div class="ms-2">
                                <img class="cart-image px-0 py-0" src="${pageContext.servletContext.contextPath}/resources/images/products/<%= od.getProduct_image()%>"  alt="<%= od.getProduct_image()%>">
                            </div>
                        </div>
                        <div class="row d-flex justify-content-between col-sm-10 ">
                            <div class="row col-sm-4 ">
                                <span class="fw-semibold text-small">Name</span>
                                <span class="fw-light text-small"><%= od.getProduct_name()%></span>
                                
                            </div>
                            <div class="row col-sm-2 text-center">
                                <span class="fw-semibold text-small">Price</span>
                                    <span class="fw-light text-small">£ <%= Helper.priceFormaterTwoDecimalPlaces(
                                                od.getProduct_price() )%></span>
                            </div>
                            <div class=" row col-sm-2 text-center">
                                <span class="fw-semibold text-small">Quantity</span>
                                <span class="fw-light text-small"><%= od.getProduct_quantity()%></span>
                            </div>
                            <div class="row col-sm-2 text-center ">
                                <span class="fw-semibold text-small">Status</span>
                                <span class="fw-light text-small">Pending</span>
                            </div>
                            <div class=" row col-sm-2 text-center">
                                <span class="fw-semibold text-small">Item Total</span>
                                <span class="fw-light text-small">£ <%= Helper.priceFormaterTwoDecimalPlaces(
                                                od.getProduct_price() *
                                                od.getProduct_quantity() ) %></span>
                            </div>
                            <% order_total += od.getProduct_price() *
                                        od.getProduct_quantity(); %>
                        </div>
                    </li>
                    <%
                                orderDetail_id = null;
                            }

                        }%>
                    <div class="text-success col-md-12 d-flex justify-content-end px-5 align-items-center fw-semibold ">
                        <span class="fw-semibold col-sm-2 text-end">Order Total</span>
                        <span class="fw-light fw-semibold col-sm-2 text-end">£ <%= Helper.priceFormaterTwoDecimalPlaces(
                                            order_total ) %></span>

                    </div>
                </div>
                <div class="card-footer text-muted">
                    <div class="col-12 d-flex justify-content-end">
                        <div class="col-12 d-flex justify-content-end">
                            <div class="col-md-4 text-end" >
                                <span class="col-sm-12 fw-semibold  text-muted text-small">
                                    <span class="px-2">Ordered by:</span>
                                    <span class=""><%= customer.toUpperCase() %></span>
                                </span>
                            </div>

                        </div>
                    </div>
                </div>
        </ul>
    </div>
    <%
            order_id = null;
        } %>
</div>






<% } else {  %>
<jsp:forward page="login.jsp"/>
<% }%>


<jsp:include page="footer.jsp"/>


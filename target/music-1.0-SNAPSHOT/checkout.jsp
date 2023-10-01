
<%@page import="com.solent.shop.utils.Helper"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page import="com.solent.shop.models.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.ProductDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="header.jsp"/>

<% if ( session.getAttribute( "name" ) !=
            null ) {

            
   
%>

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
<div class="container bd-gutter mt-3 my-md-4 bd-layout">
    <p class="text-end"> 
        Available credit to spend: 
        <span class="badge text-bg-info"><%= session.getAttribute( "balance") %>
        </span>
    </p>
   
    <div class="row mt-2">
        <div class="col-8"> <!-- Shopping details -->
            <div class="card">
                <h5 class="bg-warning card-title text-center py-4 border-bottom">Your selected items</h5>
                <div class="card-body cart-body">  </div>
            </div>

        </div>
        <div class="col-4"><!-- Order details -->
            <div class="card">
                <h5 class="bg-success card-title text-center py-4 border-bottom">
                    Your Details for Order 
                    
                </h5>

                <div class="card-body card-body">

                   
                    <div class="mb-3">
                        <label for="name" class="form-label">Your name </label>
                        <input type="text" class="form-control" id="username" name="username" value="<%= session.getAttribute( "name" )%>" placeholder="name@example.com">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email address</label>
                        <input type="email" class="form-control" id="email"  name="email" value="<%= session.getAttribute( "email" )%>" placeholder="name@example.com">
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Shipping address</label>
                        <textarea class="form-control" id="address" name="address" rows="2" placeholder="Shipping address" required></textarea>
                    </div>
                    <div class="container text-center d-flex justify-content-between ">
                        <button type="button" class="btn btn-outline-success" onclick="window.location = 'index.jsp'" >Continue Shopping</button>
                        <button type="button" class="btn btn-outline-primary" onclick="PlaceOrder()" >Place Order</button>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<% } else {  %>
<jsp:forward page="login.jsp"/>
<% }%>


<jsp:include page="footer.jsp"/>


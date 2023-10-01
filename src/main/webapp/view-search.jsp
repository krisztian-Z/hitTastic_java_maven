<!-- Marius Topor -->


<%@page import="com.solent.shop.utils.Helper"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page import="com.solent.shop.models.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.ProductDao"%>

<jsp:include page="header.jsp"/>



    <div class="row mx-4">
        <%
 
            ProductDao pdo = new ProductDao();
            String search = request.getParameter("search");
            List<Product> plist = null;
            plist = pdo.searchProduct(search);
            
        %>
        
        <div class="col-md-12 text-center">
            <div class="row col-md-12">
                <% for (Product p : plist) {%>
                <div class="col-md-4 grid gap-3 mb-3" >
                    <div class="card" width="250px;" >

                        <img src="${pageContext.servletContext.contextPath}/resources/images/products/<%= p.getImage()%>" class="card-img-top p-4" alt="<%= p.getImage()%>">
                        <div class="card-body">
                            <h5 class="card-title p-2"><%= p.getName()%></h5>
                            <p class="card-text"><%= Helper.reduceDescriptionToGivenSize(p.getProduct_description(), 10)%></p>
                            <div class="d-flex flex-row mt-3 justify-content-between align-items-center">
                                <div class="fs-4">
                                    <span class="">£</span><span class=""><%= p.getUnit_price()%></span>
                                </div>
                                <div>
                                    <% if (p.getQuantity() > 0) { %>
                                    <span class="fs-6 text-success">In Stock</span>
                                    <% } else { %>
                                    <span class="fs-6 text-danger">Out of Stock</span>
                                    <% }%>
                                </div>

                            </div>
                        </div>
                        <div class="card-footer ">
                            <button class=" w-100 btn btn-outline-primary d-flex flex-row justify-content-between align-items-center" onClick="add_to_cart(<%= p.getProduct_id()%>, '<%= p.getImage()%>','<%= p.getName()%>', <%= p.getUnit_price()%>)">
                                <span> Add me to Cart</span>
                                <ion-icon name="cart-outline" size="large"></ion-icon>
                            </button> 
                        </div>
                    </div>
                </div>

                <%  }
                if (plist.size() == 0) { %>
                <div class="d-flex card vh-100 justify-content-center align-items-center text-danger" >
                    <h1 class="display-6"> This Item in not in our shop. <%=search %>"</h1>
                </div>
                <% }
                %>


            </div>
        </div>
    </div>

<jsp:include page="footer.jsp"/>
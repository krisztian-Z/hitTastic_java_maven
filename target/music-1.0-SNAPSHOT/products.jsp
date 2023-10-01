<%-- 
    Document   : products
    Created on : 20 Nov 2022, 10:54:27
    Author     : 44796
--%>
<%@page import="com.solent.shop.utils.Helper"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page import="com.solent.shop.models.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.ProductDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="header.jsp"/>


<div>
    <%if (session.getAttribute("success") != null) {%>
    <p style="color:green"><%= session.getAttribute("success")%></p>
    <% session.removeAttribute("success");
        } %>

    <% if (session.getAttribute("error") != null) {%>
    <p style="color: red"> <%= session.getAttribute("error")%></p>
    <% session.removeAttribute("error");
        }%>
</div>

<div class="row mx-1 my-1">
    <%
            ProductDao pdo = new ProductDao();
            String cat = request.getParameter("category");
            List<Product> plist = null;
           
            //if (cat==null) {
                plist = pdo.getAllProducts();
            //} else {
                if (cat==null || cat.trim().equals("all")) {
                    plist = pdo.getAllProducts();
                }else{
                    plist = pdo.getProductByCategoryID(Integer.parseInt(cat));
                }
            //}

        

        CategoryDao cdo = new CategoryDao();
        List<Category> clist = cdo.getAllCategories();

    %>
    <div class="col-md-2 sticky-top">
        <div class="list-group">
            <a href="index.jsp?category=all" class="list-group-item list-group-item-action active">All Products</a>
            <% for (Category c : clist) {%>
            <a href="index.jsp?category=<%=c.getCat_id()%>" class="list-group-item list-group-item-action"><%= c.getCat_name()%></a>
            <%  } %>
        </div>

    </div>
    <div class="col-md-10 overflow-scroll overflow-hidden height-90">
        <div class="row col-md-12">
            <% for (Product p : plist) {%>
            <div class="col-md-4 grid gap-3 mb-3" >
                <div class="card" >
                     
                    <img src="${pageContext.servletContext.contextPath}/resources/images/products/<%= p.getImage()%>" class="card-img-top p-1" alt="<%= p.getImage()%>">
                    <div class="card-body">
                       <h6 class="card-title p-2"><%= p.getName()%></h6>
                        <p class="card-text text-small"><%= Helper.reduceDescriptionToGivenSize(p.getProduct_description(), 10)%></p>
                        <div class="d-flex flex-row mt-3 justify-content-between align-items-center">
                            <div class="fs-6">
                                <span class="">£</span><span class=""><%= Helper.priceFormaterTwoDecimalPlaces( 
                                                   p.getUnit_price())%></span>
                            </div>
                            <div>
                                <% if (p.getQuantity() > 0) { %>
                                <span class="text-small text-success">In Stock</span>
                                <% } else { %>
                                <span class="text-small text-danger">Out of Stock</span>
                                <% }%>
                            </div>

                        </div>
                    </div>
                       <div class="card-footer ">
                            
                            <button class=" w-100 btn btn-outline-primary d-flex flex-row justify-content-between align-items-center" <%= (p.getQuantity() > 0) ? "":"disabled"%> onClick="add_to_cart(<%= p.getProduct_id()%>, '<%= p.getImage()%>','<%= p.getName()%>', <%= Helper.priceFormaterTwoDecimalPlaces( 
                                            p.getUnit_price())%>)">
                                <span> Add to Cart</span>
                                <ion-icon name="cart-outline" size="large"></ion-icon>
                            </button> 
                        </div>
                </div>
            </div>

            <%  }
            if(plist.size() == 0){ %>
            <div class="d-flex card height-50 justify-content-center align-items-center text-danger" >
               <h1 class="fw-lighter"> Product <span class="display-6 text-danger">Out Of Stock </span>for a limited time period.  </h1>
            </div>
           <% }
            %>


        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
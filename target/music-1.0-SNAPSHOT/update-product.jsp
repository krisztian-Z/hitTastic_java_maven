<%-- 
    Document   : add-user
    Created on : 22 Nov 2022, 10:36:43
    Author     : 44796
--%>

<%@page import="com.solent.shop.models.Product"%>
<%@page import="com.solent.shop.dao.ProductDao"%>
<%@page import="com.solent.shop.models.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>

<%
    int pid = Integer.parseInt(request.getParameter("id"));
    CategoryDao cdo = new CategoryDao();
            List<Category> clist = cdo.getAllCategories();
    
    ProductDao productDao = new ProductDao();
    List<Product> plist = productDao.getProductByID(pid);
    

   

%>

<div class="row d-flex justify-content-center pt-2 ">
    <div class="col-sm-2 card justify-content-center align-items-center rounded-0 border rounded-start bg-gray">
        <h2 class="d-flex fs-4 p-0 justify-content-center fw-light">Add Products</h2>

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
        <% for (Product p : plist ) { %>
        <form action="Products" method="post" enctype = "multipart/form-data">
            <input type="text" name="id"  value="<%= pid %>" hidden>
                
            
            <div class="d-flex justify-content-between">
                <div class="mb-3 col-5 ">
                    <label>Product Category</label>
                    <select name="category" class="form-select" aria-label="Default select example">
                        <option value="0" selected>Choose category</option>
                        <% for (Category c : clist) {%>
                        <option value="<%=c.getCat_id()%>" <% if(p.getCategory_id() == c.getCat_id() ){%> selected <%}%>  >
                            <%= c.getCat_name()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <div class="mb-3 col-5">
                    <label>Enter product name</label>
                    <input class="form-control" type="text" name="name" value="<%= p.getName() %>" placeholder="Enter product name"><!-- comment -->
                </div>
            </div>
            <div class="d-flex col-sm-12 justify-content-between">
                <div class="mb-3 col-5">
                    <label>Enter Quantity</label>
                    <input class="form-control" type="text" name="quantity" value="<%= p.getQuantity() %>" placeholder="Enter Quantity"><!-- comment -->
                </div>
                <div class="mb-3 col-5">
                    <label>Enter Unit price </label>
                    <input class="form-control" type="text" name="unit_price" value="<%= p.getUnit_price() %>" placeholder="Enter Unit price"><!-- comment -->
                </div>
            </div>
            <div class="mb-3">
                <label>Enter product details</label>
                <textarea class="form-control" name="product_details" placeholder="Enter product details" id="floatingTextarea2" style="height: 100px"><%= p.getProduct_description() %></textarea>
            </div>
            <div class="mb-3">
                <label for="formFile" class="form-label">Choose file to upload</label>
                <input class="form-control" type="file" name="pImage" id="formFile">
            </div>
            <div class="b d-flex justify-content-end">
                <button class="btn btn-outline-primary " name="update-product" type="submit" value="update-product">Update Product</button> 
            </div>
        </form>
                <% }%>
    </div>
</div>

<jsp:include page="footer.jsp"/>
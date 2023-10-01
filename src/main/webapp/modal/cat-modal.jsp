<%@page import="com.solent.shop.models.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.solent.shop.dao.CategoryDao"%>
<%
    CategoryDao cdo = new CategoryDao();
    List<Category> clist = cdo.getAllCategories();

%>


<!-- Modal -->
<div class="modal fade" id="catModal" tabindex="-1" aria-labelledby="catModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-center bg-warning">
                <h1 class="modal-title fs-5 fw-light w-100" id="catModalLabel">Product Categories</h1>
               
            </div>
            <div class="modal-body d-flex justify-content-around align-items-center" data-bs-dismiss="modal">
                <div class="col-md-12 row">
                    <div class="list-group">
                        <a href="view-products.jsp?category=all" class="list-group-item list-group-item-action">All Products</a>
                        <% for (Category c : clist) {%>
                        <a href="view-products.jsp?category=<%=c.getCat_id()%>" class="list-group-item list-group-item-action"><%= c.getCat_name()%></a>
                        <%  }%>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
                
            </div>
        </div>
    </div>
</div>

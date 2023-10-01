<!-- Marius Topor -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="header.jsp"/>

<div class="container">
    <div class="d-flex justify-content-center align-items-center">
        <div class="card " >
            <div class="row g-0">
                <div class="col-md-4 d-flex justify-content-center align-items-center">
                    <img src="${pageContext.servletContext.contextPath}/resources/web-images/shopping.png" class="img-thumbnail rounded-start border border-0 mw-40" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Customers</h5>
                        <p class="card-text">This is our customer's list.</p>
                        <p class="card-text"><small class="text-muted">Our customers are the best!</small></p>
                        
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
            <!-- Users  start -->
             
            <div class="px-2 overflow-scroll overflow-hidden pb-5" style="height: 50vh" >
                
                <c:forEach items="${users}" var="user" varStatus="loop" >
                <li class="col-md-12 list-group-item d-flex justify-content-between align-items-center mt-1 border bborder-bottom py-2">
                    <div class="ms-2  p-2 pe-5 col-sm-1 ">
                        <div class="ms-2">
                            <img class="cart-image px-0 py-0" src="${pageContext.servletContext.contextPath}/resources/web-images/avatar.png"  alt="avatar">

                        </div>
                    </div>
                    <div class="row d-flex justify-content-around col-sm-10 ">
                        
                        <div class="row col-sm-2 ">
                            <span class="fw-semibold text-small">Name</span>
                            <span class="fw-light text-small"> ${user.name}</span>
                        </div>
                        <div class="row col-sm-4 ">
                            <span class="fw-semibold text-small">Email</span>
                            <span class="fw-light text-small"> ${user.email}</span>
                        </div>
                        
                        <div class=" row col-sm-2 text-center">
                            <span class="fw-semibold text-small">Action</span>

                            <div class="d-flex justify-content-around ">
                                
                                <a href="UserServlet?action=view-orders&id=${user.id}">View Orders</a>
                            </div>
                        </div>

                    </div>
                </li>
                </c:forEach>
                
            </div>
            <!-- Users  end -->

        </div>
    </div>

</div>
<jsp:include page="footer.jsp"/>

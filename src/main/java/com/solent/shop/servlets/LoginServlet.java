package com.solent.shop.servlets;

import com.solent.shop.dao.ProductDao;
import com.solent.shop.dao.UserDao;
import com.solent.shop.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao uc = new UserDao();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email.isEmpty() || password.isEmpty()) {
            session.setAttribute("error", "Please fill the login details.");
            RequestDispatcher req = request.getRequestDispatcher("login.jsp");
            req.include(request, response);
        } else {
            User user = uc.LoginUser(email, password);
            if (user == null) {
                request.setAttribute("error", "Please check login details.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                session.setAttribute("Id", user.getId());
                session.setAttribute("name", user.getName());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("user_role", user.getUser_role());
                session.setAttribute("balance", uc.credit_amt);
                if (user.getUser_role() == 1) {
                    request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
                } else {
                    ProductDao pDao = new ProductDao();
                    List products = pDao.getAllProducts();
                    request.setAttribute("products", products);
                    if (!products.isEmpty()) {
                        request.getRequestDispatcher("products.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

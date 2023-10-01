
package com.solent.shop.servlets;

import com.solent.shop.dao.UserDao;
import com.solent.shop.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("view".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.getRequestDispatcher("view-users.jsp").forward(request, response);
        } else if ("delete".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            int res = this.userDao.deleteUser(id);
            List<User> users = this.userDao.getAllUsers();
            request.setAttribute("users", users);
            session.setAttribute("success", "User deleted");
            request.getRequestDispatcher("view-users.jsp").forward(request, response);
        } else if ("view-orders".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            User user = this.userDao.getUserByID(id);
            request.setAttribute("email", user.getEmail());
            request.setAttribute("uname", user.getName());
            request.getRequestDispatcher("view-user-order.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("add-user".equals(request.getParameter("add-user"))) {
            request.getRequestDispatcher("add-user.jsp").forward(request, response);
        } else if ("save-user".equals(request.getParameter("save-user"))) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cpassword = request.getParameter("cpassword");
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cpassword.isEmpty()) {
                session.setAttribute("error", "Please fill all fields");
                request.getRequestDispatcher("add-user.jsp").forward(request, response);
            } else {
                UserDao uc = new UserDao();
                String user = uc.RegisterUser(name, email, password, cpassword);
                if (user == null) {
                    session.setAttribute("error", "Whoops!... Please try again.");
                    request.getRequestDispatcher("add-user.jsp").forward(request, response);
                } else {
                    session.setAttribute("success", "User created successfully.");
                    request.getRequestDispatcher("add-user.jsp").forward(request, response);
                }
            }
        } else if ("view-users".equals(request.getParameter("view-users"))) {
            List users = this.userDao.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("view-users.jsp").forward(request, response);
        } else if ("update-user".equals(request.getParameter("update-user"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int res = this.userDao.updateUser(name, email, id);
            if (res == 1) {
                List users = this.userDao.getAllUsers();
                request.setAttribute("users", users);
                session.setAttribute("success", "User updated with success.");
                request.getRequestDispatcher("update-user.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

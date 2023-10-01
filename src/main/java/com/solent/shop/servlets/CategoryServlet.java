package com.solent.shop.servlets;

import com.google.gson.Gson;
import com.solent.shop.dao.CategoryDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CategoryServlet", urlPatterns = { "/Category" })
public class CategoryServlet extends HttpServlet {

    private CategoryDao categoryDao;

    private Gson gson = new Gson();

    @Override
    public void init() {
        categoryDao = new CategoryDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("update".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            List category = this.categoryDao.getCategoryByID(id);
            request.setAttribute("category", category);
            request.getRequestDispatcher("update-category.jsp").forward(request, response);
        } else if ("delete".equals(request.getParameter("action"))) {
            String result = this.categoryDao.DeleteCategory(Integer.parseInt(request.getParameter("id")));
            session.setAttribute("success", result);
            request.getRequestDispatcher("view-category.jsp").forward(request, response);
        } else if ("cart".equals(request.getParameter("action"))) {
        } else {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("add-categories".equals(request.getParameter("add-categories"))) {
            request.getRequestDispatcher("add-category.jsp").forward(request, response);
        } else if ("save-category".equals(request.getParameter("save-category"))) {
            this.saveCategory(request, response, session);
            request.getRequestDispatcher("view-category.jsp").forward(request, response);
        } else if ("view-categories".equals(request.getParameter("view-categories"))) {
            request.getRequestDispatcher("view-category.jsp").forward(request, response);
        } else if ("update-category".equals(request.getParameter("update-category"))) {
            this.updateCategory(request, response, session);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void saveCategory(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String cName = request.getParameter("name");
        if (cName.isEmpty()) {
            session.setAttribute("error", "Please fill all the fields.");
            request.getRequestDispatcher("add-category.jsp").forward(request, response);
        } else {
            String category = categoryDao.addCategory(cName);
            session.setAttribute("success", "Category added successfully.");
            request.getRequestDispatcher("view-category.jsp").forward(request, response);
        }
    }

    public void updateCategory(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        int cID = Integer.parseInt(request.getParameter("cat_id"));
        String cName = request.getParameter("name");
        int res = categoryDao.updateCategory(cID, cName);
        if (res == 1) {
            session.setAttribute("success", "Category updated successfully.");
            request.getRequestDispatcher("view-category.jsp").forward(request, response);
        } else {
            session.setAttribute("error", "An error occured while updating, try again.");
        }
    }
}

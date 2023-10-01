package com.solent.shop.servlets;

import com.solent.shop.dao.OrderDao;
import com.solent.shop.dao.ProductDao;
import com.solent.shop.dao.UserDao;
import static java.io.FileDescriptor.out;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class OrderServlet extends HttpServlet {

    private OrderDao orderDao;

    @Override
    public void init() {
        orderDao = new OrderDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("orders".equals(request.getParameter("orders"))) {
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        } else {
            String result = null;
            int orderResult;
            JSONObject jo = new JSONObject();
            UUID uuid = UUID.randomUUID();
            String order_id = uuid.toString().toUpperCase();
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String cart = request.getParameter("cart");
            String o_total = request.getParameter("order_total");
            Double order_total = Double.valueOf(o_total);
            JSONArray ja = new JSONArray(cart);
            Object id = session.getAttribute("Id");
            int userID = Integer.parseInt(id.toString());
            OrderDao orderDao = new OrderDao();
            UserDao userDao = new UserDao();
            ProductDao productDao = new ProductDao();
            orderResult = orderDao.saveOrder(order_id, order_total, email, "P");
            if (orderResult != 0) {
                double amount = userDao.updateUserPurchaseBalance(userID, order_total);
                session.setAttribute("balance", amount);
                for (int i = 0; i < ja.length(); i++) {
                    int product_id = ja.getJSONObject(i).getInt("pid");
                    double product_price = ja.getJSONObject(i).getDouble("price");
                    String product_name = ja.getJSONObject(i).getString("name");
                    String product_image = ja.getJSONObject(i).getString("image");
                    int product_quantity = ja.getJSONObject(i).getInt("quantity");
                    productDao.updateProductQuantity(product_id, product_quantity);
                    result = orderDao.SaveOrderDetails(order_id, product_id, username, email, address, address, product_name, product_image, product_price, product_quantity);
                }
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("DONE");
                request.setAttribute("result", result);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

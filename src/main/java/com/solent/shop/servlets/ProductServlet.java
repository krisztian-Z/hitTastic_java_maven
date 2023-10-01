package com.solent.shop.servlets;

import com.solent.shop.dao.ProductDao;
import com.solent.shop.models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@WebServlet(name = "Product", urlPatterns = { "/Product" })
@MultipartConfig
public class ProductServlet extends HttpServlet {

    private ProductDao pDao;

    private Gson gson = new Gson();

    @Override
    public void init() {
        pDao = new ProductDao();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Product</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Product at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ("update".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
        } else if ("delete".equals(request.getParameter("action"))) {
            int id = Integer.parseInt(request.getParameter("id"));
            int res = this.pDao.DeleteProduct(id);
            List products = this.pDao.getAllProducts();
            request.setAttribute("products", products);
            session.setAttribute("success", "Product deleted");
            request.getRequestDispatcher("view-products.jsp").forward(request, response);
        } else if ("cart".equals(request.getParameter("action"))) {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDao productDao = new ProductDao();
        if ("view-products".equals(request.getParameter("view-products"))) {
            List products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("view-products.jsp").forward(request, response);
        } else if ("add-products".equals(request.getParameter("add-products"))) {
            List products = productDao.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
        } else if ("save-product".equals(request.getParameter("save-product"))) {
            this.saveProduct(request, response, session);
        } else if ("update-product".equals(request.getParameter("update-product"))) {
            this.updateProduct(request, response, session);
        } else {
            int pID = Integer.parseInt(request.getParameter("id"));
            List<Product> list = productDao.getProductByID(pID);
            String productJsonString = this.gson.toJson(list);
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(productJsonString);
                out.flush();
            }
        }
    }

    public void saveProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        String pImage = "No Image";
        String pCat = request.getParameter("category");
        String pName = request.getParameter("name");
        String pDesc = request.getParameter("product_details");
        int pQty = parseInt(request.getParameter("quantity"));
        double pPrice = parseDouble(request.getParameter("unit_price"));
        Part part = request.getPart("pImage");
        if (pName.isEmpty() || pDesc.isEmpty() || pPrice == 0 || pQty == 0) {
            session.setAttribute("error", "Please fill all the fields.");
            request.getRequestDispatcher("add-product.jsp").forward(request, response);
        } else {
            String product = pDao.AddProduct(Integer.parseInt(pCat), pQty, pName, pDesc, part.getSubmittedFileName(), pPrice);
            if (product == null) {
                session.setAttribute("error", "Please try again.");
                request.getRequestDispatcher("add-product.jsp").forward(request, response);
            } else {
                session.setAttribute("success", "Product created .");
                String path = request.getRealPath(File.separator + "resources" + File.separator + "images" + File.separator + "products" + File.separator) + part.getSubmittedFileName();
                System.out.println(path);
                FileOutputStream fos;
                fos = new FileOutputStream(path);
                InputStream is = part.getInputStream();
                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
                List products = pDao.getAllProducts();
                request.setAttribute("products", products);
                request.getRequestDispatcher("view-products.jsp").forward(request, response);
            }
        }
    }

    public void updateProduct(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        int pID = Integer.parseInt(request.getParameter("id"));
        String pImage = "No Image";
        int pCat = Integer.parseInt(request.getParameter("category"));
        String pName = request.getParameter("name");
        String pDesc = request.getParameter("product_details");
        int pQty = parseInt(request.getParameter("quantity"));
        double pPrice = parseDouble(request.getParameter("unit_price"));
        Part part = request.getPart("pImage");
        String product_image = part.getSubmittedFileName();
        if (pName.isEmpty() || pDesc.isEmpty() || pPrice == 0 || pQty == 0) {
            session.setAttribute("error", "Please fill all the fields.");
            request.getRequestDispatcher("update-product.jsp").forward(request, response);
        } else {
            if (product_image.isBlank() || product_image.isEmpty()) {
                int res = pDao.UpdateProductWithoutImage(pID, pCat, pQty, pName, pDesc, pPrice);
                if (res == 0) {
                    session.setAttribute("error", "Please try again.");
                    request.getRequestDispatcher("update-product.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("view-products.jsp").forward(request, response);
                }
            } else {
                int res = pDao.UpdateProduct(pID, pCat, pQty, pName, part.getSubmittedFileName(), pDesc, pPrice);
                if (res == 0) {
                    session.setAttribute("error", "Please try again.");
                    request.getRequestDispatcher("update-product.jsp").forward(request, response);
                } else {
                    session.setAttribute("success", "Product created .");
                    String path = request.getRealPath(File.separator + "resources" + File.separator + "images" + File.separator + "products" + File.separator) + part.getSubmittedFileName();
                    System.out.println(path);
                    FileOutputStream fos;
                    fos = new FileOutputStream(path);
                    InputStream is = part.getInputStream();
                    byte[] data = new byte[is.available()];
                    is.read(data);
                    fos.write(data);
                    fos.close();
                    request.getRequestDispatcher("view-products.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

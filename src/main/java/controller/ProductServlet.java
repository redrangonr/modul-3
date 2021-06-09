package controller;

import model.Product;
import service.IProductService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewProduct(request, response);
                break;
            case "edit":
                showEditProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                showListProduct(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
        }
    }
    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int Quantity = Integer.parseInt(request.getParameter("quantity"));
        String Color = request.getParameter("color");
        String Category  = request.getParameter("category");
        float Price =Float.parseFloat(request.getParameter("price"));

        Product product = new Product(id, name,Price,Quantity,Color,Category);
        boolean isInserted= productService.update(id, product);
        if(!isInserted){
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message","Successful");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Edit.jsp");
        dispatcher.forward(request, response);
    }
    private void createNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Create.jsp");
        dispatcher.forward(request, response);
    }
    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int Quantity = Integer.parseInt(request.getParameter("quantity"));
        String Color = request.getParameter("color");
        String Category  = request.getParameter("category");
        float Price =Float.parseFloat(request.getParameter("price"));

        Product product = new Product(name,Price,Quantity,Color,Category);
        boolean isInserted = productService.add(product);
        if (!isInserted) {
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message", "Successful!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Create.jsp");
        dispatcher.forward(request, response);
    }
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error-404.jsp");
            dispatcher.forward(request, response);
        }
        productService.remove(id);
        response.sendRedirect("/product");
    }
    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Edit.jsp");
        if (product == null) {
            dispatcher = request.getRequestDispatcher("/error-404.jsp");
        }
        request.setAttribute("product", product);
        dispatcher.forward(request, response);
    }

    private void showListProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;
        products = productService.findAll();
        request.setAttribute("product", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/List.jsp");
        dispatcher.forward(request, response);
    }
}


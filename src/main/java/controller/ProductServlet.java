package controller;

import model.ProductList;
import service.IProductService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private IProductService productServiceService = new ProductService();
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
        int Quantity = Integer.parseInt(request.getParameter("Quantity"));
        String Color = request.getParameter("color");
        String Category  = request.getParameter("category");
        float Price =

        ProductList productLsit = new ProductList(id, name,Price,Quantity,Color,Category);
        boolean isInserted= productServiceService.update(id,prodcutList);
        if(!isInserted){
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message","Successful");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Edit.jsp");
        dispatcher.forward(request, response);
    }
}

package service;

import dao.InterfaceDAO;
import dao.ProductDAO;
import model.ProductList;

import java.util.List;

public class ProductService implements IProductService{
    private InterfaceDAO productDAO = new ProductDAO();

    @Override
    public List<ProductList> findAll() {
        return productDAO.findAll();
    }

    @Override
    public ProductList findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean add(ProductList productList) {
        return productDAO.add(productList);
    }

    @Override
    public boolean update(int id, ProductList productList) {
        return productDAO.update(id,productList);
    }

    @Override
    public boolean remove(int id) {
        return productDAO.remove(id);
    }
}

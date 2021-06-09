package dao;

import dao.InterfaceDAO;
import dao.SQLConection;
import model.ProductList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    public static final String SELECT_ALL_PRODUCT = "select * from productlist;";
    public static final String SELECT_PRODUCT_BY_ID = "select * from productlist where id = ?;";
    public static final String INSERT_PRODUCT = "insert into productlist( ProductName, Price, Quantity, Color, Category) values (?,?,?,?,?);";
    public static final String UPDATE_PRODUCT_BY_ID = "update productlist set ProductName =? where id=?;";
    public static final String DELETE_PRODUCT = "delete from productlist where id =?;";


    @Override
    public List<ProductList> findAll() {
        List<ProductList> productLists= new ArrayList<>();
        Connection connection = SQLConection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String productName = resultSet.getString("ProductName");
                float price = resultSet.getFloat("Price");
                int quantity = resultSet.getInt("Quantity");
                String color = resultSet.getString("Color");
                String category = resultSet.getString("Category");
                productLists.add(new ProductList( id,  productName,  price,  quantity, color,  category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productLists;

    }

    @Override
    public ProductList findById(int id) {
        ProductList productList = new ProductList();
        Connection connection = SQLConection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("ProductName");
                float price = resultSet.getFloat("Price");
                int quantity = resultSet.getInt("Quantity");
                String color = resultSet.getString("Color");
                String category = resultSet.getString("Category");
                productList.setProductName(name);
                productList.setPrice(price);
                productList.setQuantity(quantity);
                productList.setColor(color);
                productList.setCategory(category);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public boolean add(ProductList productList) {
        Connection connection = SQLConection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, productList.getProductName());
            preparedStatement.setFloat(2,productList.getPrice());
            preparedStatement.setInt(3,productList.getQuantity());
            preparedStatement.setString(4,productList.getColor());
            preparedStatement.setString(5,productList.getCategory());
            rowInserted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted!=0;
    }

    @Override
    public boolean update(int id,ProductList productList) {
        Connection connection = SQLConection.getConnection();
        int rowUpdated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, productList.getProductName());
            preparedStatement.setFloat(3,productList.getPrice());
            preparedStatement.setInt(4,productList.getQuantity());
            preparedStatement.setString(5,productList.getColor());
            preparedStatement.setString(6,productList.getCategory());
            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated !=0;
    }

    @Override
    public boolean remove(int id) {
        Connection connection = SQLConection.getConnection();
        int rowDeleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted !=0;
    }
}
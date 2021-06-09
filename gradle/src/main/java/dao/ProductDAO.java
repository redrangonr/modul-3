package dao;

import model.Product;

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
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
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
                products.add(new Product( id,  productName,  price,  quantity, color,  category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;

    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
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
                product.setProductName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setColor(color);
                product.setCategory(category);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean add(Product product) {
        Connection connection = SQLConection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getCategory());
            rowInserted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted!=0;
    }

    @Override
    public boolean update(int id, Product product) {
        Connection connection = SQLConection.getConnection();
        int rowUpdated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setString(6, product.getCategory());
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
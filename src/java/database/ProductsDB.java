package database;


import java.sql.SQLException;
import java.util.ArrayList;
import jnxpress.Filter;
import jnxpress.Product;
import response.Respuesta;


public class ProductsDB extends MySql {
    
    
    private static Respuesta existDB(int id) {
        
        Respuesta respuesta = new Respuesta(200, true, "OK!");
        
        try {
            String query = "SELECT `product-id` FROM `products` WHERE `product-id` = " + id;
            
            rs = stm.executeQuery(query);
            
            if (!rs.next()) {
                
                respuesta.setOk(false);
                respuesta.setStatus(403);
                respuesta.setMessage("El producto no existe!");
                
            }
            
            
        }catch (SQLException e) {    
            
            respuesta = exeption(e);  

        }
        
        return respuesta;
    }
    
    private static Respuesta isYourProduct(int idUser, int idProduct) {
        
        Respuesta respuesta = new Respuesta(200, true, "OK!");
        
        try {
            
            String query = "SELECT * FROM `products` WHERE `user-id` = " + idUser + " AND `product-id` = " + idProduct;
            
            rs = stm.executeQuery(query);
            
            if (!rs.next()) {
                
                respuesta.setOk(false);
                respuesta.setStatus(403);
                respuesta.setMessage("El producto no es suyo!");
                
            }
            
            
        }catch(SQLException e) {
            
                respuesta = exeption(e);
                
        }
        
        return respuesta;
        
    }
    
    
    public static Respuesta<ArrayList<Product>> getProducts(int index) {
        
        index *= 4;
        
        Respuesta<ArrayList<Product>> respuesta = getConnection();
        
        ArrayList<Product> listProducts = new ArrayList();
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM products ORDER BY `product-id` DESC LIMIT " + index + ",4" ;
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    Product product = new Product(
                        rs.getInt("product-id"),
                        rs.getString("product-name"),
                        rs.getString("product-description"),
                        rs.getInt("product-price"),
                        rs.getInt("product-stock"),
                        rs.getString("product-image"),
                        rs.getString("product-date")
                    );
                    
                    listProducts.add(product);
                }

            }
            
            catch(SQLException e) {
                respuesta = exeption(e);
            }
            
        }
                        
        respuesta.setContent(listProducts);
        
        closeConnection();
        
        return respuesta;
    }
    
    
    public static Respuesta<String> postProduct(Product product){
        
        Respuesta<String> respuesta = getConnection();
       
        if (respuesta.isOk()) {
            
            try {
                
                String query = "INSERT INTO `products` ("
                        + "`user-id`" + ","
                        + "`product-name`" + ","
                        + "`product-description`" + ","
                        + "`product-price`" + ","
                        + "`product-stock`" + ","
                        + "`product-image`" + ","
                        + "`category-id`" + ","
                        + "`condition-id`"
                        + ") VALUES ("
                        + product.getUser().getId() + ","
                        + "'" + product.getName() + "'" + ","
                        + "'" + product.getDescription() + "'" + ","
                        + product.getPrice() + ","
                        + product.getStock() + ","
                        + "'" + product.getImage() + "'" + ","
                        + product.getCategory().getId() + ","
                        + product.getCondition().getId()
                        + ")";
                
                respuesta.setContent(query);
                stm.executeUpdate(query);
            }
            catch(SQLException e) {
                respuesta = exeption(e);
            }
        }
        
        closeConnection();
        
        return respuesta;
    }
    
    public static Respuesta<ArrayList<Product>> getProductsForFilter(Filter filter) {
        
        Respuesta<ArrayList<Product>> respuesta = getConnection();
        ArrayList<Product> listProduct = new ArrayList<>();
        
        if (respuesta.isOk()) {
            String query = "SELECT * FROM `products` WHERE ";
            boolean addAnd = false;
            
            if (!filter.getTerm().equals("")) {
                query += "`product-name` LIKE '%" + filter.getTerm() + "%'";
                addAnd = true;
            }
            if (filter.getCategory().getId() > 0) {
                if (addAnd) {
                    query += " AND ";
                }
                query += "`category-id` = " + filter.getCategory().getId();
            }
            if (filter.getCondition().getId() > 0) {
                if (addAnd) {
                    query += " AND ";
                }
                query += "`condition-id` = " + filter.getCondition().getId();
            }
            
            try {
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    
                    Product producto = new Product(
                        rs.getInt("product-id"),
                        rs.getString("product-name"),
                        rs.getString("product-description"),
                        rs.getInt("product-price"),
                        rs.getInt("product-stock"),
                        rs.getString("product-image"),
                        rs.getString("product-date")
                    );
                    
                    listProduct.add(producto);
                    
                }
                
                respuesta.setContent(listProduct);
                
            }catch(SQLException e) {
                respuesta = exeption(e);
            }
            
            
        }
        
        closeConnection();
        
        return respuesta;
    }
    
    
    public static Respuesta<String> putProduct(int idProduct, Product newProduct) {
        
        Respuesta<String> respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = UsersDB.existDB(newProduct.getUser().getId());
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        respuesta = existDB(newProduct.getId());
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = isYourProduct(newProduct.getUser().getId(), newProduct.getId());
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        try {
            
            String query = "UPDATE `products` SET "
                    + "`product-name` = '" + newProduct.getName() + "',"
                    + "`product-description` = '" + newProduct.getDescription()+ "',"
                    + "`product-price` = " + newProduct.getPrice() + ","
                    + "`product-stock` = " + newProduct.getStock() + ","
                    + "`product-image` = '" + newProduct.getImage() + "',"
                    + "`category-id` = " + newProduct.getCategory().getId() + ","
                    + "`condition-id` = " + newProduct.getCondition().getId()
                    + " WHERE `product-id` = " + newProduct.getId();
            
            stm.executeUpdate(query);
            
        }catch (SQLException e) {
            
            respuesta.setOk(false);
            respuesta.setStatus(500);
            respuesta.setMessage(e.getMessage());
            
        }
        
        closeConnection();
        
        return respuesta;
        
    }
    
}

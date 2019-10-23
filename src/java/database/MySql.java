
package database;

import response.Respuesta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import jnxpress.Filter;
import jnxpress.Product;
import jnxpress.Review;
import jnxpress.User;

public class MySql {
    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static String user = "root";
    private static String password = "";
    private static String database = "jnxpress";
    private static String url = "jdbc:mysql://localhost:3306/" + database + "?user=" + user + "&password=" + password;
    
    private static Respuesta getConnection() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url);
            stm = con.createStatement();
            
            return new Respuesta(200 ,true, "OK!");
        }
        catch(ClassNotFoundException e) {
            return new Respuesta(500,false, e.getMessage());
        }
        catch(SQLException e) {
            return new Respuesta(500,false , e.getMessage());
        }
    }
    
    private static void closeConnection() {
        try {
            stm.close();
            rs.close();
            con.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static Respuesta<ArrayList<Product>> getProducts(int index) {
        
        index *= 4;
        
        Respuesta<ArrayList<Product>> respuesta = getConnection();
        
        ArrayList<Product> listProducts = new ArrayList();
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM products ORDER BY `product-id` DESC LIMIT " + index + ",4" ;
                
                int idCategory;
                int idCondition;
                
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
                    
                    idCategory = rs.getInt("category-id");
                    idCondition = rs.getInt("condition-id");
                    listProducts.add(producto);
                }
                closeConnection();
            }
            catch(SQLException e) {
                respuesta.setStatus(404);
                respuesta.setMessage(e.getMessage());
            }
            
        }
        
        respuesta.setContent(listProducts);
        
        return respuesta;
    }
    
    public static Respuesta<String> getProduct(int id) {
        
        Respuesta respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
            try {
                String query = "";
                
                rs = stm.executeQuery(query);
                
                if (rs.next()) {
                    
                    respuesta.setStatus(200);
                    respuesta.setOk(true);
                    respuesta.setMessage("OK!");
                    
                }else {
                    
                    respuesta.setStatus(403);
                    respuesta.setOk(false);
                    respuesta.setMessage("El producto no existe.");
                    
                }
                
                closeConnection();
                
            }catch(SQLException e) {
                
            }
            
            
        }
        
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
                
                /*query = "INSERT INTO `products`(`user-id`, `product-name`, 
                `producs-description`, `product-price`, `product-stock`, `product-image`, 
                `category-id`, `condition-id`) VALUES (1,'Moto one', 
                'Un celular, no tengo ganas de buscar la descripción pero si de escribir esto xd.', 
                7000.00, 5, 'Despues arreglo esto xd', 1, 1)";*/
                
                respuesta.setContent(query);
                stm.executeUpdate(query);
                closeConnection();
            }
            catch(SQLException e) {
                respuesta.setStatus(500);
                respuesta.setMessage(e.getMessage());
            }
        }
        
        
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
                respuesta.setStatus(404);
                respuesta.setMessage(e.getMessage());
            }
            
            
        }
        
        return respuesta;
    }
    
    public static Respuesta getUserForId(int id) {
        
        Respuesta respuesta = getConnection();
    
        if (respuesta.isOk()) {
            
            try {
                
                String query = "Select * FROM `users` WHERE `user-id` = " + id;
                
                rs = stm.executeQuery(query);
                
                if (rs.next()) {
                    
                    respuesta.setStatus(200);
                    respuesta.setOk(true);
                    respuesta.setMessage("OK!");
                    
                }else{
                    
                    respuesta.setStatus(403);
                    respuesta.setOk(false);
                    respuesta.setMessage("Usuario no encontrado!");
                
                }
                
                
            }catch (SQLException e) {
                
                respuesta.setStatus(500);
                respuesta.setOk(false);
                respuesta.setMessage(e.getMessage());
                
            }
            
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<String> getUserForUsername(String username) {
        
        Respuesta<String> respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM users WHERE `user-username` = '" + username + "'";
                
                rs = stm.executeQuery(query);
                
                if (rs.next()) {
                    
                    respuesta.setStatus(403);
                    respuesta.setMessage("Nombre de usuario ya existente!");
                    respuesta.setOk(false);
                    
                }else {
                    
                    respuesta.setStatus(200);
                    respuesta.setMessage("OK!");
                    
                }
                
                closeConnection();
                
            }
            catch(SQLException e) {
                    respuesta.setStatus(404);
                    respuesta.setMessage(e.getMessage());
                    respuesta.setOk(false);
            }
            
        }

        return respuesta;
        
    }
    
    public static Respuesta<String> getUserForEmail(String email) {
        
        Respuesta<String> respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM users WHERE `user-email` = '" + email + "'";
                
                rs = stm.executeQuery(query);
                
                if (rs.next()) {
                    
                    respuesta.setStatus(403);
                    respuesta.setMessage("El email ingresado ya esta siendo ocupado!");
                    respuesta.setOk(false);
                    
                }else {
                    
                    respuesta.setStatus(200);
                    respuesta.setMessage("OK!");
                    
                }
                
                closeConnection();
                
            }
            catch(SQLException e) {
                    respuesta.setStatus(404);
                    respuesta.setMessage(e.getMessage());
                    respuesta.setOk(false);
            }
            
        }

        return respuesta;
        
    }
    
    public static Respuesta<User> getUser(int id) {
        
        Respuesta<User> respuesta = getConnection();
        
        User usuario;
        
        if (respuesta.isOk()) {
            
            try {
                String query = "SELECT * FROM users WHERE `user-id` = '" + id+ "'";
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                usuario = new User(
                        id,
                        rs.getString("user-username"),
                        rs.getString("user-email"),
                        rs.getFloat("user-balance"),
                        rs.getInt("user-sales"),
                        rs.getInt("user-purchase"),
                        rs.getInt("user-appreciation")
                );
                
                respuesta.setContent(usuario);
                
            }  
            catch(SQLException e) {
                respuesta.setStatus(500);
                respuesta.setMessage(e.getMessage());
            }
            
        }
        
        return respuesta;
    }
    
    public static Respuesta postUser(User user) {
        
        Respuesta respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
            try {
                String query = "INSERT INTO `users` ("
                        + "`user-username`,"
                        + "`user-description`,"
                        + "`user-email`,"
                        + "`user-password`,"
                        + "`user-balance`"
                        + ") VALUES ("
                        + "'" + user.getUsername() + "',"
                        + "'" + user.getDescription() + "',"
                        + "'" + user.getEmail() + "',"
                        + "'" + user.getPassword() + "',"
                        + user.getBalance()
                        + ")";
                
                stm.executeUpdate(query);
                
                
            }catch(SQLException e) {
                
                respuesta.setStatus(500);
                respuesta.setOk(false);
                respuesta.setMessage(e.getMessage());
                
            }
            
            
        }
        
        
        
        
        return respuesta;
    }
    
    
    
    
    
        public static Respuesta postProductReview(Review<Product> review) {
        
        Respuesta respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
           try {
               
               String query = "INSERT INTO `products-reviews` ("
                       + "`user-id`,"
                       + "`product-id`,"
                       + "`product-review-content`,"
                       + "`product-review-appreciation`"
                       + ") VALUES ("
                       + review.getUser().getId() + ","
                       + review.getTarget().getId() + ","
                       + "'" + review.getContent() + "',"
                       + "'" + review.getAppreciation() + "'"
                       + ")";
               
               stm.executeUpdate(query);
               
           } catch(SQLException e) {
                
                respuesta.setStatus(500);
                respuesta.setOk(false);
                respuesta.setMessage(e.getMessage());
                
           }
            
        }
        
        
        return respuesta;
    }
    
    
    
    
    
    
    public static Respuesta postUserReview(Review<User> review) {
        
        Respuesta respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
           try {
               
               String query = "INSERT INTO `users-reviews` ("
                       + "`user-id`,"
                       + "`target-id`,"
                       + "`user-review-content`,"
                       + "`user-review-appreciation`"
                       + ") VALUES ("
                       + review.getUser().getId() + ","
                       + review.getTarget().getId() + ","
                       + "'" + review.getContent() + "',"
                       + "'" + review.getAppreciation() + "'"
                       + ")";
               
               stm.executeUpdate(query);
               
           } catch(SQLException e) {
                
                respuesta.setStatus(500);
                respuesta.setOk(false);
                respuesta.setMessage(e.getMessage());
                
           }
            
        }
        
        
        return respuesta;
    }
    
    
    
}


package database;

import response.Respuesta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import jnxpress.Product;

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
            
            return new Respuesta(200 ,"OK!");
        }
        catch(ClassNotFoundException e) {
            return new Respuesta(500,e.getMessage());
        }
        catch(SQLException e) {
            return new Respuesta(404,e.getMessage());
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
    
    public static Respuesta<ArrayList<Product>> getProductos() {
        
        Respuesta<ArrayList<Product>> respuesta = getConnection();
        
        ArrayList<Product> listProducts = new ArrayList();
        
        if (respuesta.getCode() == 200) {
            
            try {
                
                String query = "SELECT * FROM products";
                
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
                respuesta.setCode(404);
                respuesta.setMessage(e.getMessage());
            }
            
        }
        
        respuesta.setContent(listProducts);
        
        return respuesta;
    }
    
    public static Respuesta<User> getUser(String email, String password) {
        
        Respuesta<User> respuesta = getConnection();
        
        User usuario;
        
        
        
        if (respuesta.getCode() == 200) {
            
            try {
                
                String query = "SELECT * FROM users WHERE `user-email` = '" + email + "'";
                //String query =  "SELECT * FROM `users` WHERE `user-email` = 'c.nahu.roman@gmail.com'";

                          
                usuario = new User();
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                String passwordDB = rs.getString("user-password");     
                
                
                
                if (password.equals(passwordDB)) {
                    usuario = new User(
                        rs.getInt("user-id"),
                        rs.getString("user-username"),
                        rs.getString("user-email"),
                        rs.getFloat("user-balance"),
                        rs.getInt("user-sales"),
                        rs.getInt("user-purchases"),
                        rs.getInt("user-appreciation")
                    );
                    
                    respuesta.setContent(usuario);
                    
                }else {
                    
                    respuesta.setCode(403);
                    respuesta.setMessage("Usuario o contraseña incorrectas");
                    
                }
                
                closeConnection();
            }
            catch(SQLException e) {
                    respuesta.setCode(404);
                    respuesta.setMessage(e.getMessage());
            }
            
            
            
        }
        
        return respuesta;
    }
    
    
    public static Respuesta<String> postProduct(Product product){
        
        Respuesta<String> respuesta = getConnection();
       
        if (respuesta.getCode() == 200) {
            
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
                int num = stm.executeUpdate(query);
            }
            catch(SQLException e) {
                respuesta.setCode(500);
                respuesta.setMessage(e.getMessage());
            }
        }
        
        
        return respuesta;
    }
    
}

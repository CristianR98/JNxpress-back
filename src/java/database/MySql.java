
package database;

import response.Respuesta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import jnxpress.Producto;
import jnxpress.Usuario;

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
    
    public static Respuesta<ArrayList<Producto>> getProductos() {
        
        Respuesta<ArrayList<Producto>> respuesta = getConnection();
        
        ArrayList<Producto> listProducts = new ArrayList();
        
        if (respuesta.getCode() == 200) {
            
            try {
                
                String query = "SELECT * FROM products";
                
                int idCategory;
                int idCondition;
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    Producto producto = new Producto(
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
    
    public static Respuesta<Usuario> getUser(String email, String password) {
        
        Respuesta<Usuario> respuesta = getConnection();
        
        Usuario usuario;
        
        
        
        if (respuesta.getCode() == 200) {
            
            try {
                
                String query = "SELECT * FROM users WHERE `user-email` = '" + email + "'";
                //String query =  "SELECT * FROM `users` WHERE `user-email` = 'c.nahu.roman@gmail.com'";

                          
                usuario = new Usuario();
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                String passwordDB = rs.getString("user-password");     
                
                
                
                if (password.equals(passwordDB)) {
                    usuario = new Usuario(
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
    
}

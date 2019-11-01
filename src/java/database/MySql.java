
package database;

import response.Respuesta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    protected static Connection con;
    protected static Statement stm;
    protected static ResultSet rs;
    protected static String userDB = "root";
    protected static String password = "";
    protected static String database = "jnxpress";
    protected static String url = "jdbc:mysql://localhost:3306/" + database + "?user=" + userDB + "&password=" + password;
    
    protected static Respuesta getConnection() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url);
            stm = con.createStatement();
            
            return new Respuesta(200 ,true, "OK!");
        }
        catch(ClassNotFoundException | SQLException e) {
            return new Respuesta(500,false, e.getMessage());
        }
        
    }
    
    protected static void closeConnection() {
        try {
            stm.close();
            if (rs != null) {
                rs.close();
            }
            con.close();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    protected static Respuesta exception( Exception e) {
        return new Respuesta(500, false, e.getMessage() );
    }
    
}

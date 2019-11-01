package database;

import static database.MySql.getConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;
import models.User;
import response.Respuesta;

public class UsersDB extends MySql{
    
    protected static Respuesta existDB(int id) {
        
        Respuesta respuesta = new Respuesta(200, true, "OK!");
        
        try {
            String query = "SELECT `user-id` FROM `users` WHERE `user-id` = " + id;
            
            rs = stm.executeQuery(query);
            
            if (!rs.next()) {
                
                respuesta = new Respuesta(403, false, "El usuario no existe!");
                
            }
            
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    protected static int existUsername(String username) {
        
        try {
            
            String query = "SELECT * FROM `users` WHERE `user-username` = '" + username + "'";
            
            rs = stm.executeQuery(query);
            
            if (rs.next()) {
                return rs.getInt("user-id");
            }else {
                return 0;
            }
            
        }catch (SQLException e) {
            return 0;
        }
        
    }
    
    public static Respuesta<String> busyUsername(String username) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        respuesta.setOk((existUsername(username) > 0));
        
        if (respuesta.isOk()) {
            return new Respuesta(403, false, "Nombre de usuario ya ocupado");
        }else {
            return new Respuesta(200, true, "OK!");
        }
        
    }
    
    public static Respuesta login(String email, String password) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
        
            String query = "SELECT * FROM `users` WHERE `user-email` = '" + email + "' AND `user-password` = '" + password + "'" ;
            
            rs = stm.executeQuery(query);
            
            if (rs.next()) {
                
                respuesta = new Respuesta(200, true, "OK!");
                User user = new User();
                user.setId(rs.getInt("user-id"));
                user.setUsername(rs.getString("user-username"));
                user.setDescription(rs.getString("user-description"));
                user.setEmail(rs.getString("user-email"));
                user.setBalance(rs.getFloat("user-balance"));
                user.setSales(rs.getInt("user-sales"));
                user.setPurchases(rs.getInt("user-purchases"));
                user.setAppreciation(rs.getInt("user-appreciation"));
                
                respuesta.setContent(user);
                
            }else {
                respuesta = new Respuesta(403, false, "Email o password incorrectos!");
            }
            
        }catch(SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    
    public static Respuesta<User> getUserById(int id) {
        
        Respuesta respuesta = getConnection();
    
        if (respuesta.isOk()) {
            
            try {
                
                String query = "Select * FROM `users` WHERE `user-id` = " + id;
                
                rs = stm.executeQuery(query);
                
                if (rs.next()) {
                    
                    User user = new User();
                    
                    user.setId(rs.getInt("user-id"));
                    user.setUsername(rs.getString("user-username"));
                    user.setDescription(rs.getString("user-description"));
                    user.setEmail(rs.getString("user-email"));
                    user.setBalance(rs.getFloat("user-balance"));
                    user.setSales(rs.getInt("user-sales"));
                    user.setPurchases(rs.getInt("user-purchases"));
                    user.setAppreciation(rs.getInt("user-appreciation"));

                    respuesta.setContent(user);
                    
                }else{
                    
                    respuesta = new Respuesta(404, false, "Usuario no encontrado!");
                
                }
                
                
            }catch (SQLException e) {
                
                respuesta.setStatus(500);
                respuesta.setOk(false);
                respuesta.setMessage(e.getMessage());
                
            }
            
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<User> getUserByUsername(String username) {
        
        Respuesta<User> respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM users WHERE `user-username` = '" + username + "'";
                
                rs = stm.executeQuery(query);
                
                if (rs.next()) {
                    
                    respuesta = new Respuesta(200, true, "OK!");
                    
                }else {
                    
                    respuesta = new Respuesta(404, false, "Usuario no encontrado!");
                    
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
    
    public static Respuesta<ArrayList<User>> getUsersByUsername(String username) {
        
        Respuesta<ArrayList<User>> respuesta = getConnection();
        
        ArrayList<User> listUsers = new ArrayList();
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM users WHERE `user-username` LIKE '%" + username + "%' LIMIT 0,5";
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    
                    User user = new User();
                    user.setUsername(rs.getString("user-username"));
                    respuesta = new Respuesta(200, true, "OK!");
                    listUsers.add(user);
                    
                }
                
                respuesta.setContent(listUsers);
                
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
    
    public static Respuesta<String> validateUsername(String username) {
        
        Respuesta respuesta = getUserByUsername(username);
        
        if (respuesta.isOk()) {
            respuesta = new Respuesta(403, false, "Nombre de usuario ya existente");
        }else {
            respuesta = new Respuesta(200, true, "OK!");
        }
     
        return respuesta;
        
    }
    
    public static Respuesta<String> getUserByEmail(String email) {
                   
        Respuesta respuesta = new Respuesta(200, true, "OK!");
        try {
              
            String query = "SELECT * FROM users WHERE `user-email` = '" + email + "'";
                
            rs = stm.executeQuery(query);
                
            if (rs.next()) {
                    
                respuesta = new Respuesta(403, false, "El email ingresado ya esta siendo ocupado!");
                    
            }
                
        }catch(SQLException e) {
                respuesta = exception(e);
        }
        
            
        return respuesta;
            
    }
    
    
    
    public static Respuesta postUser(User user) {
        Respuesta respuesta = getConnection();
        
        if(!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = getUserByEmail(user.getEmail());
        
        System.out.println(respuesta.getMessage());
        
        if(!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = busyUsername(user.getUsername());
        
        if(!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
            
            try {
                
                //rs.close();
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
                
                query = "SELECT `user-id` FROM `users` WHERE `user-email` = '" + user.getEmail() + "'";
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                int id = rs.getInt("user-id");
                
                for (int i = 0; i < user.getInterests().size(); i++) {
                    System.out.println(user.getInterests().get(i).getId());
                    Category category = user.getInterests().get(i);
                    
                    query = "INSERT INTO `interests` (`category-id`, `user-id`) VALUES (" + category.getId() + ", " + id +")";

                    stm.executeUpdate(query);
                }
                
                
            }catch(SQLException e) {
                
                respuesta = exception(e);
                
            }
            
            
        
        
        return respuesta;
        
    }
    
    
    
    public static Respuesta putUserInfo(User user, int userId) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = existDB(userId);
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        int valid = existUsername(user.getUsername());
        
        if (valid <= 0) {
            closeConnection();
            return new Respuesta(403, false, "Usuario ya ocupado!");
        }
        
        try {
            
            String query = "UPDATE `users` SET "
                    + "`user-username` = '" + user.getUsername() + "',"
                    + "`user-description` = '" + user.getDescription() + "' "
                    + "WHERE `user-id` = " + userId;
            
            stm.executeUpdate(query);
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        closeConnection();
        
        return respuesta;
        
    }
    
    
    
    public static Respuesta putPassword(int idUser, String actualPassword, String newPassword) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = existDB(idUser);
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        try {
            
            String query = "SELECT * FROM `users` WHERE "
                    + "`user-id` = " + idUser + " AND "
                    + "`user-password` = '" + actualPassword + "'";
            
            rs = stm.executeQuery(query);
            
            if (!rs.next()) {
                closeConnection();
                respuesta = new Respuesta(403, false, "Password actual invalido!");
                return respuesta;
            }
            
            query = "UPDATE `users` SET `user-password` = '" + newPassword + "' WHERE `user-id` = " + idUser;
            
            stm.executeUpdate(query);
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        closeConnection();
        
        return respuesta;
        
    }
    
    public static Respuesta<String> addBalance(int addBalance, int userId) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            String query = "SELECT * FROM `users` WHERE `user-id` = " + userId;
            
            rs = stm.executeQuery(query);
            
            int balance;
            
            if (rs.next()) {
                balance = rs.getInt("user-balance");
            }else {
                respuesta = new Respuesta(403, false, "Hubo un error!");
                return respuesta;
            }
            
            query = "UPDATE `users` SET `user-balance` = " + (balance + addBalance) + " WHERE `user-id` = " + userId;
            
            stm.executeUpdate(query);
            
            
        }catch(SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.MySql.getConnection;
import java.sql.SQLException;
import jnxpress.User;
import response.Respuesta;

/**
 *
 * @author Nahu
 */
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
            respuesta = exeption(e);
        }
        
        return respuesta;
        
    }
    
    private static Respuesta busyUsername(User user) {
        
        Respuesta respuesta = new Respuesta(200, true, "OK!");
        
        try {
            
            String query = "SELECT * FROM `users` WHERE "
                    + "`user-username` = '" + user.getUsername() + "' "
                    + "AND NOT `user-id` = " + user.getId();
            
            rs = stm.executeQuery(query);
            
            if (rs.next()) {
                
                respuesta = new Respuesta(403, false, "Ya existe un usuario con ese nombre.");
                
            }
            
        }catch (SQLException e) {
            respuesta = exeption(e);
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
    
    public static Respuesta putUserInfo(User user) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = existDB(user.getId());
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = busyUsername(user);
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        
        try {
            
            String query = "UPDATE `users` SET "
                    + "`user-username` = '" + user.getUsername() + "',"
                    + "`user-description` = '" + user.getDescription() + "' "
                    + "WHERE `user-id` = " + user.getId();
            
            stm.executeUpdate(query);
            
        }catch (SQLException e) {
            respuesta = exeption(e);
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
            respuesta = exeption(e);
        }
        
        closeConnection();
        
        return respuesta;
        
    }
    
}

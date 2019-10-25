/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.ArrayList;
import jnxpress.Product;
import jnxpress.Review;
import jnxpress.User;
import response.Respuesta;

/**
 *
 * @author Nahu
 */
public class ReviewsDB extends MySql{
    
    public static Respuesta<ArrayList<Review<User>>> getUserReviews (int idTarget, int index) {
        
        Respuesta<ArrayList<Review<User>>> respuesta = getConnection();
        
        ArrayList<Review<User>> listReview = new ArrayList();
        
        respuesta.setContent(listReview);
        
        index *= 6;
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM `users-reviews` WHERE `target-id` = " + idTarget + " "
                        + "ORDER BY `user-review-id` DESC LIMIT " + index + ",6";
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    
                    int userID = rs.getInt("user-id");
                    String content = rs.getString("user-review-content");
                    int appreciation = rs.getInt("user-review-appreciation");
                    String date = rs.getString("user-review-date");
                    
                    Review<User> review = new Review();
                    User user = new User();
                    user.setId(userID);
                    review.setUser(user);
                    review.setContent(content);
                    review.setAppreciation(appreciation);
                    review.setDate(date);
                    
                    listReview.add(review);
                    
                }
                
                
            }catch (SQLException e) {
                
                respuesta.setOk(false);
                respuesta.setStatus(404);
                respuesta.setMessage(e.getMessage());
                
            }
            
            
        }
        
        
        return respuesta;
        
    }
        
    
    public static Respuesta<ArrayList<Review<Product>>> getProductReviews (int idProduct, int index) {
        
        Respuesta<ArrayList<Review<Product>>> respuesta = getConnection();
        
        ArrayList<Review<Product>> listReview = new ArrayList();
        
        respuesta.setContent(listReview);
        
        index *= 6;
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM `products-reviews` WHERE `product-id` = " + idProduct + " "
                        + "ORDER BY `product-review-id` DESC LIMIT " + index + ",6";
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    
                    int userID = rs.getInt("user-id");
                    String content = rs.getString("product-review-content");
                    int appreciation = rs.getInt("product-review-appreciation");
                    String date = rs.getString("product-review-date");
                    
                    Review<Product> review = new Review();
                    User user = new User();
                    user.setId(userID);
                    review.setUser(user);
                    review.setContent(content);
                    review.setAppreciation(appreciation);
                    review.setDate(date);
                    
                    listReview.add(review);
                    
                }
                
                
            }catch (SQLException e) {
                
                respuesta.setOk(false);
                respuesta.setStatus(404);
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

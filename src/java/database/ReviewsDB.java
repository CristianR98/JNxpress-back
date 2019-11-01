package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Product;
import models.Review;
import models.User;
import response.Respuesta;


public class ReviewsDB extends MySql{
    
    public static Respuesta<ArrayList<Review<User>>> getUserReviews (int idTarget) {
        
        Respuesta<ArrayList<Review<User>>> respuesta = getConnection();
        
        ArrayList<Review<User>> listReview = new ArrayList();
        
        respuesta.setContent(listReview);
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM `users-reviews` WHERE `target-id` = " + idTarget + " "
                        + "ORDER BY `user-review-id`";
                
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
        
    
    public static Respuesta<ArrayList<Review<Product>>> getProductReviews (int idProduct) {
        
        Respuesta<ArrayList<Review<Product>>> respuesta = getConnection();
        
        ArrayList<Review<Product>> listReview = new ArrayList();
        
        respuesta.setContent(listReview);
        
        if (respuesta.isOk()) {
            
            try {
                
                String query = "SELECT * FROM `products-reviews` WHERE `product-id` = " + idProduct + " "
                        + "ORDER BY `product-review-id` DESC";
                
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
    
    
    public static Respuesta postProductReview(Review<Product> review, int userId) {
        
        Respuesta respuesta = getConnection();
        
        if (respuesta.isOk()) {
            
           try {
               
               String query = "INSERT INTO `products-reviews` ("
                       + "`user-id`,"
                       + "`product-id`,"
                       + "`product-review-content`,"
                       + "`product-review-appreciation`"
                       + ") VALUES ("
                       + userId + ","
                       + review.getTarget().getId() + ","
                       + "'" + review.getContent() + "',"
                       + "'" + review.getAppreciation() + "'"
                       + ")";
               
               stm.executeUpdate(query);
               
               query = "SELECT * FROM `products-reviews` WHERE `product-id` = " + review.getTarget().getId();
               
               rs = stm.executeQuery(query);
               
               int lot = 1;
               int total = review.getAppreciation();
               
               while(rs.next()) {
                   
                   total += rs.getInt("product-review-appreciation");
                   
                   lot++;
                   
               }
               
               query = "UPDATE `products` SET `product-appreciation` = " + total/lot + " WHERE `product-id` = " + review.getTarget().getId();
               
               stm.executeUpdate(query);
               
           } catch(SQLException e) {
                
               respuesta = exception(e);
               
           }
            
        }
        
        
        return respuesta;
    }
    
    public static Respuesta postUserReview(Review<User> review, int userId) {
        
        Respuesta respuesta = getConnection();
        System.out.println("hola");
        if (respuesta.isOk()) {
            
           try {
               
               String query = "INSERT INTO `users-reviews` ("
                       + "`user-id`,"
                       + "`target-id`,"
                       + "`user-review-content`,"
                       + "`user-review-appreciation`"
                       + ") VALUES ("
                       + userId + ","
                       + review.getTarget().getId() + ","
                       + "'" + review.getContent() + "',"
                       + "'" + review.getAppreciation() + "'"
                       + ")";
               
               stm.executeUpdate(query);
               
               query = "SELECT * FROM `users-reviews` WHERE `target-id` = " + review.getTarget().getId();
               
               rs = stm.executeQuery(query);
               
               int lot = 1;
               int total = review.getAppreciation();
               
               while(rs.next()) {
                   
                   total += rs.getInt("user-review-appreciation");
                   lot++;
                   System.out.println(total);
                   
               }
               
               query = "UPDATE `users` SET `user-appreciation` = " + total/lot + " WHERE `user-id` = " + review.getTarget().getId();
               
               stm.executeUpdate(query);
               
               
           } catch(SQLException e) {
               respuesta = exception(e);
                
           }
            
        }
        
        
        return respuesta;
    }
    
    
    public static Respuesta<ArrayList<Review<Product>>> getProductsReviewByUser(int userId) {
        
        Respuesta respuesta = getConnection();
        
        ArrayList<Review<Product>> listReview = new ArrayList();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            String query = "SELECT * FROM `products` WHERE `user-id` = " + userId;
            
            rs = stm.executeQuery(query);
            
            ArrayList list = new ArrayList();
            
            while(rs.next()) {
                
                int id = rs.getInt("product-id");
                list.add(id);
                
            }
            
            for (int i = 0; i < list.size(); i++) {
                
                query = "SELECT * FROM `products-reviews` WHERE `product-id` = " + list.get(i);
                
                rs = stm.executeQuery(query);
                
                Review<Product> review = new Review();
                
                while(rs.next()) {
                    review.setId(rs.getInt("review-product-id"));
                    review.setAppreciation(rs.getInt("review-product-appreciation"));
                    review.setContent(rs.getString("review-product-content"));
                    review.setTarget(new Product());
                    review.getTarget().setId(rs.getInt("product-id"));
                    review.setUser(new User());
                    review.getUser().setId(rs.getInt("product-id"));
                    
                    query = "SELECT * FROM `users` WHERE `user-id` = " + review.getUser().getId();
                    
                    ResultSet results = stm.executeQuery(query);
                    
                    results.next();
                    
                    review.getUser().setUsername(results.getString("user-username"));
                    
                    query = "SELECT * FROM `products` WHERE `product-id` = " + review.getUser().getId();
                    
                    results = stm.executeQuery(query);
                    
                    results.next();
                    
                    review.getTarget().setName(results.getString("product-name"));
                    
                    listReview.add(review);
                    
                    
                }
                
                
                closeConnection();
                
            }
            
            
        }catch (SQLException e) {
            exception(e);
        }
        
        return respuesta;
        
    }
    
    
}

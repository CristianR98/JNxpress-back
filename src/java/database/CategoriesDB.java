package database;

import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;
import response.Respuesta;

public class CategoriesDB extends MySql{
    
    public static Respuesta<ArrayList<Category>> getCategories() {
        
        Respuesta<ArrayList<Category>> respuesta = getConnection();
        
        ArrayList<Category> listCategories = new ArrayList();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            String query = "SELECT * FROM `products-categories`";
            
            rs = stm.executeQuery(query);
            
            while(rs.next()) {
                
                Category category = new Category();
                category.setId(rs.getInt("category-id"));
                category.setName(rs.getString("category-name"));
                
                listCategories.add(category);
                
            }
            
            respuesta.setContent(listCategories);
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<ArrayList<Category>> getInterests(int userId) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        ArrayList<Category> listCategory = new ArrayList();
        
        try {
            
            String query = "SELECT * FROM `interests` WHERE `user-id` = " + userId;
            
            rs = stm.executeQuery(query);
            
            while(rs.next()) {
                System.out.println("holas");
                Category category = new Category();
                category.setId(rs.getInt("category-id"));
                
                listCategory.add(category);
                
            }
            
            for (int i = 0; i < listCategory.size(); i++) {
                
                query = "SELECT * FROM `products-categories` WHERE `category-id` = " + listCategory.get(i).getId();
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                listCategory.get(i).setName(rs.getString("category-name"));
                
            }
            
            respuesta.setContent(listCategory);
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<String> putInterest(int userId, ArrayList<Category> interests) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            String query = "DELETE FROM `interests` WHERE `user-id` = " + userId;
            
            stm.executeUpdate(query);
            
            for (int i = 0; i < interests.size(); i++) {
                System.out.println(interests.get(i).getId());
                query = "INSERT INTO `interests` ("
                        + "`category-id` ,"
                        + "`user-id` "
                        + ") VALUES ("
                        + interests.get(i).getId() + ", "
                        + userId + ")" ;
                stm.executeUpdate(query);
            }
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
}

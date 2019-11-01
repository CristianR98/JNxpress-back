package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Category;
import models.Condition;
import models.Filter;
import models.Product;
import models.User;
import response.Respuesta;


public class ProductsDB extends MySql {
    
    private static String url = "http://localhost:8080/jnxpress/";    
    
    protected static Respuesta existDB(int id) {
        
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
            
            respuesta = exception(e);  

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
            
                respuesta = exception(e);
                
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<Product> getProduct(int id, int userId) {
        
        Respuesta<Product> respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            String query = "SELECT * FROM `products` WHERE `product-id` = " + id ;
            
            rs = stm.executeQuery(query);
            
            if (rs.next()) {
                
                Product product = new Product();
                product.setId(id);
                product.setName(rs.getString("product-name"));
                product.setDescription(rs.getString("product-description"));
                product.setPrice(rs.getInt("product-price"));
                product.setStock(rs.getInt("product-stock"));
                product.setImage(url + rs.getString("product-image"));
                product.setImageMin(url + rs.getString("product-image-min"));
                product.setAppreciation(rs.getInt("product-appreciation"));
                product.setDate(rs.getString("product-date"));
                product.setUser(new User());
                product.getUser().setId(rs.getInt("user-id"));
                product.setCategory(new Category());
                product.getCategory().setId(rs.getInt("category-id"));
                product.setCondition(new Condition());
                product.getCondition().setId(rs.getInt("condition-id"));
                
                
                if (userId != 0) {
                    product.isYourProduct = userId==product.getUser().getId();
                    product.favorite = isFavorite(userId, product.getId());
                }
                
                respuesta.setContent(product);
                
            }
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
    }
    
    
    public static Respuesta<ArrayList<Product>> getProductsByFilter(Filter filter,int userId) {
        
        Respuesta<ArrayList<Product>> respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        ArrayList<Product> listProduct = new ArrayList<>();
        
        if (respuesta.isOk()) {
            String query = "SELECT * FROM `products` WHERE `product-name` LIKE '%" + filter.getTerm() + "%'";
            
            int id = UsersDB.existUsername(filter.getUser());
            
            if (id > 0) {
                query += " AND `user-id` = " + id;
            }
            if (filter.getCategory() > 0) {
                query += " AND `category-id` = " + filter.getCategory();
            }
            if (filter.getCondition() > 0) {
                query += " AND `condition-id` = " + filter.getCondition();
            }
            
            query += " ORDER BY `product-id` DESC LIMIT " + filter.getIndex() + ", 6 ";
            
            try {
                
                rs = stm.executeQuery(query);
                
                while(rs.next()) {
                    
                    Product product = new Product(
                        rs.getInt("product-id"),
                        rs.getString("product-name"),
                        rs.getString("product-description"),
                        rs.getInt("product-price"),
                        rs.getInt("product-stock"),
                        url+rs.getString("product-image"),
                        url+rs.getString("product-image-min"),
                        rs.getString("product-date")
                    );
                    
                    product.setAppreciation(rs.getInt("product-appreciation"));
                    listProduct.add(product);
                    
                }
                
                if (userId != 0) {
                    
                    for (int i = 0; i < listProduct.size() ; i++) {
                    
                        listProduct.get(i).favorite = isFavorite(userId, listProduct.get(i).getId());
                        
                    }
                
                }
                
                respuesta.setContent(listProduct);
                
            }catch(SQLException e) {
                respuesta = exception(e);
            }
            
            
        }
        
        closeConnection();
        
        return respuesta;
    }
    
    public static Respuesta<ArrayList<Product>> getFavorites(int userId) {
        
        Respuesta<ArrayList<Product>> respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        ArrayList<Product> listProducts = new ArrayList();
        
        try {
            
            String query = "SELECT * FROM `products-favorites` WHERE `user-id` = " + userId;
            
            rs = stm.executeQuery(query);
            
            ArrayList ids = new ArrayList();
            
            while(rs.next()) {
                
                ids.add(rs.getInt("product-id"));
                
            }
            
            for (int i = 0; i < ids.size(); i++) {
                
                query = "SELECT * FROM `products` WHERE `product-id` = " + ids.get(i);
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                Product product = new Product();
                product.setId(rs.getInt("product-id"));
                product.setName(rs.getString("product-name"));
                product.setDescription(rs.getString("product-description"));
                product.setPrice(rs.getInt("product-price"));
                product.setStock(rs.getInt("product-stock"));
                product.setImage(url + rs.getString("product-image"));
                product.setImageMin(url + rs.getString("product-image-min"));
                product.setAppreciation(rs.getInt("product-appreciation"));
                product.setDate(rs.getString("product-date"));
                product.setUser(new User());
                product.getUser().setId(rs.getInt("user-id"));
                product.setCategory(new Category());
                product.getCategory().setId(rs.getInt("category-id"));
                product.setCondition(new Condition());
                product.getCondition().setId(rs.getInt("condition-id"));
                
                listProducts.add(product);
                
            }
            
            respuesta.setContent(listProducts);
            
            closeConnection();
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    public static Respuesta<String> postProduct(Product product){
        
        Respuesta<String> respuesta = getConnection();
       
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
                
               
            String query = "INSERT INTO `products` ("
                    + "`user-id`" + ","
                    + "`product-name`" + ","
                    + "`product-description`" + ","
                    + "`product-price`" + ","
                    + "`product-stock`" + ","
                    + "`product-image`" + ","
                    + "`product-image-min`" + ","
                    + "`category-id`" + ","
                    + "`condition-id`"
                    + ") VALUES ("
                    + product.getUser().getId() + ","
                    + "'" + product.getName() + "'" + ","
                    + "'" + product.getDescription() + "'" + ","
                    + product.getPrice() + ","
                    + product.getStock() + ","
                    + "'" + product.getImage() + "'" + ","
                    + "'" + product.getImageMin() + "'" + ","
                    + product.getCategory().getId() + ","
                    + product.getCondition().getId()
                    + ")";
                
            
            stm.executeUpdate(query);
            
        
        }catch(SQLException e) {
            respuesta = exception(e);
        }
            
        closeConnection();
        
        return respuesta;
    }
    
    public static Respuesta<String> postFavorite(int userId, int productId) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        try {
            
            String query = "SELECT * FROM `products-favorites` WHERE `user-id` = " + userId + " AND `product-id` = " + productId;
            
            rs = stm.executeQuery(query);
            
            if (rs.next()) {
                
                query = "DELETE FROM `products-favorites` WHERE `user-id` = " + userId + " AND `product-id` = " + productId;
                respuesta.setMessage("Removido de favoritos");
                
            }else { 
            
                query = "INSERT INTO `products-favorites` ("
                    + "`user-id`, `product-id`"
                    + ") VALUES ("
                    + userId + ", " + productId + ")";
                respuesta.setMessage("Añadido de favoritos");

            }
            
            stm.executeUpdate(query);
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
         
        return respuesta;
        
    }
    
    public static Respuesta<String> putProduct(Product newProduct) {
        
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
            System.out.println(newProduct.getImage());
            System.out.println(newProduct.getImageMin());
            String query = "UPDATE `products` SET "
                    + "`product-name` = '" + newProduct.getName() + "',"
                    + "`product-description` = '" + newProduct.getDescription()+ "',"
                    + "`product-price` = " + newProduct.getPrice() + ","
                    + "`product-stock` = " + newProduct.getStock() + ","
                    + "`product-image` = '" + newProduct.getImage() + "',"
                    + "`product-image-min` = '" + newProduct.getImageMin() + "',"
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
    
    public static Respuesta deleteProduct( int idProduct, int idUser ) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = checkUpdate(idUser, idProduct);
        
        if(!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        try {
            
            String query = "DELETE FROM `products` WHERE `product-id` = " + idProduct;

            stm.executeUpdate(query);
            
        }catch (SQLException e) {
            
            respuesta = exception(e);
            
        }
        
        return respuesta;
        
    }
    
    private static Respuesta checkUpdate(int idUser, int idProduct) {
        
        Respuesta respuesta = UsersDB.existDB(idUser);
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        respuesta = existDB(idProduct);
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        respuesta = isYourProduct(idUser, idProduct);
        
        if (!respuesta.isOk()) {
            closeConnection();
            return respuesta;
        }
        
        return respuesta;
        
    }
    
    private static boolean isFavorite(int userId, int productId) {
        
        try {
            System.out.println(userId + " , " + productId);
            String query = "SELECT * FROM `products-favorites` WHERE `user-id` = " + userId + " AND `product-id` = " + productId;
            
            ResultSet favorite = stm.executeQuery(query);
            
            if (favorite.next()) {
                System.out.println(favorite.getString("product-id"));
                return true;
            }
            else {
                return false;
            }
            
        }catch (SQLException e) {
            System.out.println("favorite"+e.getLocalizedMessage());
            return false;
        }
        
    }
    
}

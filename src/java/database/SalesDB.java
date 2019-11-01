package database;

import static database.MySql.getConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Product;
import models.Sale;
import models.User;
import response.Respuesta;

public class SalesDB extends MySql{
    
    public static Respuesta<ArrayList<Sale>> getSales(int userId, String type) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        ArrayList<Sale> listSales = new ArrayList();
        
        try {
            
            String query = "SELECT * FROM `" + type + "s` WHERE `user-id` = " + userId;
            
            rs = stm.executeQuery(query);
            
            while(rs.next()) {
                
                Sale sale = new Sale();
                Product product = new Product();
                sale.setId(rs.getInt(type+"-id"));
                product.setId(rs.getInt("product-id"));
                sale.setProduct(product);
                sale.setLot(rs.getInt(type+"-lot"));
                sale.setDate(rs.getString(type+"-date"));
                
                listSales.add(sale);
                
            }
            
            respuesta.setContent(listSales);
            
            for (int i = 0; i < listSales.size(); i++) {
                
                Product product = listSales.get(i).getProduct();
                query = "SELECT `product-name` FROM `products` WHERE `product-id` = " + product.getId();
                
                rs = stm.executeQuery(query);
                
                rs.next();
                
                product.setName(rs.getString("product-name"));
                
            }
            
        }catch (SQLException e ) {
            respuesta = exception(e);
        }
        
        return respuesta;
        
    }
    
    
    public static Respuesta<String> sale(int userId, int productId, int stock) {
        
        Respuesta respuesta = getConnection();
        
        if (!respuesta.isOk()) {
            return respuesta;
        }
        
        Product product = new Product();
        User user = new User();
        User target = new User();
        
        
        try {
            
            String query = "SELECT * FROM `products` WHERE `product-id` = " + productId;
            
            rs = stm.executeQuery(query);
            
            if (!rs.next()) {
                respuesta = new Respuesta(403, false, "Producto no encontrado!");
                closeConnection();
                return respuesta;
            }
            
            product.setPrice(rs.getInt("product-price"));
            product.setStock(rs.getInt("product-stock"));
            target.setId(rs.getInt("user-id"));
            
            if (product.getStock() < stock) {
                respuesta = new Respuesta(403, false, "No hay suficiente stock!");
                closeConnection();
                return respuesta;
            }
            
            query = "SELECT * FROM `users` WHERE `user-id` IN ( "+userId + " , " + target.getId() + " )";
            
            rs = stm.executeQuery(query);
            
            while(rs.next()) {
                User createUser = new User();
                
                createUser.setId(rs.getInt("user-id"));
                createUser.setBalance(rs.getFloat("user-balance"));
                
                if (createUser.getId() == target.getId()) {
                    target = createUser;
                    target.setSales(rs.getInt("user-sales"));
                }else {
                    user = createUser;
                    target.setPurchases(rs.getInt("user-purchases"));
                }
                
            }
            
            if (user.getBalance() < product.getPrice() * stock ) {
                respuesta = new Respuesta(403, false, "No tienes suficiente saldo!");
                closeConnection();
                return respuesta;
            }
            
            query = "UPDATE `products` SET "
                    + "`product-stock` = " + (product.getStock() - stock)
                    + " WHERE `product-id` = " + productId;
            
            stm.executeUpdate(query);
            
            query = "UPDATE `users` SET "
                    + "`user-balance` = " + (user.getBalance() - product.getPrice() * product.getStock()) + ", "
                    + "`user-purchases` = " + (user.getPurchases() + 1)
                    + " WHERE `user-id` = " + user.getId();
            
            stm.executeUpdate(query);
            
            query = "UPDATE `users` SET "
                    + "`user-balance` = " + (target.getBalance() + product.getPrice() * product.getStock()) + ", "
                    + "`user-sales` = " + (target.getSales() + 1)
                    + " WHERE `user-id` = " + target.getId();
            
            stm.executeUpdate(query);
            
            
            query = "INSERT INTO `purchases` ("
                    + "`user-id` , "
                    + "`product-id` , "
                    + "`purchase-lot` "
                    + ") VALUES ( "
                    + user.getId() + ", "
                    + productId + ", "
                    + stock + ")";
            
            stm.executeUpdate(query);
            
            query = "INSERT INTO `sales` ("
                    + "`user-id` , "
                    + "`product-id` , "
                    + "`sale-lot` "
                    + ") VALUES ( "
                    + target.getId() + ", "
                    + productId + ", "
                    + stock + ")";
            
            stm.executeUpdate(query);
            
            
        }catch (SQLException e) {
            respuesta = exception(e);
        }
        
        closeConnection();
        
        return respuesta;
        
    }
}

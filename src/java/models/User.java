package models;


import database.UsersDB;
import java.util.ArrayList;
import response.Respuesta;


public class User {
    private int id;
    private String username;
    private String description;
    private String email;
    private String password;
    private float balance;
    private int sales;
    private int purchases;
    private int appreciation;
    private ArrayList<Category> interests;

    public User(int id, String username, String email, float balance, int sales, int purchase, int appreciation) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.sales = sales;
        this.purchases = purchase;
        this.appreciation = appreciation;
    }

    public User() {
        id = 0;
        username = "";
        description = "";
        this.email = "";
        this.password = "";
        this.balance = 0;
        this.sales = 0;
        this.purchases = 0;
        this.appreciation = 0;
        this.interests = new ArrayList<Category>();
    }
    
    
        
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    public int getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(int appreciation) {
        this.appreciation = appreciation;
    }

    public ArrayList<Category> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Category> interests) {
        this.interests = interests;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Respuesta existDB() {
        return UsersDB.getUserById(id);
    }
    
    public Respuesta validate() {
        
        Respuesta respuesta = validatePassword(password);
        if (!respuesta.isOk()) {
            return respuesta;
        }
        respuesta = validateUsername();
        if (!respuesta.isOk()) {
            return respuesta;
        }
        respuesta = validateEmail();
        if (!respuesta.isOk()) {
            return respuesta;
        } 
        return respuesta;
    }
    
    public static Respuesta validatePassword(String password) {
        
        Respuesta respuesta = new Respuesta(200, true, "");
        
        if (password.length() >= 8 && password.length() <= 30) {
            
            return respuesta;
            
        }
            
        respuesta = new Respuesta(403, false, "El password de usuario debe tener entre 8 y 30 digitos.");
        
        return respuesta;
        
    }
    
    private Respuesta validateUsername() {
        
        Respuesta respuesta;
        
        if (username.length() >= 6 && username.length() <= 20) {
            
            respuesta = new Respuesta(200, true, "OK!.");
            
        }else {
            respuesta = new Respuesta(403, false, "El nombre de usuario debe tener entre 6 y 20 digitos.");
        }
        
        return respuesta;
    }
    
    private Respuesta validateEmail() { 
        
        Respuesta respuesta;
        
        if (email.length() <= 60) {
            
            respuesta = new Respuesta(200, true, "OK!");
            
        }else {
            respuesta = new Respuesta(403, false, "El email no debe ser menor de 60 digitos.");
        }
        
        return respuesta;
        
    }
    
}

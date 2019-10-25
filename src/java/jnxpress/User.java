/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnxpress;


import database.UsersDB;
import java.util.ArrayList;
import response.Respuesta;

/**
 *
 * @author Nahu
 */
public class User {
    private int id;
    private String username;
    private String description;
    private String email;
    private String password;
    private float balance;
    private int sales;
    private int purchase;
    private int appreciation;
    private ArrayList<Category> interests;

    public User(int id, String username, String email, float balance, int sales, int purchase, int appreciation) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.balance = balance;
        this.sales = sales;
        this.purchase = purchase;
        this.appreciation = appreciation;
    }
    
    public User() {
        
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

    public int getPurchase() {
        return purchase;
    }

    public void setPurchase(int purchase) {
        this.purchase = purchase;
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
        return UsersDB.getUserForId(id);
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
            
            respuesta = UsersDB.getUserForUsername(username);
            
        }else {
            respuesta = new Respuesta(403, false, "El nombre de usuario debe tener entre 6 y 20 digitos.");
        }
        
        return respuesta;
    }
    
    private Respuesta validateEmail() { 
        
        Respuesta respuesta;
        
        if (email.length() <= 60) {
            
            respuesta = UsersDB.getUserForUsername(username);
            
        }else {
            respuesta = new Respuesta(403, false, "El email no debe ser menor de 60 digitos.");
        }
        
        return respuesta;
        
    }
    
}

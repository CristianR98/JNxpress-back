/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnxpress;

import database.MySql;
import response.Respuesta;

/**
 *
 * @author Nahu
 */
public class User {
    public int id;
    private String username;
    private String description;
    private String email;
    private String password;
    private String confirmPassword;
    private float balance;
    private int sales;
    private int purchase;
    private int appreciation;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public Respuesta existDB() {
        return new Respuesta(200,true,"que onda");
        //return MySql.getUserForId(id);
    }
    
    public Respuesta validate() {
        
        
        Respuesta respuesta = validatePassword();
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
    
    private Respuesta validatePassword() {
        
        Respuesta respuesta;
        
        if (!password.equals(confirmPassword)) {
            respuesta = new Respuesta(403, false, "Los password no coinciden!");
            return respuesta;
        }
        if (password.length() >= 8 && password.length() <= 30) {
            respuesta = new Respuesta(200, true, "");
            return respuesta;
        }else {
            respuesta = new Respuesta(403, false, "El password de usuario debe tener entre 8 y 30 digitos.");
            return respuesta;
        }
        
        
    }
    
    private Respuesta validateUsername() {
        
        Respuesta respuesta;
        
        if (username.length() >= 6 && username.length() <= 20) {
            
            respuesta = MySql.getUserForUsername(username);
            
        }else {
            respuesta = new Respuesta(403, false, "El nombre de usuario debe tener entre 6 y 20 digitos.");
        }
        
        return respuesta;
    }
    
    private Respuesta validateEmail() { 
                
        return MySql.getUserForEmail(email);
        
    }
    
}

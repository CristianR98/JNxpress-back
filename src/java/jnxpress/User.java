/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jnxpress;

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
    
    
}

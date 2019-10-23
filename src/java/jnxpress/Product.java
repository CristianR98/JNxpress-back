package jnxpress;

import response.Respuesta;

public class Product {
    private int id;
    private User user;

    private String name;
    private String description;
    private float price;
    private int stock;
    private String image;
    private String date;
    private Category category;
    private Condition condition; 

    public Product(int id, String name, String description, float price, int stock, String image, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.date = date;
    }
    
    public Product() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    
    public boolean validate() {
        
        if (validateName() && validateDescription() && validatePrice() && validateStock()) {
            return true;
        }
        
        return false;
    }
    
    private boolean validateName() {
        
        if (name.length() > 40) {
            return false;
        }
        
        return true;
        
    }
    private boolean validateDescription() {
        
        if (description.length() > 1000) {
            return false;
        }
        
        return true;
        
    }
    private boolean validatePrice() {
        
        if (price >= 1000000) {
            return false;
        }
        
        return true;
        
    }
    private boolean validateStock() {
        
        if (stock >= 1000) {
            return false;
        }
        
        return true;
        
    }
    
    
    
}

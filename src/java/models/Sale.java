package models;

public class Sale {
    private int id;
    private User user;
    private Product product;
    private int lot;
    private String date;

    public Sale(int id, User user, Product product, int lot, String date) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.lot = lot;
        this.date = date;
    }

    public Sale() {
        
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}

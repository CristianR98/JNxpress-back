package models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.http.Part;
import response.Respuesta;

public class Product {
    private int id;
    private User user;

    private String name;
    private String description;
    private float price;
    private int stock;
    private String image;
    private String imageMin;
    private String date;
    private Category category;
    private Condition condition;
    private int appreciation;
    public boolean favorite;
    public boolean isYourProduct;

    public Product(int id, String name, String description, float price, int stock, String image, String imageMin, String date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.imageMin = imageMin;
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

    public String getImageMin() {
        return imageMin;
    }

    public void setImageMin(String imageMin) {
        this.imageMin = imageMin;
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

    public int getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(int appreciation) {
        this.appreciation = appreciation;
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
    
    
    public Respuesta getAndMoveImage(Part imagePart, Part imageMinPart) {
        
        Respuesta respuesta = new Respuesta(200, true, "OK!");
        File ruta = new File("..\\..\\..\\Documents");
        File userPath = new File("..\\..\\..\\Documents\\NetBeansProjects\\JNxpress-back\\web\\users\\" + user.getUsername());
        userPath.mkdir();
        File productPath = new File(userPath.getAbsolutePath() + "\\" + name);
        productPath.mkdir();
        
        
        try {
            
            File imagefile = new File(productPath.getCanonicalPath() + "\\" + imagePart.getSubmittedFileName());
            File imageMinfile = new File(productPath.getCanonicalPath() + "\\" + imageMinPart.getSubmittedFileName());

            InputStream inputStream = imagePart.getInputStream();  
            OutputStream outputStream = new FileOutputStream(imagePart.getSubmittedFileName());

            
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.close();
                
            inputStream = imageMinPart.getInputStream();
            outputStream = new FileOutputStream(imageMinPart.getSubmittedFileName());
                
            read = 0;
                
            while ((read = inputStream.read(bytes)) != -1) {
               outputStream.write(bytes, 0, read);
            }
                
            outputStream.close();
                
            Path origenPath = Paths.get(imagePart.getSubmittedFileName()).toAbsolutePath();
            Path destinoPath = Paths.get(imagefile.getAbsolutePath());
            
            Files.move(origenPath, destinoPath,StandardCopyOption.REPLACE_EXISTING);

            origenPath = Paths.get(imageMinPart.getSubmittedFileName()).toAbsolutePath();
            destinoPath = Paths.get(imageMinfile.getAbsolutePath());
            
            Files.move(origenPath, destinoPath,StandardCopyOption.REPLACE_EXISTING);
            image =  "users/" + user.getUsername() + "/" + name + "/" + imagePart.getSubmittedFileName();
            imageMin = "users/" + user.getUsername() + "/" + name + "/" + imageMinPart.getSubmittedFileName();
            
        }catch(IOException e) {
            respuesta = new Respuesta(500, false, e.getMessage());
            System.out.println(e);
        }
        
        return respuesta;
       
    }
    
    
}

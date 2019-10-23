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
public class Filter {
    private String term;
    private Category category;
    private Condition condition;
    private String user;

    public Filter(String term, String category, String condition, String user) {
        this.term = term;
        this.category = new Category();
        this.category.setId(Integer.parseInt(category));
        this.condition = new Condition();
        this.condition.setId(Integer.parseInt(condition));
        this.user = user;
    }
    
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
//    public boolean validateFilter() {
//        this.
//        
//        
//        return false;
//    }
}

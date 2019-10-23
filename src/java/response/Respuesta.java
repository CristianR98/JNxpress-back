package response;

public class Respuesta<T> {
    private int status;
    private boolean ok;
    private String message;
    private T content;

    public Respuesta(int status, boolean ok, String message) {
        this.status = status;
        this.ok = ok;
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}

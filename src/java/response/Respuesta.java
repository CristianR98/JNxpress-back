package response;

public class Respuesta<T> {
    private int _code;
    private String _message;
    private T _content;

    public Respuesta(int code, String message) {
        _code = code;
        _message = message;
    }

    public T getContent() {
        return _content;
    }

    public void setContent(T _content) {
        this._content = _content;
    }
    
    
    public int getCode() {
        return _code;
    }

    public void setCode(int _code) {
        this._code = _code;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String _message) {
        this._message = _message;
    }
    
    
}

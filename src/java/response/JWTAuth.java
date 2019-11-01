package response;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import models.User;



public class JWTAuth {
    
    private static byte[] key = "jnxpress".getBytes();
    private static Gson json = new Gson();
    
    public static Respuesta createToken(User user) {
        
        Date date = new Date();
        Date exp = new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours() + 1, date.getMinutes() );
        
        String userJson = json.toJson(user);
        
        
        String jwt = Jwts.builder()
                .setSubject("user/nosepxd")
                .setExpiration(exp)
                .claim("user", userJson)
                .signWith(SignatureAlgorithm.HS256, "jnxpress".getBytes())
                .compact();
            
        Respuesta respuesta = new Respuesta(201, true, "Sesión iniciada!");
        respuesta.setContent(jwt);
        return respuesta;
        
        
    }
    
    public static Respuesta<User> verifyToken(String token) {
        
        Respuesta respuesta;
        Jws<Claims> claims;
        
        try {
            claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token);
            
            String userJson = claims.getBody().get("user", String.class);
        
            User user = json.fromJson( userJson, User.class );
        
            respuesta = new Respuesta(200, true, "Session iniciada!");
            
            respuesta.setContent(user);
            
        }catch (ExpiredJwtException e) {
            respuesta = new Respuesta(403, false, "Session expirada!");
        }catch (SignatureException e) {
            respuesta = new Respuesta(403, false, "No autorizado!");
        }catch (MalformedJwtException e) {
            respuesta = new Respuesta(403, false, "No autorizado!");
        }
        return respuesta;
    }
    
}

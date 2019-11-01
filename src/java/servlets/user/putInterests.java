package servlets.user;

import com.google.gson.Gson;
import database.CategoriesDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import models.User;
import response.JWTAuth;
import response.Respuesta;


public class putInterests extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Gson json = new Gson();
            
            String token = request.getParameter("token");
            
            Respuesta<User> authorized = JWTAuth.verifyToken(token);
            
            if (authorized.isOk()) {
                
                ArrayList<Category> array = json.fromJson(request.getReader().readLine(), ArrayList.class);
                ArrayList<Category> interests = new ArrayList();
                for (int i = 0; i < array.size(); i++) {
                    
                    String jsonCat = json.toJson(array.get(i));
                    Category category = json.fromJson(jsonCat, Category.class);
                    interests.add(category);
                    
                }
                User user = authorized.getContent();
                
                Respuesta<String> respuesta = CategoriesDB.putInterest(user.getId(), interests);
                
                out.println(json.toJson(respuesta));
                
            }else {
                out.println(json.toJson(authorized));
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

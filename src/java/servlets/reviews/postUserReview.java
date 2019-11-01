package servlets.reviews;

import com.google.gson.Gson;
import database.ReviewsDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Review;
import models.User;
import response.JWTAuth;
import response.Respuesta;


public class postUserReview extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Gson json = new Gson();
            
            String token = request.getParameter("token");
            
            Respuesta<User> authorized = JWTAuth.verifyToken(token);
            
            if (authorized.isOk()) {
                
                Review<User> review = json.fromJson(request.getReader().readLine(), Review.class);

                String userString = json.toJson(review.getTarget());

                User user = json.fromJson(userString, User.class);

                review.setTarget(user);
                
                review.setUser(authorized.getContent());

                Respuesta respuesta = ReviewsDB.postUserReview(review, authorized.getContent().getId());
                
                out.print(json.toJson(respuesta));
                
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

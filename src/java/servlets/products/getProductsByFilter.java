package servlets.products;

import com.google.gson.Gson;
import database.ProductsDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Filter;
import models.Product;
import models.User;
import response.JWTAuth;
import response.Respuesta;


public class getProductsByFilter extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Gson json = new Gson();
            
            String token = request.getParameter("token");
            
            Respuesta<User> user = JWTAuth.verifyToken(token);
            
            int id = user.isOk()?user.getContent().getId():0;
            
            
            Filter filter = json.fromJson(request.getReader().readLine(), Filter.class);
            System.out.println(filter.getTerm());
            System.out.println(filter.getCategory());
            System.out.println(filter.getUser());
            System.out.println(filter.getCondition());
            Respuesta<ArrayList<Product>> respuesta = ProductsDB.getProductsByFilter(filter,id);
            
            out.println(json.toJson(respuesta));
            
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

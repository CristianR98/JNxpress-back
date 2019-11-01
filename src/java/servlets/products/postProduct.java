package servlets.products;

import com.google.gson.Gson;
import database.ProductsDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Product;
import models.User;
import response.JWTAuth;
import response.Respuesta;


@MultipartConfig
public class postProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Gson json = new Gson();
            
//            Product product = new Product();
//            product.setName(request.getParameter("name"));
//            product.setDescription(request.getParameter("description"));
//            product.setPrice(Integer.parseInt(request.getParameter("price")));
//            Category category = new Category();
//            category.setId(Integer.parseInt(request.getParameter("category")));
//            Condition condition = new Condition();
//            condition.setId(Integer.parseInt(request.getParameter("condition")));
//            product.setCategory(category);
//            product.setCondition(condition);

            
            String token = request.getParameter("token");
            
            Respuesta<User> authorize = JWTAuth.verifyToken(token);
            System.out.println(request.getParameter("product"));
            Product product = json.fromJson(request.getParameter("product"), Product.class);
            if (authorize.isOk()) {
                
                
                User user = authorize.getContent();

                Part imagePart = request.getPart("image");
                Part imageMinPart = request.getPart("image-min");
                
                product.setUser(user);
                
                Respuesta respuesta = product.getAndMoveImage(imagePart, imageMinPart);
                
                if (respuesta.isOk()) {
                    
                    respuesta = ProductsDB.postProduct(product);
                    
                    out.println(json.toJson(respuesta));
                    
                }else {
                    out.println(json.toJson(respuesta));
                }
                
                
            }else {
                out.println(json.toJson(authorize));
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

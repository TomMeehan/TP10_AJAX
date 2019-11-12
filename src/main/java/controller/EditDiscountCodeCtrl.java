package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.*;

@WebServlet(name = "EditDiscountCodeCtrl", urlPatterns = {"/EditDiscountCodeCtrl"})
public class EditDiscountCodeCtrl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            DAO dao = new DAO(DataSourceFactory.getDataSource());
            
            String action = request.getParameter("action");
            
            if( action != null) {
                
                
                String code = request.getParameter("code");
                
                switch(action){
                    case "ADD":   
                        try {
                            float rate = Float.parseFloat(request.getParameter("rate"));
                            dao.addDiscountCode(new DiscountEntity(code,rate));
                        } catch (SQLException e){
                            request.setAttribute("error","Impossible d'ajouter ce code, vérifiez les valeurs");
                        }
                        break;
                    case "DELETE":
                        try { 
                            dao.deleteDiscountCode(code);
                        }
                        catch (SQLException e){
                            request.setAttribute("error","Code " + code + " utilisé, impossible de le supprimer");
                        }
                    case "UPDATE": 
                        try {
                            float newRate = Float.parseFloat(request.getParameter("rate")); 
                            dao.updateDiscountCode(newRate, code);
                            request.setAttribute("updated","Code mis à jour");
                        } catch (SQLException e) {
                            request.setAttribute("error","Impossible de mettre à jour le code");
                        }
                        break;
                    default:
                        throw new Exception();
                }
                
            }

            LinkedList<DiscountEntity> codes = dao.getDiscountCodes();
            
            if (codes.isEmpty()) throw new Exception("Codes introuvables");
            
            request.setAttribute("codes", codes);
            
           
            request.getRequestDispatcher("views/EditDiscountCodeView.jsp").forward(request, response);
        
        }catch (Exception e){
            request.setAttribute("error", e.getMessage());			
            request.getRequestDispatcher("views/errorView.jsp").forward(request, response);  
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

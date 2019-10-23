/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import JDBC.DAO2;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
public class ShowClientInStateWithForm extends HttpServlet {

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
            throws ServletException, IOException, SQLException, DAOException {
        response.setContentType("text/html;charset=UTF-8");
        
        DAO2 dao = new DAO2(DataSourceFactory.getDataSource());
        
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateForl</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form method='GET' action='ShowClientInStateWithForm?state='>");
            out.println("<select name='state' required>");
            for(String state : dao.existingStates()){
                out.println("<option value='" + state + "'>" + state + "</option>");
            }
            out.println("</select>");
            out.println("<input type='submit' value='state'>");
            out.println("</form>");
            
            try {
                String val = request.getParameter("state");
                if(val == null){
                    throw new Exception();
                }
                
                List<CustomerEntity> listeclient = dao.customersInState(val);
                System.out.println(val);
                out.println("<table>");
                out.println("<tbody>");
                out.println("<tr><td><br>ID</br></td><td><br>Name</br></td><td><br>Address</br></td></tr>");
                for(CustomerEntity client : listeclient){
                    out.println("<tr>");
                    out.println("<td>" + client.getCustomerId() + "</td>");
                    out.println("<td>" + client.getName() + "</td>");
                    out.println("<td>" + client.getAddressLine1() + "</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
                
            } catch (Exception e) {
                out.printf("Choisissez un état!");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ShowClientInStateWithForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(ShowClientInStateWithForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ShowClientInStateWithForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(ShowClientInStateWithForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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

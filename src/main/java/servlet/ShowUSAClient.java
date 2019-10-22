/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DataSourceFactory;


/**
 *
 * @author pedago
 */
@WebServlet(name = "ShowUSAClient", urlPatterns = {"/ShowUSAClient"})
public class ShowUSAClient extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShowClient</title>");
            out.println("</head>");
            out.println("<body>");
            
            try {
                String val = request.getParameter("state");
                if(val == null){
                    throw new Exception();
                }
                
                DAO dao = new DAO(DataSourceFactory.getDataSource());
                List<CustomerEntity> listeclient = dao.customersInState(val);
                out.println("<table>");
                out.println("<tbody>");
                out.println("<tr><td><br>ID</br></td><td><br>Name</br></td><td><br>Address</br></td></tr>");
                for(CustomerEntity client : listeclient ){
                    out.println("<tr>");
                    out.println("<td>" + client.getCustomerId() + "</td>");
                    out.println("<td>" + client.getName() + "</td>");
                    out.println("<td>" + client.getAddressLine1() + "</td>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
                
            } catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
        out.println("</body>");
        out.println("</html>");
        } catch (Exception ex){
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
}

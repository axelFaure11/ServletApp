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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;
import simplejdbc.DataSourceFactory;

/**
 *
 * @author pedago
 */
@WebServlet(name = "StateForm", urlPatterns = {"/StateForm"})
public class StateForm extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        DAO2 dao = new DAO2(DataSourceFactory.getDataSource());
        
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateForl</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form method='GET' action='ShowUSAClient?state='>");
            out.println("<select name='state' required>");
            try {
                for(String state : dao.existingStates()){
                    out.println("<option value='" + state + "'>" + state + "</option>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(StateForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DAOException ex) {
                Logger.getLogger(StateForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</select>");
            out.println("<input type='submit' value='state'>");
            out.println("</form>");
        } 
    }
    
    
}

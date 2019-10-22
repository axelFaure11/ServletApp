/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pedago
 */
@WebServlet(name = "StateForm", urlPatterns = {"/StateForm"})
public class StateForm extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        try(PrintWriter out = response.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StateForl</title>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<form method='POST' action='ShowUSAClient?state=state_name'>");
            out.println("<select name='state_name' required>");
            out.println("<option value='CA'>CA</option>");
            out.println("<option value='TX'>TX</option>");
            out.println("</select>");
            out.println("<input type='submit' value='state_name'>");
            out.println("</form>");
        } 
    }
    
    
}

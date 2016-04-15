/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_no_clients extends HttpServlet {
HttpSession session;
String client_entry="",src="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        session=request.getSession();
        client_entry="";
        String clients=session.getAttribute("no_of_clients").toString();
System.out.println("no of clients is   "+clients);
        int no_of_clients=Integer.parseInt(clients);
System.out.println("no of clients is   "+no_of_clients);
System.out.println("provider is   "+no_of_clients);
for (int i=1; i<=no_of_clients;i++){
    client_entry+="<tr>"
            + "<td>"+i+"</td>"
            + "<td><input type=\"text\" value=\"\" name=\"fname"+i+"\" pattern=\".{3,}\"  id=\"fname"+i+"\" placeholder=\"First Name\" class=\"textbox\" style=\"width: 100px;\"   /></td>"
            + "<td><input type=\"text\" value=\"\" name=\"mname"+i+"\" pattern=\".{3,}\"  id=\"mname"+i+"\" placeholder=\"Middle Name\" class=\"textbox\"  style=\"width: 100px;\" /> </td>"
            + "<td><input type=\"text\" value=\"\" name=\"lname"+i+"\" pattern=\".{1,}\"  id=\"lname"+i+"\" placeholder=\"Last Name\" class=\"textbox\"  style=\"width: 100px;\" /></td>"
            + "<td><input type=\"text\" value=\"\" name=\"age"+i+"\" id=\"age"+i+"\" onkeypress=\"return numbers(event)\" maxlength=\"2\" placeholder=\"Age\" class=\"textbox\" style=\"width: 100px;\"  /></td>"
            + "<td><select name=\"gender"+i+"\"  id=\"gender"+i+"\"  class=\"textbox2\" style=\"width: 100px;\">"
            + "<option value=\"\">Choose Gender</option>"
            + "<option value=\"Male\">Male</option>"
            + "<option value=\"Female\">Female</option>"
            + "</select></td>"
            + "</tr>";
    
    
    
}

 System.out.println(" data will be  :  "+session.getAttribute("group_id")); 
 src=session.getAttribute("src").toString();
   session.setAttribute("client_entry", client_entry); 
   session.setAttribute("total_clients", no_of_clients);
   response.sendRedirect(src);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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

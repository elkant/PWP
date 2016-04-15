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
public class groups_maker extends HttpServlet {
int no_of_groups;
String group_details,grps;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session=request.getSession();
       grps =session.getAttribute("no_of_groups").toString();
       no_of_groups=Integer.parseInt(grps); 
       group_details="";
System.out.println("no of groups  :  "+no_of_groups);
int i=1;
while(i<=no_of_groups){
 group_details+="<tr><td>"+i+"</td><td><input type=\"text\" name=\"group"+i+"\" value=\"\" id=\"group"+i+"\" placeholder=\"Enter Group Name\" class=\"textbox\"/></td>"
         + "<td><input type=\"text\" name=\"location"+i+"\" id=\"location"+i+"\" value=\"\" placeholder=\"Location\" class=\"textbox\" /></td> "
         + "<td> <input type=\"text\" name=\"year_formed"+i+"\" id=\"year_formed"+i+"\" onkeypress=\"return numbers(event)\" maxlength=\"4\" value=\"\" placeholder=\"Year Formed\" class=\"textbox\" /></td></tr>";   
 i++;   
}
session.setAttribute("groups", group_details);
session.setAttribute("no_of_groups", i);
System.out.println("Out put"+group_details);
response.sendRedirect("add_groups2.jsp");
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

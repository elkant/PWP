/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
public class IndividualDecider extends HttpServlet {
HttpSession session;
String client_id,nextpage,messageid;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       session=request.getSession();
      nextpage=request.getParameter("markType");
      messageid=request.getParameter("message");
      System.out.println("message id : "+messageid);
       client_id="";
        int totalSelected=Integer.parseInt(request.getParameter("all").trim());
        for(int i=1; i<=totalSelected;i++){
 String checkbox=request.getParameter("checker_"+i);
if(checkbox!=null){
    System.out.println("CHECKBOX DATA "+checkbox+"    at position "+i);
    client_id+=request.getParameter("id_"+i)+",";  
 }
 }
 System.out.println("client ids : "+client_id);
  
// session.setAttribute("totalInd", totalSelected);
 session.setAttribute("indClicents", client_id);
 session.setAttribute("messageID", messageid);
 response.sendRedirect(nextpage);
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

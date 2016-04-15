/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CleanUp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pwp.dbConn;
/**
 *
 * @author Nyabuto Geofrey
 */
public class unMarkClients extends HttpServlet {
HttpSession session;
String clientId,messageID,status;
int counter=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        
        messageID=request.getParameter("message");
        System.out.println("entered here : "+messageID);
        counter=0;
        status="<font color=\"red\">No changes detected.</font>";
        int totalSelected=Integer.parseInt(request.getParameter("all").trim());
        for(int i=1; i<=totalSelected;i++){
 String checkbox=request.getParameter("checker_"+i);
if(checkbox!=null){
    System.out.println("CHECKBOX DATA "+checkbox+"    at position "+i);
    clientId=request.getParameter("id_"+i);  
    //      SET TO UNMARK SUMMARY REGISTER
      String unmarker="UPDATE register SET S"+messageID+" = '5' WHERE client_id='"+clientId+"'";
      conn.st.executeUpdate(unmarker);
      
      String unMarkReg2="UPDATE register2 SET value='5', date='0', datekey='0', month='', year='' WHERE client_id='"+clientId+"' AND session_no='"+messageID+"'";
      conn.st.executeUpdate(unMarkReg2);
      counter++;
 }
 }
   
        if(counter>0){     status=counter+" <font color=\"blue\">Clients were updated successfully.</font>";}
    session.setAttribute("unMarked", status);
    
        System.out.println("ended : "+counter);
        
    response.sendRedirect("unMarkClients.jsp");
    
    
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
        Logger.getLogger(unMarkClients.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(unMarkClients.class.getName()).log(Level.SEVERE, null, ex);
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

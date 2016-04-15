/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

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
public class deletePartnerData extends HttpServlet {
HttpSession session;
String partnerid,clientId,preventionID,partnerName,disPlayString;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
      
//       partnerid=request.getParameter("partnerid");
   
       String [] partners =request.getParameterValues("partner");
        for( String partner_id : partners){
            
      if(!partner_id.equals("")){
       partnerid=partner_id;
       System.out.println("partner id is : "+partnerid);
       position=0;
       String getPartner="SELECT partner_name FROM partner WHERE partner_id='"+partnerid+"'";
       conn.rs=conn.st.executeQuery(getPartner);
       if(conn.rs.next()==true){
           partnerName=conn.rs.getString(1);
       }
       disPlayString+="Started merging : <b>"+partnerName+"</b><br>";
       session.removeAttribute("disPlayString");
       
       session.setAttribute("disPlayString", disPlayString);
          System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>started deleting...."+partnerName);
       String getClients="SELECT client_id FROM personal_information WHERE partner_id='"+partnerid+"'";
       conn.rs=conn.st.executeQuery(getClients);
       while(conn.rs.next()){
           clientId=conn.rs.getString(1);
           preventionID="";position++;
           String getID="SELECT id FROM prevention_messages WHERE client_id='"+clientId+"'";
   conn.rs1=conn.st1.executeQuery(getID);
   if(conn.rs1.next()==true){
     preventionID=conn.rs1.getString(1);
   }
   String deletePreventionCounselling="DELETE FROM prevention_counseling WHERE prevention_message_id='"+preventionID+"'";
   conn.st1.executeUpdate(deletePreventionCounselling);
    
   String deletefamily_planning_tb_pmtct="DELETE FROM family_planning_tb_pmtct WHERE prevention_message_id='"+preventionID+"'";
   conn.st1.executeUpdate(deletefamily_planning_tb_pmtct);
   
    String deletePreventionMessage="DELETE FROM prevention_messages WHERE client_id='"+conn.rs.getString(1)+"'";
   conn.st1.executeUpdate(deletePreventionMessage);
           
   
            String deleteregister2="DELETE FROM register2 WHERE client_id='"+clientId+"'";
            conn.st1.executeUpdate(deleteregister2);
           
           String deleteAdherence="DELETE FROM adherence WHERE client_id='"+clientId+"'";
           conn.st1.executeUpdate(deleteAdherence);
       
          String deleteregister="DELETE FROM register WHERE client_id='"+clientId+"'";
          conn.st1.executeUpdate(deleteregister);
         
          String deleteservices="DELETE FROM services_provided WHERE client_id='"+clientId+"'";
          conn.st1.executeUpdate(deleteservices);
       
         String deleteClient="DELETE FROM personal_information WHERE client_id='"+clientId+"'";
         conn.st1.executeUpdate(deleteClient);
     
           System.out.println("at pos : "+position);
       }
          System.out.println("completed deleting client tables.");
       
     String deleteGroups="DELETE FROM groups WHERE partner_id='"+partnerid+"'";
    conn.st.executeUpdate(deleteGroups);
    
     String deleteSessions="DELETE FROM sessions WHERE partner_id='"+partnerid+"'";
    conn.st.executeUpdate(deleteSessions);
    
    
     String deleteSP="DELETE FROM service_provider WHERE partner_id='"+partnerid+"'";
    conn.st.executeUpdate(deleteSP);
    
    String deleteNOGroup="DELETE FROM no_group WHERE partner_id='"+partnerid+"'";
    conn.st.executeUpdate(deleteNOGroup);
    
       System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>completed deleting...."+partnerName);
      }
      
   }
    response.sendRedirect("deletePartnerData.jsp");
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
        Logger.getLogger(deletePartnerData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(deletePartnerData.class.getName()).log(Level.SEVERE, null, ex);
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

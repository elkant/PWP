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
 * @author Geofrey Nyabuto
 */
public class IndividualRemover extends HttpServlet {
HttpSession session;
String client_id,groupname;
int pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn  = new dbConn();
            pos=0;
            
       String getDetails="SELECT personal_information.client_id,groups.group_name FROM personal_information "
               + "JOIN groups ON personal_information.group_id=groups.group_id WHERE groups.group_id!='0'";
       conn.rs=conn.st.executeQuery(getDetails);
       while(conn.rs.next()){
        client_id=conn.rs.getString(1);
        groupname=conn.rs.getString(2).toUpperCase();
          
           
        if(groupname.contains("INDIVIDUAL")){
            pos++;
        System.out.println(pos+"Client id : "+client_id+"   group name   "+groupname);
       
        String updategroup="UPDATE personal_information SET group_id='0' WHERE client_id='"+client_id+"'";
        conn.st1.executeUpdate(updategroup);
        
        } 
           
       }
      System.out.println("COMPLETED LOADING ALL CLIENTS");
       
      if(conn.conn!=null){conn.conn.close();}
       
        } finally {
            out.close();
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
        Logger.getLogger(IndividualRemover.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(IndividualRemover.class.getName()).log(Level.SEVERE, null, ex);
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

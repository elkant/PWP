/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataQuality;

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
public class DeleteClients extends HttpServlet {
HttpSession session;
String id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
          session=request.getSession();
       dbConn conn = new dbConn();
       
       id=request.getParameter("id");
       System.out.println("called ACTION DELETE : "+id);
//          DELETE FROM REGISTER2==============================
       
      String getReg2="SELECT * FROM register2 WHERE client_id='"+id+"'";
     
      conn.rs=conn.st.executeQuery(getReg2);
      while(conn.rs.next()){
         String insertReg2="INSERT INTO deletedregister2 (reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) VALUES (?,?,?,?,?,?,?,?,?)";
         conn.pst=conn.conn.prepareStatement(insertReg2);
         
           conn.pst.setString(1, conn.rs.getString(1));
           conn.pst.setString(2, conn.rs.getString(2));
           conn.pst.setString(3, conn.rs.getString(3));
           conn.pst.setString(4, conn.rs.getString(4));
           conn.pst.setString(5, conn.rs.getString(5));
           conn.pst.setString(6, conn.rs.getString(6));
           conn.pst.setString(7, conn.rs.getString(7));
           conn.pst.setString(8, conn.rs.getString(8));
           conn.pst.setString(9, conn.rs.getString(9));
           
           conn.pst.executeUpdate();
      }
      String deleteregister2="DELETE FROM register2 WHERE client_id='"+id+"'";
       conn.st.executeUpdate(deleteregister2);
//    DELETE FROM REGISTER==========================================
       
       String getReg="SELECT * FROM register WHERE client_id='"+id+"'";
       conn.rs=conn.st.executeQuery(getReg);
       if(conn.rs.next()==true){
           
       String insertReg="INSERT INTO deletedregister (reg_id,client_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,timestamp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       conn.pst=conn.conn.prepareStatement(insertReg);
       
           conn.pst.setString(1, conn.rs.getString(1));
           conn.pst.setString(2, conn.rs.getString(2));
           conn.pst.setString(3, conn.rs.getString(3));
           conn.pst.setString(4, conn.rs.getString(4));
           conn.pst.setString(5, conn.rs.getString(5));
           conn.pst.setString(6, conn.rs.getString(6));
           conn.pst.setString(7, conn.rs.getString(7));
           conn.pst.setString(8, conn.rs.getString(8));
           conn.pst.setString(9, conn.rs.getString(9));
           conn.pst.setString(10, conn.rs.getString(10));
           conn.pst.setString(11, conn.rs.getString(11));
           conn.pst.setString(12, conn.rs.getString(12));
           conn.pst.setString(13, conn.rs.getString(13));
           conn.pst.setString(14, conn.rs.getString(14));
           conn.pst.setString(15, conn.rs.getString(15));
           conn.pst.setString(16, conn.rs.getString(16));
//           conn.pst.setString(17, conn.rs.getString(17));
           conn.pst.executeUpdate();
       }
       String deleteregister="DELETE FROM register WHERE client_id='"+id+"'";
       conn.st.executeUpdate(deleteregister);
       
       
       
       
//       DELETE FROM SERVICES PROVIDED================================
       
       String getAllServices="SELECT * FROM services_provided WHERE client_id='"+id+"'";
       conn.rs=conn.st.executeQuery(getAllServices);
       while(conn.rs.next()){
      String insertServices="INSERT INTO deletedservices(services_id,client_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,submission_year,timestamp,session_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      conn.pst=conn.conn.prepareStatement(insertServices);
      
           conn.pst.setString(1, conn.rs.getString(1));
           conn.pst.setString(2, conn.rs.getString(2));
           conn.pst.setString(3, conn.rs.getString(3));
           conn.pst.setString(4, conn.rs.getString(4));
           conn.pst.setString(5, conn.rs.getString(5));
           conn.pst.setString(6, conn.rs.getString(6));
           conn.pst.setString(7, conn.rs.getString(7));
           conn.pst.setString(8, conn.rs.getString(8));
           conn.pst.setString(9, conn.rs.getString(9));
           conn.pst.setString(10, conn.rs.getString(10));
           conn.pst.setString(11, conn.rs.getString(11));
           conn.pst.setString(12, conn.rs.getString(12));
           conn.pst.setString(13, conn.rs.getString(13));
           conn.pst.setString(14, conn.rs.getString(14));
           conn.pst.setString(15, conn.rs.getString(15));
           conn.pst.setString(16, conn.rs.getString(16));
           conn.pst.setString(17, conn.rs.getString(17));
           conn.pst.setString(18, conn.rs.getString(18));
//           conn.pst.setString(19, conn.rs.getString(19));
          conn.pst.executeUpdate();
       }
       String deleteservices="DELETE FROM services_provided WHERE client_id='"+id+"'";
       conn.st.executeUpdate(deleteservices);
       
       
//       DELETE MAIN CLIENTS ENTRY===============================
       String getDetails="SELECT * FROM clients WHERE client_id='"+id+"'";
       conn.rs=conn.st.executeQuery(getDetails);
           if(conn.rs.next()==true){
           String inserter="INSERT INTO deletedclients(client_id,fname,mname,lname,age,gender,group_id,groupings,district_id,partner_id,lessons_attended,marking_status,year,provider_id,timestamp,completionyear,completionmonth) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                   + "";
           conn.pst=conn.conn.prepareStatement(inserter);
           conn.pst.setString(1, conn.rs.getString(1));
           conn.pst.setString(2, conn.rs.getString(2));
           conn.pst.setString(3, conn.rs.getString(3));
           conn.pst.setString(4, conn.rs.getString(4));
           conn.pst.setString(5, conn.rs.getString(5));
           conn.pst.setString(6, conn.rs.getString(6));
           conn.pst.setString(7, conn.rs.getString(7));
           conn.pst.setString(8, conn.rs.getString(8));
           conn.pst.setString(9, conn.rs.getString(9));
           conn.pst.setString(10, conn.rs.getString(10));
           conn.pst.setString(11, conn.rs.getString(11));
           conn.pst.setString(12, conn.rs.getString(12));
           conn.pst.setString(13, conn.rs.getString(13));
           conn.pst.setString(14, conn.rs.getString(14));
           conn.pst.setString(15, conn.rs.getString(15));
//           conn.pst.setString(16, conn.rs.getString(17));
           conn.pst.setString(16, conn.rs.getString(17));
           conn.pst.setString(17, conn.rs.getString(18));
          conn.pst.executeUpdate();
           }    
       String deleteClient="DELETE FROM clients WHERE client_id='"+id+"'";
       conn.st.executeUpdate(deleteClient);
       System.out.println("client deleted successfully.............................");
       session.setAttribute("deleted", "<font color=\"red\">Client Deleted Successfully</font>");
       
       if(conn.rs!=null){conn.rs.close();}
if(conn.st!=null){conn.st.close();}
if(conn.rs1!=null){conn.rs1.close();}
if(conn.st1!=null){conn.st1.close();}
if(conn.rs2!=null){conn.rs2.close();}
if(conn.st2!=null){conn.st2.close();}
if(conn.st3!=null){conn.st3.close();}
if(conn.pst!=null){conn.pst.close();}
if(conn.pst!=null){conn.pst.close();}
if(conn.pst1!=null){conn.pst1.close();}
if(conn.pst1!=null){conn.pst1.close();}
if(conn.conn!=null){conn.conn.close();}

       response.sendRedirect("loadClients");
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
        Logger.getLogger(DeleteClients.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(DeleteClients.class.getName()).log(Level.SEVERE, null, ex);
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

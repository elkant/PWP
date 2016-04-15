/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edits;

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
public class saveEditedClient extends HttpServlet {
HttpSession session;
String status;
String id,value,columnName,columnId,columnPosition,rowId;
int found,checker;
String districtid,partnerid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
//        try {
        checker=0;
                 status="";found=0;districtid="";partnerid="";
           dbConn conn = new dbConn();
              id = request.getParameter("id");// row passed from the ajax
       value = request.getParameter("value");// values passed from the ajax
       columnName = request.getParameter("columnName");// values passed from the ajax
       columnId = request.getParameter("columnId");// values passed from the ajax
       columnPosition = request.getParameter("columnPosition");// values passed from the ajax
       rowId = request.getParameter("rowId"); // values passed from the ajax 
       System.out.println("id=="+id+"val=="+value+"colname=="+columnName+"colid=="+columnId+"colpos=="+columnPosition+"rowid=="+rowId);
//     value=value.toUpperCase();
       response.getWriter().print(value);  
       if(columnName.equals("FIRST NAME <font color=\"red\">*</font>")&&value.trim().length()>=3){
        
//         UPDATE FIRST NAME========================================
         String updatefname="UPDATE clients SET fname=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updatefname);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
          checker++;
     }
     
    else if(columnName.equals("MIDDLE NAME <font color=\"red\">*</font>")){
        value=value.toUpperCase();
//         UPDATE MIDDLE NAME========================================
         String updatemname="UPDATE clients SET mname=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updatemname);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
          checker++;
     }
     
     else if(columnName.equals("LAST NAME <font color=\"red\">*</font>")&&value.trim().length()>=3){
         value=value.toUpperCase();
//         UPDATE LAST NAME========================================
         String updatelname="UPDATE clients SET lname=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updatelname);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
          checker++;
     }
     else if(columnName.equals("AGE <font color=\"red\">*</font>")){
//         UPDATE AGE========================================
         if (value.matches("[0-9]+")){//CHECK IF THE AGE ENTERED IS A NUMBER BEFORE SAVING=========
         String updateage="UPDATE clients SET age=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updateage);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
          checker++;
     } 
     
         
     }
     else if(columnName.equals("GENDER <font color=\"red\">*</font>")){
//         UPDATE GENDER========================================
         String updategender="UPDATE clients SET gender=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updategender);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
          checker++;
     }
     
 else if(columnName.equals("GROUP NAME <font color=\"red\">*</font>")){
//         UPDATE GROUP========================================
     
     System.out.println("called to update group details here=====================");
     if(!value.equals("")){
         value=value.toUpperCase();
         String updategender="UPDATE clients SET group_id=?,groupings=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updategender);
         conn.pst.setString(1, value);
         conn.pst.setString(2, value);
         conn.pst.setString(3, id);
         
         conn.pst.executeUpdate();
         
         String getOtherDetails="SELECT district_id,partner_id FROM groups WHERE group_id='"+value+"'";
         conn.rs=conn.st.executeQuery(getOtherDetails);
         if(conn.rs.next()==true){
           districtid=conn.rs.getString(1);
           partnerid=conn.rs.getString(2);  
         }
          checker++;
         String updateClient="UPDATE clients SET district_id='"+districtid+"', partner_id='"+partnerid+"' WHERE client_id='"+id+"'";
         conn.st.executeUpdate(updateClient);
     }
     } 
  else if(columnName.equals("PROVIDER NAME <font color=\"red\">*</font>")){
//         UPDATE GROUP========================================
     
     System.out.println("called to update provider details here=====================");
     if(!value.equals("")){
         value=value.toUpperCase();
         String updategender="UPDATE clients SET provider_id=? WHERE client_id=?";
         conn.pst=conn.conn.prepareStatement(updategender);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
      String getOtherDetails="SELECT district_id,partner_id FROM service_provider WHERE provider_id='"+value+"'";
         conn.rs=conn.st.executeQuery(getOtherDetails);
         if(conn.rs.next()==true){
           districtid=conn.rs.getString(1);
           partnerid=conn.rs.getString(2);  
         }
         checker++;
         String updateClient="UPDATE clients SET district_id='"+districtid+"', partner_id='"+partnerid+"' WHERE client_id='"+id+"'";
         conn.st.executeUpdate(updateClient);
     }
     }
 
 else{
 System.out.println("data not updated missing column==========================================");
 } 
  
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

       System.out.println("checker : "+checker);
//       if(checker>0){
  
//       }
//       else{
//           
//       }
     // out.println(status);
//        } finally {
//            out.close();
//        }
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
        Logger.getLogger(saveEditedClient.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveEditedClient.class.getName()).log(Level.SEVERE, null, ex);
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

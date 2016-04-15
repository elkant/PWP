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
public class saveEditedGroup extends HttpServlet {
HttpSession session;
String status;
String id,value,columnName,columnId,columnPosition,rowId;
int found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
           status="";found=0;
           dbConn conn = new dbConn();
              id = request.getParameter("id");// row passed from the ajax
       value = request.getParameter("value").toUpperCase();// values passed from the ajax
       columnName = request.getParameter("columnName");// values passed from the ajax
       columnId = request.getParameter("columnId");// values passed from the ajax
       columnPosition = request.getParameter("columnPosition");// values passed from the ajax
       rowId = request.getParameter("rowId"); // values passed from the ajax 
       System.out.println("id=="+id+"val=="+value+"colname=="+columnName+"colid=="+columnId+"colpos=="+columnPosition+"rowid=="+rowId);
     response.getWriter().print(value); 
      if(columnName.equals("GROUP NAME <font color=\"red\">*</font>")&&columnName.trim().length()>=3){
//         UPDATE GROUP NAME========================================
         String updategroupname="UPDATE groups SET group_name=? WHERE group_id=?";
         conn.pst=conn.conn.prepareStatement(updategroupname);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
     }
     
    else if(columnName.equals("LOCATION <font color=\"red\">*</font>")){
//         UPDATE LOCATION========================================
         String updatelocation="UPDATE groups SET location=? WHERE group_id=?";
         conn.pst=conn.conn.prepareStatement(updatelocation);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         
         conn.pst.executeUpdate();
     }
     
     else if(columnName.equals("YEAR FORMED")&&columnName.trim().length()>=3){
                  if(value.matches("[0-9]+")){
//         UPDATE year formed========================================
         String updateyearformed="UPDATE groups SET year_formed=? WHERE group_id=?";
         conn.pst=conn.conn.prepareStatement(updateyearformed);
         conn.pst.setString(1, value);
         conn.pst.setString(2, id);
         conn.pst.executeUpdate();
          System.out.println("LAST NAME UPDATED SUCCESSFULLY");
     }
    
     }
     else{
         System.out.println("Nothing updated");
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

//    out.println(status);
//        }
//      
//      finally {
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
        Logger.getLogger(saveEditedGroup.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveEditedGroup.class.getName()).log(Level.SEVERE, null, ex);
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

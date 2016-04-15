/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quality;

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
public class addWards extends HttpServlet {
HttpSession session;
String partnerids;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session=request.getSession();
           dbConn conn = new dbConn();
       
          String getAll="SELECT * FROM wards_dummy";
          conn.rs=conn.st.executeQuery(getAll);
          while(conn.rs.next()){
           String getWards="SELECT id,partner_ids FROM wards WHERE name=? && county_id=?" ;
           conn.pst=conn.conn.prepareStatement(getWards);
           conn.pst.setString(1, conn.rs.getString("name"));
           conn.pst.setString(2, conn.rs.getString("countyid"));
           
           conn.rs1=conn.pst.executeQuery();
           if(conn.rs1.next()==true){
               partnerids="";
            if(conn.rs1.getString(2).equals("0")) {
                partnerids=","+conn.rs.getString("partner_name")+",";
            }
       else{
                if(conn.rs1.getString(2).contains(","+conn.rs.getString("partner_name")+",")){
                    
                }
                else{
        partnerids=conn.rs1.getString(2)+""+conn.rs.getString("partner_name")+",";
          }
            }
        String updator="UPDATE wards SET partner_ids=? WHERE id=?";
        conn.pst1=conn.conn.prepareStatement(updator);
        conn.pst1.setString(1, partnerids);
        conn.pst1.setString(2, conn.rs1.getString(1));
        conn.pst1.executeUpdate();    
           }
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

          System.out.println("completed");
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
        Logger.getLogger(addWards.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(addWards.class.getName()).log(Level.SEVERE, null, ex);
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
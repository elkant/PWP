/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
public class loadWards extends HttpServlet {
HttpSession session;
String wards,partner_id,countyid,partner_ids,client_id,selectedWard;
int counter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn = new dbConn();
           session=request.getSession();
           partner_id=request.getParameter("partner_id");
           countyid=request.getParameter("county_id");
            wards="<option value=\"0\">Choose Ward</option>";counter=0;
          if(request.getParameter("client_id")==null){
            String getWards="SELECT id,name,partner_ids FROM wards WHERE county_id='"+countyid+"'";
           conn.rs=conn.st.executeQuery(getWards);
           while(conn.rs.next()){
           partner_ids=conn.rs.getString(3);
           if(partner_ids.contains(","+partner_id+",")){
            wards+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";   
            counter++;   
           }
           }
          }
          else{
            client_id=request.getParameter("client_id"); 
            selectedWard=request.getParameter("selectedWard"); 
            String getWards="SELECT id,name,partner_ids FROM wards WHERE county_id='"+countyid+"'";
           conn.rs=conn.st.executeQuery(getWards);
           while(conn.rs.next()){
           partner_ids=conn.rs.getString(3);
           if(partner_ids.contains(","+partner_id+",")){
           if(conn.rs.getString(1).equals(selectedWard)){
               wards+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
           }
           else{
             wards+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";        
           }
               counter++;   
           }
           }   
          }
           
           if(counter==0){
               wards="<option value=\"\">No Ward</option>";
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

            out.println(wards);
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
        Logger.getLogger(loadWards.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadWards.class.getName()).log(Level.SEVERE, null, ex);
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

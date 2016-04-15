/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package targets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pwp.IdGenerator;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class saveTargets extends HttpServlet {
HttpSession session;
String partnerid,countyid,year,target,id,timestamp;
int total,found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       
     total=Integer.parseInt(request.getParameter("all_rows")) ;
     
     for (int i=1;i<=total;i++){
         found=0;
         partnerid=countyid=year=target=id=timestamp="";
     partnerid=request.getParameter("partnerid"+i);
     countyid=request.getParameter("countyid"+i);
     year=request.getParameter("year"+i);
     target=request.getParameter("target"+i);
       
     IdGenerator ig = new IdGenerator();
         id=ig.current_id();
         timestamp=ig.toDay();
     
     String checker="SELECT COUNT(id) FROM yearly_targets WHERE year='"+year+"' && partner='"+partnerid+"' && county='"+countyid+"'";
     conn.rs=conn.st.executeQuery(checker);
     if(conn.rs.next()==true){
         found=conn.rs.getInt(1);
     }
      if(found==0) {
        String inserter="INSERT INTO yearly_targets (id,county,partner,year,target,timestamp) "
                + "VALUES('"+id+"','"+countyid+"','"+partnerid+"','"+year+"','"+target+"','"+timestamp+"')"  ;
        conn.st.executeUpdate(inserter);
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

     session.setAttribute("savedtarget","<font color=\"green\">Yearly Targets have been saved successfully.</option>") ; 
    response.sendRedirect("YearlyTargets.jsp");
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
        Logger.getLogger(saveTargets.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveTargets.class.getName()).log(Level.SEVERE, null, ex);
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

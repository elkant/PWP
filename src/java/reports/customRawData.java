/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

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
public class customRawData extends HttpServlet {
String nextPage,groups;
HttpSession session;
String reportType,startDate,endDate,group_ids;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        session=request.getSession();
        dbConn conn = new dbConn();
        
//        INVALIDATE ALL THE SESSION DATA FOR THIS SPECIFIC SESSION WITH SELECTED GROUP IDS=======
//        if(session.getAttribute("customInd")!=null){
//            session.removeAttribute("customInd");
//        }
//        if(session.getAttribute("custstartDate")!=null){
//            session.removeAttribute("custstartDate");
//        }
//        if(session.getAttribute("custendDate")!=null){
//            session.removeAttribute("custendDate");
//        }
       startDate=request.getParameter("startDate");
       endDate=request.getParameter("endDate");
        nextPage=request.getParameter("reportType");
       if(nextPage.equals("IndvRawData")){
        reportType=request.getParameter("groupAll");
       
       System.out.println("start d ====== "+startDate);
       
       group_ids="";
       
       if(reportType.equals("all_groups")){
           group_ids="0,";
           String getGroupIDs="SELECT * FROM groups";
           conn.rs=conn.st.executeQuery(getGroupIDs);
           while(conn.rs.next()==true){
               group_ids+=conn.rs.getString(1)+",";
           }
       }
       if(reportType.equals("selected_groups")){
        String [] ids=request.getParameterValues("group");
       for(String grpid:ids){
           if(!grpid.equals("")&& !grpid.equals(",")){
            group_ids+=grpid+",";   
           }
       } 
       }
       session.setAttribute("customInd", group_ids);
       System.out.println(" group _ids are  : "+group_ids);    
       } 
    session.setAttribute("custendDate", endDate);
    session.setAttribute("custstartDate", startDate);   
      System.out.println("start date ============ "+session.getAttribute("custstartDate"));
      
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

       response.sendRedirect(nextPage);
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
        Logger.getLogger(customRawData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(customRawData.class.getName()).log(Level.SEVERE, null, ex);
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

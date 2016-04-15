/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class save_edited_sessions extends HttpServlet {
HttpSession session;
String sessions_id;
String session_date,messages,method_used,duration,male_cds,female_cds,iec_materials;
String [] mess,meth;
int total_cds=0;
String dates="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        session=request.getSession();
        dbConn conn = new dbConn();
       IdGenerator IG = new IdGenerator(); 
        messages=method_used=",";
        total_cds=0;
        dates="";
        for (int i=1; i<=13;i++){
            method_used=",";
           sessions_id=request.getParameter("sessions"+i);
            session_date=request.getParameter("session_date"+i);
            duration=request.getParameter("duration"+i);
            male_cds=request.getParameter("male_cds"+i);
            female_cds=request.getParameter("female_cds"+i);
            iec_materials=request.getParameter("iec_no"+i);
          
            if(session_date==null || session_date.equals("")){
              session_date=""; 
           }
           if(duration==null || duration.equals("")){
              duration="0"; 
           }
           if(male_cds==null || male_cds.equals("")){
              male_cds="0"; 
           }
           if(female_cds==null || female_cds.equals("")){
              female_cds="0"; 
           }
           if(iec_materials==null || iec_materials.equals("")){
              iec_materials="0"; 
           }
           
            meth=request.getParameterValues("method"+i);
            mess=request.getParameterValues("message"+i);
            
//            System.out.println("sessions_id      "+sessions_id);
           System.out.println("sessions_date   "+session_date);
//            System.out.println("sessions duration  "+duration);
//            System.out.println("male cds   :  "+male_cds);
//            System.out.println("female cds   :  "+female_cds);
//            System.out.println("iec  materials   :  "+iec_materials);
            
           if(meth!=null) {
            for(int j=0;j<meth.length;j++){
             method_used+=meth[j]+",";   
                
            }
           }
          if(meth==null || method_used.equals(",")){
           method_used="";   
           }
          
//          for(int h=1;h<=13;h++){
    if(session.getAttribute("chosen_session_1").toString().contains(","+i+",")){
      total_cds+=Integer.parseInt(male_cds);
      total_cds+=Integer.parseInt(female_cds); 
    }
//}
    if(!session_date.equals("")){
 dates+=session_date+",";
    }
    if(session_date.equals("")){
 dates+="0"+",";
    }
    
session.setAttribute("total_cds", total_cds);

          
         System.out.println("methods used  :  "+method_used);
      String district="",partner="",session_no="",group_id="",groupings="",metho="";  
          if(session.getAttribute("chosen_session_1").toString().contains(","+i+",")){
         String update_sessions="UPDATE sessions SET session_date='"+session_date+"',"
           + " method_id='"+method_used+"', time_taken='"+duration+"', male_condoms='"+male_cds+"', female_condoms='"+female_cds+"', "
           + "no_iec='"+iec_materials+"',timestamp='"+IG.toDay()+"' WHERE sessions_id='"+sessions_id+"'";
           conn.st.executeUpdate(update_sessions);
           
          }
 
            messages=method_used=",";
        }
session.setAttribute("dates", dates);
  System.out.println("dates   :   "+dates);

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
        response.sendRedirect("edit_register2");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(save_edited_sessions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(save_edited_sessions.class.getName()).log(Level.SEVERE, null, ex);
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

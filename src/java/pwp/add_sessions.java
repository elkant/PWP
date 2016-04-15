/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
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
public class add_sessions extends HttpServlet {
HttpSession session;
String session_date,message_given,method_used,duration,male_cds,female_cds,iec_materials;
String [] message_use,method_use;
String group_id="";
String partner_id, district_id,groupings;
String full_date,month_s;
String period,yea,attended_sessions;
double data_exist=0;
int total_cds=0;
String dates="";
String date_key;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        attended_sessions=",";
        data_exist=0;
        total_cds=0;
        dates="";
        Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
full_date=year+"-"+month+"-"+date+"-"+hour+"-"+min;
session_date=duration=male_cds=female_cds=iec_materials="0";
if(group_id!=null){
        group_id=session.getAttribute("sessions_group_id").toString();
        partner_id=session.getAttribute("partner_id").toString();
         district_id=session.getAttribute("district_id").toString();
         yea=""+year;
         groupings=session.getAttribute("groupings").toString();

        for ( int i=1; i<=13; i++){
            message_given=",";
            method_used=",";
            data_exist=0;
//            &&&&&&&&&&&&&&RECEIVE ALL PARAMETERS AND SAVE THEM&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
           session_date=request.getParameter("session_date"+i);
           duration=request.getParameter("time_taken"+i);
           male_cds=request.getParameter("male_cds"+i);
           female_cds=request.getParameter("female_cds"+i);
           iec_materials=request.getParameter("iec_materials"+i);
//           System.out.println("male cds  :   "+male_cds);
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
           
           message_use=request.getParameterValues("messages_given"+i);
           method_use=request.getParameterValues("method_used"+i);
           if(message_use!=null){
           for( int j=0; j<message_use.length;j++){
            message_given+=message_use[j]+"," ;     
           }
           }
            if(method_use!=null){
           for( int k=0; k<method_use.length;k++){
            method_used+=method_use[k]+"," ;     
           }
           }
                    IdGenerator ig = new IdGenerator();
           
//          INSERT THESE VALUES TO THE DATABASE
           if(message_given.equals(",")){
               message_given="";
           }
           if(method_used.equals(",")){
               method_used="";
           }
           if(!method_used.equals("")){
            attended_sessions+=i+",";   
           }
//for(int h=1;h<=13;h++){
    if(session.getAttribute("chosen_session_1").toString().contains(","+i+",")){
      total_cds+=Integer.parseInt(male_cds);
      total_cds+=Integer.parseInt(female_cds); 
     
    }
    if(!session_date.equals("")){
 dates+=session_date+",";
    }
    if(session_date.equals("")){
 dates+="0"+",";
    }
session.setAttribute("dates", dates);
session.setAttribute("total_cds", total_cds);
System.out.println("total cds");
         String get_sess="SELECT sessions_id FROM sessions WHERE group_id='"+group_id+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' && groupings='"+groupings+"' && session_id='"+i+"' && year='"+yea+"'";
         conn.rs=conn.st.executeQuery(get_sess);
         if(conn.rs.next()==true){
            data_exist=conn.rs.getDouble(1); 
         }
           if(data_exist==0){
           String insert_sessions="INSERT INTO sessions SET sessions_id='"+ig.current_id()+"', group_id='"+group_id+"',groupings='"+groupings+"', session_id='"+i+"', session_date='"+session_date+"',"
           + " 	message_id='"+i+"', method_id='"+method_used+"', time_taken='"+duration+"', male_condoms='"+male_cds+"', female_condoms='"+female_cds+"', "
           + "no_iec='"+iec_materials+"',partner_id='"+partner_id+"' , district_id='"+district_id+"',year='"+yea+"',timestamp='"+ig.toDay()+"'";
           conn.st.executeUpdate(insert_sessions);
           }
           else{
           String update_sessions="UPDATE sessions SET group_id='"+group_id+"',groupings='"+groupings+"', session_id='"+i+"', session_date='"+session_date+"',"
           + " 	message_id='"+i+"', method_id='"+method_used+"', time_taken='"+duration+"', male_condoms='"+male_cds+"', female_condoms='"+female_cds+"', "
           + "no_iec='"+iec_materials+"',partner_id='"+partner_id+"' , district_id='"+district_id+"',year='"+yea+"',timestamp='"+ig.toDay()+"' WHERE sessions_id='"+data_exist+"'";
           conn.st.executeUpdate(update_sessions);
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

}
response.sendRedirect("load_attendance");
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
            Logger.getLogger(add_sessions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_sessions.class.getName()).log(Level.SEVERE, null, ex);
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

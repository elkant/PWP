/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

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

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_a_session extends HttpServlet {
HttpSession session;
String session_rows,group_id="";
String all_dates,message_given,method_used,time_taken,male_cds,female_cds,iec_materials,all_checker;
String sess_no="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn= new dbConn();
        all_dates=message_given=method_used=time_taken=male_cds=female_cds=iec_materials=all_checker=""; 
       System.out.println("here");
        if(session.getAttribute("group_id")!=null){
           group_id= session.getAttribute("group_id").toString();
        }
       if(session.getAttribute("chosen_session_1")!=null) {
        sess_no=session.getAttribute("chosen_session_1").toString();
       }
      System.out.println("grp id iss    :  lll  :  "+group_id);
        if(!group_id.equals("")){
        session.setAttribute("sessions_group_id", group_id);
        if(!group_id.equals("0")){
        session.setAttribute("groupings", "0");
        }
        System.out.println("group_id   :   "+group_id);
        if(group_id.equals("0")){
         session.setAttribute("sessions_group_name", "INDIVIDUALS");   
        }
        else{
        String group_name_selector="select * from groups where group_id='"+group_id+"'";
        conn.rs=conn.st.executeQuery(group_name_selector);
        conn.rs.next();
        
        session.setAttribute("sessions_group_name", conn.rs.getString(2));
        }
     String   message="";
        String select_messages="SELECT * FROM message_codes";
        conn.rs=conn.st.executeQuery(select_messages);
        while(conn.rs.next()){
         message+="<option value=\""+conn.rs.getString(1) +"\" title=\""+conn.rs.getString(2) +"\">"+conn.rs.getString(1) +". "+conn.rs.getString(2)+"";   
        }
        
     String   method="";
        String select_methods="SELECT * FROM methods_used";
        conn.rs=conn.st.executeQuery(select_methods);
        while(conn.rs.next()){
         method+="<option value=\""+conn.rs.getString(1) +"\" title=\""+conn.rs.getString(2) +"\" >"+conn.rs.getString(1) +". "+conn.rs.getString(2)+"";   
        }
        
       
        session_rows="";
//         for(int i=1;i<=13;i++){
////        all_checker+="<td><input type=\"checkbox\" value=\"1\" onClick=\"return checker_type(this);\" name=\"checker_"+i+"\" id=\"checker_"+i+"\" style=\"width: 80px\" readonly></td>";
//         }
        for(int i=1;i<=13;i++){
            if(sess_no.contains(","+i+",")){
            all_dates+="<td><input type=\"text\" value=\"\" name=\"session_date"+i+"\" id=\"datepicker"+i+"\" style=\"width: 80px\" required readonly=\"true\" ></td>";              
            }
            else{
          all_dates+="<td><input type=\"text\" value=\"\" name=\"session_date"+i+"\" id=\"datepicker"+i+"\" style=\"width: 80px\" disabled=\"true\" readonly=\"true\" ></td>";  
        }
        }
//        for(int i=1;i<=13;i++){
//            if(i==sess_no){
//          message_given+="<td><select name=\"messages_given"+i+"\" id=\"messages_given"+i+"\" class=\"\" style=\"width: 80px\" required multiple>"
//                  +message+
//                  "</select></td>"; }
//            else{
//            message_given+="<td><select name=\"messages_given"+i+"\" id=\"messages_given"+i+"\" class=\"\" style=\"width: 80px\" disabled=\"true\" multiple>"
//                  +message+
//                  "</select></td>";    
//            }
//        }
                for(int i=1;i<=13;i++){
                    if(sess_no.contains(","+i+",")){
         method_used+="<td><select name=\"method_used"+i+"\" id=\"method_used"+i+"\" class=\"\" style=\"width: 80px\" required  multiple>"
                  +method+
                  "</select></td>"; }
                    else{
                     method_used+="<td><select name=\"method_used"+i+"\" id=\"method_used"+i+"\" class=\"\" style=\"width: 80px\" disabled=\"true\" multiple>"
                  +method+
                  "</select></td>";   
                    }
        }
                for(int i=1;i<=13;i++){
                   if(sess_no.contains(","+i+",")){
          time_taken+="<td><input type=\"\" name=\"time_taken"+i+"\" id=\"time_taken"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"3\" required></td>";  
        }
                    else{
  time_taken+="<td><input type=\"\" name=\"time_taken"+i+"\" id=\"time_taken"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"3\" disabled=\"true\"></td>";                                
                    }            
                }
                for(int i=1;i<=13;i++){
                    if(sess_no.contains(","+i+",")){
          male_cds+="<td><input type=\"\" name=\"male_cds"+i+"\" id=\"male_cds"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"5\" ></td>";  
        }
                    else{
                       male_cds+="<td><input type=\"\" name=\"male_cds"+i+"\" id=\"male_cds"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"5\" disabled ></td>";    
                    }
                }
                for(int i=1;i<=13;i++){
                    if(sess_no.contains(","+i+",")){
          female_cds+="<td><input type=\"\" name=\"female_cds"+i+"\" id=\"female_cds"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"5\" ></td>";  
        }
           else{
               female_cds+="<td><input type=\"\" name=\"female_cds"+i+"\" id=\"female_cds"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"5\" disabled></td>";  
                  
                    }         
                }
                for(int i=1;i<=13;i++){
                    if(sess_no.contains(","+i+",")){
          iec_materials+="<td><input type=\"\" name=\"iec_materials"+i+"\" id=\"iec_materials"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"5\" ></td>";  
        }
                    else{
                    iec_materials+="<td><input type=\"\" name=\"iec_materials"+i+"\" id=\"iec_materials"+i+"\" class=\"\" style=\"width: 80px\" onkeypress=\"return numbers(event)\" maxlength=\"5\" disabled></td>";       
                    }
                }
//        session_rows+="<tr><td>Check The Sessions To Mark.</td>"+all_checker+"</tr>\n";
        session_rows+="<tr><td>Session Date (MM/DD/YYYY)</td>"+all_dates+"</tr>\n";
//        session_rows+="<tr><td>Message Given</td>"+message_given+"</tr>\n";
        session_rows+="<tr><td>Methods Used</td>"+method_used+"</tr>\n";
        session_rows+="<tr><td>Time Taken In Mins</td>"+time_taken+"</tr>";
        session_rows+="<tr><td>No. Male Condoms Distributed</td>"+male_cds+"</tr>";
        session_rows+="<tr><td>No. Female Condoms Distributed</td>"+female_cds+"</tr>";
        session_rows+="<tr><td>No. Of IEC Materials Distributed</td>"+iec_materials+"</tr>";
        
System.out.println(session_rows);
session.setAttribute("session_details", session_rows);
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

response.sendRedirect("attendance_level_decider");
//%%%%%%%%%%%%%%%%%%%%%%
    }

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
            Logger.getLogger(load_a_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_a_session.class.getName()).log(Level.SEVERE, null, ex);
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

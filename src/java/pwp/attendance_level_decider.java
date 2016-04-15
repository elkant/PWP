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
public class attendance_level_decider extends HttpServlet {
HttpSession session;
String group_id,next_page;
int counter1,counter2,counter3,counter4,counter5;
String client_id;
    String partner_id, district_id;
    String year,period,month,chosen_session="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        session=request.getSession();
        dbConn conn = new dbConn();
       
        chosen_session="";
     chosen_session=session.getAttribute("chosen_session").toString();
     
         System.out.println("chosen session   :  "+session.getAttribute("chosen_session").toString());
          if(session.getAttribute("src").toString().equals("add_a_clients2.jsp")){
    System.out.println("the group id is atten  ;  "+session.getAttribute("sessions_group_id"));}
          
        counter1=counter2=counter3=counter4=counter5=0;
        System.out.println(session.getAttribute("sessions_group_id"));
        if(session.getAttribute("sessions_group_id")!=null){
            
      group_id=session.getAttribute("sessions_group_id").toString();
      partner_id=session.getAttribute("partner_id").toString();
         district_id=session.getAttribute("district_id").toString();
         String dist_name="SELECT district_name FROM district WHERE district_id='"+district_id+"'";
         conn.rs=conn.st.executeQuery(dist_name);
         if(conn.rs.next()==true){
             session.setAttribute("district_name", conn.rs.getString(1));
         }
         String part_name="SELECT partner_name FROM partner WHERE partner_id='"+partner_id+"'";
         conn.rs=conn.st.executeQuery(part_name);
         if(conn.rs.next()==true){
             session.setAttribute("partner_name", conn.rs.getString(1));
         }
         if(!group_id.equals("0")){
         String grp_name="SELECT group_name FROM groups WHERE group_id='"+group_id+"'";
         conn.rs=conn.st.executeQuery(grp_name);
         if(conn.rs.next()==true){
             session.setAttribute("group_name_s", conn.rs.getString(1));
         }
         }
         if(group_id.equals("0")){
           session.setAttribute("group_name_s", "INDIVIDUALS");  
         }
         if(!group_id.equals("0")){
      String check_if_member_exist="SELECT client_id,client_id FROM personal_information WHERE group_id='"+group_id+"'&& partner_id='"+partner_id+"' && district_id='"+district_id+"' ORDER BY lessons_attended DESC LIMIT 1";
      conn.rs=conn.st.executeQuery(check_if_member_exist);}
         else if(group_id.equals("0")){
         String check_if_member_exist="SELECT COUNT(client_id),client_id FROM personal_information WHERE groupings='"+session.getAttribute("groupings").toString()+"'&& partner_id='"+partner_id+"' && district_id='"+district_id+"' GROUP BY group_id";
      conn.rs=conn.st.executeQuery(check_if_member_exist);    
         }
      if(conn.rs.next()==true){
       counter1=1;
       client_id=conn.rs.getString(2);
       System.out.println("The client id is :  "+client_id);
      }
      if(counter1==0){
          next_page="add_a_client.jsp";
          session.setAttribute("already_clients","<font color=\"red\"> No Clients Exist Within the given Specifications .Select Details Appropriately to Add New Clients / Mark/Edit Attendance </font>");
      System.out.println("Here Please............................................ add clients");
      }
      else{
//          if(group_id.equals("0")){
//              
//          }
          if(!group_id.equals("0")){
      String Check_if_session_added="SELECT count(sessions_id) FROM sessions WHERE group_id='"+group_id+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' GROUP BY group_id";
      conn.rs=conn.st.executeQuery(Check_if_session_added);
          }
   
          if(group_id.equals("0")){
      String Check_if_session_added="SELECT count(sessions_id) FROM sessions WHERE groupings='"+session.getAttribute("groupings").toString()+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' GROUP BY group_id";
      conn.rs=conn.st.executeQuery(Check_if_session_added);
          }
      if(conn.rs.next()==true){
       counter2=conn.rs.getInt(1);   
      }
      if(counter2==0){
          System.out.println("Here Please............................................mark attendance1");
          next_page="mark_attendance2.jsp";
          session.setAttribute("decider","<font color=\"blue\"> Clients Exist. Enter all the sessions details as required. </font>");
      }
      
      else{
            String Check_if_register_marked="SELECT count(reg_id) FROM register WHERE client_id='"+client_id+"' GROUP BY client_id";
      conn.rs=conn.st.executeQuery(Check_if_register_marked);
      if(conn.rs.next()==true){
       counter3=conn.rs.getInt(1);   
      }
      if(counter3==0){
          next_page="load_attendance";
          System.out.println("Here Please............................................mark attendance2");
          session.setAttribute("register3","<font color=\"blue\"> Sessions For these Clients has been added. Mark the Information Counselling Provided</font>");
      }
       
      else{
       String Check_if_services_provided_added="SELECT count(services_id) FROM services_provided WHERE client_id='"+client_id+"'";
      conn.rs=conn.st.executeQuery(Check_if_services_provided_added);
      if(conn.rs.next()==true){
       counter4=conn.rs.getInt(1);   
      }
      System.out.println("the counter4 is  :   "+counter4);
      if(counter4==0){
          next_page="load_services_provided";
          System.out.println("Here Please............................................mark attendance4");
          session.setAttribute("decider","<font color=\"blue\"> Information Counselling Has already Been Added  . Mark Services Provided Appropriately. </font>");
      }   
      if(counter4>0){
          session.setAttribute("complete", "yes");
         next_page="edit_register1";
         System.out.println("Here Please............................................edit register");
         session.setAttribute("decider","<font color=\"blue\"> Attendance for this group/ individual(s) has already been marked. Edit the attendance</font>");
      } 
      
      }
      
      }
      }   
    }
        else{
          next_page= "mark_attendance.jsp" ;
          session.setAttribute("attendance_level", "<font color=\"red\">Failed to load the attendance. Log out and then try again.</font>");
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
        System.out.println("sessioner   :  "+session.getAttribute("decider"));
        response.sendRedirect(next_page);
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
            Logger.getLogger(attendance_level_decider.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(attendance_level_decider.class.getName()).log(Level.SEVERE, null, ex);
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

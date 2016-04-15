/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
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
public class save_edited_client extends HttpServlet {
HttpSession session;
String fname,mname,lname,age,sex;
String group_id,partner_id,district_id;
String client_id;
int checker,found;
String full_date,full_name;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
     dbConn conn = new dbConn();
     IdGenerator IG = new IdGenerator();
      Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
full_date=year+"-"+month+"-"+date+"-"+hour+"-"+min;
     
  full_name=""  ; 
     
     checker=found=0;
     if(session.getAttribute("client").toString()!=null){
       client_id=session.getAttribute("client").toString();
       fname=request.getParameter("fname");
       mname=request.getParameter("mname");
       lname=request.getParameter("lname");
       age=request.getParameter("age");
       fname=fname.replaceAll("\\s+","");
      lname=lname.replaceAll("\\s+","");
      mname=mname.replaceAll("\\s+","");
      age=age.replaceAll("\\s+","");
       if("".equals(mname) || mname==null){
           mname=lname;
       }
            fname=fname.toUpperCase();
            mname=mname.toUpperCase();
            lname=lname.toUpperCase(); 
       full_name=fname+" "+mname+" "+lname;
       sex=request.getParameter("gender");
       
       group_id=request.getParameter("groups");
       district_id=request.getParameter("district");
       partner_id=request.getParameter("partner");
       
       String pick_dets="select * from clients where client_id='"+client_id+"'";
       conn.rs=conn.st.executeQuery(pick_dets);
       conn.rs.next();
       String grp=conn.rs.getString("group_id");
       String groupings=conn.rs.getString("groupings");
       if(!grp.equals("0")){
           groupings="0";
       }
       System.out.println("group id is  :  "+grp);
       System.out.println("groupings id is  :  "+groupings);
       
       String check_if_attendance_marked="SELECT COUNT(sessions_id) FROM sessions WHERE group_id='"+grp+"' && groupings='"+groupings+"' GROUP BY session_id";
       conn.rs=conn.st.executeQuery(check_if_attendance_marked);
       if(conn.rs.next()==true){
         checker=conn.rs.getInt(1);  
       }
      String check_if_details_exist=" select count(client_id) FROM clients WHERE fname='"+fname+"' && mname='"+mname+"' && lname='"+lname+"' &&  age='"+age+"' && "
                   + " group_id='"+group_id+"' && groupings='"+group_id+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' &&"
                   + "client_id!='"+client_id+"' ";
      conn.rs=conn.st.executeQuery(check_if_details_exist);
      if(conn.rs.next()==true){
       found=conn.rs.getInt(1);  
      }
//     %%%%%%%%%%%%%%%%%%%%%%%%IF NOT MARKED UPDATE ALL DETAILS&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
       if(checker==0 && found==0){
         if(!"0".equals(group_id)){
           String update_all="UPDATE clients SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"', age='"+age+"',timestamp='"+IG.toDay()+"',"
                   + "gender='"+sex+"', group_id='"+group_id+"',groupings='"+group_id+"',partner_id='"+partner_id+"',district_id='"+district_id+"'"
                   + "WHERE client_id='"+client_id+"' ";
           conn.st.executeUpdate(update_all);
           System.out.println("Executed for a group  :  ");
         }
         
         if("0".equals(group_id)){
           String update_all="UPDATE clients SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"', age='"+age+"',"
                   + "gender='"+sex+"', group_id='0',partner_id='"+partner_id+"',timestamp='"+IG.toDay()+"',district_id='"+district_id+"'"
                   + "WHERE client_id='"+client_id+"' ";
           conn.st.executeUpdate(update_all);
           System.out.println("Executed for an individual  :  ");
         }
        session.setAttribute("update_client", "<font color=\"green\">"+full_name+" Details were updated successfully.</font>"); 
       }
//      ^^^^^^^^^^IF ATTENDANCE HAS BEEN MARKED ,ALTER ONLY NAMES AND BASIC DETAILS^^^^^^^^^^^^^^^^^  
       if(checker>0){
         String update_all="UPDATE clients SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"',timestamp='"+IG.toDay()+"', age='"+age+"',"
                   + "gender='"+sex+"'WHERE client_id='"+client_id+"' ";
           conn.st.executeUpdate(update_all); 
           System.out.println("Updated only basic details  :  ");
      session.setAttribute("update_client", "<font color=\"blue\">"+full_name+" Details were partially updated. first name, middle name, last name, age and sex were only updated.</font>");
       }
       if(found>0){
           session.setAttribute("update_client", "<font color=\"red\">"+full_name+" Details were not updated. failed because another client also exist within the group with the same details.</font>");
       }
       System.out.println(" found  :  "+checker);
     }
     else{
       session.setAttribute("update_client", "<font color=\"red\">"+full_name+" Details were not updated. Log out and then log in a fresh.</font>");   
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

     response.sendRedirect("edit_client.jsp");
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
            Logger.getLogger(save_edited_client.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_edited_client.class.getName()).log(Level.SEVERE, null, ex);
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

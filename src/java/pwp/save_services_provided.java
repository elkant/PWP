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
public class save_services_provided extends HttpServlet {
HttpSession session;
int total_clients;
String client_id,contraceptive,service_point,cds,tb,sti,partner_testing,children_testing,disclosed_status;
String remarks, prepared_by,reviewed_by,submission_date,submission_month,submission_year;
String month,date,year,session_id;
int found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        
        
        if(session.getAttribute("total_clients")!=null){ 
           total_clients=Integer.parseInt(session.getAttribute("total_clients").toString());
System.out.println("total clients  :  "+total_clients);

month=year="";
remarks=request.getParameter("remarks");
prepared_by=request.getParameter("prepared_by");
reviewed_by=request.getParameter("reviewer");
submission_date=request.getParameter("submission");
String [] div_sum=submission_date.split("/");
session_id=session.getAttribute("chosen_session").toString();
month=div_sum[0];
year=div_sum[2];

 session.setAttribute("complete", "yes");
for (int i=1; i<=total_clients;i++){
  System.out.println("here :  "+i);
    client_id=request.getParameter("client_id"+i);
     contraceptive=request.getParameter("contraceptive"+i);
     service_point=request.getParameter("service_point"+i);
     cds=request.getParameter("cds"+i);
     tb=request.getParameter("tb"+i);
     sti=request.getParameter("sti"+i);
     partner_testing=request.getParameter("partner_testing"+i);
     children_testing=request.getParameter("children_testing"+i);
     disclosed_status=request.getParameter("disclosed_status"+i);
    System.out.println("contra  "+contraceptive);
     if(contraceptive==null || contraceptive.equals("")){
     contraceptive="NO";    
     }
     if(service_point==null || service_point.equals("")){
     service_point="NO";    
     }
     if(tb==null || tb.equals("")){
     tb="NO";    
     }
     if(sti==null || sti.equals("")){
     sti="NO";    
     }
     if(partner_testing==null || partner_testing.equals("")){
     partner_testing="NO";    
     }
     if(children_testing==null || children_testing.equals("")){
     children_testing="NO";    
     }
     if(disclosed_status==null || disclosed_status.equals("")){
     disclosed_status="NO";    
     }
     if(cds==null || cds.equals("")){
     cds="0";    
     }
     String sess1=session.getAttribute("chosen_session_1").toString();
//     String sess []=sess1.split(",");
     System.out.println("sessions  :  "+sess1);
     IdGenerator ig = new IdGenerator(); 
String check_existence="SELECT * FROM services_provided WHERE client_id='"+client_id+"'";
conn.rs=conn.st.executeQuery(check_existence);
if(conn.rs.next()==true){
   found=0; 
    String existing_sessions=conn.rs.getString("session_no");
    String ex_sess []=existing_sessions.split(",");
for (int j=0;j<ex_sess.length;j++)
{
    if(ex_sess[j]!=null && !ex_sess[j].equals("") && !ex_sess[j].equals(",")){
        if(sess1.contains(","+ex_sess[j]+",")){
 found++;   
}
}
}
System.out.println("existing session   :    "+existing_sessions);
}
System.out.println("found   :    "+found);
     System.out.println(" messages   "+session.getAttribute("chosen_session_1").toString() );
     if(found==0){
  String insert_services="INSERT INTO services_provided (services_id,client_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,submission_year,timestamp,session_no) VALUES"
          + "('"+ig.current_id()+"','"+client_id+"','"+contraceptive+"','"+service_point+"','"+cds+"','"+tb+"','"+sti+"','"+partner_testing+"','"+children_testing+"','"+disclosed_status+"','"+remarks+"','"+prepared_by+"','"+reviewed_by+"','"+submission_date+"','"+month+"','"+year+"','"+ig.toDay()+"','"+session.getAttribute("chosen_session_1").toString() +"')";   
  conn.st.executeUpdate(insert_services);

     }
}    
String deleter="TRUNCATE TABLE tracker" ;
System.out.println("deleter     :  "+deleter);
    int chek=conn.st2.executeUpdate(deleter);
    
    String deleter2="TRUNCATE TABLE tracker2" ;
       conn.st.executeUpdate(deleter2);
       
    System.out.println("status : "+chek);
if(conn.st!=null){
            conn.st.close();
            }
  session.setAttribute("edit_success", "<font color=\"green\">Services provided were added Successfully.</font>");
     session.setAttribute("complete", "yes");  
        
       
        
        }
        else{
            session.setAttribute("edit_success", "<font color=\"red\">Services provided were not added. Try Again.</font>");
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

        response.sendRedirect("add_a_group.jsp");
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
            Logger.getLogger(save_services_provided.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_services_provided.class.getName()).log(Level.SEVERE, null, ex);
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

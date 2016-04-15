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
public class save_edited_services_provided extends HttpServlet {
HttpSession session;
String contraceptive,service_point,cds,tb,sti,partner_testing,children_testing,disclosed_status;
String service_id,clients;
int all_clients;
String remarks, prepared_by,reviewed_by,submission_date,submission_month,submission_year;
String month,date,year,otherServiceId,client_id,disabler;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        session=request.getSession();
        dbConn conn= new dbConn();
        IdGenerator IG = new IdGenerator();
        if(session.getAttribute("client_ids")!=null && session.getAttribute("clients")!=null){
            clients=session.getAttribute("clients").toString();
            all_clients=Integer.parseInt(clients);
            
            month=year="";
remarks=request.getParameter("remarks");
prepared_by=request.getParameter("prepared_by");
reviewed_by=request.getParameter("reviewer");
submission_date=request.getParameter("submission");
String [] div_sum=submission_date.split("/");

month=div_sum[0];
year=div_sum[2];

            for (int i=1; i<=all_clients;i++){
                IdGenerator IGNew = new IdGenerator();
                otherServiceId=IGNew.current_id();
           service_id=request.getParameter("service_id"+i);
           contraceptive=request.getParameter("contraceptive"+i); 
           service_point=request.getParameter("service_point"+i);
           cds=request.getParameter("cds"+i);
           tb=request.getParameter("tb"+i);
           sti=request.getParameter("sti"+i);
           partner_testing=request.getParameter("partner_testing"+i);
           children_testing=request.getParameter("children_testing"+i);
           disclosed_status=request.getParameter("disclosed_status"+i);
           client_id=request.getParameter("clientID"+i);
           disabler=request.getParameter("disabler_"+i);
            if(contraceptive==null){
     contraceptive="NO";    
     }
     if(service_point==null){
     service_point="NO";    
     }
     if(tb==null){
     tb="NO";    
     }
     if(sti==null){
     sti="NO";    
     }
     if(partner_testing==null){
     partner_testing="NO";    
     }
     if(children_testing==null){
     children_testing="NO";    
     }
     if(disclosed_status==null){
     disclosed_status="NO";    
     }
     if(cds==null){
     cds="0";    
     }
     else{
         if(cds.equals("")){cds="0";}
     }
//    CHECK ELEMENTS TO UPDATE
     if(!disabler.equals("disabled")){
           if(!service_id.equals("")){
           String service_provided_updator="UPDATE services_provided SET contraceptive_method='"+contraceptive+"',rsp='"+service_point+"', cds_given='"+cds+"',"
           + "screened_stis='"+sti+"',screened_tb='"+tb+"',tested_partner='"+partner_testing+"',tested_children='"+children_testing+"',disclosed_status='"+disclosed_status+"',"
           + "remarks='"+remarks+"',prepared_by='"+prepared_by+"',reviewed_by='"+reviewed_by+"',submission_date='"+submission_date+"',submission_month='"+month+"',submission_year='"+year+"',timestamp='"+IG.toDay()+"' WHERE services_id='"+service_id+"'";
           conn.st.executeUpdate(service_provided_updator);
           }
           
           else{
            String insert_services="INSERT INTO services_provided (services_id,client_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,submission_year,timestamp,session_no) VALUES"
          + "('"+otherServiceId+"','"+client_id+"','"+contraceptive+"','"+service_point+"','"+cds+"','"+tb+"','"+sti+"','"+partner_testing+"','"+children_testing+"','"+disclosed_status+"','"+remarks+"','"+prepared_by+"','"+reviewed_by+"','"+submission_date+"','"+month+"','"+year+"','"+IGNew.toDay()+"','"+session.getAttribute("sessIDS").toString() +"')";   
  conn.st.executeUpdate(insert_services);
    
           }
           
     }
            }   
String deleter="DELETE FROM tracker2" ;
       conn.st.executeUpdate(deleter);

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

        session.setAttribute("edit_success", "<font color=\"green\">All the details have been edited And Saved Successfully.</font>");
    }
    else{
    session.setAttribute("edit_success", "<font color=\"red\">Failed to edit Details. Try Again.</font>");
}
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
            Logger.getLogger(save_edited_services_provided.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_edited_services_provided.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class edit_register3 extends HttpServlet {
HttpSession session;
    ArrayList edit_reg3 = new ArrayList();
    ArrayList edit_reg3_general = new ArrayList();
String client_info="";
String [] client_ids;
String service_id, full_names,age,sex;
String contraceptive,service_point,cds,tb,sti,partner_testing,children_testing,disclosed_status;
int positioner;
 private String contraceptive_image,service_point_image,cds_image,tb_image,sti_image,partner_testing_image,children_testing_image,disclosed_status_image;
  String remarks, prepared_by,reviewed_by,submission_date,month,nextpage,registerid,sessIDS,clientId;
  int alreadyMarked;
String activeClientId;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
 
        session=request.getSession();
        dbConn conn = new dbConn();
       edit_reg3.clear();
        edit_reg3_general.clear();
        registerid="";
       
        
        positioner=0;alreadyMarked=0;
        if(session.getAttribute("activeClients")!=null){
         System.out.println("chosen session   :   "+session.getAttribute("chosen_session_1"));
            String chose_sess=session.getAttribute("chosen_session_1").toString();
          edit_register3_bean ebean3_general=new edit_register3_bean();  
           String yr=request.getParameter("year");
         String period=request.getParameter("period"); 
//          month=session.getAttribute("month").toString(); 
         
         
         
       client_info=session.getAttribute("client_ids").toString(); 
       client_ids=client_info.split(",");

       System.out.println("already here : "+client_ids.length);
       for(int i=0; i<client_ids.length; i++){
        activeClientId=client_ids[i];
           if(!activeClientId.equals("")){
               positioner++;
              
               System.out.println("active clients : "+activeClientId);
             edit_register3_bean ebean3=new edit_register3_bean();
               
             String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE client_id='"+activeClientId+"' ORDER BY fname,mname,lname";
             conn.rs1=conn.st1.executeQuery(client_selector);
            if(conn.rs1.next())
            {
             String fname=conn.rs1.getString(2);
            String mname=conn.rs1.getString(3);
             String lname=conn.rs1.getString(4);
               age=conn.rs1.getString(5);
                sex=conn.rs1.getString(6);
               if(mname.equals(lname)){
                   mname="";
               }
               
             full_names=fname+" "+mname+" "+lname;
            }
            int    found=0;
              String checrer="SELECT * FROM services_provided WHERE client_id='"+activeClientId+"'";
             conn.rs=conn.st.executeQuery(checrer);
            while(conn.rs.next()){
                
                String sess_no=conn.rs.getString("session_no");
                String sess_l[]=chose_sess.split(",");
                for (int m=0;m<sess_l.length;m++){
                   if(sess_l[m]!=null && !sess_l[m].equals(",") && !sess_l[m].equals("")) {
                      if(sess_no.contains(sess_l[m])) {
                      found++; alreadyMarked++;
                      registerid=conn.rs.getString("services_id");
//                      break;
                      }
                  }
                    
                }
                
            }
            System.out.println("Already marked "+alreadyMarked+"   found :   "+found+"service_id : "+registerid);
            if(found>0 && !registerid.equals("")){
             String services_provided_selector="SELECT * FROM services_provided WHERE services_id='"+registerid+"'";
             conn.rs=conn.st.executeQuery(services_provided_selector);
            if(conn.rs.next()==true){
              session.setAttribute("sessIDS", conn.rs.getString("session_no"));
    if(session.getAttribute("missed_clients").toString().contains(","+activeClientId+",")){
                                 service_id=conn.rs.getString(1);

              contraceptive="";
              contraceptive_image="images/absent.JPG";
              service_point=""; 
              service_point_image="images/absent.JPG"; 
              cds=""; 
              tb=""; 
              tb_image="images/absent.JPG"; 
              sti=""; 
              sti_image="images/absent.JPG";
              partner_testing=""; 
              partner_testing_image="images/absent.JPG"; 
              children_testing="";
              children_testing_image="images/absent.JPG";
              disclosed_status="";   
              disclosed_status_image="images/absent.JPG";
             
           
//             Add the values to the bean object
             ebean3.setClientId(activeClientId);
             ebean3.setFull_name(full_names);
             ebean3.setAge(age);
             ebean3.setSex(sex);
             ebean3.setCds(cds);
             ebean3.setChildren_testing(children_testing);
             ebean3.setContraceptive(contraceptive);
             ebean3.setDisclosed_status(disclosed_status);
             ebean3.setPartner_testing(partner_testing);
             ebean3.setPositioner(positioner);
             ebean3.setService_id(service_id);
             ebean3.setService_point(service_point);
             ebean3.setSti(sti);
             ebean3.setTb(tb);
             
             ebean3.setChildren_testing_image(children_testing_image);
             ebean3.setContraceptive_image(contraceptive_image);
             ebean3.setDisclosed_status_image(disclosed_status_image);
             ebean3.setPartner_testing_image(partner_testing_image);
             ebean3.setCds_image(cds_image);
             ebean3.setService_point_image(service_point_image);
             ebean3.setSti_image(sti_image);
             ebean3.setTb_image(tb_image);
             ebean3.setDisabled("disabled");
             remarks=conn.rs.getString(11);
             prepared_by=conn.rs.getString(12);
             reviewed_by=conn.rs.getString(13);
             submission_date=conn.rs.getString(14);
            edit_reg3.add(ebean3); 
//            nextpage="edit_services_provided.jsp";
                }
                else{
         ebean3.setDisabled("");
             service_id=conn.rs.getString(1);
             if(conn.rs.getString(3).equals("YES")){
              contraceptive="checked";
              contraceptive_image="images/present.JPG";
             }
             else if(conn.rs.getString(3).equals("NO")){
              contraceptive="";
              contraceptive_image="images/absent.JPG";
             } 
             
             if(conn.rs.getString(4).equals("YES")){
              service_point="checked"; 
              service_point_image="images/present.JPG"; 
             }
             else if(conn.rs.getString(4).equals("NO")){
              service_point=""; 
              service_point_image="images/absent.JPG"; 
             }
             
            cds=conn.rs.getString(5); 
             
             if(conn.rs.getString(6).equals("YES")){
              tb="checked"; 
              tb_image="images/present.JPG"; 
             }
             else if(conn.rs.getString(6).equals("NO")){
              tb=""; 
              tb_image="images/absent.JPG"; 
             }
             
             if(conn.rs.getString(7).equals("YES")){
              sti="checked";   
              sti_image="images/present.JPG";
             }
             else if(conn.rs.getString(7).equals("NO")){
              sti=""; 
              sti_image="images/absent.JPG";
             }
             
             
             if(conn.rs.getString(8).equals("YES")){
              partner_testing="checked"; 
              partner_testing_image="images/present.JPG"; 
             }
            else if(conn.rs.getString(8).equals("NO")){
              partner_testing=""; 
              partner_testing_image="images/absent.JPG"; 
             }
             
             if(conn.rs.getString(9).equals("YES")){
              children_testing="checked";  
              children_testing_image="images/present.JPG";
             }
             else if(conn.rs.getString(9).equals("NO")){
              children_testing="";
              children_testing_image="images/absent.JPG";
             }
             
             if(conn.rs.getString(10).equals("YES")){
              disclosed_status="checked";
              disclosed_status_image="images/present.JPG";
             }
             else if(conn.rs.getString(10).equals("NO")){
              disclosed_status="";   
              disclosed_status_image="images/absent.JPG";
             }
             
           
//             Add the values to the bean object
             ebean3.setClientId(activeClientId);
             ebean3.setFull_name(full_names);
             ebean3.setAge(age);
             ebean3.setSex(sex);
             ebean3.setCds(cds);
             ebean3.setChildren_testing(children_testing);
             ebean3.setContraceptive(contraceptive);
             ebean3.setDisclosed_status(disclosed_status);
             ebean3.setPartner_testing(partner_testing);
             ebean3.setPositioner(positioner);
             ebean3.setService_id(service_id);
             ebean3.setService_point(service_point);
             ebean3.setSti(sti);
             ebean3.setTb(tb);
             
             ebean3.setChildren_testing_image(children_testing_image);
             ebean3.setContraceptive_image(contraceptive_image);
             ebean3.setDisclosed_status_image(disclosed_status_image);
             ebean3.setPartner_testing_image(partner_testing_image);
             ebean3.setCds_image(cds_image);
             ebean3.setService_point_image(service_point_image);
             ebean3.setSti_image(sti_image);
             ebean3.setTb_image(tb_image);
             
             remarks=conn.rs.getString(11);
             prepared_by=conn.rs.getString(12);
             reviewed_by=conn.rs.getString(13);
             submission_date=conn.rs.getString(14);
            edit_reg3.add(ebean3); 
         
            }
            }
           }
            else{
           
//           FOR NEWL ADDED CLIENTS TO AN EXISTING GROUP===============================================
                             
    if(session.getAttribute("missed_clients").toString().contains(","+activeClientId+",")){
                                 service_id="";

              contraceptive="";
              contraceptive_image="images/absent.JPG";
              service_point=""; 
              service_point_image="images/absent.JPG"; 
              cds=""; 
              tb=""; 
              tb_image="images/absent.JPG"; 
              sti=""; 
              sti_image="images/absent.JPG";
              partner_testing=""; 
              partner_testing_image="images/absent.JPG"; 
              children_testing="";
              children_testing_image="images/absent.JPG";
              disclosed_status="";   
              disclosed_status_image="images/absent.JPG";
             
           
//             Add the values to the bean object
             ebean3.setClientId(activeClientId);
             ebean3.setFull_name(full_names);
             ebean3.setAge(age);
             ebean3.setSex(sex);
             ebean3.setCds(cds);
             ebean3.setChildren_testing(children_testing);
             ebean3.setContraceptive(contraceptive);
             ebean3.setDisclosed_status(disclosed_status);
             ebean3.setPartner_testing(partner_testing);
             ebean3.setPositioner(positioner);
             ebean3.setService_id(service_id);
             ebean3.setService_point(service_point);
             ebean3.setSti(sti);
             ebean3.setTb(tb);
             
             ebean3.setChildren_testing_image(children_testing_image);
             ebean3.setContraceptive_image(contraceptive_image);
             ebean3.setDisclosed_status_image(disclosed_status_image);
             ebean3.setPartner_testing_image(partner_testing_image);
             ebean3.setCds_image(cds_image);
             ebean3.setService_point_image(service_point_image);
             ebean3.setSti_image(sti_image);
             ebean3.setTb_image(tb_image);
             ebean3.setDisabled("disabled");
//             remarks="";
//             prepared_by="";
//             reviewed_by="";
//             submission_date="";
            edit_reg3.add(ebean3); 
//            nextpage="edit_services_provided.jsp";
                }
                else{
         ebean3.setDisabled("");
             service_id="";
           
              contraceptive="";
              contraceptive_image="images/absent.JPG";
             service_point=""; 
             service_point_image="images/absent.JPG"; 
            cds=""; 
             tb=""; 
              tb_image="images/absent.JPG"; 
             sti=""; 
              sti_image="images/absent.JPG";
             
              partner_testing=""; 
              partner_testing_image="images/absent.JPG"; 
             
             children_testing="";
              children_testing_image="images/absent.JPG";
             disclosed_status="";   
              disclosed_status_image="images/absent.JPG";
             
             
           
//             Add the values to the bean object
             ebean3.setClientId(activeClientId);
             ebean3.setFull_name(full_names);
             ebean3.setAge(age);
             ebean3.setSex(sex);
             ebean3.setCds(cds);
             ebean3.setChildren_testing(children_testing);
             ebean3.setContraceptive(contraceptive);
             ebean3.setDisclosed_status(disclosed_status);
             ebean3.setPartner_testing(partner_testing);
             ebean3.setPositioner(positioner);
             ebean3.setService_id(service_id);
             ebean3.setService_point(service_point);
             ebean3.setSti(sti);
             ebean3.setTb(tb);
             
             ebean3.setChildren_testing_image(children_testing_image);
             ebean3.setContraceptive_image(contraceptive_image);
             ebean3.setDisclosed_status_image(disclosed_status_image);
             ebean3.setPartner_testing_image(partner_testing_image);
             ebean3.setCds_image(cds_image);
             ebean3.setService_point_image(service_point_image);
             ebean3.setSti_image(sti_image);
             ebean3.setTb_image(tb_image);
             
//             remarks="";
//             prepared_by="";
//             reviewed_by="";
//             submission_date="";
            edit_reg3.add(ebean3); 
         
            }
   
                
                
               
                
            }  
                
                
            
           }  
       }
  System.out.println("next page : "+nextpage);
       
             ebean3_general.setRemarks(remarks);
             ebean3_general.setPrepared_by(prepared_by);
             ebean3_general.setReviewed_by(reviewed_by);
             ebean3_general.setSubmission_date(submission_date);
             edit_reg3_general.add(ebean3_general);
             
//         ADD DATA TO THE TRACKERS================    
        int found=0;
     //       INSERT TRACKER TO THE DATABASE---------------------
        String checker="SELECT COUNT(id) FROM tracker2";
        conn.rs=conn.st.executeQuery(checker);
       if(conn.rs.next()==true){
           found=conn.rs.getInt(1);
       }
       if(found==0){
      
           IdGenerator IGM = new IdGenerator();
           
      String inserter="INSERT INTO tracker2 (id,clientids,sessions,missed_clients,timestamp,district,partner,groupname) VALUES(?,?,?,?,?,?,?,?)";
         conn.pst=conn.conn.prepareStatement(inserter);
       conn.pst.setString(1, IGM.current_id());
       conn.pst.setString(2, session.getAttribute("client_ids").toString());
       conn.pst.setString(3, session.getAttribute("chosen_session_1").toString());
       conn.pst.setString(4, session.getAttribute("missed_clients").toString());
       conn.pst.setString(5, IGM.toDay());
       conn.pst.setString(6, session.getAttribute("district_name").toString());
       conn.pst.setString(7, session.getAttribute("partner_name").toString());
       conn.pst.setString(8, session.getAttribute("sessions_group_name").toString());
       
       conn.pst.executeUpdate();
       
       
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

if(alreadyMarked==0){
    nextpage="load_services_provided";   
}
else{
   nextpage="edit_services_provided.jsp";    
}
        session.setAttribute("edit_reg3", edit_reg3);
        session.setAttribute("edit_reg3_general", edit_reg3_general);
        session.setAttribute("clients", positioner);
        response.sendRedirect(nextpage);
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
            Logger.getLogger(edit_register3.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_register3.class.getName()).log(Level.SEVERE, null, ex);
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

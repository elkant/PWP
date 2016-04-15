/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

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
import pwp.dbConn4;

/**
 *
 * @author Geofrey Nyabuto
 */
public class partnerkePMSDatabases extends HttpServlet {
HttpSession session;
String group_id,group_name,partner_id,district_id,nhf_id,location,year_formed,timestamp;
String client_id,fname,mname,lname,age,gender,groupings,lessons_attended,marking_status,year,provider_id,id;
String name,status;
String reg_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
String session_no,value,date,datekey,month;
String services_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner;
String tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,submission_year;
String phone,group_ids;
String sessions_id,session_id,session_date,message_id,method_id,time_taken,male_condoms,female_condoms,no_iec;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
        dbConn4 conn2 = new dbConn4();
        session=request.getSession();
        
        position=0;
        String [] partners =request.getParameterValues("partner");
        for( String partnerid : partners){
            
      if(!partnerid.equals("")){
       partner_id=partnerid;
       System.out.println("partner id is : "+partner_id);
       
//       GET GROUPS=======================================
       String  getGroups="SELECT * FROM groups WHERE partner_id='"+partner_id+"'";
       conn.rs=conn.st.executeQuery(getGroups);
       while(conn.rs.next()){
           
      String insertergrp="INSERT INTO groups (group_id,group_name,partner_id,district_id,nhf_id,location,year_formed,timestamp,ward_id) "
              + "VALUES (?,?,?,?,?,?,?,?,?)";
      conn2.pst=conn2.connx.prepareStatement(insertergrp);
      
      conn2.pst.setString(1, conn.rs.getString(1));
      conn2.pst.setString(2, conn.rs.getString(2));
      conn2.pst.setString(3, conn.rs.getString(3));
      conn2.pst.setString(4, conn.rs.getString(4));
      conn2.pst.setString(5, conn.rs.getString(5));
      conn2.pst.setString(6, conn.rs.getString(6));
      conn2.pst.setString(7, conn.rs.getString(7));
      conn2.pst.setString(8, conn.rs.getString(8));
      conn2.pst.setString(9, conn.rs.getString(9));
      conn2.pst.execute();
           
       }
       System.out.println("end of groups : ");
//    GET NO GROUPS==============================================  
    String getnogroup="SELECT * FROM no_group WHERE partner_id='"+partner_id+"'";
    conn.rs=conn.st.executeQuery(getnogroup);
    while(conn.rs.next()){
       String inserternogrp="INSERT INTO no_group(id,name,district_id,partner_id,nhf_id,status,timestamp) "
               + "VALUES(?,?,?,?,?,?,?)";
       conn2.pst=conn2.connx.prepareStatement(inserternogrp);
       conn2.pst.setString(1, conn.rs.getString(1));
      conn2.pst.setString(2, conn.rs.getString(2));
      conn2.pst.setString(3, conn.rs.getString(3));
      conn2.pst.setString(4, conn.rs.getString(4));
      conn2.pst.setString(5, conn.rs.getString(5));
      conn2.pst.setString(6, conn.rs.getString(6));
      conn2.pst.setString(7, conn.rs.getString(7));
      conn2.pst.execute();
    }
     System.out.println("end of no group");
//    GET SERVICE PROVIDER=====================================
    String getProviders="SELECT * FROM service_provider WHERE partner_id='"+partner_id+"'";
    conn.rs=conn.st.executeQuery(getProviders);
    while(conn.rs.next()){
        
    String insertProviders="INSERT INTO service_provider (provider_id,fname,mname,lname,phone,group_ids,partner_id,district_id,timestamp,national_id) "
            + " VALUES(?,?,?,?,?,?,?,?,?,?)" ;
    conn2.pst=conn2.connx.prepareStatement(insertProviders);
      conn2.pst.setString(1, conn.rs.getString(1));
      conn2.pst.setString(2, conn.rs.getString(2));
      conn2.pst.setString(3, conn.rs.getString(3));
      conn2.pst.setString(4, conn.rs.getString(4));
      conn2.pst.setString(5, conn.rs.getString(5));
      conn2.pst.setString(6, conn.rs.getString(6));
      conn2.pst.setString(7, conn.rs.getString(7));
      conn2.pst.setString(8, conn.rs.getString(8));
      conn2.pst.setString(9, conn.rs.getString(9));
      conn2.pst.setString(10, conn.rs.getString(10));
      conn2.pst.execute();   
        
    }
     System.out.println("end of service provider");
//   GET SESSIONS================================================
    String getsessions="SELECT * FROM sessions WHERE partner_id='"+partner_id+"'";
    conn.rs=conn.st.executeQuery(getsessions);
    while(conn.rs.next()){
        
     String insertsessions="INSERT INTO sessions (sessions_id,group_id,groupings,session_id,session_date,message_id,method_id,time_taken,male_condoms,female_condoms,no_iec,partner_id,district_id,year,timestamp) "
             + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     conn2.pst=conn2.connx.prepareStatement(insertsessions);
     conn2.pst.setString(1, conn.rs.getString(1));
      conn2.pst.setString(2, conn.rs.getString(2));
      conn2.pst.setString(3, conn.rs.getString(3));
      conn2.pst.setString(4, conn.rs.getString(4));
      conn2.pst.setString(5, conn.rs.getString(5));
      conn2.pst.setString(6, conn.rs.getString(6));
      conn2.pst.setString(7, conn.rs.getString(7));
      conn2.pst.setString(8, conn.rs.getString(8));
      conn2.pst.setString(9, conn.rs.getString(9));
      conn2.pst.setString(10, conn.rs.getString(10));
      conn2.pst.setString(11, conn.rs.getString(11));
      conn2.pst.setString(12, conn.rs.getString(12));
      conn2.pst.setString(13, conn.rs.getString(13));
      conn2.pst.setString(14, conn.rs.getString(14));
      conn2.pst.setString(15, conn.rs.getString(15));
      conn2.pst.execute();  
        
        
    }
    System.out.println("end of sessions"); 
    
//   ADD CLIENTS TO THE NEW DATABASE===========================================================
    
    String getClients="SELECT * FROM clients WHERE partner_id='"+partner_id+"'";
    conn.rs=conn.st.executeQuery(getClients);
    while(conn.rs.next()){
        
    String addClient= "INSERT INTO clients (client_id,fname,mname,lname,age,gender,group_id,groupings,district_id,partner_id,lessons_attended,marking_status,year,provider_id,timestamp) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     conn2.pst=conn2.connx.prepareStatement(addClient);
     conn2.pst.setString(1, conn.rs.getString(1));
      conn2.pst.setString(2, conn.rs.getString(2));
      conn2.pst.setString(3, conn.rs.getString(3));
      conn2.pst.setString(4, conn.rs.getString(4));
      conn2.pst.setString(5, conn.rs.getString(5));
      conn2.pst.setString(6, conn.rs.getString(6));
      conn2.pst.setString(7, conn.rs.getString(7));
      conn2.pst.setString(8, conn.rs.getString(8));
      conn2.pst.setString(9, conn.rs.getString(9));
      conn2.pst.setString(10, conn.rs.getString(10));
      conn2.pst.setString(11, conn.rs.getString(11));
      conn2.pst.setString(12, conn.rs.getString(12));
      conn2.pst.setString(13, conn.rs.getString(13));
      conn2.pst.setString(14, conn.rs.getString(14));
      conn2.pst.setString(15, conn.rs.getString(15));  
      conn2.pst.execute(); 
    }
    System.out.println("end of clients"); 
    
    
    String merger="SELECT * FROM personal_information WHERE partner_id='"+partner_id+"'";
    conn.rs=conn.st.executeQuery(merger);
    while(conn.rs.next()){
        
    String add_Client="INSERT INTO personal_information"
+ "(client_id,fname,mname,lname,district_id,location,national_id,mobile_no,gender,dob,marital_status,"
+ "employment_status,education_level,under_18s,ovc_children,group_id,provider_id,partner_id,"
+ "hiv_year,art_status,hf_id,ccc_no,registration_date,approved_by,designation,approval_date,"
        + "lessons_attended,completionyear,completionmonth,status,timestamp,dic_id,ward_id)"
+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          conn2.pst=conn2.connx.prepareStatement(add_Client);
          conn2.pst.setString(1, conn.rs.getString(1));
          conn2.pst.setString(2, conn.rs.getString(2));
          conn2.pst.setString(3, conn.rs.getString(3));
          conn2.pst.setString(4, conn.rs.getString(4));
          conn2.pst.setString(5, conn.rs.getString(5));
          conn2.pst.setString(6, conn.rs.getString(6));
          conn2.pst.setString(7, conn.rs.getString(7));
          conn2.pst.setString(8, conn.rs.getString(8));
          conn2.pst.setString(9, conn.rs.getString(9));
          conn2.pst.setString(10, conn.rs.getString(10));
          conn2.pst.setString(11, conn.rs.getString(11));
          conn2.pst.setString(12, conn.rs.getString(12));
          conn2.pst.setString(13, conn.rs.getString(13));
          conn2.pst.setString(14, conn.rs.getString(14));
          conn2.pst.setString(15, conn.rs.getString(15));
          conn2.pst.setString(16, conn.rs.getString(16));
          conn2.pst.setString(17, conn.rs.getString(17));
          conn2.pst.setString(18, conn.rs.getString(18));
          conn2.pst.setString(19, conn.rs.getString(19));
          conn2.pst.setString(20, conn.rs.getString(20));
          conn2.pst.setString(21, conn.rs.getString(21));
          conn2.pst.setString(22, conn.rs.getString(22));
          conn2.pst.setString(23, conn.rs.getString(23));
          conn2.pst.setString(24, conn.rs.getString(24));
          conn2.pst.setString(25, conn.rs.getString(25));
          conn2.pst.setString(26, conn.rs.getString(26));
          conn2.pst.setString(27, conn.rs.getString(27));
          conn2.pst.setString(28, conn.rs.getString(28));
          conn2.pst.setString(29, conn.rs.getString(29));
          conn2.pst.setString(30, conn.rs.getString(30));
          conn2.pst.setString(31, conn.rs.getString(31));
          conn2.pst.setString(32, conn.rs.getString(32));
          conn2.pst.setString(33, conn.rs.getString(33));
          conn2.pst.executeUpdate();    
        
        
     String getRegister="SELECT * FROM register WHERE client_id='"+conn.rs.getString(1)+"'";
     conn.rs1=conn.st1.executeQuery(getRegister);
     while(conn.rs1.next()){
         
         String addRegister="INSERT INTO register (reg_id,client_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,timestamp) "
                 + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      conn2.pst=conn2.connx.prepareStatement(addRegister);
      conn2.pst.setString(1, conn.rs1.getString(1));
      conn2.pst.setString(2, conn.rs1.getString(2));
      conn2.pst.setString(3, conn.rs1.getString(3));
      conn2.pst.setString(4, conn.rs1.getString(4));
      conn2.pst.setString(5, conn.rs1.getString(5));
      conn2.pst.setString(6, conn.rs1.getString(6));
      conn2.pst.setString(7, conn.rs1.getString(7));
      conn2.pst.setString(8, conn.rs1.getString(8));
      conn2.pst.setString(9, conn.rs1.getString(9));
      conn2.pst.setString(10, conn.rs1.getString(10));
      conn2.pst.setString(11, conn.rs1.getString(11));
      conn2.pst.setString(12, conn.rs1.getString(12));
      conn2.pst.setString(13, conn.rs1.getString(13));
      conn2.pst.setString(14, conn.rs1.getString(14));
      conn2.pst.setString(15, conn.rs1.getString(15)); 
      conn2.pst.setString(16, conn.rs1.getString(16)); 
      conn2.pst.execute();
     }
   
   String getRegister2="SELECT * FROM register2 WHERE client_id='"+conn.rs.getString(1)+"'";
   conn.rs1=conn.st1.executeQuery(getRegister2);
   while(conn.rs1.next()){
    String insertregister2="INSERT INTO register2 (reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
            + "VALUES (?,?,?,?,?,?,?,?,?)" ;
    
      conn2.pst=conn2.connx.prepareStatement(insertregister2);
      conn2.pst.setString(1, conn.rs1.getString(1));
      conn2.pst.setString(2, conn.rs1.getString(2));
      conn2.pst.setString(3, conn.rs1.getString(3));
      conn2.pst.setString(4, conn.rs1.getString(4));
      conn2.pst.setString(5, conn.rs1.getString(5));
      conn2.pst.setString(6, conn.rs1.getString(6));
      conn2.pst.setString(7, conn.rs1.getString(7));
      conn2.pst.setString(8, conn.rs1.getString(8));
      conn2.pst.setString(9, conn.rs1.getString(9));
      conn2.pst.execute();  
    }
     
   String getServices="SELECT * FROM services_provided WHERE client_id='"+conn.rs.getString(1)+"'";
   conn.rs1=conn.st1.executeQuery(getServices);
   while(conn.rs1.next()){
       String addServices="INSERT INTO services_provided (services_id,client_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,submission_year,timestamp,session_no) "
               + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      conn2.pst=conn2.connx.prepareStatement(addServices);
      conn2.pst.setString(1, conn.rs1.getString(1));
      conn2.pst.setString(2, conn.rs1.getString(2));
      conn2.pst.setString(3, conn.rs1.getString(3));
      conn2.pst.setString(4, conn.rs1.getString(4));
      conn2.pst.setString(5, conn.rs1.getString(5));
      conn2.pst.setString(6, conn.rs1.getString(6));
      conn2.pst.setString(7, conn.rs1.getString(7));
      conn2.pst.setString(8, conn.rs1.getString(8));
      conn2.pst.setString(9, conn.rs1.getString(9));
      conn2.pst.setString(10, conn.rs1.getString(10));
      conn2.pst.setString(11, conn.rs1.getString(11));
      conn2.pst.setString(12, conn.rs1.getString(12));
      conn2.pst.setString(13, conn.rs1.getString(13));
      conn2.pst.setString(14, conn.rs1.getString(14));
      conn2.pst.setString(15, conn.rs1.getString(15)); 
      conn2.pst.setString(16, conn.rs1.getString(16)); 
      conn2.pst.setString(17, conn.rs1.getString(17)); 
      conn2.pst.setString(18, conn.rs1.getString(18)); 
      conn2.pst.execute();  
   }
//   GET ADHERENCE 
   
   String getAdherence="SELECT * FROM adherence WHERE client_id='"+conn.rs.getString(1)+"'";
   conn.rs2=conn.st2.executeQuery(getAdherence);
   while(conn.rs2.next()){
       String inserter="INSERT INTO adherence (id,client_id,session_no,status,date_of_session,timestamp) "
                + "VALUES('"+conn.rs2.getString(1)+"','"+conn.rs2.getString(2)+"','"+conn.rs2.getString(3)+"','"+conn.rs2.getString(4)+"','"+conn.rs2.getString(5)+"','"+conn.rs2.getString(6)+"')";
     conn2.stt.executeUpdate(inserter);
   }
   
//   GET FAMILY PLANNING

   
   
//   prevention messages
   String preventionMess="SELECT * FROM prevention_messages WHERE client_id='"+conn.rs.getString(1)+"'";
   conn.rs2=conn.st2.executeQuery(preventionMess);
   while(conn.rs2.next()){
 String addPreventionMessages="INSERT INTO prevention_messages (id,client_id,date_of_assessment,knowledge_of_hiv,"
 + "partner_hiv_testing,any_child_tested,any_child_not_tested,discordance,hiv_disclosure,abstinence,"
+ "faithful_to_one_partner,safer_sex_methods,multiple_sex_partner,condom_use,alcohol_substance_abuse,"
+ "adherence_to_arv,adherence_to_others,asking_stis_questions,family_planning,"
+ "planning_to_have_children,screened_for_TB,timestamp) "
+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 conn2.pst=conn2.connx.prepareStatement(addPreventionMessages);
  conn2.pst.setString(1,conn.rs2.getString("id"));
  conn2.pst.setString(2,conn.rs2.getString("client_id"));
  conn2.pst.setString(3,conn.rs2.getString("date_of_assessment"));
  conn2.pst.setString(4,conn.rs2.getString("knowledge_of_hiv"));
  conn2.pst.setString(5,conn.rs2.getString("partner_hiv_testing"));
  conn2.pst.setString(6,conn.rs2.getString("any_child_tested"));
  conn2.pst.setString(7,conn.rs2.getString("any_child_not_tested"));
  conn2.pst.setString(8,conn.rs2.getString("discordance"));
  conn2.pst.setString(9,conn.rs2.getString("hiv_disclosure"));
  conn2.pst.setString(10,conn.rs2.getString("abstinence"));
  conn2.pst.setString(11,conn.rs2.getString("faithful_to_one_partner"));
  conn2.pst.setString(12,conn.rs2.getString("safer_sex_methods"));
  conn2.pst.setString(13,conn.rs2.getString("multiple_sex_partner"));
  conn2.pst.setString(14,conn.rs2.getString("condom_use"));
  conn2.pst.setString(15,conn.rs2.getString("alcohol_substance_abuse"));
  conn2.pst.setString(16,conn.rs2.getString("adherence_to_arv") );
  conn2.pst.setString(17,conn.rs2.getString("adherence_to_others") );
  conn2.pst.setString(18,conn.rs2.getString("asking_stis_questions") );
  conn2.pst.setString(19,conn.rs2.getString("family_planning"));
  conn2.pst.setString(20,conn.rs2.getString("planning_to_have_children"));
  conn2.pst.setString(21,conn.rs2.getString("screened_for_TB"));
  conn2.pst.setString(22,conn.rs2.getString("timestamp"));
  conn2.pst.executeUpdate();
  
//  prevention counselling
  String GetPC="SELECT * FROM prevention_counseling WHERE id='"+conn.rs2.getString("id")+"'";
  conn.rs3=conn.st3.executeQuery(GetPC);
  if(conn.rs3.next()==true){
    String addPreventionCounseling="INSERT INTO prevention_counseling(id,prevention_message_id,hiv_disclosure,"
    + "safer_sex_methods,alcohol_use,adherence_to_arvs,adherence_other_medications,couples_counseling)"
      + " VALUES(?,?,?,?,?,?,?,?,?)";
 conn2.pst=conn2.connx.prepareStatement(addPreventionCounseling);
 conn2.pst.setString(1, conn.rs3.getString("id"));
 conn2.pst.setString(2, conn.rs3.getString("prevention_message_id"));
 conn2.pst.setString(3, conn.rs3.getString("hiv_disclosure"));
 conn2.pst.setString(4, conn.rs3.getString("safer_sex_methods"));
 conn2.pst.setString(5, conn.rs3.getString("alcohol_use"));
 conn2.pst.setString(6, conn.rs3.getString("adherence_to_arvs"));
 conn2.pst.setString(7, conn.rs3.getString("adherence_other_medications"));
 conn2.pst.setString(8, conn.rs3.getString("couples_counseling"));
 conn2.pst.setString(9, conn.rs3.getString("timestamp"));
 conn2.pst.executeUpdate();
  }
// hiv testing
 String GetHIV="SELECT * FROM hiv_testing_stis WHERE id='"+conn.rs2.getString("id")+"'";
  conn.rs3=conn.st3.executeQuery(GetHIV);
  if(conn.rs3.next()==true){
 String addHIV="INSERT INTO hiv_testing_stis(id,prevention_message_id,partner_tested,children_tested,"
         + "referral_for_sti,risk_reduction_info,treatment_adherence,condoms_provided,timestamp)"
         + " VALUES(?,?,?,?,?,?,?,?,?)";
 
 conn2.pst=conn2.connx.prepareStatement(addHIV);
 conn2.pst.setString(1, conn.rs3.getString("id"));
 conn2.pst.setString(2, conn.rs3.getString("prevention_message_id"));
 conn2.pst.setString(3, conn.rs3.getString("partner_tested"));
 conn2.pst.setString(4, conn.rs3.getString("children_tested"));
 conn2.pst.setString(5, conn.rs3.getString("referral_for_sti"));
 conn2.pst.setString(6, conn.rs3.getString("risk_reduction_info"));
 conn2.pst.setString(7, conn.rs3.getString("treatment_adherence"));
 conn2.pst.setString(8, conn.rs3.getString("condoms_provided"));
 conn2.pst.setString(9, conn.rs3.getString("timestamp"));
 conn2.pst.executeUpdate(); 
 
  }
// family planning
 
 String GetFP="SELECT * FROM family_planning_tb_pmtct WHERE id='"+conn.rs2.getString("id")+"'";
  conn.rs3=conn.st3.executeQuery(GetFP);
  if(conn.rs3.next()==true){
  String addFamilyPlanning="INSERT INTO family_planning_tb_pmtct (id,prevention_message_id,pregnancy_status,"
         + "hormonal_contraceptive,condoms,pregnancy_counseling,transmission_risks,tb_screening,"
         + "referred_tb_diagnosis,referred_pmtct_services,other_referrals,referral_point) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

 conn2.pst=conn2.connx.prepareStatement(addFamilyPlanning);
 conn2.pst.setString(1, conn.rs3.getString("id"));
 conn2.pst.setString(2, conn.rs3.getString("prevention_message_id"));
 conn2.pst.setString(3, conn.rs3.getString("pregnancy_status"));
 conn2.pst.setString(4, conn.rs3.getString("hormonal_contraceptive"));
 conn2.pst.setString(5, conn.rs3.getString("condoms"));
 conn2.pst.setString(6, conn.rs3.getString("pregnancy_counseling"));
 conn2.pst.setString(7, conn.rs3.getString("transmission_risks"));
 conn2.pst.setString(8, conn.rs3.getString("tb_screening"));
 conn2.pst.setString(9, conn.rs3.getString("referred_tb_diagnosis"));
 conn2.pst.setString(10, conn.rs3.getString("referred_pmtct_services"));
 conn2.pst.setString(11, conn.rs3.getString("other_referrals"));
 conn2.pst.setString(12, conn.rs3.getString("referral_point"));
 conn2.pst.setString(13, conn.rs3.getString("timestamp"));
 conn2.pst.executeUpdate();
  }
   }//end of assessment
   position++;
   System.out.println("client inserted at position : "+position);
   }//end of clients loop in personal information
      }//
        }//end of partner loop
        
        
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
        
    System.out.println("completed splitting data.");
    session.setAttribute("splitData", "<font color=\"green\">Completed splitting data successfully.</font>");
    response.sendRedirect("separateData.jsp");
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
        Logger.getLogger(partnerkePMSDatabases.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(partnerkePMSDatabases.class.getName()).log(Level.SEVERE, null, ex);
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

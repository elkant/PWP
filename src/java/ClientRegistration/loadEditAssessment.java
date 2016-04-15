/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class loadEditAssessment extends HttpServlet {
HttpSession session;
String date_of_assessment,id,client_id;
String knowledge_of_hiv,partner_hiv_testing,any_child_tested,any_child_not_tested,discordance,hiv_disclosure;
String abstinence,faithful_to_one_partner,safer_sex_methods,multiple_sex_partner,condom_use,alcohol_substance_abuse;
String adherence_to_arv,adherence_to_others,asking_stis_questions,family_planning;
String planning_to_have_children,screened_for_TB; 

String prevention_message_id,hiv_disclosure2,safer_sex_methods2,alcohol_use,adherence_to_arvs;
String adherence_other_medications,couples_counseling;

String partner_tested,children_tested,referral_for_sti,risk_reduction_info,treatment_adherence,condoms_provided;

String pregnancy_status,hormonal_contraceptive,condoms,pregnancy_counseling,transmission_risks;
String tb_screening,referred_tb_diagnosis,referred_pmtct_services,other_referrals,referral_point;

String knowledge_of_hivx,partner_hiv_testingx,any_child_testedx,any_child_not_testedx,discordancex,hiv_disclosurex;
String abstinencex,faithful_to_one_partnerx,safer_sex_methodsx,multiple_sex_partnerx,condom_usex,alcohol_substance_abusex;
String adherence_to_arvx,adherence_to_othersx,asking_stis_questionsx,family_planningx;
String planning_to_have_childrenx,screened_for_TBx; 

String prevention_message_idx,hiv_disclosure2x,safer_sex_methods2x,alcohol_usex,adherence_to_arvsx;
String adherence_other_medicationsx,couples_counselingx;

String partner_testedx,children_testedx,referral_for_stix,risk_reduction_infox,treatment_adherencex,condoms_providedx;

String pregnancy_statusx,hormonal_contraceptivex,condomsx,pregnancy_counselingx,transmission_risksx;
String tb_screeningx,referred_tb_diagnosisx,referred_pmtct_servicesx,other_referralsx;
String  toogleValue,toogleName;
ArrayList data= new ArrayList();
String prevention_counseling_id,hiv_testing_stis_id,family_planning_tb_pmtct_id;

String client_name,dob,sex,group_name,provider_name,district_name,location;
String cfname,cmname,clname,pfname,pmname,plname;
ArrayList data2 = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
       dbConn conn = new dbConn();
       data.clear();
    
       prevention_counseling_id=hiv_testing_stis_id=family_planning_tb_pmtct_id=""; 
      date_of_assessment=id=client_id="";
knowledge_of_hiv=partner_hiv_testing=any_child_tested=any_child_not_tested=discordance=hiv_disclosure="";
abstinence=faithful_to_one_partner=safer_sex_methods=multiple_sex_partner=condom_use=alcohol_substance_abuse="";
adherence_to_arv=adherence_to_others=asking_stis_questions=family_planning="";
planning_to_have_children=screened_for_TB; 
prevention_message_id=hiv_disclosure2=safer_sex_methods2=alcohol_use=adherence_to_arvs="";
adherence_other_medications=couples_counseling;
partner_tested=children_tested=referral_for_sti=risk_reduction_info=treatment_adherence=condoms_provided="";
pregnancy_status=hormonal_contraceptive=condoms=pregnancy_counseling=transmission_risks="";
tb_screening=referred_tb_diagnosis=referred_pmtct_services=other_referrals="";


       
       client_id=request.getParameter("client_id");
       
       if(session.getAttribute("clientIDAssess")!=null){
           client_id=session.getAttribute("clientIDAssess").toString();
       }
        toogleValue=toogleName="";
        
           String getDetails="SELECT personal_information.fname,personal_information.mname,personal_information.lname,"
              + "personal_information.gender,"
              + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) ),personal_information.location,"
              + "groups.group_name,district.district_name,"
              + "service_provider.fname,service_provider.mname,service_provider.lname "
              + "FROM personal_information JOIN district ON personal_information.district_id=district.district_id "
              + "JOIN service_provider ON service_provider.provider_id=personal_information.provider_id "
              + "LEFT JOIN groups ON personal_information.group_id=groups.group_id WHERE "
              + "personal_information.client_id=?";
      conn.pst=conn.conn.prepareStatement(getDetails);
      conn.pst.setString(1, client_id);
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()==true){
       cfname=conn.rs.getString(1);
       cmname=conn.rs.getString(2);
       clname=conn.rs.getString(3);
       
       sex=conn.rs.getString(4);
       dob=conn.rs.getString(5);
       location=conn.rs.getString(6);
       group_name=conn.rs.getString(7);
       district_name=conn.rs.getString(8);
       
       pfname=conn.rs.getString(9);
       pmname=conn.rs.getString(10);
       plname=conn.rs.getString(11);
       
       if(cmname.equalsIgnoreCase(clname)){cmname="";}
       if(pmname.equalsIgnoreCase(plname)){pmname="";}
       
       client_name=cfname+" "+cmname+" "+clname;
       provider_name=pfname+" "+pmname+" "+plname;
       
       if(group_name==null){
           group_name="INDIVIDUAL";
       }
      if(location.equals("")){
          location="NONE";
      }
      if(dob==null){
          dob="<font color=\"red\">Error: </font> No date of birth";
      }
      else if (dob.equals("1")){
        dob=dob+" Year";  
      }
      else{
           dob=dob+" Years"; 
      }
      
       
      }
        
        
   String getData="SELECT prevention_messages.id,prevention_messages.date_of_assessment,prevention_messages.client_id,prevention_messages.knowledge_of_hiv,prevention_messages.partner_hiv_testing,prevention_messages.any_child_tested,prevention_messages.any_child_not_tested,prevention_messages.discordance,prevention_messages.hiv_disclosure,"
  + "prevention_messages.abstinence,prevention_messages.faithful_to_one_partner,prevention_messages.safer_sex_methods,prevention_messages.multiple_sex_partner,prevention_messages.condom_use,prevention_messages.alcohol_substance_abuse,"
  + "prevention_messages.adherence_to_arv,prevention_messages.adherence_to_others,prevention_messages.asking_stis_questions,prevention_messages.family_planning,prevention_messages.planning_to_have_children,prevention_messages.screened_for_TB,"
  
  + "prevention_counseling.prevention_message_id,prevention_counseling.hiv_disclosure,prevention_counseling.safer_sex_methods,prevention_counseling.alcohol_use,prevention_counseling.adherence_to_arvs,"
  + "prevention_counseling.adherence_other_medications,prevention_counseling.couples_counseling,"
      
  + "hiv_testing_stis.partner_tested,hiv_testing_stis.children_tested,hiv_testing_stis.referral_for_sti,hiv_testing_stis.risk_reduction_info,hiv_testing_stis.treatment_adherence,hiv_testing_stis.condoms_provided,"
   
  + "family_planning_tb_pmtct.pregnancy_status,family_planning_tb_pmtct.hormonal_contraceptive,family_planning_tb_pmtct.condoms,family_planning_tb_pmtct.pregnancy_counseling,family_planning_tb_pmtct.transmission_risks,"
  + "family_planning_tb_pmtct.tb_screening,family_planning_tb_pmtct.referred_tb_diagnosis,family_planning_tb_pmtct.referred_pmtct_services,family_planning_tb_pmtct.other_referrals,"
  + "prevention_counseling.id,hiv_testing_stis.id,family_planning_tb_pmtct.id,family_planning_tb_pmtct.referral_point "
         
           + "FROM prevention_messages JOIN prevention_counseling ON prevention_messages.id=prevention_counseling.prevention_message_id "
           + "JOIN hiv_testing_stis ON hiv_testing_stis.prevention_message_id=prevention_messages.id "
           + "JOIN family_planning_tb_pmtct ON family_planning_tb_pmtct.prevention_message_id=prevention_messages.id "
           + "WHERE prevention_messages.client_id=?";
   conn.pst=conn.conn.prepareStatement(getData);
   conn.pst.setString(1, client_id);
   conn.rs=conn.pst.executeQuery();
   if(conn.rs.next()==true){
      Assessment assess = new Assessment();
      
      id=conn.rs.getString(1);
      date_of_assessment=conn.rs.getString(2);
       String toogler="SELECT * FROM toogle ORDER BY id desc";
       conn.rs1=conn.st1.executeQuery(toogler);
       while(conn.rs1.next()){
  toogleValue=conn.rs1.getString(1);
  toogleName=conn.rs1.getString(2);
  
   knowledge_of_hivx=conn.rs.getString(4);
  if(toogleValue.equals(knowledge_of_hivx)){knowledge_of_hiv+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
  else{knowledge_of_hiv+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

  partner_hiv_testingx=conn.rs.getString(5);
  if(toogleValue.equals(partner_hiv_testingx)){partner_hiv_testing+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
  else{partner_hiv_testing+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

any_child_testedx=conn.rs.getString(6);
if(toogleValue.equals(any_child_testedx)){any_child_tested+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
  else{any_child_tested+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

any_child_not_testedx=conn.rs.getString(7);
if(toogleValue.equals(any_child_not_testedx)){any_child_not_tested+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
  else{any_child_not_tested+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

discordancex=conn.rs.getString(8);
if(toogleValue.equals(discordancex)){discordance+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{discordance+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

hiv_disclosurex=conn.rs.getString(9);
if(toogleValue.equals(hiv_disclosurex)){hiv_disclosure+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{hiv_disclosure+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

abstinencex=conn.rs.getString(10);
if(toogleValue.equals(abstinencex)){abstinence+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{abstinence+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

faithful_to_one_partnerx=conn.rs.getString(11);
if(toogleValue.equals(faithful_to_one_partnerx)){faithful_to_one_partner+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{faithful_to_one_partner+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

safer_sex_methodsx=conn.rs.getString(12);
if(toogleValue.equals(safer_sex_methodsx)){safer_sex_methods+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{safer_sex_methods+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

multiple_sex_partnerx=conn.rs.getString(13);
if(toogleValue.equals(multiple_sex_partnerx)){multiple_sex_partner+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{multiple_sex_partner+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

condom_usex=conn.rs.getString(14);
if(toogleValue.equals(condom_usex)){condom_use+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{condom_use+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

alcohol_substance_abusex=conn.rs.getString(15);
if(toogleValue.equals(alcohol_substance_abusex)){alcohol_substance_abuse+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{alcohol_substance_abuse+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

adherence_to_arvx=conn.rs.getString(16);
if(toogleValue.equals(adherence_to_arvx)){adherence_to_arv+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{adherence_to_arv+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

adherence_to_othersx=conn.rs.getString(17);
if(toogleValue.equals(adherence_to_othersx)){adherence_to_others+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{adherence_to_others+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

asking_stis_questionsx=conn.rs.getString(18);
if(toogleValue.equals(asking_stis_questionsx)){asking_stis_questions+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{asking_stis_questions+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

family_planningx=conn.rs.getString(19);
if(toogleValue.equals(family_planningx)){family_planning+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{family_planning+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

planning_to_have_childrenx=conn.rs.getString(20);
if(toogleValue.equals(planning_to_have_childrenx)){planning_to_have_children+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{planning_to_have_children+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

screened_for_TBx=conn.rs.getString(21);
if(toogleValue.equals(screened_for_TBx)){screened_for_TB+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{screened_for_TB+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

prevention_message_idx=conn.rs.getString(22);
if(toogleValue.equals(prevention_message_idx)){prevention_message_id+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{prevention_message_id+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

hiv_disclosure2x=conn.rs.getString(23);
if(toogleValue.equals(hiv_disclosure2x)){hiv_disclosure2+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{hiv_disclosure2+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

safer_sex_methods2x=conn.rs.getString(24);
if(toogleValue.equals(safer_sex_methods2x)){safer_sex_methods2+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{safer_sex_methods2+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

alcohol_usex=conn.rs.getString(25);
if(toogleValue.equals(alcohol_usex)){alcohol_use+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{alcohol_use+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

adherence_to_arvsx=conn.rs.getString(26);
if(toogleValue.equals(adherence_to_arvsx)){adherence_to_arvs+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{adherence_to_arvs+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

adherence_other_medicationsx=conn.rs.getString(27);
if(toogleValue.equals(adherence_other_medicationsx)){adherence_other_medications+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{adherence_other_medications+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

couples_counselingx=conn.rs.getString(28);
if(toogleValue.equals(couples_counselingx)){couples_counseling+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{couples_counseling+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

partner_testedx=conn.rs.getString(29);
if(toogleValue.equals(partner_testedx)){partner_tested+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{partner_tested+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

children_testedx=conn.rs.getString(30);
if(toogleValue.equals(children_testedx)){children_tested+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{children_tested+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

referral_for_stix=conn.rs.getString(31);
if(toogleValue.equals(referral_for_stix)){referral_for_sti+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{referral_for_sti+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

risk_reduction_infox=conn.rs.getString(32);
if(toogleValue.equals(risk_reduction_infox)){risk_reduction_info+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{risk_reduction_info+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

treatment_adherencex=conn.rs.getString(33);
if(toogleValue.equals(treatment_adherencex)){treatment_adherence+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{treatment_adherence+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

condoms_providedx=conn.rs.getString(34);
if(toogleValue.equals(condoms_providedx)){condoms_provided+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{condoms_provided+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

pregnancy_statusx=conn.rs.getString(35);
if(toogleValue.equals(pregnancy_statusx)){pregnancy_status+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{pregnancy_status+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

hormonal_contraceptivex=conn.rs.getString(36);
if(toogleValue.equals(hormonal_contraceptivex)){hormonal_contraceptive+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{hormonal_contraceptive+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

condomsx=conn.rs.getString(37);
if(toogleValue.equals(condomsx)){condoms+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{condoms+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

pregnancy_counselingx=conn.rs.getString(38);
if(toogleValue.equals(pregnancy_counselingx)){pregnancy_counseling+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{pregnancy_counseling+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

transmission_risksx=conn.rs.getString(39);
if(toogleValue.equals(transmission_risksx)){transmission_risks+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{transmission_risks+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

tb_screeningx=conn.rs.getString(40);
if(toogleValue.equals(tb_screeningx)){tb_screening+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{tb_screening+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

referred_tb_diagnosisx=conn.rs.getString(41);
if(toogleValue.equals(referred_tb_diagnosisx)){referred_tb_diagnosis+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{referred_tb_diagnosis+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

referred_pmtct_servicesx=conn.rs.getString(42);
if(toogleValue.equals(referred_pmtct_servicesx)){referred_pmtct_services+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{referred_pmtct_services+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 

other_referralsx=conn.rs.getString(43);
if(toogleValue.equals(other_referralsx)){other_referrals+="<option value=\""+toogleValue+"\" selected>"+toogleName+"</option>" ;}
else{other_referrals+="<option value=\""+toogleValue+"\">"+toogleName+"</option>" ;} 
prevention_counseling_id=conn.rs.getString(44);
hiv_testing_stis_id=conn.rs.getString(45);
family_planning_tb_pmtct_id=conn.rs.getString(46);
 referral_point=conn.rs.getString(47);


       }
     System.out.println("abstinence : "+abstinence);
       
   assess.setAbstinence(abstinence);
   assess.setAdherence_other_medications(adherence_other_medications);
   assess.setAdherence_to_arv(adherence_to_arv);
   assess.setAdherence_to_arvs(adherence_to_arvs);
   assess.setAdherence_to_others(adherence_to_others);
   assess.setAlcohol_substance_abuse(alcohol_substance_abuse);
   assess.setAlcohol_use(alcohol_use);
   assess.setAny_child_not_tested(any_child_not_tested);
   assess.setAny_child_tested(any_child_tested);
   assess.setAsking_stis_questions(asking_stis_questions);
   assess.setChildren_tested(children_tested);
   assess.setClient_id(client_id);
   assess.setCondom_use(condom_use);
   assess.setCondoms(condoms);
   assess.setCondoms_provided(condoms_provided);
   assess.setCouples_counseling(couples_counseling);
   assess.setDate_of_assessment(date_of_assessment);
   assess.setDiscordance(discordance);
   assess.setFaithful_to_one_partner(faithful_to_one_partner);
   assess.setFamily_planning(family_planning);
   assess.setHiv_disclosure(hiv_disclosure);
   assess.setHiv_disclosure2(hiv_disclosure2);
   assess.setHormonal_contraceptive(hormonal_contraceptive);
   assess.setId(id);
   assess.setKnowledge_of_hiv(knowledge_of_hiv);
   assess.setMultiple_sex_partner(multiple_sex_partner);
   assess.setOther_referrals(other_referrals);
   assess.setPartner_hiv_testing(partner_hiv_testing);
   assess.setPartner_tested(partner_tested);
   assess.setPlanning_to_have_children(planning_to_have_children);
   assess.setPregnancy_counseling(pregnancy_counseling);
   assess.setPregnancy_status(pregnancy_status);
   assess.setPrevention_message_id(prevention_message_id);
   assess.setReferral_for_sti(referral_for_sti);
   assess.setReferred_pmtct_services(referred_pmtct_services);
   assess.setReferred_tb_diagnosis(referred_tb_diagnosis);
   assess.setRisk_reduction_info(risk_reduction_info);
   assess.setSafer_sex_methods(safer_sex_methods);
   assess.setSafer_sex_methods2(safer_sex_methods2);
   assess.setScreened_for_TB(screened_for_TB);
   assess.setTb_screening(tb_screening);
   assess.setTransmission_risks(transmission_risks);
   assess.setTreatment_adherence(treatment_adherence);
   
   assess.setPrevention_counseling_id(prevention_counseling_id);
   assess.setHiv_testing_stis_id(hiv_testing_stis_id);
   assess.setFamily_planning_tb_pmtct_id(family_planning_tb_pmtct_id);
   assess.setReferral_point(referral_point);
    
     assess.setClient_name(client_name);
     assess.setDob(dob);
     assess.setSex(sex);
     assess.setGroup_name(group_name);
     assess.setProvider_name(provider_name);
     assess.setDistrict_name(district_name);
     assess.setLocation(location);
     
     
   data.add(assess);
       
   }
  
   session.setAttribute("Assess", data);
  
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

   session.removeAttribute("clientIDAssess");
   response.sendRedirect("EditAssessment.jsp");
       
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
        Logger.getLogger(loadEditAssessment.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditAssessment.class.getName()).log(Level.SEVERE, null, ex);
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

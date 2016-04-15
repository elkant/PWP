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
public class edit_register2 extends HttpServlet {
HttpSession session;
ArrayList edit_reg2 = new ArrayList();
String group_id,client_id;
int positioner;
String reg_id, full_names,age,sex,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,client_ids;
String s1_image,s2_image,s3_image,s4_image,s5_image,s6_image,s7_image,s8_image,s9_image,s10_image,s11_image,s12_image,s13_image;
       String partner_id, district_id;
       String full_name="",month;
       String sessions_are;
       int second;
       int editChecker;
String disabled1,disabled2,disabled3,disabled4,disabled5,disabled6,disabled7,disabled8,disabled9,disabled10,disabled11,disabled12,disabled13;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        session=request.getSession();
        dbConn conn = new dbConn();
        second=0;editChecker=0;
        sessions_are=",";
        client_ids="";
       edit_reg2.clear();
       positioner=0;
       s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="";
       s1_image=s2_image=s3_image=s4_image=s5_image=s6_image=s7_image=s8_image="";
       s9_image=s10_image=s11_image=s12_image=s13_image="";
       if(session.getAttribute("sessions_group_id")!=null){
        group_id=session.getAttribute("sessions_group_id").toString();
         partner_id=session.getAttribute("partner_id").toString();
         district_id=session.getAttribute("district_id").toString();
        
        if(!group_id.equals("0")){
        String checker="SELECT * FROM personal_information JOIN register ON register.client_id=personal_information.client_id WHERE group_id='"+group_id+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' && status='ACTIVE' LIMIT 1";
        conn.rs1=conn.st1.executeQuery(checker); 
        }
         if(group_id.equals("0")){
        String checker="SELECT * FROM personal_information JOIN register ON register.client_id=personal_information.client_id WHERE groupings='"+session.getAttribute("groupings")+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"'  && status='ACTIVE' LIMIT 1";
        conn.rs1=conn.st1.executeQuery(checker); 
         }
         if(conn.rs1.next()==true){
//             System.out.println("here");
             for(int i=1;i<=13;i++){
                 if(!conn.rs1.getString("s"+i).equals("5")){
             sessions_are+=i+",";
                 }
         }
         }
//         System.out.println("sessions      :   "+sessions_are+"    client id is   :   "+conn.rs1.getString("client_id"));
        if(!group_id.equals("0")){
        String session_details_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE group_id='"+group_id+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"'  && status='ACTIVE' "
                + " ORDER BY fname,mname,lname";
        conn.rs1=conn.st1.executeQuery(session_details_selector);
         }
         
         else if(group_id.equals("0")){
        String session_details_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE groupings='"+session.getAttribute("groupings")+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' && status='ACTIVE' ORDER BY fname,mname,lname";
        conn.rs1=conn.st1.executeQuery(session_details_selector);
         }
          while(conn.rs1.next()){
   disabled1=disabled2=disabled3=disabled4=disabled5=disabled6=disabled7=disabled8=disabled9=disabled10=disabled11=disabled12=disabled13="";
              client_ids+=conn.rs1.getString(1)+",";
              client_id=conn.rs1.getString(1); 
//            System.out.println(" client_ids   "+ client_ids);
            edit_register2_bean ebean2 = new edit_register2_bean();
            
            
           String fname=conn.rs1.getString(2);
            String mname=conn.rs1.getString(3);
             String lname=conn.rs1.getString(4);
               age=conn.rs1.getString(5);
                sex=conn.rs1.getString(6);
               if(mname.equals(lname)){
                   mname="";
               }
               
        String register_selector="SELECT * FROM register WHERE client_id='"+conn.rs1.getString(1)+"'";
        conn.rs=conn.st.executeQuery(register_selector);
      if(conn.rs.next()==true){
            positioner++;
           
//           Set Images
           
           if(conn.rs.getString(3).equals("1")){
           s1_image="images/present.JPG";  
//           editChecker++;
           }
           if(conn.rs.getString(3).equals("2")){
            s1_image="images/absent.JPG";   
           }
           if(conn.rs.getString(3).equals("5")){
            s1_image="images/warning.png";    
           }
           if(conn.rs.getString(3).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",1,")){
            disabled1="NO";
               s1_image="images/non.png";    
           }
           
           if(conn.rs.getString(4).equals("1")){
           s2_image="images/present.JPG";    
           }
           if(conn.rs.getString(4).equals("2")){
            s2_image="images/absent.JPG";   
           }
           if(conn.rs.getString(4).equals("5")){
            s2_image="images/warning.png";    
           }
           if(conn.rs.getString(4).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",2,")){
            disabled2="NO";
               s2_image="images/non.png";    
           }
           if(conn.rs.getString(5).equals("1")){
           s3_image="images/present.JPG";    
           }
           if(conn.rs.getString(5).equals("2")){
            s3_image="images/absent.JPG";   
           }
           if(conn.rs.getString(5).equals("5")){
            s3_image="images/warning.png";    
           }
           if(conn.rs.getString(5).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",3,")){
           disabled3="NO";
               s3_image="images/non.png";    
           }
           
           if(conn.rs.getString(6).equals("1")){
           s4_image="images/present.JPG";  
           }
           if(conn.rs.getString(6).equals("2")){
            s4_image="images/absent.JPG";   
           }
           if(conn.rs.getString(6).equals("5")){
            s4_image="images/warning.png";    
           }
           if(conn.rs.getString(6).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",4,")){
             disabled4="NO";
               s4_image="images/non.png";    
           }
           
           if(conn.rs.getString(7).equals("1")){
           s5_image="images/present.JPG";   
           }
           if(conn.rs.getString(7).equals("2")){
            s5_image="images/absent.JPG";   
           }
           if(conn.rs.getString(7).equals("5")){
            s5_image="images/warning.png";    
           }
           if(conn.rs.getString(7).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",5,")){
            disabled5="NO";
               s5_image="images/non.png";    
           }
           
           if(conn.rs.getString(8).equals("1")){
           s6_image="images/present.JPG";  
           }
           if(conn.rs.getString(8).equals("2")){
            s6_image="images/absent.JPG";   
           }
           if(conn.rs.getString(8).equals("5")){
            s6_image="images/warning.png";    
           }
           if(conn.rs.getString(8).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",6,")){
            disabled6="NO";
               s6_image="images/non.png";    
           }
           
           if(conn.rs.getString(9).equals("1")){
           s7_image="images/present.JPG";    
           }
           if(conn.rs.getString(9).equals("2")){
            s7_image="images/absent.JPG";   
           }
           if(conn.rs.getString(9).equals("5")){
            s7_image="images/warning.png";    
           }
           if(conn.rs.getString(9).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",7,")){
           disabled7="NO";
               s7_image="images/non.png";    
           }
           
           if(conn.rs.getString(10).equals("1")){
           s8_image="images/present.JPG";   
           }
           if(conn.rs.getString(10).equals("2")){
            s8_image="images/absent.JPG";   
           }
           if(conn.rs.getString(10).equals("5")){
            s8_image="images/warning.png";    
           }
           if(conn.rs.getString(10).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",8,")){
            disabled8="NO";
               s8_image="images/non.png";    
           }
           
           if(conn.rs.getString(11).equals("1")){
           s9_image="images/present.JPG";  
           }
           if(conn.rs.getString(11).equals("2")){
            s9_image="images/absent.JPG";   
           }
           if(conn.rs.getString(11).equals("5")){
            s9_image="images/warning.png";   
           }
          if(conn.rs.getString(11).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",9,")){
            disabled9="NO";
              s9_image="images/non.png";    
           } 
           if(conn.rs.getString(12).equals("1")){
           s10_image="images/present.JPG"; 
           }
           if(conn.rs.getString(12).equals("2")){
            s10_image="images/absent.JPG";   
           }
           if(conn.rs.getString(12).equals("5")){
            s10_image="images/warning.png";   
           }
           if(conn.rs.getString(12).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",10,")){
            disabled10="NO";
            s10_image="images/non.png";    
           }
           if(conn.rs.getString(13).equals("1")){
           s11_image="images/present.JPG";    
           }
           if(conn.rs.getString(13).equals("2")){
            s11_image="images/absent.JPG";   
           }
           if(conn.rs.getString(13).equals("5")){
            s11_image="images/warning.png";   
           }
           if(conn.rs.getString(13).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",11,")){
            disabled11="NO";
            s11_image="images/non.png";    
           }
           
           if(conn.rs.getString(14).equals("1")){
           s12_image="images/present.JPG";   
           }
           if(conn.rs.getString(14).equals("2")){
            s12_image="images/absent.JPG";   
           }
           if(conn.rs.getString(14).equals("5")){
            s12_image="images/warning.png";   
           }
           if(conn.rs.getString(14).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",12,")){
            disabled12="NO";
               s12_image="images/non.png";    
           }
           
           if(conn.rs.getString(15).equals("1")){
           s13_image="images/present.JPG"; 
           }
           if(conn.rs.getString(15).equals("2")){
            s13_image="images/absent.JPG";   
           }
           if(conn.rs.getString(15).equals("5")){
            s13_image="images/warning.png";   
           }
           if(conn.rs.getString(15).equals("5") && session.getAttribute("chosen_session_1").toString().contains(",13,")){
           disabled13="NO";
               s13_image="images/non.png";    
           }
           
          
           
       
            
               full_name=fname+" "+mname+" "+lname;
               
               String s1_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s1_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(3))) {
                     s1+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 
                 else{
                     s1+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
      
               
               String s2_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s2_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(4))) {
                     s2+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s2+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s3_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s3_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(5))) {
                     s3+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s3+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s4_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s4_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(6))) {
                     s4+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s4+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s5_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s5_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(7))) {
                     s5+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";;
                 }
                 else{
                     s5+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s6_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s6_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(8))) {
                     s6+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s6+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s7_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s7_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(9))) {
                     s7+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s7+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s8_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s8_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(10))) {
                     s8+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s8+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s9_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s9_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(11))) {
                     s9+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                second++;
                 }
                 else{
                     s9+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s10_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s10_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(12))) {
                     s10+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s10+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s11_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s11_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(13))) {
                     s11+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s11+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s12_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s12_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(14))) {
                     s12+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s12+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
               
               String s13_selects="SELECT * FROM attendance_signs WHERE sign_id<='2'";
               conn.rs2=conn.st2.executeQuery(s13_selects);
               while(conn.rs2.next()){
                 if(conn.rs2.getString(1).equals(conn.rs.getString(15))) {
                     s13+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
                 }
                 else{
                     s13+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";
                 }
               }
          if(second>0){session.setAttribute("second","<br><font color=\"plum\"><b>Subsequent marking.<input type=\"hidden\" readonly name=\"subsequent\" id=\"subsequent\" value=\"YES\"></b></font>");}     
    if(!session.getAttribute("chosen_session_1").toString().contains(",1,") && conn.rs.getString(3).equals("5")){s1="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",2,") && conn.rs.getString(4).equals("5")){s2="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",3,") && conn.rs.getString(5).equals("5")){s3="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",4,") && conn.rs.getString(6).equals("5")){s4="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",5,")&& conn.rs.getString(7).equals("5")){s5="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",6,")&& conn.rs.getString(8).equals("5")){s6="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",7,") && conn.rs.getString(9).equals("5")){s7="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",8,") && conn.rs.getString(10).equals("5")){s8="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",9,") && conn.rs.getString(11).equals("5")){s9="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",10,") && conn.rs.getString(12).equals("5")){s10="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",11,") && conn.rs.getString(13).equals("5")){s11="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",12,") && conn.rs.getString(14).equals("5")){s12="<option value=\"5\" selected></option>";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",13,") && conn.rs.getString(15).equals("5")){s13="<option value=\"5\" selected></option>";}
     
               
               ebean2.setAge(age);
               ebean2.setSex(sex);
               ebean2.setFull_names(full_name);
               ebean2.setPositioner(positioner);
               ebean2.setReg_id(conn.rs.getString(1));
               ebean2.setClient_id(client_id);
               ebean2.setS1(s1);
               ebean2.setS2(s2);
               ebean2.setS3(s3);
               ebean2.setS4(s4);
               ebean2.setS5(s5);
               ebean2.setS6(s6);
               ebean2.setS7(s7);
               ebean2.setS8(s8);
               ebean2.setS9(s9);
               ebean2.setS10(s10);
               ebean2.setS11(s11);
               ebean2.setS12(s12);
               ebean2.setS13(s13);
               ebean2.setS1_image(s1_image);
               ebean2.setS2_image(s2_image);
               ebean2.setS3_image(s3_image);
               ebean2.setS4_image(s4_image);
               ebean2.setS5_image(s5_image);
               ebean2.setS6_image(s6_image);
               ebean2.setS7_image(s7_image);
               ebean2.setS8_image(s8_image);
               ebean2.setS9_image(s9_image);
               ebean2.setS10_image(s10_image);
               ebean2.setS11_image(s11_image);
               ebean2.setS12_image(s12_image);
               ebean2.setS13_image(s13_image);
               ebean2.setDisabled1(disabled1);
               ebean2.setDisabled2(disabled2);
               ebean2.setDisabled3(disabled3);
               ebean2.setDisabled4(disabled4);
               ebean2.setDisabled5(disabled5);
               ebean2.setDisabled6(disabled6);
               ebean2.setDisabled7(disabled7);
               ebean2.setDisabled8(disabled8);
               ebean2.setDisabled9(disabled9);
               ebean2.setDisabled10(disabled10);
               ebean2.setDisabled11(disabled11);
               ebean2.setDisabled12(disabled12);
               ebean2.setDisabled13(disabled13);
               
               edit_reg2.add(ebean2);
//               System.out.println(session.getAttribute("chosen_session"));
    }
      else{
     positioner++; 
    s1_image=s2_image=s3_image=s4_image=s5_image=s6_image=s7_image=s8_image="images/non.png"; 
    s9_image=s10_image=s11_image=s12_image=s13_image="images/non.png";
//           Set Images
           
               full_name=fname+" "+mname+" "+lname;
               
               String s1_selects="SELECT * FROM attendance_signs WHERE sign_id<='2' ORDER BY sign_id ASC";
               conn.rs2=conn.st2.executeQuery(s1_selects);
               while(conn.rs2.next()){
                 s1+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s2+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s3+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s4+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s5+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s6+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s7+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s8+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s9+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s10+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s11+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s12+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
                 s13+="<option value=\""+conn.rs2.getString(1)+"\" >"+conn.rs2.getString(2)+"</option>";
               }
             
    if(!session.getAttribute("chosen_session_1").toString().contains(",1,")){s1="<option value=\"5\" selected></option>";s1_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",2,")){s2="<option value=\"5\" selected></option>";s2_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",3,")){s3="<option value=\"5\" selected></option>";s3_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",4,")){s4="<option value=\"5\" selected></option>";s4_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",5,")){s5="<option value=\"5\" selected></option>";s5_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",6,")){s6="<option value=\"5\" selected></option>";s6_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",7,")){s7="<option value=\"5\" selected></option>";s7_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",8,")){s8="<option value=\"5\" selected></option>";s8_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",9,")){s9="<option value=\"5\" selected></option>";s9_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",10,")){s10="<option value=\"5\" selected></option>";s10_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",11,")){s11="<option value=\"5\" selected></option>";s11_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",12,")){s12="<option value=\"5\" selected></option>";s12_image="images/warning.png";}
    if(!session.getAttribute("chosen_session_1").toString().contains(",13,") ){s13="<option value=\"5\" selected></option>";s13_image="images/warning.png";}
     
//       if(!session.getAttribute("chosen_session_1").toString().contains(",1,") && !sessions_are.contains(",1,")){s1="<option value=\"5\" selected></option>";s1_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",2,")&& !sessions_are.contains(",2,") ){s2="<option value=\"5\" selected></option>";s2_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",3,")&& !sessions_are.contains(",3,")){s3="<option value=\"5\" selected></option>";s3_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",4,")&& !sessions_are.contains(",4,")){s4="<option value=\"5\" selected></option>";s4_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",5,")&& !sessions_are.contains(",4,")){s5="<option value=\"5\" selected></option>";s5_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",6,")&& !sessions_are.contains(",6,")){s6="<option value=\"5\" selected></option>";s6_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",7,")&& !sessions_are.contains(",7,")){s7="<option value=\"5\" selected></option>";s7_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",8,")&& !sessions_are.contains(",8,") ){s8="<option value=\"5\" selected></option>";s8_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",9,")&& !sessions_are.contains(",9,") ){s9="<option value=\"5\" selected></option>";s9_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",10,")&& !sessions_are.contains(",10,")){s10="<option value=\"5\" selected></option>";s10_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",11,")&& !sessions_are.contains(",11,") ){s11="<option value=\"5\" selected></option>";s11_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",12,")&& !sessions_are.contains(",12,") ){s12="<option value=\"5\" selected></option>";s12_image="images/warning.png";}
//    if(!session.getAttribute("chosen_session_1").toString().contains(",13,")&& !sessions_are.contains(",13,") ){s13="<option value=\"5\" selected></option>";s13_image="images/warning.png";}
//           
 disabled1=disabled2=disabled3=disabled4=disabled5=disabled6=disabled7=disabled8=disabled9=disabled10=disabled11=disabled12=disabled13="NO";
  
               ebean2.setAge(age);
               ebean2.setSex(sex);
               ebean2.setFull_names(full_name);
               ebean2.setPositioner(positioner);
               ebean2.setReg_id("0");
               ebean2.setClient_id(client_id);
               ebean2.setS1(s1);
               ebean2.setS2(s2);
               ebean2.setS3(s3);
               ebean2.setS4(s4);
               ebean2.setS5(s5);
               ebean2.setS6(s6);
               ebean2.setS7(s7);
               ebean2.setS8(s8);
               ebean2.setS9(s9);
               ebean2.setS10(s10);
               ebean2.setS11(s11);
               ebean2.setS12(s12);
               ebean2.setS13(s13);
               ebean2.setS1_image(s1_image);
               ebean2.setS2_image(s2_image);
               ebean2.setS3_image(s3_image);
               ebean2.setS4_image(s4_image);
               ebean2.setS5_image(s5_image);
               ebean2.setS6_image(s6_image);
               ebean2.setS7_image(s7_image);
               ebean2.setS8_image(s8_image);
               ebean2.setS9_image(s9_image);
               ebean2.setS10_image(s10_image);
               ebean2.setS11_image(s11_image);
               ebean2.setS12_image(s12_image);
               ebean2.setS13_image(s13_image);
               ebean2.setDisabled1(disabled1);
               ebean2.setDisabled2(disabled2);
               ebean2.setDisabled3(disabled3);
               ebean2.setDisabled4(disabled4);
               ebean2.setDisabled5(disabled5);
               ebean2.setDisabled6(disabled6);
               ebean2.setDisabled7(disabled7);
               ebean2.setDisabled8(disabled8);
               ebean2.setDisabled9(disabled9);
               ebean2.setDisabled10(disabled10);
               ebean2.setDisabled11(disabled11);
               ebean2.setDisabled12(disabled12);
               ebean2.setDisabled13(disabled13);
      
               edit_reg2.add(ebean2);
//               System.out.println(session.getAttribute("chosen_session"));     
      }
               s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="";
       s1_image=s2_image=s3_image=s4_image=s5_image=s6_image=s7_image=s8_image="";
       s9_image=s10_image=s11_image=s12_image=s13_image="";
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
       
  System.out.println("atended : "+editChecker);
       
       session.setAttribute("edit_reg2", edit_reg2);
       session.setAttribute("total_client", positioner);
       session.setAttribute("client_ids", client_ids);
       response.sendRedirect("edit_attendance.jsp");
       positioner=0;
       client_ids="";
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
            Logger.getLogger(edit_register2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_register2.class.getName()).log(Level.SEVERE, null, ex);
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

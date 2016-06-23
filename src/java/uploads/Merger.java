/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uploads;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import pwp.dbConn2;
import pwp.dbConn3;

/**
 *
 * @author Geofrey Nyabuto
 */
public class Merger {
   dbConn3 conn3 = new dbConn3();
   dbConn2 conn2 = new dbConn2();
   String DBTables=",";
   int found=0;
String[] CurrentTables={"clerks","clients","personal_information","groups","no_group","register","register2","services_provided","service_provider","sessions","users","adherence","prevention_messages","prevention_counseling","hiv_testing_stis","family_planning_tb_pmtct"};
   
public int CheckTables()throws SQLException{
 int found_tables=0;
 
 String allTables="SHOW TABLES FROM pwp_temp";
 conn2.rst=conn2.stt.executeQuery(allTables);
 while(conn2.rst.next()){
   DBTables+=conn2.rst.getString(1); 
 }
for(int i=0;i<CurrentTables.length;i++){
   if(DBTables.contains(CurrentTables[i])) {
   found_tables++;    
   }}
System.out.println("FOUND TABLES  :  "+found_tables);
    return found_tables;
}

// MERGE CLERKS.  
   public int MergeDataClerk() throws SQLException{
    int merged=0;  
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String merger="SELECT * FROM clerks";
    conn2.rst=conn2.stt.executeQuery(merger);
    while(conn2.rst.next()){
        found=0;
    String clerk_id,first_name,sur_name,phone,timestamp;    
     clerk_id=conn2.rst.getString("clerk_id");   
     first_name=conn2.rst.getString("first_name");
     sur_name=conn2.rst.getString("sur_name"); 
     phone=conn2.rst.getString("phone"); 
     timestamp=conn2.rst.getString("timestamp");  
    
     String check="SELECT COUNT(clerk_id) FROM clerks WHERE (clerk_id='"+clerk_id+"') OR (first_name='"+first_name+"' && sur_name='"+sur_name+"')";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getInt(1); 
     }
    
     if(found==0){
         String inserter="INSERT INTO clerks (clerk_id,first_name,sur_name,phone,timestamp) VALUES ('"+clerk_id+"','"+first_name+"','"+sur_name+"','"+phone+"','"+timestamp+"')";
   merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE clerks SET first_name='"+first_name+"',sur_name='"+sur_name+"',phone='"+phone+"',timestamp='"+timestamp+"' WHERE clerk_id='"+clerk_id+"'";
       conn3.st.executeUpdate(updator);
     }
    }
    
    
   return merged;
   }
    
 
   
//   MERGE CLIENTS.
   public String MergeDataClients() throws SQLException{
   int existing=0,merged=0;
    String data="";
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String merger="SELECT * FROM clients";
    conn2.rst=conn2.stt.executeQuery(merger);
    while(conn2.rst.next()){
        found=0;
    String client_id,fname,mname,lname,age,gender,group_id,groupings,district_id,partner_id,lessons_attended,marking_status,year,period,provider_id,timestamp,month,id;    
    String completionyear,completionmonth;
    client_id=conn2.rst.getString("client_id");   
    fname=conn2.rst.getString("fname");
    mname=conn2.rst.getString("mname"); 
    lname=conn2.rst.getString("lname"); 
    age=conn2.rst.getString("age");  
    gender=conn2.rst.getString("gender");   
    group_id=conn2.rst.getString("group_id");
    groupings=conn2.rst.getString("groupings"); 
    district_id=conn2.rst.getString("district_id"); 
    partner_id=conn2.rst.getString("partner_id"); 
    lessons_attended=conn2.rst.getString("lessons_attended");   
    marking_status=conn2.rst.getString("marking_status");
    year=conn2.rst.getString("year"); 
     provider_id=conn2.rst.getString("provider_id");  
     timestamp=conn2.rst.getString("timestamp"); 
     completionyear=conn2.rst.getString("completionyear");
     completionmonth=conn2.rst.getString("completionmonth");
     id=conn2.rst.getString("id");  
     String check="SELECT client_id FROM clients WHERE client_id='"+client_id+"' LIMIT 1";
//     String check="SELECT COUNT(client_id) FROM clients WHERE (client_id='"+client_id+"') OR (fname='"+fname+"' && mname='"+mname+"' && lname='"+lname+"')";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length();
        System.out.println("already exist=========="+client_id);
        String chekifduplicate="SELECT id FROM clients WHERE client_id!='"+client_id+"'";
        conn3.rs=conn3.st.executeQuery(chekifduplicate);
        if(conn3.rs.next()==true){
            String addAsDuplicate="INSERT INTO duplicates(clientid) VALUES ('"+client_id+"')";
            conn3.st2.executeUpdate(addAsDuplicate);
        }
     }
//    System.out.println("client id   :  "+client_id+" found================="+found);
    
     if(found==0){
          System.out.println("new client=========="+client_id);
      String inserter="INSERT INTO clients (client_id,fname,mname,lname,age,gender,group_id,groupings,district_id,partner_id,lessons_attended,marking_status,year,provider_id,timestamp,completionyear,completionmonth)"
                 + " VALUES ('"+client_id+"','"+fname+"','"+mname+"','"+lname+"','"+age+"','"+gender+"','"+group_id+"','"+groupings+"','"+district_id+"','"+partner_id+"','"+lessons_attended+"','"+marking_status+"','"+year+"','"+provider_id+"','"+timestamp+"','"+completionyear+"','"+completionmonth+"')";
   merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE clients SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"',age='"+age+"',gender='"+gender+"',group_id='"+group_id+"',groupings='"+groupings+"',district_id='"+district_id+"',partner_id='"+partner_id+"',lessons_attended='"+lessons_attended+"',marking_status='"+marking_status+"',year='"+year+"',provider_id='"+provider_id+"',timestamp='"+timestamp+"',completionyear='"+completionyear+"',completionmonth='"+completionmonth+"' WHERE client_id='"+client_id+"'";
        existing+=conn3.st.executeUpdate(updator);
     }
    }
    data=merged+"-"+existing;
    
    return data;
   }
   
   //   MERGE GROUPS.
   public String MergeDataGroups() throws SQLException{
   int existing=0,merged=0;   
   String data="";
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM groups";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String group_id,group_name,partner_id,district_id,nhf_id,location,year_formed,timestamp,ward_id;    
     group_id=conn2.rst.getString("group_id");   
     group_name=conn2.rst.getString("group_name");
     partner_id=conn2.rst.getString("partner_id"); 
     district_id=conn2.rst.getString("district_id"); 
     nhf_id=conn2.rst.getString("nhf_id");  
     location=conn2.rst.getString("location");   
     year_formed=conn2.rst.getString("year_formed");  
     timestamp=conn2.rst.getString("timestamp");   
     ward_id=conn2.rst.getString("ward_id");
    
     String check="SELECT group_id FROM groups WHERE group_id='"+group_id+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         String inserter="INSERT INTO groups (group_id,group_name,partner_id,district_id,nhf_id,location,year_formed,timestamp,ward_id)"
                 + " VALUES (?,?,?,?,?,?,?,?,?)";
   conn3.pst=conn3.conn3.prepareStatement(inserter);
   conn3.pst.setString(1, group_id);
   conn3.pst.setString(2, group_name);
   conn3.pst.setString(3, partner_id);
   conn3.pst.setString(4, district_id);
   conn3.pst.setString(5, nhf_id);
   conn3.pst.setString(6, location);
   conn3.pst.setString(7, year_formed);
   conn3.pst.setString(8, timestamp);
   conn3.pst.setString(9, ward_id);
   merged+=conn3.pst.executeUpdate();
     }
     if(found>0){
         String updator="UPDATE groups SET group_name=?,partner_id=?,district_id=?,"
                 + "nhf_id=?,location=?,year_formed=?,timestamp=?,ward_id=? WHERE group_id=?";
   conn3.pst=conn3.conn3.prepareStatement(updator);
   conn3.pst.setString(1, group_name);
   conn3.pst.setString(2, partner_id);
   conn3.pst.setString(3, district_id);
   conn3.pst.setString(4, nhf_id);
   conn3.pst.setString(5, location);
   conn3.pst.setString(6, year_formed);
   conn3.pst.setString(7, timestamp);
   conn3.pst.setString(8, ward_id);
   conn3.pst.setString(9, group_id);
   existing+=conn3.pst.executeUpdate();
     }
    }
  data=merged+"-"+existing;
  return data;  
  }
   
    //   MERGE NO GROUPS.
   public String MergeDataNoGroups() throws SQLException{
    int existing=0,merged=0;   
   String data="";
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM no_group";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String id,name,partner_id,district_id,nhf_id,status,timestamp;    
     id=conn2.rst.getString("id");   
     name=conn2.rst.getString("name");
     partner_id=conn2.rst.getString("partner_id"); 
     district_id=conn2.rst.getString("district_id"); 
     nhf_id=conn2.rst.getString("nhf_id");  
     status=conn2.rst.getString("status");    
     timestamp=conn2.rst.getString("timestamp");   
 
    
     String check="SELECT id FROM no_group WHERE id='"+id+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         String inserter="INSERT INTO no_group (id,name,partner_id,district_id,nhf_id,status,timestamp)"
                 + " VALUES ('"+id+"','"+name+"','"+partner_id+"','"+district_id+"','"+nhf_id+"','"+status+"','"+timestamp+"')";
  merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE no_group SET name='"+name+"',partner_id='"+partner_id+"',district_id='"+district_id+"',nhf_id='"+nhf_id+"',status='"+status+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
         existing+=conn3.st.executeUpdate(updator);
     }
    }
   data+=merged+"-"+existing;
 return data; 
  }
   
   //   MERGE NO REGISTER.
   public String MergeDataRegister() throws SQLException{
    int existing=0,merged=0;   
   String data="";
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM register";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String reg_id,client_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,timestamp;    
     reg_id=conn2.rst.getString("reg_id");   
     client_id=conn2.rst.getString("client_id");
     s1=conn2.rst.getString("s1"); 
     s2=conn2.rst.getString("s2"); 
     s3=conn2.rst.getString("s3");  
     s4=conn2.rst.getString("s4"); 
     s5=conn2.rst.getString("s5");   
     s6=conn2.rst.getString("s6");
     s7=conn2.rst.getString("s7"); 
     s8=conn2.rst.getString("s8"); 
     s9=conn2.rst.getString("s9");  
     s10=conn2.rst.getString("s10");
     s11=conn2.rst.getString("s11");   
     s12=conn2.rst.getString("s12");
     s13=conn2.rst.getString("s13"); 
     timestamp=conn2.rst.getString("timestamp");   
 
    
     String check="SELECT reg_id FROM register WHERE reg_id='"+reg_id+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         String inserter="INSERT INTO register (reg_id,client_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,timestamp)"
                 + " VALUES ('"+reg_id+"','"+client_id+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"','"+s13+"','"+timestamp+"')";
   merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE register SET s1='"+s1+"',s2='"+s2+"',s3='"+s3+"',s4='"+s4+"',s5='"+s5+"',s6='"+s6+"',s7='"+s7+"',s8='"+s8+"',s9='"+s9+"',s10='"+s10+"',s11='"+s11+"',s12='"+s12+"',s13='"+s13+"',timestamp='"+timestamp+"' WHERE reg_id='"+reg_id+"'";
         existing+=conn3.st.executeUpdate(updator);
     }
    }
 data+=merged+"-"+existing;
 return data;
   }
   
    //   MERGE NO SERVICES PROVIDED.
   public String MergeDataServicesProvided() throws SQLException{
   int existing=0,merged=0;   
   String data="";
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM services_provided";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String services_id,client_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,
            tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,
            submission_year,timestamp,session_no;    
     services_id=conn2.rst.getString("services_id");   
     client_id=conn2.rst.getString("client_id");
     contraceptive_method=conn2.rst.getString("contraceptive_method"); 
     rsp=conn2.rst.getString("rsp"); 
     cds_given=conn2.rst.getString("cds_given");  
     screened_tb=conn2.rst.getString("screened_tb"); 
     screened_stis=conn2.rst.getString("screened_stis");   
     tested_partner=conn2.rst.getString("tested_partner");
     tested_children=conn2.rst.getString("tested_children"); 
     disclosed_status=conn2.rst.getString("disclosed_status"); 
     remarks=conn2.rst.getString("remarks");  
     prepared_by=conn2.rst.getString("prepared_by");
     reviewed_by=conn2.rst.getString("reviewed_by");   
     submission_date=conn2.rst.getString("submission_date");
     submission_month=conn2.rst.getString("submission_month"); 
     submission_year=conn2.rst.getString("submission_year"); 
     timestamp=conn2.rst.getString("timestamp");   
    session_no=conn2.rst.getString("session_no"); 
    
     String check="SELECT services_id FROM services_provided WHERE services_id='"+services_id+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         String inserter="INSERT INTO services_provided (services_id,client_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status,remarks,prepared_by,reviewed_by,submission_date,submission_month,submission_year,timestamp,session_no)"
                 + " VALUES ('"+services_id+"','"+client_id+"','"+contraceptive_method+"','"+rsp+"','"+cds_given+"','"+screened_tb+"','"+screened_stis+"','"+tested_partner+"','"+tested_children+"','"+disclosed_status+"','"+remarks+"','"+prepared_by+"','"+reviewed_by+"','"+submission_date+"','"+submission_month+"','"+submission_year+"','"+timestamp+"','"+session_no+"')";
 merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE services_provided SET contraceptive_method='"+contraceptive_method+"',rsp='"+rsp+"',cds_given='"+cds_given+"',screened_tb='"+screened_tb+"',screened_stis='"+screened_stis+"',tested_partner='"+tested_partner+"',tested_children='"+tested_children+"',disclosed_status='"+disclosed_status+"',remarks='"+remarks+"',prepared_by='"+prepared_by+"',reviewed_by='"+reviewed_by+"',submission_date='"+submission_date+"',submission_month='"+submission_month+"',submission_year='"+submission_year+"',timestamp='"+timestamp+"',session_no='"+session_no+"' WHERE services_id='"+services_id+"'";
        existing+=conn3.st.executeUpdate(updator);
     }
    }
    
 data+=merged+"-"+existing;
 return data;
   }
   
    //   MERGE SERVICE PROVIDER.
   public String MergeDataServiceProvider() throws SQLException{
  int existing=0,merged=0;   
   String data="";
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM service_provider";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String provider_id,fname,mname,lname,phone,group_ids,partner_id,district_id,national_id,timestamp;    
     provider_id=conn2.rst.getString("provider_id");   
     fname=conn2.rst.getString("fname");
     mname=conn2.rst.getString("mname"); 
     lname=conn2.rst.getString("lname"); 
     phone=conn2.rst.getString("phone");  
     group_ids=conn2.rst.getString("group_ids"); 
     partner_id=conn2.rst.getString("partner_id");   
     district_id=conn2.rst.getString("district_id"); 
     national_id=conn2.rst.getString("national_id"); 
     timestamp=conn2.rst.getString("timestamp");   
 
    
     String check="SELECT provider_id FROM service_provider WHERE provider_id='"+provider_id+"'";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         String inserter="INSERT INTO service_provider (provider_id,fname,mname,lname,phone,group_ids,partner_id,district_id,national_id,timestamp)"
                 + " VALUES (?,?,?,?,?,?,?,?,?,?)";
    conn3.pst=conn3.conn3.prepareStatement(inserter);
    conn3.pst.setString(1, provider_id);
    conn3.pst.setString(2, fname);
    conn3.pst.setString(3, mname);
    conn3.pst.setString(4, lname);
    conn3.pst.setString(5, phone);
    conn3.pst.setString(6, group_ids);
    conn3.pst.setString(7, partner_id);
    conn3.pst.setString(8, district_id);
    conn3.pst.setString(9, national_id);
    conn3.pst.setString(10, timestamp);
     
    merged+=conn3.pst.executeUpdate();
    
    
    
    
     }
     if(found>0){
         String updator="UPDATE service_provider SET fname=?,mname=?,lname=?,phone=?,group_ids=?,"
                 + "partner_id=?,district_id=?,national_id=?,timestamp=? WHERE provider_id=?";
        conn3.pst=conn3.conn3.prepareStatement(updator);
    conn3.pst.setString(1, fname);
    conn3.pst.setString(2, mname);
    conn3.pst.setString(3, lname);
    conn3.pst.setString(4, phone);
    conn3.pst.setString(5, group_ids);
    conn3.pst.setString(6, partner_id);
    conn3.pst.setString(7, district_id);
    conn3.pst.setString(8, national_id);
    conn3.pst.setString(9, timestamp);
    conn3.pst.setString(10, provider_id);
    
         existing+=conn3.pst.executeUpdate();
     }
    }
 data+=merged+"-"+existing;
 return data;
   }
  
    //   MERGE  SESSIONS.
   public String MergeDataSessions() throws SQLException{
  int existing=0,merged=0;   
   String data=""; 
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM sessions";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String sessions_id,group_id,groupings,session_id,session_date ,message_id,method_id,time_taken
            ,male_condoms,female_condoms,no_iec,partner_id,district_id,year,period,timestamp,month;    
     sessions_id=conn2.rst.getString("sessions_id");   
     group_id=conn2.rst.getString("group_id");
     groupings=conn2.rst.getString("groupings"); 
     session_id=conn2.rst.getString("session_id"); 
     session_date=conn2.rst.getString("session_date");  
     message_id=conn2.rst.getString("message_id"); 
     method_id=conn2.rst.getString("method_id");   
     time_taken=conn2.rst.getString("time_taken"); 
     male_condoms=conn2.rst.getString("male_condoms"); 
     female_condoms=conn2.rst.getString("female_condoms");   
     no_iec=conn2.rst.getString("no_iec");
     partner_id=conn2.rst.getString("partner_id"); 
     district_id=conn2.rst.getString("district_id"); 
     year=conn2.rst.getString("year");  
     timestamp=conn2.rst.getString("timestamp"); 
     String check="SELECT sessions_id FROM sessions WHERE sessions_id='"+sessions_id+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         String inserter="INSERT INTO sessions (sessions_id,group_id,groupings,session_id,session_date,message_id,method_id,time_taken,male_condoms,female_condoms,no_iec,partner_id,district_id,year,timestamp)"
                 + " VALUES ('"+sessions_id+"','"+group_id+"','"+groupings+"','"+session_id+"','"+session_date+"','"+message_id+"','"+method_id+"','"+time_taken+"','"+male_condoms+"','"+female_condoms+"','"+no_iec+"','"+partner_id+"','"+district_id+"','"+year+"','"+timestamp+"')";
    merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE sessions SET group_id='"+group_id+"',groupings='"+groupings+"',session_id='"+session_id+"',session_date='"+session_date+"',message_id='"+message_id+"',method_id='"+method_id+"',time_taken='"+time_taken+"',male_condoms='"+male_condoms+"',female_condoms='"+female_condoms+"',no_iec='"+no_iec+"',partner_id='"+partner_id+"',district_id='"+district_id+"',year='"+year+"',timestamp='"+timestamp+"' WHERE sessions_id='"+sessions_id+"'";
     existing+=conn3.st.executeUpdate(updator);
     }
    }
 data+=merged+"-"+existing;
 return data;
   }  
    //   MERGE SERVICE PROVIDER.
   public String MergeDataUsers() throws SQLException{
 int existing=0,merged=0;   
   String data="";
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM users";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String userid,username,password,level,timestamp;    
     userid=conn2.rst.getString("userid");   
     username=conn2.rst.getString("username");
     password=conn2.rst.getString("password"); 
     level=conn2.rst.getString("level"); 
     timestamp=conn2.rst.getString("timestamp");     
 
  
     String check="SELECT COUNT(userid) FROM users WHERE userid='"+userid+"' || username='"+username+"'";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getInt(1); 
     }
    
     if(found==0){
        System.out.println(userid);   
         String inserter="INSERT INTO users (userid,username,password,level,timestamp)"
                 + " VALUES ('"+userid+"','"+username+"','"+password+"','"+level+"','"+timestamp+"')";
    merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE users SET username='"+username+"',password='"+password+"',level='"+level+"',timestamp='"+timestamp+"' WHERE userid='"+userid+"'";
    existing+=conn3.st.executeUpdate(updator);
     }
    }
    
 data+=merged+"-"+existing;
 return data;
  }
  //   MERGE REGISTER2.
   public String MergeRegister2() throws SQLException{
   int existing=0,merged=0;   
   String data="";
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String selector="SELECT * FROM register2";
    conn2.rst=conn2.stt.executeQuery(selector);
    while(conn2.rst.next()){
        found=0;
    String reg_id,client_id,session_no,value,date,date_key,month,year,timestamp;    
     reg_id=conn2.rst.getString("reg_id");   
     client_id=conn2.rst.getString("client_id");
     session_no=conn2.rst.getString("session_no"); 
     value=conn2.rst.getString("value");
     date=conn2.rst.getString("date");   
     date_key=conn2.rst.getString("datekey");
     month=conn2.rst.getString("month");
     year=conn2.rst.getString("year"); 
     timestamp=conn2.rst.getString("timestamp");     
 
  
     String check="SELECT reg_id FROM register2 WHERE reg_id='"+reg_id+"' && session_no='"+session_no+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
        
         String inserter="INSERT INTO register2 (reg_id,client_id,session_no,value,date,datekey,month,year,timestamp)"
                 + " VALUES ('"+reg_id+"','"+client_id+"','"+session_no+"','"+value+"','"+date+"','"+date_key+"','"+month+"','"+year+"','"+timestamp+"')";
    merged+=conn3.st.executeUpdate(inserter);
     }
     if(found>0){
         String updator="UPDATE register2 SET client_id='"+client_id+"',session_no='"+session_no+"',value='"+value+"',date='"+date+"',datekey='"+date_key+"',month='"+month+"',year='"+year+"',timestamp='"+timestamp+"' WHERE reg_id='"+reg_id+"'&& session_no='"+session_no+"'";
   existing+=conn3.st.executeUpdate(updator);
     }
    }
    data=merged+"-"+existing;
 return data;
   }  
   
  // MERGE CLERKS.  
   
   public String MergePersonalInformation() throws SQLException{
    int merged=0,existing=0;  
    
//    CONNECT TO THE TEMPORARY DATABASE AND SELECT THE DATA TO BE MERGED.
    String merger="SELECT * FROM personal_information";
    conn2.rst=conn2.stt.executeQuery(merger);
    while(conn2.rst.next()){
        found=0;
    String client_id,fname,mname,lname,district_id,location,national_id,mobile_no,gender,dob,marital_status,
            employment_status,education_level,under_18s,ovc_children,group_id,provider_id,partner_id,
            hiv_year,art_status,hf_id,ccc_no,registration_date,approved_by,designation,approval_date,
            lessons_attended,completionyear,completionmonth,status,timestamp,dic_id,ward_id,linked_group;
    
    client_id=conn2.rst.getString("client_id");
    fname=conn2.rst.getString("fname");
    mname=conn2.rst.getString("mname");
    lname=conn2.rst.getString("lname");
    district_id=conn2.rst.getString("district_id");
    location=conn2.rst.getString("location");
    national_id=conn2.rst.getString("national_id");
    mobile_no=conn2.rst.getString("mobile_no");
    gender=conn2.rst.getString("gender");
    dob=conn2.rst.getString("dob");
    marital_status=conn2.rst.getString("marital_status");
    employment_status=conn2.rst.getString("employment_status");
    education_level=conn2.rst.getString("education_level");
    under_18s=conn2.rst.getString("under_18s");
    ovc_children=conn2.rst.getString("ovc_children");
    group_id=conn2.rst.getString("group_id");
    provider_id=conn2.rst.getString("provider_id");
    partner_id=conn2.rst.getString("partner_id");
    hiv_year=conn2.rst.getString("hiv_year");
    art_status=conn2.rst.getString("art_status");
    hf_id=conn2.rst.getString("hf_id");
    ccc_no=conn2.rst.getString("ccc_no");
    registration_date=conn2.rst.getString("registration_date");
    approved_by=conn2.rst.getString("approved_by");
    designation=conn2.rst.getString("designation");
    approval_date=conn2.rst.getString("approval_date");
    lessons_attended=conn2.rst.getString("lessons_attended");
    completionyear=conn2.rst.getString("completionyear");
    completionmonth=conn2.rst.getString("completionmonth");
    status=conn2.rst.getString("status");
    timestamp=conn2.rst.getString("timestamp");
    dic_id=conn2.rst.getString("dic_id");
    ward_id=conn2.rst.getString("ward_id");
    linked_group=conn2.rst.getString("linked_group");
     String check="SELECT client_id FROM personal_information WHERE client_id='"+client_id+"' LIMIT 1";
     conn3.rs=conn3.st.executeQuery(check);
     if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length(); 
     }
    
     if(found==0){
         System.out.println("ward_id_"+ward_id+"_");
String add_Client="INSERT INTO personal_information"
+ "(client_id,fname,mname,lname,district_id,location,national_id,mobile_no,gender,dob,marital_status,"
+ "employment_status,education_level,under_18s,ovc_children,group_id,provider_id,partner_id,"
+ "hiv_year,art_status,hf_id,ccc_no,registration_date,approved_by,designation,approval_date,"
        + "lessons_attended,completionyear,completionmonth,status,timestamp,dic_id,ward_id,linked_group)"
+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          conn3.pst=conn3.conn3.prepareStatement(add_Client);
          conn3.pst.setString(1, client_id);
          conn3.pst.setString(2, fname);
          conn3.pst.setString(3, mname);
          conn3.pst.setString(4, lname);
          conn3.pst.setString(5, district_id);
          conn3.pst.setString(6, location);
          conn3.pst.setString(7, national_id);
          conn3.pst.setString(8, mobile_no);
          conn3.pst.setString(9, gender);
          conn3.pst.setString(10, dob);
          conn3.pst.setString(11, marital_status);
          conn3.pst.setString(12, employment_status);
          conn3.pst.setString(13, education_level);
          conn3.pst.setString(14, under_18s);
          conn3.pst.setString(15, ovc_children);
          conn3.pst.setString(16, group_id);
          conn3.pst.setString(17, provider_id);
          conn3.pst.setString(18, partner_id);
          conn3.pst.setString(19, hiv_year);
          conn3.pst.setString(20, art_status);
          conn3.pst.setString(21, hf_id);
          conn3.pst.setString(22, ccc_no);
          conn3.pst.setString(23, registration_date);
          conn3.pst.setString(24, approved_by);
          conn3.pst.setString(25, designation);
          conn3.pst.setString(26, approval_date);
          conn3.pst.setString(27, lessons_attended);
          conn3.pst.setString(28, completionyear);
          conn3.pst.setString(29, completionmonth);
          conn3.pst.setString(30, status);
          conn3.pst.setString(31, timestamp);
          conn3.pst.setString(32, dic_id);
          conn3.pst.setString(33, ward_id);
          conn3.pst.setString(34, linked_group);
          
          merged+=conn3.pst.executeUpdate();
     }
     if(found>0){
  String UpdateClient="UPDATE personal_information SET "
+ "fname=?,mname=?,lname=?,district_id=?,location=?,national_id=?,mobile_no=?,gender=?,dob=?,marital_status=?,"
+ "employment_status=?,education_level=?,under_18s=?,ovc_children=?,group_id=?,provider_id=?,partner_id=?,"
+ "hiv_year=?,art_status=?,hf_id=?,ccc_no=?,registration_date=?,approved_by=?,designation=?,approval_date=?,"
+ "status=?,timestamp=?,lessons_attended=?,completionyear=?,completionmonth=?,dic_id=?,ward_id=?,linked_group=? "
    + "WHERE client_id=?";
          conn3.pst=conn3.conn3.prepareStatement(UpdateClient);
          conn3.pst.setString(1, fname);
          conn3.pst.setString(2, mname);
          conn3.pst.setString(3, lname);
          conn3.pst.setString(4, district_id);
          conn3.pst.setString(5, location);
          conn3.pst.setString(6, national_id);
          conn3.pst.setString(7, mobile_no);
          conn3.pst.setString(8, gender);
          conn3.pst.setString(9, dob);
          conn3.pst.setString(10, marital_status);
          conn3.pst.setString(11, employment_status);
          conn3.pst.setString(12, education_level);
          conn3.pst.setString(13, under_18s);
          conn3.pst.setString(14, ovc_children);
          conn3.pst.setString(15, group_id);
          conn3.pst.setString(16, provider_id);
          conn3.pst.setString(17, partner_id);
          conn3.pst.setString(18, hiv_year);
          conn3.pst.setString(19, art_status);
          conn3.pst.setString(20, hf_id);
          conn3.pst.setString(21, ccc_no);
          conn3.pst.setString(22, registration_date);
          conn3.pst.setString(23, approved_by);
          conn3.pst.setString(24, designation);
          conn3.pst.setString(25, approval_date);
          conn3.pst.setString(26, status);
          conn3.pst.setString(27, timestamp);
          conn3.pst.setString(28, lessons_attended);
          conn3.pst.setString(29, completionyear);
          conn3.pst.setString(30, completionmonth);
          conn3.pst.setString(31, dic_id);
          conn3.pst.setString(32, ward_id);
          conn3.pst.setString(33, linked_group);
          conn3.pst.setString(34, client_id);
          
          existing+=conn3.pst.executeUpdate();
     }
    }
   String allData=merged+"-"+existing; 
    
   return allData;
   }
   
     public String MergePreventionMessage() throws SQLException{
    int merged=0,existing=0; 
  String date_of_assessment,id,client_id;
String knowledge_of_hiv,partner_hiv_testing,any_child_tested,any_child_not_tested,discordance,hiv_disclosure;
String abstinence,faithful_to_one_partner,safer_sex_methods,multiple_sex_partner,condom_use,alcohol_substance_abuse;
String adherence_to_arv,adherence_to_others,asking_stis_questions,family_planning;
String planning_to_have_children,screened_for_TB,timestamp; 

    String getPreventionMessage="SELECT * FROM prevention_messages";
        conn2.rst=conn2.stt.executeQuery(getPreventionMessage);
    while(conn2.rst.next()){
        found=0;
  id=conn2.rst.getString("id");
  client_id=conn2.rst.getString("client_id");
  date_of_assessment=conn2.rst.getString("date_of_assessment");
  knowledge_of_hiv=conn2.rst.getString("knowledge_of_hiv");
 partner_hiv_testing=conn2.rst.getString("partner_hiv_testing");
 any_child_tested=conn2.rst.getString("any_child_tested");
 any_child_not_tested=conn2.rst.getString("any_child_not_tested");
 discordance=conn2.rst.getString("discordance");
 hiv_disclosure=conn2.rst.getString("hiv_disclosure");
 abstinence=conn2.rst.getString("abstinence");
faithful_to_one_partner=conn2.rst.getString("faithful_to_one_partner");
safer_sex_methods=conn2.rst.getString("safer_sex_methods");
multiple_sex_partner=conn2.rst.getString("multiple_sex_partner");
condom_use=conn2.rst.getString("condom_use");
alcohol_substance_abuse=conn2.rst.getString("alcohol_substance_abuse");
adherence_to_arv=conn2.rst.getString("adherence_to_arv");
adherence_to_others=conn2.rst.getString("adherence_to_others");
asking_stis_questions=conn2.rst.getString("asking_stis_questions");
family_planning=conn2.rst.getString("family_planning");
planning_to_have_children=conn2.rst.getString("planning_to_have_children");
screened_for_TB =conn2.rst.getString("screened_for_TB");     
timestamp =conn2.rst.getString("timestamp");       
   
//   CHECKexistence========================================================
String checker="SELECT id FROM prevention_messages WHERE id='"+id+"' LIMIT 1";
conn3.rs=conn3.st.executeQuery(checker);
  if(conn3.rs.next()==true){
      found=conn3.rs.getString(1).length();
  }
  
  if (found>0){
//      UPDATE THE ASSESSMENT FOR PREVENTION MESSAGES=====================
String addPreventionMessages="UPDATE prevention_messages SET date_of_assessment=?,knowledge_of_hiv=?,"
+"partner_hiv_testing=?,any_child_tested=?,any_child_not_tested=?,discordance=?,hiv_disclosure=?,abstinence=?,"
+ "faithful_to_one_partner=?,safer_sex_methods=?,multiple_sex_partner=?,condom_use=?,alcohol_substance_abuse=?,"
+ "adherence_to_arv=?,adherence_to_others=?,asking_stis_questions=?,family_planning=?,"
+ "planning_to_have_children=?,screened_for_TB =?,timestamp=? WHERE id=?";
 conn3.pst=conn3.conn3.prepareStatement(addPreventionMessages);

  conn3.pst.setString(1,date_of_assessment );
  conn3.pst.setString(2,knowledge_of_hiv );
  conn3.pst.setString(3,partner_hiv_testing );
  conn3.pst.setString(4,any_child_tested );
  conn3.pst.setString(5,any_child_not_tested );
  conn3.pst.setString(6,discordance );
  conn3.pst.setString(7,hiv_disclosure );
  conn3.pst.setString(8,abstinence );
  conn3.pst.setString(9,faithful_to_one_partner );
  conn3.pst.setString(10,safer_sex_methods );
  conn3.pst.setString(11,multiple_sex_partner );
  conn3.pst.setString(12,condom_use );
  conn3.pst.setString(13,alcohol_substance_abuse );
  conn3.pst.setString(14,adherence_to_arv );
  conn3.pst.setString(15,adherence_to_others );
  conn3.pst.setString(16,asking_stis_questions );
  conn3.pst.setString(17,family_planning );
  conn3.pst.setString(18,planning_to_have_children );
  conn3.pst.setString(19,screened_for_TB );
  conn3.pst.setString(20,timestamp); 
  conn3.pst.setString(21,id );
  
  existing+=conn3.pst.executeUpdate();
  }
  else if(found==0){
 String addPreventionMessages="INSERT INTO prevention_messages (id,client_id,date_of_assessment,knowledge_of_hiv,"
 + "partner_hiv_testing,any_child_tested,any_child_not_tested,discordance,hiv_disclosure,abstinence,"
+ "faithful_to_one_partner,safer_sex_methods,multiple_sex_partner,condom_use,alcohol_substance_abuse,"
+ "adherence_to_arv,adherence_to_others,asking_stis_questions,family_planning,"
+ "planning_to_have_children,screened_for_TB,timestamp) "
+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 conn3.pst=conn3.conn3.prepareStatement(addPreventionMessages);
  conn3.pst.setString(1,id );
  conn3.pst.setString(2,client_id );
  conn3.pst.setString(3,date_of_assessment );
  conn3.pst.setString(4,knowledge_of_hiv );
  conn3.pst.setString(5,partner_hiv_testing );
  conn3.pst.setString(6,any_child_tested );
  conn3.pst.setString(7,any_child_not_tested );
  conn3.pst.setString(8,discordance );
  conn3.pst.setString(9,hiv_disclosure );
  conn3.pst.setString(10,abstinence );
  conn3.pst.setString(11,faithful_to_one_partner );
  conn3.pst.setString(12,safer_sex_methods );
  conn3.pst.setString(13,multiple_sex_partner );
  conn3.pst.setString(14,condom_use );
  conn3.pst.setString(15,alcohol_substance_abuse );
  conn3.pst.setString(16,adherence_to_arv );
  conn3.pst.setString(17,adherence_to_others );
  conn3.pst.setString(18,asking_stis_questions );
  conn3.pst.setString(19,family_planning );
  conn3.pst.setString(20,planning_to_have_children );
  conn3.pst.setString(21,screened_for_TB );
  conn3.pst.setString(22,timestamp );
  
  merged+=conn3.pst.executeUpdate();     
  }
  else{}
    }
    String data=merged+"-"+existing;
    return data;
    }
        
     public String MergePreventionCounselling() throws SQLException{
    int merged=0,existing=0; 
    String id,prevention_message_id,hiv_disclosure,safer_sex_methods,alcohol_use,adherence_to_arvs;
String adherence_other_medications,couples_counseling,timestamp;

String getPreventionCounselling="SELECT * FROM prevention_counseling";
conn2.rst=conn2.stt.executeQuery(getPreventionCounselling);
while(conn2.rst.next()){
    found=0;
 id=conn2.rst.getString("id");
 prevention_message_id=conn2.rst.getString("prevention_message_id");
 hiv_disclosure=conn2.rst.getString("hiv_disclosure");
 safer_sex_methods=conn2.rst.getString("safer_sex_methods");
 alcohol_use=conn2.rst.getString("alcohol_use");
 adherence_to_arvs=conn2.rst.getString("adherence_to_arvs");
 adherence_other_medications=conn2.rst.getString("adherence_other_medications");
 couples_counseling=conn2.rst.getString("couples_counseling");
 timestamp =conn2.rst.getString("timestamp");
    
 String checker="SELECT id FROM prevention_counseling WHERE id='"+id+"' LIMIT 1";
 conn3.rs=conn3.st.executeQuery(checker);
 if(conn3.rs.next()==true){found=conn3.rs.getString(1).length(); }
   if(found==0) {
        String addPreventionCounseling="INSERT INTO prevention_counseling(id,prevention_message_id,hiv_disclosure,"
    + "safer_sex_methods,alcohol_use,adherence_to_arvs,adherence_other_medications,couples_counseling,timestamp)"
      + " VALUES(?,?,?,?,?,?,?,?,?)";
 conn3.pst=conn3.conn3.prepareStatement(addPreventionCounseling);
 conn3.pst.setString(1, id);
 conn3.pst.setString(2, prevention_message_id);
 conn3.pst.setString(3, hiv_disclosure);
 conn3.pst.setString(4, safer_sex_methods);
 conn3.pst.setString(5, alcohol_use);
 conn3.pst.setString(6, adherence_to_arvs);
 conn3.pst.setString(7, adherence_other_medications);
 conn3.pst.setString(8, couples_counseling);
 conn3.pst.setString(9, timestamp);
 
 merged+=conn3.pst.executeUpdate();
   }
   else if(found>0){
       
        String addPreventionCounseling="UPDATE prevention_counseling SET hiv_disclosure=?,"
+ "safer_sex_methods=?,alcohol_use=?,adherence_to_arvs=?,adherence_other_medications=?,couples_counseling=?,timestamp=?"
+ " WHERE id=?";
 conn3.pst=conn3.conn3.prepareStatement(addPreventionCounseling);

 
 conn3.pst.setString(1, hiv_disclosure);
 conn3.pst.setString(2, safer_sex_methods);
 conn3.pst.setString(3, alcohol_use);
 conn3.pst.setString(4, adherence_to_arvs);
 conn3.pst.setString(5, adherence_other_medications);
 conn3.pst.setString(6, couples_counseling);
 conn3.pst.setString(7, timestamp);
 conn3.pst.setString(8, id);
 
 existing+=conn3.pst.executeUpdate();
   }
   else{}
    
}
String data=merged+"-"+existing;
       return data;
     }
     
        
     public String MergeHIVTestingSTIs() throws SQLException{
    int merged=0,existing=0; 
    String partner_tested,children_tested,referral_for_sti,risk_reduction_info,treatment_adherence,condoms_provided;
    String prevention_message_id,id,timestamp;
    String getAllData="SELECT * FROM hiv_testing_stis";
    conn2.rst=conn2.stt.executeQuery(getAllData);
    while(conn2.rst.next()){
        found=0;
      id=conn2.rst.getString("id");
      prevention_message_id=conn2.rst.getString("prevention_message_id");
      partner_tested=conn2.rst.getString("partner_tested");
      children_tested=conn2.rst.getString("children_tested");
      referral_for_sti=conn2.rst.getString("referral_for_sti");
      risk_reduction_info=conn2.rst.getString("risk_reduction_info");
      treatment_adherence=conn2.rst.getString("treatment_adherence");
      condoms_provided=conn2.rst.getString("condoms_provided");  
      timestamp=conn2.rst.getString("timestamp");
      
      String checker="SELECT id FROM hiv_testing_stis WHERE id='"+id+"' LIMIT 1";
      conn3.rs=conn3.st.executeQuery(checker);
      if(conn3.rs.next()==true){found=conn3.rs.getString(1).length();}
      
      if(found==0){
//       ADD THE DATA TO THE DATABASE====================   
    String addHIV="INSERT INTO hiv_testing_stis(id,prevention_message_id,partner_tested,children_tested,"
         + "referral_for_sti,risk_reduction_info,treatment_adherence,condoms_provided,timestamp)"
         + " VALUES(?,?,?,?,?,?,?,?,?)";
 
 conn3.pst=conn3.conn3.prepareStatement(addHIV);
 conn3.pst.setString(1, id);
 conn3.pst.setString(2, prevention_message_id);
 conn3.pst.setString(3, partner_tested);
 conn3.pst.setString(4, children_tested);
 conn3.pst.setString(5, referral_for_sti);
 conn3.pst.setString(6, risk_reduction_info);
 conn3.pst.setString(7, treatment_adherence);
 conn3.pst.setString(8, condoms_provided);
 conn3.pst.setString(9, timestamp);
 
 merged+=conn3.pst.executeUpdate();       
          
      }
      else if(found>0){
//     UPDATE THE SYSTEM DATA================================     
    
           String addHIV="UPDATE hiv_testing_stis SET partner_tested=?,children_tested=?,"
         + "referral_for_sti=?,risk_reduction_info=?,treatment_adherence=?,condoms_provided=?,timestamp=?"
         + " WHERE id=?";
 
 conn3.pst=conn3.conn3.prepareStatement(addHIV);
 
 conn3.pst.setString(1, partner_tested);
 conn3.pst.setString(2, children_tested);
 conn3.pst.setString(3, referral_for_sti);
 conn3.pst.setString(4, risk_reduction_info);
 conn3.pst.setString(5, treatment_adherence);
 conn3.pst.setString(6, condoms_provided);
 conn3.pst.setString(7, timestamp);
 conn3.pst.setString(8, id);
 
 existing+=conn3.pst.executeUpdate();
          
      }
    }
    String data=merged+"-"+existing;
       return data;
     }
     
        
     public String MergeFamilyPlanningTBPMTCT() throws SQLException{
    int merged=0,existing=0; 
    String pregnancy_status,hormonal_contraceptive,condoms,pregnancy_counseling,transmission_risks;
String tb_screening,referred_tb_diagnosis,referred_pmtct_services,other_referrals,referral_point,timestamp;
 String id,prevention_message_id;
String getAll="SELECT * FROM family_planning_tb_pmtct";
conn2.rst=conn2.stt.executeQuery(getAll);
while(conn2.rst.next()){
    found=0;
    id=conn2.rst.getString("id");
    prevention_message_id=conn2.rst.getString("prevention_message_id");
    pregnancy_status=conn2.rst.getString("pregnancy_status");
    hormonal_contraceptive=conn2.rst.getString("hormonal_contraceptive");
    condoms=conn2.rst.getString("condoms");
    pregnancy_counseling=conn2.rst.getString("pregnancy_counseling");
    transmission_risks=conn2.rst.getString("transmission_risks");
    tb_screening=conn2.rst.getString("tb_screening");
    referred_tb_diagnosis=conn2.rst.getString("referred_tb_diagnosis");
    referred_pmtct_services=conn2.rst.getString("referred_pmtct_services");
    other_referrals=conn2.rst.getString("other_referrals");
    referral_point=conn2.rst.getString("referral_point");
    timestamp=conn2.rst.getString("timestamp");
    
    String checker="SELECT id FROM family_planning_tb_pmtct WHERE id='"+id+"' LIMIT 1";
    conn3.rs=conn3.st.executeQuery(checker);
    if(conn3.rs.next()==true){found=conn3.rs.getString(1).length();}
   if(found==0){
   
        String addFamilyPlanning="INSERT INTO family_planning_tb_pmtct (id,prevention_message_id,pregnancy_status,"
         + "hormonal_contraceptive,condoms,pregnancy_counseling,transmission_risks,tb_screening,"
         + "referred_tb_diagnosis,referred_pmtct_services,other_referrals,referral_point,timestamp) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

 conn3.pst=conn3.conn3.prepareStatement(addFamilyPlanning);
 conn3.pst.setString(1, id);
 conn3.pst.setString(2, prevention_message_id);
 conn3.pst.setString(3, pregnancy_status);
 conn3.pst.setString(4, hormonal_contraceptive);
 conn3.pst.setString(5, condoms);
 conn3.pst.setString(6, pregnancy_counseling);
 conn3.pst.setString(7, transmission_risks);
 conn3.pst.setString(8, tb_screening);
 conn3.pst.setString(9, referred_tb_diagnosis);
 conn3.pst.setString(10, referred_pmtct_services);
 conn3.pst.setString(11, other_referrals);
 conn3.pst.setString(12, referral_point);
 conn3.pst.setString(13, timestamp);
 
 merged+=conn3.pst.executeUpdate();
   } 
   else if(found>0){
     String addFamilyPlanning="UPDATE family_planning_tb_pmtct SET pregnancy_status=?,"
         + "hormonal_contraceptive=?,condoms=?,pregnancy_counseling=?,transmission_risks=?,tb_screening=?,"
         + "referred_tb_diagnosis=?,referred_pmtct_services=?,other_referrals=?,referral_point=?,timestamp=? WHERE id=?";

 conn3.pst=conn3.conn3.prepareStatement(addFamilyPlanning);
 conn3.pst.setString(1, pregnancy_status);
 conn3.pst.setString(2, hormonal_contraceptive);
 conn3.pst.setString(3, condoms);
 conn3.pst.setString(4, pregnancy_counseling);
 conn3.pst.setString(5, transmission_risks);
 conn3.pst.setString(6, tb_screening);
 conn3.pst.setString(7, referred_tb_diagnosis);
 conn3.pst.setString(8, referred_pmtct_services);
 conn3.pst.setString(9, other_referrals);
 conn3.pst.setString(10, referral_point);
 conn3.pst.setString(11, timestamp);
 conn3.pst.setString(12, id);
 
 existing+=conn3.pst.executeUpdate();   
       
   }
    
}
String data=merged+"-"+existing;

return data;
 }
 
      public String MergeAdherence() throws SQLException{
    int merged=0,existing=0; 
   String id,client_id,session_no,status,date_of_session,timestamp;
    String getAdherence="SELECT * FROM adherence";
    conn2.rst=conn2.stt.executeQuery(getAdherence);
    while(conn2.rst.next()){
        found=0;
    id=conn2.rst.getString("id");
    client_id=conn2.rst.getString("client_id");
    session_no=conn2.rst.getString("session_no");
    status=conn2.rst.getString("status");
    date_of_session=conn2.rst.getString("date_of_session");
    timestamp=conn2.rst.getString("timestamp");  
    
    String checker="SELECT id FROM adherence WHERE id='"+id+"'";
    conn3.rs=conn3.st.executeQuery(checker);
    if(conn3.rs.next()==true){
        found=conn3.rs.getString(1).length();
    }
    if(found==0){
//    insert data=======================================
        String inserter="INSERT INTO adherence (id,client_id,session_no,status,date_of_session,timestamp) "
                + "VALUES('"+id+"','"+client_id+"','"+session_no+"','"+status+"','"+date_of_session+"','"+timestamp+"')";
        merged+=conn3.st.executeUpdate(inserter);
    }   
    
    else{
//      update data here===============================
           String inserter="UPDATE adherence SET client_id='"+client_id+"',session_no='"+session_no+"',"
 + "status='"+status+"',date_of_session='"+date_of_session+"',timestamp='"+timestamp+"' WHERE id='"+id+"'";
        existing+=conn3.st.executeUpdate(inserter);
    }
    }
   String data=merged+" "+existing;
   
    return data;
    }
      
      
      public int deleteClients() throws SQLException{
          String client_id="",assessmentId="";
          int totalDeleted=0;
          
          String getDeleted="SELECT * FROM deletedclients";
          conn2.rst=conn2.stt.executeQuery(getDeleted);
          while(conn2.rst.next()){
            client_id=conn2.rst.getString("client_id");
            
            System.out.println("to delete client : "+client_id);
            
          conn3.st.executeUpdate("DELETE FROM adherence WHERE client_id='"+client_id+"'");    
          totalDeleted+=conn3.st.executeUpdate("DELETE FROM personal_information WHERE client_id='"+client_id+"'");
          conn3.st.executeUpdate("DELETE FROM register WHERE client_id='"+client_id+"'");
          conn3.st.executeUpdate("DELETE FROM register2 WHERE client_id='"+client_id+"'");
          conn3.st.executeUpdate("DELETE FROM services_provided WHERE client_id='"+client_id+"'");
          
          
          String getAssess="SELECT id FROM prevention_messages  WHERE client_id='"+client_id+"'";
          conn3.rs=conn3.st.executeQuery(getAssess);
          if(conn3.rs.next()==true){
          assessmentId=conn3.rs.getString(1);
          conn3.st1.executeUpdate("DELETE FROM prevention_counseling WHERE id='"+assessmentId+"'");
          conn3.st1.executeUpdate("DELETE FROM hiv_testing_stis WHERE id='"+assessmentId+"'");
          conn3.st1.executeUpdate("DELETE FROM family_planning_tb_pmtct WHERE id='"+assessmentId+"'");     
          }
          conn3.st.executeUpdate("DELETE FROM prevention_messages WHERE client_id='"+client_id+"'");
          
          }
       
          return totalDeleted;
      }
}


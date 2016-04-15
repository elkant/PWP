/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
import pwp.IdGenerator;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class saveIndividualRegister extends HttpServlet {
HttpSession session;
int total_clients;
String client_id="";
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
int present,checker,checker2;
String full_dates[];
String missed_clients;
String date="",month="",year="";
String date_key="",nextpage="";
String regid1,regid2,regid3,regid4,regid5,regid6,regid7,regid8,regid9,regid10,regid11,regid12,regid13;
String ds1,ds2,ds3,ds4,ds5,ds6,ds7,ds8,ds9,ds10,ds11,ds12,ds13;
String dater;
String s9special;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConn conn = new dbConn();
IdGenerator IG = new IdGenerator();
 
       String timestamp=IG.toDay();

nextpage="";
missed_clients=",";
present=checker=checker2=0;
if(session.getAttribute("total_clients")!=null){
  date="";month="";year=""; 
total_clients=Integer.parseInt(request.getParameter("pos"));
for (int i=1; i<=total_clients;i++){
    checker=checker2=0;
    s9special="2";
  System.out.println("here :  "+i);
    client_id=request.getParameter("client_id"+i);
    System.out.println("client id : "+client_id);
    s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="5";
regid1=regid2=regid3=regid4=regid5=regid6=regid7=regid8=regid9=regid10=regid11=regid12=regid13="";
ds1=ds2=ds3=ds4=ds5=ds6=ds7=ds8=ds9=ds10=ds11=ds12=ds13="";
    s1=request.getParameter("s_"+i+"_1");
    s2=request.getParameter("s_"+i+"_2");
    s3=request.getParameter("s_"+i+"_3");
    s4=request.getParameter("s_"+i+"_4");
    s5=request.getParameter("s_"+i+"_5");
    s6=request.getParameter("s_"+i+"_6");
    s7=request.getParameter("s_"+i+"_7");
    s8=request.getParameter("s_"+i+"_8");
    s9=request.getParameter("s_"+i+"_9");
    s10=request.getParameter("s_"+i+"_10");
    s11=request.getParameter("s_"+i+"_11");
    s12=request.getParameter("s_"+i+"_12");
    s13=request.getParameter("s_"+i+"_13");
    if(request.getParameter("subsequent_"+i)!=null){s9special="2";}
    
    if(s1.equals("")){s1="5";}
    if(s2.equals("")){s2="5";}
    if(s3.equals("")){s3="5";}
    if(s4.equals("")){s4="5";}
    if(s5.equals("")){s5="5";}
    if(s6.equals("")){s6="5";}
    if(s7.equals("")){s7="5";}
    if(s8.equals("")){s8="5";}
    if(s9.equals("")){s9="5";}
    if(s10.equals("")){s10="5";}
    if(s11.equals("")){s11="5";}
    if(s12.equals("")){s12="5";}
    if(s13.equals("")){s13="5";}
    
    
    
    
    ds1=request.getParameter("session_date_"+i+"_1");
    ds2=request.getParameter("session_date_"+i+"_2");
    ds3=request.getParameter("session_date_"+i+"_3");
    ds4=request.getParameter("session_date_"+i+"_4");
    ds5=request.getParameter("session_date_"+i+"_5");
    ds6=request.getParameter("session_date_"+i+"_6");
    ds7=request.getParameter("session_date_"+i+"_7");
    ds8=request.getParameter("session_date_"+i+"_8");
    ds9=request.getParameter("session_date_"+i+"_9");
    ds10=request.getParameter("session_date_"+i+"_10");
    ds11=request.getParameter("session_date_"+i+"_11");
    ds12=request.getParameter("session_date_"+i+"_12");
    ds13=request.getParameter("session_date_"+i+"_13");
    
    regid1=request.getParameter("regid_"+i+"_1");
    regid2=request.getParameter("regid_"+i+"_2");
    regid3=request.getParameter("regid_"+i+"_3");
    regid4=request.getParameter("regid_"+i+"_4");
    regid5=request.getParameter("regid_"+i+"_5");
    regid6=request.getParameter("regid_"+i+"_6");
    regid7=request.getParameter("regid_"+i+"_7");
    regid8=request.getParameter("regid_"+i+"_8");
    regid9=request.getParameter("regid_"+i+"_9");
    regid10=request.getParameter("regid_"+i+"_10");
    regid11=request.getParameter("regid_"+i+"_11");
    regid12=request.getParameter("regid_"+i+"_12");
    regid13=request.getParameter("regid_"+i+"_13");
    
    
   System.out.println("session 2   "+s2);
    if(s1!=null && (s1.equals("1") || s1.equals("3")) ){
        present++;
    }
    if( s2!=null && (s2.equals("1") || s2.equals("3"))){
        present++;
    }
    if(s3!=null && (s3.equals("1") || s3.equals("3"))){
        present++;
    }
    if(s4!=null && (s4.equals("1") || s4.equals("3"))){
        present++;
    }
    if(s5!=null && (s5.equals("1") || s5.equals("3"))){
        present++;
    }
    if(s6!=null && (s6.equals("1") || s6.equals("3"))){
        present++;
    }
    if(s7!=null && (s7.equals("1") || s7.equals("3"))){
        present++;
    }
    if(s8!=null && (s8.equals("1") || s8.equals("3"))){
        present++;
    }
    if(s9!=null && (s9.equals("1") || s9.equals("3"))){
        present++;
    }
    if(s10!=null && (s10.equals("1") || s10.equals("3"))){
        present++;
    }
    if(s11!=null && (s11.equals("1") || s11.equals("3"))){
        present++;
    }
    if(s12!=null && (s12.equals("1") || s12.equals("3"))){
        present++;
    }
    if(s13!=null && (s13.equals("1") || s13.equals("3"))){
        present++;
    }
    
    
    if(s1==null){s1="5";}
    if(s2==null){s2="5";}
    if(s3==null){s3="5";}
    if(s4==null){s4="5";}
    if(s5==null){s5="5";}
    if(s6==null){s6="5";}
    if(s7==null){s7="5";}
    if(s8==null){s8="5";}
    if(s9==null){s9="5";}
    if(s10==null){s10="5";}
    if(s11==null){s11="5";}
    if(s12==null){s12="5";}
    if(s13==null){s13="5";}
    checker2=0;
     IdGenerator ig = new IdGenerator();
     String check_existence="SELECT COUNT(reg_id) FROM register WHERE client_id='"+client_id+"'";
     conn.rs=conn.st.executeQuery(check_existence);
     if(conn.rs.next()==true){
         checker=conn.rs.getInt(1);
     }
     if(checker==0){
         String id=ig.current_id();
String dets_inserter="INSERT INTO register(reg_id,client_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,timestamp) VALUES('"+ig.current_id()+"','"+client_id+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"',"
+ "'"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"','"+s13+"','"+ig.toDay()+"')"; 
conn.st.executeUpdate(dets_inserter);



//INSERT ATTENDANCE 2........................................

for (int g=1;g<=13;g++){
    IdGenerator ignew = new IdGenerator();
String reg_id=ignew.current_id();

   if(g==1){
    if(!ds1.equals("")){
        full_dates=ds1.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s1+"','"+ds1+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 if(g==2){
    if(!ds2.equals("")){
        full_dates=ds2.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s2+"','"+ds2+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
  if(g==3){
     if(!ds3.equals("")){
        full_dates=ds3.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s3+"','"+ds3+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
   if(g==4){
      if(!ds4.equals("")){
        full_dates=ds4.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s4+"','"+ds4+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
} if(g==5){
  if(!ds5.equals("")){
        full_dates=ds5.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }  
   
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s5+"','"+ds5+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 if(g==6){
     if(!ds6.equals("")){
        full_dates=ds6.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s6+"','"+ds6+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
  if(g==7){
      if(!ds7.equals("")){
        full_dates=ds7.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s7+"','"+ds7+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
   if(g==8){
      if(!ds8.equals("")){
        full_dates=ds8.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s8+"','"+ds8+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
    if(g==9){
        if(!ds9.equals("")){
        full_dates=ds9.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s9+"','"+ds9+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);

  IdGenerator IGAdhere = new IdGenerator();
        String sid=IGAdhere.current_id();

                  int adhere=1;

    String inserter="INSERT INTO adherence (id,client_id,session_no,status,date_of_session,timestamp) "
            + "VALUES('"+sid+"','"+client_id+"','"+adhere+"','"+s9+"','"+ds9+"','"+ig.toDay()+"')";
    conn.st.executeUpdate(inserter);
    
}
     if(g==10){
         if(!ds10.equals("")){
        full_dates=ds10.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s10+"','"+ds10+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
} if(g==11){
    if(!ds11.equals("")){
        full_dates=ds11.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s11+"','"+ds11+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 if(g==12){
    if(!ds12.equals("")){
        full_dates=ds12.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s12+"','"+ds12+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
  if(g==13){
     if(!ds13.equals("")){
        full_dates=ds13.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    }else{
        month=year="";
        date_key="";
    }
    
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s13+"','"+ds13+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 
     
   

}   
    
     } 
     
        else{
         
//     UPDATE ALREADY ATTENDED LESSONS===========================================
         
         if(s9special.equals("1")) {
                    String update_register="UPDATE register SET s1='"+s1+"',s2='"+s2+"',s3='"+s3+"',"
                            + "s4='"+s4+"',s5='"+s5+"',s6='"+s6+"',s7='"+s7+"',s8='"+s8+"',"
                            + "s9='"+s9+"',s10='"+s10+"',s11='"+s11+"',s12='"+s12+"',s13='"+s13+"',timestamp='"+IG.toDay()+"' WHERE client_id='"+client_id+"'";
                    conn.st.executeUpdate(update_register);
         }
           else if(s9special.equals("2")) {
                  String getSess9="SELECT s9 FROM register WHERE client_id='"+client_id+"'";
                  conn.rs=conn.st.executeQuery(getSess9);
                  if(conn.rs.next()==true){
                   if(!conn.rs.getString(1).equals("1")){
                     String update_registerx="UPDATE register SET s9='"+s9+"',timestamp='"+IG.toDay()+"' WHERE client_id='"+client_id+"'";
                    conn.st4.executeUpdate(update_registerx);     
                   }   
                  }   
                }
            else{}

//INSERT ATTENDANCE 2........................................

for (int g=1;g<=13;g++){ 
    if(g==1 && !ds1.equals("")){
      full_dates=ds1.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s1.equals(conn.rs.getString(1)) && ds1.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s1+"',date='"+ds1+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
         
}
 if(g==2 && !ds2.equals("")){
 full_dates=ds2.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s2.equals(conn.rs.getString(1)) && ds2.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s2+"',date='"+ds2+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   } 
}
  if(g==3 && !ds3.equals("")){
  full_dates=ds3.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s3.equals(conn.rs.getString(1)) && ds3.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s3+"',date='"+ds3+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    
    }
   } 
}
   if(g==4 && !ds4.equals("")){
 full_dates=ds4.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s4.equals(conn.rs.getString(1)) && ds4.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s4+"',date='"+ds4+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   } 
} if(g==5 && !ds5.equals("")){
 full_dates=ds5.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s5.equals(conn.rs.getString(1)) && ds5.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s5+"',date='"+ds5+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
 if(g==6 && !ds6.equals("") ){
  full_dates=ds6.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s6.equals(conn.rs.getString(1)) && ds6.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s6+"',date='"+ds6+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
  if(g==7 && !ds7.equals("")){
 full_dates=ds7.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s7.equals(conn.rs.getString(1)) && ds7.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s7+"',date='"+ds7+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
   if(g==8 && !ds8.equals("")){
 full_dates=ds8.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s8.equals(conn.rs.getString(1)) && ds8.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s8+"',date='"+ds8+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
    if(g==9 && !ds9.equals("")){
 full_dates=ds9.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
   if(s9special.equals("1")){
       if(s9.equals(conn.rs.getString(1)) && ds9.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s9+"',date='"+ds9+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st4.executeUpdate(update);    
    }
   }
     if(s9special.equals("2") && !ds9.equals(conn.rs.getString(2))){
        IdGenerator IGAdhere = new IdGenerator();
        String sid=IGAdhere.current_id();
        
       String getSess9="SELECT value FROM register2 WHERE client_id='"+client_id+"' && session_no='"+g+"'";
                  conn.rs4=conn.st4.executeQuery(getSess9);
                  if(conn.rs4.next()==true){
                    System.out.println("coner :  "+conn.rs.getString(1));
                   if(!conn.rs4.getString(1).equals("1")){
                   String update="UPDATE register2 SET value='"+s9+"',date='"+ds9+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'"; 
                      conn.st3.executeUpdate(update);   
                  
                   }   
                  } 
                  
                  int adhere=0;
    String getMarkeds9="SELECT MAX(session_no) FROM adherence WHERE client_id='"+client_id+"'" ;
    conn.rs4=conn.st4.executeQuery(getMarkeds9);
    if(conn.rs4.next()==true){
        adhere=conn.rs4.getInt(1);
    }
    adhere++;
    String inserter="INSERT INTO adherence (id,client_id,session_no,status,date_of_session,timestamp) "
            + "VALUES('"+sid+"','"+client_id+"','"+adhere+"','"+s9+"','"+ds9+"','"+IG.toDay()+"')";
    conn.st4.executeUpdate(inserter);
                 }
    
    }
   }

     if(g==10 && !ds10.equals("")){
 full_dates=ds10.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s10.equals(conn.rs.getString(1)) && ds10.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s10+"',date='"+ds10+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
     
     if(g==11 && !ds11.equals("")){
 full_dates=ds11.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s11.equals(conn.rs.getString(1)) && ds11.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s11+"',date='"+ds11+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
 if(g==12 && !ds12.equals("")){
 full_dates=ds12.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s12.equals(conn.rs.getString(1)) && ds12.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s12+"',date='"+ds12+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   }
}
  if(g==13 && !ds13.equals("")){
 full_dates=ds13.split("/");
       System.out.println("the array sixe    :   "+full_dates.length);
    date_key=full_dates[2]+""+full_dates[0]+""+full_dates[1];
      month=full_dates[0];
      year=full_dates[2];
    String getMarked="SELECT value,date FROM register2 WHERE session_no='"+g+"' && client_id='"+client_id+"'";
   conn.rs=conn.st.executeQuery(getMarked);
   if(conn.rs.next()==true){
    if(s13.equals(conn.rs.getString(1)) && ds13.equals(conn.rs.getString(2))){
    System.out.println("NO CHANGE WAS DETECTED sess "+g+" client_id : "+client_id);  
    }
    else{
    String update="UPDATE register2 SET value='"+s13+"',date='"+ds13+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update);    
    }
   } 
}

}
        }
     
     String getPresent="SELECT SUM(value) FROM register2 WHERE client_id='"+client_id+"' && value='1'";
     conn.rs=conn.st.executeQuery(getPresent);
     if(conn.rs.next()==true){
         present=conn.rs.getInt(1);
     }
    System.out.println("lessons attended : "+present);
    
   String total_sessions_updator="UPDATE personal_information SET lessons_attended='"+present+"' WHERE client_id='"+client_id+"'";
conn.st.executeUpdate(total_sessions_updator);
     
     
            int atended=0,yr=0,sessionno=0,pos2=0,achieved=0,achievedyear=0;
            String mnth="0";
            
//GET CLIENT AND UPDATE COMPLETIONS-----------------------------------------------------
 String getAttended="SELECT lessons_attended,completionyear FROM personal_information JOIN register ON personal_information.client_id=register.client_id WHERE register.s9='1' && personal_information.client_id='"+client_id+"'";
 conn.rs=conn.st.executeQuery(getAttended);
 if(conn.rs.next()==true){
     atended=conn.rs.getInt(1);
     achievedyear=conn.rs.getInt(2);
 }
 if(achievedyear==0){
 if(atended>=3){
    
      String checkCompletionDate="SELECT year,session_no,month"
             + " FROM register2 WHERE client_id='"+client_id+"'  && value='1' && datekey>0 ORDER by datekey";
     conn.rs1=conn.st1.executeQuery(checkCompletionDate);
     while(conn.rs1.next()){
         pos2++;
         sessionno=conn.rs1.getInt(2);
         yr=conn.rs1.getInt(1);
         mnth=conn.rs1.getString(3);
      if(pos2>=3 && sessionno==9){
           break;
       }
      if(pos2<3 && sessionno==9){
           achieved++;
       }
      if(achieved==1 && pos2==3){
          break;
      }
     }
     
   System.out.println("year :"+year+"month :"+month+" session no : "+sessionno);
     
    String updateClients="UPDATE personal_information SET completionyear='"+yr+"', completionmonth='"+mnth+"' , timestamp='"+timestamp+"' WHERE client_id='"+client_id+"'";
    conn.st1.executeUpdate(updateClients);
 }  
 
 }
present=checker2=checker=0;

}
System.out.println("missed clients  :  "+missed_clients);
session.setAttribute("missed_clients", missed_clients);
if(conn.st!=null){
//            conn.st.close();
            }

System.out.println("Here at level 2 save.");

nextpage="load_services_provided";
 
}
else{
    session.setAttribute("already_clients", "<font color=\"red\">No Clients In the database...</font>");
    nextpage="add_a_client.jsp";
}
System.out.println("next page is : "+nextpage);
System.out.println("mised clients are : "+missed_clients);
//System.out.println("active clients are : "+);


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


response.sendRedirect("LoadIndNewServices");
System.out.println("here updated individual reg");
//response.sendRedirect("viewIndividuals.jsp");
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
        Logger.getLogger(saveIndividualRegister.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveIndividualRegister.class.getName()).log(Level.SEVERE, null, ex);
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

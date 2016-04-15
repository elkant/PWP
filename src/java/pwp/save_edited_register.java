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
public class save_edited_register extends HttpServlet {
HttpSession session;
String reg_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
String clients,client_id;
int total_clients,present;
int checker,checker2;
String chosen_sessions[],full_dates[];
String missed_clients,present_sessions;
String month,year;
String date_key;
String subsequent,selectedClients,markStatus;
String activeClients = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
present_sessions=",";selectedClients="";
        session=request.getSession();
        dbConn conn= new dbConn();
        IdGenerator IG = new IdGenerator();
        present=0;activeClients="";
        subsequent=markStatus="";
        subsequent="YES";
        chosen_sessions=session.getAttribute("chosen_session_1").toString().split(",");
        if(request.getParameter("subsequent")!=null){
            subsequent=request.getParameter("subsequent");
            if(subsequent==null){
                subsequent="YES";
            }
            else{
                subsequent="YES";
            }
            subsequent="YES";
            
        }
        
        System.out.println("subsequent value : "+subsequent);
        
        full_dates=session.getAttribute("dates").toString().split(",");
System.out.println("dates :   :  "+session.getAttribute("dates").toString());
missed_clients=",";
checker=checker2=0;
        if(session.getAttribute("total_client")!=null){
        clients=session.getAttribute("total_client").toString();
        total_clients=Integer.parseInt(clients);
        
        for ( int i=1; i<=total_clients; i++){
            IdGenerator ig = new IdGenerator();
//            $$$$$$$$$$$$$$$ GET PARAMETER VALUES  $$$$$$$$$$$$$$$$$$$$$$
            reg_id=request.getParameter("register_id"+i);
            System.out.println("reg id is      :    "+reg_id);
            
            
            
            
//            if(request.getParameter("client_"+i)!=null){
//            System.out.println("here : "+request.getParameter("client_"+i)+"   pos :"+i);
            
                    client_id=request.getParameter("client_id"+i);
                    selectedClients+=client_id+",";
                    s1=request.getParameter("session1-"+i);
                    s2=request.getParameter("session2-"+i);
                    s3=request.getParameter("session3-"+i);
                    s4=request.getParameter("session4-"+i);
                    s5=request.getParameter("session5-"+i);
                    s6=request.getParameter("session6-"+i);
                    s7=request.getParameter("session7-"+i);
                    s8=request.getParameter("session8-"+i);
                    s9=request.getParameter("session9-"+i);
                    s10=request.getParameter("session10-"+i);
                    s11=request.getParameter("session11-"+i);
                    s12=request.getParameter("session12-"+i);
                    s13=request.getParameter("session13-"+i);
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
    present_sessions=",";
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
    

    
//    System.out.println("chosen session  :  "+session.getAttribute("chosen_session_1").toString()+"  present sessions  :  "+present_sessions);
    for(int k=0;k<chosen_sessions.length;k++){
        String sess=chosen_sessions[k];
      if(sess.equals("1")){ if(s1.equals("1")){checker2++;  }}
     if(sess.equals("2")){if(s2.equals("1")){checker2++;   }}
     if(sess.equals("3")){if(s3.equals("1")){checker2++;   }}
     if(sess.equals("4")){if(s4.equals("1")){checker2++;   }}
     if(sess.equals("5")){if(s5.equals("1")){checker2++;   }}
     if(sess.equals("6")){if(s6.equals("1")){checker2++;   }}
    if(sess.equals("7")){ if(s7.equals("1")){checker2++;   }}
    if(sess.equals("8")){ if(s8.equals("1")){checker2++;   }}
    if(sess.equals("9")){ if(s9.equals("1")){checker2++;  }}
     if(sess.equals("10")){if(s10.equals("1")){checker2++;  }}
    if(sess.equals("11")){ if(s11.equals("1")){checker2++;   }}
     if(sess.equals("12")){if(s12.equals("1")){checker2++;  }}
     if(sess.equals("13")){if(s13.equals("1")){checker2++;  }}
    }
    if(checker2==0){
      missed_clients+=client_id+",";
    }
    checker2=0;
//    
// System.out.println("reg_id   :   "+reg_id);
//System.out.println("session id    :   "+s1);
                
                
            if(reg_id.equals("0")){
//              System.out.println("Clients with 0 id   :     "+reg_id);
                       String id=ig.current_id();
String dets_inserter="INSERT INTO register(reg_id,client_id,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,timestamp) VALUES('"+ig.current_id()+"','"+client_id+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"',"
+ "'"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s10+"','"+s11+"','"+s12+"','"+s13+"','"+ig.toDay()+"')"; 
conn.st.executeUpdate(dets_inserter);
  
if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
//INSERT ATTENDANCE 2........................................

for (int g=1;g<=13;g++){
    IdGenerator ignew = new IdGenerator();
reg_id=ignew.current_id();
//    System.out.println("the array sixe    :   "+full_dates.length);
    date_key="0";
    if(!full_dates[g-1].equals("0") && !full_dates[g-1].equals(",")){
    String monther []=full_dates[g-1].split("/");
    month=monther[0];
    
    String yearer []=full_dates[g-1].split("/");
    date_key=yearer[2]+""+yearer[0]+""+yearer[1];
    
    year=yearer[2];
    }else{
        month=year="";
    }
    if(g==1){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s1+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 if(g==2){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s2+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
  if(g==3){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s3+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
   if(g==4){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s4+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
} if(g==5){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s5+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 if(g==6){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s6+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
  if(g==7){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s7+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
   if(g==8){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s8+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
    if(g==9){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s9+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);

        IdGenerator IGAdhere = new IdGenerator();
        String sid=IGAdhere.current_id();

                  int adhere=1;

    String inserter="INSERT INTO adherence (id,client_id,session_no,status,date_of_session,timestamp) "
            + "VALUES('"+sid+"','"+client_id+"','"+adhere+"','"+s9+"','"+full_dates[g-1]+"','"+IG.toDay()+"')";
    conn.st.executeUpdate(inserter);
                 
 
}
     if(g==10){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s10+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
} if(g==11){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s11+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 if(g==12){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s12+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
  if(g==13){
String inserter2="INSERT INTO register2(reg_id,client_id,session_no,value,date,datekey,month,year,timestamp) "
        + "VALUES ('"+reg_id+"','"+client_id+"','"+g+"','"+s13+"','"+full_dates[g-1]+"','"+date_key+"','"+month+"','"+year+"','"+ig.toDay()+"')";
conn.st.executeUpdate(inserter2);
}
 
     
   

}
  }
            else{

//INSERT ATTENDANCE 2........................................

for (int g=1;g<=13;g++){
if(session.getAttribute("chosen_session_1").toString().contains(","+g+",")){
     date_key="0";
//    System.out.println("the array sixe    :   "+full_dates.length);
    if(!full_dates[g-1].equals("0") && !full_dates[g-1].equals(",")){
    String monther []=full_dates[g-1].split("/");
    month=monther[0];
    
    String yearer []=full_dates[g-1].split("/");
    date_key=yearer[2]+""+yearer[0]+""+yearer[1];
    year=yearer[2];
    }else{
        month=year="";
    }
    if(g==1){
        if(request.getParameter("active_"+g+"_"+i)!=null){
          if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
         String update_register="UPDATE register SET s1='"+s1+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
  String update="UPDATE register2 SET value='"+s1+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
    }
 if(g==2){
     if(request.getParameter("active_"+g+"_"+i)!=null){
  if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
     String update_register="UPDATE register SET s2='"+s2+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s2+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
 }
  if(g==3){
      if(request.getParameter("active_"+g+"_"+i)!=null){
      if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
      String update_register="UPDATE register SET s3='"+s3+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s3+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
  }
   if(g==4){
       if(request.getParameter("active_"+g+"_"+i)!=null){
       if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
       String update_register="UPDATE register SET s4='"+s4+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s4+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
   }
  if(g==5){
      if(request.getParameter("active_"+g+"_"+i)!=null){
       if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
    String update_register="UPDATE register SET s5='"+s5+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s5+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
  }
 if(g==6){
     if(request.getParameter("active_"+g+"_"+i)!=null){
      if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
     String update_register="UPDATE register SET s6='"+s6+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s6+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
 }
  if(g==7){
      if(request.getParameter("active_"+g+"_"+i)!=null){
       if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
      String update_register="UPDATE register SET s7='"+s7+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s7+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
  }
   if(g==8){
       if(request.getParameter("active_"+g+"_"+i)!=null){
     if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
       String update_register="UPDATE register SET s8='"+s8+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s8+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
   }
    if(g==9){
       if(!activeClients.contains(client_id)){activeClients+=client_id+",";}  
    if (subsequent.equals("")) {
  
     String update_register="UPDATE register SET s9='"+s9+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
   String update="UPDATE register2 SET value='"+s9+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
   conn.st.executeUpdate(update);   
                }
    else if(subsequent.equals("YES")){
        
         String getSess="SELECT s9 FROM register WHERE reg_id='"+reg_id+"'";
                  conn.rs=conn.st.executeQuery(getSess);
                  if(conn.rs.next()==true){
                   if(!conn.rs.getString(1).equals("1")){
                     String update_register="UPDATE register SET s9='"+s9+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register);     
                   }   
                  }
        
        IdGenerator IGAdhere = new IdGenerator();
        String sid=IGAdhere.current_id();
       System.out.println("executed here +++++++++++++ ");
                  String getSess9="SELECT value FROM register2 WHERE client_id='"+client_id+"' && session_no='"+g+"'";
                  conn.rs=conn.st.executeQuery(getSess9);
                  if(conn.rs.next()==true){
                    System.out.println("coner :  "+conn.rs.getString(1));
                   if(!conn.rs.getString(1).equals("1")){
                   String update="UPDATE register2 SET value='"+s9+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'"; 
                      conn.st.executeUpdate(update);   
                  
                   }   
                  }
                  int adhere=0;
    String getMarkeds9="SELECT MAX(session_no) FROM adherence WHERE client_id='"+client_id+"'" ;
    conn.rs=conn.st.executeQuery(getMarkeds9);
    if(conn.rs.next()==true){
        adhere=conn.rs.getInt(1);
    }
    adhere++;
    String inserter="INSERT INTO adherence (id,client_id,session_no,status,date_of_session,timestamp) "
            + "VALUES('"+sid+"','"+client_id+"','"+adhere+"','"+s9+"','"+full_dates[g-1]+"','"+IG.toDay()+"')";
    conn.st.executeUpdate(inserter);
                 }      
       
       
}
     if(g==10){
         if(request.getParameter("active_"+g+"_"+i)!=null){
          if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
         String update_register="UPDATE register SET s10='"+s10+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register);
                    
 String update="UPDATE register2 SET value='"+s10+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
         }
} if(g==11){
    if(request.getParameter("active_"+g+"_"+i)!=null){
        if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
    String update_register="UPDATE register SET s11='"+s11+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s11+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
}
 if(g==12){
     if(request.getParameter("active_"+g+"_"+i)!=null){
       if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
     String update_register="UPDATE register SET s12='"+s12+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s12+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
                + " WHERE client_id='"+client_id+"' && session_no='"+g+"'";
       conn.st.executeUpdate(update); 
}
 }
  if(g==13){
      if(request.getParameter("active_"+g+"_"+i)!=null){
      if(!activeClients.contains(client_id)){activeClients+=client_id+",";}
      String update_register="UPDATE register SET s13='"+s13+"',timestamp='"+IG.toDay()+"' WHERE reg_id='"+reg_id+"'";
                    conn.st.executeUpdate(update_register); 
                    
 String update="UPDATE register2 SET value='"+s13+"',date='"+full_dates[g-1]+"',datekey='"+date_key+"', month='"+month+"',year='"+year+"',timestamp='"+IG.toDay()+"' "
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
//    System.out.println("lessons attended : "+present);
    
   String total_sessions_updator="UPDATE personal_information SET lessons_attended='"+present+"' WHERE client_id='"+client_id+"'";
conn.st.executeUpdate(total_sessions_updator);


            
            int atended=0,yr=0,sessionno=0,pos2=0,achieved=0,completionyear=0;
            String mnth="0";
//GET CLIENT AND UPDATE COMPLETIONS-----------------------------------------------------
 String getAttended="SELECT lessons_attended,completionyear FROM personal_information JOIN register ON personal_information.client_id=register.client_id WHERE register.s9='1' && personal_information.client_id='"+client_id+"'";
 conn.rs=conn.st.executeQuery(getAttended);
 if(conn.rs.next()==true){
     atended=conn.rs.getInt(1);
     completionyear=conn.rs.getInt(2);
 }
 if(completionyear==0){
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
//           System.out.println("year :"+year+"month :"+month+" session no : "+sessionno);
           break;
       }
      if(pos2<3 && sessionno==9){
           achieved++;
       }
      if(achieved==1 && pos2==3){
          break;
      }
     }
     
 //           System.out.println("year :"+year+"month :"+month+" session no : "+sessionno);
  
     
  String timestamp=IG.toDay(); 


     
    String updateClients="UPDATE personal_information SET completionyear='"+yr+"', completionmonth='"+mnth+"' , timestamp='"+timestamp+"' WHERE client_id='"+client_id+"'";
    conn.st1.executeUpdate(updateClients);
 }
 }
        
       present=checker2=checker=0;                   
//        }   
//       else{
//            
//        }  
        }
         
        }
System.out.println("active  :  "+activeClients+"chossen session: "+session.getAttribute("chosen_session_1"));
session.setAttribute("missed_clients", missed_clients);
session.setAttribute("client_ids", activeClients);
//session.setAttribute("activeClients", activeClients);

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

if(session.getAttribute("second")!=null){
    session.removeAttribute("second");
}
response.sendRedirect("edit_register3");
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
            Logger.getLogger(save_edited_register.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_edited_register.class.getName()).log(Level.SEVERE, null, ex);
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

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
public class save_register extends HttpServlet {
HttpSession session;
int total_clients;
String client_id="";
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
int present,checker,checker2;
String chosen_sessions[],full_dates[];
String missed_clients;
String date="",month="",year="";
String date_key="",nextpage;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConn conn = new dbConn();
nextpage="";
chosen_sessions=session.getAttribute("chosen_session_1").toString().split(",");
missed_clients=",";
present=checker=checker2=0;
if(session.getAttribute("total_clients")!=null){
  date="";month="";year=""; 
  if(!session.getAttribute("dates").toString().equals("")){
total_clients=Integer.parseInt(session.getAttribute("total_clients").toString());
full_dates=session.getAttribute("dates").toString().split(",");
System.out.println("dates :   :  "+session.getAttribute("dates").toString());
for (int i=1; i<=total_clients;i++){
    checker=checker2=0;
  System.out.println("here :  "+i);
    client_id=request.getParameter("client_id"+i);
    s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="5";
    s1=request.getParameter("session"+i+"1");
    s2=request.getParameter("session"+i+"2");
    s3=request.getParameter("session"+i+"3");
    s4=request.getParameter("session"+i+"4");
    s5=request.getParameter("session"+i+"5");
    s6=request.getParameter("session"+i+"6");
    s7=request.getParameter("session"+i+"7");
    s8=request.getParameter("session"+i+"8");
    s9=request.getParameter("session"+i+"9");
    s10=request.getParameter("session"+i+"10");
    s11=request.getParameter("session"+i+"11");
    s12=request.getParameter("session"+i+"12");
    s13=request.getParameter("session"+i+"13");
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
    System.out.println("chosen session  :  "+session.getAttribute("chosen_session_1").toString());
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

String total_sessions_updator="UPDATE personal_information SET lessons_attended='"+present+"' WHERE client_id='"+client_id+"'";
conn.st.executeUpdate(total_sessions_updator);


//INSERT ATTENDANCE 2........................................

for (int g=1;g<=13;g++){
    IdGenerator ignew = new IdGenerator();
String reg_id=ignew.current_id();
    System.out.println("the array sixe    :   "+full_dates.length);
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
     
            int atended=0,yr=0,sessionno=0,pos2=0,achieved=0;
            String mnth="0";
//GET CLIENT AND UPDATE COMPLETIONS-----------------------------------------------------
 String getAttended="SELECT lessons_attended FROM personal_information JOIN register ON personal_information.client_id=register.client_id WHERE register.s9='1' && personal_information.client_id='"+client_id+"'";
 conn.rs=conn.st.executeQuery(getAttended);
 if(conn.rs.next()==true){
     atended=conn.rs.getInt(1);
 }
 
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
   IdGenerator IG = new IdGenerator();
  String timestamp=IG.toDay(); 
   
    String updateClients="UPDATE personal_information SET completionyear='"+yr+"', completionmonth='"+mnth+"' , timestamp='"+timestamp+"' WHERE client_id='"+client_id+"'";
    conn.st1.executeUpdate(updateClients);
 }  
     
present=checker2=checker=0;

}
System.out.println("missed clients  :  "+missed_clients);
session.setAttribute("missed_clients", missed_clients);
if(conn.st!=null){
            conn.st.close();
            }

System.out.println("Here at level 2 save.");

nextpage="load_services_provided";
  }
  else{
      nextpage="add_a_client.jsp";
      session.setAttribute("already_clients", "<font color=\"red\">Sorry. "
              + "You didnot complete all the steps for the last group or Individuals you marked.</font><font color=\"black\">Please Select the year,last marked message and existing clients then press next.  </font>");
  }


}
else{
    session.setAttribute("already_clients", "<font color=\"red\">No Clients In the database...</font>");
    nextpage="add_a_client.jsp";
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
            Logger.getLogger(save_register.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_register.class.getName()).log(Level.SEVERE, null, ex);
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

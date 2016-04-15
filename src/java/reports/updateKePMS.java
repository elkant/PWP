/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

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
public class updateKePMS extends HttpServlet {
HttpSession session;
String startDate,endDate,month,partnername,clientid,gender,agebracket;
int year,prevyear,pos,sessionno,pepfaryear,pos2,achieved,age;
int start,end,datekey;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
        dbConn conn = new dbConn();
        pos=0;
         IdGenerator IG = new IdGenerator();
       String timestamp=IG.toDay();
         String getClients="SELECT clients.client_id FROM clients "
              + "JOIN register ON clients.client_id=register.client_id "
              + " WHERE clients.lessons_attended>=3 && register.s9='1' ORDER BY completionyear,completionmonth";
      conn.rs=conn.st.executeQuery(getClients);
      while(conn.rs.next()){
          partnername=clientid=gender="";age=0;
          clientid=conn.rs.getString(1);
          pos2=achieved=0;
        //          System.out.println(pos+"-----partner :"+partnername+" clientid :"+clientid+"age :"+age+"gender :"+gender);
     String checkCompletionDate="SELECT year,session_no,month,datekey"
             + " FROM register2 WHERE client_id='"+clientid+"'  && value='1' && datekey>0 ORDER by datekey";
     conn.rs1=conn.st1.executeQuery(checkCompletionDate);
     while(conn.rs1.next()){
         pos2++;
         sessionno=conn.rs1.getInt(2);
         year=conn.rs1.getInt(1);
         month=conn.rs1.getString(3);
         datekey=conn.rs1.getInt(4);
      if(pos2>=3 && sessionno==9){
//           System.out.println("year :"+year+"month :"+month+" session no : "+sessionno);
           break;
       }
      if(pos2<3 && sessionno==9){
           achieved++;
       }
      if(achieved==1 && pos2==3){
//           System.out.println("year :"+year+"month :"+month+" session no : "+sessionno);
          break;
      }
     }
//     if(datekey>=start && datekey<=end && year>=2014){
      pos++;      
     
       
         System.out.println(pos+"-----partner :"+partnername+" age bracket :"+agebracket+" gender :"+gender+" completion month : "+month);
    String updateClients="UPDATE clients SET completionyear='"+year+"', completionmonth='"+month+"' , timestamp='"+timestamp+"' WHERE client_id='"+clientid+"'";
//  System.out.println(updateClients);
    conn.st1.executeUpdate(updateClients);
//     }  
          
          
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
        Logger.getLogger(updateKePMS.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(updateKePMS.class.getName()).log(Level.SEVERE, null, ex);
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

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
import pwp.IdGenerator;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class BackUpReminder extends HttpServlet {
HttpSession session;
String status,today,timestamp;
int days,maxid,foundClients,foundRegister;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            foundClients=foundRegister=0;
            status="";
            session = request.getSession();
            dbConn conn = new dbConn();
            
           
            
            IdGenerator IG = new IdGenerator();
            today=IG.toDay();
            
            String getMax="SELECT MAX(id) FROM timestamper";
            conn.rs=conn.st.executeQuery(getMax);
            if(conn.rs.next()==true){
                maxid=conn.rs.getInt(1);
               
            }
//            
            String getTimestamp="SELECT timestamp FROM timestamper WHERE id='"+maxid+"'";
            conn.rs=conn.st.executeQuery(getTimestamp);
            if(conn.rs.next()==true){
               timestamp=conn.rs.getString(1);
            }
              
           String getDays1="SELECT DATEDIFF(STR_TO_DATE('"+today+"','%Y-%m-%d'),STR_TO_DATE(timestamp,'%Y-%m-%d')) "
                    + "FROM timestamper WHERE id='"+maxid+"'";
            System.out.println(getDays1);
            conn.rs=conn.st.executeQuery(getDays1);
            if(true==conn.rs.next()){
             days=conn.rs.getInt(1);
            }
            
          System.out.println("max id  :   "+maxid+" days  :  "+days);
          
          
          
          if(days>=10){
//             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkClients="SELECT COUNT(client_id) FROM clients WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + "BETWEEN STR_TO_DATE('"+timestamp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d') ";
             System.out.println(checkClients);
              conn.rs=conn.st.executeQuery(checkClients);
              if(conn.rs.next()==true){
                  foundClients=conn.rs.getInt(1);
              }
             
            if(foundClients==0){
                //             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkRegister="SELECT COUNT(reg_id) FROM register2 WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + " BETWEEN STR_TO_DATE('"+timestamp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d')";
              System.out.println(checkRegister);
              conn.rs=conn.st.executeQuery(checkRegister);
              if(conn.rs.next()==true){
                  foundRegister=conn.rs.getInt(1);
              }
            } 
          
          if(foundClients<10 && foundRegister<10){
            status="";  
          }  
          else{
          status="NOTE: "+days+" days have gone since you last backed up your data. There is data that has not yet been backed up. Please "
                  + "log in to the system and back up this data." ;
          }  
             
          }
          else{
            status=""; 
          }
//          status="";
          if(conn.rs!=null){conn.rs.close();}
if(conn.st!=null){conn.st.close();}
if(conn.rs1!=null){conn.rs1.close();}
if(conn.st1!=null){conn.st1.close();}
if(conn.rs2!=null){conn.rs2.close();}
if(conn.st2!=null){conn.st2.close();}
if(conn.st3!=null){conn.st3.close();}
if(conn.pst!=null){conn.pst.close();}
if(conn.pst!=null){conn.pst.close();}
                if(conn.conn!=null){conn.conn.close();}
          System.out.println("maxid : "+maxid+" timestamp : "+timestamp+" days : "+days+" foud clients : "+foundClients+" found register : "+foundRegister);
           
//           status=""; 
          out.println(status);
        } finally {
            out.close();
        }
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
        Logger.getLogger(BackUpReminder.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(BackUpReminder.class.getName()).log(Level.SEVERE, null, ex);
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

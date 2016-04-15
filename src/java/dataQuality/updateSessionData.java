/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataQuality;

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

/**
 *
 * @author Geofrey Nyabuto
 */
public class updateSessionData extends HttpServlet {
HttpSession session;
String status,id,group_id,groupingsid,sessionid,sessiondate="";
String month,year,datekey,date;
String sessdate,query,clientid;
int pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
            dbConn conn = new dbConn();
            
            clientid="";pos=0;
            String getAllSessions="SELECT sessions_id,group_id,groupings,session_id,session_date FROM sessions";
            conn.rs=conn.st.executeQuery(getAllSessions);
            while(conn.rs.next()){
            id=group_id=groupingsid=sessionid=sessiondate=""; 
            id=conn.rs.getString(1);
            group_id=conn.rs.getString(2);
            groupingsid=conn.rs.getString(3);
            sessionid=conn.rs.getString(4);
            sessiondate=conn.rs.getString(5);
         String updateSessions="UPDATE sessions SET message_id='"+sessionid+"' WHERE sessions_id='"+id+"'";
         conn.st1.executeUpdate(updateSessions);
         if(!sessiondate.equals("")){
          String []  dater=sessiondate.split("/");
          sessdate=dater[0]+"/"+dater[1]+"/"+dater[2];
          month=dater[0];
          year=dater[2];
          datekey=dater[2]+""+dater[0]+""+dater[1];
         
         if(group_id.equals("0")) {
//           GET INDIVIDUAL CLIENTS=========================================  
          query="SELECT client_id FROM clients WHERE groupings='"+groupingsid+"'";    
         }
         else{
         query="SELECT client_id FROM clients WHERE group_id='"+group_id+"'";      
         }
        conn.rs2=conn.st2.executeQuery(query);
        while(conn.rs2.next()){
            pos++;
            System.out.println("position      :       "+pos);
//       UPDATE REGISTER 2 BASED ON THE SESSION NUMBER AND HE CLIENT ID     
        clientid=conn.rs2.getString(1);
       String regupdator="UPDATE register2 SET datekey='"+datekey+"', date='"+sessdate+"',month='"+month+"', year='"+year+"' WHERE client_id='"+clientid+"' && session_no='"+sessionid+"'";    
       conn.st1.executeUpdate(regupdator);
            
        }
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
        Logger.getLogger(updateSessionData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(updateSessionData.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tracker;

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
public class checkUnmarked extends HttpServlet {
String checker,partner,district,groupid,groupings,sessions,year,group;
int found;
HttpSession session;
String clientids,sessions2,missed_clients,output;
String partner_name,district_name,group_id,pending_sessions;
int counter,no;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          dbConn conn = new dbConn();
          session = request.getSession();
          year=clientids=""; counter=no=0;output=""; pending_sessions=""; 
         String checker1="SELECT * FROM tracker" ;
         conn.rs=conn.st.executeQuery(checker1);
         if(conn.rs.next()==true){
            partner=conn.rs.getString("partner");
            groupid=conn.rs.getString("groupid");
            groupings=conn.rs.getString("groupings");
            district=conn.rs.getString("district");
            sessions=conn.rs.getString("sessions");
            year=conn.rs.getString("year");
            
           String [] sess=sessions.split(",");
           for(int i=0;i<sess.length;i++){
               if(!sess[i].equals("") && !sess[i].equals(",")){
            String getSessions="SELECT message FROM message_codes WHERE message_id='"+sess[i]+"'";
            conn.rs3=conn.st3.executeQuery(getSessions);
            if(conn.rs3.next()==true){
                no++;
                pending_sessions+=no+". "+conn.rs3.getString(1)+".<br><br>";
            }
                    
                   
                    }
           }
            missed_clients=conn.rs.getString("missed_clients");
          String getPartner="SELECT partner_name FROM partner WHERE partner_id='"+partner+"'";
          conn.rs1=conn.st1.executeQuery(getPartner);
          if(conn.rs1.next()==true){
          session.setAttribute("partner_name", conn.rs1.getString(1));
          output+=conn.rs1.getString(1)+"##";
          }
          String getdist="SELECT district_name FROM district WHERE district_id='"+district+"'";
          conn.rs1=conn.st1.executeQuery(getdist);
          if(conn.rs1.next()==true){
          session.setAttribute("district_name", conn.rs1.getString(1)); 
          output+=conn.rs1.getString(1)+"##";
          }
          if(!groupid.equals("0")){
          String getgrp="SELECT group_name FROM groups WHERE group_id='"+groupid+"'";
          conn.rs1=conn.st1.executeQuery(getgrp);
          if(conn.rs1.next()==true){
          session.setAttribute("sessions_group_name", conn.rs1.getString(1));
          output+=conn.rs1.getString(1)+"##";
          }    
          }
          else{
          session.setAttribute("sessions_group_name","INDIVIDUALS");
          output+="INDIVIDUALS##";
          }
          output+=pending_sessions+"##";
          }
         
          if (!year.equals("")) {
//           SET THE SESSIONS============================================= 
              session.setAttribute("partner_id", partner);
              session.setAttribute("sessions_group_id", groupid);
              session.setAttribute("groupings", groupings);
              session.setAttribute("district_id", district);
              session.setAttribute("chosen_session", sessions);
              session.setAttribute("year", year);
              session.setAttribute("missed_clients", missed_clients);
              session.setAttribute("chosen_session_1", sessions);
              session.setAttribute("checker", "5");
              System.out.println("for newly added");
              counter++;
            }
 
          else{
              output="";no=0;
              session.removeAttribute("checker");
              missed_clients=pending_sessions="";
//              LEVEL 2 CHECKER===============================================
           String getmarked="SELECT * FROM tracker2"  ;
           conn.rs=conn.st.executeQuery(getmarked);
           if(conn.rs.next()==true){
            missed_clients=conn.rs.getString("missed_clients");
            sessions2=conn.rs.getString("sessions");
            clientids=conn.rs.getString("clientids");
            group=conn.rs.getString("groupname");
            district=conn.rs.getString("district");
            partner=conn.rs.getString("partner");
            
            String [] sess=sessions2.split(",");
           for(int i=0;i<sess.length;i++){
               if(!sess[i].equals("") && !sess[i].equals(",")){
            String getSessions="SELECT message FROM message_codes WHERE message_id='"+sess[i]+"'";
            conn.rs3=conn.st3.executeQuery(getSessions);
            if(conn.rs3.next()==true){
                no++;
                pending_sessions+=no+". "+conn.rs3.getString(1)+".<br><br>";
            }
                    
                   
                    }
           }
            
            output+=partner+"##";
            output+=district+"##";
            output+=group+"##";
            output+=pending_sessions+"##";
//            output+=partner+"##";
           }
           if(!clientids.equals("")){
//               session.removeAttribute("missed_clients");
//               session.removeAttribute("chosen_session_1");
//               session.removeAttribute("client_ids");
//               session.removeAttribute("client_ids");
//               session.removeAttribute("district_name");
//               session.removeAttribute("partner_name");
//               session.removeAttribute("sessions_group_name");
               
            session.setAttribute("missed_clients", missed_clients);
            session.setAttribute("chosen_session_1", sessions2);
            session.setAttribute("client_ids", clientids);
            
            session.setAttribute("district_name", district);
            session.setAttribute("partner_name", partner);
            session.setAttribute("sessions_group_name", group);
            
            System.out.println("for editing"); 
            counter++;
           }
           else{
               System.out.println("here nothing really bad");
           }
          }
          if(counter==0){
          output="NONE";    
          }
            else{
//                output="found";
                 System.out.println("chosen.......1......."+output);
            }
            session.setAttribute("total_clients", 2);
//                output="NONE";
            /* TODO output your page here. You may use following sample code. */
            out.println(output);
System.out.println(".............."+output+"..............");

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
        Logger.getLogger(checkUnmarked.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(checkUnmarked.class.getName()).log(Level.SEVERE, null, ex);
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

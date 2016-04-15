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
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class SplitCDONData extends HttpServlet {
HttpSession session;
String id,partner_id,county_id;
int pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       
       dbConn conn = new dbConn();
       
       pos=0;
       String getAllClients="SELECT clients.client_id,clients.partner_id,district.county_id FROM clients JOIN district "
               + "ON clients.district_id=district.district_id WHERE partner_id='15'";
       conn.rs=conn.st.executeQuery(getAllClients);
       while(conn.rs.next()){
           pos++;
           
            id=conn.rs.getString(1);
            partner_id=conn.rs.getString(2);
            county_id=conn.rs.getString(3);
      if(county_id.equals("3")){
          System.out.println("this is narok data");
          String updator="UPDATE clients SET partner_id='2124' WHERE client_id='"+id+"'";
          conn.st1.executeUpdate(updator);
      }
      else if(county_id.equals("5")){
          System.out.println("this is kajiado data");
      }
       
      
      System.out.println("At client position : "+pos);
       }
       pos=0;
      // clean groups 
       String getAllGroups="SELECT groups.group_id,groups.partner_id,district.county_id FROM groups JOIN district "
               + "ON groups.district_id=district.district_id WHERE partner_id='15'";
       conn.rs=conn.st.executeQuery(getAllGroups);
       while(conn.rs.next()){
           pos++;
            
            id=conn.rs.getString(1);
            partner_id=conn.rs.getString(2);
            county_id=conn.rs.getString(3);
      if(county_id.equals("3")){
          System.out.println("this is narok data");
           String updator="UPDATE groups SET partner_id='2124' WHERE group_id='"+id+"'";
          conn.st1.executeUpdate(updator);
      }
      else if(county_id.equals("5")){
          System.out.println("this is kajiado data");
      }
       
      
      System.out.println("At groups position : "+pos);
       }
       pos=0;
     // clean groups 
       String getAllNoGroup="SELECT no_group.id,no_group.partner_id,district.county_id FROM no_group JOIN district "
               + "ON no_group.district_id=district.district_id WHERE partner_id='15'";
       conn.rs=conn.st.executeQuery(getAllNoGroup);
       while(conn.rs.next()){
           pos++;
            
            id=conn.rs.getString(1);
            partner_id=conn.rs.getString(2);
            county_id=conn.rs.getString(3);
      if(county_id.equals("3")){
          System.out.println("this is narok data");
           String updator="UPDATE no_group SET partner_id='2124' WHERE id='"+id+"'";
          conn.st1.executeUpdate(updator);
      }
      else if(county_id.equals("5")){
          System.out.println("this is kajiado data");
      }
       
      
      System.out.println("At no_group position : "+pos);
       }
      pos=0; 
   String getAllSessions="SELECT sessions.sessions_id,sessions.partner_id,district.county_id FROM sessions JOIN district "
               + "ON sessions.district_id=district.district_id WHERE partner_id='15'";
       conn.rs=conn.st.executeQuery(getAllSessions);
       while(conn.rs.next()){
           pos++;
            
            id=conn.rs.getString(1);
            partner_id=conn.rs.getString(2);
            county_id=conn.rs.getString(3);
      if(county_id.equals("3")){
          System.out.println("this is narok data");
           String updator="UPDATE sessions SET partner_id='2124' WHERE sessions_id='"+id+"'";
          conn.st1.executeUpdate(updator);
      }
      else if(county_id.equals("5")){
          System.out.println("this is kajiado data");
      }
       
      
      System.out.println("At sessions position : "+pos);
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
        Logger.getLogger(SplitCDONData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(SplitCDONData.class.getName()).log(Level.SEVERE, null, ex);
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

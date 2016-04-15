/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class getBasicDetails extends HttpServlet {
HttpSession session;
String client_id;
String client_name,dob,sex,group_name,provider_name,district_name,location;
String cfname,cmname,clname,pfname,pmname,plname;
ArrayList data = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      session=request.getSession();
      dbConn conn = new dbConn();
      
      data.clear();
      client_id=request.getParameter("dt");
      Assessment assess = new Assessment();
     client_name=dob=sex=group_name=provider_name=district_name=location=""; 
      System.out.println("client_id was here-------------- : "+client_id);
      String getDetails="SELECT personal_information.fname,personal_information.mname,personal_information.lname,"
              + "personal_information.gender,"
              + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) ),personal_information.location,"
              + "groups.group_name,district.district_name,"
              + "service_provider.fname,service_provider.mname,service_provider.lname "
              + "FROM personal_information JOIN district ON personal_information.district_id=district.district_id "
              + "JOIN service_provider ON service_provider.provider_id=personal_information.provider_id "
              + "LEFT JOIN groups ON personal_information.group_id=groups.group_id WHERE "
              + "personal_information.client_id=?";
      conn.pst=conn.conn.prepareStatement(getDetails);
      conn.pst.setString(1, client_id);
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()==true){
       cfname=conn.rs.getString(1);
       cmname=conn.rs.getString(2);
       clname=conn.rs.getString(3);
       
       sex=conn.rs.getString(4);
       dob=conn.rs.getString(5);
       location=conn.rs.getString(6);
       group_name=conn.rs.getString(7);
       district_name=conn.rs.getString(8);
       
       pfname=conn.rs.getString(9);
       pmname=conn.rs.getString(10);
       plname=conn.rs.getString(11);
       
       if(cmname.equalsIgnoreCase(clname)){cmname="";}
       if(pmname.equalsIgnoreCase(plname)){pmname="";}
       
       client_name=cfname+" "+cmname+" "+clname;
       provider_name=pfname+" "+pmname+" "+plname;
       
       if(group_name==null){
           group_name="INDIVIDUAL";
       }
      if(location.equals("")){
          location="NONE";
      }
      if(dob==null){
          dob="<font color=\"red\">Error: </font> No date of birth";
      }
      else if (dob.equals("1")){
        dob=dob+" Year";  
      }
      else{
           dob=dob+" Years"; 
      }
      
       
      }
      assess.setClient_id(client_id);
     assess.setClient_name(client_name);
     assess.setDob(dob);
     assess.setSex(sex);
     assess.setGroup_name(group_name);
     assess.setProvider_name(provider_name);
     assess.setDistrict_name(district_name);
     assess.setLocation(location);
     
     data.add(assess);
      
     session.setAttribute("Assess", data);
     
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

     response.sendRedirect("Assessment.jsp");
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
        Logger.getLogger(getBasicDetails.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(getBasicDetails.class.getName()).log(Level.SEVERE, null, ex);
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

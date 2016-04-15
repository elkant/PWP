/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edits;

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
public class loadEditClients extends HttpServlet {
HttpSession session;
String districtid,partnerid,groupid,data;
int position,pos;
String countyname,districtname,partnername,groupname,fname,mname,lname,age,gender,clientid;
String provider,sMname,sFname,sLname;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
          districtid=request.getParameter("district");
          partnerid=request.getParameter("partner");
          groupid=request.getParameter("group");
          pos=0;
          data="<thead>"
                  + "<tr>"
                  + "<th>NO</th>"
                  + "<th>COUNTY NAME</th>"
                  + "<th>PARTNER NAME</th>"
                  + "<th>DISTRICT NAME</th>"
                  + "<th>GROUP NAME <font color=\"red\">*</font></th>"
                  + "<th>PROVIDER NAME <font color=\"red\">*</font></th>"
                  + "<th>FIRST NAME <font color=\"red\">*</font></th>"
                  + "<th>MIDDLE NAME <font color=\"red\">*</font></th>"
                  + "<th>LAST NAME <font color=\"red\">*</font></th>"
                  + "<th>AGE <font color=\"red\">*</font></th>"
                  + "<th>GENDER <font color=\"red\">*</font></th>"
                  + "</tr>"
                  + "</thead>";
          String getClients="SELECT county.county_name,partner.partner_name,district.district_name,group_id,clients.age, "
        + "clients.gender,clients.fname,clients.mname,clients.lname,clients.client_id,service_provider.fname,service_provider.mname,service_provider.lname FROM clients JOIN partner ON clients.partner_id=partner.partner_id "
        + "JOIN (district JOIN county ON district.county_id=county.county_id )ON district.district_id=clients.district_id "
                  + "JOIN service_provider ON service_provider.provider_id=clients.provider_id "
                  + "WHERE clients.partner_id='"+partnerid+"' && clients.district_id='"+districtid+"' && clients.group_id='"+groupid+"'"
                  + "ORDER BY clients.fname,clients.mname,clients.lname";
          conn.rs=conn.st.executeQuery(getClients);
          while(conn.rs.next()){
              gender="";pos++;
             countyname=conn.rs.getString(1);
             partnername=conn.rs.getString(2);
             districtname=conn.rs.getString(3);
           groupid=conn.rs.getString(4);
             age=conn.rs.getString(5);
         gender=conn.rs.getString(6);
             fname=conn.rs.getString(7);
             mname=conn.rs.getString(8);
             lname=conn.rs.getString(9);
             clientid=conn.rs.getString(10);
             sFname=conn.rs.getString(11);
             sMname=conn.rs.getString(12);
             sLname=conn.rs.getString(13);
             if(mname.equals(lname)){mname="";}
             if(sMname.equals(sLname)){sMname="";}
             provider=sFname+" "+sMname+" "+sLname;
             if(groupid.equals("0")){
                 groupname="INDIVIDUAL";
             }
             else{
                 String getGroups="SELECT group_name FROM groups WHERE group_id='"+groupid+"'";
                 conn.rs1=conn.st1.executeQuery(getGroups);
                 while(conn.rs1.next()){
                     groupname=conn.rs1.getString(1);
                 }
             }
             
//             String getGender="SELECT * FROM gender";
//             conn.rs1=conn.st1.executeQuery(getGender);
//             while(conn.rs1.next()){
//                 if(sex.equals(conn.rs1.getString(2))){
//                     gender+="<option value=\""+conn.rs1.getString(2)+"\" selected>"+conn.rs1.getString(2)+"</option>";
//                 }
//                 else{
//                 gender+="<option value=\""+conn.rs1.getString(2)+"\">"+conn.rs1.getString(2)+"</option>";    
//                 }
//             }
             
//         ADD THIS DATA TO THE OUTPUT=====================================
              data+=""
                      + "<tr id=\""+clientid+"\">"
                      + "<td>"+pos+"</td>"
                      + "<td>"+countyname+"</td>"
                      + "<td>"+partnername+"</td>"
                      + "<td>"+districtname+"</td>"
                      + "<td>"+groupname+"</td>"
                      + "<td>"+provider+"</td>"
                      + "<td>"+fname+"</td>"
                      + "<td>"+mname+"</td>"
                      + "<td>"+lname+"</td>"
                      + "<td>"+age+"</td>"
                      + "<td>"+gender+"</td>"
                      + "</tr>"
                      + "";
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

            out.println(data);
           System.out.println(data);
            
            
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
        Logger.getLogger(loadEditClients.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditClients.class.getName()).log(Level.SEVERE, null, ex);
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

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
public class loadEditProviders extends HttpServlet {
HttpSession session;
String partnername,groups,countyname,districtname;
String fname,mname,lname,phone,data,groupnames,providerid,providername;
String [] groupids;
int pos,total;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session=request.getSession();
           dbConn conn = new dbConn();
           
           pos=0;
           
            data="<thead>"
                    + "<tr>"
                   + "<td>NO</td>"
                   + "<td>COUNTY NAME</td>"
                   + "<td>PARTNER NAME</td>"
                   + "<td>GROUPS</td>"
                   + "<td>FIRST NAME <font color=\"red\">*</font></td>"
                   + "<td>MIDDLE NAME <font color=\"red\">*</font></td>"
                   + "<td>LAST NAME <font color=\"red\">*</font></td>"
                   + "<td>PHONE NUMBER <font color=\"red\">*</font></td>"
                   + "<td>NO. OF CLIENTS</td>"
                   + "</tr>"
                    + "</thead>"; 
           
           
           
           String selectprovider="SELECT county.county_name,partner.partner_name,service_provider.fname,service_provider.mname,"
                   + "service_provider.lname,service_provider.group_ids,service_provider.provider_id,service_provider.phone FROM service_provider "
                   + "JOIN partner ON service_provider.partner_id=partner.partner_id "
                   + "JOIN (district JOIN county ON county.county_id=district.county_id) ON district.district_id=service_provider.district_id";
           conn.rs=conn.st.executeQuery(selectprovider);
           while(conn.rs.next()){
               pos++;
               groupnames=partnername=countyname=fname=mname=lname="";
               countyname=conn.rs.getString(1);
               partnername=conn.rs.getString(2);
               fname=conn.rs.getString(3);
               mname=conn.rs.getString(4);
               lname=conn.rs.getString(5);
             groupids=conn.rs.getString(6).split(","); 
             providerid=conn.rs.getString(7);
             phone=conn.rs.getString(8);
               total=0;
          String getMembers="SELECT COUNT(personal_information.group_id) FROM personal_information WHERE provider_id='"+providerid+"'";
          conn.rs1=conn.st1.executeQuery(getMembers);
          if(conn.rs1.next()==true){
              total=conn.rs1.getInt(1);
          }
             if(mname.equals(lname)){mname="";}
             
             for(String groupid: groupids){
              if(!(groupid.equals("") || groupid.equals(","))){
                 if(groupid.equals("0")){
                  groupnames+="INDIVIDUAL,<br>";   
                 }
                 else{
                     String getName="SELECT group_name FROM groups WHERE group_id='"+groupid+"'";
                         conn.rs1=conn.st1.executeQuery(getName);
                         while(conn.rs1.next()==true){
                             if(!conn.rs1.getString(1).contains("INDIVIDUAL")){
                         groupnames+=conn.rs1.getString(1)+",<br>";
                             }
                         }
                 }
                  
              }   
             }
//          OUTPUT DATA HERE==============================================   
          if(total>0){
             data+="<tr id=\""+providerid+"\">"
                   + "<td>"+pos+"</td>"
                   + "<td>"+countyname+"</td>"
                   + "<td>"+partnername+"</td>"
                   + "<td>"+groupnames+"</td>"
                   + "<td>"+fname+"</td>"
                   + "<td>"+mname+"</td>"
                   + "<td>"+lname+"</td>"
                   + "<td>"+phone+"</td>"
                   + "<td>"+total+"</td>"
                   + "</tr>";  
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

//           System.out.println(data);
           out.println(data);
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
        Logger.getLogger(loadEditProviders.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditProviders.class.getName()).log(Level.SEVERE, null, ex);
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

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
public class loadEditGroup extends HttpServlet {
HttpSession session;
String data,partnername,countyname,districtname,groupname,yearformed,location,groupid;
int pos,total,counter;
String partnerid,countyid,partnerids,wards,wardid;
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
                   + "<td>DISTRICT NAME</td>"
                   + "<td>GROUP NAME <font color=\"red\">*</font></td>"
                   + "<td>WARD <font color=\"red\">*</font></td>"
//                   + "<td>YEAR FORMED <font color=\"red\">*</font></td>"
                   + "<td>NO. OF CLIENTS</td>"
                   + "</tr>"
                   + "</thead>";
           
           String getGroups="SELECT county.county_name,partner.partner_name,district.district_name,"
                   + "groups.group_name,groups.location,groups.year_formed,groups.group_id,groups.ward_id,county.county_id,partner.partner_id,COUNT(personal_information.client_id) "
                   + "FROM personal_information LEFT JOIN groups ON personal_information.group_id=groups.group_id "
                   + "LEFT JOIN partner ON groups.partner_id=partner.partner_id "
                   + "LEFT JOIN (district JOIN county ON county.county_id=district.county_id) "
                   + "ON district.district_id=groups.district_id GROUP BY groups.group_id,district.district_name";
           conn.rs=conn.st.executeQuery(getGroups);
           while(conn.rs.next()){
               pos++;
          countyname=conn.rs.getString(1);
          partnername=conn.rs.getString(2);
          districtname=conn.rs.getString(3);
          if(conn.rs.getString(4)!=null){groupname=conn.rs.getString(4);}
          else{groupname="INDIVIDUAL";}
          location=conn.rs.getString(5);
          yearformed=conn.rs.getString(6);
          groupid=conn.rs.getString(7);
          wardid=conn.rs.getString(8);
          countyid=conn.rs.getString(9);
          partnerid=conn.rs.getString(10);
          total=conn.rs.getInt(11);
//          
//          String getMembers="SELECT COUNT(personal_information.group_id) FROM personal_information WHERE group_id='"+groupid+"'";
//          conn.rs1=conn.st1.executeQuery(getMembers);
//          if(conn.rs1.next()==true){
//              total=conn.rs1.getInt(1);
//          }
          wards="<select name=\"ward_"+pos+"\" id=\"ward_"+pos+"\" onchange=\"return addWard(ward_"+pos+");\">";
          wards+="<option value=\"0-0\">Choose Ward</option>"; 
         String getWards="SELECT id,name,partner_ids FROM wards WHERE county_id='"+countyid+"'";
           conn.rs2=conn.st2.executeQuery(getWards);
           while(conn.rs2.next()){
            partnerids=conn.rs2.getString(3);
           if(partnerids.contains(","+partnerid+",")){
           if(conn.rs2.getString(1).equals(wardid)){
               wards+="<option value=\""+groupid+"-"+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
           }
           else{
             wards+="<option value=\""+groupid+"-"+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";        
           }
               counter++;   
           }
           }  
          wards+="</select>";
           System.out.println("wards : "+wards);
    //  ADDTO THE OUTPUT===============================
           if(conn.rs.getString(4)!=null){
          data+="<tr id=\""+groupid+"\">"
          + "<td>"+pos+"</td>"
          + "<td>"+countyname+"</td>"
          + "<td>"+partnername+"</td>"
          + "<td>"+districtname+"</td>"
          + "<td>"+groupname+"</td>"
          + "<td>"+wards+"</td>"
//          + "<td>"+location+"</td>"
//          + "<td>"+yearformed+"</td>"
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
        Logger.getLogger(loadEditGroup.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditGroup.class.getName()).log(Level.SEVERE, null, ex);
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

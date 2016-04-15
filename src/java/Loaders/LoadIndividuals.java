/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Loaders;

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
public class LoadIndividuals extends HttpServlet {
HttpSession session;
String data;
String fullname,fname,mname,lname,id,groupname,provider,client_id;
String pfname,pmname,plname,partner_id,district_id,partner,district;
int pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session=request.getSession();
          dbConn conn = new dbConn();
           partner_id=session.getAttribute("partner_id").toString();
          district_id=session.getAttribute("district_id").toString();
//          partner_id="1";
//          district_id="4";
          pos=0;
         data="<thead>"
                  + "<tr>"
                  + "<th>NO</th>"
                  + "<th>PARTNER NAME</th>"
                  + "<th>DISTRICT NAME</th>"
                  + "<th>GROUP NAME</th>"
                  + "<th>PROVIDER NAME</th>"
                  + "<th>CLIENT NAME</th>"
                  + "<th>EDIT<input type=\"checkbox\" name=\"checkall\" id=\"checkall\"></th>"
                  + "</tr>"
                  + "</thead>";
          
          String getPartner="SELECT partner_name FROM partner WHERE partner_id='"+partner_id+"'";
          conn.rs=conn.st.executeQuery(getPartner);
          if(conn.rs.next()==true){
              partner=conn.rs.getString(1);
          }
          
          String getDistrict="SELECT district_name FROM district WHERE district_id='"+district_id+"'";
          conn.rs=conn.st.executeQuery(getDistrict);
          if(conn.rs.next()==true){
              district=conn.rs.getString(1);
          }
          
          String getClients="SELECT client_id,personal_information.fname,personal_information.mname,personal_information.lname,"
                  + "service_provider.fname,service_provider.mname,service_provider.lname FROM "
                  + "personal_information JOIN service_provider ON personal_information.provider_id=service_provider.provider_id "
                  + "WHERE personal_information.partner_id='"+partner_id+"' && group_id='0' && status='ACTIVE' && personal_information.district_id='"+district_id+"' "
                  + "ORDER BY personal_information.fname,personal_information.mname,personal_information.lname ASC";
           conn.rs=conn.st.executeQuery(getClients);
           while(conn.rs.next()){
           client_id=conn.rs.getString(1);
           fname=conn.rs.getString(2);
           mname=conn.rs.getString(3);
           lname=conn.rs.getString(4);
           
           pfname=conn.rs.getString(5);
           pmname=conn.rs.getString(6);
           plname=conn.rs.getString(7);
           
           if(mname.equals(lname)){
          mname="";     
           }
           if(pmname.equals(plname)){
          pmname="";     
           }
           
           provider=pfname+" "+pmname+" "+plname;
          fullname=fname+" "+mname+" "+lname;
          groupname="INDIVIDUAL";
          pos++;
          data+=""
                      + "<tr>"
                      + "<td>"+pos+"<input type=\"hidden\" name=\"id_"+pos+"\" id=\"id_"+pos+"\" value=\""+client_id+"\"></td>"
                      + "<td>"+partner+"</td>"
                      + "<td>"+district+"</td>"
                      + "<td>"+groupname+"</td>"
                      + "<td>"+provider+"</td>"
                      + "<td>"+fullname+"</td>"
                      + "<td><input type=\"checkbox\" name=\"checker_"+pos+"\" value=\"data\" id=\"checker_"+pos+"\"></td>"
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
        Logger.getLogger(LoadIndividuals.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadIndividuals.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataQuality;

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
public class loadClients extends HttpServlet {
HttpSession session;
String county,district,hf,partner,groupname,serviceprovider,clientname,age,gender,groupings,year,providerid;
String countyid,districtid,hfid,partnerid,groupid,serviceproviderid,clientid;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
String cm,rsp,cd,tb,sti,testedpartner,testedchild,session_no,value,status;
int sess,val,cds=0,i;
ArrayList AList = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        AList.clear();
        
      String getClients="SELECT * FROM clients";
        conn.rs=conn.st.executeQuery(getClients);
       while(conn.rs.next()){
  county=district=hf=partner=groupname=serviceprovider=clientname=age=gender=groupings=year=providerid="";
 countyid=districtid=hfid=partnerid=groupid=serviceproviderid=clientid="";
 s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="";
 cm=rsp=cd=tb=sti=testedpartner=testedchild=session_no=value=status;
 sess=val=cds=0;
      clientid=conn.rs.getString(1);
          clientname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
          age=conn.rs.getString(5);
          gender=conn.rs.getString(6);
          groupid=conn.rs.getString(7);
          groupings=conn.rs.getString(8);
          districtid=conn.rs.getString(9);
          partnerid=conn.rs.getString(10);
          year=conn.rs.getString(13);
          providerid=conn.rs.getString(14); 
         if(conn.rs.getString(3).equals(conn.rs.getString(4))){
         clientname=conn.rs.getString(2)+" "+conn.rs.getString(4);    
         }
         cds=conn.rs.getInt("lessons_attended");
         
     String getCnt="SELECT district.district_name,county.county_name FROM district JOIN county ON district.county_id=county.county_id WHERE district.district_id='"+districtid+"'";  
     conn.rs1=conn.st1.executeQuery(getCnt);
     if(conn.rs1.next()==true){
         district=conn.rs1.getString(1);
         county=conn.rs1.getString(2);
     }
     String getPart="SELECT partner_name FROM partner WHERE partner_id='"+partnerid+"'";
     conn.rs1=conn.st1.executeQuery(getPart);
     if(conn.rs1.next()==true){
         partner=conn.rs1.getString(1);
     }
      if(!groupid.equals("0")){
     String getgrp="SELECT groups.group_name,health_facility.hf_name FROM groups JOIN health_facility ON groups.nhf_id=health_facility.hf_id"
             + " WHERE groups.group_id='"+groupid+"'";
     conn.rs1=conn.st1.executeQuery(getgrp);
     if(conn.rs1.next()==true){
         groupname=conn.rs1.getString(1);
         hf=conn.rs1.getString(2);
     }
       }
     if(groupid.equals("0")){
     String getgrp1="SELECT health_facility.hf_name FROM no_group JOIN health_facility ON no_group.nhf_id=health_facility.hf_id"
             + " WHERE no_group.name='"+groupings+"'";
     conn.rs1=conn.st1.executeQuery(getgrp1);
     if(conn.rs1.next()==true){
         groupname="INDIVIDUAL";
         hf=conn.rs1.getString(1);
     }
     }   
//    ADD TO THE        
     
   Clients CL = new Clients();
    CL.setAge(age);
    CL.setAttended(cds);
    CL.setClientname(clientname);
    CL.setCounty(county);
    CL.setDistrict(district);
    CL.setGender(gender);
    CL.setGroup(groupname);
    CL.setPartner(partner);
    CL.setClientid(clientid);
    
    AList.add(CL);
     
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
       
       
       session.setAttribute("AList", AList);
       response.sendRedirect("viewClients.jsp");
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
        Logger.getLogger(loadClients.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadClients.class.getName()).log(Level.SEVERE, null, ex);
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

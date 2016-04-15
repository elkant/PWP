/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
public class saveEditedClientForm extends HttpServlet {
HttpSession session;
String found,client_id,fname,mname,lname,district_id,location,national_id,mobile_no,gender,dob,marital_status;
String employment_status,education_level,under_18,ovc_children;
String has_group,group_id,provider_id;
String hiv_year,art_status,hf_id,ccc_no;
String registration_date,approved_by,designation,approval_date;
String fullname,status,partner_id;
String new_group_name;
String pfname,pmname,plname,pnationalID,pmobileNO,timestamp,dic_id,ward_id;
String group_status,provider_status,existingGroups;
String cfullname,cfname,cmname,clname;
String linked_groupid,ifLinked;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
      dbConn conn = new dbConn();
     existingGroups="";
 found=client_id=fname=mname=lname=district_id=location=national_id=mobile_no=gender=dob=marital_status="";
 employment_status=education_level=under_18=ovc_children="";
 has_group=group_id=provider_id=partner_id="";
 hiv_year=art_status=hf_id=ccc_no="";
registration_date=approved_by=designation=approval_date="";
  fullname=status=""; 
  linked_groupid=ifLinked="";
        IdGenerator IG = new IdGenerator();
  timestamp=IG.toDay();
  client_id=request.getParameter("client_id");
        fname=request.getParameter("fname").toUpperCase();
        mname=request.getParameter("mname").toUpperCase();
        lname=request.getParameter("lname").toUpperCase();
        district_id=request.getParameter("district");
        location=request.getParameter("location").toUpperCase();
        national_id=request.getParameter("nationalID");
        mobile_no=request.getParameter("mobileNO");
        gender=request.getParameter("gender");
        dob=request.getParameter("dob");
        marital_status=request.getParameter("maritalStatus");
        
        
       employment_status=request.getParameter("employment");
       education_level=request.getParameter("education");
       under_18=request.getParameter("under_18");
       ovc_children=request.getParameter("ovc_children");
  
       if(under_18.equals("")){under_18="0";}
       if(ovc_children.equals("")){ovc_children="0";}
       
       has_group=request.getParameter("client_messages");
       ifLinked=request.getParameter("linked_to_group");
       group_status=request.getParameter("group_status");
       provider_status=request.getParameter("provider_status");
       group_id=request.getParameter("group_name");
       provider_id=request.getParameter("service_provider");
       partner_id=request.getParameter("partner_name");
       System.out.println("group id is :"+group_id);
       if(has_group.equals("no")){ group_id="0";}
      if(ifLinked.equals("no")){linked_groupid="";group_id="0";}else{linked_groupid=request.getParameter("group_name");}
      
      hiv_year=request.getParameter("year_confirmed");
      art_status=request.getParameter("art_status");
      hf_id=request.getParameter("health_facility");
      ccc_no=request.getParameter("ccc_no"); 
      
      registration_date=request.getParameter("registration_date");
      approved_by=request.getParameter("approved_by");
      designation=request.getParameter("designation");
      approval_date=request.getParameter("approval_date");
      
      
      new_group_name=request.getParameter("new_group_name").toUpperCase();
      
      pfname=request.getParameter("pfname").toUpperCase();
      pmname=request.getParameter("pmname").toUpperCase();
      plname=request.getParameter("plname").toUpperCase();
      pnationalID=request.getParameter("pnationalID");
      pmobileNO=request.getParameter("pmobileNO");
      
      dic_id=request.getParameter("dic");
      ward_id=request.getParameter("ward");
      
     cfullname=cfname=cmname=clname="";
      
     
      if(ifLinked.equals("yes")){
          if(group_status.equals("no")){
      String checkGROUP="SELECT group_id FROM groups WHERE group_name=?";
      conn.pst=conn.conn.prepareStatement(checkGROUP);
      conn.pst.setString(1, new_group_name);
      
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()==true){
      group_id=conn.rs.getString(1);
      linked_groupid=group_id;
       if(has_group.equals("no")){ group_id="0";}
       System.out.println("group exist====================");
      }
      else{
           System.out.println("adding a new group====================");
          IdGenerator IGrp = new IdGenerator();
          group_id=IGrp.current_id();
          linked_groupid=group_id;
           if(has_group.equals("no")){ group_id="0";}
          String addGroup="INSERT INTO groups (group_id,group_name,partner_id,district_id,nhf_id,location,year_formed,timestamp)"
                  + "VALUES(?,?,?,?,?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(addGroup);
        conn.pst.setString(1, group_id);
        conn.pst.setString(2, new_group_name);
        conn.pst.setString(3, partner_id);
        conn.pst.setString(4, district_id);
        conn.pst.setString(5, hf_id);
        conn.pst.setString(6, location);
        conn.pst.setString(7, "0");
        conn.pst.setString(8, timestamp);
        conn.pst.executeUpdate();
      }
      }  
    }
    if(provider_status.equals("no")){
      String checkprovider="SELECT provider_id,group_ids FROM service_provider WHERE (fname=? && lname=?) || (national_id=? && national_id!=?)";
      conn.pst=conn.conn.prepareStatement(checkprovider);
      conn.pst.setString(1, pfname);
      conn.pst.setString(2, plname);
      conn.pst.setString(3, pnationalID);
      conn.pst.setString(4, "0");
      
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()==true){
          provider_id=conn.rs.getString(1);
          existingGroups=conn.rs.getString(2);
          if(!existingGroups.contains(","+group_id+",")){
          existingGroups+=group_id+",";  
          String updateGroups="UPDATE service_provider SET group_ids=? WHERE provider_id=?";
          conn.pst1=conn.conn.prepareStatement(updateGroups);
          conn.pst1.setString(1, existingGroups);
          conn.pst1.setString(2, provider_id);
          conn.pst1.executeUpdate();
          }
          System.out.println("Provider_exist====================");
      }
      
      else{
           System.out.println("new Provider====================");
          IdGenerator IP = new IdGenerator();
          provider_id=IP.current_id();
          String addProvider="INSERT INTO service_provider (provider_id,fname,mname,lname,phone,group_ids,partner_id,district_id,timestamp,national_id)"
                  + "VALUES(?,?,?,?,?,?,?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(addProvider);
          conn.pst.setString(1, provider_id);
          conn.pst.setString(2, pfname);
          conn.pst.setString(3, pmname);
          conn.pst.setString(4, plname);
          conn.pst.setString(5, pmobileNO);
          conn.pst.setString(6, ","+group_id+",");
          conn.pst.setString(7, partner_id);
          conn.pst.setString(8, district_id);
          conn.pst.setString(9, timestamp);
          conn.pst.setString(10, pnationalID);
          
          conn.pst.executeUpdate();
      }
    }
    else{
          String getProviderId="SELECT group_ids FROM service_provider WHERE provider_id='"+provider_id+"'";
         conn.rs=conn.st.executeQuery(getProviderId);
         if(conn.rs.next()==true){
         existingGroups=conn.rs.getString(1);    
        if(!existingGroups.contains(","+group_id+",")){
          existingGroups+=group_id+",";  
          String updateGroups="UPDATE service_provider SET group_ids=? WHERE provider_id=?";
          conn.pst1=conn.conn.prepareStatement(updateGroups);
          conn.pst1.setString(1, existingGroups);
          conn.pst1.setString(2, provider_id);
          conn.pst1.executeUpdate();
          }
         }
     
    }
    
      String checkIfRegistered="SELECT client_id FROM personal_information WHERE (national_id=? && national_id!=?) || (ccc_no=?) || (client_id=?) ";
     conn.pst=conn.conn.prepareStatement(checkIfRegistered);
//     conn.pst.setString(1, fname);
//      conn.pst.setString(2, lname);
      conn.pst.setString(1, national_id);
      conn.pst.setString(2, "0");
      conn.pst.setString(3, ccc_no);
      conn.pst.setString(4, client_id);
      
      conn.rs=conn.pst.executeQuery();
      if(conn.rs.next()==true){
          found=conn.rs.getString(1);
      }
      
      if(found.length()>0){
//         CHECK IF CCC NO ALREADY ASSIGNED TO ANOTHER PERSON=============
        
          String checker="SELECT fname,mname,lname FROM personal_information WHERE (ccc_no=? && client_id!=?) || (national_id!=? && national_id!=? && national_id=? && client_id!=?)";
          conn.pst1=conn.conn.prepareStatement(checker);
          conn.pst1.setString(1, ccc_no);
          conn.pst1.setString(2, client_id);
          conn.pst1.setString(3, "");
          conn.pst1.setString(4, "0");
          conn.pst1.setString(5, national_id);
          conn.pst1.setString(6, client_id);
          
          conn.rs1=conn.pst1.executeQuery();
          if(conn.rs1.next()==true){
              
             cfname=conn.rs1.getString(1);
             cmname=conn.rs1.getString(2);
             clname=conn.rs1.getString(3);
             
             if(cmname.equals(clname)){
                 cfullname=cfname+" "+clname;
             }
             else{
              cfullname=cfname+" "+cmname+" "+clname;    
             }
          }
          System.out.print("found existing client ==============+"+ cfullname);
          System.out.println("district id : "+district_id);
//       ADD PERSONA INFORMATION==============================
          System.out.println("group_id  : "+group_id);
          
          if(cfullname.equals("")){
       System.out.println("updating existing client data====================");
    String add_Client="UPDATE personal_information SET "
+ "fname=?,mname=?,lname=?,district_id=?,location=?,national_id=?,mobile_no=?,gender=?,dob=?,marital_status=?,"
+ "employment_status=?,education_level=?,under_18s=?,ovc_children=?,group_id=?,provider_id=?,partner_id=?,"
+ "hiv_year=?,art_status=?,hf_id=?,ccc_no=?,registration_date=?,approved_by=?,designation=?,approval_date=?,"
            + "timestamp=?,dic_id=?,ward_id=?,linked_group=? "
    + "WHERE client_id=?";
          conn.pst=conn.conn.prepareStatement(add_Client);
          
          conn.pst.setString(1, fname);
          conn.pst.setString(2, mname);
          conn.pst.setString(3, lname);
          conn.pst.setString(4, district_id);
          conn.pst.setString(5, location);
          conn.pst.setString(6, national_id);
          conn.pst.setString(7, mobile_no);
          conn.pst.setString(8, gender);
          conn.pst.setString(9, dob);
          conn.pst.setString(10, marital_status);
          conn.pst.setString(11, employment_status);
          conn.pst.setString(12, education_level);
          conn.pst.setString(13, under_18);
          conn.pst.setString(14, ovc_children);
          conn.pst.setString(15, group_id);
          conn.pst.setString(16, provider_id);
          conn.pst.setString(17, partner_id);
          conn.pst.setString(18, hiv_year);
          conn.pst.setString(19, art_status);
          conn.pst.setString(20, hf_id);
          conn.pst.setString(21, ccc_no);
          conn.pst.setString(22, registration_date);
          conn.pst.setString(23, approved_by);
          conn.pst.setString(24, designation);
          conn.pst.setString(25, approval_date);
          conn.pst.setString(26, timestamp);
          conn.pst.setString(27, dic_id);
          conn.pst.setString(28, ward_id);
          conn.pst.setString(29, linked_groupid);
          conn.pst.setString(30, client_id);
          
          conn.pst.executeUpdate();
          
        fullname=fname+" "+mname+" "+lname;   
          
     status="<font color=\"green\">"+fullname+" data was edited successfully.</font>" ;    
          
      }
          else{
          status="<font color=\"red\"><b>FAILED : </b></font><font color=\"black\"> Either ID Number("+national_id+") or CCC NO ("+ccc_no+") has been registered to : <b>"+cfullname+"</b>.</font>" ;    
           }   
          
      }
      else{
          status="<font color=\"red\">"+fullname+" was not edited successfully.</font>" ;
      }
      
   session.setAttribute("editClient", status);
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
   response.sendRedirect("ChooseClient.jsp");
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
        Logger.getLogger(saveEditedClientForm.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(saveEditedClientForm.class.getName()).log(Level.SEVERE, null, ex);
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

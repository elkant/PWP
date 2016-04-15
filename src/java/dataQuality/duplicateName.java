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
public class duplicateName extends HttpServlet {
HttpSession session;
String county,district,hf,partner,groupname,serviceprovider,clientname,age,gender,groupings,year,providerid;
String countyid,districtid,hfid,partnerid,groupid,serviceproviderid,clientid;
String providername,startdate,enddate,start_date,end_date;
String cm,rsp,cd,tb,sti,testedpartner,testedchild,session_no,value,status;
int sess,val,cds=0,i,found,duplicate,pos;
String data,national_id,ccc_no,mobile_no,dob;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
          session=request.getSession();
            System.out.println("entered duplicate checker=========================");
          data="";
          String datatype=request.getParameter("data");
        System.out.println("level:   "+session.getAttribute("level"));
         System.out.println("here is the data that has been parsed by ajax   :    "+datatype);
         if(!datatype.equals("")){
             System.out.println("entered here : ");
          pos=0;
          data="<thead>"
                  + ""
                  + "<tr style=\"background-color:orange;\"><th><font color=\"black\"><b>No</b></font></th>"
                  + "<th><font color=\"black\"><b>COUNTY</b></font></th>"
                  + "<th><font color=\"black\"><b>PARTNER</b></font></th>"
                  + "<th><font color=\"black\"><b>DISTRICT</b></font></th>"
                   + "<th><font color=\"black\"><b>HEALTH FACILITY</b></font></th>"
                  + "<th><font color=\"black\"><b>GROUP</b></font></th>"
                  + "<th><font color=\"black\"><b>SERVICE PROVIDER</b></font></th>"
                  + "<th><font color=\"black\"><b>CLIENT NAME</b></font></th>"
                  + "<th><font color=\"black\"><b>AGE</b></font></th>"
                  + "<th><font color=\"black\"><b>CCC NO</b></font></th>"
                  + "<th><font color=\"black\"><b>NATIONAL ID</b></font></th>"
                  + "<th><font color=\"black\"><b>MOBILE NUMBER</b></font></th>"
                  +"<th><font color=\"black\"><b>SESSIONS ATTENDED</b></font></th>";
                //if(!session.getAttribute("level").toString().equals("2")){  
                if(1==1){  
                  data+="<th><font color=\"black\"><b>DELETE</b></font></th>";
                          }
                data+="</tr>"
                  + ""
                  + "</thead>";
        
                
         String getClients="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender,group_id,district_id,partner_id,provider_id,lessons_attended,national_id,ccc_no,mobile_no,dob,hf_id FROM personal_information"
                + " ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(getClients);
       while(conn.rs.next()){
 county=district=hf=partner=groupname=serviceprovider=clientname=age=gender=groupings=year=providerid="";
 countyid=districtid=hfid=partnerid=groupid=serviceproviderid=clientid="";
 sess=val=cds=duplicate=0;national_id=ccc_no=mobile_no=dob="";  
  startdate=enddate="";  
  providername="";
          clientid=conn.rs.getString(1);
          clientname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
          age=conn.rs.getString(5);
          gender=conn.rs.getString(6);
          
          groupid=conn.rs.getString(7);
          groupings=conn.rs.getString(8);
          districtid=conn.rs.getString(8);
          partnerid=conn.rs.getString(9);
          year=conn.rs.getString(13);
          providerid=conn.rs.getString(10); 
          sess=conn.rs.getInt(11);
         if(conn.rs.getString(3).equals(conn.rs.getString(4))){
         clientname=conn.rs.getString(2)+" "+conn.rs.getString(4);    
         }
       cds=conn.rs.getInt(11);
       national_id=conn.rs.getString(12);
       ccc_no=conn.rs.getString(13);
       mobile_no=conn.rs.getString(14);
       dob=conn.rs.getString(15);
       hfid=conn.rs.getString(16);
       start_date=end_date="";
       String serviceprov="SELECT fname,mname,lname FROM service_provider WHERE provider_id='"+providerid+"'";
       conn.rs1=conn.st1.executeQuery(serviceprov);
       if(conn.rs1.next()){
      if(conn.rs1.getString(2).equals(conn.rs1.getString(3))){
     providername=conn.rs1.getString(1)+" "+conn.rs1.getString(3);     
      }
      else{
      providername=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs1.getString(3);    
      }
       }
       
//       CODE TO DETERMINE THE OUTPUT HERE================
       if(datatype.equals("1")){
     String checker="SELECT COUNT(client_id) FROM personal_information WHERE fname=? && lname=? && client_id!=?";
     conn.pst=conn.conn.prepareStatement(checker);
     conn.pst.setString(1, conn.rs.getString(2));
     conn.pst.setString(2, conn.rs.getString(4));
     conn.pst.setString(3, clientid);
 
     conn.rs1=conn.pst.executeQuery();
     }
       else if(datatype.equals("2")){
     String checker="SELECT COUNT(client_id) FROM personal_information WHERE fname=? && lname=? && client_id!=? && dob=? && dob!=?";
     conn.pst=conn.conn.prepareStatement(checker);
     
     conn.pst.setString(1, conn.rs.getString(2));
     conn.pst.setString(2, conn.rs.getString(4));
     conn.pst.setString(3, clientid);
     conn.pst.setString(4, dob);
     conn.pst.setString(5, "");
     
     conn.rs1=conn.pst.executeQuery();
      }
        
      else   if(datatype.equals("3")){
     String checker="SELECT COUNT(client_id) FROM personal_information WHERE fname=? && lname=? && client_id!=? && partner_id=?";
     conn.pst=conn.conn.prepareStatement(checker);
     
     conn.pst.setString(1, conn.rs.getString(2));
     conn.pst.setString(2, conn.rs.getString(4));
     conn.pst.setString(3, clientid);
     conn.pst.setString(4, partnerid);
 
     conn.rs1=conn.pst.executeQuery();
     
       }
      
            else   if(datatype.equals("4")){
     String checker="SELECT COUNT(client_id) FROM personal_information WHERE ccc_no!='' && ccc_no='"+ccc_no+"' && client_id!='"+clientid+"'";
     conn.rs1=conn.st4.executeQuery(checker);
            }
            
                  else   if(datatype.equals("5")){
     String checker="SELECT COUNT(client_id) FROM personal_information WHERE national_id!='' && national_id='"+national_id+"' && client_id!='"+clientid+"'";
     conn.rs1=conn.st4.executeQuery(checker); }
                  
                        else   if(datatype.equals("6")){
     String checker="SELECT COUNT(client_id) FROM personal_information WHERE mobile_no!='' && mobile_no='"+mobile_no+"' && client_id!='"+clientid+"'";
     conn.rs1=conn.st4.executeQuery(checker);}
                        
      else{} 
     
     
     found=0;
     if(conn.rs1.next()==true){
         found=conn.rs1.getInt(1);
     }
//     System.out.println("found  :   "+found);
     
     if(found>0){
         duplicate=found;
     
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
          String gethf="SELECT health_facility.hf_name FROM health_facility WHERE hf_id='"+hfid+"'";
     conn.rs1=conn.st1.executeQuery(gethf);
     if(conn.rs1.next()==true){
        hf=conn.rs1.getString(1);
     }
     
      if(!groupid.equals("0")){
     String getgrp="SELECT groups.group_name FROM groups WHERE groups.group_id='"+groupid+"'";
     conn.rs1=conn.st1.executeQuery(getgrp);
     if(conn.rs1.next()==true){
         groupname=conn.rs1.getString(1);
 }
       }
     if(groupid.equals("0")){
     groupname="INDIVIDUAL";   
     }
     pos++;
//    ADD DATA TO THE OUTPUT==========================
      data+="<tr id=\""+pos+"\">"
                     + "<td class=\"sorting_1\">"+pos+"</td>"
                     + "<input type=\"hidden\" required class=\"textbox\" style=\"border-color: green;\" name=\"id"+pos+"\" id=\"id"+pos+"\" value=\""+clientid+"\">"
                     + "<td class=\"sorting_1\">"+county+"</td>"
                     + "<td class=\"sorting_1\">"+partner+"</td>"
                     + "<td class=\"sorting_1\">"+district+"</td>"
                     + "<td class=\"sorting_1\">"+hf+"</td>"
                     + "<td class=\"sorting_1\">"+groupname+"</td>"
                     + "<td class=\"sorting_1\">"+providername+"</td>"
                     + "<td class=\"sorting_1\">"+clientname+"</td>"
                     + "<td class=\"sorting_1\">"+age+"</td>"
                     + "<td class=\"sorting_1\">"+ccc_no+"</td>"
                     + "<td class=\"sorting_1\">"+national_id+"</td>"
                     + "<td class=\"sorting_1\">"+mobile_no+"</td>"
                     + "<td class=\"sorting_1\">"+sess+"</td>";
       //if(!session.getAttribute("level").toString().equals("2")){  
       if(1==1){  
           String datas=clientid+"-"+datatype;
      data+= "<td class=\"sorting_1\"><a class=\"linkstyle\" style=\"width:100px;\" href=\"#\" onclick=\"return deleter("+clientid+");\">DELETE</a></td>";
      
       }
       
                    data+="</tr>";
      
       }
     if(found==0){
         duplicate=0;
     }
          
       }  
//       System.out.println("EXITED DUPLICATE CHECKER====================");
         }
         else{
             data="";
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
//            System.out.println("data  :   "+data);
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
        Logger.getLogger(duplicateName.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(duplicateName.class.getName()).log(Level.SEVERE, null, ex);
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

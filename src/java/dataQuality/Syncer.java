/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataQuality;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class Syncer extends HttpServlet {
HttpSession session;
String startdate,enddate,clientid,attendance_status,sdate,ndate;
String countyname,partnername,groupname,providername,clientname,timestamp,districtname,gender;
int age,pos,sessionno,value;
String messagename,cm,rsp,screenedTB,ScreenedStis,screenedTb,testedpartner,testedChildren,discosedStatus="";
int givenCDS,pos1,pos2,pos3;
String [] sess;
String datekey,submissiondate;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       
      String [] dater1=request.getParameter("startdate").split("/");
      String [] dater2=request.getParameter("enddate").split("/");
      String [] bydate=request.getParameter("bydate").split("/");
      
   System.out.println("started");
   
     datekey=bydate[2]+""+bydate[1]+""+bydate[0];
      startdate=dater1[2]+"-"+dater1[1]+"-"+dater1[0];
      enddate=dater2[2]+"-"+dater2[1]+"-"+dater2[0];
      
     sdate=dater1[2]+"_"+dater1[1]+"_"+dater1[0];
     ndate=dater2[2]+"_"+dater2[1]+"_"+dater2[0];
      
     submissiondate=bydate[1]+"/"+bydate[0]+"/"+bydate[2];
     System.out.println(submissiondate);
 //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   HSSFWorkbook wb=new HSSFWorkbook();
//  HSSFSheet shet1=wb.createSheet("Client Enrollments");
  HSSFSheet shet2=wb.createSheet("Session Attendance");
  HSSFSheet shet3=wb.createSheet("Services Provided");
  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.LIME.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
HSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);
         
//   //  HSSFSheet sheet1 = wb.getSheetAt(0);
//    shet1.setColumnWidth(0, 4000); 
//    shet1.setColumnWidth(1, 4000); 
//    shet1.setColumnWidth(2, 4000); 
//    shet1.setColumnWidth(3, 7500);
//    shet1.setColumnWidth(4, 7000); 
//    shet1.setColumnWidth(5, 7300); 
//    shet1.setColumnWidth(6, 2500);
//    shet1.setColumnWidth(7, 3200); 
//    shet1.setColumnWidth(8, 4200); 
    
    shet2.setColumnWidth(0, 4000); 
    shet2.setColumnWidth(1, 4000); 
    shet2.setColumnWidth(2, 4000); 
    shet2.setColumnWidth(3, 6500);
    shet2.setColumnWidth(4, 7000); 
    shet2.setColumnWidth(5, 7300); 
    shet2.setColumnWidth(6, 2500);
    shet2.setColumnWidth(7, 3200); 
    shet2.setColumnWidth(8, 4200); 
    
    shet3.setColumnWidth(0, 4000); 
    shet3.setColumnWidth(1, 4000); 
    shet3.setColumnWidth(2, 4000); 
    shet3.setColumnWidth(3, 6500);
    shet3.setColumnWidth(4, 7000); 
    shet3.setColumnWidth(5, 7300); 
    shet3.setColumnWidth(6, 2500);
    shet3.setColumnWidth(7, 3200); 
    shet3.setColumnWidth(8, 4200); 
    shet3.setColumnWidth(9, 4200); 
    shet3.setColumnWidth(10, 4800); 
    shet3.setColumnWidth(11, 4000);  
    shet3.setColumnWidth(12, 4300); 
    shet3.setColumnWidth(13, 4000); 
    shet3.setColumnWidth(14, 4300); 
    shet3.setColumnWidth(15, 4000); 
    shet3.setColumnWidth(16, 4200); 
    shet3.setColumnWidth(17, 4200);
    
//   HSSFRow rw4=shet1.createRow(0);
//    rw4.setHeightInPoints(45);
//    rw4.setRowStyle(style2);
//// rw4.createCell(1).setCellValue("Number");
//    HSSFCell cell0,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8;
//   
//    cell0=rw4.createCell(0);
//    cell1=rw4.createCell(1);
//   cell2=rw4.createCell(2);
//   cell3=rw4.createCell(3);
//   cell4=rw4.createCell(4);
//   cell5=rw4.createCell(5);
//   cell6=rw4.createCell(6);
//   cell7=rw4.createCell(7);
//   cell8=rw4.createCell(8);
//   
//   
//   cell0 .setCellValue("County name");
// cell1.setCellValue("Partner name");
// cell2.setCellValue("District name");
// cell3.setCellValue("Group name");
// cell4.setCellValue("Service provider");
// cell5.setCellValue("Client name");
// cell6.setCellValue("Age");
// cell7.setCellValue("Gender");
// cell8.setCellValue("Timestamp");

//    cell0 .setCellStyle(stylex);
// cell1.setCellStyle(stylex);
// cell2.setCellStyle(stylex);
// cell3.setCellStyle(stylex);
// cell4.setCellStyle(stylex);
// cell5.setCellStyle(stylex);
// cell6.setCellStyle(stylex);
// cell7.setCellStyle(stylex);
// cell8.setCellStyle(stylex);
 
 HSSFRow rw2m=shet2.createRow(0);
    rw2m.setHeightInPoints(45);
    rw2m.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cellm0,cellm1,cellm2,cellm3,cellm4,cellm5,cellm6,cellm7,cellm8,cellm9,cellm10,cellmk11;
   
    cellm0=rw2m.createCell(0);
    cellm1=rw2m.createCell(1);
   cellm2=rw2m.createCell(2);
   cellm3=rw2m.createCell(3);
   cellm4=rw2m.createCell(4);
   cellm5=rw2m.createCell(5);
   cellm6=rw2m.createCell(6);
   cellm7=rw2m.createCell(7);
   cellm8=rw2m.createCell(8);
   cellm9=rw2m.createCell(9);
   cellm10=rw2m.createCell(10);
   cellmk11=rw2m.createCell(11);
   
 cellm0 .setCellValue("County name");
 cellm1.setCellValue("Partner name");
 cellm2.setCellValue("District name");
 cellm3.setCellValue("Group name");
 cellm4.setCellValue("Service provider");
 cellm5.setCellValue("Client name");
 cellm6.setCellValue("Age");
 cellm7.setCellValue("Gender");
 cellm8.setCellValue("Message");
 cellm9.setCellValue("Status");
 cellm10.setCellValue("Session Date");
 cellmk11.setCellValue("Timestamp");
 
 cellm0 .setCellStyle(stylex);
 cellm1.setCellStyle(stylex);
 cellm2.setCellStyle(stylex);
 cellm3.setCellStyle(stylex);
 cellm4.setCellStyle(stylex);
 cellm5.setCellStyle(stylex);
 cellm6.setCellStyle(stylex);
 cellm7.setCellStyle(stylex);
 cellm8.setCellStyle(stylex);
 cellm9.setCellStyle(stylex);
 cellm10.setCellStyle(stylex);
 cellmk11.setCellStyle(stylex);
 
 
 
 HSSFRow rw4n=shet3.createRow(0);
    rw4n.setHeightInPoints(45);
    rw4n.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell0n,cell1n,cell2n,cell3n,cell4n,cell5n,cell6n,cell7n,cell8n,cell9n,cell10n,cell11n,cell12n,cell13n,cell14n,cell15n,cell16n,cell17n,cell18n;
    cell0n=rw4n.createCell(0);
    cell1n=rw4n.createCell(1);
   cell2n=rw4n.createCell(2);
   cell3n=rw4n.createCell(3);
   cell4n=rw4n.createCell(4);
   cell5n=rw4n.createCell(5);
   cell6n=rw4n.createCell(6);
   cell7n=rw4n.createCell(7);
   cell8n=rw4n.createCell(8);
   cell9n=rw4n.createCell(9);
   cell10n=rw4n.createCell(10);
   cell11n=rw4n.createCell(11);
   cell12n=rw4n.createCell(12);
   cell13n=rw4n.createCell(13);
   cell14n=rw4n.createCell(14);
   cell15n=rw4n.createCell(15);
   cell16n=rw4n.createCell(16);
   cell17n=rw4n.createCell(17);
   cell18n=rw4n.createCell(18);
   
 cell0n .setCellValue("County name");
 cell1n.setCellValue("Partner name");
 cell2n.setCellValue("District name");
 cell3n.setCellValue("Group name");
 cell4n.setCellValue("Service provider");
 cell5n.setCellValue("Client name");
 cell6n.setCellValue("Age");
 cell7n.setCellValue("Gender");
 
 cell8n.setCellValue("Message (s)");
 cell9n .setCellValue("Received Contraceptives");
 cell10n.setCellValue("Reffered To Service Point");
 cell11n.setCellValue("Given Condoms");
 cell12n.setCellValue("Screened For TB");
 cell13n .setCellValue("Screened For STIs");
 cell14n.setCellValue("Partner Tested");
 cell15n.setCellValue("Children Tested");
 cell16n.setCellValue("Disclosed Status");
 cell17n .setCellValue("Submission date");
 cell18n .setCellValue("Timestamp");
 
  cell0n .setCellStyle(stylex);
 cell1n.setCellStyle(stylex);
 cell2n.setCellStyle(stylex);
 cell3n.setCellStyle(stylex);
 cell4n.setCellStyle(stylex);
 cell5n.setCellStyle(stylex);
 cell6n.setCellStyle(stylex);
 cell7n.setCellStyle(stylex);
 
 cell8n.setCellStyle(stylex);
 cell9n .setCellStyle(stylex);
 cell10n.setCellStyle(stylex);
 cell11n.setCellStyle(stylex);
 cell12n.setCellStyle(stylex);
 cell13n .setCellStyle(stylex);
 cell14n.setCellStyle(stylex);
 cell15n.setCellStyle(stylex);
 cell16n.setCellStyle(stylex);
 cell17n .setCellStyle(stylex);
 cell18n .setCellStyle(stylex);
      
pos1=1;      
 pos2=1;
 pos3=1;
//String getClientRegistration="SELECT county.county_name,partner.partner_name,district.district_name,group_id,clients.age,provider_id, "
//        + "clients.gender,clients.timestamp,clients.fname,clients.mname,clients.lname FROM clients JOIN partner ON clients.partner_id=partner.partner_id "
//        + "JOIN (district JOIN county ON district.county_id=county.county_id )ON district.district_id=clients.district_id "
//        + "WHERE STR_TO_DATE(clients.timestamp,'%Y-%m-%d') BETWEEN STR_TO_DATE('"+startdate+"','%Y-%m-%d') AND STR_TO_DATE('"+enddate+"','%Y-%m-%d')";
// conn.rs=conn.st.executeQuery(getClientRegistration);
// while(conn.rs.next()){
//    countyname=conn.rs.getString(1);
//     partnername=conn.rs.getString(2);
//     districtname=conn.rs.getString(3);
//     age=conn.rs.getInt(5);
//     gender=conn.rs.getString(7);
//     timestamp=conn.rs.getString(8);
//     
//     clientname=conn.rs.getString(9)+" "+conn.rs.getString(10)+" "+conn.rs.getString(11);
//         if(conn.rs.getString(10).equals(conn.rs.getString(11))){
//       clientname=conn.rs.getString(9)+" "+conn.rs.getString(11);       
//         }
//         
//     if(!conn.rs.getString(4).equals("0")){
//     String getGroupname="SELECT group_name FROM groups WHERE group_id='"+conn.rs.getString(4)+"'";
//     conn.rs1=conn.st1.executeQuery(getGroupname);
//     if(conn.rs1.next()==true){
//       groupname=conn.rs1.getString(1);
//     }
//     }
//     else{
//         groupname="INDIVIDUALS";
//     }
//     String getProvider="SELECT fname,mname,lname FROM service_provider WHERE provider_id='"+conn.rs.getString(6)+"'";
//     conn.rs1=conn.st1.executeQuery(getProvider);
//     if(conn.rs1.next()==true){
//         providername=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs1.getString(3);
//         if(conn.rs1.getString(2).equals(conn.rs1.getString(3))){
//       providername=conn.rs1.getString(1)+" "+conn.rs1.getString(3);       
//         }
//     }
////   ADD TO THE EXCELL OUTPUT..............................................................
//  
//    HSSFRow rw4x=shet1.createRow(pos1);
//    rw4x.setHeightInPoints(25);
//    rw4x.setRowStyle(style2);
//    HSSFCell cell0x,cell1x,cell2x,cell3x,cell4x,cell5x,cell6x,cell7x,cell8x;
//   cell0x=rw4x.createCell(0);
//   cell1x=rw4x.createCell(1);
//   cell2x=rw4x.createCell(2);
//   cell3x=rw4x.createCell(3);
//   cell4x=rw4x.createCell(4);
//   cell5x=rw4x.createCell(5);
//   cell6x=rw4x.createCell(6);
//   cell7x=rw4x.createCell(7);
//   cell8x=rw4x.createCell(8);
//   
//   //  OUTPUT SERVICES PROVIDED================================     
// cell0x .setCellValue(countyname);
// cell1x.setCellValue(partnername);
// cell2x.setCellValue(districtname);
// cell3x.setCellValue(groupname);
// cell4x.setCellValue(providername);
// cell5x.setCellValue(clientname);
// cell6x.setCellValue(age);
// cell7x.setCellValue(gender);
// cell8x.setCellValue(timestamp); 
// 
//  cell0x .setCellStyle(stborder);
// cell1x.setCellStyle(stborder);
// cell2x.setCellStyle(stborder);
// cell3x.setCellStyle(stborder);
// cell4x.setCellStyle(stborder);
// cell5x.setCellStyle(stborder);
// cell6x.setCellStyle(stborder);
// cell7x.setCellStyle(stborder);
// cell8x.setCellStyle(stborder);
//     
//     
//  pos1++;   
// }
      
//  CHECK SESSIONS ATTENDED==============================================================
 
 
 String getSessioner="SELECT county.county_name,partner.partner_name,district.district_name,group_id,clients.age,provider_id, "
        + "clients.gender,register2.timestamp,register2.session_no,register2.value,clients.fname,clients.mname,clients.lname,register2.date "
         + "FROM clients JOIN partner ON clients.partner_id=partner.partner_id "
        + "JOIN (district JOIN county ON district.county_id=county.county_id )ON district.district_id=clients.district_id "
         + "JOIN register2 ON register2.client_id=clients.client_id "
        + "WHERE  register2.value<'5' && register2.datekey<'"+datekey+"' && register2.datekey>'0' && STR_TO_DATE(register2.timestamp,'%Y-%m-%d') BETWEEN STR_TO_DATE('"+startdate+"','%Y-%m-%d') AND STR_TO_DATE('"+enddate+"','%Y-%m-%d')"
         + " ORDER BY clients.client_id ";   
  conn.rs=conn.st.executeQuery(getSessioner);
  while(conn.rs.next()){
    countyname=conn.rs.getString(1);
     partnername=conn.rs.getString(2);
     districtname=conn.rs.getString(3);
     age=conn.rs.getInt(5);
     gender=conn.rs.getString(7);
     timestamp=conn.rs.getString(8);
     sessionno=conn.rs.getInt(9);
     value=conn.rs.getInt(10);
//     String sessiondate=conn.rs.getString(14);
     
  String [] sessdt=conn.rs.getString(14).split("/");
  
  String  sessiondate=sessdt[2]+"-"+sessdt[0]+"-"+sessdt[1];
     if(value==1){attendance_status="Present";}
     else{attendance_status="Absent";}
     clientname=conn.rs.getString(11)+" "+conn.rs.getString(12)+" "+conn.rs.getString(13);
         if(conn.rs.getString(12).equals(conn.rs.getString(13))){
       clientname=conn.rs.getString(11)+" "+conn.rs.getString(13);       
         }
         
    if(!conn.rs.getString(4).equals("0")){
     String getGroupname="SELECT group_name FROM groups WHERE group_id='"+conn.rs.getString(4)+"'";
     conn.rs1=conn.st1.executeQuery(getGroupname);
     if(conn.rs1.next()==true){
       groupname=conn.rs1.getString(1);
     }
     }
     else{
         groupname="INDIVIDUALS";
     }
     String getProvider="SELECT fname,mname,lname FROM service_provider WHERE provider_id='"+conn.rs.getString(6)+"'";
     conn.rs1=conn.st1.executeQuery(getProvider);
     if(conn.rs1.next()==true){
         providername=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs1.getString(3);
         if(conn.rs1.getString(2).equals(conn.rs1.getString(3))){
       providername=conn.rs1.getString(1)+" "+conn.rs1.getString(3);       
         }
     }
     String getMessage="SELECT message FROM message_codes WHERE message_id='"+sessionno+"'";
     conn.rs1=conn.st1.executeQuery(getMessage);
     if(conn.rs1.next()){
         messagename=conn.rs1.getString(1);
     }
//   ADD TO THE EXCELL OUTPUT..............................................................
 HSSFRow rw2m1=shet2.createRow(pos2);
    rw2m1.setHeightInPoints(25);
    rw2m1.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cellm01,cellm11,cellm21,cellm31,cellm41,cellm51,cellm61,cellm71,cellm81,cellm91,cellm101,cellm111;
   
    cellm01=rw2m1.createCell(0);
    cellm11=rw2m1.createCell(1);
   cellm21=rw2m1.createCell(2);
   cellm31=rw2m1.createCell(3);
   cellm41=rw2m1.createCell(4);
   cellm51=rw2m1.createCell(5);
   cellm61=rw2m1.createCell(6);
   cellm71=rw2m1.createCell(7);
   cellm81=rw2m1.createCell(8);
   cellm91=rw2m1.createCell(9);
   cellm101=rw2m1.createCell(10);
   cellm111=rw2m1.createCell(11);
   
 cellm01 .setCellValue(countyname);
 cellm11.setCellValue(partnername);
 cellm21.setCellValue(districtname);
 cellm31.setCellValue(groupname);
 cellm41.setCellValue(providername);
 cellm51.setCellValue(clientname);
 cellm61.setCellValue(age);
 cellm71.setCellValue(gender);
 cellm81.setCellValue(messagename);
 cellm91.setCellValue(attendance_status);
 cellm101.setCellValue(sessiondate); 
 
 cellm111.setCellValue(timestamp); 
 
  cellm01 .setCellStyle(stborder);
 cellm11.setCellStyle(stborder);
 cellm21.setCellStyle(stborder);
 cellm31.setCellStyle(stborder);
 cellm41.setCellStyle(stborder);
 cellm51.setCellStyle(stborder);
 cellm61.setCellStyle(stborder);
 cellm71.setCellStyle(stborder);
 cellm81.setCellStyle(stborder);
 cellm91.setCellStyle(stborder);
 cellm101.setCellStyle(stborder);
 cellm111.setCellStyle(stborder);
     
pos2++; 
System.out.println("register record number : "+pos2);
  }
System.out.println("finished processing register2");
 //  CHECK SESSIONS ATTENDED==============================================================
 
 
 String getServices="SELECT county.county_name,partner.partner_name,district.district_name,group_id,clients.age,provider_id, "
        + "clients.gender,services_provided.timestamp,services_provided.session_no,services_provided.contraceptive_method,"
         + "services_provided.rsp,services_provided.cds_given,services_provided.screened_tb, services_provided.screened_stis, services_provided.tested_partner, services_provided.tested_children, services_provided.disclosed_status"
         + ",clients.fname,clients.mname,clients.lname,services_provided.submission_date"
         + " FROM clients JOIN partner ON clients.partner_id=partner.partner_id "
        + "JOIN (district JOIN county ON district.county_id=county.county_id )ON district.district_id=clients.district_id "
         + "JOIN services_provided ON services_provided.client_id=clients.client_id "
        + "WHERE STR_TO_DATE(services_provided.submission_date,'%m/%d/%Y')< STR_TO_DATE(services_provided.submission_date,'"+submissiondate+"') && STR_TO_DATE(services_provided.timestamp,'%Y-%m-%d') BETWEEN STR_TO_DATE('"+startdate+"','%Y-%m-%d') AND STR_TO_DATE('"+enddate+"','%Y-%m-%d') "
//         + " && (services_provided.contraceptive_method='YES' || services_provided.rsp || services_provided.cds_given>0 || services_provided.screened_tb='YES' || services_provided.screened_stis='YES' || services_provided.tested_partner='YES' || services_provided.tested_children='YES' || services_provided.disclosed_status='YES')"
         + " ORDER BY clients.client_id ";   
  conn.rs=conn.st.executeQuery(getServices);
  while(conn.rs.next()){
      cm=rsp=screenedTB=ScreenedStis=screenedTb=testedpartner=testedChildren=discosedStatus=messagename="";
      givenCDS=0;
    countyname=conn.rs.getString(1);
     partnername=conn.rs.getString(2);
     districtname=conn.rs.getString(3);
     age=conn.rs.getInt(5);
     gender=conn.rs.getString(7);
     timestamp=conn.rs.getString(8);
     sess=conn.rs.getString(9).split(",");
     cm=conn.rs.getString(10);
     rsp=conn.rs.getString(11);
     givenCDS=conn.rs.getInt(12);
     screenedTb=conn.rs.getString(13);
     ScreenedStis=conn.rs.getString(14);
     testedpartner=conn.rs.getString(15);
     testedChildren=conn.rs.getString(16);
     discosedStatus=conn.rs.getString(17);
   String submissiondate=conn.rs.getString(21);
   
     clientname=conn.rs.getString(18)+" "+conn.rs.getString(19)+" "+conn.rs.getString(20);
         if(conn.rs.getString(19).equals(conn.rs.getString(20))){
       clientname=conn.rs.getString(18)+" "+conn.rs.getString(20); 
       }
         
         for(String session1:sess){
             if(!(session1.equals("") || session1.equals(","))){
               String getMess="SELECT message FROM message_codes WHERE message_id='"+session1+"'";
               conn.rs1=conn.st1.executeQuery(getMess);
               if(conn.rs1.next()==true){
                  messagename+=conn.rs1.getString(1)+"\n";
               }
             }
         }
         
         
    if(!conn.rs.getString(4).equals("0")){
     String getGroupname="SELECT group_name FROM groups WHERE group_id='"+conn.rs.getString(4)+"'";
     conn.rs1=conn.st1.executeQuery(getGroupname);
     if(conn.rs1.next()==true){
       groupname=conn.rs1.getString(1);
     }
     }
     else{
         groupname="INDIVIDUALS";
     }
     String getProvider="SELECT fname,mname,lname FROM service_provider WHERE provider_id='"+conn.rs.getString(6)+"'";
     conn.rs1=conn.st1.executeQuery(getProvider);
     if(conn.rs1.next()==true){
         providername=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs1.getString(3);
         if(conn.rs1.getString(2).equals(conn.rs1.getString(3))){
       providername=conn.rs1.getString(1)+" "+conn.rs1.getString(3);       
         }
     }
    
//   ADD TO THE EXCELL OUTPUT..............................................................
  HSSFRow rw4n1=shet3.createRow(pos3);
    rw4n1.setHeightInPoints(25);
    rw4n1.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell0n1,cell1n1,cell2n1,cell3n1,cell4n1,cell5n1,cell6n1,cell7n1,cell8n1,cell9n1,cell10n1,cell11n1,cell12n1,cell13n1,cell14n1,cell15n1,cell16n1,cell17n1,cell18n1;
    cell0n1=rw4n1.createCell(0);
    cell1n1=rw4n1.createCell(1);
   cell2n1=rw4n1.createCell(2);
   cell3n1=rw4n1.createCell(3);
   cell4n1=rw4n1.createCell(4);
   cell5n1=rw4n1.createCell(5);
   cell6n1=rw4n1.createCell(6);
   cell7n1=rw4n1.createCell(7);
   cell8n1=rw4n1.createCell(8);
   cell9n1=rw4n1.createCell(9);
   cell10n1=rw4n1.createCell(10);
   cell11n1=rw4n1.createCell(11);
   cell12n1=rw4n1.createCell(12);
   cell13n1=rw4n1.createCell(13);
   cell14n1=rw4n1.createCell(14);
   cell15n1=rw4n1.createCell(15);
   cell16n1=rw4n1.createCell(16);
   cell17n1=rw4n1.createCell(17);
   cell18n1=rw4n1.createCell(18);
 cell0n1 .setCellValue(countyname);
 cell1n1.setCellValue(partnername);
 cell2n1.setCellValue(districtname);
 cell3n1.setCellValue(groupname);
 cell4n1.setCellValue(providername);
 cell5n1.setCellValue(clientname);
 cell6n1.setCellValue(age);
 cell7n1.setCellValue(gender);
 
 cell8n1.setCellValue(messagename);
 cell9n1.setCellValue(cm);
 cell10n1.setCellValue(rsp);
 cell11n1.setCellValue(givenCDS);
 cell12n1.setCellValue(screenedTb);
 cell13n1 .setCellValue(ScreenedStis);
 cell14n1.setCellValue(testedpartner);
 cell15n1.setCellValue(testedChildren);
 cell16n1.setCellValue(discosedStatus);
 cell17n1.setCellValue(submissiondate);
 
 cell18n1.setCellValue(timestamp); 
      
  cell0n1 .setCellStyle(stborder);
 cell1n1.setCellStyle(stborder);
 cell2n1.setCellStyle(stborder);
 cell3n1.setCellStyle(stborder);
 cell4n1.setCellStyle(stborder);
 cell5n1.setCellStyle(stborder);
 cell6n1.setCellStyle(stborder);
 cell7n1.setCellStyle(stborder);
 
 cell8n1.setCellStyle(stborder);
 cell9n1.setCellStyle(stborder);
 cell10n1.setCellStyle(stborder);
 cell11n1.setCellStyle(stborder);
 cell12n1.setCellStyle(stborder);
 cell13n1 .setCellStyle(stborder);
 cell14n1.setCellStyle(stborder);
 cell15n1.setCellStyle(stborder);
 cell16n1.setCellStyle(stborder);
 cell17n1.setCellStyle(stborder); 
 cell18n1.setCellStyle(stborder);
      
 pos3++; 
 System.out.println("services record number : "+pos3);
  } 
  
  System.out.println("finished all");
  
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

  // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_Raw_Data_Between_"+sdate.trim()+"_AND_"+ndate.trim()+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
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
        Logger.getLogger(Syncer.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Syncer.class.getName()).log(Level.SEVERE, null, ex);
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

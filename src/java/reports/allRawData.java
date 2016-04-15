/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class allRawData extends HttpServlet {
HttpSession session;
String county,district,hf,partner,groupname,serviceprovider,clientname,age,gender,groupings,year,providerid;
String countyid,districtid,hfid,partnerid,groupid,serviceproviderid,clientid,dob;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
String cm,rsp,cd,tb,sti,testedpartner,testedchild,session_no,value,status;
int sess,val,cds=0,i;
String  hf_id,lessons_attended,national_id,ccc_no,mobile_no; 
String reportType,partners,partner_ids;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
       reportType=request.getParameter("partnerAll");
       partner_ids="";
       if(reportType.equals("all_partners")){
           String getPartnerIDs="SELECT * FROM partner";
           conn.rs=conn.st.executeQuery(getPartnerIDs);
           while(conn.rs.next()==true){
               partner_ids+=conn.rs.getString(1)+",";
           }
       }
       if(reportType.equals("selected_partners")){
        String [] ids=request.getParameterValues("partner");
       for(String partid:ids){
           if(!partid.equals("")&& !partid.equals(",")){
            partner_ids+=partid+",";   
           }
       } 
       }
       
       System.out.println(" partner _ids are  : "+partner_ids);
        i=4;
        
       //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
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
    
    
    shet1.setColumnWidth(0, 5000); 
    shet1.setColumnWidth(1, 5000); 
    shet1.setColumnWidth(2, 5000); 
    shet1.setColumnWidth(3, 5500);
    
    shet1.setColumnWidth(4, 7000); 
    
    shet1.setColumnWidth(5, 5300); 
    shet1.setColumnWidth(6, 3000);
    shet1.setColumnWidth(7, 3200); 
    shet1.setColumnWidth(8, 3200); 
    shet1.setColumnWidth(9, 3200); 
    shet1.setColumnWidth(10, 3800); 
    shet1.setColumnWidth(11, 3000);  
    shet1.setColumnWidth(12, 5300); 
    
    shet1.setColumnWidth(13, 5000); 
    
    shet1.setColumnWidth(14, 5300); 
    shet1.setColumnWidth(15, 5000); 
    shet1.setColumnWidth(16, 5200); 
    shet1.setColumnWidth(17, 5200);
    shet1.setColumnWidth(18, 5200); 
    shet1.setColumnWidth(19, 5800); 
    shet1.setColumnWidth(20, 5000); 
    shet1.setColumnWidth(21, 5300);
    
    shet1.setColumnWidth(22, 5300); 
    shet1.setColumnWidth(23, 5000); 
    shet1.setColumnWidth(24, 5200); 
    shet1.setColumnWidth(25, 5200);
    shet1.setColumnWidth(26, 5200); 
    shet1.setColumnWidth(27, 5800); 
    shet1.setColumnWidth(28, 5000); 
    shet1.setColumnWidth(29, 5300);
    shet1.setColumnWidth(30, 5800); 
    shet1.setColumnWidth(31, 5000); 
    shet1.setColumnWidth(32, 5300);
    
//    shet1.setColumnWidth(20, 2000);
    HSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
   HSSFRow rw4=shet1.createRow(0);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell0,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16,cell17,cell18,cell19,cell20;
    HSSFCell cell21,cell22,cell23,cell24,cell25,cell26,cell27,cell28,cell29,cell30,cell31,cell32,cell33;
   
    cell0=rw4.createCell(0);
    cell1=rw4.createCell(1);
   cell2=rw4.createCell(2);
   cell3=rw4.createCell(3);
   cell4=rw4.createCell(4);
   cell5=rw4.createCell(5);
   cell6=rw4.createCell(6);
   cell7=rw4.createCell(7);
   cell8=rw4.createCell(8);
   cell9=rw4.createCell(9);
   cell10=rw4.createCell(10);
   cell11=rw4.createCell(11);
   cell12=rw4.createCell(12);
   cell13=rw4.createCell(13);
   cell14=rw4.createCell(14);
   cell15=rw4.createCell(15);
   cell16=rw4.createCell(16);
   cell17=rw4.createCell(17);
   cell18=rw4.createCell(18);
   cell19=rw4.createCell(19);
   cell20=rw4.createCell(20);
   cell21=rw4.createCell(21);
   cell22=rw4.createCell(22);
   cell23=rw4.createCell(23);
   cell24=rw4.createCell(24);
   cell25=rw4.createCell(25);
   cell26=rw4.createCell(26);
   cell27=rw4.createCell(27);
   cell28=rw4.createCell(28);
   cell29=rw4.createCell(29);
   cell30=rw4.createCell(30);
   cell31=rw4.createCell(31);
   cell32=rw4.createCell(32);
   cell33=rw4.createCell(33);
   
 cell0 .setCellValue("COUNTY NAME");
 cell1.setCellValue("PARTNER NAME");
 cell2.setCellValue("NEAREST FACILITY");
 cell3.setCellValue("GROUP NAME");
 cell4.setCellValue("SERVICE PROVIDER");
 cell5.setCellValue("FULL NAME");
 
 cell6.setCellValue("AGE");
 cell7.setCellValue("GENDER");
 cell8.setCellValue("DATE OF BIRTH");
 cell9 .setCellValue("NATIONAL ID");
 cell10.setCellValue("MOBILE NO");
 cell11.setCellValue("CCC NO");
 cell12 .setCellValue("Messages Attended");
 
 cell13.setCellValue("Knowledge of HIV Status");
 cell14.setCellValue("Partner HIV Testing");
 cell15.setCellValue("Child HIV Testing");
 cell16 .setCellValue("Discordance");
 cell17.setCellValue("HIV Disclosure");
 cell18.setCellValue("Risk Factor/Reduction");
 cell19.setCellValue("Condom Use");
 cell20.setCellValue("Alcohol and Substance Abuse");
  
 cell21 .setCellValue("Adherence");
 cell22.setCellValue("STIs");
 cell23.setCellValue("Family Planning");
 cell24.setCellValue("PMTCT");
 cell25 .setCellValue("TB");
 cell26.setCellValue("Received Contraceptives");
 cell27.setCellValue("Reffered To Service Point");
 cell28.setCellValue("Given Condoms");
 cell29 .setCellValue("Screened For TB");
 cell30.setCellValue("Screened For STIs");
 cell31.setCellValue("Partner Tested");
 cell32.setCellValue("Children Tested");
 cell33.setCellValue("Disclosed Status");

 
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

cell0.setCellStyle(stylex);
cell1.setCellStyle(stylex);
cell2.setCellStyle(stylex);
cell3.setCellStyle(stylex);
cell4.setCellStyle(stylex);
cell5.setCellStyle(stylex);
cell6.setCellStyle(stylex);
cell7.setCellStyle(stylex);
cell8.setCellStyle(stylex);
cell9.setCellStyle(stylex);
cell10.setCellStyle(stylex);
cell11.setCellStyle(stylex);
cell12.setCellStyle(stylex);
cell13.setCellStyle(stylex);
cell14.setCellStyle(stylex);
cell15.setCellStyle(stylex);
cell16.setCellStyle(stylex);
cell17.setCellStyle(stylex);
cell18.setCellStyle(stylex);
cell19.setCellStyle(stylex);
cell20.setCellStyle(stylex);
cell21.setCellStyle(stylex);
cell22.setCellStyle(stylex); 
cell23.setCellStyle(stylex);
cell24.setCellStyle(stylex);
cell25.setCellStyle(stylex);
cell26.setCellStyle(stylex);
cell27.setCellStyle(stylex);
cell28.setCellStyle(stylex);
cell29.setCellStyle(stylex);
cell30.setCellStyle(stylex);
cell31.setCellStyle(stylex);
cell32.setCellStyle(stylex); 
cell33.setCellStyle(stylex);
        
   i=1;     
   String [] partIDS=partner_ids.split(",");
   for(String partner_id:partIDS){
     if(!partner_id.equals("")&& !partner_id.equals(",")){  
         partnerid=partner_id;
         System.out.println("partner id is : "+partnerid);
        String getClients="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender,group_id,district_id,partner_id,provider_id,lessons_attended,national_id,ccc_no,mobile_no,dob,hf_id FROM personal_information"
                + " WHERE partner_id='"+partnerid+"' ORDER BY partner_id,district_id,group_id,fname,mname,lname";
        conn.rs=conn.st.executeQuery(getClients);
       while(conn.rs.next()){
 county=district=hf=partner=groupname=serviceprovider=clientname=age=gender=groupings=year=providerid="";
 countyid=districtid=hfid=partnerid=groupid=serviceproviderid=clientid="";
 s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="";
 cm=rsp=cd=tb=sti=testedpartner=testedchild=session_no=value=status;
 sess=val=cds=0;  
  hf_id=lessons_attended=national_id=ccc_no=mobile_no=dob="";           
         
           HSSFRow rw4x=shet1.createRow(i);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell0x,cell1x,cell2x,cell3x,cell4x,cell5x,cell6x,cell7x,cell8x,cell9x,cell10x,cell11x,cell12x,cell13x,cell14x,cell15x,cell16x,cell17x,cell18x,cell19x,cell20x;
    HSSFCell cell21x,cell22x,cell23x,cell24x,cell25x,cell26x,cell27x,cell28x,cell29x,cell30x,cell31x,cell32x,cell33x;
   cell0x=rw4x.createCell(0);
   cell1x=rw4x.createCell(1);
   cell2x=rw4x.createCell(2);
   cell3x=rw4x.createCell(3);
   cell4x=rw4x.createCell(4);
   cell5x=rw4x.createCell(5);
   cell6x=rw4x.createCell(6);
   cell7x=rw4x.createCell(7);
   cell8x=rw4x.createCell(8);
   cell9x=rw4x.createCell(9);
   cell10x=rw4x.createCell(10);
   cell11x=rw4x.createCell(11);
   cell12x=rw4x.createCell(12);
   cell13x=rw4x.createCell(13);
   cell14x=rw4x.createCell(14);
   cell15x=rw4x.createCell(15);
   cell16x=rw4x.createCell(16);
   cell17x=rw4x.createCell(17);
   cell18x=rw4x.createCell(18);
   cell19x=rw4x.createCell(19);
   cell20x=rw4x.createCell(20);
   cell21x=rw4x.createCell(21);
   cell22x=rw4x.createCell(22);
   cell23x=rw4x.createCell(23);
   cell24x=rw4x.createCell(24);
   cell25x=rw4x.createCell(25);
   cell26x=rw4x.createCell(26);
   cell27x=rw4x.createCell(27);
   cell28x=rw4x.createCell(28);
   cell29x=rw4x.createCell(29);
   cell30x=rw4x.createCell(30);
   cell31x=rw4x.createCell(31);
   cell32x=rw4x.createCell(32);
   cell33x=rw4x.createCell(33);

          clientid=conn.rs.getString(1);
          clientname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
          age=conn.rs.getString(5);
          gender=conn.rs.getString(6);
          groupid=conn.rs.getString(7);
          districtid=conn.rs.getString(8);
          partnerid=conn.rs.getString(9);
          providerid=conn.rs.getString(10);
       lessons_attended=conn.rs.getString(11);
       national_id=conn.rs.getString(12);
       ccc_no=conn.rs.getString(13);
       mobile_no=conn.rs.getString(14);
       dob=conn.rs.getString(15);
       hfid=conn.rs.getString(16);
       
         if(conn.rs.getString(3).equals(conn.rs.getString(4))){
         clientname=conn.rs.getString(2)+" "+conn.rs.getString(4);    
         }
        String getServiceProvider="SELECT fname,mname,lname FROM service_provider WHERE provider_id='"+providerid+"'"  ;
        conn.rs1=conn.st1.executeQuery(getServiceProvider);
        if(conn.rs1.next()==true){
        serviceprovider=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs1.getString(3);
            if(conn.rs1.getString(2).equals(conn.rs1.getString(3))){
         serviceprovider=conn.rs1.getString(1)+" "+conn.rs1.getString(3);    
         }
        }
     
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
     
     String getgrp1="SELECT health_facility.hf_name FROM health_facility WHERE health_facility.hf_id='"+hfid+"' LIMIT 1";
     conn.rs1=conn.st1.executeQuery(getgrp1);
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
     
     
     String getAttended="SELECT session_no,value FROM register2 WHERE client_id='"+clientid+"'";
     conn.rs1=conn.st1.executeQuery(getAttended);
     while(conn.rs1.next()){
     sess=conn.rs1.getInt(1);
     val=conn.rs1.getInt(2);
     
     if(sess==1){
         if(val==1){ s1="1";}
         else if (val==2){s1="0";}
         else{s1="";}
         
     }
     if(sess==2){
         if(val==1){ s2="1";}
         else if (val==2){s2="0";}
         else{s2="";}
         
     }
     if(sess==3){
         if(val==1){ s3="1";}
         else if (val==2){s3="0";}
         else{s3="";}
         
     }
     if(sess==4){
         if(val==1){ s4="1";}
         else if (val==2){s4="0";}
         else{s4="";}
         
     }
     if(sess==5){
         if(val==1){ s5="1";}
         else if (val==2){s5="0";}
         else{s5="";}
         
     }
     if(sess==6){
         if(val==1){ s6="1";}
         else if (val==2){s6="0";}
         else{s6="";}
         
     }
     if(sess==7){
         if(val==1){ s7="1";}
         else if (val==2){s7="0";}
         else{s7="";}
         
     }
     if(sess==8){
         if(val==1){ s8="1";}
         else if (val==2){s8="0";}
         else{s8="";}
         
     }
     if(sess==9){
         if(val==1){ s9="1";}
         else if (val==2){s9="0";}
         else{s9="";}
         
     }
     if(sess==10){
         if(val==1){ s10="1";}
         else if (val==2){s10="0";}
         else{s10="";}
         
     }
     if(sess==11){
         if(val==1){ s11="1";}
         else if (val==2){s11="0";}
         else{s11="";}
         
     }
     if(sess==12){
         if(val==1){ s12="1";}
         else if (val==2){s12="0";}
         else{s12="";}
         
     }
     if(sess==13){
         if(val==1){ s13="1";}
         else if (val==2){s13="0";}
         else{s13="";}
         
     }
     
     }
     
     
       
//      OUTPUT ATTENDED-------------------------------- 
     
       
       cm=rsp=tb=sti=testedpartner=testedchild=session_no=value=status="NO";
       cds=0;
  String getServices="SELECT contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status FROM services_provided WHERE client_id='"+clientid+"'"; 
  conn.rs1=conn.st1.executeQuery(getServices);
  while(conn.rs1.next()){
   cds+=conn.rs1.getInt(3);
   if(conn.rs1.getString(1).equals("YES")){
   cm=conn.rs1.getString(1);}
   if(conn.rs1.getString(2).equals("YES")){
   rsp=conn.rs1.getString(2);}
   if(conn.rs1.getString(4).equals("YES")){
   tb=conn.rs1.getString(4);}
   if(conn.rs1.getString(5).equals("YES")){
   sti=conn.rs1.getString(5);}
   if(conn.rs1.getString(6).equals("YES")){
   testedpartner=conn.rs1.getString(6);}
   if(conn.rs1.getString(7).equals("YES")){
   testedchild=conn.rs1.getString(7);}
   if(conn.rs1.getString(8).equals("YES")){
   status=conn.rs1.getString(8);}
  }
    
//  OUTPUT SERVICES PROVIDED================================     
 cell0x .setCellValue(county);
 cell1x.setCellValue(partner);
 cell2x.setCellValue(hf);
 cell3x.setCellValue(groupname);
 cell4x.setCellValue(serviceprovider);
 cell5x.setCellValue(clientname);
 cell6x.setCellValue(age);
 cell7x.setCellValue(gender);
 cell8x.setCellValue(dob);  
 cell9x .setCellValue(national_id);
 cell10x.setCellValue(mobile_no);
 cell11x.setCellValue(ccc_no);
 cell12x.setCellValue(lessons_attended);
 cell13x .setCellValue(s1);
 cell14x.setCellValue(s2);
 cell15x.setCellValue(s3);
 cell16x.setCellValue(s4);
 cell17x .setCellValue(s5);
 cell18x.setCellValue(s6);
 cell19x.setCellValue(s7);
 cell20x.setCellValue(s8);
 cell21x.setCellValue(s9);
  
 cell22x .setCellValue(s10);
 cell23x.setCellValue(s11);
 cell24x.setCellValue(s12);
 
 cell25x.setCellValue(s13);
 cell26x .setCellValue(cm);
 cell27x.setCellValue(rsp);
 cell28x.setCellValue(cds);
 cell29x.setCellValue(tb);
 cell30x.setCellValue(sti);
 cell31x.setCellValue(testedpartner);
 cell32x.setCellValue(testedchild);
 cell33x.setCellValue(status);
  
cell0x.setCellStyle(styleBorder);
cell1x.setCellStyle(styleBorder);
cell2x.setCellStyle(styleBorder);
cell3x.setCellStyle(styleBorder);
cell4x.setCellStyle(styleBorder);
cell5x.setCellStyle(styleBorder);
cell6x.setCellStyle(styleBorder);
cell7x.setCellStyle(styleBorder);
cell8x.setCellStyle(styleBorder);

cell9x.setCellStyle(styleBorder);
cell10x.setCellStyle(styleBorder);
cell11x.setCellStyle(styleBorder);
cell12x.setCellStyle(styleBorder);
cell13x.setCellStyle(styleBorder);
cell14x.setCellStyle(styleBorder);
cell15x.setCellStyle(styleBorder);
cell16x.setCellStyle(styleBorder);
cell17x.setCellStyle(styleBorder);
cell18x.setCellStyle(styleBorder);
cell19x.setCellStyle(styleBorder);
cell20x.setCellStyle(styleBorder);
cell21x.setCellStyle(styleBorder);

cell22x.setCellStyle(styleBorder); 
cell23x.setCellStyle(styleBorder);
cell24x.setCellStyle(styleBorder);
cell25x.setCellStyle(styleBorder);
cell26x.setCellStyle(styleBorder);
cell27x.setCellStyle(styleBorder);
cell28x.setCellStyle(styleBorder);
cell29x.setCellStyle(styleBorder);
cell30x.setCellStyle(styleBorder);
cell31x.setCellStyle(styleBorder);
cell32x.setCellStyle(styleBorder);
cell33x.setCellStyle(styleBorder);
     i++;  
     System.out.println("here : "+i);
    }
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

    // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_Raw_Data.xls");
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
        Logger.getLogger(allRawData.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(allRawData.class.getName()).log(Level.SEVERE, null, ex);
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

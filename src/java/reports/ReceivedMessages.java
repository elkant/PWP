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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class ReceivedMessages extends HttpServlet {
HttpSession session;
String county,district,hf,partner,groupname,serviceprovider,clientname,age,gender,groupings,year,providerid;
String countyid,districtid,hfid,partnerid,groupid,serviceproviderid,clientid,dob;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
String cm,rsp,cd,tb,sti,testedpartner,testedchild,session_no,value,status;
int sess,val,cds=0,i;
String  hf_id,lessons_attended,national_id,ccc_no,mobile_no; 
String reportType,partners;
String startDate,endDate;
int added,message_no,achieved=0;
String current_group,previous_group;
String previousDistrict,currentDistrict="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      session=request.getSession();
        dbConn conn = new dbConn();
 startDate=session.getAttribute("custstartDate").toString();
 endDate=session.getAttribute("custendDate").toString();
       
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
    
    shet1.setColumnWidth(4, 5500); 
    
    shet1.setColumnWidth(5, 10); 
    shet1.setColumnWidth(6, 10);
    shet1.setColumnWidth(7, 10); 
    shet1.setColumnWidth(8, 10); 
    shet1.setColumnWidth(9, 10); 
    shet1.setColumnWidth(10, 10); 
    shet1.setColumnWidth(11, 10);  
    shet1.setColumnWidth(12, 10); 
    
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
    
//    shet1.setColumnWidth(26, 5200); 
//    shet1.setColumnWidth(27, 5800); 
//    shet1.setColumnWidth(28, 5000); 
//    shet1.setColumnWidth(29, 5300);
//    shet1.setColumnWidth(30, 5800); 
//    shet1.setColumnWidth(31, 5000); 
//    shet1.setColumnWidth(32, 5300);
    
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
//   cell5=rw4.createCell(5);
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
//   
//   cell26=rw4.createCell(26);
//   cell27=rw4.createCell(27);
//   cell28=rw4.createCell(28);
//   cell29=rw4.createCell(29);
//   cell30=rw4.createCell(30);
//   cell31=rw4.createCell(31);
//   cell32=rw4.createCell(32);
//   cell33=rw4.createCell(33);
   
 cell0 .setCellValue("COUNTY NAME");
 cell1.setCellValue("PARTNER NAME");
 cell2.setCellValue("DISTRICT NAME");
 cell3.setCellValue("NEAREST FACILITY");
 cell4.setCellValue("GROUP NAME");
// cell5.setCellValue("ACHIEVED");
 
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
 
// cell26.setCellValue("Received Contraceptives");
// cell27.setCellValue("Reffered To Service Point");
// cell28.setCellValue("Given Condoms");
// cell29 .setCellValue("Screened For TB");
// cell30.setCellValue("Screened For STIs");
// cell31.setCellValue("Partner Tested");
// cell32.setCellValue("Children Tested");
// cell33.setCellValue("Disclosed Status");

 
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
//cell5.setCellStyle(stylex);
//cell6.setCellStyle(stylex);
//cell7.setCellStyle(stylex);
//cell8.setCellStyle(stylex);
//cell9.setCellStyle(stylex);
//cell10.setCellStyle(stylex);
//cell11.setCellStyle(stylex);
//cell12.setCellStyle(stylex);
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

        
   i=1;previousDistrict=currentDistrict="";     
current_group="";
        String getClients="SELECT DISTINCT(personal_information.client_id),personal_information.fname,personal_information.mname,personal_information.lname,"
+ "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )"
+ ",personal_information.gender,groups.group_name,district.district_name,partner.partner_name,"
+ "service_provider.fname,service_provider.mname,service_provider.lname,personal_information.lessons_attended,"
+ "personal_information.national_id,personal_information.ccc_no,personal_information.mobile_no,health_facility.hf_name,county.county_name,"
+ "register2.session_no,SUM(register2.value) "
+ " FROM personal_information"
+ " LEFT JOIN health_facility ON personal_information.hf_id=health_facility.hf_id "
+ "LEFT JOIN district ON personal_information.district_id=district.district_id "
+ "LEFT JOIN county ON district.county_id=county.county_id "
+ "LEFT JOIN service_provider ON personal_information.provider_id=service_provider.provider_id "
+ "LEFT JOIN partner ON personal_information.partner_id=partner.partner_id "
+ "LEFT JOIN groups ON personal_information.group_id =groups.group_id "
+ "LEFT JOIN register2 ON personal_information.client_id=register2.client_id "
+ " WHERE register2.value<2 && STR_TO_DATE(register2.date,'%m/%d/%Y') BETWEEN STR_TO_DATE('"+startDate+"','%m/%d/%Y') AND STR_TO_DATE('"+endDate+"','%m/%d/%Y')  "
+ " GROUP BY county.county_name,partner.partner_name,district.district_name,groups.group_name, register2.session_no ORDER BY partner.partner_name,district.district_name,groups.group_name";
conn.rs=conn.st.executeQuery(getClients);
       while(conn.rs.next()){
 county=district=hf=partner=groupname=serviceprovider=clientname=age=gender=groupings=year=providerid="";
 countyid=districtid=hfid=partnerid=groupid=serviceproviderid=clientid="";
 s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="";
 cm=rsp=cd=tb=sti=testedpartner=testedchild=session_no=value=status;
 sess=val=cds=0;  
  hf_id=lessons_attended=national_id=ccc_no=mobile_no=dob="";           
      added=0;   
message_no=achieved=0;

          clientid=conn.rs.getString(1);
          if(!conn.rs.getString(3).equalsIgnoreCase(conn.rs.getString(4))){
          clientname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);}
          else{
          clientname=conn.rs.getString(2)+" "+conn.rs.getString(4);    
          }
          age=conn.rs.getString(5);
          gender=conn.rs.getString(6);
          groupname=conn.rs.getString(7);
          if(groupname==null){
              groupname="INDIVIDUAL";
          }
          district=conn.rs.getString(8);
          partner=conn.rs.getString(9);
          serviceprovider=conn.rs.getString(10)+" "+conn.rs.getString(11)+" "+conn.rs.getString(12);
        if(conn.rs.getString(11)!=null && conn.rs.getString(12)!=null){
          if(conn.rs.getString(11).equals(conn.rs.getString(12))){
         serviceprovider=conn.rs.getString(10)+" "+conn.rs.getString(12);    
         }
        }
       lessons_attended=conn.rs.getString(13);
       national_id=conn.rs.getString(14);
       ccc_no=conn.rs.getString(15);
       mobile_no=conn.rs.getString(16);
       hf=conn.rs.getString(17);
       county=conn.rs.getString(18);
     message_no=conn.rs.getInt(19);
     achieved=conn.rs.getInt(20);  
//      OUTPUT ATTENDED-------------------------------- 
  current_group=groupname;
      

currentDistrict=district; 

    if(current_group.equals("")){
        previous_group=current_group;
        previousDistrict=currentDistrict;
      //  OUTPUT SERVICES PROVIDED================================     
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
//   cell5x=rw4x.createCell(5);
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
  
 cell0x .setCellValue(county);
 cell1x.setCellValue(partner);
 cell2x.setCellValue(district);
 cell3x.setCellValue(hf);
 cell4x.setCellValue(groupname);
// cell5x.setCellValue(achieved);
 if(message_no==1){cell13x .setCellValue(achieved);}
 
  if(message_no==2){cell14x.setCellValue(achieved);}
 
  if(message_no==3){cell15x.setCellValue(achieved);}
 
  if(message_no==4){cell16x.setCellValue(achieved);}
 
  if(message_no==5){cell17x .setCellValue(achieved);}
 
  if(message_no==6){cell18x.setCellValue(achieved);}
 
  if(message_no==7){cell19x.setCellValue(achieved);}
 
  if(message_no==8){cell20x.setCellValue(achieved);}
 
  if(message_no==9){cell21x.setCellValue(achieved); }
 
  if(message_no==10){cell22x .setCellValue(achieved);}
 
  if(message_no==11){cell23x.setCellValue(achieved);}
 
  if(message_no==12){cell24x.setCellValue(achieved);}
 
  if(message_no==13){cell25x.setCellValue(achieved);}

  
cell0x.setCellStyle(styleBorder);
cell1x.setCellStyle(styleBorder);
cell2x.setCellStyle(styleBorder);
cell3x.setCellStyle(styleBorder);
cell4x.setCellStyle(styleBorder);
//cell5x.setCellStyle(styleBorder);
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

i++;
    } 
     else if(!currentDistrict.equals(previousDistrict)){
previousDistrict=currentDistrict;
             previous_group=current_group;
      //  OUTPUT SERVICES PROVIDED================================     
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
//   cell5x=rw4x.createCell(5);
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
   
  
 cell0x .setCellValue(county);
 cell1x.setCellValue(partner);
 cell2x.setCellValue(district);
 cell3x.setCellValue(hf);
 cell4x.setCellValue(groupname);
// cell5x.setCellValue(achieved);
 if(message_no==1){cell13x .setCellValue(achieved);}
 
  if(message_no==2){cell14x.setCellValue(achieved);}
 
  if(message_no==3){cell15x.setCellValue(achieved);}
 
  if(message_no==4){cell16x.setCellValue(achieved);}
 
  if(message_no==5){cell17x .setCellValue(achieved);}
 
  if(message_no==6){cell18x.setCellValue(achieved);}
 
  if(message_no==7){cell19x.setCellValue(achieved);}
 
  if(message_no==8){cell20x.setCellValue(achieved);}
 
  if(message_no==9){cell21x.setCellValue(achieved); }
 
  if(message_no==10){cell22x .setCellValue(achieved);}
 
  if(message_no==11){cell23x.setCellValue(achieved);}
 
  if(message_no==12){cell24x.setCellValue(achieved);}
 
  if(message_no==13){cell25x.setCellValue(achieved);}
 
  
cell0x.setCellStyle(styleBorder);
cell1x.setCellStyle(styleBorder);
cell2x.setCellStyle(styleBorder);
cell3x.setCellStyle(styleBorder);
cell4x.setCellStyle(styleBorder);
//cell5x.setCellStyle(styleBorder);

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

   
  i++;
    }
    else if(!current_group.equals(previous_group)){
//      update attended sessions;  
             previous_group=current_group;
      //  OUTPUT SERVICES PROVIDED================================     
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
//   cell5x=rw4x.createCell(5);
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
   
  
 cell0x .setCellValue(county);
 cell1x.setCellValue(partner);
 cell2x.setCellValue(district);
 cell3x.setCellValue(hf);
 cell4x.setCellValue(groupname);
// cell5x.setCellValue(achieved);
 if(message_no==1){cell13x .setCellValue(achieved);}
 
  if(message_no==2){cell14x.setCellValue(achieved);}
 
  if(message_no==3){cell15x.setCellValue(achieved);}
 
  if(message_no==4){cell16x.setCellValue(achieved);}
 
  if(message_no==5){cell17x .setCellValue(achieved);}
 
  if(message_no==6){cell18x.setCellValue(achieved);}
 
  if(message_no==7){cell19x.setCellValue(achieved);}
 
  if(message_no==8){cell20x.setCellValue(achieved);}
 
  if(message_no==9){cell21x.setCellValue(achieved); }
 
  if(message_no==10){cell22x .setCellValue(achieved);}
 
  if(message_no==11){cell23x.setCellValue(achieved);}
 
  if(message_no==12){cell24x.setCellValue(achieved);}
 
  if(message_no==13){cell25x.setCellValue(achieved);}
 
  
cell0x.setCellStyle(styleBorder);
cell1x.setCellStyle(styleBorder);
cell2x.setCellStyle(styleBorder);
cell3x.setCellStyle(styleBorder);
cell4x.setCellStyle(styleBorder);
//cell5x.setCellStyle(styleBorder);

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
  
  i++;
    }
     

 else if(current_group.equals(previous_group) && currentDistrict.equals(previousDistrict)){
 HSSFRow rw4x=shet1.getRow(i-1);
if(message_no==1){ HSSFCell cell13x=rw4x.createCell(13); cell13x .setCellValue(achieved); cell13x.setCellStyle(styleBorder);}
if(message_no==2){ HSSFCell cell14x=rw4x.createCell(14);cell14x.setCellValue(achieved);cell14x.setCellStyle(styleBorder);}
 if(message_no==3){HSSFCell cell15x=rw4x.createCell(15);cell15x.setCellValue(achieved);cell15x.setCellStyle(styleBorder);}
 if(message_no==4){HSSFCell cell16x=rw4x.createCell(16);cell16x.setCellValue(achieved);cell16x.setCellStyle(styleBorder);}
 if(message_no==5){HSSFCell cell17x=rw4x.createCell(17);cell17x .setCellValue(achieved);cell17x.setCellStyle(styleBorder);}
 if(message_no==6){HSSFCell cell18x=rw4x.createCell(18);cell18x.setCellValue(achieved);cell18x.setCellStyle(styleBorder);}
 if(message_no==7){HSSFCell cell19x=rw4x.createCell(19);cell19x.setCellValue(achieved);cell19x.setCellStyle(styleBorder);}
 if(message_no==8){HSSFCell cell20x=rw4x.createCell(20);cell20x.setCellValue(achieved);cell20x.setCellStyle(styleBorder);}
 if(message_no==9){HSSFCell cell21x=rw4x.createCell(21);cell21x.setCellValue(achieved);cell21x.setCellStyle(styleBorder); }
 if(message_no==10){HSSFCell cell22x=rw4x.createCell(22);cell22x .setCellValue(achieved);cell22x.setCellStyle(styleBorder); }
 if(message_no==11){HSSFCell cell23x=rw4x.createCell(23);cell23x.setCellValue(achieved);cell23x.setCellStyle(styleBorder);}
 if(message_no==12){HSSFCell cell24x=rw4x.createCell(24);cell24x.setCellValue(achieved);cell24x.setCellStyle(styleBorder);}
 if(message_no==13){HSSFCell cell25x=rw4x.createCell(25);cell25x.setCellValue(achieved);cell25x.setCellStyle(styleBorder);}

    }
 else{
     System.out.println("here-------------nothing seen");
 }
       }
     System.out.println("here : "+i);
    
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
        Logger.getLogger(ReceivedMessages.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ReceivedMessages.class.getName()).log(Level.SEVERE, null, ex);
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

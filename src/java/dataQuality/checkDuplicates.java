/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataQuality;

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
public class checkDuplicates extends HttpServlet {
HttpSession session;
String county,district,hf,partner,groupname,serviceprovider,clientname,age,gender,groupings,year,providerid;
String countyid,districtid,hfid,partnerid,groupid,serviceproviderid,clientid;
String providername,startdate,enddate,start_date,end_date;
String cm,rsp,cd,tb,sti,testedpartner,testedchild,session_no,value,status;
int sess,val,cds=0,i,found,duplicate;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        
    
        i=4;
        
       //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
//    font.setItalic(true);
//    font.setBoldweight((short)12);
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font2=wb.createFont();
// font2.setFontHeightInPoints((short)15);
    font2.setFontName("Arial Black");
//    font.setItalic(true);
//    font2.setBoldweight((short)18);
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
//    stborder.setFillForegroundColor(HSSFColor.ORANGE.index);
//    stborder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
    
    
//  HSSFSheet sheet1 = wb.getSheetAt(0);
    shet1.setColumnWidth(0, 5000); 
    shet1.setColumnWidth(1, 5000); 
    shet1.setColumnWidth(2, 5000); 
    shet1.setColumnWidth(3, 5500);
    
    shet1.setColumnWidth(4, 7000); 
    
    shet1.setColumnWidth(5, 5300); 
    shet1.setColumnWidth(6, 5000);
    shet1.setColumnWidth(7, 5200); 
    shet1.setColumnWidth(8, 5200); 
    shet1.setColumnWidth(9, 5200); 
    shet1.setColumnWidth(10, 5800); 
    shet1.setColumnWidth(11, 5000);  
    
    
//    shet1.setColumnWidth(20, 2000);
    HSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
//    styleBorder.setFillForegroundColor(HSSFColor.ORANGE.index);
//    styleBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  
   HSSFRow rw4=shet1.createRow(0);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
    HSSFCell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16,cell17,cell18,cell19,cell20;
   cell1=rw4.createCell(0);
   cell2=rw4.createCell(1);
   cell3=rw4.createCell(2);
   cell4=rw4.createCell(3);
   cell5=rw4.createCell(4);
   cell6=rw4.createCell(5);
   cell7=rw4.createCell(6);
   cell8=rw4.createCell(7);
   cell9=rw4.createCell(8);
   cell10=rw4.createCell(9);
   cell11=rw4.createCell(10);
   cell12=rw4.createCell(11);
   cell13=rw4.createCell(12);
   cell14=rw4.createCell(13);
   
 cell1 .setCellValue("COUNTY NAME");
 cell2.setCellValue("PARTNER NAME");
 cell3.setCellValue("DISTRICT");
 cell4.setCellValue("FACILITY");
 cell5.setCellValue("GROUP NAME");
 cell6.setCellValue("CLIENT NAME");
 cell7.setCellValue("AGE");
 cell8.setCellValue("GENDER");
 
 cell9 .setCellValue("YEAR");
 cell10.setCellValue("SESSION ATTENDED");
 cell11.setCellValue("No. of duplicates");
 cell11 .setCellValue("SERVICE PROVIDER");
 cell12.setCellValue("START DATE");
 cell13.setCellValue("END DATE");


 
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
//cell14.setCellStyle(stylex);
 i=1;     
        
        String getClients="SELECT * FROM clients ORDER BY fname";
        conn.rs=conn.st.executeQuery(getClients);
       while(conn.rs.next()){
 county=district=hf=partner=groupname=serviceprovider=clientname=age=gender=groupings=year=providerid="";
 countyid=districtid=hfid=partnerid=groupid=serviceproviderid=clientid="";
 sess=val=cds=duplicate=0;  
  startdate=enddate="";         
//      System.out.println("here    :   "+i);
           HSSFRow rw4x=shet1.createRow(i);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell1x,cell2x,cell3x,cell4x,cell5x,cell6x,cell7x,cell8x,cell9x,cell10x,cell11x,cell12x,cell13x,cell14x,cell15x,cell16x,cell17x,cell18x,cell19x,cell20x;
  cell1x=rw4x.createCell(0);
   cell2x=rw4x.createCell(1);
   cell3x=rw4x.createCell(2);
   cell4x=rw4x.createCell(3);
   cell5x=rw4x.createCell(4);
   cell6x=rw4x.createCell(5);
   cell7x=rw4x.createCell(6);
   cell8x=rw4x.createCell(7);
   cell9x=rw4x.createCell(8);
   cell10x=rw4x.createCell(9);
   cell11x=rw4x.createCell(10);
   cell12x=rw4x.createCell(11);
   cell13x=rw4x.createCell(12);
//   cell14x=rw4x.createCell(13);
   
providername="";
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
       start_date=end_date="";
       String serviceprov="SELECT fname,mname,lname FROM service_provider WHERE provider_id='"+providerid+"'";
       conn.rs1=conn.st1.executeQuery(serviceprov);
       if(conn.rs1.next()){
      if(conn.rs1.getString(2).equals(conn.rs1.getString(3))){
     providername=conn.rs1.getString(1)+" "+conn.rs.getString(3);     
      }
      else{
      providername=conn.rs1.getString(1)+" "+conn.rs1.getString(2)+" "+conn.rs.getString(3);    
      }
       }
       
     String checker="SELECT COUNT(client_id) FROM clients WHERE fname=? && lname=? && client_id!=?";
     conn.pst=conn.conn.prepareStatement(checker);
     
     conn.pst.setString(1, conn.rs.getString(2));
     conn.pst.setString(2, conn.rs.getString(3));
//     conn.pst.setString(3, conn.rs.getString(4));
     conn.pst.setString(3, clientid);
//     conn.pst.setString(3, conn.rs.getString(2));
//     conn.pst.setString(4, conn.rs.getString(3));
//     conn.pst.setString(5, groupid);
//     conn.pst.setString(6, conn.rs.getString(2));
//     conn.pst.setString(7, age);
//     conn.pst.setString(8, clientid);
//     conn.pst.setString(8, age);
     
     conn.rs1=conn.pst.executeQuery();
     found=0;
     if(conn.rs1.next()==true){
         found=conn.rs1.getInt(1);
     }
     System.out.println("found  :   "+found);
     if(found>0){
         duplicate=found;
     }
     if(found==0){
         duplicate=0;
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
      if(!groupid.equals("0")){
     String getgrp="SELECT groups.group_name,health_facility.hf_name FROM groups JOIN health_facility ON groups.nhf_id=health_facility.hf_id"
             + " WHERE groups.group_id='"+groupid+"'";
     conn.rs1=conn.st1.executeQuery(getgrp);
     if(conn.rs1.next()==true){
         groupname=conn.rs1.getString(1);
         hf=conn.rs1.getString(2);
//        SELECT START END DATE DATE FOR GROUP INDIVIDUALS----------------------------------------------
         String getDates="SELECT MAX(session_date),MIN(session_date) FROM sessions WHERE group_id='"+groupid+"' && session_date!=''";
         conn.rs1=conn.st1.executeQuery(getDates);
         if(conn.rs1.next()==true){
         start_date=conn.rs1.getString(2);
         end_date=conn.rs1.getString(1);
             
         }
         
         
         
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
      String getDates="SELECT MAX(session_date),MIN(session_date) FROM sessions WHERE groupings='"+groupings+"' && session_date!=''";
         conn.rs1=conn.st1.executeQuery(getDates);
         if(conn.rs1.next()==true){
         start_date=conn.rs1.getString(2);
         end_date=conn.rs1.getString(1);
             
         }
     
     
     }
    
       
//      OUTPUT ATTENDED-------------------------------- 
if(duplicate>0){
//  OUTPUT SERVICES PROVIDED================================     
 cell1x .setCellValue(county);
 cell2x.setCellValue(partner);
 cell3x.setCellValue(district);
 cell4x.setCellValue(hf);
 cell5x.setCellValue(groupname);
 cell6x.setCellValue(clientname);
 cell7x.setCellValue(age);
 cell8x.setCellValue(gender);  
  
 cell9x .setCellValue(year);
 cell10x.setCellValue(cds);
// cell11x.setCellValue(duplicate);
 
 cell11x .setCellValue(providername);
 cell12x.setCellValue(start_date);
 cell13x.setCellValue(end_date);

  
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
cell11x.setCellStyle(styleBorder);
cell12x.setCellStyle(styleBorder);
cell13x.setCellStyle(styleBorder);
//cell14x.setCellStyle(styleBorder);
     i++;  
     System.out.println("here : "+i);
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_DUPLICATE_REPORT.xls");
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
        Logger.getLogger(checkDuplicates.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(checkDuplicates.class.getName()).log(Level.SEVERE, null, ex);
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

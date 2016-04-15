<%-- 
    Document   : dummy
    Created on : Nov 1, 2014, 8:02:40 PM
    Author     : Geofrey Nyabuto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <img src="images/pwpgif.gif" alt="pwp guide loading">
    </body>
</html>
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
import pwp.dbConn;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
/**
 *
 * @author Geofrey Nyabuto
 */
public class kePMSServices extends HttpServlet {
HttpSession session;
String countyname,countyid,partnername,partnerid,quarter;
String startdate,enddate,month,gender;
int pos,achieved,year;
 int contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
 session=request.getSession();
      dbConn conn = new dbConn();
    
    
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
    shet1.setColumnWidth(0, 6000); 
    shet1.setColumnWidth(1, 6000); 
    shet1.setColumnWidth(2, 6000); 
    shet1.setColumnWidth(3, 6000);
    shet1.setColumnWidth(4, 6000); 
    shet1.setColumnWidth(5, 6000); 
    shet1.setColumnWidth(6, 6000); 
    shet1.setColumnWidth(7, 6000); 
    shet1.setColumnWidth(8, 6000);
    shet1.setColumnWidth(9, 6000);

//    shet1.setColumnWidth(20, 2000);
    HSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleBorder.setFillForegroundColor(HSSFColor.ORANGE.index);
    styleBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  cell=rw1.createCell(0);
  cell.setCellValue("PWP SERVICES PROVIDED PER MONTH ");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,4));
    
//  CREATE HEADING 2
  HSSFRow rheading2=shet1.createRow(2);
  rheading2.setHeightInPoints(25);
  HSSFCell cellxx1,cellxx2,cellxx3,cellxx4,cellxx5,cellxx6,cellxx7,cellxx8,cellxx9,cellxx10;
  cellxx1=rheading2.createCell(0);
  cellxx2=rheading2.createCell(1);
    cellxx3=rheading2.createCell(2);
    cellxx4=rheading2.createCell(3);
    cellxx5=rheading2.createCell(4);
    cellxx6=rheading2.createCell(5);
      cellxx7=rheading2.createCell(6);
      cellxx8=rheading2.createCell(7);
    cellxx9=rheading2.createCell(8);
   cellxx10=rheading2.createCell(9);
       
 cellxx1.setCellValue("COUNTY NAME");
 cellxx2.setCellValue("GENDER");
 cellxx3.setCellValue("contraceptive_method");
 cellxx4.setCellValue("rsp");
 cellxx5.setCellValue("cds_given");
 cellxx6.setCellValue("screened_tb");
 cellxx7.setCellValue("screened_stis");
 cellxx8.setCellValue("tested_partner");
 cellxx9.setCellValue("tested_children");
 cellxx10.setCellValue("disclosed_status");
  
             cellxx1.setCellStyle(styleBorder);
             cellxx2.setCellStyle(styleBorder);
             cellxx3.setCellStyle(styleBorder);
             cellxx4.setCellStyle(styleBorder);
             cellxx5.setCellStyle(styleBorder);
             cellxx6.setCellStyle(styleBorder);
             cellxx7.setCellStyle(styleBorder);
             cellxx8.setCellStyle(styleBorder);
             cellxx9.setCellStyle(styleBorder);
             cellxx10.setCellStyle(styleBorder);


pos=3;
 
 HSSFCellStyle stylex = wb.createCellStyle();
//stylex.setFillForegroundColor(HSSFColor.LIME.index);
//stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
HSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);
String client_id;     
      
   String   getData="SELECT clients.client_id,county.county_name,clients.gender FROM clients JOIN"
           + "(district JOIN county ON county.county_id=district.county_id) ON district.district_id=clients.district_id ORDER BY client_id";
   conn.rs=conn.st.executeQuery(getData);
   while(conn.rs.next()){
       System.out.println("at position :"+(pos-3));
        contraceptive_method=rsp=cds_given=screened_tb=screened_stis=tested_partner=tested_children=disclosed_status=0;
       client_id=conn.rs.getString(1);
     String  getServices="SELECT  CASE" +
" WHEN services_provided.contraceptive_method= 'YES' THEN '1'" +
" WHEN services_provided.contraceptive_method= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS contraceptive_method," +
"CASE" +
" WHEN services_provided.rsp= 'YES' THEN '1'" +
" WHEN services_provided.rsp= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS rsp," +
"CASE" +
" WHEN services_provided.cds_given>0 THEN '1'" +
" WHEN services_provided.cds_given=0 THEN '0'" +
"ELSE 'NONE'" +
"END AS cds_given," +
"CASE" +
" WHEN services_provided.screened_tb= 'YES' THEN '1'" +
" WHEN services_provided.screened_tb= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS screened_tb," +
"CASE" +
" WHEN services_provided.screened_stis= 'YES' THEN '1'" +
" WHEN services_provided.screened_stis= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS screened_stis," +
"CASE" +
" WHEN services_provided.tested_partner= 'YES' THEN '1'" +
" WHEN services_provided.tested_partner= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS tested_partner," +
"CASE" +
" WHEN services_provided.tested_children= 'YES' THEN '1'" +
" WHEN services_provided.tested_children= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS tested_children," +
"CASE" +
" WHEN services_provided.disclosed_status= 'YES' THEN '1'" +
" WHEN services_provided.disclosed_status= 'NO' THEN '0'" +
"ELSE 'NONE'" +
"END AS disclosed_status"+
" FROM services_provided WHERE client_id='"+client_id+"' && services_provided.submission_month>='10'"+
" && services_provided.submission_month<='12' && services_provided.submission_year='2014'"; 
   conn.rs1=conn.st1.executeQuery(getServices);
    while(conn.rs1.next()){
//        contraceptive_method=rsp=cds_given=screened_tb=screened_stis=tested_partner=tested_children=disclosed_status=0
           if(contraceptive_method==0){
            contraceptive_method=conn.rs1.getInt(1);   
           }
           if(rsp==0){
           rsp=conn.rs1.getInt(2);    
           } 
            
           if(cds_given==0){
           cds_given =conn.rs1.getInt(3);    
           }
            
           if(screened_tb==0){
             screened_tb=conn.rs1.getInt(4);
           }
          
           if(screened_stis==0){
            screened_stis=conn.rs1.getInt(5);    
           }
           
           if(tested_partner==0){
             tested_partner=conn.rs1.getInt(6);   
           }
           
           if(tested_children==0){
            tested_children=conn.rs1.getInt(7);    
           }
           
           if(disclosed_status==0){
            disclosed_status = conn.rs1.getInt(8);    
           }
       }  
    
 if(contraceptive_method>0 || rsp>0 || cds_given>0 || screened_tb>0 || screened_stis>0 || tested_partner>0 || tested_children>0 || disclosed_status>0) {  
   countyname=conn.rs.getString(2);
   gender=conn.rs.getString(3);
//  CREATE ROW AND ADD DATA TO THE DATA CELLS======================
    HSSFRow data=shet1.createRow(pos);
  data.setHeightInPoints(25);
  HSSFCell cellx1,cellx2,cellx3,cellx4,cellx5,cellx6,cellx7,cellx8,cellx9,cellx10;
  cellx1=data.createCell(0);
  cellx2=data.createCell(1);
    cellx3=data.createCell(2);
    cellx4=data.createCell(3);
    cellx5=data.createCell(4);
    cellx6=data.createCell(5);
     cellx7=data.createCell(6);
    cellx8=data.createCell(7);
    cellx9=data.createCell(8);
    cellx10=data.createCell(9);  

 cellx1.setCellValue(countyname);
 cellx2.setCellValue(client_id);
 cellx3.setCellValue(contraceptive_method);
 cellx4.setCellValue(rsp);
 cellx5.setCellValue(cds_given);
 cellx6.setCellValue(screened_tb);
 cellx7.setCellValue(screened_stis);
 cellx8.setCellValue(tested_partner);
 cellx9.setCellValue(tested_children);
  cellx10.setCellValue(disclosed_status);
  
             cellx1.setCellStyle(stylex);
             cellx2.setCellStyle(stylex);
             cellx3.setCellStyle(stylex);
             cellx4.setCellStyle(stylex);
             cellx5.setCellStyle(stylex); 
             cellx6.setCellStyle(stylex);
             cellx7.setCellStyle(stylex);
             cellx8.setCellStyle(stylex);
             cellx9.setCellStyle(stylex);
             cellx10.setCellStyle(stylex); 

   pos++;
   }
   }
    // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_SERVICES_PROVIDED_REPORT.xls");
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
        Logger.getLogger(kePMSServices.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(kePMSServices.class.getName()).log(Level.SEVERE, null, ex);
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

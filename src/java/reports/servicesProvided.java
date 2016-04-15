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
public class servicesProvided extends HttpServlet {
HttpSession session;
String countyname,countyid,partnername,partnerid,quarter;
String startdate,enddate,month;
int pos,achieved,year;
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
    
    shet1.setColumnWidth(4, 8000); 

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
  HSSFCell cellxx1,cellxx2,cellxx3,cellxx4,cellxx5,cellxx6;
  cellxx1=rheading2.createCell(0);
  cellxx2=rheading2.createCell(1);
    cellxx3=rheading2.createCell(2);
    cellxx4=rheading2.createCell(3);
    cellxx5=rheading2.createCell(4);
//    cellxx6=rheading2.createCell(5);


 cellxx1.setCellValue("COUNTY NAME");
 cellxx2.setCellValue("PARTNER NAME");
 cellxx3.setCellValue("YEAR");
 cellxx4.setCellValue("MONTH");
 cellxx5.setCellValue("NO. OF CLIENTS GIVEN SERVICES");

  
             cellxx1.setCellStyle(styleBorder);
             cellxx2.setCellStyle(styleBorder);
             cellxx3.setCellStyle(styleBorder);
             cellxx4.setCellStyle(styleBorder);
             cellxx5.setCellStyle(styleBorder);


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
     
      
   String   getData="SELECT county.county_name AS COUNTY,partner.partner_name AS PARTNER, "
           + "COUNT(DISTINCT services_provided.client_id) AS ACHIEVED," +
"services_provided.submission_year,services_provided.submission_month," +
" CASE" +
"      WHEN max(services_provided.submission_month) BETWEEN 01 AND 03 THEN 'Q2' " +
"      WHEN max(services_provided.submission_month) BETWEEN 04 AND 06 THEN 'Q3' " +
"      WHEN max(services_provided.submission_month) BETWEEN 07 AND 09 THEN 'Q4' " +
"      WHEN max(services_provided.submission_month) BETWEEN 10 AND 12 THEN 'Q1' " +
"      ELSE 'NOT SELECTED' " +
"      END AS QUARTER, " +
           "CASE" +
" when services_provided.submission_month =01 THEN 'JAN'" +
" when services_provided.submission_month =02 THEN 'FEB'" +
" when services_provided.submission_month =03 THEN 'MAR'" +
" when services_provided.submission_month=04 THEN 'APR'" +
" when services_provided.submission_month=05 THEN 'MAY'" +
" when services_provided.submission_month=06 THEN 'JUN'" +
" when services_provided.submission_month=07 THEN 'JUL'" +
" when services_provided.submission_month=08 THEN 'AUG'" +
" when services_provided.submission_month=09 THEN 'SEPT'" +
" when services_provided.submission_month=10 THEN 'OCT'" +
" when services_provided.submission_month=11 THEN 'NOV'" +
" when services_provided.submission_month=12 THEn 'DEC'" +
" END AS MONTHS "+
"FROM personal_information JOIN services_provided ON personal_information.client_id=services_provided.client_id " +
"JOIN partner ON partner.partner_id=personal_information.partner_id JOIN (district JOIN county ON county.county_id=district.county_id) " +
"ON district.district_id=personal_information.district_id " +
"WHERE contraceptive_method='YES' OR rsp='YES' OR cds_given>0 OR screened_tb='YES' OR screened_stis='YES' " +
"OR tested_partner='YES' OR tested_children='YES' OR disclosed_status='YES' " +
"GROUP BY county.county_name,partner.partner_name,services_provided.submission_year,services_provided.submission_month" ;
   conn.rs=conn.st.executeQuery(getData);
   while(conn.rs.next()){
   countyname=conn.rs.getString(1);
   partnername=conn.rs.getString(2);
   achieved=conn.rs.getInt(3);
   month=conn.rs.getString(7);
   year=conn.rs.getInt(4);
//  CREATE ROW AND ADD DATA TO THE DATA CELLS======================
    HSSFRow data=shet1.createRow(pos);
  data.setHeightInPoints(25);
  HSSFCell cellx1,cellx2,cellx3,cellx4,cellx5,cellx6;
  cellx1=data.createCell(0);
  cellx2=data.createCell(1);
    cellx3=data.createCell(2);
    cellx4=data.createCell(3);
    cellx5=data.createCell(4);
//    cellxx6=rheading2.createCell(5);


 cellx1.setCellValue(countyname);
 cellx2.setCellValue(partnername);
 cellx3.setCellValue(year);
 cellx4.setCellValue(month);
 cellx5.setCellValue(achieved);

  
             cellx1.setCellStyle(stylex);
             cellx2.setCellStyle(stylex);
             cellx3.setCellStyle(stylex);
             cellx4.setCellStyle(stylex);
             cellx5.setCellStyle(stylex); 
   
  System.out.println("county : "+countyname+" partner : "+partnername+" ahieved:"+achieved+" month: "+month+" quarter: "+quarter);
   
   
   pos++;
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
        Logger.getLogger(servicesProvided.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(servicesProvided.class.getName()).log(Level.SEVERE, null, ex);
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

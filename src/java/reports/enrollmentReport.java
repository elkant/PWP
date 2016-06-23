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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pwp.IdGenerator;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class enrollmentReport extends HttpServlet {
HttpSession session;
String countyname,countyid,partnername,partnerid,quarter;
String startdate,enddate,month,districtname,gender,agebracket;
int pos,achieved,year,pepfaryear,prevYear;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        session=request.getSession();
        dbConn conn = new dbConn();
           
   
   int m1=0,m2=0;
   String d1="",d2="",y1="",y2="";
//   pepfaryear=2015;
   pepfaryear=Integer.parseInt(request.getParameter("year"));
   prevYear=pepfaryear-1;
 
   //    COPY FILE TO BE WRITTEN TO 
    Path original = Paths.get(getServletContext().getRealPath("/ENROLLMENT_TEMPLATE_2.xlsm")); //original file
   Path destination = Paths.get(getServletContext().getRealPath("/ENROLLMENT_TEMPLATE_2.xlsm")); //new file
   System.out.println("origin :  "+original+" destination    :  "+destination);
//try {
//       //Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
//       System.out.println("file copied----------------");
//    } catch (IOException x) {
//       //catch all for IO problems
//        System.out.println("fine not copied");
//    }
    
    
        String allpath = getServletContext().getRealPath("/ENROLLMENT_TEMPLATE_2.xlsm");
        
         XSSFWorkbook wb;
 OPCPackage pkg = OPCPackage.open(allpath);
 
wb = new XSSFWorkbook(pkg);
    
     //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
  XSSFSheet shet1=wb.getSheet("Sheet1");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)11);
    font.setFontName("Arial Black");
//    font.setItalic(true);
//    font.setBoldweight((short)12);
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
     XSSFFont font2=wb.createFont();
// font2.setFontHeightInPoints((short)15);
    font2.setFontName("Arial Black");
//    font.setItalic(true);
//    font2.setBoldweight((short)18);
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(XSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(XSSFCellStyle.BORDER_THIN);
//    stborder.setFillForegroundColor(HSSFColor.ORANGE.index);
//    stborder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    stborder.setAlignment(XSSFCellStyle.ALIGN_CENTER);
    
    
    
//  HSSFSheet sheet1 = wb.getSheetAt(0);
    shet1.setColumnWidth(0, 6000); 
    shet1.setColumnWidth(1, 6000); 
    shet1.setColumnWidth(2, 6000); 
    shet1.setColumnWidth(3, 6000);
    shet1.setColumnWidth(4, 6000); 
    shet1.setColumnWidth(5, 7000); 
//    shet1.setColumnWidth(20, 2000);
    XSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(XSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(XSSFCellStyle.BORDER_THIN);
    styleBorder.setFillForegroundColor(HSSFColor.ORANGE.index);
    styleBorder.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
    styleBorder.setAlignment(XSSFCellStyle.ALIGN_CENTER);
    
//  CREATE HEADING 2
  XSSFRow rheading2=shet1.createRow(0);
  rheading2.setHeightInPoints(25);
  XSSFCell cellxx1,cellxx2,cellxx3,cellxx4,cellxx5,cellxx6,cellxx7,cellxx8,cellxx9;
  cellxx1=rheading2.createCell(0);
  cellxx2=rheading2.createCell(1);
    cellxx3=rheading2.createCell(2);
    cellxx4=rheading2.createCell(3);
    cellxx5=rheading2.createCell(4);
    cellxx6=rheading2.createCell(5);
    cellxx7=rheading2.createCell(6);
    cellxx8=rheading2.createCell(7);
    cellxx9=rheading2.createCell(7);


 cellxx1.setCellValue("COUNTY NAME");
 cellxx2.setCellValue("PARTNER NAME");
 cellxx3.setCellValue("DISTRICT NAME");
 cellxx4.setCellValue("YEAR");
 cellxx5.setCellValue("MONTH");
 cellxx6.setCellValue("TOTAL ENROLLED");
 cellxx7.setCellValue("GENDER");
 cellxx8.setCellValue("AGE BRACKET");
 cellxx9.setCellValue("DIC");

  
             cellxx1.setCellStyle(styleBorder);
             cellxx2.setCellStyle(styleBorder);
             cellxx3.setCellStyle(styleBorder);
             cellxx4.setCellStyle(styleBorder);
             cellxx5.setCellStyle(styleBorder);
             cellxx6.setCellStyle(styleBorder);
             cellxx7.setCellStyle(styleBorder);
             cellxx8.setCellStyle(styleBorder);
             cellxx9.setCellStyle(styleBorder);


pos=1;
 
 XSSFCellStyle stylex = wb.createCellStyle();
//stylex.setFillForegroundColor(HSSFColor.LIME.index);
//stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(XSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(XSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(XSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(XSSFCellStyle.ALIGN_CENTER);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);
     
      
   String   getData="SELECT county.county_name,partner.partner_name,district.district_name, " +
"extract(YEAR  FROM registration_date) AS YEAR, " +
"CASE  " +
"WHEN extract(MONTH  FROM registration_date)=1 THEN 'JAN'  " +
"WHEN extract(MONTH  FROM registration_date)=2 THEN 'FEB' " +
"WHEN extract(MONTH  FROM registration_date)=3 THEN 'MAR'  " +
"WHEN extract(MONTH  FROM registration_date)=4 THEN 'APR'  " +
"WHEN extract(MONTH  FROM registration_date)=5 THEN 'MAY'  " +
"WHEN extract(MONTH  FROM registration_date)=6 THEN 'JUN'  " +
"WHEN extract(MONTH  FROM registration_date)=7 THEN 'JUL'  " +
"WHEN extract(MONTH  FROM registration_date)=8 THEN 'AUG'  " +
"WHEN extract(MONTH  FROM registration_date)=9 THEN 'SEPT'  " +
"WHEN extract(MONTH  FROM registration_date)=10 THEN 'OCT'  " +
"WHEN extract(MONTH  FROM registration_date)=11 THEN 'NOV'  " +
"WHEN extract(MONTH  FROM registration_date)=12 THEN 'DEC'  " +
"ELSE 'NO REGISTRATION DATE' " +
"END AS MONTH " +
",COUNT(personal_information.client_id) AS REGISTERED,"
           + "CASE "
           + "WHEN personal_information.gender ='Female' THEN 'F' " 
           + "WHEN personal_information.gender ='Male' THEN 'M' "
           + "ELSE 'NO GENDER' "
           + "END AS GENDER, " 
           + "CASE" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 0 AND 9 THEN '0-9'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 10 AND 14 THEN '10-14'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 15 AND 19 THEN '15-19'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 20 AND 24 THEN '20-24'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 25 AND 49 THEN '25-49'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) >49 THEN '50 and above'" +
              " ELSE 'NO DATE OF BIRTH'" +
"   END AS AGEBRACKET, dic.dic_name as DIC "+
"FROM personal_information  " +
"JOIN partner ON partner.partner_id=personal_information.partner_id  " +
"JOIN district ON district.district_id=personal_information.district_id  " +
"JOIN county ON county.county_id=district.county_id LEFT JOIN  dic ON dic.dic_id=personal_information.dic_id WHERE registration_date BETWEEN '"+prevYear+"-10-01' AND '"+pepfaryear+"-09-30'  " +
"GROUP BY county.county_name,district.district_name,partner.partner_name,YEAR, MONTH,AGEBRACKET,personal_information.gender  " +
"ORDER BY county.county_name,district.district_name,partner.partner_name,YEAR, MONTH" ;
   conn.rs=conn.st.executeQuery(getData);
   while(conn.rs.next()){
   countyname=conn.rs.getString(1);
   partnername=conn.rs.getString(2);
   districtname=conn.rs.getString(3);
   year=conn.rs.getInt(4);
   month=conn.rs.getString(5);
   achieved=conn.rs.getInt(6);
   gender=conn.rs.getString(7);
   agebracket=conn.rs.getString(8);
   String dicname="";
   if(conn.rs.getString("DIC")==null){dicname="NO DIC";} else {dicname=conn.rs.getString("DIC");}
//  CREATE ROW AND ADD DATA TO THE DATA CELLS======================
    XSSFRow data=shet1.createRow(pos);
  data.setHeightInPoints(25);
  XSSFCell cellx1,cellx2,cellx3,cellx4,cellx5,cellx6,cellx7,cellx8,cellx9;
  cellx1=data.createCell(0);
  cellx2=data.createCell(1);
    cellx3=data.createCell(2);
    cellx4=data.createCell(3);
    cellx5=data.createCell(4);
    cellx6=data.createCell(5);
   cellx7=data.createCell(6);
   cellx8=data.createCell(7);
   cellx9=data.createCell(7);

 cellx1.setCellValue(countyname);
 cellx2.setCellValue(partnername);
 cellx3.setCellValue(districtname);
 cellx4.setCellValue(year);
 cellx5.setCellValue(month);
 cellx6.setCellValue(achieved);
 cellx7.setCellValue(gender);
 cellx8.setCellValue(agebracket);
 cellx9.setCellValue(dicname);
  
             cellx1.setCellStyle(stylex);
             cellx2.setCellStyle(stylex);
             cellx3.setCellStyle(stylex);
             cellx4.setCellStyle(stylex);
             cellx5.setCellStyle(stylex);
             cellx6.setCellStyle(stylex);
             cellx7.setCellStyle(stylex);
             cellx8.setCellStyle(stylex);
             cellx9.setCellStyle(stylex);
   
//  System.out.println("county : "+countyname+" partner : "+partnername+" ahieved:"+achieved+" month: "+month+" quarter: "+quarter);
   
   
   pos++;
  }
   System.out.println("report generated   :     "+pos);
    // write it as an excel attachment
        IdGenerator IG = new IdGenerator();
    
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

 if(pos==1){
     session.setAttribute("noEnrollments", "<font color=\"red\">No enrollments found in pepfar year </font> <font color=\"black\"><b>"+pepfaryear+"</b></font>");
    pkg.close();
     response.sendRedirect("enrollments.jsp");
 }
 else{
 ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_new_enrollment_report_created_on_"+IG.timestamp()+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();   
 pkg.close();   
    
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
        Logger.getLogger(enrollmentReport.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(enrollmentReport.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(enrollmentReport.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(enrollmentReport.class.getName()).log(Level.SEVERE, null, ex);
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

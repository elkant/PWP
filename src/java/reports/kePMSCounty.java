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
import java.nio.file.LinkOption;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
public class kePMSCounty extends HttpServlet {
HttpSession session;
String startDate,endDate,month,partnername,clientid,gender,agebracket,county,district;
int year,prevyear,pos,sessionno,pepfaryear,pos2,achieved,age;
int start,end,datekey;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
        session=request.getSession();
        dbConn conn = new dbConn();
        pos=0;
        
//    pepfaryear=2015;
 pepfaryear=Integer.parseInt(session.getAttribute("PepfarYear").toString());
    prevyear=pepfaryear-1;
   String enddate=pepfaryear+"09";    
   String startdate=prevyear+"10";    
        
   start=Integer.parseInt(startdate);
   end=Integer.parseInt(enddate);
    System.out.println("start date : "+start+" end date  : "+end);
//    COPY FILE TO BE WRITTEN TO 
    Path original = Paths.get(getServletContext().getRealPath("/perCounty.xlsm")); //original file
   Path destination = Paths.get(getServletContext().getRealPath("/perCounty_1.xlsm")); //new file
   System.out.println("origin :  "+original+" destination    :  "+destination);
try {
       Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
       System.out.println("file copied----------------");
    } catch (IOException x) {
       //catch all for IO problems
        System.out.println("fine not copied");
    }
    
    
        String allpath = getServletContext().getRealPath("/perCounty_1.xlsm");

    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
  XSSFWorkbook wb;
 OPCPackage pkg = OPCPackage.open(allpath);
 
wb = new XSSFWorkbook(pkg);
        
//        HSSFWorkbook wb=new HSSFWorkbook();
  XSSFSheet shet1=wb.getSheet("sheet1");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     XSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
    XSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.LIME.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);
         
   //  HSSFSheet sheet1 = wb.getSheetAt(0);
    shet1.setColumnWidth(0, 4000); 
    shet1.setColumnWidth(1, 4000); 
    shet1.setColumnWidth(2, 4000); 
    shet1.setColumnWidth(3, 4000);
    shet1.setColumnWidth(4, 4000);
    
       XSSFRow rw4=shet1.createRow(0);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    XSSFCell cell0,cell1,cell2,cell3,cell4,cell5,cell6;
   
    cell0=rw4.createCell(0);
    cell1=rw4.createCell(1);
   cell2=rw4.createCell(2);
   cell3=rw4.createCell(3);
   cell4=rw4.createCell(4);
   cell5=rw4.createCell(5);
   cell6=rw4.createCell(6);
   
 cell0 .setCellValue("DISTRICT NAME");
 cell1.setCellValue("AGE BRACKET");
 cell2.setCellValue("GENDER");
 cell3.setCellValue("MONTH");
 cell4.setCellValue("ACHIEVED");
cell5.setCellValue("COUNTY");  
cell6.setCellValue("");   

      String getClients2="SELECT county.county_name,district.district_name,"
            + "CASE " +
"when personal_information.completionmonth =01 THEN '"+pepfaryear+"-01(JAN)' " +
"when personal_information.completionmonth =02 THEN '"+pepfaryear+"-02 (FEB)' " +
"when personal_information.completionmonth =03 THEN '"+pepfaryear+"-03 (MAR)' " +
"when personal_information.completionmonth=04 THEN '"+pepfaryear+"-04 (APR)' " +
"when personal_information.completionmonth=05 THEN '"+pepfaryear+"-05 (MAY)' " +
"when personal_information.completionmonth=06 THEN '"+pepfaryear+"-06 (JUN)' " +
"when personal_information.completionmonth=07 THEN '"+pepfaryear+"-07 (JUL)' " +
"when personal_information.completionmonth=08 THEN '"+pepfaryear+"-08 (AUG)' " +
"when personal_information.completionmonth=09 THEN '"+pepfaryear+"-09 (SEPT)' " +
"when personal_information.completionmonth=10 THEN '"+prevyear+"-10 (OCT)' " +
"when personal_information.completionmonth=11 THEN '"+prevyear+"-11 (NOV)'" +
"when personal_information.completionmonth=12 THEN '"+prevyear+"-12 (DEC)'" +
"END AS MONTHS,personal_information.completionyear,personal_information.completionmonth,"
             
              + "personal_information.client_id,personal_information.gender FROM personal_information "
               + " JOIN district ON district.district_id=personal_information.district_id "
              + "JOIN county ON county.county_id=district.county_id "
              + " WHERE personal_information.completionmonth>0 && personal_information.completionyear>0";
      
      
      
      
      conn.rs=conn.st.executeQuery(getClients2);
      while(conn.rs.next()){
         
          county=conn.rs.getString(1);
          district=conn.rs.getString(2);
          month=conn.rs.getString(3);
          year=conn.rs.getInt(4);
          gender=conn.rs.getString(7);
          if(gender.equalsIgnoreCase("female")){gender="F";}
          else{gender="M";}
         String dkey=year+""+conn.rs.getString(5);
         datekey=Integer.parseInt(dkey);
//         achieved=conn.rs.getInt(6);
      if(datekey>=start && datekey<=end && year>=2014){
      System.out.println("date key : "+datekey);
      pos++;   
    XSSFRow rw4x=shet1.createRow(pos);
    rw4x.setHeightInPoints(25);
    rw4x.setRowStyle(style2);
    XSSFCell cell0x,cell1x,cell2x,cell3x,cell4x,cell5x,cell6x;
   cell0x=rw4x.createCell(0);
   cell1x=rw4x.createCell(1);
   cell2x=rw4x.createCell(2);
   cell3x=rw4x.createCell(3);
   cell4x=rw4x.createCell(4);
   cell5x=rw4x.createCell(5);
   cell6x=rw4x.createCell(6);
   //  OUTPUT SERVICES PROVIDED================================     
 cell0x .setCellValue(district);
 cell1x.setCellValue(agebracket);
 cell2x.setCellValue(gender);
 cell3x.setCellValue(month);   
 cell4x.setCellValue(conn.rs.getString(6));          
 cell5x.setCellValue(county);          
  cell6x.setCellValue(""); 
   
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

        IdGenerator IGR = new IdGenerator();
      ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_kePMS_REPORT_PER_COUNTY_FOR_PEPFAR_YEAR_"+pepfaryear+"_CREATED_ON_"+IGR.timestamp()+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();

pkg.close();
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
        Logger.getLogger(kePMSCounty.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(kePMSCounty.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(kePMSnew.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(kePMSnew.class.getName()).log(Level.SEVERE, null, ex);
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

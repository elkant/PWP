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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
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
public class kePMSGroup extends HttpServlet {
HttpSession session;
String partner_name,age,gender,county,partner_id;
int achieved;
String startDate,endDate,month,partnername,clientid,agebracket,group_name;
int year,prevyear,pos,sessionno,pepfaryear,pos2;
int start,end,datekey,incrementor;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InvalidFormatException, SQLException {
       session=request.getSession();
      dbConn conn = new dbConn();
      incrementor=0;
      partner_id=session.getAttribute("partnerDIC").toString();
      pos=0;
        
    pepfaryear=Integer.parseInt(session.getAttribute("PepfarYear").toString());
    prevyear=pepfaryear-1;
   String enddate=pepfaryear+"09";    
   String startdate=prevyear+"10"; 
   
    start=Integer.parseInt(startdate);
   end=Integer.parseInt(enddate);
   
   System.out.println(" partner id : "+partner_id+"    pepfaryear : "+pepfaryear);
      Path original = Paths.get(getServletContext().getRealPath("/AchievedGroup.xlsm")); //original file
   Path destination = Paths.get(getServletContext().getRealPath("/AchievedGroup_1.xlsm")); //new file
   System.out.println("origin :  "+original+" destination    :  "+destination);
try {
       Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
       System.out.println("file copied----------------");
    } catch (IOException x) {
       //catch all for IO problems
        System.out.println("fine not copied");
    }
    
    
        String allpath = getServletContext().getRealPath("/AchievedGroup_1.xlsm");

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
    XSSFCell cell0,cell1,cell2,cell3,cell4;
   
    cell0=rw4.createCell(0);
    cell1=rw4.createCell(1);
   cell2=rw4.createCell(2);
   cell3=rw4.createCell(3);
   cell4=rw4.createCell(4);
   
 cell0 .setCellValue("GROUP NAME");
 cell1.setCellValue("AGE BRACKET");
 cell2.setCellValue("GENDER");
 cell3.setCellValue("MONTH");
 cell4.setCellValue("ACHIEVED");
        
      
      
      
      String getData="SELECT count(personal_information.client_id),groups.group_name,"
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
"when personal_information.completionmonth=12 THEn '"+prevyear+"-12 (DEC)'" +
"END AS MONTHS,"
              + "CASE" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 0 AND 9 THEN '0-9'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 10 AND 14 THEN '10-14'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 15 AND 19 THEN '15-19'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 20 AND 24 THEN '20-24'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 25 AND 49 THEN '25-49'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) >49 THEN '50 and above'" +
 " ELSE 'NO DATE OF BIRTH'" +
"   END AS AGEBRACKET,"
              + "CASE " +
"when personal_information.gender LIKE 'Female' THEN 'F' " +
"when personal_information.gender LIKE 'Male' THEN 'M' " +
"ELSE 'NO SEX' " +
"END AS SEX,personal_information.completionyear,personal_information.completionmonth"
              + " FROM personal_information "
              + "JOIN groups ON groups.group_id=personal_information.group_id"
              + " WHERE personal_information.group_id!='0' && personal_information.partner_id='"+partner_id+"' "
              + "GROUP BY groups.group_name,SEX,AGEBRACKET,personal_information.completionyear,MONTHS ORDER BY groups.group_name";
    
      conn.rs=conn.st.executeQuery(getData);
      while(conn.rs.next()){
          month=age=gender=group_name="";achieved=0;
          
    achieved=conn.rs.getInt(1); 
    group_name=conn.rs.getString(2);
    month=conn.rs.getString(3);
    age=conn.rs.getString(4);
    gender=conn.rs.getString(5);
    String dkey=conn.rs.getString(6)+""+conn.rs.getString(7);
   datekey=Integer.parseInt(dkey);
   incrementor+=achieved;
   System.out.println("date key : "+datekey+"  start : "+start+"   end : "+end);
     if(datekey>=start && datekey<=end){
   pos++;   
    XSSFRow rw4x=shet1.createRow(pos);
    rw4x.setHeightInPoints(25);
    rw4x.setRowStyle(style2);
    XSSFCell cell0x,cell1x,cell2x,cell3x,cell4x;
   cell0x=rw4x.createCell(0);
   cell1x=rw4x.createCell(1);
   cell2x=rw4x.createCell(2);
   cell3x=rw4x.createCell(3);
   cell4x=rw4x.createCell(4);
   
   //  OUTPUT SERVICES PROVIDED================================     
 cell0x .setCellValue(group_name);
 cell1x.setCellValue(age);
 cell2x.setCellValue(gender);
 cell3x.setCellValue(month);   
 cell4x.setCellValue(achieved);          

   
 System.out.println("entered to fetch data============="+achieved);
     
     }  
     }
     
      String getDataIndividual="SELECT count(personal_information.client_id),"
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
"when personal_information.completionmonth=12 THEn '"+prevyear+"-12 (DEC)'" +
"END AS MONTHS,"
              + "CASE" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 0 AND 14 THEN '0-14'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 15 AND 19 THEN '15-19'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 20 AND 24 THEN '20-24'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) >24 THEN '>25'" +
 " ELSE 'NO DATE OF BIRTH'" +
"   END AS AGEBRACKET,"
              + "CASE " +
"when personal_information.gender LIKE 'Female' THEN 'F' " +
"when personal_information.gender LIKE 'Male' THEN 'M' " +
"ELSE 'NO SEX' " +
"END AS SEX,personal_information.completionyear,personal_information.completionmonth"
              + " FROM personal_information "
              + " WHERE personal_information.group_id='0' && personal_information.partner_id='"+partner_id+"' "
              + "GROUP BY SEX,AGEBRACKET,personal_information.completionyear,MONTHS ";
    
      conn.rs=conn.st.executeQuery(getDataIndividual);
      while(conn.rs.next()){
          month=age=gender=group_name="";achieved=0;
          
    achieved=conn.rs.getInt(1);
    incrementor+=achieved;
    
    group_name="INDIVIDUAL";
    month=conn.rs.getString(2);
    age=conn.rs.getString(3);
    gender=conn.rs.getString(4);
    String dkey=conn.rs.getString(5)+""+conn.rs.getString(6);
   datekey=Integer.parseInt(dkey);
   System.out.println("date key : "+datekey+"  start : "+start+"   end : "+end);
     if(datekey>=start && datekey<=end){
   pos++;   
    XSSFRow rw4x=shet1.createRow(pos);
    rw4x.setHeightInPoints(25);
    rw4x.setRowStyle(style2);
    XSSFCell cell0x,cell1x,cell2x,cell3x,cell4x;
   cell0x=rw4x.createCell(0);
   cell1x=rw4x.createCell(1);
   cell2x=rw4x.createCell(2);
   cell3x=rw4x.createCell(3);
   cell4x=rw4x.createCell(4);
   
   //  OUTPUT SERVICES PROVIDED================================     
 cell0x .setCellValue(group_name);
 cell1x.setCellValue(age);
 cell2x.setCellValue(gender);
 cell3x.setCellValue(month);   
 cell4x.setCellValue(achieved);          

   
 System.out.println("entered to fetch data============="+achieved);
     
     }  
     }
      
      
      
    
     String getPartner="SELECT partner_name FROM partner WHERE partner_id='"+partner_id+"'";
     conn.rs=conn.st.executeQuery(getPartner);
     if(conn.rs.next()==true){
              partner_name=conn.rs.getString(1).trim().replace(" ", "_");
     }
   session.removeAttribute("PepfarYear");
   session.removeAttribute("partnerDIC"); 
   System.out.println("incrementer : "+incrementor);
 
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

   if(incrementor>0){
      System.out.println("===============END IS HERE=============="); 
      IdGenerator CRT = new IdGenerator();
     ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_kePMS_REPORT_FOR_PEPFAR_YEAR_"+pepfaryear+"_PER_GROUP_FOR_"+partner_name+"_CREATED_ON_"+CRT.timestamp()+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();

pkg.close();
    }
   
   else{
       session.setAttribute("kePMSError", "<font color=\"red\"><b>NO DATA WITHIN THE SELECTED PARAMETERS.</b></font>");
  response.sendRedirect("kePMS.jsp");     
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
    } catch (InvalidFormatException ex) {
        Logger.getLogger(kePMSGroup.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(kePMSGroup.class.getName()).log(Level.SEVERE, null, ex);
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
    } catch (InvalidFormatException ex) {
        Logger.getLogger(kePMSGroup.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(kePMSGroup.class.getName()).log(Level.SEVERE, null, ex);
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

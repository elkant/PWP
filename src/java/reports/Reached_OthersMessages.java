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
public class Reached_OthersMessages extends HttpServlet {
HttpSession session;
String startDate,startDate1,endDate,endDate1;
String endYearMonth,year2,month,startmonth,endMonth,prevReportMonth;
int year,reportYear,quarter;
int prevReportYear,position;
String countyName,partnerName,districtName,reportDate,ageBracket,gender;
String fullName,fname,mname,lname;
String groupName,dic_name;
String ouputyear,outputMonth;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
       session=request.getSession();
      dbConn conn = new dbConn();
      
      ouputyear=outputMonth="";
      
      if(session.getAttribute("PepfarYear")!=null){
      year=Integer.parseInt(session.getAttribute("PepfarYear").toString());
      quarter=Integer.parseInt(session.getAttribute("period").toString());
//      year=2015;
//      quarter=2;  
      System.out.println("dates are : "+year+" quarter : "+quarter);
      if(quarter==4){
     reportYear=year-1; 
     startmonth="10";endMonth="12";
     prevReportYear=reportYear;prevReportMonth="09";
     
      }
      else{
    reportYear=year;   
      if(quarter==1){
          startmonth="01";endMonth="03";
          prevReportYear=reportYear-1;prevReportMonth="12";
      }
        if(quarter==2){
          startmonth="04";endMonth="06";
          prevReportYear=reportYear;prevReportMonth="03";
      }  
        if(quarter==3){
          startmonth="07";endMonth="09";
          prevReportYear=reportYear;prevReportMonth="06";
      }
      }
      
      
      startDate=""+startmonth+"/01/"+reportYear+"";
      endDate=""+endMonth+"/31/"+reportYear+"";
      
      startDate1=""+reportYear+"-"+startmonth+"-01";
      endDate1=""+reportYear+"-"+endMonth+"-31";
      
     endYearMonth=prevReportYear+""+prevReportMonth; 
     position=1;
   
//      ouputyear=""+reportYear;
      if(quarter==1){outputMonth="Jan_March_"+reportYear;}
      if(quarter==2){outputMonth="Apr_Jun_"+reportYear;}
      if(quarter==3){outputMonth="July_Sept_"+reportYear;}
      if(quarter==4){outputMonth="Oct_Dec_"+reportYear;}
           
     String  reportHeader [] =("COUNTY NAME ,PARTNER NAME,DISTRICT NAME,DIC NAME, GROUP NAME,CLIENT NAME , AGE BRACKET, GENDER,YEAR,MONTH").split(",");
     
    //    COPY FILE TO BE WRITTEN TO 
    Path original = Paths.get(getServletContext().getRealPath("/OTHER_MESSAGES.xlsm")); //original file
   Path destination = Paths.get(getServletContext().getRealPath("/OTHER_MESSAGES_1.xlsm")); //new file
   System.out.println("origin :  "+original+" destination    :  "+destination);
try {
       Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
       System.out.println("file copied----------------");
    } catch (IOException x) {
       //catch all for IO problems
        System.out.println("fine not copied");
    }
    
    
        String allpath = getServletContext().getRealPath("/OTHER_MESSAGES_1.xlsm");

    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
  XSSFWorkbook wb;
 OPCPackage pkg = OPCPackage.open(allpath);
 
wb = new XSSFWorkbook(pkg);
        


//            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
//   HSSFWorkbook wb=new HSSFWorkbook();
  XSSFSheet shet1=wb.getSheet("Sheet1");
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
    
    
    for(int i=0;i<=reportHeader.length;i++){
    shet1.setColumnWidth(i, 4000);     
    }
    
    XSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

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

  XSSFCell cell;
   XSSFRow rw0=shet1.createRow(0);
    rw0.setHeightInPoints(30);
    rw0.setRowStyle(style2);
    
    for(int i=0;i<=(reportHeader.length-1);i++){
    cell=rw0.createCell(i);
    cell.setCellValue(reportHeader[i]);
    cell.setCellStyle(stylex);
    }
    
     
     
     String query="SELECT CLIENT, " +
"" +
"if( (dateRegister BETWEEN '"+startDate1+"' AND '"+endDate1+"'),dateRegister,dateAdherence) REPORTDATE," +
" " +
"dateRegister,dateAdherence, countyName, partnerName, districtName, AGEBRACKET, SEX,fname,mname,lname,GROUPNAME,DIC FROM (" +
"SELECT DISTINCT(tempData.clientID) as CLIENT,if( (reg2Date BETWEEN '"+startDate1+"' AND '"+endDate1+"'),reg2Date,'0') dateRegister,if( (AdherenceDate BETWEEN '"+startDate1+"' AND '"+endDate1+"'),AdherenceDate,'0') dateAdherence, countyName, partnerName, districtName, AGEBRACKET, SEX,fname,mname,lname,GROUPNAME,DIC FROM (" +
"SELECT DISTINCT(personal_information.client_id) as clientID,STR_TO_DATE(register2.date,'%m/%d/%Y') as reg2Date,STR_TO_DATE(adherence.date_of_session,'%m/%d/%Y') as AdherenceDate," +
"             CONCAT(personal_information.completionyear,personal_information.completionmonth) AS YEARMONTH," +
"county.county_name AS countyName,partner.partner_name AS partnerName,district.district_name AS districtName," +
"CASE " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 0 AND 9 THEN '0-9' " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 10 AND 14 THEN '10-14' " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 15 AND 19 THEN '15-19' " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 20 AND 24 THEN '20-24' " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 25 AND 49 THEN '25-49' " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) >49 THEN '50 and above' " +
"               ELSE 'NO DATE OF BIRTH'" +
" END AS AGEBRACKET," +
"               CASE  " +
"when personal_information.gender LIKE 'Female' THEN 'F'  " +
"when personal_information.gender LIKE 'Male' THEN 'M'  " +
"ELSE 'NO SEX'  " +
"END AS SEX,personal_information.fname as fname,personal_information.mname as mname ,personal_information.lname as lname,"
             + "groups.group_name as GROUPNAME,dic.dic_name as DIC " +
"			FROM personal_information " +
"LEFT JOIN register2 ON personal_information.client_id=register2.client_id " +
"LEFT JOIN adherence ON personal_information.client_id=adherence.client_id " +
"LEFT JOIN groups ON personal_information.group_id=groups.group_id " +
"LEFT JOIN dic ON personal_information.dic_id=dic.dic_id " +
"LEFT JOIN district ON personal_information.district_id=district.district_id " +
"LEFT JOIN partner ON personal_information.partner_id=partner.partner_id " +
"LEFT JOIN county ON district.county_id=county.county_id " +
"WHERE personal_information.completionyear>0 " +
"&& (register2.datekey BETWEEN '"+startDate1.replace("-", "")+"' AND '"+endDate1.replace("-", "")+"' || STR_TO_DATE(adherence.date_of_session,'%m/%d/%Y') BETWEEN STR_TO_DATE('"+startDate+"','%m/%d/%Y') AND STR_TO_DATE('"+endDate+"','%m/%d/%Y'))" +
"         HAVING YEARMONTH<="+endYearMonth+") AS tempData GROUP BY tempData.clientID) AS finalTable "  ;
     System.out.println(query);
 conn.rs=conn.st.executeQuery(query);
 while(conn.rs.next()){
  countyName=conn.rs.getString(5);
  partnerName=conn.rs.getString(6);
  districtName=conn.rs.getString(7);        
  reportDate=conn.rs.getString(2);
  ageBracket= conn.rs.getString(8);       
  gender=conn.rs.getString(9);       
  fname=conn.rs.getString(10);
  mname=conn.rs.getString(11);
  lname=conn.rs.getString(12);       
 if(conn.rs.getString(13)!=null){groupName=conn.rs.getString(13);}
 else{groupName="Individual";}
 if(conn.rs.getString(14)!=null){groupName=conn.rs.getString(14);}
 else{dic_name="NO DIC";}
  
  if(mname.equals(lname)){mname="";}
 fullName=fname+" "+mname+" "+lname;
  String dateSplit [] =reportDate.split("-");
  
  if(dateSplit[1].equals("01")){month="Jan";}
  if(dateSplit[1].equals("02")){month="Feb";}
  if(dateSplit[1].equals("03")){month="Mar";}
  if(dateSplit[1].equals("04")){month="Apr";}
  if(dateSplit[1].equals("05")){month="May";}
  if(dateSplit[1].equals("06")){month="Jun";}
  if(dateSplit[1].equals("07")){month="Jul";}
  if(dateSplit[1].equals("08")){month="Aug";}
  if(dateSplit[1].equals("09")){month="Sept";}
  if(dateSplit[1].equals("10")){month="Oct";}
  if(dateSplit[1].equals("11")){month="Nov";}
  if(dateSplit[1].equals("12")){month="Dec";}
  
  year2=dateSplit[0];
 
String data []=(countyName+","+partnerName+","+districtName+","+dic_name+","+groupName+","+fullName+","+ageBracket+","+gender+","+year2+","+month).split(",");
 

XSSFRow rw1=shet1.createRow(position);
    rw1.setHeightInPoints(25);
    rw1.setRowStyle(style2);
    
    for(int i=0;i<=(data.length-1);i++){
    cell=rw1.createCell(i);
    cell.setCellValue(data[i]);
    cell.setCellStyle(styleBorder);
    }
    
    position++;
  
 }
     
     
   
          IdGenerator CRT = new IdGenerator();
      ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_REACHED_WITH_OTHER_MESSAGES_REPORT_FOR_"+outputMonth+"_CREATED_ON_"+CRT.timestamp()+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();

pkg.close();     
    
      }
      else{
          session.setAttribute("kePMSError", "<font color=\"red\">Error : Please try again.</font>");
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
    } catch (SQLException ex) {
        Logger.getLogger(Reached_OthersMessages.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(Reached_OthersMessages.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(Reached_OthersMessages.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(Reached_OthersMessages.class.getName()).log(Level.SEVERE, null, ex);
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

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
public class completed13Messages extends HttpServlet {
HttpSession session;
String groupName,DICName,districtName,partnerName,countyName,agebracket,month,year;
String clientFname,clientMname,clientLname,clientFullName,ccc_no,mobile_no,gender,dob,marital_status,
        location,employment_status,education_level,under_18,ovc_children,hiv_year,art_status,
        registration_date,approved_by,designation,approval_date;
String SPFname,SPMname,SPLname,SPFullName;
String healthFacility;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
       session=request.getSession();
       dbConn conn = new dbConn();
       
       position=1;
       
     String  reportHeader [] =("COUNTY NAME ,PARTNER NAME,DISTRICT NAME, DIC NAME, GROUP NAME,CLIENT FULL NAME ,"
           +" CCC NO. , MOBILE NUMBER , GENDER , DATE OF BIRTH , MARITAL STATUS , EMPLOYMENT STATUS ,"
           + "EDUCATION LEVEL , ART STATUS , SERVICE PROVIDER NAME , HEALTH FACILITY, YEAR, MONTH,AGE BRACKET").split(",");
       
     //    COPY FILE TO BE WRITTEN TO 
    Path original = Paths.get(getServletContext().getRealPath("/ALL_13_MESSAGES.xlsm")); //original file
   Path destination = Paths.get(getServletContext().getRealPath("/ALL_13_MESSAGES_1.xlsm")); //new file
   System.out.println("origin :  "+original+" destination    :  "+destination);
try {
       Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
       System.out.println("file copied----------------");
    } catch (IOException x) {
       //catch all for IO problems
        System.out.println("fine not copied");
    }
    
    
        String allpath = getServletContext().getRealPath("/ALL_13_MESSAGES_1.xlsm");

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
    
    
       String getClients="SELECT county.county_name,partner.partner_name,district.district_name,dic.dic_name,"
       +"groups.group_name,personal_information.fname,personal_information.mname,personal_information.lname,"
       + "personal_information.ccc_no,personal_information.mobile_no, personal_information.gender,"
       + "personal_information.dob,marital_status.name,employment_status.name,education_levels.name,"
       + "art_status.name,"
       + "service_provider.fname,service_provider.mname,service_provider.lname,health_facility.hf_name, "
            + "CASE " +
"when personal_information.completionmonth =01 THEN 'JAN' " +
"when personal_information.completionmonth =02 THEN 'FEB' " +
"when personal_information.completionmonth =03 THEN 'MAR' " +
"when personal_information.completionmonth=04 THEN 'APR' " +
"when personal_information.completionmonth=05 THEN 'MAY' " +
"when personal_information.completionmonth=06 THEN 'JUN' " +
"when personal_information.completionmonth=07 THEN 'JUL' " +
"when personal_information.completionmonth=08 THEN 'AUG' " +
"when personal_information.completionmonth=09 THEN 'SEPT' " +
"when personal_information.completionmonth=10 THEN 'OCT' " +
"when personal_information.completionmonth=11 THEN 'NOV'" +
"when personal_information.completionmonth=12 THEN 'DEC'" +
"END AS MONTHS,personal_information.completionyear,"
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
"END AS SEX "
              + " FROM personal_information "
              + " LEFT JOIN groups ON personal_information.group_id=groups.group_id "
              + " LEFT JOIN dic ON personal_information.dic_id=dic.dic_id "
              + " LEFT JOIN service_provider ON personal_information.provider_id=service_provider.provider_id "
              + " LEFT JOIN health_facility ON personal_information.hf_id=health_facility.hf_id "
              + " LEFT JOIN district ON personal_information.district_id=district.district_id "
               
              + " LEFT JOIN marital_status ON personal_information.marital_status=marital_status.id "
              + " LEFT JOIN employment_status ON personal_information.employment_status=employment_status.id "
              + " LEFT JOIN education_levels ON personal_information.education_level=education_levels.id "
              + " LEFT JOIN art_status ON personal_information.art_status=art_status.id "
              
               + " LEFT JOIN partner ON personal_information.partner_id=partner.partner_id "
              + " LEFT JOIN county ON district.county_id=county.county_id "
               
              + " WHERE personal_information.lessons_attended=13 "
              + " ORDER BY partner.partner_name,county.county_name,district.district_name,dic.dic_name,"
       +"groups.group_name,personal_information.completionyear,MONTHS ";
      conn.rs=conn.st.executeQuery(getClients);
      while(conn.rs.next()){
//     ADD THE DATA TO EXCEL HERE 
    groupName=DICName=districtName=partnerName=countyName=agebracket=month=year="";
    clientFname=clientMname=clientLname=ccc_no=mobile_no=gender=dob=marital_status="";
    location=employment_status=education_level=under_18=ovc_children=hiv_year=art_status="";
    registration_date=approved_by=designation=approval_date="";
    SPFname=SPMname=SPLname=SPFullName=healthFacility="";   
    
    if(conn.rs.getString(1)!=null){countyName=conn.rs.getString(1);}
    if(conn.rs.getString(2)!=null){partnerName=conn.rs.getString(2);}
    if(conn.rs.getString(3)!=null){districtName=conn.rs.getString(3);}
    if(conn.rs.getString(4)!=null){DICName=conn.rs.getString(4);}
    else{DICName="NO DIC";}
    if(conn.rs.getString(5)!=null){groupName=conn.rs.getString(5);}
    else{groupName="Individual";}
    if(conn.rs.getString(6)!=null){clientFname=conn.rs.getString(6);}
    if(conn.rs.getString(7)!=null){clientMname=conn.rs.getString(7);}
    if(conn.rs.getString(8)!=null){clientLname=conn.rs.getString(8);}
    if(conn.rs.getString(9)!=null){ccc_no=conn.rs.getString(9);}
    if(conn.rs.getString(10)!=null){mobile_no=conn.rs.getString(10);}
    if(conn.rs.getString(11)!=null){gender=conn.rs.getString(11);}
    if(conn.rs.getString(12)!=null){dob=conn.rs.getString(12);}
    if(conn.rs.getString(13)!=null){marital_status=conn.rs.getString(13);}
    if(conn.rs.getString(14)!=null){employment_status=conn.rs.getString(14);}
    if(conn.rs.getString(15)!=null){education_level=conn.rs.getString(15);}
    if(conn.rs.getString(16)!=null){art_status=conn.rs.getString(16);}
    if(conn.rs.getString(17)!=null){SPFname=conn.rs.getString(17);}
    if(conn.rs.getString(18)!=null){SPMname=conn.rs.getString(18);}
    if(conn.rs.getString(19)!=null){SPLname=conn.rs.getString(19);}
    if(conn.rs.getString(20)!=null){healthFacility=conn.rs.getString(20);}
    if(conn.rs.getString(21)!=null){month=conn.rs.getString(21);}
    if(conn.rs.getString(22)!=null){year=conn.rs.getString(22);}
    if(conn.rs.getString(23)!=null){agebracket=conn.rs.getString(23);}
    if(conn.rs.getString(24)!=null){gender=conn.rs.getString(24);}
    
    if(clientMname.equals(clientLname)){clientMname="";}
   
    if(SPMname.equals(SPLname)){SPMname="";}
  
    SPFullName=SPFname+" "+SPMname+" "+SPLname;
    clientFullName=clientFname+" "+clientMname+" "+clientLname;
   
   String rawData []=(countyName+","+partnerName+","+districtName+","+DICName+","+groupName+","+clientFullName
           +","+ccc_no+","+mobile_no+","+gender+","+dob+","+marital_status+","+employment_status+","+
           education_level+","+art_status+","+SPFullName+","+healthFacility+","+year+","+month+","+agebracket).split(",");
   
   
    XSSFRow rw1=shet1.createRow(position);
    rw1.setHeightInPoints(25);
    rw1.setRowStyle(style2);
    
    for(int i=0;i<=(rawData.length-1);i++){
    cell=rw1.createCell(i);
    cell.setCellValue(rawData[i]);
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_ATTENDED_13_SESSIONS_REPORT_CREATED_ON_"+CRT.timestamp()+".xlsm");
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
        Logger.getLogger(completed13Messages.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(completed13Messages.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(completed13Messages.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
        Logger.getLogger(completed13Messages.class.getName()).log(Level.SEVERE, null, ex);
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

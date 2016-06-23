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
import java.util.Date;
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
import pwp.dbConn;
/**
 *
 * @author Geofrey Nyabuto
 */
public class kePMSServices extends HttpServlet {
HttpSession session;
String countyname,countyid,partnername,partnerid,quarter;
String startdate,enddate,month,gender,partner,district,period;
int pos,achieved,year;
 int contraceptive_method,rsp,cds_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status;
  int prevyear,sessionno,pepfaryear,pos2;
int start,end,datekey,incrementor;
String yearMonth,monthname,agebracket;
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
 session=request.getSession();
      dbConn conn = new dbConn();
    incrementor=0;
         pos=0;
        
    pepfaryear=Integer.parseInt(session.getAttribute("PepfarYear").toString());
    prevyear=pepfaryear-1;
    String [] periods=session.getAttribute("period").toString().split("-");
     if(session.getAttribute("period").toString().equals("10-12")){
           period="OCT-DEC";
       }
      else  if(session.getAttribute("period").toString().equals("01-03")){
           period="JAN-MARCH";
       }
        else if(session.getAttribute("period").toString().equals("04-06")){
           period="APRIL-JUNE";
       }
        else  if(session.getAttribute("period").toString().equals("07-09")){
           period="JULY-SEPT";
       }
        else{}   
    startdate=periods[0]; 
    enddate=periods[1]; 
    if(session.getAttribute("period").toString().equals("10-12")){
    pepfaryear=pepfaryear-1;    
    }
    
   start=Integer.parseInt(startdate);
   end=Integer.parseInt(enddate);
    Path original = Paths.get(getServletContext().getRealPath("/ServicesAll.xlsm")); //original file
    
     Date da= new Date();
String dat2 = da.toString().replace(" ", "_");
 dat2 = dat2.toString().replace(":", "_");
   
    String mydrive = original.toString().substring(0, 1);
    
    String np=mydrive+":\\APHIAPLUS\\PWPDBCONNECTION\\ServicesAll_1"+dat2+".xlsm";
   
   
        
   Path destination = Paths.get(getServletContext().getRealPath("/ServicesAll_1.xlsm")); //new file
   
   System.out.println("origin :  "+original+" destination    :  "+destination);
try {
       Files.copy(original, destination, StandardCopyOption.REPLACE_EXISTING);
       System.out.println("file copied----------------");
    } catch (IOException x) {
       //catch all for IO problems
        System.out.println("file not copied");
    }
    
    
        String allpath = getServletContext().getRealPath("/ServicesAll.xlsm");

          //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
  XSSFWorkbook wb;
 OPCPackage pkg = OPCPackage.open(allpath);
 
wb = new XSSFWorkbook(pkg);
   
   
     //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
  XSSFSheet shet1=wb.getSheet("sheet1");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
//    font.setItalic(true);
//    font.setBoldweight((short)12);
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     XSSFFont font2=wb.createFont();
// font2.setFontHeightInPoints((short)15);
    font2.setFontName("Arial Black");
//    font.setItalic(true);
//    font2.setBoldweight((short)18);
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   XSSFCellStyle stborder = wb.createCellStyle();
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
    shet1.setColumnWidth(10, 6000);
    shet1.setColumnWidth(11, 6000);

//    shet1.setColumnWidth(20, 2000);
    XSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleBorder.setFillForegroundColor(HSSFColor.ORANGE.index);
    styleBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
//  CREATE HEADING 2
  XSSFRow rheading2=shet1.createRow(0);
  rheading2.setHeightInPoints(25);
  XSSFCell cellxx1,cellxx2,cellxx3,cellxx4,cellxx5,cellxx6,cellxx7,cellxx8,cellxx9,cellxx10,cellxx11,cellxx12,cellxx13,cellxx14,cellxx15;
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
   cellxx11=rheading2.createCell(10);
   cellxx12=rheading2.createCell(11);
   cellxx13=rheading2.createCell(12);
   cellxx14=rheading2.createCell(13);
//   cellxx15=rheading2.createCell(14);
       
 cellxx1.setCellValue("COUNTY NAME");
 cellxx2.setCellValue("PARTNER NAME");
 cellxx3.setCellValue("DISTRICT NAME");
 cellxx4.setCellValue("GENDER");
 cellxx5.setCellValue("CONTRACEPTIVE METHOD");
 cellxx6.setCellValue("REFERRED TO A SERVICE POINT");
 cellxx7.setCellValue("GIVEN CONDOMS");
 cellxx8.setCellValue("SCREENED FOR TB");
 cellxx9.setCellValue("SCREENED FOR STIS");
 cellxx10.setCellValue("TESTED PARTNER");
 cellxx11.setCellValue("TESTED CHILDREN");
 cellxx12.setCellValue("DISCLOSED STATUS");
// cellxx13.setCellValue("YEAR");
 cellxx14.setCellValue("MONTH");
 cellxx13.setCellValue("AGE BRACKET");
 
  
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
             cellxx11.setCellStyle(styleBorder);
             cellxx12.setCellStyle(styleBorder);
             cellxx13.setCellStyle(styleBorder);
//             cellxx14.setCellStyle(styleBorder);
//             cellxx15.setCellStyle(styleBorder);


pos=1;
 
 XSSFCellStyle stylex = wb.createCellStyle();
//stylex.setFillForegroundColor(HSSFColor.LIME.index);
//stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

    String  getServices="SELECT client_id,COUNTY,GENDER, bit_or(cm) AS CONTRACEPTIVE_METHOD,bit_or(sp) AS REFERRED_TO_SERVICE_POINT, " +
"SUM(cg) AS CONDOMS_GIVEN ,bit_or(st) AS SCREENED_TB ,bit_or(ss) AS SCREENED_STIS,bit_or(tp) TESTED_PARTNER, " +
"bit_or(tc) AS TESTED_CHILDREN,bit_or(ds) as DISCLOSED_STATUS,year AS pepfaryear,month as pepfarmonth,partnername as partner,districtname as district,MONTHNAME,AGEBRACKET FROM ( " +
"SELECT personal_information.client_id as client_id,county.county_name as COUNTY,personal_information.gender as GENDER, " +
" CASE " +
" WHEN services_provided.contraceptive_method= 'YES' THEN 1 " +
" WHEN services_provided.contraceptive_method= 'NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS cm, " +
"CASE " +
" WHEN services_provided.rsp LIKE 'YES' THEN 1 " +
" WHEN services_provided.rsp LIKE 'NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS sp, " +
" services_provided.cds_given AS cg, " +
"CASE " +
" WHEN services_provided.screened_tb='YES' THEN 1 " +
" WHEN services_provided.screened_tb='NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS st, " +
"CASE " +
" WHEN services_provided.screened_stis='YES' THEN 1 " +
" WHEN services_provided.screened_stis='NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS ss, " +
"CASE " +
" WHEN services_provided.tested_partner='YES' THEN 1 " +
" WHEN services_provided.tested_partner='NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS tp, " +
"CASE " +
" WHEN services_provided.tested_children= 'YES' THEN 1 " +
" WHEN services_provided.tested_children= 'NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS tc, " +
"CASE " +
" WHEN services_provided.disclosed_status= 'YES' THEN 1 " +
" WHEN services_provided.disclosed_status= 'NO' THEN 0 " +
"ELSE 'NONE' " +
"END AS ds,services_provided.submission_month AS month,services_provided.submission_year as year,partner.partner_name as partnername, "
            + " district.district_name as districtname, "
            + " CASE "
            + " WHEN services_provided.submission_month=1 THEN '"+pepfaryear+" 1.(JAN)' " 
            + " WHEN services_provided.submission_month=2 THEN '"+pepfaryear+" 2. (FEB)' " 
            + " WHEN services_provided.submission_month=3 THEN '"+pepfaryear+" 3. (MAR)' "
            + " WHEN services_provided.submission_month=4 THEN '"+pepfaryear+" 4. (APR)' "
            + " WHEN services_provided.submission_month=5 THEN '"+pepfaryear+" 5. (MAY)' " 
            + " WHEN services_provided.submission_month=6 THEN '"+pepfaryear+" 6. (JUN)' " 
            + " WHEN services_provided.submission_month=7 THEN '"+pepfaryear+" 7. (JUL)' " 
            + " WHEN services_provided.submission_month=8 THEN '"+pepfaryear+" 8. (AUG)' " 
            + " WHEN services_provided.submission_month=9 THEN '"+pepfaryear+" 9. (SEPT)' " 
            + " WHEN services_provided.submission_month=10 THEN '"+pepfaryear+" 10.(OCT)' " 
            + " WHEN services_provided.submission_month=11 THEN '"+pepfaryear+" 11.(NOV)' " 
            + " WHEN services_provided.submission_month=12 THEN '"+pepfaryear+" 12. (DEC)' "
            + " ELSE 'NO MONTH' END AS MONTHNAME, "
                        + " CASE " +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 0 AND 9 THEN '0-9'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 10 AND 14 THEN '10-14'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 15 AND 19 THEN '15-19'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 20 AND 24 THEN '20-24'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) BETWEEN 25 AND 49 THEN '25-49'" +
"      WHEN (DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( personal_information.dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( personal_information.dob, 'YYYY-%mm-%dd' ) )) >49 THEN '50 and above'" +
              " ELSE 'NO DATE OF BIRTH' " +
"   END AS AGEBRACKET "+
" FROM personal_information JOIN partner ON personal_information.partner_id=partner.partner_id "
+ ""
+ "" +
" JOIN  (district JOIN county ON county.county_id=district.county_id) ON district.district_id=personal_information.district_id " +
"           JOIN services_provided ON services_provided.client_id=personal_information.client_id" +
"           WHERE services_provided.submission_month>='"+startdate+"' && services_provided.submission_month<='"+enddate+"' "
+ "&& services_provided.submission_year='"+pepfaryear+"' order by personal_information.client_id ) as temptbl" +
" WHERE (cm>0 || sp>0 || cg>0 || st>0 || ss>0" +
" || tp>0 || tc>0 || ds>0)  GROUP BY client_id ORDER BY client_id"; 
    
     System.out.println(""+getServices);
    
 conn.rs=conn.st.executeQuery(getServices);
    while(conn.rs.next()){
       countyname=conn.rs.getString(2);
       gender=conn.rs.getString(3);
       contraceptive_method=conn.rs.getInt(4);
       rsp=conn.rs.getInt(5);
       cds_given=conn.rs.getInt(6);
       screened_tb=conn.rs.getInt(7);
       screened_stis=conn.rs.getInt(8);
       tested_partner=conn.rs.getInt(9);
       tested_children=conn.rs.getInt(10);
       disclosed_status=conn.rs.getInt(11);
       datekey=Integer.parseInt(conn.rs.getInt(12)+""+conn.rs.getInt(13));
       partner=conn.rs.getString(14);
       district=conn.rs.getString(15);
       monthname=conn.rs.getString("MONTHNAME");
       agebracket=conn.rs.getString(17);
 if(contraceptive_method>0 || rsp>0 || cds_given>0 || screened_tb>0 || screened_stis>0 || tested_partner>0 || tested_children>0 || disclosed_status>0) {  
//  CREATE ROW AND ADD DATA TO THE DATA CELLS======================
     incrementor++;
    XSSFRow data=shet1.createRow(pos);
  data.setHeightInPoints(25);
  XSSFCell cellx1,cellx2,cellx3,cellx4,cellx5,cellx6,cellx7,cellx8,cellx9,cellx10,cellx11,cellx12,cellx13,cellx14,cellx15;
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
    cellx11=data.createCell(10);
    cellx12=data.createCell(11); 
    cellx13=data.createCell(12);  
    cellx14=data.createCell(13);
//    cellx15=data.createCell(14); 
    
 cellx1.setCellValue(countyname);
 cellx2.setCellValue(partner);
 cellx3.setCellValue(district);
 cellx4.setCellValue(gender);
 cellx5.setCellValue(contraceptive_method);
 cellx6.setCellValue(rsp);
 cellx7.setCellValue(cds_given);
 cellx8.setCellValue(screened_tb);
 cellx9.setCellValue(screened_stis);
 cellx10.setCellValue(tested_partner);
 cellx11.setCellValue(tested_children);
  cellx12.setCellValue(disclosed_status);
 cellx14.setCellValue(monthname);
 cellx13.setCellValue(agebracket);
// cellx15.setCellValue();
  
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
             cellx11.setCellStyle(stylex);
             cellx12.setCellStyle(stylex); 
             cellx13.setCellStyle(stylex); 
//             cellx14.setCellStyle(stylex);
//             cellx15.setCellStyle(stylex); 

   pos++;
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

     if(incrementor>0){   
    // write it as an excel attachment
          if(session.getAttribute("period").toString().equals("10-12")){
            pepfaryear++;
        }
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_SERVICES_PROVIDED_REPORT_FOR_PEPFAR_YEAR_"+pepfaryear+"("+period+").xlsm");
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
    } catch (SQLException ex) {
        Logger.getLogger(kePMSServices.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidFormatException ex) {
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
    } catch (InvalidFormatException ex) {
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

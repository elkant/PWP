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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class getMonthlyCompletionRate extends HttpServlet {
HttpSession session;
String partnername,partnerid,month,month2;
int completedmale,completedfemale,completedmale1,completedfemale1;
int completedmale2,completedfemale2,completedmale3,completedfemale3;
int attended=0,attendedx=0,age,position;
String gender;

int completedmalex,completedfemalex,completedmale1x,completedfemale1x;
int completedmale2x,completedfemale2x,completedmale3x,completedfemale3x;

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
    shet1.setColumnWidth(0, 4000); 
    shet1.setColumnWidth(1, 1500); 
    shet1.setColumnWidth(2, 2000); 
    shet1.setColumnWidth(3, 2000);
    
    shet1.setColumnWidth(4, 2000); 
    
    shet1.setColumnWidth(5, 2000); 
    shet1.setColumnWidth(6, 2000);
    shet1.setColumnWidth(7, 2000); 
    shet1.setColumnWidth(8, 2000); 
    shet1.setColumnWidth(9, 2000); 
    shet1.setColumnWidth(10, 2000); 
    shet1.setColumnWidth(11, 2000);  
    shet1.setColumnWidth(12, 2000); 
    shet1.setColumnWidth(13, 2000);
    //    shet1.setColumnWidth(20, 2000);
    HSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleBorder.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
    styleBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  cell=rw1.createCell(0);
  cell.setCellValue("kePMS Report");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,13));
  
position=2;
      String getPartners="SELECT * FROM partner";
        conn.rs=conn.st.executeQuery(getPartners);
        while(conn.rs.next()){
            partnername=partnerid="";
            completedmale=completedfemale=0;completedmale2=completedfemale2=0;
            completedmale3=completedfemale3=0;completedmale1=completedfemale1=0;
          partnerid=conn.rs.getString(1);
           partnername=conn.rs.getString(2);
           
  HSSFRow rheading2=shet1.createRow(position);
  rheading2.setHeightInPoints(25);
  shet1.addMergedRegion(new CellRangeAddress(position,position,0,13));
  
   HSSFCell cellxx1=rheading2.createCell(0);
  HSSFCell cellxx2=rheading2.createCell(1);
   
  HSSFCell cellxx3=rheading2.createCell(2);
  HSSFCell cellxx4=rheading2.createCell(3);
  HSSFCell cellxx5=rheading2.createCell(4);
  HSSFCell cellxx6=rheading2.createCell(5);
   
  HSSFCell cellxx7=rheading2.createCell(6);
  HSSFCell cellxx8=rheading2.createCell(7);
  HSSFCell cellxx9=rheading2.createCell(8);
  HSSFCell cellxx10=rheading2.createCell(9);
   
  HSSFCell cellxx11=rheading2.createCell(10);
  HSSFCell cellxx12=rheading2.createCell(11);
  HSSFCell cellxx13=rheading2.createCell(12);
  HSSFCell cellxx14=rheading2.createCell(13);
  

  
  cellxx1.setCellValue("PARTNER : "+partnername);
    
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
             cellxx14.setCellStyle(styleBorder);
          position+=2;   
             
//             HEADINGS===============================================
       HSSFRow rheading=shet1.createRow(position);
  rheading2.setHeightInPoints(25);
  
   HSSFCell cellx1=rheading.createCell(0);
  HSSFCell cellx2=rheading.createCell(1);
   
  HSSFCell cellx3=rheading.createCell(2);
  HSSFCell cellx4=rheading.createCell(3);
  HSSFCell cellx5=rheading.createCell(4);
  HSSFCell cellx6=rheading.createCell(5);
   
  HSSFCell cellx7=rheading.createCell(6);
  HSSFCell cellx8=rheading.createCell(7);
  HSSFCell cellx9=rheading.createCell(8);
  HSSFCell cellx10=rheading.createCell(9);
   
  HSSFCell cellx11=rheading.createCell(10);
  HSSFCell cellx12=rheading.createCell(11);
  HSSFCell cellx13=rheading.createCell(12);
  HSSFCell cellx14=rheading.createCell(13);  
    
  cellx1.setCellValue("AGE BRACKET ");
  cellx2.setCellValue("APRIL");
  cellx3.setCellValue("");
  cellx4.setCellValue("MAY");
  cellx5.setCellValue("");
  cellx6.setCellValue("JUNE");
  cellx7.setCellValue("");
  cellx8.setCellValue("JULY");
  cellx9.setCellValue("");
  cellx10.setCellValue("AUGUST");
  cellx11.setCellValue("");
  cellx12.setCellValue("SEPT");
  cellx13.setCellValue("");
    
             cellx1.setCellStyle(styleBorder);
             cellx2.setCellStyle(styleBorder);
             cellx3.setCellStyle(styleBorder);
             cellx4.setCellStyle(styleBorder);
             cellx5.setCellStyle(styleBorder);
             cellx6.setCellStyle(styleBorder);
             cellx7.setCellStyle(styleBorder);
             cellx8.setCellStyle(styleBorder);
             cellx9.setCellStyle(styleBorder);
             cellx10.setCellStyle(styleBorder);
             cellx11.setCellStyle(styleBorder);
             cellx12.setCellStyle(styleBorder);
             cellx13.setCellStyle(styleBorder);
             cellx14.setCellStyle(styleBorder);
             
            shet1.addMergedRegion(new CellRangeAddress(position,position,1,2));
  shet1.addMergedRegion(new CellRangeAddress(position,position,3,4));
  shet1.addMergedRegion(new CellRangeAddress(position,position,5,6));
  shet1.addMergedRegion(new CellRangeAddress(position,position,7,8));
  shet1.addMergedRegion(new CellRangeAddress(position,position,9,10));
  shet1.addMergedRegion(new CellRangeAddress(position,position,11,12));   
             position++;
              HSSFRow rheading1=shet1.createRow(position);
  rheading2.setHeightInPoints(25);
  
   HSSFCell cell1=rheading1.createCell(0);
  HSSFCell cell2=rheading1.createCell(1);
   
  HSSFCell cell3=rheading1.createCell(2);
  HSSFCell cell4=rheading1.createCell(3);
  HSSFCell cell5=rheading1.createCell(4);
  HSSFCell cell6=rheading1.createCell(5);
   
  HSSFCell cell7=rheading1.createCell(6);
  HSSFCell cell8=rheading1.createCell(7);
  HSSFCell cell9=rheading1.createCell(8);
  HSSFCell cell10=rheading1.createCell(9);
   
  HSSFCell cell11=rheading1.createCell(10);
  HSSFCell cell12=rheading1.createCell(11);
  HSSFCell cell13=rheading1.createCell(12);
  HSSFCell cell14=rheading1.createCell(13);  
    
  cell1.setCellValue("");
  cell2.setCellValue("M");
  cell3.setCellValue("F");
  cell4.setCellValue("M");
  cell5.setCellValue("F");
  cell6.setCellValue("M");
  cell7.setCellValue("F");
  cell8.setCellValue("M");
  cell9.setCellValue("F");
  cell10.setCellValue("M");
  cell11.setCellValue("F");
  cell12.setCellValue("M");
  cell13.setCellValue("F");
    
             cell1.setCellStyle(styleBorder);
             cell2.setCellStyle(styleBorder);
             cell3.setCellStyle(styleBorder);
             cell4.setCellStyle(styleBorder);
             cell5.setCellStyle(styleBorder);
             cell6.setCellStyle(styleBorder);
             cell7.setCellStyle(styleBorder);
             cell8.setCellStyle(styleBorder);
             cell9.setCellStyle(styleBorder);
             cell10.setCellStyle(styleBorder);
             cell11.setCellStyle(styleBorder);
             cell12.setCellStyle(styleBorder);
             cell13.setCellStyle(styleBorder);
             cell14.setCellStyle(styleBorder);
            position++; 
  HSSFRow rheadingS1=shet1.createRow(position);
  rheadingS1.setHeightInPoints(20);
  HSSFCell cellS1=rheadingS1.createCell(0);
  cellS1.setCellValue("0-14");
  position++;
  HSSFRow rheadingS2=shet1.createRow(position);
  rheadingS2.setHeightInPoints(20);
  HSSFCell cellS2=rheadingS2.createCell(0);
  cellS2.setCellValue("15-19");
  position++;
  HSSFRow rheadingS3=shet1.createRow(position);
  rheadingS3.setHeightInPoints(20);
  HSSFCell cellS3=rheadingS3.createCell(0);
  cellS3.setCellValue("20-24");
  position++;
  HSSFRow rheadingS4=shet1.createRow(position);
  rheadingS4.setHeightInPoints(20);
  HSSFCell cellS4=rheadingS4.createCell(0);
   cellS4.setCellValue(">=25");
   position++;
   
   int cnt2=1;
   
            for(int i=4;i<=9;i++){
                 month="0"+i;
                 int j=i-1;
                 month2="0"+j;
                 
           String getClient="SELECT client_id,age,gender FROM clients WHERE partner_id='"+partnerid+"' && lessons_attended>2 && year='2014'";
           conn.rs2=conn.st2.executeQuery(getClient);
           while(conn.rs2.next()){
               age=conn.rs2.getInt(2);
               gender=conn.rs2.getString(3);
         System.out.println("client id   :    "+conn.rs2.getString(1));
       String counter="SELECT SUM(register2.value) FROM register2 WHERE  month<='"+month+"'&& register2.session_no='9' && register2.value='1' && client_id='"+conn.rs2.getString(1)+"'";   
        conn.rs1=conn.st1.executeQuery(counter);
        if(conn.rs1.next()==true){
    attended=conn.rs1.getInt(1);     
        }
        
        String counter2="SELECT SUM(register2.value) FROM register2 WHERE  month<='"+month2+"' && register2.session_no='9' && register2.value='1' && client_id='"+conn.rs2.getString(1)+"'";   
        conn.rs1=conn.st1.executeQuery(counter2);
        if(conn.rs1.next()==true){
    attendedx=conn.rs1.getInt(1);     
        }
        System.out.println("attended   :    "+attended);
        if(attended>2){
    if(gender.equalsIgnoreCase("female")){
    if(age>0&&age<15){
     completedfemale++;   
    }
    else if(age>14&&age<20){
      completedfemale1++;  
    }
    else if(age>19&&age<25){
      completedfemale2++;  
    }
    else if(age>24){
       completedfemale3++; 
    }
        
    }
    else{
     if(age>0&&age<15){
     completedmale++;   
    }
    else if(age>14&&age<20){
      completedmale1++;  
    }
    else if(age>19&&age<25){
      completedmale2++;  
    }
    else if(age>24){
       completedmale3++; 
    }   
        
    }
    }
//      ATTENDED PREVIOUSLY=============================
         if(attendedx==13){
    if(gender.equalsIgnoreCase("female")){
    if(age>0&&age<15){
     completedfemalex++;   
    }
    else if(age>14&&age<20){
      completedfemale1x++;  
    }
    else if(age>19&&age<25){
      completedfemale2x++;  
    }
    else if(age>24){
       completedfemale3x++; 
    }
        
    }
    else{
     if(age>0&&age<15){
     completedmalex++;   
    }
    else if(age>14&&age<20){
      completedmale1x++;  
    }
    else if(age>19&&age<25){
      completedmale2x++;  
    }
    else if(age>24){
       completedmale3x++; 
    }   
        
    }
    }  
        
        
        
           }
       if(completedmale3>0 || completedmale2>0 || completedmale1>0 || completedmale>0 ||completedfemale3>0 || completedfemale2>0 || completedfemale1>0 || completedfemale>0 ){
           System.out.println(" here completed     :      "+partnername);
       } 
//       if(partnerid.equals(""))
      
//        ADD DATA FOR EACH MONTH=================================================
      HSSFCell cellS11=rheadingS1.createCell(cnt2);
      HSSFCell cellS12=rheadingS1.createCell(cnt2+1);
  cellS11.setCellValue(completedmale-completedmalex); 
   cellS12.setCellValue(completedfemale-completedfemale); 
   
     HSSFCell cellS21=rheadingS2.createCell(cnt2);
     HSSFCell cellS22=rheadingS2.createCell(cnt2+1);
  cellS21.setCellValue(completedmale1-completedmale1x);
  cellS22.setCellValue(completedfemale1-completedfemale1x);
  
  HSSFCell cellS31=rheadingS3.createCell(cnt2);
  HSSFCell cellS32=rheadingS3.createCell(cnt2+1);
  cellS31.setCellValue(completedmale2-completedmale2x);
  cellS32.setCellValue(completedfemale2-completedfemale2x);
  
  HSSFCell cellS41=rheadingS4.createCell(cnt2);
  HSSFCell cellS42=rheadingS4.createCell(cnt2+1);
   cellS41.setCellValue(completedmale3-completedmale3x);
   cellS42.setCellValue(completedfemale3-completedfemale3x);  
   
   
       completedfemale3=completedmale3 =completedfemale2=completedmale2=0;
       completedfemale1=completedmale1 =completedfemale=completedmale=0;
       
       completedfemale3x=completedmale3x =completedfemale2x=completedmale2x=0;
       completedfemale1x=completedmale1x =completedfemalex=completedmalex=0;
       
cnt2+=2;
            }    
//            GET ANOTHER PARTNER=======================================
           System.out.println("partner name  :   "+partnername);
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_kePMS_Report.xls");
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
        Logger.getLogger(getMonthlyCompletionRate.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(getMonthlyCompletionRate.class.getName()).log(Level.SEVERE, null, ex);
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

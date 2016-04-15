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
public class CompletionSummary extends HttpServlet {
HttpSession session;
String county_name,partner_name,county_id,partner_id,district_id;
int total,completed,comp_receiveService,incomp_receiveService;
int year,q1,q2,q3,q4,attended,date,givenservice;
String start_date,end_date;
int q1s,q1e,q2s,q3s,q4s,q2e,q3e,q4e;
String client_id;
int q1c,q1i,q2c,q2i,q3c,q3i,q4c,q4i;
int pos,q1t,q2t,q3t,q4t;
int q1comp,q2comp,q3comp,q4comp;
int prevyear=0;
String sq1,sq2,sq3,sq4,sq5,sq6,sq7,sq8,sq9,sq10;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        dbConn conn = new dbConn();
       total=completed=comp_receiveService=incomp_receiveService=0;
       county_name=partner_name="";
       
     String yeargt=request.getParameter("year");
     year=Integer.parseInt(yeargt);
     prevyear=year-1;
     sq1=prevyear+""+1001;
     sq2=prevyear+""+1231;
     sq3=year+""+101;
     sq4=year+""+331;
     sq5=year+""+401;
     sq6=year+""+631;
     sq7=year+""+701;
     sq8=year+""+931;
             
     q1s=Integer.parseInt(sq1);
     q1e=Integer.parseInt(sq2);
     q2s=Integer.parseInt(sq3);
     q2e=Integer.parseInt(sq4);
     q3s=Integer.parseInt(sq5);
     q3e=Integer.parseInt(sq6);
     q4s=Integer.parseInt(sq7);
     q4e=Integer.parseInt(sq8);
     
     
     
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
    shet1.setColumnWidth(0, 2000); 
    shet1.setColumnWidth(1, 3500); 
    shet1.setColumnWidth(2, 5000); 
    shet1.setColumnWidth(3, 5000);
    
    shet1.setColumnWidth(4, 5000); 
    
    shet1.setColumnWidth(5, 5000); 
    shet1.setColumnWidth(6, 5000);
    shet1.setColumnWidth(7, 5000); 
    shet1.setColumnWidth(8, 5000); 
    shet1.setColumnWidth(9, 5000); 
    shet1.setColumnWidth(10, 5000); 
    shet1.setColumnWidth(11, 5000);  
    shet1.setColumnWidth(12, 5000); 
    
    shet1.setColumnWidth(13, 5000); 
    
    shet1.setColumnWidth(14, 5000); 
    shet1.setColumnWidth(15, 5000); 
    shet1.setColumnWidth(16, 5000); 
    shet1.setColumnWidth(17, 5000);
    shet1.setColumnWidth(18, 5000); 
    shet1.setColumnWidth(19, 5000); 
    shet1.setColumnWidth(20, 5000); 
    shet1.setColumnWidth(21, 5000);
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
  cell.setCellValue("PWP COMPLETION SUMMARY PER QUARTER");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,19));
    
//  CREATE HEADING 2
  HSSFRow rheading2=shet1.createRow(2);
  rheading2.setHeightInPoints(25);
  HSSFCell cellxx1,cellxx2,cellxx3,cellxx4,cellxx5,cellxx6,cellxx7,cellxx8,cellxx9,cellxx10,cellxx11,cellxx12,cellxx13,cellxx14,cellxx15,cellxx16,cellxx17,cellxx18,cellxx19,cellxx20;
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
   
   cellxx15=rheading2.createCell(14);
   cellxx16=rheading2.createCell(15);
   cellxx17=rheading2.createCell(16);
   cellxx18=rheading2.createCell(17);
   
   cellxx19=rheading2.createCell(18); 
//   int prevyear=year-1;
 cellxx3.setCellValue("OCT - DEC "+prevyear);
 cellxx7.setCellValue("JAN - MARCH "+year);
 cellxx11.setCellValue("APRIL - JUNE "+year);
 cellxx15.setCellValue("JULY - SEPT"+year);
  shet1.addMergedRegion(new CellRangeAddress(2,2,2,5));
  shet1.addMergedRegion(new CellRangeAddress(2,2,6,9));
  shet1.addMergedRegion(new CellRangeAddress(2,2,10,13));
  shet1.addMergedRegion(new CellRangeAddress(2,2,14,17));
  
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
             cellxx15.setCellStyle(styleBorder);
             cellxx16.setCellStyle(styleBorder);
             cellxx17.setCellStyle(styleBorder);
             cellxx18.setCellStyle(styleBorder);
             cellxx19.setCellStyle(styleBorder);
  
   HSSFRow rw4=shet1.createRow(3);
    rw4.setHeightInPoints(75);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16,cell17,cell18,cell19,cell20;
    HSSFCell cell21,cell22;
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
   cell15=rw4.createCell(14);
   cell16=rw4.createCell(15);
   cell17=rw4.createCell(16);
   cell18=rw4.createCell(17);
   cell19=rw4.createCell(18);
   cell20=rw4.createCell(19);
   cell21=rw4.createCell(20);
   cell22=rw4.createCell(21);
   
 cell1 .setCellValue("COUNTY NAME");
 cell2.setCellValue("PARTNER NAME");
 
 cell3.setCellValue("TOTAL ENROLLED");
 cell4.setCellValue("COMPLETED ALL SESSIONS");
 cell5.setCellValue("COMPLETED ALL SESSIONS AND RECEIVED SERVICES");
 cell6 .setCellValue("DID NOT COMPLETE ALL SESSIONS BUT RECEIVED SERVICES");
 
 cell7.setCellValue("TOTAL ENROLLED");
 cell8.setCellValue("COMPLETED  ALL SESSIONS");
 cell9.setCellValue("COMPLETED ALL SESSIONS AND RECEIVED SERVICES");
 cell10 .setCellValue("DID NOT COMPLETE ALL SESSIONS BUT RECEIVED SERVICES");
 
 cell11.setCellValue("TOTAL ENROLLED");
 cell12.setCellValue("COMPLETED  ALL SESSIONS");
 cell13.setCellValue("COMPLETED ALL SESSIONS AND RECEIVED SERVICES");
 cell14.setCellValue("DID NOT COMPLETE ALL SESSIONS BUT RECEIVED SERVICES");
  
 cell15 .setCellValue("TOTAL ENROLLED");
 cell16.setCellValue("COMPLETED  ALL SESSIONS");
 cell17.setCellValue("COMPLETED ALL SESSIONS AND RECEIVED SERVICES");
 cell18.setCellValue("DID NOT COMPLETE ALL SESSIONS BUT RECEIVED SERVICES");
 
 cell19 .setCellValue("TOTAL");
// cell20.setCellValue("Partner Tested");
// cell21.setCellValue("Children Tested");
// cell22.setCellValue("Disclosed Status");
 

pos=4;
 
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
cell14.setCellStyle(stylex);
cell15.setCellStyle(stylex);
cell16.setCellStyle(stylex);
cell17.setCellStyle(stylex);
cell18.setCellStyle(stylex);
cell19.setCellStyle(stylex);
//cell20.setCellStyle(stylex);
//cell21.setCellStyle(stylex);
//cell22.setCellStyle(stylex);
   
     
     
     
     
     
     String county_selector="SELECT county_name,county_id FROM county";
     conn.rs=conn.st.executeQuery(county_selector);
     while(conn.rs.next()){
//      GET COUNTY NAME
         county_name="";
         county_name=conn.rs.getString(1);
         county_id=conn.rs.getString(2);
         
         String getpartners="SELECT * FROM partner";
         conn.rs0=conn.st0.executeQuery(getpartners);
         while(conn.rs0.next()){
             partner_id=conn.rs0.getString(1);
             partner_name=conn.rs0.getString(2);
            q1c=q1i=q2c=q2i=q3c=q3i=q4c=q4i=0;
             q1comp=q2comp=q3comp=q4comp=0;
             q1t=q2t=q3t=q4t=0;
             total=0;
             System.out.println("partner : "+partner_name);
         String distselector="SELECT district_id FROM district WHERE county_id='"+county_id+"'";
         conn.rs1=conn.st1.executeQuery(distselector);
         while(conn.rs1.next()){
             district_id="";
             district_id=conn.rs1.getString(1);
            String getq="SELECT COUNT(client_id) FROM clients WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"' && (timestamp BETWEEN '2013-10-01' AND '2013-12-31')";  
          conn.rs2=conn.st2.executeQuery(getq);
          while(conn.rs2.next()){
              q1t+=conn.rs2.getInt(1);
          }
          
          String getq1="SELECT COUNT(client_id) FROM clients WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"' && (timestamp BETWEEN '2014-1-01' AND '2014-3-31')";  
          conn.rs2=conn.st2.executeQuery(getq1);
          while(conn.rs2.next()){
              q2t+=conn.rs2.getInt(1);
          }
          String getq2="SELECT COUNT(client_id) FROM clients WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"' && (timestamp BETWEEN '2014-4-01' AND '2014-6-31')";  
          conn.rs2=conn.st2.executeQuery(getq2);
          while(conn.rs2.next()){
              q3t+=conn.rs2.getInt(1);
          }
          String getq3="SELECT COUNT(client_id) FROM clients WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"' && (timestamp BETWEEN '2014-7-01' AND '2014-9-30')";  
          conn.rs2=conn.st2.executeQuery(getq3);
          while(conn.rs2.next()){
              q4t+=conn.rs2.getInt(1);
          }
String getCompleted="SELECT COUNT(DISTINCT clients.client_id) FROM clients JOIN register2 ON clients.client_id=register2.client_id "
        + "WHERE clients.partner_id='"+partner_id+"' && register2.month>9 && register2.month<=12 && clients.district_id='"+district_id+"' && register2.year='2014' && register2.value=1 GROUP BY register2.client_id HAVING SUM(register2.value)=13";
    conn.rs2=conn.st2.executeQuery(getCompleted);
    if(conn.rs2.next()==true){
    conn.rs2.last();
    q1comp+=conn.rs2.getRow();
    conn.rs2.beforeFirst();
    }
    
    String getCompleted1="SELECT COUNT(DISTINCT clients.client_id) FROM clients JOIN register2 ON clients.client_id=register2.client_id "
        + "WHERE clients.partner_id='"+partner_id+"' && register2.month>0 && register2.month<=3 && clients.district_id='"+district_id+"' && register2.year='2014' && register2.value=1 GROUP BY register2.client_id HAVING SUM(register2.value)=13";
    conn.rs2=conn.st2.executeQuery(getCompleted1);
    if(conn.rs2.next()==true){
    conn.rs2.last();
    q2comp+=conn.rs2.getRow();
    conn.rs2.beforeFirst();
    }
    
    String getCompleted2="SELECT COUNT(DISTINCT clients.client_id) FROM clients JOIN register2 ON clients.client_id=register2.client_id "
        + "WHERE clients.partner_id='"+partner_id+"' && register2.month>3 && register2.month<=6 && clients.district_id='"+district_id+"' && register2.year='2014' && register2.value=1 GROUP BY register2.client_id HAVING SUM(register2.value)=13";
    conn.rs2=conn.st2.executeQuery(getCompleted2);
    if(conn.rs2.next()==true){
    conn.rs2.last();
    q3comp+=conn.rs2.getRow();
    conn.rs2.beforeFirst();
    }
    
    String getCompleted3="SELECT COUNT(DISTINCT clients.client_id) FROM clients JOIN register2 ON clients.client_id=register2.client_id "
        + "WHERE clients.partner_id='"+partner_id+"' && register2.month>6 && register2.month<=9 && clients.district_id='"+district_id+"' && register2.year='2014' && register2.value=1 GROUP BY register2.client_id HAVING SUM(register2.value)=13";
    conn.rs2=conn.st2.executeQuery(getCompleted3);
    if(conn.rs2.next()==true){
    conn.rs2.last();
    q4comp+=conn.rs2.getRow();
    conn.rs2.beforeFirst();
    }
    
//    GET DATA FOR THE SERVICES GIVEN=====================================================
    
    }
         
       if (q1t>0 || q2t>0 || q3t>0 || q4t>0){
           total=q1t+q2t+q3t+q4t;
        HSSFRow rwx= shet1.createRow(pos);
             rwx.setHeightInPoints(20);
             HSSFCell cellx1,cellx2,cellx3, cellx4, cellx5,cellx6,cellx7,cellx8,cellx9, cellx10;
             HSSFCell cellx11,cellx12,cellx13, cellx14, cellx15,cellx16,cellx17,cellx18,cellx19, cellx20,cellx21, cellx22;
             cellx1=rwx.createCell(0);
             cellx2=rwx.createCell(1);
             cellx3=rwx.createCell(2);
             cellx4=rwx.createCell(3);
             cellx5=rwx.createCell(4);
             cellx6=rwx.createCell(5);
             cellx7=rwx.createCell(6);
             cellx8=rwx.createCell(7);
             cellx9=rwx.createCell(8);
             cellx10=rwx.createCell(9);
             cellx11=rwx.createCell(10);
             cellx12=rwx.createCell(11);
             cellx13=rwx.createCell(12);
             cellx14=rwx.createCell(13);
             cellx15=rwx.createCell(14);
             cellx16=rwx.createCell(15);
             cellx17=rwx.createCell(16);
             cellx18=rwx.createCell(17);
             cellx19=rwx.createCell(18);
//             cellx20=rwx.createCell(19);
//             cellx21=rwx.createCell(20);
//             cellx22=rwx.createCell(21); 
             cellx1.setCellValue(county_name);
             cellx2.setCellValue(partner_name);
             
             cellx3.setCellValue(q1t);
             cellx4.setCellValue(q1comp);
             cellx5.setCellValue(q1c);
             cellx6.setCellValue(q1i);
             
             cellx7.setCellValue(q2t);
             cellx8.setCellValue(q2comp-q1comp);
             cellx9.setCellValue(q2c);
             cellx10.setCellValue(q2i);
             
             cellx11.setCellValue(q3t);
             cellx12.setCellValue(q3comp-q2comp);
             cellx13.setCellValue(q3c);
             cellx14.setCellValue(q3i);
             
             cellx15.setCellValue(q4t);
             cellx16.setCellValue(q4comp-q3comp);
             cellx17.setCellValue(q4c);
             cellx18.setCellValue(q4i);
             
             cellx19.setCellValue(total);
             
         cellx1.setCellStyle(stborder);
             cellx2.setCellStyle(stborder);
             cellx3.setCellStyle(stborder);
             cellx4.setCellStyle(stborder);
             cellx5.setCellStyle(stborder);
             cellx6.setCellStyle(stborder);
             cellx7.setCellStyle(stborder);
             cellx8.setCellStyle(stborder);
             cellx9.setCellStyle(stborder);
             cellx10.setCellStyle(stborder);
             cellx11.setCellStyle(stborder);
             cellx12.setCellStyle(stborder);
             cellx13.setCellStyle(stborder);
             cellx14.setCellStyle(stborder);
             cellx15.setCellStyle(stborder);
             cellx16.setCellStyle(stborder);
             cellx17.setCellStyle(stborder);
             cellx18.setCellStyle(stborder);
             cellx19.setCellStyle(stborder);
         
        pos++; 
        
        System.out.println("here partner : "+partner_name);
         }
         } //END PARTNER SELECTION
   
     }//end of county----------------------
     
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_Completion_Rate_Summary.xls");
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
        Logger.getLogger(CompletionSummary.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(CompletionSummary.class.getName()).log(Level.SEVERE, null, ex);
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

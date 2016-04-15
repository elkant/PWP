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
public class completedSessions extends HttpServlet {
HttpSession session;
String countyname,countyid,partnername,partnerid,quarter;
String startdate,enddate,month,start,end;
int pos,achieved,year,attended,comp1,comp2;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
      dbConn conn = new dbConn();
      
      String [] starter=request.getParameter("start_date").split("/");
   String [] ender=request.getParameter("end_date").split("/");
   String m1="",m2="";
   String d1="",d2="",y1="",y2="";
   
   start=request.getParameter("start_date");
   end=request.getParameter("end_date");
   
   m1=starter[1];
   m2=ender[1];
   d1=starter[0];
   d2=ender[0];
   y1=starter[2];
   y2=ender[2];
   
   startdate=y1+""+m1+""+d1;
   enddate=y2+""+m2+""+d2;
   
   System.out.println("start date   :   "+startdate+"     end  date    :    "+enddate);
   
     //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)12);
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
    shet1.setColumnWidth(0, 9000); 
    shet1.setColumnWidth(1, 9000); 
    shet1.setColumnWidth(2, 9000); 
//    shet1.setColumnWidth(3, 6000);
//    shet1.setColumnWidth(4, 6000); 

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
  cell.setCellValue("PWP SESSIONS COMPLETION REPORT BETWEEN "+start+" AND "+end+"");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,2));
    
//  CREATE HEADING 2
  HSSFRow rheading2=shet1.createRow(2);
  rheading2.setHeightInPoints(25);
  HSSFCell cellxx1,cellxx2,cellxx3,cellxx4,cellxx5,cellxx6;
  cellxx1=rheading2.createCell(0);
  cellxx2=rheading2.createCell(1);
    cellxx3=rheading2.createCell(2);
//    cellxx4=rheading2.createCell(3);
//    cellxx5=rheading2.createCell(4);
//    cellxx6=rheading2.createCell(5);


 cellxx1.setCellValue("COUNTY NAME");
 cellxx2.setCellValue("PARTNER NAME");
 cellxx3.setCellValue("TOTAL COMPLETED");
// cellxx4.setCellValue("MONTH");
// cellxx5.setCellValue("GIVED SERVICES");

  
             cellxx1.setCellStyle(styleBorder);
             cellxx2.setCellStyle(styleBorder);
             cellxx3.setCellStyle(styleBorder);
//             cellxx4.setCellStyle(styleBorder);
//             cellxx5.setCellStyle(styleBorder);


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
     
      
   String   getCOUNTY="SELECT * FROM county";
   conn.rs=conn.st.executeQuery(getCOUNTY);
   while(conn.rs.next()){
     countyname=conn.rs.getString(2);
       String getPartner="SELECT * FROM partner";
       conn.rs1=conn.st1.executeQuery(getPartner);
       while(conn.rs1.next()){
           partnername=conn.rs1.getString(2);
           achieved=comp1=comp2=0;
//      DATA FOR THE PARTNER-------------------------     

      String checkCompleted="SELECT DISTINCT clients.client_id "
              + "FROM clients JOIN district ON clients.district_id=district.district_id"
              + " JOIN register2 ON clients.client_id=register2.client_id "
              + "WHERE district.county_id='"+conn.rs.getString(1)+"' && clients.partner_id='"+conn.rs1.getString(1)+"' && "
              + " register2.datekey<='"+startdate+"' && register2.value='1' GROUP BY register2.client_id HAVING SUM(value)=13";
      conn.rs3=conn.st3.executeQuery(checkCompleted);
      if(conn.rs3.next()==true){
     conn.rs3.last();
    comp1=conn.rs3.getRow();
    conn.rs3.beforeFirst();
      }  
      
      String checkCompleted2="SELECT DISTINCT clients.client_id "
              + "FROM clients JOIN district ON clients.district_id=district.district_id"
              + " JOIN register2 ON clients.client_id=register2.client_id "
              + "WHERE district.county_id='"+conn.rs.getString(1)+"' && clients.partner_id='"+conn.rs1.getString(1)+"' && "
              + " register2.datekey<='"+enddate+"' && register2.value='1' GROUP BY register2.client_id HAVING SUM(value)=13";
      conn.rs3=conn.st3.executeQuery(checkCompleted2);
      if(conn.rs3.next()==true){
     conn.rs3.last();
    comp2=conn.rs3.getRow();
    conn.rs3.beforeFirst();
      }
      
      achieved=comp2-comp1;
      
      System.out.println("county name  "+partnername+"   partner name "+partnername+" attended  :    "+achieved);
   
//   COMPLETED PER PARTNER HERE--------------------------------
      
      if(achieved>0){
//     OUTPUT HERE TO EXCEL>>>>>>>>>>>>>>>>>>>>>>>>>>>>
          //  CREATE ROW AND ADD DATA TO THE DATA CELLS======================
    HSSFRow data=shet1.createRow(pos);
  data.setHeightInPoints(25);
  HSSFCell cellx1,cellx2,cellx3,cellx4,cellx5,cellx6;
  cellx1=data.createCell(0);
  cellx2=data.createCell(1);
    cellx3=data.createCell(2);
//    cellx4=data.createCell(3);
//    cellx5=data.createCell(4);
//    cellxx6=rheading2.createCell(5);


 cellx1.setCellValue(countyname);
 cellx2.setCellValue(partnername);
 cellx3.setCellValue(achieved);
// cellx4.setCellValue(month);
// cellx5.setCellValue(achieved);

  
             cellx1.setCellStyle(stylex);
             cellx2.setCellStyle(stylex);
             cellx3.setCellStyle(stylex);
//             cellx4.setCellStyle(stylex);
//             cellx5.setCellStyle(stylex); 
   
  System.out.println("county : "+countyname+" partner : "+partnername+" achieved:"+achieved+" month: "+month+" quarter: "+quarter);
   
   
   pos++;
          
          
          
          
          
      }
     
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

   // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=PWP_COMPLETION_REPORT.xls");
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
        Logger.getLogger(completedSessions.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(completedSessions.class.getName()).log(Level.SEVERE, null, ex);
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

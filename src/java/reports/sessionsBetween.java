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
public class sessionsBetween extends HttpServlet {
String partner_name,year,period,partner_id,pop_name,pop_id,county_name;
HttpSession session;
String period2 [],county [],part2 [];
int max_lessons,completed,completed2,pos,total,all,merger;
String parts;
String  county_id="";
int pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8;
int pos11,pos22,pos33,pos44,pos55,pos66,pos77,pos88;
int merge_mids;
int ag1,ag2,ag3,ag4,ag5,ag6,ag7,ag8;
int ag11,ag22,ag33,ag44,ag55,ag66,ag77,ag88;
int ag_comp,ag_all;
String start_date="",end_date="";
int start_year=0,end_year=0,all_clients=0;
String group_id="",groupings="",session_no="";
String start_dateKey,end_dateKey="";
int att,cds;
 String   cm,rsp,tb,stis,partner,children,status="";
 String start,end;
 int cm1,rsp1,tb1,stis1,partner1,children1,status1,cds1;
  int cm2,rsp2,tb2,stis2,partner2,children2,status2,cds2;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
       session= request.getSession();
       
       
      String [] starter=request.getParameter("start_date").split("/");
   String [] ender=request.getParameter("end_date").split("/");
   
//   String [] starter="10/02/2010".split("/");
//   String [] ender="24/10/2014".split("/");
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
       
   start_dateKey=m1+"/"+d1+"/"+y1;
   end_dateKey=m2+"/"+d2+"/"+y2; 
   
//   System.out.println("");
   
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
    shet1.setColumnWidth(1, 4000); 
    shet1.setColumnWidth(2, 4000); 
    shet1.setColumnWidth(3, 4500);
    
    shet1.setColumnWidth(4, 4300); 
    
    shet1.setColumnWidth(5, 4300); 
    shet1.setColumnWidth(6, 4000);
    shet1.setColumnWidth(7, 4200); 
    shet1.setColumnWidth(8, 4200); 
    shet1.setColumnWidth(9, 4200); 
 
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
  cell.setCellValue("PWP number of individual provided with services from "+start+" to "+end);
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,9));
    

   HSSFRow rw4=shet1.createRow(2);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10;
  
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

   
 cell1 .setCellValue("County Name");
 cell2.setCellValue("Partner Name");
 cell3 .setCellValue("Received Contraceptives");
 cell4.setCellValue("Reffered To Service Point");
 cell5.setCellValue("Given Condoms");
 cell6.setCellValue("Screened For TB");
 cell7 .setCellValue("Screened For STIs");
 cell8.setCellValue("Partner Tested");
 cell9.setCellValue("Children Tested");
 cell10.setCellValue("Disclosed Status");

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
   
pos=3;


                  String ct_selector="SELECT * FROM county";
                  conn.rs3=conn.st3.executeQuery(ct_selector);
                  while(conn.rs3.next()){
                      county_name=conn.rs3.getString(2);
                      county_id=conn.rs3.getString(1);
                      String partner_name_selector="SELECT * FROM partner";
       conn.rs=conn.st.executeQuery(partner_name_selector);
       while(conn.rs.next()){
        partner_name=conn.rs.getString(2);
       partner_id=conn.rs.getString(1);
       cm2=rsp2=tb2=stis2=partner2=children2=status2=cds2=0;
                   all_clients=0;
//                   pos++;
                  String district_selector="SELECT district_id FROM district WHERE county_id='"+county_id+"'" ;    
                  conn.rs1=conn.st1.executeQuery(district_selector);
                  while(conn.rs1.next()){
                    String district_id=conn.rs1.getString(1);
                    
String getClients="SELECT client_id FROM personal_information WHERE partner_id='"+partner_id+"' && district_id='"+district_id+"'";
conn.rs4=conn.st4.executeQuery(getClients);
while(conn.rs4.next()){
 String client_id=conn.rs4.getString(1);
 cm1=rsp1=tb1=stis1=partner1=children1=status1=cds1=0;
 String getServices="SELECT * FROM services_provided WHERE client_id='"+client_id+"' && "
         + "STR_TO_DATE(submission_date,'%m/%d/%Y') BETWEEN STR_TO_DATE('"+start_dateKey+"','%m/%d/%Y') AND STR_TO_DATE('"+end_dateKey+"','%m/%d/%Y') ";   
   System.out.println(getServices);
 conn.rs2=conn.st2.executeQuery(getServices);
    while(conn.rs2.next()){
       cm=rsp=tb=stis=partner=children=status="" ;
       System.out.println("client id : "+client_id);
        cds=0;
       cm=conn.rs2.getString("contraceptive_method");
       rsp=conn.rs2.getString("rsp");
       tb=conn.rs2.getString("screened_tb");
       stis=conn.rs2.getString("screened_stis");
       partner=conn.rs2.getString("tested_partner");
       children=conn.rs2.getString("tested_children");
       status=conn.rs2.getString("disclosed_status");
       cds=conn.rs2.getInt("cds_given");
     
       if(cm.equals("YES")){
      cm1++;
    }if(rsp.equals("YES")){
      rsp1++;
}if(tb.equals("YES")){
      tb1++;
                  }if(stis.equals("YES")){
      stis1++;
       }if(partner.equals("YES")){
      partner1++;
                  }if(children.equals("YES")){
      children1++;
    }if(status.equals("YES")){
      status1++; 
}if(cds>0){
  cds1++;     
    }
    
        }
//    ADD TO THE RESPECTIVE SERVICES PROVIDED========================================
    if(cm1>0){
    cm2  ++;  
    }
    if(rsp1>0){
     rsp2 ++;  
    }
    if(tb1>0){
     tb2   ++;
    }
    if(stis1>0){
      stis2 ++;
    }
    if(partner1>0){
     partner2++;   
    }
    if(children1>0){
     children2 ++;   
    }
    if(status1>0){
    status2 ++;   
    }
    if(cds1>0){
     cds2   ++;
    }
    
    
      }
       
      }
    System.out.println("out=="+pos+"-----------"+cm2+"-"+rsp2+"-"+tb2+"-"+stis2+"-"+partner2+"-"+children2+"-"+status2+"-"+cds2);
    if(cm2>0 || rsp2>0 || tb2>0 || stis2>0 || partner2>0 || children2>0 || status2>0 || cds2>0){
    HSSFRow rw5=shet1.createRow(pos);
    rw5.setHeightInPoints(45);
    rw5.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell1x,cell2x,cell3x,cell4x,cell5x,cell6x,cell7x,cell8x,cell9x,cell10x;
 
   cell1x=rw5.createCell(0);
   cell2x=rw5.createCell(1);
   cell3x=rw5.createCell(2);
   cell4x=rw5.createCell(3);
   cell5x=rw5.createCell(4);
   cell6x=rw5.createCell(5);
   cell7x=rw5.createCell(6);
   cell8x=rw5.createCell(7);
   cell9x=rw5.createCell(8);
   cell10x=rw5.createCell(9);

   
 cell1x .setCellValue(county_name);
 cell2x.setCellValue(partner_name);
 cell3x .setCellValue(cm2);
 cell4x.setCellValue(rsp2);
 cell5x.setCellValue(cds2);
 cell6x.setCellValue(tb2);
 cell7x .setCellValue(stis2);
 cell8x.setCellValue(partner2);
 cell9x.setCellValue(children2);
 cell10x.setCellValue(status2);
 

cell1x.setCellStyle(stborder);
cell2x.setCellStyle(stborder);
cell3x.setCellStyle(stborder);
cell4x.setCellStyle(stborder);
cell5x.setCellStyle(stborder);
cell6x.setCellStyle(stborder);
cell7x.setCellStyle(stborder);
cell8x.setCellStyle(stborder);
cell9x.setCellStyle(stborder);
cell10x.setCellStyle(stborder);
   
pos++;
    }            
     }
//       end of partner
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_SERVICES_PROVIDED.xls");
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
        Logger.getLogger(sessionsBetween.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(sessionsBetween.class.getName()).log(Level.SEVERE, null, ex);
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

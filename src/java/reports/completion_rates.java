/*
 * To change this template, choose Tools | Templates
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
import java.util.Calendar;
/**
 *
 * @author Geofrey Nyabuto
 */
public class completion_rates extends HttpServlet {
String partner_name,year,period,partner_id,pop_name,pop_id,county_name;
HttpSession session;
String period2 [],county [],part2 [];
int max_lessons,completed,completed2,pos,total,all,merger;
String parts;
String  county_id="";
int pos1,pos2,pos3,pos4,pos5,pos6,pos7,pos8;
int pos11,pos22,pos33,pos44,pos55,pos66,pos77,pos88;
int merge_mids;

int ag_comp,ag_all;
String start_date="",end_date="";
int start_year=0,end_year=0,all_clients=0;
String group_id="",groupings="",session_no="";
String start_dateKey,end_dateKey="";
int att,cds;
int incomplete,ag_incomp=0,counter;
double comp_per,incomp_per,all_comp_per,all_incomp_per;
//    CURRENT DATE
 Calendar cal = Calendar.getInstance();
int year1=cal.get(Calendar.YEAR);
int month1=cal.get(Calendar.MONTH)+1;
int date1=cal.get(Calendar.DATE);
int hour1 = cal.get(Calendar.HOUR_OF_DAY);
int min1=cal.get(Calendar.MINUTE);
int sec1=cal.get(Calendar.SECOND);

String date_created="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         OutputStream out = response.getOutputStream();
//        try{
            dbConn conn = new dbConn();
    part2=request.getParameterValues("partner");
counter=ag_incomp=ag_all=ag_comp=0;
     county=request.getParameterValues("county");
     pos=4;
    merge_mids=5;
     start_date=request.getParameter("start_date");
     end_date=request.getParameter("end_date");
     date_created="_CREATED_ON_"+year1+"_"+month1+"_"+date1+"_"+hour1+"_"+min1+"_"+sec1;
     all_clients=0;
     String syear[]=start_date.split("/");
     start_year=Integer.parseInt(syear[2]);
     String eyear[]=end_date.split("/");
     end_year=Integer.parseInt(eyear[2]);
     
    start_dateKey=syear[2]+""+syear[1]+""+syear[0];
    end_dateKey=eyear[2]+""+eyear[1]+""+eyear[0];
    
     
    System.out.println("start date key  :  "+start_dateKey); 
    System.out.println("end date key  :  "+end_dateKey); 
     
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
    shet1.setColumnWidth(0, 3000); 
    shet1.setColumnWidth(1, 6000); 
    shet1.setColumnWidth(2, 6000); 
    shet1.setColumnWidth(3, 6000);
    shet1.setColumnWidth(4, 6000); 
     shet1.setColumnWidth(5, 6000); 
     
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
  cell.setCellValue("PWP PERCENTAGE COMPLETION RATE");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,5));

   HSSFRow rw4=shet1.createRow(3);
    rw4.setHeightInPoints(45);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell0,cell1,cell2,cell3,cell4,cell5;
   
   cell0=rw4.createCell(0);
   cell1=rw4.createCell(1);
   cell2=rw4.createCell(2);
   cell3=rw4.createCell(3);
   cell4=rw4.createCell(4);
   cell5=rw4.createCell(5);

 cell0 .setCellValue("No.");
 cell1 .setCellValue("Partner Name");
 cell2.setCellValue("County Name");
 cell3.setCellValue("Total No of Individuals");
 cell4.setCellValue("% Completed");
 cell5.setCellValue("% Not Completed");
 

 
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
cell0.setCellStyle(stylex);
cell1.setCellStyle(stylex);
cell2.setCellStyle(stylex);
cell3.setCellStyle(stylex);
cell4.setCellStyle(stylex);
cell5.setCellStyle(stylex);
    
     if(part2!=null){
     for( int q=0;q<part2.length;q++) {   
      if(part2[q]!=null && !part2[q].equals(""))  { 
    partner_id=part2[q];
//     System.out.println("size of partner id  : "+partner_id);
     String partner_name_selector="SELECT * FROM partner WHERE partner_id='"+partner_id+"'";
       conn.rs=conn.st.executeQuery(partner_name_selector);
       if(conn.rs.next()==true){
        partner_name=conn.rs.getString(2);
       }
       completed2=0;
merger=0;
              if(county!=null){
                  for (int i=0;i<county.length;i++){
                       completed=completed2=total=all=0;
                 incomplete=0;
                   if(county[i]!=null && !county[i].equals("")){
                        county_id=county[i];
                        counter++;
//                         System.out.println("size of county id  : "+county_id);
                   String ct_selector="SELECT * FROM county WHERE county_id='"+county_id+"'";
                   conn.rs3=conn.st3.executeQuery(ct_selector);
                   while(conn.rs3.next()){county_name=conn.rs3.getString(2);
                   all_clients=0;
                  String district_selector="SELECT district_id FROM district WHERE county_id='"+county_id+"'" ;    
                  conn.rs1=conn.st1.executeQuery(district_selector);
                  while(conn.rs1.next()){
                    String district_id=conn.rs1.getString(1); 
                    for(int f=start_year;f<=end_year;f++){
                      all_clients=0; 
//                      ALL PARTICIPANTS........................................................
//                      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                      
//    String get_all_clients="SELECT COUNT(clients.client_id) FROM clients WHERE district_id='"+district_id+"' && "
//            + "partner_id='"+partner_id+"' && year='"+f+"'";
//    conn.rs=conn.st.executeQuery(get_all_clients);
//    if(conn.rs.next()==true){
//      total+=conn.rs.getInt(1);
//      ag_all+=conn.rs.getInt(1);
//    }
                  
String get_numbers="SELECT register2.client_id, SUM(register2.value) FROM  clients JOIN register2 ON clients.client_id=register2.client_id "
        + " WHERE clients.district_id='"+district_id+"' && clients.partner_id='"+partner_id+"' && register2.year='"+f+"' &&"
        +" register2.value=1 && register2.datekey BETWEEN "+start_dateKey+" AND "+end_dateKey+" GROUP BY clients.client_id";
  conn.rs=conn.st.executeQuery(get_numbers);
   while(conn.rs.next()){
       att=0;
   att=conn.rs.getInt(2);
//       total+=att;
//      ag_all+=att;
   if(att==13){completed++;ag_comp++;}
  if(att<13){incomplete++;ag_incomp++;} 
   System.out.println("attended sessions  :  "+att);
}

//   }  
   
  }
                  
                  }
                   
                   }
         total=completed+incomplete;
              merger++;
              merge_mids++;
              if(total>0){
              comp_per=(completed*100)/total;
              incomp_per=(incomplete*100)/total;
              }
              System.out.println("completed  : "+completed+" INCOMPLETE  :  "+incomplete);
             HSSFRow rwx= shet1.createRow(pos);
             rwx.setHeightInPoints(20);
             HSSFCell cellx0,cellx1,cellx2,cellx3, cellx4, cellx5;
             cellx0=rwx.createCell(0);
             cellx1=rwx.createCell(1);
             cellx2=rwx.createCell(2);
             cellx3=rwx.createCell(3);
             cellx4=rwx.createCell(4);
             cellx5=rwx.createCell(5);
             
             cellx0.setCellValue(counter);
             cellx1.setCellValue(partner_name);
             cellx2.setCellValue(county_name);
             if(total>0){cellx3.setCellValue(total);}
             if(total==0){cellx3.setCellValue("");}
             if(completed>0){cellx4.setCellValue(comp_per+"%");}
             if(completed==0){cellx4.setCellValue("");}
             if(incomplete>0){cellx5.setCellValue(incomp_per+"%");}
             if(incomplete==0){cellx5.setCellValue("");}

//             ADD BORDERS
             cellx0.setCellStyle(stborder);
             cellx1.setCellStyle(stborder);
             cellx2.setCellStyle(stborder);
             cellx3.setCellStyle(stborder);
             cellx4.setCellStyle(stborder);
             cellx5.setCellStyle(stborder);
            
             
             
              pos++;
              completed=incomplete=total=0;
                   } }
              }
      shet1.addMergedRegion(new CellRangeAddress(pos-merger,pos-1,1,1));
      }
      
//       }
      }
     
     }
     ag_all=ag_comp+ag_incomp;
     if(ag_all>0){
all_comp_per=(ag_comp*100)/ag_all;
all_incomp_per=(ag_incomp*100)/ag_all;
     }
     System.out.println("agg comp   :  "+ag_comp+" agg incomplete  :  "+ag_incomp);
      int current_pos=merge_mids-1; 
       HSSFRow tots= shet1.createRow(current_pos);
       tots.setHeightInPoints(25);
           HSSFCell celle0,celle1,celle2,celle3,celle4,celle5;
   
   celle0=tots.createCell(0);
   celle1=tots.createCell(1);
   celle2=tots.createCell(2);
   celle3=tots.createCell(3);
   celle4=tots.createCell(4);
   celle5=tots.createCell(5);
  
   
 celle0 .setCellValue("TOTALS : ");
 celle2.setCellValue("");
 
 celle3.setCellValue(ag_all);
 if(ag_comp>0){celle4.setCellValue(all_comp_per+"%");}
 if(ag_comp==0){celle4.setCellValue("0%");}
 if(ag_incomp>0){celle5.setCellValue(all_incomp_per+"%");}
 if(ag_incomp==0){celle5.setCellValue("0%");}
// celle5.setCellValue(ag_incomp);
 
 HSSFCellStyle stylex1 = wb.createCellStyle();
stylex1.setFillForegroundColor(HSSFColor.LAVENDER.index);
stylex1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex1.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex1.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
 celle0.setCellStyle(stylex1);
 celle1.setCellStyle(stylex1);
celle2.setCellStyle(stylex1);
celle3.setCellStyle(stylex1);
celle4.setCellStyle(stylex1);
celle5.setCellStyle(stylex1);

       shet1.addMergedRegion(new CellRangeAddress(current_pos,current_pos,0,2));
//        }
       
       
//     CLOSE ALL CONNECTIONS...............................................................................
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_percentage_Completion_Rate"+date_created.trim()+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(completion_rates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(completion_rates.class.getName()).log(Level.SEVERE, null, ex);
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

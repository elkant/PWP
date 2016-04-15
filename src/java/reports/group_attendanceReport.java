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
import java.util.Calendar;
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
public class group_attendanceReport extends HttpServlet {

HttpSession session;
String year,district,partner,group;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
String fname,mname,lname,age,gender;
String  district_name,partner_name,group_name;
int counter=0,pos=0;
int ss1,ss2,ss3,ss4,ss5,ss6,ss7,ss8,ss9,ss10,ss11,ss12,ss13;



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
       session=request.getSession();
       dbConn conn = new dbConn();
       
       ss1=ss2=ss3=ss4=ss5=ss6=ss7=ss8=ss9=ss10=ss11=ss12=ss13=0;
       date_created="_CREATED_ON_"+year1+"_"+month1+"_"+date1+"_"+hour1+"_"+min1+"_"+sec1;
       year=request.getParameter("year");
       district=request.getParameter("district");
       partner=request.getParameter("partner");
       group=request.getParameter("grp");
       
       String get_partner="SELECT partner_name FROM partner WHERE partner_id='"+partner+"'";
       conn.rs=conn.st.executeQuery(get_partner);
       while(conn.rs.next()){
           partner_name=conn.rs.getString(1);
       }
       
       String get_district="SELECT district_name FROM district WHERE district_id='"+district+"'";
       conn.rs=conn.st.executeQuery(get_district);
       while(conn.rs.next()){
           district_name=conn.rs.getString(1);
       }
       
       String get_group="SELECT group_name FROM groups WHERE group_id='"+group+"'";
       conn.rs=conn.st.executeQuery(get_group);
       while(conn.rs.next()){
           group_name=conn.rs.getString(1);
       }
       System.out.println("dist:"+district_name+"partner:"+partner_name+"group:"+group_name);
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
    shet1.setColumnWidth(1, 2500); 
    shet1.setColumnWidth(2, 2500); 
    shet1.setColumnWidth(3, 2500);
    
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
    shet1.setColumnWidth(14, 2000); 
    shet1.setColumnWidth(15, 2000); 
    shet1.setColumnWidth(16, 2000); 
    shet1.setColumnWidth(17, 2000);
    shet1.setColumnWidth(18, 2000); 
    shet1.setColumnWidth(19, 2000); 
//    shet1.setColumnWidth(20, 2000); 
//    shet1.setColumnWidth(21, 2300);
//    shet1.setColumnWidth(20, 2000);
    HSSFCellStyle styleBorder=wb.createCellStyle();
    styleBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleBorder.setFillForegroundColor(HSSFColor.ORANGE.index);
    styleBorder.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
    
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

  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  cell=rw1.createCell(0);
  cell.setCellValue("PWP GROUP ATTENDANCE REPORT");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
   
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,19));

  //  CREATE HEADING 2
  HSSFRow rheading2=shet1.createRow(2);
  rheading2.setHeightInPoints(25);
  

  HSSFCell cell01=rheading2.createCell(0);
  HSSFCell cell02=rheading2.createCell(2);
  
  HSSFCell cell03=rheading2.createCell(5);
  HSSFCell cell04=rheading2.createCell(7);
  
  HSSFCell cell05=rheading2.createCell(10);
  HSSFCell cell06=rheading2.createCell(12);
  
  HSSFCell cell07=rheading2.createCell(15);
  HSSFCell cell08=rheading2.createCell(17);
  
  cell01.setCellValue("District Name");
  cell02.setCellValue(district_name);
  
  cell03.setCellValue("Partner Name");
  cell04.setCellValue(partner_name);
  
  cell05.setCellValue("Group Name");
  cell06.setCellValue(group_name);
  
  cell07.setCellValue("Year");
  cell08.setCellValue(year);
  
  HSSFCell cell09=rheading2.createCell(1);
  HSSFCell cell010=rheading2.createCell(3);
  HSSFCell cell011x=rheading2.createCell(4);
  HSSFCell cell012=rheading2.createCell(6);
  HSSFCell cell013=rheading2.createCell(8);
  HSSFCell cell014=rheading2.createCell(9);
  HSSFCell cell015=rheading2.createCell(11);
  HSSFCell cell016=rheading2.createCell(13);
  HSSFCell cell017=rheading2.createCell(14);
  HSSFCell cell018=rheading2.createCell(16);
  HSSFCell cell019=rheading2.createCell(18);
  
  
  cell01.setCellStyle(styleBorder);
  cell02.setCellStyle(styleBorder);
  cell03.setCellStyle(styleBorder);
  cell04.setCellStyle(styleBorder);
  cell05.setCellStyle(styleBorder);
  cell06.setCellStyle(styleBorder);
  cell07.setCellStyle(styleBorder);
  cell08.setCellStyle(styleBorder);
  cell011x.setCellStyle(styleBorder);
  cell012.setCellStyle(styleBorder);
  cell013.setCellStyle(styleBorder);
  cell014.setCellStyle(styleBorder);
  cell015.setCellStyle(styleBorder);
  cell016.setCellStyle(styleBorder);
  cell017.setCellStyle(styleBorder);
  cell018.setCellStyle(styleBorder);
  cell010.setCellStyle(styleBorder);
  cell019.setCellStyle(styleBorder);
  cell09.setCellStyle(styleBorder);

  shet1.addMergedRegion( new CellRangeAddress(2,2,0,1));
  shet1.addMergedRegion( new CellRangeAddress(2,2,2,4));
  shet1.addMergedRegion( new CellRangeAddress(2,2,5,6));
  shet1.addMergedRegion( new CellRangeAddress(2,2,7,9));
  shet1.addMergedRegion( new CellRangeAddress(2,2,10,11));
  shet1.addMergedRegion( new CellRangeAddress(2,2,12,14));
  shet1.addMergedRegion( new CellRangeAddress(2,2,15,16));
  shet1.addMergedRegion( new CellRangeAddress(2,2,17,18));
  
//MESSAGE HEADING..............................  
  
    HSSFRow rheading3=shet1.createRow(3);
  rheading3.setHeightInPoints(63);
  
  HSSFCell cell011=rheading3.createCell(0);
  HSSFCell cell021=rheading3.createCell(1);
  HSSFCell cell031=rheading3.createCell(2);
  HSSFCell cell041=rheading3.createCell(3);
  HSSFCell cell051=rheading3.createCell(4);
  HSSFCell cell061=rheading3.createCell(5);
  
  cell011.setCellValue("No.");
  cell021.setCellValue("First Name");
  cell031.setCellValue("Middle Name");
  cell041.setCellValue("Last Name");
  cell051.setCellValue("Age");
  cell061.setCellValue("Gender");
  
  cell011.setCellStyle(stylex);
  cell021.setCellStyle(stylex);
  cell031.setCellStyle(stylex);
  cell041.setCellStyle(stylex);
  cell051.setCellStyle(stylex);
  cell061.setCellStyle(stylex);
  int mess_no=0;
  String message="";
  String get_messages="SELECT * FROM message_codes";
  conn.rs=conn.st.executeQuery(get_messages);
  while(conn.rs.next()){
      mess_no=conn.rs.getInt(1)+5;
      message=conn.rs.getString(2);
  HSSFCell cell071=rheading3.createCell(mess_no);
cell071.setCellValue(message);
cell071.setCellStyle(stylex);

  }
  pos=5;
//       get group details and send them to excel.
           String get_members="SELECT clients.fname,clients.mname,clients.lname,clients.age,clients.gender,"
                   + "register.s1,register.s2,register.s3,register.s4,register.s5,register.s6,register.s7,register.s8,register.s9,"
                   + "register.s10,register.s11,register.s12,register.s13 FROM clients JOIN register ON clients.client_id=register.client_id "
                   + "WHERE clients.group_id='"+group+"'&& clients.year='"+year+"' ORDER BY clients.client_id";
           conn.rs=conn.st.executeQuery(get_members);
           counter=0;
            while(conn.rs.next()){
                counter++;
                pos=counter+3;
            fname=conn.rs.getString(1);
            mname=conn.rs.getString(2);
            lname=conn.rs.getString(3);
            age=conn.rs.getString(4);
            gender=conn.rs.getString(5);
            if(mname.equals(lname)){mname="";}
//             ATTENDANCE DATA.............................................................................................
            s1=conn.rs.getString(6);
            s2=conn.rs.getString(7);
            s3=conn.rs.getString(8);
            s4=conn.rs.getString(9);
            s5=conn.rs.getString(10);
            s6=conn.rs.getString(11);
            s7=conn.rs.getString(12);
            s8=conn.rs.getString(13);
            s9=conn.rs.getString(14);
            s10=conn.rs.getString(15);
            s11=conn.rs.getString(16);
            s12=conn.rs.getString(17);
            s13=conn.rs.getString(18);
            
           if(s1.equals("2")){s1="0";}if(s1.equals("5")){s1="";} if(s1.equals("1")){ss1++;}   
           if(s2.equals("2")){s2="0";}if(s2.equals("5")){s2="";} if(s2.equals("1")){ss2++;} 
           if(s3.equals("2")){s3="0";}if(s3.equals("5")){s3="";} if(s3.equals("1")){ss3++;} 
           if(s4.equals("2")){s4="0";}if(s4.equals("5")){s4="";} if(s4.equals("1")){ss4++;} 
           if(s5.equals("2")){s5="0";}if(s5.equals("5")){s5="";} if(s5.equals("1")){ss5++;} 
           if(s6.equals("2")){s6="0";}if(s6.equals("5")){s6="";} if(s6.equals("1")){ss6++;} 
           if(s7.equals("2")){s7="0";}if(s7.equals("5")){s7="";} if(s7.equals("1")){ss7++;} 
           if(s8.equals("2")){s8="0";}if(s8.equals("5")){s8="";} if(s8.equals("1")){ss8++;} 
           if(s9.equals("2")){s9="0";}if(s9.equals("5")){s9="";} if(s9.equals("1")){ss9++;} 
           if(s10.equals("2")){s10="0";}if(s10.equals("5")){s10="";} if(s10.equals("1")){ss10++;} 
           if(s11.equals("2")){s11="0";}if(s11.equals("5")){s11="";} if(s11.equals("1")){ss11++;} 
           if(s12.equals("2")){s12="0";}if(s12.equals("5")){s12="";} if(s12.equals("1")){ss12++;} 
           if(s13.equals("2")){s13="0";}if(s13.equals("5")){s13="";} if(s13.equals("1")){ss13++;} 
           
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
             cellx1.setCellValue(counter);
             cellx2.setCellValue(fname);
             cellx3.setCellValue(mname);
             cellx4.setCellValue(lname);
             cellx5.setCellValue(age);
             cellx6.setCellValue(gender);
             cellx7.setCellValue(s1);
             cellx8.setCellValue(s2);
             cellx9.setCellValue(s3);
             cellx10.setCellValue(s4);
             cellx11.setCellValue(s5);
             cellx12.setCellValue(s6);
             cellx13.setCellValue(s7);
             cellx14.setCellValue(s8);
             cellx15.setCellValue(s9);
             cellx16.setCellValue(s10);
             cellx17.setCellValue(s11);
             cellx18.setCellValue(s12);
             cellx19.setCellValue(s13);
           
           //             ADD BORDERS
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
//             cellx20.setCellStyle(stborder);
//             cellx21.setCellStyle(stborder);
//             cellx22.setCellStyle(stborder);
           
           s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="";
           fname=mname=lname=age=gender="";
           }
//           OUT PUT THE COMPLETED TOTALS TO THE EXCEL FILE/SHEET
          counter++;  
           pos++; 
                   HSSFRow tots= shet1.createRow(pos);
       tots.setHeightInPoints(25);
           HSSFCell celle1,celle2,celle3,celle4,celle5,celle6,celle7,celle8,celle9,celle10,celle11,celle12,celle13,celle14,celle15,celle16,celle17,celle18,celle19,celle20;
  
       
   celle1=tots.createCell(0);
   celle2=tots.createCell(1);
   celle3=tots.createCell(2);
   celle4=tots.createCell(3);
   celle5=tots.createCell(4);
   celle6=tots.createCell(5);
   celle7=tots.createCell(6);
   celle8=tots.createCell(7);
   celle9=tots.createCell(8);
   celle10=tots.createCell(9);
   celle11=tots.createCell(10);
   celle12=tots.createCell(11);
   celle13=tots.createCell(12);
   celle14=tots.createCell(13);
   celle15=tots.createCell(14);
   celle16=tots.createCell(15);
   celle17=tots.createCell(16);
   celle18=tots.createCell(17);
   celle19=tots.createCell(18);
   celle20=tots.createCell(19);
   
 celle1 .setCellValue("TOTALS : ");
 celle2.setCellValue("");
 celle3.setCellValue("");
 celle4.setCellValue("");
 celle5.setCellValue("");
 celle6 .setCellValue("");
 celle7.setCellValue(ss1);
 celle8.setCellValue(ss2);
 celle9.setCellValue(ss3);
 celle10 .setCellValue(ss4);
 celle11.setCellValue(ss5);
 celle12.setCellValue(ss6);
 celle13.setCellValue(ss7);
 celle14.setCellValue(ss8);
 celle15 .setCellValue(ss9);
 celle16.setCellValue(ss10);
 celle17.setCellValue(ss11);
 celle18.setCellValue(ss12);
 celle19 .setCellValue(ss13);
 
 shet1.addMergedRegion(new CellRangeAddress(pos,pos,0,5));
 
 HSSFCellStyle stylex1 = wb.createCellStyle();
stylex1.setFillForegroundColor(HSSFColor.LAVENDER.index);
stylex1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex1.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex1.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
 
 celle1.setCellStyle(stylex1);
celle2.setCellStyle(stylex1);
celle3.setCellStyle(stylex1);
celle4.setCellStyle(stylex1);
celle5.setCellStyle(stylex1);
celle6.setCellStyle(stylex1);
celle7.setCellStyle(stylex1);
celle8.setCellStyle(stylex1);
celle9.setCellStyle(stylex1);
celle10.setCellStyle(stylex1);
celle11.setCellStyle(stylex1);
celle12.setCellStyle(stylex1);
celle13.setCellStyle(stylex1);
celle14.setCellStyle(stylex1);
celle15.setCellStyle(stylex1);
celle16.setCellStyle(stylex1);
celle17.setCellStyle(stylex1);
celle18.setCellStyle(stylex1);
celle19.setCellStyle(stylex1);


            
            
            
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_Attendance_per_group"+date_created.trim()+".xls");
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
            Logger.getLogger(group_attendanceReport.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(group_attendanceReport.class.getName()).log(Level.SEVERE, null, ex);
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

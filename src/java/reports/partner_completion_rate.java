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
public class partner_completion_rate extends HttpServlet {
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
    shet1.setColumnWidth(0, 2000); 
    shet1.setColumnWidth(1, 2000); 
    shet1.setColumnWidth(2, 3000); 
    shet1.setColumnWidth(3, 2500);
    
    shet1.setColumnWidth(4, 300); 
    
    shet1.setColumnWidth(5, 3300); 
    shet1.setColumnWidth(6, 3000);
    shet1.setColumnWidth(7, 2200); 
    shet1.setColumnWidth(8, 2200); 
    shet1.setColumnWidth(9, 2200); 
    shet1.setColumnWidth(10, 1800); 
    shet1.setColumnWidth(11, 2000);  
    shet1.setColumnWidth(12, 2300); 
    
    shet1.setColumnWidth(13, 300); 
    
    shet1.setColumnWidth(14, 3300); 
    shet1.setColumnWidth(15, 3000); 
    shet1.setColumnWidth(16, 2200); 
    shet1.setColumnWidth(17, 2200);
    shet1.setColumnWidth(18, 2200); 
    shet1.setColumnWidth(19, 1800); 
    shet1.setColumnWidth(20, 2000); 
    shet1.setColumnWidth(21, 2300);
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
  cell.setCellValue("PWP COMPLETION RATE");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,0,19));
    
//  CREATE HEADING 2
  HSSFRow rheading2=shet1.createRow(2);
  rheading2.setHeightInPoints(25);
  
  for (int f=0;f<22;f++){
  HSSFCell cell01=rheading2.createCell(f);
  
  if(f>=5 && f<13){
    cell01.setCellValue("# OF INDIVIDUALS WHO COMPLETED ALL SESSION AND RECEIVED SERVICES");  
  }
  if(f>=14 && f<22){
    cell01.setCellValue("# OF INDIVIDUALS WHO DID NOT COMPLETE ALL SESSION BUT RECEIVED SERVICES");  
  }
  
  cell01.setCellStyle(styleBorder);
  }
  shet1.addMergedRegion( new CellRangeAddress(2,2,5,12));
   shet1.addMergedRegion( new CellRangeAddress(2,2,14,21));
  shet1.addMergedRegion( new CellRangeAddress(2,2,0,3));
  
   HSSFRow rw4=shet1.createRow(3);
    rw4.setHeightInPoints(45);
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
   
 cell1 .setCellValue("Partner Name");
 cell2.setCellValue("County Name");
 cell3.setCellValue("Total No of Individuals");
 cell4.setCellValue("# Completed Session");
 
  cell5.setCellValue("");
  
 cell6 .setCellValue("Received Contraceptives");
 cell7.setCellValue("Reffered To Service Point");
 cell8.setCellValue("Given Condoms");
 cell9.setCellValue("Screened For TB");
 cell10 .setCellValue("Screened For STIs");
 cell11.setCellValue("Partner Tested");
 cell12.setCellValue("Children Tested");
 cell13.setCellValue("Disclosed Status");
 
  cell14.setCellValue("");
  
 cell15 .setCellValue("Received Contraceptives");
 cell16.setCellValue("Reffered To Service Point");
 cell17.setCellValue("Given Condoms");
 cell18.setCellValue("Screened For TB");
 cell19 .setCellValue("Screened For STIs");
 cell20.setCellValue("Partner Tested");
 cell21.setCellValue("Children Tested");
 cell22.setCellValue("Disclosed Status");
 


 
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
cell20.setCellStyle(stylex);
cell21.setCellStyle(stylex);
cell22.setCellStyle(stylex);

ag1=ag2=ag3=ag4=ag5=ag6=ag7=ag8=ag_comp=ag_all=ag11=ag22=ag33=ag44=ag55=ag66=ag77=ag88=0;     


     
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
                    pos1=pos2=pos3=pos4=pos5=pos6=pos7=pos8=0;
                    pos11=pos22=pos33=pos44=pos55=pos66=pos77=pos88=0;  
                       completed=completed2=total=all=0;
                 
                   if(county[i]!=null && !county[i].equals("")){
                        county_id=county[i];
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
                      
    String get_all_clients="SELECT COUNT(clients.client_id) FROM clients WHERE district_id='"+district_id+"' && "
            + "partner_id='"+partner_id+"' && year='"+f+"'";
    conn.rs=conn.st.executeQuery(get_all_clients);
    if(conn.rs.next()==true){
      total+=conn.rs.getInt(1);
      ag_all+=conn.rs.getInt(1);
    }
                  
String get_numbers="SELECT register2.client_id, SUM(register2.value),services_provided.contraceptive_method,services_provided.rsp,"
        + "services_provided.cds_given,services_provided.screened_tb,services_provided.screened_stis,services_provided.tested_partner,"
        + "services_provided.tested_children,services_provided.disclosed_status FROM  clients JOIN register2 ON clients.client_id=register2.client_id JOIN "
        + " services_provided ON services_provided.client_id=clients.client_id"
        + " WHERE clients.district_id='"+district_id+"' && clients.partner_id='"+partner_id+"' && register2.year='"+f+"' &&"
        +" register2.value=1 && register2.datekey BETWEEN "+start_dateKey+" AND "+end_dateKey+" GROUP BY clients.client_id";
  conn.rs=conn.st.executeQuery(get_numbers);
   while(conn.rs.next()){
//       if(conn.rs.getString(3)!=null){
       att=cds=0;
       cm=rsp=tb=stis=partner=children=status="";
   att=conn.rs.getInt(2);
   cm=conn.rs.getString(3);
   rsp=conn.rs.getString(4);
   cds=conn.rs.getInt(5);
   tb=conn.rs.getString(6);
   stis=conn.rs.getString(7);
   partner=conn.rs.getString(8);
   children=conn.rs.getString(9);
   status=conn.rs.getString(10);
  System.out.println(conn.rs.getString(1) +"    att"+att+" cm :"+cm+" rsp: "+rsp+" cds:"+cds+"tb: "+stis+" part:"+partner+" child :"+children+" stat: "+status); 
  if(att==13){completed++;ag_comp++;}
  if(att==13 && cm.equals("YES")){pos1++;ag1++; }
  if(att==13 && rsp.equals("YES")){pos2++;ag2++; }
  if(att==13 && cds>0){pos3++;ag3++; }
  if(att==13 && tb.equals("YES")){pos4++;ag4++; }
  if(att==13 && stis.equals("YES")){pos5++;ag5++; }
  if(att==13 && partner.equals("YES")){pos6++;ag6++; }
  if(att==13 && children.equals("YES")){pos7++;ag7++; }
  if(att==13 && status.equals("YES")){pos8++;ag8++; }
  
  if(att<13 && cm.equals("YES")){pos11++;ag11++; }
  if(att<13 && rsp.equals("YES")){pos22++;ag22++; }
  if(att<13 && cds>0){pos33++;ag33++; }
  if(att<13 && tb.equals("YES")){pos44++;ag44++; }
  if(att<13 && stis.equals("YES")){pos55++;ag55++; }
  if(att<13 && partner.equals("YES")){pos66++;ag66++; }
  if(att<13 && children.equals("YES")){pos77++;ag77++; }
  if(att<13 && status.equals("YES")){pos88++;ag88++; }
   System.out.println("here  : "+all_clients+" dist : "+district_id);  
}

//   }  
   
  }
                  
                  }
                   
                   }
         
              merger++;
              merge_mids++;
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
             cellx20=rwx.createCell(19);
             cellx21=rwx.createCell(20);
             cellx22=rwx.createCell(21);
              
             cellx1.setCellValue(partner_name);
             cellx2.setCellValue(county_name);
             if(total>0){cellx3.setCellValue(total);}
             if(total==0){cellx3.setCellValue("");}
             if(completed>0){cellx4.setCellValue(completed);}
             if(completed==0){cellx4.setCellValue("");}
              
              if(pos1>0){cellx6.setCellValue(pos1);}
              if(pos1==0){cellx6.setCellValue("");}
              if(pos2>0){ cellx7.setCellValue(pos2);}
              if(pos2==0){ cellx7.setCellValue("");}
              if(pos3>0){cellx8.setCellValue(pos3);}
              if(pos3==0){cellx8.setCellValue("");}
              if(pos4>0){cellx9.setCellValue(pos4);}
              if(pos4==0){cellx9.setCellValue("");}
              if(pos5>0){cellx10.setCellValue(pos5);}
              if(pos5==0){cellx10.setCellValue("");}
              if(pos6>0){ cellx11.setCellValue(pos6);}
              if(pos6==0){ cellx11.setCellValue("");}
              if(pos7>0){cellx12.setCellValue(pos7);}
              if(pos7==0){cellx12.setCellValue("");}
              if(pos8>0){cellx13.setCellValue(pos8);}
              if(pos8==0){cellx13.setCellValue("");}
             
             if(pos11>0){cellx15.setCellValue(pos11);}
             if(pos11==0){cellx15.setCellValue("");}
             if(pos22>0){ cellx16.setCellValue(pos22);}
             if(pos22==0){ cellx16.setCellValue("");}
             if(pos33>0){cellx17.setCellValue(pos33);}
             if(pos33==0){cellx17.setCellValue("");}
             if(pos44>0){cellx18.setCellValue(pos44);}
             if(pos44==0){cellx18.setCellValue("");}
             if(pos55>0){cellx19.setCellValue(pos55);}
             if(pos55==0){cellx19.setCellValue("");}
             if(pos66>0){ cellx20.setCellValue(pos66);}
             if(pos66==0){ cellx20.setCellValue("");}
             if(pos77>0){cellx21.setCellValue(pos77);}
             if(pos77==0){cellx21.setCellValue("");}
             if(pos88>0){cellx22.setCellValue(pos88);}
             if(pos88==0){cellx22.setCellValue("");}

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
             cellx20.setCellStyle(stborder);
             cellx21.setCellStyle(stborder);
             cellx22.setCellStyle(stborder);
             
             
              pos++;
              completed=completed2=0;
                   } }
              }
      shet1.addMergedRegion(new CellRangeAddress(pos-merger,pos-1,0,0));
      }
      
//       }
      }
     
     } 

      
      int current_pos=merge_mids-1; 
       HSSFRow tots= shet1.createRow(current_pos);
       tots.setHeightInPoints(25);
           HSSFCell celle1,celle2,celle3,celle4,celle5,celle6,celle7,celle8,celle9,celle10,celle11,celle12,celle13,celle14,celle15,celle16,celle17,celle18,celle19,celle20;
    HSSFCell celle21,celle22;
       
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
   celle21=tots.createCell(20);
   celle22=tots.createCell(21);
   
 celle1 .setCellValue("TOTALS : ");
 celle2.setCellValue("");
 celle3.setCellValue(ag_all);
 celle4.setCellValue(ag_comp);
 
  celle5.setCellValue("");
  
 celle6 .setCellValue(ag1);
 celle7.setCellValue(ag2);
 celle8.setCellValue(ag3);
 celle9.setCellValue(ag4);
 celle10 .setCellValue(ag5);
 celle11.setCellValue(ag6);
 celle12.setCellValue(ag7);
 celle13.setCellValue(ag8);
 
  celle14.setCellValue("");
  
 celle15 .setCellValue(ag11);
 celle16.setCellValue(ag22);
 celle17.setCellValue(ag33);
 celle18.setCellValue(ag44);
 celle19 .setCellValue(ag55);
 celle20.setCellValue(ag66);
 celle21.setCellValue(ag77);
 celle22.setCellValue(ag88); 
 
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
celle20.setCellStyle(stylex1);
celle21.setCellStyle(stylex1);
celle22.setCellStyle(stylex1);
       shet1.addMergedRegion(new CellRangeAddress(4,merge_mids,4,4));
       shet1.addMergedRegion(new CellRangeAddress(4,merge_mids,13,13));

       shet1.addMergedRegion(new CellRangeAddress(current_pos,current_pos,0,1));
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
response.setHeader("Content-Disposition", "attachment; filename=PWP_Completion_Rate"+date_created.trim()+".xls");
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
            Logger.getLogger(partner_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(partner_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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

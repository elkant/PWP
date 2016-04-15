/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pwp.IdGenerator;
import pwp.dbConn;
import tracker.autoBackUp;

/**
 *
 * @author Geofrey Nyabuto
 */
public class ManualBackUp extends HttpServlet {
HttpSession session;
String today,path,ExistingPath,filePath,lastBackUp;
int foundClients,foundRegister;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        
           //CREATE A PATH IN THE COMPUTER FOR STORING TEXT FILES
           IdGenerator IG = new IdGenerator();
           today=IG.toDay();
           foundClients=foundRegister=0;
    String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
        //dbconnpath=mydrive+":\\MNHC_SYSTEM_APHIA_PLUS\\"; 
  path=mydrive+":\\APHIAPLUS\\PWPDBCONNECTION\\DO_NOT_DELETE\\_\\_\\."; 
  ExistingPath=mydrive+":\\APHIAPLUS\\PWPDBCONNECTION\\DO_NOT_DELETE\\_\\_\\LastBackUp.txt";     
           File f = new File(ExistingPath);
           if(f.isFile() && !f.isDirectory()){
           System.out.println("The file exist");
           
          
            String fpath = mydrive+ ":/APHIAPLUS/PWPDBCONNECTION/DO_NOT_DELETE/_/_/./LastBackUp.txt";
  
            FileInputStream fstream = new FileInputStream(fpath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stLine;
            //Read File Line By Line
            int count = 0;
            if((stLine = br.readLine()) != null) {
            lastBackUp=stLine;
            System.out.println("last date is : "+lastBackUp);
//            Check if date has gone past 1 day and data exist for back up...............
          if(!today.equals(lastBackUp)){
//             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkClients="SELECT COUNT(client_id) FROM personal_information WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + "BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d') ";
             System.out.println(checkClients);
              conn.rs=conn.st.executeQuery(checkClients);
              if(conn.rs.next()==true){
                  foundClients=conn.rs.getInt(1);
              }
             
            if(foundClients==0){
                //             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkRegister="SELECT COUNT(reg_id) FROM register2 WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + " BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d')";
              System.out.println(checkRegister);
              conn.rs=conn.st.executeQuery(checkRegister);
              if(conn.rs.next()==true){
                  foundRegister=conn.rs.getInt(1);
              }
            } 
          System.out.println("found clients :   "+foundClients+"     found register   :  "+foundRegister);
          if(foundClients>0 || foundRegister>0){
              System.out.println("===================YOUR DATA WILL BE BACKED UP==================");
    autoBackUp backup = new autoBackUp();
    backup.CreateBackUp(); 
    
//    UPDATE LAST BACK UP DATE------------------------------------
   filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out = new PrintWriter(outFile);

out.print(today);
out.close();
 } catch (IOException e) {} 
    
          }  
          else{
              System.out.println("============no data to back up======================");
         
          }  
          }
          else{
              System.out.println("today's back up has already been created==============");
          }
            
            }
            in.close(); 
           }
           
           else{
               System.out.println("File not found");
              new File(path).mkdirs();
             filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out = new PrintWriter(outFile);

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

out.print(today);
out.close();

   } catch (IOException e) {}
             
   autoBackUp backup = new autoBackUp();
    backup.CreateBackUp();          
    }

        
//

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
        Logger.getLogger(ManualBackUp.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(ManualBackUp.class.getName()).log(Level.SEVERE, null, ex);
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

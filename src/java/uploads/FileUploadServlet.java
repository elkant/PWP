/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uploads;
 
import java.io.File;
import java.io.IOException;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import pwp.IdGenerator;
import pwp.dbConn;
import pwp.dbConn2;
 

@MultipartConfig(fileSizeThreshold=1024*1024*20, 	// 20 MB 
                 maxFileSize=1024*1024*50,      	// 50 MB
                 maxRequestSize=1024*1024*100)   	// 100 MB

public class FileUploadServlet extends HttpServlet{
 String full_path="";
 String fileName="";
 String created_db="";
 String master_db, temp_db,found_folder,path;
 String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
 HttpSession session;
 String dbname="",dbuser="",dbpassword="";
  private static final long serialVersionUID = 205242440643911308L;
	 // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   String DB_URL = "jdbc:mysql://localhost:3306/";

   //  Database credentials
   static final String USER = "root";
   String PASS = "";
   String  executeCmd="";  
String[] executeCmd1=null;

    /**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
     
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
        // creates the save directory if it does not exists
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
               dbConn conn = new dbConn();
               
//               GET CREDENTIALS FOR THE TEMPORARY DATABASE.
            if (conn.dbsetup[3] != null) {
                dbpassword = conn.dbsetup[3];
}
            DB_URL = "jdbc:mysql://"+conn.dbsetup[0]+"/";
 dbname="pwp_temp";
 dbuser="root";  
PASS=dbpassword;
//dbpassword="";
System.out.println("pass  :  "+DB_URL);
IdGenerator IG = new IdGenerator();
 String [] localhostsplit  = conn.dbsetup[0].split(":");
 
 System.out.println("here "+localhostsplit[0]);
  System.out.println("here   2 "+localhostsplit[1]);
        //Get all the parts from request and write it to the file on server
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
        try {      
           created_db=CreateDB();
           System.out.println("created dd temp"); 
        } catch (SQLException ex) {
            Logger.getLogger(FileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileUploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("CREATED DB IS   :  "+created_db);
        System.out.println("FULL PATH IS  :  "+full_path); 
        temp_db=created_db;    
        System.out.println("temporary db  :  "+temp_db);
       String executer_path="";
    
       //        CREATE AN OBJECT OF MERGER CLASS.
        Merger merger = new Merger();
       session.setAttribute("mergerdone", "Initializing file upload");
       System.out.println("rea=============================="+session.getAttribute("mergerdone"));
        for (int i=0;i<myalphabet.length;i++){
                    System.out.println("at position  :  "+myalphabet[i]);
              String current_drive=myalphabet[i];
              File f =  new File(current_drive+":\\wamp\\mysql\\bin\\");
              File f1 = new File(current_drive+":\\wamp\\bin\\mysql\\mysql5.6.17\\bin");
              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.7\\bin");
              File f3 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.6\\bin");
             

        
  
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.
              
                         if (f3.exists() && f3.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysql", "--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  


found_folder="it is workbench";
System.out.println("here workbench");
break;
}
              
           if (f2.exists() && f2.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql", "--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  


found_folder="it is workbench";
System.out.println("here workbench");
break;
}   
if (f.exists() && f.isDirectory()){
  executer_path =current_drive+":\\wamp\\mysql\\bin\\mysql --user=" + dbuser+ "--password=" + dbpassword+ " " + dbname+ "-e source "+full_path;
   System.out.println("executer path    :  "+executer_path); 
executeCmd1 = new String[]{current_drive+":\\wamp\\mysql\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  


System.out.println(executeCmd1);
found_folder="it is old wamp";
break;
}
if (f1.exists() && f1.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysql", "--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+full_path};  


System.out.println(executeCmd1);

found_folder="it is new wamp";
break;
}
 
}               
             
System.out.println(found_folder);
System.out.println(executeCmd1);
System.out.println("DB URL IS: "+DB_URL+"USER NAME : "+USER+" PASSWORD : "+ PASS);
Process runtimeProcess;
        try {
 System.out.println("trying to import the data.");
 
 session.setAttribute("mergerdone", "Loading data file to server..");
 //if the selected database is biometric_master_db,do not import a database since 
    System.out.println("rea=============================="+session.getAttribute("mergerdone")); 
 
runtimeProcess = Runtime.getRuntime().exec(executeCmd1);

System.out.println("process started"+runtimeProcess);
             int processComplete = runtimeProcess.waitFor();
            System.out.println("at 1 is "+processComplete);
            if (processComplete == 0) {
                session.setAttribute("mergerdone", "Validating selected file."); 
//            READ THE SENT DATA AND MERGE IT TO THE MASTER DATABASE.
                int found_tables=merger.CheckTables();
                if(found_tables==16){
              session.setAttribute("mergerdone", "Data Merging has started.");
//                int clerks= merger.MergeDataClerk();
//                System.out.println("Merged clecks 1");
                String clients=merger.MergeDataClients();
                String [] cnts=clients.split("-");
                String mergedClients=cnts[0];
                String existingClients=cnts[1];
                String client="Old Form Clients Added :(<font color=\"blue\">"+mergedClients+"</font>).  Already existing Clients :</font> (<font color=\"blue\">"+existingClients+"</font>). <br>";
                 
                session.removeAttribute("mergerdone");
                session.setAttribute("mergerdone", "4% Done.");
                
                String personal_info=merger.MergePersonalInformation();
                String [] pi=personal_info.split("-");
                String mergedClient=pi[0];
                String existingClient=pi[1];
                String personalInfo="New Form >> Clients Added :(<font color=\"blue\">"+mergedClient+"</font>).  Already existing :</font> (<font color=\"blue\">"+existingClient+"</font>). <br>";
                session.removeAttribute("mergerdone");
                session.setAttribute("mergerdone", "15% Done.");
                
                 System.out.println("Merged clients "+clients);
                String groups=merger.MergeDataGroups();
                String [] gr=groups.split("-");
                String mergedgroups=gr[0];
                String existinggroups=gr[1];
                String group="Groups Added :(<font color=\"blue\">"+mergedgroups+"</font>).  Already Existing Groups :</font> (<font color=\"blue\">"+existinggroups+"</font>). <br>";
               session.removeAttribute("mergerdone");
                session.setAttribute("mergerdone", "20% Done.");
                
                
                 System.out.println("Merged groups "+groups);
//                int nhf=merger.MergeDataNhf();
                String nogroups=merger.MergeDataNoGroups();
                String [] ngrp=nogroups.split("-");
                String mergednogroup=ngrp[0];
                String existingnogroup=ngrp[1];
                String nogroup="Individual Added :(<font color=\"blue\">"+mergednogroup+"</font>).  Already Existing Groups :</font> (<font color=\"blue\">"+existingnogroup+"</font>). <br>";
                
                
                 System.out.println("Merged indiv "+nogroups);
                String registers=merger.MergeDataRegister();
                String [] reg=registers.split("-");
                String mergedregister=reg[0];
                String existingregister=reg[1];
                String register="Summary Register added :(<font color=\"blue\">"+mergedregister+"</font>).  Already Existing summary register :</font> (<font color=\"blue\">"+existingregister+"</font>). <br>";
                session.removeAttribute("mergerdone");
              session.setAttribute("mergerdone", "24% Done.");
                
                 System.out.println("Merged register "+registers);
                String serviceproviders=merger.MergeDataServiceProvider();
                String [] spders=serviceproviders.split("-");
                String mergedserviceproviders=spders[0];
                String existingserviceproviders=spders[1];
                String serviceprovider="Service providers added :(<font color=\"blue\">"+mergedserviceproviders+"</font>).  Already Existing service providers :</font> (<font color=\"blue\">"+existingserviceproviders+"</font>). <br>";
                session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "28% Done.");
                
                 System.out.println("Merged service provider "+serviceproviders);
                String servicesprovideds=merger.MergeDataServicesProvided();
                String [] spded=servicesprovideds.split("-");
                String mergedservicesprovided=spded[0];
                String existingservicesprovided=spded[1];
                String servicesprovided="Services provided rows added :(<font color=\"blue\">"+mergedservicesprovided+"</font>).  Already Existing services provided :</font> (<font color=\"blue\">"+existingservicesprovided+"</font>). <br>";
               session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "35% Done.");
                  
                 System.out.println("Merged servics "+servicesprovideds);
                String sessions=merger.MergeDataSessions();
                String [] sess=sessions.split("-");
                String mergedsessions=sess[0];
                String existingsessions=sess[1];
                String sessioner="Session rows added :(<font color=\"blue\">"+mergedsessions+"</font>).  Already Existing sessions :</font> (<font color=\"blue\">"+existingsessions+"</font>). <br>";
               session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "45% Done.(This may take Longer...)");
                
                 System.out.println("Merged sessions "+sessions);
                 String register2s=merger.MergeRegister2();
                String [] reg2=register2s.split("-");
                String mergedregister2=reg2[0];
                String existingregister2=reg2[1];
                String register2="Detailed register added rows :(<font color=\"blue\">"+mergedregister2+"</font>).  Updated rows :</font> (<font color=\"blue\">"+existingregister2+"</font>). <br>";
               session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "70% Done.");
                 
                 System.out.println("Merged register 2 "+register2s);
                 
                 String PreventionMessage=merger.MergePreventionMessage();
                 String [] prevmess=PreventionMessage.split("-");
                 String mergedPreventionMessage=prevmess[0];
                 String existingPreventionMessage=prevmess[1];
                 String preventionMess="Assessments, added rows : (<font color=\"blue\">"+mergedPreventionMessage+"</font>).  Updated rows :</font> (<font color=\"blue\">"+existingPreventionMessage+"</font>). <br>";
                 
                 merger.MergeFamilyPlanningTBPMTCT();
                 session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "75% Done.");
                 
                 merger.MergeHIVTestingSTIs();
                 session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "80% Done.");
                 
                 merger.MergePreventionCounselling ();   
                 session.removeAttribute("mergerdone");
                 session.setAttribute("mergerdone", "85% Done."); 
                 
                 String Adherence=merger.MergeAdherence();
                 session.removeAttribute("mergerdone");
                  session.setAttribute("mergerdone", "90% Done.");
                 
                   int deleteDuplicate =merger.deleteClients();
                 session.removeAttribute("mergerdone");
                  session.setAttribute("mergerdone", "95% Done.");
                  
                  String duplicates=deleteDuplicate+" <font color=\"red\"> duplicates were deleted by partner.</font><br>";
                  
                String notification=client+""+personalInfo+""+group+""+register+""+serviceprovider+""+servicesprovided+""+sessioner+""+register2+""+preventionMess+""+duplicates;
                 
                 
                System.out.println("Import completed successfully");
//           String updateSyncer="INSERT INTO syncdate (date) VALUES(?)";
//           conn.pst=conn.conn.prepareStatement(updateSyncer);
//           conn.pst.setString(1, IG.toDay());
//           conn.pst.execute();
              
           session.setAttribute("mergerdone", "98% Done.");
             session.setAttribute("mergerdone", "100% Done."); 
                session.setAttribute("datasend1", "<font color=\"green\">Data has been imported successfully: </font><br> "+notification+" <br>");
                session.setAttribute("saved_success", "success"); 
                  DropDB();
               
                }
                else{
                     session.setAttribute("mergerdone", "<font color=\"red\">Failed</font>: Wrong data file selected.");
              DropDB();
                  session.setAttribute("saved_success", "fail");   
                 session.setAttribute("datasend1", "<font color=\"red\">Failed To Import. Choose The Correct Database File.</font>");   
    session.setAttribute("mergerdone", "<font color=\"red\">NOTE</font>: Please select the correct PWP data file.");
               }  
                 
//               DROP TEMPORARY DATABASE.
             
             
                session.setAttribute("mergerdone", "Removing Uploaded File.");
             } else {
                 session.setAttribute("saved_success", "fail"); 
                System.out.println("Could not import data");
	session.setAttribute("datasend1", "<font color=\"red\">Data Not Imported.</font>");
            }
             session.removeAttribute("mergerdone");
            response.sendRedirect("MergeData.jsp");
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
//        request.setAttribute("message", "File uploaded successfully!");
//        getServletContext().getRequestDispatcher("/response.jsp").forward(
//                request, response);
        
    }
 
    /**
     * Utility method to get fil+ name from HTTP header content-disposition
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return contentDisp;
    }
    public String CreateDB() throws SQLException, ClassNotFoundException{
        Connection conn = null;
   Statement stmt = null;
   String pwp_temp="pwp_temp";
//STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      System.out.println("Creating database...");
      stmt = conn.createStatement();
     
      String sql_temp = "CREATE DATABASE IF NOT EXISTS "+pwp_temp+"";
      stmt.executeUpdate(sql_temp);
      System.out.println("Database created successfully...");
      
      
      return pwp_temp;
}
    
     public void DropDB() throws SQLException, ClassNotFoundException{
        Connection conn = null;
   Statement stmt = null;
   String pwp_temp="pwp_temp";
//STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Dropping Database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 4: Execute a query
      stmt = conn.createStatement();
      String drop_temp="DROP DATABASE "+pwp_temp+"";    
      stmt.executeUpdate(drop_temp); 
      System.out.println("Database Droped successfully...");
      
}
    
}

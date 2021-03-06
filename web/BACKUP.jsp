<%-- 
    Document   : BACKUP
    Created on : Mar 24, 2014, 4:03:32 PM
    Author     : Geofrey Nyabuto
--%>
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class BackUpData extends HttpServlet {

    String dbname, dbuser, dbpassword;
    boolean executed = false;
    String dbpath, dbpathD, dbpathE, dbpathF, dbpathG, dbpathM;
    String found_folder, full_date, path, computername, senderofmail;
    HttpSession session;
    String[] myalphabet = {"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
String day2="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session = request.getSession();
        dbConn conn = new dbConn();

        String lasttimestampid = "1";
        String lasttimestamp = "2014-3-10 ";

        dbname = "pwp";
        dbuser = "root";
        dbpassword = "";
        String nextpage = "";
        found_folder = "";
//MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
//        GET CURRENT DATE:
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
if(day<10){
    day2="0"+day;
}
        
        full_date = "Created_On_" + year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
        String full_dates = day + " / " + month + " / " + year + "  :and the exact time is  " + hour + ":" + min + ":" + sec;
        URL location = dbConn.class.getProtectionDomain().getCodeSource().getLocation();
        if (session.getAttribute("fname") != null && session.getAttribute("lname") != null) {


            senderofmail = " which has been send by system user by name " + session.getAttribute("fname").toString() + " " + session.getAttribute("lname").toString();

        } else {
            senderofmail = "";
        }
        if (isInternetReachable() == false) {
//         System.out.println("you are offline");
            session.setAttribute("datasend", "<font color=\"red\">You are not connected to the internet. Please connect to the internet and try again.</font>");

        }
        computername = InetAddress.getLocalHost().getHostName();
        if (1 == 1) {
//         
            String executeCmd = "";


            if (conn.dbsetup[3] != null) {
                dbpassword = conn.dbsetup[3];


            }



            if (conn.dbsetup[2] != null) {

                dbuser = conn.dbsetup[2];


            }



            if (conn.dbsetup[1] != null) {

                dbname = conn.dbsetup[1];

            }
            System.out.println("PASSWORD IS :" + dbpassword);
            System.out.println("USER IS :" + dbuser);
            System.out.println("DBNAME IS :" + dbname);


            for (int i = 0; i < myalphabet.length; i++) {
                try {
                    System.out.println("at position  :  " + myalphabet[i]);
                    String current_drive = myalphabet[i];
                    File f = new File(current_drive + ":\\wamp\\mysql\\bin\\");
                    File f1 = new File(current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
                    File f2 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
                    File f3 = new File(current_drive + ":\\APHIAPLUS\\PWPDBCONNECTION");

                    //     CREATE A DIRECTORY AND THE FILE TO HOLD DATA
                    if (f3.exists() && f3.isDirectory()) {
                        path = current_drive + ":\\APHIAPLUS\\PWPDBCONNECTION\\DATA\\BACKUP";
                        new File(path).mkdirs();
                        dbpath = path + "\\" + full_date + "_pwp.sql";
                    }

                    //select the last timestamp which a backup was picked from.....



                    conn.rs_6 = conn.st_6.executeQuery("select MAX(id) from timestamper");
                    if (conn.rs_6.next()) {lasttimestampid = conn.rs_6.getString(1);}
                   
                    conn.rs_6 = conn.st_6.executeQuery("select timestamp from timestamper WHERE id='"+lasttimestampid+"'");
                    if (conn.rs_6.next()) {lasttimestamp = conn.rs_6.getString(1);}

                    //conn.st_6.close();


// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             

                    if (f.exists() && f.isDirectory()) {
                        executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump --host=localhost --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " clerks clients groups health_facility no_group register service_provider services_provided sessions users --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
//executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";

                        found_folder = "it is old wamp";
                    }
                    if (f1.exists() && f1.isDirectory()) {


                        executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " clerks clients groups health_facility no_group register service_provider services_provided sessions users --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                        found_folder = "it is new wamp";
                    }
                    if (f2.exists() && f2.isDirectory()) {
                        executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " clerks clients groups health_facility no_group register service_provider services_provided sessions users --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                        found_folder = "it is workbench";
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BackUpData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }




            System.out.println(found_folder);
            System.out.println(executeCmd);
            Process runtimeProcess;
            try {
                System.out.println("trying to back up the data.");
                runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                System.out.println("at 1 is " + processComplete);
                if (processComplete == 0) {
//                SEND TO THE MAIL BACKED UP DATA.
                    System.out.println("Backup created successfully");

                    if (isInternetReachable() == true) {
                        Send_Data dt = new Send_Data();


                        //============at this pint, if the data i send, then do a new timestamp into the database


                        if (dt.Sendattachment(full_dates, dbpath, computername, senderofmail) == true) {
                            //do an insert
                            conn.st.executeUpdate("update timestamper set sent='yes' where id='" + lasttimestampid + "'");

String daytime=""+year+"-"+month+"-"+day2;
                            // a new timestamp that will be called next time a backup is being created.

                            conn.st.executeUpdate("insert into timestamper (timestamp,sent) values('"+daytime+"','No')");



                            session.setAttribute("datasend", "<font color=\"green\">Backup has been created and send via mail</font>");
                        } else {

                            session.setAttribute("datasend", "<font color=\"red\">Backup has been created but NOT send via mail due to problems in internet connection. Try to send Backup Again.</font>");
                        }

                    } else {

                        session.setAttribute("datasend", "<font color=\"red\">Backup has been created but NOT send via mail due to problems in internet connection</font>");
                    }

                } else {
                    System.out.println("Could not create the backup");
                    session.setAttribute("datasend", "Backup Could not be created");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
        response.sendRedirect("backupdata.jsp");
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
            Logger.getLogger(BackUpData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BackUpData.class.getName()).log(Level.SEVERE, null, ex);
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

  public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            URLConnection urlConnect = url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            urlConnect.getInputStream();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println("ALL SMS SCHEDULER:Unknown host");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("ALL SMS SCHEDULER:Error in internet connection");
            return false;
        }
        return true;
    }

}

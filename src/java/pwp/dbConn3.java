/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geofrey Nyabuto
 */

public class dbConn3{
public ResultSet rs3,rs4,rs5,rs1,rs,rs6,rs7;
    public Statement st, st1, st2, st3, st4, st5, st6, st7;
  public  PreparedStatement pst,pst1,pst2,pst3,pst4,pst5;
  public  PreparedStatement prest,prest1,prest2,prest3,prest4,prest5;
  public  Connection conn3 = null;
   public String passw="";
   public dbConn3 (){
    
    try{
        dbConn conn = new dbConn(); 
            if (conn.dbsetup[3] != null) {
                passw = conn.dbsetup[3];
}
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn3 = DriverManager.getConnection("jdbc:mysql://"+conn.dbsetup[0]+"/pwp",conn.dbsetup[2], ""+passw+"");
                 st = conn3.createStatement();
                st1 = conn3.createStatement();
                st2 = conn3.createStatement();
                st3 = conn3.createStatement();
                st4 = conn3.createStatement();
                st5 = conn3.createStatement();
       
    }
    catch(Exception e){
      Logger.getLogger(dbConn3.class.getName()).log(Level.SEVERE, null, e);  
    }
    
}
}
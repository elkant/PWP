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

public class dbConn4{
public ResultSet rst3,rst4,rst5,rst1,rst,rst6,rts7;
    public Statement stt, stt1, stt2, stt3, stt4, stt5, stt6, stt7;
  public  PreparedStatement pst,pst1,pst2,pst3,pst4,pst5;
  public  PreparedStatement prest,prest1,prest2,prest3,prest4,prest5;
   public String passw="";
   public  Connection connx = null;
   public dbConn4 (){
    
    try{
        dbConn conn = new dbConn(); 
            if (conn.dbsetup[3] != null) {
                passw = conn.dbsetup[3];
}
            Class.forName("com.mysql.jdbc.Driver").newInstance();
                 connx = DriverManager.getConnection("jdbc:mysql://"+conn.dbsetup[0]+"/pwp_temp",conn.dbsetup[2], ""+passw+"");
                 stt = connx.createStatement();
                stt1 = connx.createStatement();
                stt2 = connx.createStatement();
                stt3 = connx.createStatement();
                stt4 = connx.createStatement();
                stt5 = connx.createStatement();
       System.out.println("database : pwp_temp host : "+conn.dbsetup[0]+" user : "+conn.dbsetup[2]+" password :   "+passw);
    }
    catch(Exception e){
      Logger.getLogger(dbConn4.class.getName()).log(Level.SEVERE, null, e);  
    }
    
}
}
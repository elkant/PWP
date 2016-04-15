/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class LoadEditClient extends HttpServlet {
HttpSession session;
String found,client_id,fname,mname,lname,district_id,location,national_id,mobile_no,gender,dob,marital_status;
String employment_status,education_level,under_18,ovc_children;
String has_group,group_id,provider_id;
String art_status,hf_id,ccc_no;
String registration_date,approved_by,designation,approval_date;
String fullname,status,partner_id;
String new_group_name;
String pfname,pmname,plname,pnationalID,pmobileNO,timestamp,pfullname;
String district,county,county_id="";
String genderStatus,employment,education,marital;
String group_name,hasgroup,partner,provider,year_reg,art,dicid,dic,ward_id,selectedWard;
String hiv_year;
String linked_groupid,ifLinked;
Calendar cal=Calendar.getInstance();
ArrayList data = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       data.clear();
        int year=cal.get(Calendar.YEAR);  
    
        linked_groupid=ifLinked="";
        
       client_id=request.getParameter("client_id");
//       client_id="1886011833319";
       String getDetails="SELECT * FROM personal_information WHERE client_id='"+client_id+"'";
       conn.rs=conn.st.executeQuery(getDetails);
       if(conn.rs.next()==true){
         fname=conn.rs.getString("fname");
         mname=conn.rs.getString("mname");
         lname=conn.rs.getString("lname");
         if(mname.equals(lname)){mname="";}
         district_id=conn.rs.getString("district_id").trim();
         System.out.println("dissssssssssss : "+district_id);
         location=conn.rs.getString("location");
         national_id=conn.rs.getString("national_id");        
         mobile_no  =conn.rs.getString("mobile_no");      
         gender=conn.rs.getString("gender");
         dob   =conn.rs.getString("dob");     
         marital_status=conn.rs.getString("marital_status");
         employment_status =conn.rs.getString("employment_status");       
         education_level=conn.rs.getString("education_level");
         under_18     =conn.rs.getString("under_18s");   
         ovc_children =conn.rs.getString("ovc_children");       
         group_id=conn.rs.getString("group_id");
         provider_id  =conn.rs.getString("provider_id");      
         partner_id   =conn.rs.getString("partner_id");     
         hiv_year=conn.rs.getString("hiv_year");
         art   =conn.rs.getString("art_status");     
         hf_id      =conn.rs.getString("hf_id");  
         ccc_no    =conn.rs.getString("ccc_no");    
         registration_date=conn.rs.getString("registration_date");
         approved_by=conn.rs.getString("approved_by");
         designation=conn.rs.getString("designation");
         approval_date=conn.rs.getString("approval_date");  
         dicid=conn.rs.getString("dic_id");
         selectedWard=conn.rs.getString("ward_id");
         linked_groupid=conn.rs.getString("linked_group");
        ClientEditor CE = new ClientEditor();
        district="";
        county="";
        county_id="";
        genderStatus="";employment="";education="";marital="";
        group_name=hasgroup=partner=provider="";
        session.setAttribute("hfID", hf_id);
        
        
        
        if(linked_groupid==null){
         linked_groupid="";   
        }
            if(linked_groupid.equals("") && !group_id.equals("0")){
             linked_groupid= group_id;  
            }
        
        
        county="<option value=\"\">Choose County</option>";
        district="<option value=\"\">Choose District</option>";
        genderStatus="<option value=\"\">Gender</option>";
        marital="<option value=\"\">Marital Status</option>";
        employment="<option value=\"\">Employment type</option>";
        education="<option value=\"\">Educational level</option>";
        group_name="<option value=\"\">Choose group</option>";
        hasgroup="<option value=\"\">Choose status</option>";
        ifLinked="<option value=\"\">Choose status</option>";
        partner="<option value=\"\">Choose partner</option>";
        provider="<option value=\"\">Choose provider</option>";
        year_reg="<option value=\"\">Year</option>";
        art_status="<option value=\"\">ART Status</option>";
        
        String getCountyId="SELECT county_id FROM district WHERE district_id='"+district_id+"'";
        conn.rs1=conn.st1.executeQuery(getCountyId);
        if(conn.rs1.next()==true){
            county_id=conn.rs1.getString(1);
        }
    
        
        String getCounties="SELECT county_id,county_name FROM county";
        conn.rs1=conn.st1.executeQuery(getCounties);
        while(conn.rs1.next()){
          if(conn.rs1.getString(1).equals(county_id)){
               county+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";
         }
         else{
          county+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";   
         }
        }
        
      
      String getDistrict="SELECT district_id,district_name FROM district WHERE county_id='"+county_id+"'";
        conn.rs1=conn.st1.executeQuery(getDistrict);
        while(conn.rs1.next()){
         if(conn.rs1.getString(1).trim().equals(district_id.trim())){
             System.out.println("selected district is : "+conn.rs1.getString(2));
             district+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";
         }
         else{
          district+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";   
         }
        }
        
       String getGender="SELECT gender_name FROM gender";
       conn.rs1=conn.st1.executeQuery(getGender);
       while(conn.rs1.next()){
           if(conn.rs1.getString(1).toLowerCase().equalsIgnoreCase(gender.toLowerCase())){
           genderStatus+="<option value=\""+conn.rs1.getString(1).toLowerCase()+"\" selected>"+conn.rs1.getString(1).toUpperCase()+"</option>";
           }
           else{
           genderStatus+="<option value=\""+conn.rs1.getString(1).toLowerCase()+"\">"+conn.rs1.getString(1).toUpperCase()+"</option>";    
           }
       }
        String getMaritalStatus="SELECT id,name FROM marital_status";
       conn.rs1=conn.st1.executeQuery(getMaritalStatus);
       while(conn.rs1.next()){
        if(marital_status.equals(conn.rs1.getString(1))) {
         marital+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";   
        }  
        else{
          marital+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";    
        }  
          }
        System.out.println("status : "+marital);
       
       String getEmployment="SELECT id,name FROM employment_status";
       conn.rs1=conn.st1.executeQuery(getEmployment);
       while(conn.rs1.next()){
           if(employment_status.equals(conn.rs1.getString(1))){
               employment+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";
           }
           else{
               employment+="<option value=\""+conn.rs1.getString(1)+"\" >"+conn.rs1.getString(2)+"</option>";
           }
       }
       System.out.println("employment : "+employment);
      String getEducationLevel="SELECT id,name FROM education_levels";
      conn.rs1=conn.st1.executeQuery(getEducationLevel);
      while(conn.rs1.next()){
          if(education_level.equals(conn.rs1.getString(1))){
         education+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";   
          }
          else{
              education+="<option value=\""+conn.rs1.getString(1)+"\" >"+conn.rs1.getString(2)+"</option>";
          }
      }
    System.out.println("education : "+education);  
    
      String getPartners="SELECT partner_id,partner_name FROM partner";
      conn.rs1=conn.st1.executeQuery(getPartners);
      while(conn.rs1.next()){
         if(conn.rs1.getString(1).equals(partner_id)){
             partner+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>"; 
         }
         else{
             partner+="<option value=\""+conn.rs1.getString(1)+"\" >"+conn.rs1.getString(2)+"</option>";
         }
      }
    
      if(group_id.equals("0")){
    ifLinked+="<option value=\"yes\">Receive message in a group.</option>" ;
    ifLinked+="<option value=\"no\" selected>Receive message as an individual.</option>";
   }
   else{
    ifLinked+="<option value=\"yes\" selected>Receive message in a group.</option>" ;
    ifLinked+="<option value=\"no\">Receive message as an individual.</option>";   
   }
      
   if(linked_groupid.equals("")){
    hasgroup+="<option value=\"yes\">Yes</option>" ;
    hasgroup+="<option value=\"no\" selected>No</option>";
   }
   else{
    hasgroup+="<option value=\"yes\" selected>Yes</option>" ;
    hasgroup+="<option value=\"no\">No</option>";   
   }
  System.out.println("has group : "+hasgroup);
      String getGroup="SELECT group_id,group_name FROM groups WHERE district_id='"+district_id+"'";
      conn.rs1=conn.st1.executeQuery(getGroup);
      while(conn.rs1.next()){
          System.out.println("group id : "+conn.rs1.getString(1)+" group name : "+conn.rs1.getString(2));
     if(conn.rs1.getString(1).equals(linked_groupid)){
         group_name+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";
     }     
     else{
         group_name+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";
     }     
      }
      
      
     String getProvider="SELECT provider_id,fname,mname,lname FROM service_provider WHERE district_id='"+district_id+"'";
      conn.rs1=conn.st1.executeQuery(getProvider);
      while(conn.rs1.next()){
     pfname=conn.rs1.getString(2);
     pmname=conn.rs1.getString(3);
     plname=conn.rs1.getString(4);
     if(pmname.equals(plname)){ pmname="";}
     fullname=pfname+" "+pmname+" "+plname;
     
     if(conn.rs1.getString(1).equals(provider_id)){
         provider+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+fullname+"</option>";
     }
     else{
            provider+="<option value=\""+conn.rs1.getString(1)+"\">"+fullname+"</option>";
     }
      } 
      
 
         if(hiv_year.equals("0")){
            year_reg+="<option value=\"0\" selected>N/A</option>";
         }
         if(!hiv_year.equals("0")){
            year_reg+="<option value=\"0\">N/A</option>";
         }
        
           for(int i=1990;i<=year;i++){
               if(hiv_year.equals(""+i)){
                year_reg+="<option value=\""+i+"\" selected>"+i+"</option>";    
               }
               else{
                    year_reg+="<option value=\""+i+"\">"+i+"</option>";
               }
              
           }
         
          String getART="SELECT id,name FROM art_status";
          conn.rs1=conn.st1.executeQuery(getART);
          while(conn.rs1.next()){
              if(conn.rs1.getString(1).equals(art)){
                  art_status+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";
                     }     
                    else{
         art_status+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";
                        }
          }
          dic="<option value=\"\">Choose DIC</option>";
        String getdic="SELECT dic_id,dic_name FROM dic WHERE partner_id='"+partner_id+"'";
        conn.rs1=conn.st1.executeQuery(getdic);
        while(conn.rs1.next()){
            
            if(conn.rs1.getString(1).equals(dicid)){
                dic+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(2)+"</option>";
            }
            else{
                dic+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(2)+"</option>";
            }
        }
        String partner_ids="";
         ward_id="<option value=\"0\">Choose Ward</option>";
        int counter=0;
        String getWards="SELECT id,name,partner_ids FROM wards WHERE county_id='"+county_id+"'";
           conn.rs=conn.st.executeQuery(getWards);
           while(conn.rs.next()){
           partner_ids=conn.rs.getString(3);
           if(partner_ids.contains(","+partner_id+",")){
           if(conn.rs.getString(1).equals(selectedWard)){
               ward_id+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
           }
           else{
             ward_id+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";        
           }
               counter++;   
           }
           }
          if(counter==0){
               ward_id="<option value=\"\">No Ward</option>";
           } 
          
      System.out.println("district "+district);
        CE.setWard_id(ward_id);
        CE.setApproval_date(approval_date);
        CE.setApproved_by(approved_by);
        CE.setArt_status(art_status);
        CE.setCcc_no(ccc_no);
        CE.setClient_id(client_id);
        CE.setApproval_date(approval_date);
        CE.setDistrict_id(district_id);
        CE.setDob(dob);
        CE.setEducation_level(education_level);
        CE.setEmployment_status(employment_status);
        CE.setFname(fname);
        CE.setGender(gender);
        CE.setGroup_id(group_id);
        CE.setHas_group(has_group);
        CE.setHf_id(hf_id);
        CE.setLname(lname);
        CE.setLocation(location);
        CE.setMarital_status(marital_status);
        CE.setMname(mname);
        CE.setMobile_no(mobile_no);
        CE.setNational_id(national_id);
        CE.setNew_group_name(new_group_name);
        CE.setOvc_children(ovc_children);
        CE.setPartner_id(partner_id);
        CE.setProvider_id(provider_id);
        CE.setRegistration_date(registration_date);
        CE.setUnder_18(under_18);
        CE.setDesignation(designation);
        
        CE.setYear_reg(year_reg);
        CE.setEducation(education);
        CE.setEmployment(employment);
        CE.setGenderStatus(genderStatus);
        CE.setHas_group(hasgroup);
        CE.setIfLinked(ifLinked);
        CE.setGroup_name(group_name);
        CE.setMarital(marital);
        CE.setProvider(provider);
        CE.setPartner(partner);
        CE.setDistrict(district);
        CE.setCounty(county);
        CE.setDic(dic);
         System.out.println("marital status : "+marital);
       data.add(CE);
       }
       
       session.setAttribute("editClientData", data);
      
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
       response.sendRedirect("EditClientRegistrationForm.jsp");
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
        Logger.getLogger(LoadEditClient.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadEditClient.class.getName()).log(Level.SEVERE, null, ex);
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

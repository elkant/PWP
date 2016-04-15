       
     
       String addPersonalDetails="INSERT INTO personal_information "
               + "(client_id,fname,mname,lname,district_id,location,"
               + "national_id,mobile_no,gender,dob,marital_status) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
       conn.pst=conn.conn.prepareStatement(addPersonalDetails);
       conn.pst.setString(1, client_id);
       conn.pst.setString(2, fname);
       conn.pst.setString(3, mname);
       conn.pst.setString(4, lname);
       conn.pst.setString(5, district_id);
       conn.pst.setString(6, location);
       conn.pst.setString(7, national_id);
       conn.pst.setString(8, mobile_no);
       conn.pst.setString(9, gender);
       conn.pst.setString(10, dob);
       conn.pst.setString(11, marital_status);
       
       conn.pst.executeUpdate();
       
       
      String addGroupDetails="INSERT INTO linked_group (client_id,group_id,provider_id,district_id,partner_id) "
               + "VALUES(?,?,?,?,?)";
       conn.pst=conn.conn.prepareStatement(addGroupDetails);
       conn.pst.setString(1, client_id);
       conn.pst.setString(2, group_id);
       conn.pst.setString(3, provider_id);
       conn.pst.setString(4, district_id);
       conn.pst.setString(5, partner_id);
       
       conn.pst.executeUpdate();
       
       
       
          String addHouseHold="INSERT INTO household_details(client_id,employment_status,education_level,under_18s,ovc_children) "
                  + "VALUES(?,?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(addHouseHold);
          conn.pst.setString(1, client_id);
          conn.pst.setString(2, employment_status);
          conn.pst.setString(3, education_level);
          conn.pst.setString(4, under_18);
          conn.pst.setString(5, ovc_children);
          
          conn.pst.executeUpdate();
          
          
          String addCaregiver="INSERT INTO health_details(client_id,hiv_year,art_status,hf_id,ccc_no) "
                  + "VALUES(?,?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(addCaregiver);
          conn.pst.setString(1, client_id);
          conn.pst.setString(2, hiv_year);
          conn.pst.setString(3, art_status);
          conn.pst.setString(4, hf_id);
          conn.pst.setString(5, ccc_no);
          
          conn.pst.executeUpdate();
         
          
           String addApprover="INSERT INTO approval_section(client_id,registration_date,approved_by,designation,approval_date) "
                  + "VALUES(?,?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(addApprover);
          conn.pst.setString(1, client_id);
          conn.pst.setString(2, registration_date);
          conn.pst.setString(3, approved_by);
          conn.pst.setString(4, designation);
          conn.pst.setString(5, approval_date);
          
          conn.pst.executeUpdate();
          
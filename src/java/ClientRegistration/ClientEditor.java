/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

import java.io.Serializable;

/**
 *
 * @author Geofrey Nyabuto
 */
public class ClientEditor implements Serializable{
String found,client_id,fname,mname,lname,district_id,location,national_id,mobile_no,gender,dob,marital_status;
String employment_status,education_level,under_18,ovc_children;
String has_group,group_id,provider_id;
String art_status,hf_id,ccc_no;
String registration_date,approved_by,designation,approval_date;
String fullname,status,partner_id;
String new_group_name;
String pfname,pmname,plname,pnationalID,pmobileNO,timestamp; 
String genderStatus,employment,education,marital;
String district,county,county_id="";
String year_reg,dic,ward_id;
String ifLinked;

    public String getIfLinked() {
        return ifLinked;
    }

    public void setIfLinked(String ifLinked) {
        this.ifLinked = ifLinked;
    }
    public String getWard_id() {
        return ward_id;
    }

    public void setWard_id(String ward_id) {
        this.ward_id = ward_id;
    }

    public String getDic() {
        return dic;
    }

    public void setDic(String dic) {
        this.dic = dic;
    }

    public String getYear_reg() {
        return year_reg;
    }

    public void setYear_reg(String year_reg) {
        this.year_reg = year_reg;
    }
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCounty_id() {
        return county_id;
    }

    public void setCounty_id(String county_id) {
        this.county_id = county_id;
    }
    public String getGenderStatus() {
        return genderStatus;
    }

    public void setGenderStatus(String genderStatus) {
        this.genderStatus = genderStatus;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getHasgroup() {
        return hasgroup;
    }

    public void setHasgroup(String hasgroup) {
        this.hasgroup = hasgroup;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
String group_name,hasgroup,partner,provider;
    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getUnder_18() {
        return under_18;
    }

    public void setUnder_18(String under_18) {
        this.under_18 = under_18;
    }

    public String getOvc_children() {
        return ovc_children;
    }

    public void setOvc_children(String ovc_children) {
        this.ovc_children = ovc_children;
    }

    public String getHas_group() {
        return has_group;
    }

    public void setHas_group(String has_group) {
        this.has_group = has_group;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(String provider_id) {
        this.provider_id = provider_id;
    }

    public String getArt_status() {
        return art_status;
    }

    public void setArt_status(String art_status) {
        this.art_status = art_status;
    }

    public String getHf_id() {
        return hf_id;
    }

    public void setHf_id(String hf_id) {
        this.hf_id = hf_id;
    }

    public String getCcc_no() {
        return ccc_no;
    }

    public void setCcc_no(String ccc_no) {
        this.ccc_no = ccc_no;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getNew_group_name() {
        return new_group_name;
    }

    public void setNew_group_name(String new_group_name) {
        this.new_group_name = new_group_name;
    }

    public String getPfname() {
        return pfname;
    }

    public void setPfname(String pfname) {
        this.pfname = pfname;
    }

    public String getPmname() {
        return pmname;
    }

    public void setPmname(String pmname) {
        this.pmname = pmname;
    }

    public String getPlname() {
        return plname;
    }

    public void setPlname(String plname) {
        this.plname = plname;
    }

    public String getPnationalID() {
        return pnationalID;
    }

    public void setPnationalID(String pnationalID) {
        this.pnationalID = pnationalID;
    }

    public String getPmobileNO() {
        return pmobileNO;
    }

    public void setPmobileNO(String pmobileNO) {
        this.pmobileNO = pmobileNO;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
 
    }

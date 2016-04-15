/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.Serializable;

/**
 *
 * @author Geofrey Nyabuto
 */
public class attendance_bean implements Serializable{
    private String client_name,age,sex,client_id,total_clients,disabled,checked,message;
String contraceptive_method,rsp,condoms_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status;
String regid;
String imageCM,imageSP,imageTB,imageSTI,imagePartner,imageChildren,imageStatus;

    public String getImageCM() {
        return imageCM;
    }

    public void setImageCM(String imageCM) {
        this.imageCM = imageCM;
    }

    public String getImageSP() {
        return imageSP;
    }

    public void setImageSP(String imageSP) {
        this.imageSP = imageSP;
    }

    public String getImageTB() {
        return imageTB;
    }

    public void setImageTB(String imageTB) {
        this.imageTB = imageTB;
    }

    public String getImageSTI() {
        return imageSTI;
    }

    public void setImageSTI(String imageSTI) {
        this.imageSTI = imageSTI;
    }

    public String getImagePartner() {
        return imagePartner;
    }

    public void setImagePartner(String imagePartner) {
        this.imagePartner = imagePartner;
    }

    public String getImageChildren() {
        return imageChildren;
    }

    public void setImageChildren(String imageChildren) {
        this.imageChildren = imageChildren;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }
    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }
    public String getContraceptive_method() {
        return contraceptive_method;
    }

    public void setContraceptive_method(String contraceptive_method) {
        this.contraceptive_method = contraceptive_method;
    }

    public String getRsp() {
        return rsp;
    }

    public void setRsp(String rsp) {
        this.rsp = rsp;
    }

    public String getCondoms_given() {
        return condoms_given;
    }

    public void setCondoms_given(String condoms_given) {
        this.condoms_given = condoms_given;
    }

    public String getScreened_tb() {
        return screened_tb;
    }

    public void setScreened_tb(String screened_tb) {
        this.screened_tb = screened_tb;
    }

    public String getScreened_stis() {
        return screened_stis;
    }

    public void setScreened_stis(String screened_stis) {
        this.screened_stis = screened_stis;
    }

    public String getTested_partner() {
        return tested_partner;
    }

    public void setTested_partner(String tested_partner) {
        this.tested_partner = tested_partner;
    }

    public String getTested_children() {
        return tested_children;
    }

    public void setTested_children(String tested_children) {
        this.tested_children = tested_children;
    }

    public String getDisclosed_status() {
        return disclosed_status;
    }

    public void setDisclosed_status(String disclosed_status) {
        this.disclosed_status = disclosed_status;
    }
 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public int getPositioner() {
        return positioner;
    }

    public void setPositioner(int positioner) {
        this.positioner = positioner;
    }
    int positioner;

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getTotal_clients() {
        return total_clients;
    }

    public void setTotal_clients(String total_clients) {
        this.total_clients = total_clients;
    }
    
    
}

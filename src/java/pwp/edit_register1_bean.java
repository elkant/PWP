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
public class edit_register1_bean implements Serializable{
    private String session_date,messages,method_used,duration,male_cds,female_cds,iec_materials;
         int session_id;
     String sessions_id,disabled;

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
 int positioner;

    public int getPositioner() {
        return positioner;
    }

    public String getSessions_id() {
        return sessions_id;
    }

    public void setSessions_id(String sessions_id) {
        this.sessions_id = sessions_id;
    }

    public void setPositioner(int positioner) {
        this.positioner = positioner;
    }
    public String getSession_date() {
        return session_date;
    }

    public void setSession_date(String session_date) {
        this.session_date = session_date;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getMethod_used() {
        return method_used;
    }

    public void setMethod_used(String method_used) {
        this.method_used = method_used;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMale_cds() {
        return male_cds;
    }

    public void setMale_cds(String male_cds) {
        this.male_cds = male_cds;
    }

    public String getFemale_cds() {
        return female_cds;
    }

    public void setFemale_cds(String female_cds) {
        this.female_cds = female_cds;
    }

    public String getIec_materials() {
        return iec_materials;
    }

    public void setIec_materials(String iec_materials) {
        this.iec_materials = iec_materials;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    
}

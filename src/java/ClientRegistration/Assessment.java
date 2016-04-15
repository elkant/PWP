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
public class Assessment implements Serializable{
String prevention_counseling_id,hiv_testing_stis_id,family_planning_tb_pmtct_id,referral_point;

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
String client_name,dob,sex,group_name,provider_name,district_name,location;
    public String getReferral_point() {
        return referral_point;
    }

    public void setReferral_point(String referral_point) {
        this.referral_point = referral_point;
    }

    public String getPrevention_counseling_id() {
        return prevention_counseling_id;
    }

    public void setPrevention_counseling_id(String prevention_counseling_id) {
        this.prevention_counseling_id = prevention_counseling_id;
    }

    public String getHiv_testing_stis_id() {
        return hiv_testing_stis_id;
    }

    public void setHiv_testing_stis_id(String hiv_testing_stis_id) {
        this.hiv_testing_stis_id = hiv_testing_stis_id;
    }

    public String getFamily_planning_tb_pmtct_id() {
        return family_planning_tb_pmtct_id;
    }

    public void setFamily_planning_tb_pmtct_id(String family_planning_tb_pmtct_id) {
        this.family_planning_tb_pmtct_id = family_planning_tb_pmtct_id;
    }
String date_of_assessment,id,client_id;
String knowledge_of_hiv,partner_hiv_testing,any_child_tested,any_child_not_tested,discordance,hiv_disclosure;
String abstinence,faithful_to_one_partner,safer_sex_methods,multiple_sex_partner,condom_use,alcohol_substance_abuse;
String adherence_to_arv,adherence_to_others,asking_stis_questions,family_planning;

    public String getDate_of_assessment() {
        return date_of_assessment;
    }

    public void setDate_of_assessment(String date_of_assessment) {
        this.date_of_assessment = date_of_assessment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getKnowledge_of_hiv() {
        return knowledge_of_hiv;
    }

    public void setKnowledge_of_hiv(String knowledge_of_hiv) {
        this.knowledge_of_hiv = knowledge_of_hiv;
    }

    public String getPartner_hiv_testing() {
        return partner_hiv_testing;
    }

    public void setPartner_hiv_testing(String partner_hiv_testing) {
        this.partner_hiv_testing = partner_hiv_testing;
    }

    public String getAny_child_tested() {
        return any_child_tested;
    }

    public void setAny_child_tested(String any_child_tested) {
        this.any_child_tested = any_child_tested;
    }

    public String getAny_child_not_tested() {
        return any_child_not_tested;
    }

    public void setAny_child_not_tested(String any_child_not_tested) {
        this.any_child_not_tested = any_child_not_tested;
    }

    public String getDiscordance() {
        return discordance;
    }

    public void setDiscordance(String discordance) {
        this.discordance = discordance;
    }

    public String getHiv_disclosure() {
        return hiv_disclosure;
    }

    public void setHiv_disclosure(String hiv_disclosure) {
        this.hiv_disclosure = hiv_disclosure;
    }

    public String getAbstinence() {
        return abstinence;
    }

    public void setAbstinence(String abstinence) {
        this.abstinence = abstinence;
    }

    public String getFaithful_to_one_partner() {
        return faithful_to_one_partner;
    }

    public void setFaithful_to_one_partner(String faithful_to_one_partner) {
        this.faithful_to_one_partner = faithful_to_one_partner;
    }

    public String getSafer_sex_methods() {
        return safer_sex_methods;
    }

    public void setSafer_sex_methods(String safer_sex_methods) {
        this.safer_sex_methods = safer_sex_methods;
    }

    public String getMultiple_sex_partner() {
        return multiple_sex_partner;
    }

    public void setMultiple_sex_partner(String multiple_sex_partner) {
        this.multiple_sex_partner = multiple_sex_partner;
    }

    public String getCondom_use() {
        return condom_use;
    }

    public void setCondom_use(String condom_use) {
        this.condom_use = condom_use;
    }

    public String getAlcohol_substance_abuse() {
        return alcohol_substance_abuse;
    }

    public void setAlcohol_substance_abuse(String alcohol_substance_abuse) {
        this.alcohol_substance_abuse = alcohol_substance_abuse;
    }

    public String getAdherence_to_arv() {
        return adherence_to_arv;
    }

    public void setAdherence_to_arv(String adherence_to_arv) {
        this.adherence_to_arv = adherence_to_arv;
    }

    public String getAdherence_to_others() {
        return adherence_to_others;
    }

    public void setAdherence_to_others(String adherence_to_others) {
        this.adherence_to_others = adherence_to_others;
    }

    public String getAsking_stis_questions() {
        return asking_stis_questions;
    }

    public void setAsking_stis_questions(String asking_stis_questions) {
        this.asking_stis_questions = asking_stis_questions;
    }

    public String getFamily_planning() {
        return family_planning;
    }

    public void setFamily_planning(String family_planning) {
        this.family_planning = family_planning;
    }

    public String getPlanning_to_have_children() {
        return planning_to_have_children;
    }

    public void setPlanning_to_have_children(String planning_to_have_children) {
        this.planning_to_have_children = planning_to_have_children;
    }

    public String getScreened_for_TB() {
        return screened_for_TB;
    }

    public void setScreened_for_TB(String screened_for_TB) {
        this.screened_for_TB = screened_for_TB;
    }

    public String getPrevention_message_id() {
        return prevention_message_id;
    }

    public void setPrevention_message_id(String prevention_message_id) {
        this.prevention_message_id = prevention_message_id;
    }

    public String getHiv_disclosure2() {
        return hiv_disclosure2;
    }

    public void setHiv_disclosure2(String hiv_disclosure2) {
        this.hiv_disclosure2 = hiv_disclosure2;
    }

    public String getSafer_sex_methods2() {
        return safer_sex_methods2;
    }

    public void setSafer_sex_methods2(String safer_sex_methods2) {
        this.safer_sex_methods2 = safer_sex_methods2;
    }

    public String getAlcohol_use() {
        return alcohol_use;
    }

    public void setAlcohol_use(String alcohol_use) {
        this.alcohol_use = alcohol_use;
    }

    public String getAdherence_to_arvs() {
        return adherence_to_arvs;
    }

    public void setAdherence_to_arvs(String adherence_to_arvs) {
        this.adherence_to_arvs = adherence_to_arvs;
    }

    public String getAdherence_other_medications() {
        return adherence_other_medications;
    }

    public void setAdherence_other_medications(String adherence_other_medications) {
        this.adherence_other_medications = adherence_other_medications;
    }

    public String getCouples_counseling() {
        return couples_counseling;
    }

    public void setCouples_counseling(String couples_counseling) {
        this.couples_counseling = couples_counseling;
    }

    public String getPartner_tested() {
        return partner_tested;
    }

    public void setPartner_tested(String partner_tested) {
        this.partner_tested = partner_tested;
    }

    public String getChildren_tested() {
        return children_tested;
    }

    public void setChildren_tested(String children_tested) {
        this.children_tested = children_tested;
    }

    public String getReferral_for_sti() {
        return referral_for_sti;
    }

    public void setReferral_for_sti(String referral_for_sti) {
        this.referral_for_sti = referral_for_sti;
    }

    public String getRisk_reduction_info() {
        return risk_reduction_info;
    }

    public void setRisk_reduction_info(String risk_reduction_info) {
        this.risk_reduction_info = risk_reduction_info;
    }

    public String getTreatment_adherence() {
        return treatment_adherence;
    }

    public void setTreatment_adherence(String treatment_adherence) {
        this.treatment_adherence = treatment_adherence;
    }

    public String getCondoms_provided() {
        return condoms_provided;
    }

    public void setCondoms_provided(String condoms_provided) {
        this.condoms_provided = condoms_provided;
    }

    public String getPregnancy_status() {
        return pregnancy_status;
    }

    public void setPregnancy_status(String pregnancy_status) {
        this.pregnancy_status = pregnancy_status;
    }

    public String getHormonal_contraceptive() {
        return hormonal_contraceptive;
    }

    public void setHormonal_contraceptive(String hormonal_contraceptive) {
        this.hormonal_contraceptive = hormonal_contraceptive;
    }

    public String getCondoms() {
        return condoms;
    }

    public void setCondoms(String condoms) {
        this.condoms = condoms;
    }

    public String getPregnancy_counseling() {
        return pregnancy_counseling;
    }

    public void setPregnancy_counseling(String pregnancy_counseling) {
        this.pregnancy_counseling = pregnancy_counseling;
    }

    public String getTransmission_risks() {
        return transmission_risks;
    }

    public void setTransmission_risks(String transmission_risks) {
        this.transmission_risks = transmission_risks;
    }

    public String getTb_screening() {
        return tb_screening;
    }

    public void setTb_screening(String tb_screening) {
        this.tb_screening = tb_screening;
    }

    public String getReferred_tb_diagnosis() {
        return referred_tb_diagnosis;
    }

    public void setReferred_tb_diagnosis(String referred_tb_diagnosis) {
        this.referred_tb_diagnosis = referred_tb_diagnosis;
    }

    public String getReferred_pmtct_services() {
        return referred_pmtct_services;
    }

    public void setReferred_pmtct_services(String referred_pmtct_services) {
        this.referred_pmtct_services = referred_pmtct_services;
    }

    public String getOther_referrals() {
        return other_referrals;
    }

    public void setOther_referrals(String other_referrals) {
        this.other_referrals = other_referrals;
    }
String planning_to_have_children,screened_for_TB; 

String prevention_message_id,hiv_disclosure2,safer_sex_methods2,alcohol_use,adherence_to_arvs;
String adherence_other_medications,couples_counseling;

String partner_tested,children_tested,referral_for_sti,risk_reduction_info,treatment_adherence,condoms_provided;

String pregnancy_status,hormonal_contraceptive,condoms,pregnancy_counseling,transmission_risks;
String tb_screening,referred_tb_diagnosis,referred_pmtct_services,other_referrals;

    
    
}

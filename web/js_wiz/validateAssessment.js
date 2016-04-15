$(document).ready(function(){
    	// Smart Wizard     	
  		$('#wizard').smartWizard({transitionEffect:'slideleft',onLeaveStep:leaveAStepCallback,onFinish:onFinishCallback,enableFinishButton:true});

      function leaveAStepCallback(obj){
        var step_num= obj.attr('rel');
        return validateSteps(step_num);
      }
      
      function onFinishCallback(){
       if(validateAllSteps()){
        $('form').submit();
       }
      }
            
		});
	   
    function validateAllSteps(){
       var isStepValid = true;
        if(validateStep1() == false){
         isStepValid = true;
//         $('#wizard').smartWizard('setError',{stepnum:1,iserror:true});         
       }else{
//         $('#wizard').smartWizard('setError',{stepnum:1,iserror:false});
       }
       
       if(validateStep2() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:2,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:2,iserror:false});
       }
       
       if(validateStep3() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:3,iserror:false});
       }
       if(validateStep4() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:4,iserror:false});
       }
        if(validateStep5() == false){
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:5,iserror:false});
       }
       if(!isStepValid){
          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please correct the errors in the steps and continue');
       }
              
       return isStepValid;
    } 	
		
		
		function validateSteps(step){
		  var isStepValid = true;
      // validate step 1
//        
//      
//       
        
//   

       if(step == 1){
        if(validateStep1() == false ){
          isStepValid = true; 
          
//        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
//          $('#wizard').smartWizard('hideMessage');
//          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
      if(step == 2){
        if(validateStep2() == false ){
          isStepValid = false; 
        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
     
     //  validate step2
      if(step == 3){
        if(validateStep3() == false ){
          isStepValid = false; 
        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
            if(step == 4){
        if(validateStep4() == false ){
          isStepValid = false; 
         $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
 if(step == 5){
        if(validateStep5() == false ){
          isStepValid = false; 
       $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
      return isStepValid;
    }

function validateStep1(){
       var isValid = true;
//      alert("called");
     return isValid;
       }


function validateStep2(){
       var isValid = true;
       
 var date_of_assessment;
var knowledge_of_hiv,partner_hiv_testing,any_child_tested,any_child_not_tested,discordance,hiv_disclosure;
var abstinence,faithful_to_one_partner,safer_sex_methods,multiple_sex_partner,condom_use,alcohol_substance_abuse;
var adherence_to_arv,adherence_to_others,asking_stis_questions,family_planning;
var planning_to_have_children,screened_for_TB; 

date_of_assessment=document.getElementById("date_of_assessment").value;
knowledge_of_hiv=document.getElementById("knowledge_of_hiv").value;
partner_hiv_testing=document.getElementById("partner_hiv_testing").value;
any_child_tested=document.getElementById("any_child_tested").value;
any_child_not_tested=document.getElementById("any_child_not_tested").value;
discordance=document.getElementById("discordance").value;
hiv_disclosure=document.getElementById("hiv_disclosure").value;
abstinence=document.getElementById("abstinence").value;
faithful_to_one_partner=document.getElementById("faithful_to_one_partner").value;
safer_sex_methods=document.getElementById("safer_sex_methods").value;
multiple_sex_partner=document.getElementById("multiple_sex_partner").value;
condom_use=document.getElementById("condom_use").value;
alcohol_substance_abuse=document.getElementById("alcohol_substance_abuse").value;
adherence_to_arv=document.getElementById("adherence_to_arv").value;
adherence_to_others=document.getElementById("adherence_to_others").value;
asking_stis_questions=document.getElementById("asking_stis_questions").value;
family_planning=document.getElementById("family_planning").value;
planning_to_have_children=document.getElementById("planning_to_have_children").value;
screened_for_TB=document.getElementById("screened_for_TB").value;


if(date_of_assessment==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select date of assessment.</b></font>');
        isValid=false;    
        }
else{
 if(knowledge_of_hiv==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client has knowledge on HIV status.</b></font>');
        isValid=false;    
        }
    else{
 if(partner_hiv_testing==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in partner HIV testing.</b></font>');
        isValid=false;    
        }
   else{
 if(any_child_tested==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if any children was tested.</b></font>');
        isValid=false;    
        }
    else{
 if(any_child_not_tested==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if any children was NOT tested.</b></font>');
        isValid=false;    
        }
     else{
 if(discordance==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in discordance.</b></font>');
        isValid=false;    
        }
       else{
 if(hiv_disclosure==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in HIV disclosure.</b></font>');
        isValid=false;    
        }
   else{
 if(abstinence==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in abstinence.</b></font>');
        isValid=false;    
        }
     else{
 if(faithful_to_one_partner==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in faithful to one partner.</b></font>');
        isValid=false;    
        }
    else{
 if(safer_sex_methods==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in safer sex methods.</b></font>');
        isValid=false;    
        }
       else{
 if(multiple_sex_partner==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in Multiple sex partners.</b></font>');
        isValid=false;    
        }
       else{
 if(condom_use==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in condom use.</b></font>');
        isValid=false;    
        }
      else{
 if(alcohol_substance_abuse==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in Alcohol and substance abuse.</b></font>');
        isValid=false;    
        }
       else{
 if(adherence_to_arv==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client is adhering to ARV\'s.</b></font>');
        isValid=false;    
        }
    else{
 if(adherence_to_others==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client is adhering to other medications.</b></font>');
        isValid=false;    
        }
     else{
 if(asking_stis_questions==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client is asking assessment questions.</b></font>');
        isValid=false;    
        }
      else{
 if(family_planning==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if assessing pregnancy intentions.</b></font>');
        isValid=false;    
        }
       else{
 if(planning_to_have_children==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client is planning to have children.</b></font>');
        isValid=false;    
        }
      else{
 if(screened_for_TB==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client was screened for TB.</b></font>');
        isValid=false;    
        }
        
       }}}}}}}}}}}}}}}}}
       } 
   return isValid;
//    return true;
    }
    
function validateStep3(){
       var isValid = true;
       var hiv_disclosure2,safer_sex_methods2,alcohol_use,adherence_to_arvs;
       var adherence_other_medications,couples_counseling;
       var partner_tested,children_tested;
//       alert("here")
      
       hiv_disclosure2=$("#hiv_disclosure2").val();
       safer_sex_methods2=$("#safer_sex_methods2").val();
       alcohol_use=$("#alcohol_use").val();
       adherence_to_arvs=$("#adherence_to_arvs").val();
       adherence_other_medications=$("#adherence_other_medications").val();
       couples_counseling=$("#couples_counseling").val();
       partner_tested=$("#partner_tested").val();
       children_tested=$("#children_tested").val();
 
       
        if(hiv_disclosure2==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in HIV disclosure. </b></font>');
       isValid = false; 
        }
        else{
         if(safer_sex_methods2==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in safer sex methods. </b></font>');
       isValid = false; 
        }
        else{
         if(alcohol_use==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in alcohol use. </b></font>');
       isValid = false; 
        }
        else{
        if(adherence_to_arvs==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in adherence to ARV\'s. </b></font>');
       isValid = false; 
        }
        else{
        if(adherence_other_medications==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in adherence to <br> other medications (TB,Prophylaxis). </b></font>');
       isValid = false; 
        }
        else{
         if(couples_counseling==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in couple <br> counseling(discordant and concordant). </b></font>');
       isValid = false; 
        }
        else{
        if(partner_tested==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in HIV testing (Partners). </b></font>');
       isValid = false; 
        }
        else{
        if(children_tested==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select  an item in HIV testing (Children). </b></font>');
       isValid = false; 
        }
       }}}}}}  
         
        }
       return isValid;
//   return true;
   }
   
function validateStep4(){
       var isValid = true;
       var referral_for_sti,risk_reduction_info,treatment_adherence,condoms_provided;
       var pregnancy_status,hormonal_contraceptive,condoms,pregnancy_counseling,transmission_risks;
       
       referral_for_sti=$("#referral_for_sti").val();
       risk_reduction_info=$("#risk_reduction_info").val();
       treatment_adherence=$("#treatment_adherence").val();
       condoms_provided=$("#condoms_provided").val();
       pregnancy_status=$("#pregnancy_status").val();
       hormonal_contraceptive=$("#hormonal_contraceptive").val();
       condoms=$("#condoms").val();
       pregnancy_counseling=$("#pregnancy_counseling").val();
       transmission_risks=$("#transmission_risks").val();
       

        if(referral_for_sti==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in providing <br> referral for STI management at a health facility.</b></font>');
        isValid=false;    
        }
        else{
           if(risk_reduction_info==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in providing <br> targeted isk reducton information and conseling.</b></font>');
        isValid=false;    
        }
        else{
         if(treatment_adherence==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Select an tem in follow up to <br> ensure treatment adherence.</b></font>');
        isValid=false;    
        }
        else{
         if(condoms_provided==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if condoms were provided.</b></font>');
        isValid=false;    
        }
        else{
        if(pregnancy_status==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in assessing <br> pregnancy status and intentions.</b></font>');
        isValid=false;    
        }
        else{
        if(hormonal_contraceptive==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in <br> referral for hormonal contraceptives.</b></font>');
        isValid=false;    
        }
        else{
         if(condoms==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in <br> provision or referral for condoms.</b></font>');
        isValid=false;    
        }
        else{
         if(pregnancy_counseling==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select an item in conducting <br> safer pregnancy counseling.</b></font>');
        isValid=false;    
        }
        else{
        if(transmission_risks==""){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>please select an item in provided with <br> information on transmission risks .</b></font>');
        isValid=false;    
        }}}}}}}} 
            
        }
      
   return isValid;
//   return true;
    }
    
function validateStep5(){
       var isValid = true;
        
        var tb_screening,referred_tb_diagnosis,referred_pmtct_services,other_referrals,referral_point;
        
        tb_screening=$("#tb_screening").val();
        referred_tb_diagnosis=$("#referred_tb_diagnosis").val();
        referred_pmtct_services=$("#referred_pmtct_services").val();
        other_referrals=$("#other_referrals").val();
        referral_point=$("#referral_point").val();
        if(tb_screening==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client<br> was referred for TB screening. </b></font>');
       isValid = false; 
        }
        else{
         if(referred_tb_diagnosis==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client<br> was referred for TB diagnosis. </b></font>');
       isValid = false; 
        }
        else{
          if(referred_pmtct_services==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if the client<br> was referred for PMTCT services. </b></font>');
       isValid = false; 
        }
        else{
          if(other_referrals==""){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select if there were other referrals. </b></font>');
       isValid = false; 
        }
        else{
           if(referral_point=="" && other_referrals=="1"){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please enter the referral point. </b></font>');
       isValid = false; 
        }
        }
          
        } } }
       return isValid;
   
   }
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
         isStepValid = false;
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:true});         
       }else{
         $('#wizard').smartWizard('setError',{stepnum:1,iserror:false});
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
          isStepValid = false; 
      
//        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
         $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
     
     //  validate step2
      if(step == 2){
        if(validateStep2() == false ){
          isStepValid = false; 
       
//         $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
         $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
            if(step == 3){
        if(validateStep3() == false ){
          isStepValid = false; 
        
//          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
 if(step == 4){
        if(validateStep4() == false ){
          isStepValid = false; 
//      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
        $('#wizard').smartWizard('setError',{stepnum:step,iserror:true});         
        }else{
          $('#wizard').smartWizard('hideMessage');
          $('#wizard').smartWizard('setError',{stepnum:step,iserror:false});
        }
      }
      
       if(step == 5){
        if(validateStep5() == false ){
          isStepValid = false; 
//       $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please Enter all Values in  step '+step+ ' and click next.</b></font>');
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
      var exist="";
//       alert("here");
        var fname=document.getElementById("fname").value;
        var mname=document.getElementById("mname").value;
        var lname=document.getElementById("lname").value;
        var county=document.getElementById("county").value;
        var district=document.getElementById("district").value;
        var location=document.getElementById("location").value;
        var nationalID=document.getElementById("nationalID").value;
        var mobileNO=document.getElementById("mobileNO").value;
        var gender=document.getElementById("gender").value;
        var dob=document.getElementById("dob").value;
        var marital_status=document.getElementById("maritalStatus").value;
//         alert("id length ; "+parseInt(location.length));
        if(fname="" || parseInt(fname.length)<3){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>First name must have atleast 3 characters.</b></font>');
        isValid=false;    
        }
        else{
        if(lname="" || parseInt(lname.length)<3){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Last name must have atleast 3 characters.</b></font>');
        isValid=false;    
        }
        else{
        if(county.length=="0"){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please choose county.</b></font>');
        isValid=false;    
        }
        else{
        if(district.length=="0"){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please choose district.</b></font>');
        isValid=false;    
        }
        else{
         if(parseInt(location.length)>0 && parseInt(location.length)<3){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please enter location name.</b></font>');
        isValid=false;    
        }
        
        else{
//        if(parseInt(nationalID.length)>0 && parseInt(nationalID.length)<10){
//                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please enter valid national ID number.</b></font>');
//        isValid=true;    
//        }
//        else{
         if(parseInt(mobileNO.length)>0 && parseInt(mobileNO.length)<10){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please enter mobile phone number.</b></font>');
        isValid=false;    
        }
        else{
       if(gender.length=="0"){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please choose gender.</b></font>');
        isValid=false;    
        }
        else{
        if(dob.length=="0"){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please choose date of birth.</b></font>');
        isValid=false;    
        }
        else{
        if(marital_status.length=="0"){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please choose marital status.</b></font>');
        isValid=false;    
        }
    }}}}}}}}
//                }
// dataChecker();
   return isValid;
    }
    
    function validateStep2(){
       var isValid = true;
        
        var employment=$("#employment").val();
        var education=$("#education").val();
        if(employment.length=="0"){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select type of employment. </b></font>');
       isValid = false; 
        }
      else{
        if(education.length=="0"){
                   $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select education level. </b></font>');
      isValid = false; 
            }  
      }   
  return isValid;
   
   }
    
    
    
      function validateStep3(){
       var isValid = true;
        var partner_name=document.getElementById("partner_name").value;
        var linked_to_group=document.getElementById("linked_to_group").value;
        var client_messages=document.getElementById("client_messages").value;
        var group_status=document.getElementById("group_status").value;
        var new_group_name=document.getElementById("new_group_name").value;
        var group_name=document.getElementById("group_name").value;
         
        var provider_status=document.getElementById("provider_status").value;
        var service_provider=document.getElementById("service_provider").value;
        var pfname=document.getElementById("pfname").value;
        var plname=document.getElementById("plname").value;
        var pnationalID=document.getElementById("pnationalID").value;
        var pmobileNO=document.getElementById("pmobileNO").value;
        if(parseInt(partner_name.length)==0){
         $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Select partner name. </b></font>');
           isValid = false;    
        }
        else{
        if(linked_to_group=="yes"){
            if(client_messages===""){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Choose how the client receives messages. </b></font>');
           isValid = false;     
            }
           else{ 
         if(group_status=="yes"){
            if(group_name==""){
                        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Choose a group. </b></font>');
           isValid = false; 
                  } 
         }
         else if(group_status=="no"){
         if(new_group_name=="" || parseInt(new_group_name.length<3)){
                       $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Enter group name. </b></font>');
           isValid = false; 
                  }    
         }
         else{
                       $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Select if a group exist or not. </b></font>');
             isValid = false; 
         }
     }
        }
        else if(linked_to_group=="no"){
            
        }
        else{
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Select if a client is linked to a support group or not. </b></font>');
             isValid = false; 
        }
    }


if(isValid==true){
  if(provider_status=="yes"){
  if(service_provider.length=="0"){
      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Select a service provider. </b></font>');
             isValid = false;  
  }
                  
  }
  else if(provider_status=="no"){
  if(pfname="" || parseInt(pfname.length)<3){
                      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>First name must have atleast 3 characters.</b></font>');
        isValid=false;    
        }
        else{
        if(plname="" || parseInt(plname.length)<3){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Last name must have atleast 3 characters.</b></font>');
        isValid=false;    
        }
//        else{
//         if(parseInt(pnationalID.length)>0 && parseInt(pnationalID.length)<8){
//                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please enter national ID number.</b></font>');
//        isValid=false;    
//        }
        else{
         if(parseInt(pmobileNO.length)>0 && parseInt(pmobileNO.length)<10){
                     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please enter mobile phone number.</b></font>');
        isValid=false;    
        }   
            
        }
//        }
    }
  }
  else{
  $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Choose if the service provider exists in the system or not. </b></font>');
             isValid = false;     
  }
}

  return isValid;
    }  
  

function validateStep4(){
       var isValid = true;
  var year_confirmed=document.getElementById("year_confirmed").value;
  var art=$("#art_status").val();
  var health_facility=document.getElementById("health_facility").value;
  var ccc_no=document.getElementById("ccc_no").value;
  var ccc_check=document.getElementById("existccc").value;
//   alert("length of ART IS : "+art.length);
  if(year_confirmed==""){
     $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Choose year confirmed HIV+. </b></font>');
             isValid = false;   
  }
  else{
  
      if(art.length=="0"){
      $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Choose ART status. </b></font>');
             isValid = false;     
      }
      else{
          if(health_facility.length=="0"){
           $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Choose linked health facility. </b></font>');
             isValid = false;      
          }
          else{
            if(ccc_no.length<6){
                 $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>CCC NO must have 6 to 11 numbers. </b></font>');
             isValid = false;  
            }
        else{
        if(ccc_check.length>0){
        $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Another client exist with this CCC number. </b></font>');
             isValid = false;      
        }    
         } }}}
  return isValid;
}

function validateStep5(){
       var isValid = true;
    
        var registration_date=document.getElementById("registration_date").value;
        var approved_by=document.getElementById("approved_by").value;
        var designation=document.getElementById("designation").value;
        var approval_date=document.getElementById("approval_date").value;
        
        if(registration_date.length==0){
          $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Please select the registration date. </b></font>');
             isValid = false;    
        }
        
        else{
            if(approved_by.lenght<3){
             $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Enter name of approver. </b></font>');
             isValid = false;    
        }
        else{
            
            if(designation.lenght<3){
            $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Enter designation. </b></font>');
             isValid = false;     
            }
            else{
                if(approval_date.length<1){
               $('#wizard').smartWizard('showMessage','<font color=\'red\'><b>Select approval date. </b></font>');
             isValid = false;       
                }
            }
        }
        
            }
        

  return isValid;
}
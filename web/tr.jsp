<link rel="stylesheet" href="chosen/css/chosen.css" type="text/css" media="all"/>

 <script src="chosen/scripts/chosen.jquery.js" type="text/javascript"></script>
    <script>
    jQuery(document).ready(function(){
	jQuery(".chosen").chosen();
        
});
 
    var config = {
      '.chosen-select'           : {},
      '.chosen-select-deselect'  : {allow_single_deselect:true},
      '.chosen-select-no-single' : {disable_search_threshold:10},
      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
      '.chosen-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
  
</script> 
<select name=""  id="title" data-placeholder="Choose an indicator..." class="chosen-select" style="width:350px;" id="choose-form" required>
                                  <option value="">Select Indicator Title</option>
                                  
                                  </select>
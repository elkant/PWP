/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 function caller(){
        calender1 ();
        calender2 ();
        calender3 ();
        calender4 ();
        calender5 ();
        }
function calender1 (){
		new JsDatePick({
			useMode:2,
			target:"#datepicker1",
			dateFormat:"%d-%m-%Y"
		});
	};
        function calender2 (){
		new JsDatePick({
			useMode:2,
			target:"Biodata_form",
			dateFormat:"%d-%m-%Y"
		});
	};
         function calender3 (){
		new JsDatePick({
			useMode:2,
			target:"Exit_form",
			dateFormat:"%d-%m-%Y"
		});
	};
         function calender4 (){
		new JsDatePick({
			useMode:2,
			target:"OLMIS_Version_date",
			dateFormat:"%d-%m-%Y"
		});
	};
         function calender5 (){
		new JsDatePick({
			useMode:2,
			target:"Date_of_submision",
			dateFormat:"%d-%m-%Y"
		});
	};

function add_new()
{	
	var l = document.getElementById("count").value;
	for(var i=1;i<l;i++)
	{
		document.getElementById("e_"+i).removeAttribute("onClick");
	}
	document.getElementById("addnew").removeAttribute("onClick");
	$('#my_table > tbody:last').after('<tr><td width= "21%"><input type="text" id="new_value_1" name="new_value_1"  size= "12" style="border:none "/></td><td width= "21%"><input type="text" style="border:none " id="new_value_2" name="new_value_2" size= "10"/></td><td width= "20%"><input type="text" style="border:none " id="new_value_3" name="new_value_3" size= "8"/></td><td width= "7%"><select name="new_value_4" id="new_value_4"></select></td><td width= "6%"><img src="save.jpg" height="32" width="32" value = "s" onclick= "s_add()" alt="button" title = "Save"></td><td width="6%"><img src="del.png" height="37" width="37"value="cancel" title = "Cancel" onClick="window.location.reload()"/></td></tr>');
	$('#inst option').clone().appendTo('#new_value_4');
	//document.getElementById("new_value_1").value = count;
	document.getElementById("new_value_1").focus();	
}
function s_add()
{
	document.getElementById("hid").value = "2";
	if(document.getElementById("new_value_1").value== "")
	{
		alert("Please Enter the Department ID!!!");
		return false;
	}
	else if(document.getElementById("new_value_2").value== "")
	{
		alert("Please Enter the Department Name!!!");
		return false;
	}
	else if(document.getElementById("new_value_3").value== "")
	{
		alert("Please Enter the HOD !!!");
		return false;
	}
	document.forms["my_form"].submit();
}

function edit_values(n)
{
//	alert("dept");
	var l = document.getElementById("count").value;
	for(var i=1;i<l;i++)
	{
		document.getElementById("e_"+i).removeAttribute("onClick");
	}
	document.getElementById("addnew").removeAttribute("onClick");
	$('#my_table tr#check_'+n).append('<td width = "1%"><img src="save.jpg" height="32" width="32" value = "s" onclick= "save_values()" alt="button" title = "Save"></td>');
	///document.getElementById("s_edit").disabled = false;
	
	var d_id =  $('tr#check_'+n).find('td#td1_'+n).html();
	var d_n =  $('tr#check_'+n).find('td#td2_'+n).html();
	var h =  $('tr#check_'+n).find('td#td3_'+n).html();
	var ins_id =  $('tr#check_'+n).find('td#td4_'+n).html();
	//alert(d_id);
	$('#check_'+n+ ' td#td1_'+n).empty();
	$('tr#check_'+n+ ' td#td1_'+n).append('<input type="text" id="text5" name = "edit_txt1"  size= "12" style = "border:none; width:70px;" readonly/>');
	document.getElementById("text5").value = d_id;
	$('#check_'+n+ ' td#td2_'+n).empty();
	$('tr#check_'+n+ ' td#td2_'+n).append('<input type="text" id="text6" name = "edit_txt2" size= "10" style = "border:none; width:100px;"/>');
	document.getElementById("text6").value = d_n;
	document.getElementById("text6").focus();
	$('#check_'+n+ ' td#td3_'+n).empty();
	$('tr#check_'+n+ ' td#td3_'+n).append('<input type="text" id="text7" size= "8" name = "edit_txt3" style = "border:none; width:100px;"/>');
	document.getElementById("text7").value = h;
	$('#check_'+n+ ' td#td4_'+n).empty();
$('tr#check_'+n+ ' td#td4_'+n).append('<select name="edit_txt4" id="text8" style = "width:90px;"></select>');
	
	$('#inst option').clone().appendTo('#text8');
}
function save_values()
{
	document.getElementById("hid").value = "1";
	if(document.getElementById("text6").value== "")
	{
		alert("Please Enter the Department Name!!!");
		return false;
	}
	else if(document.getElementById("text7").value== "")
	{
		alert("Please Enter the HOD!!!");
		return false;
	}
	document.forms["my_form"].submit();
}
function delete_values(a){
	document.getElementById("hid").value = "3" ;
	var where_to= confirm("Do you really want to delete?");
	{
		if (where_to== true)
		 {
			
			alert("Record Deleted Successfully !!!");
			
		 }
		else
			{
			return false;
			}
		}
	
    var d_id =  $('tr#check_'+a).find('td#td1_'+a).html();
    var d_name =  $('tr#check_'+a).find('td#td2_'+a).html();
    var HOD =  $('tr#check_'+a).find('td#td3_'+a).html();
    var i_id =  $('tr#check_'+a).find('td#td4_'+a).html();
    
    
    
    document.getElementById("hid1").value = d_id;
    $('tr#check_'+a).remove();
    document.forms["my_form"].submit();
   
    
    
    }
function search_result()
{
	//alert("hello");
	var search=document.getElementById("search_box").value;
	if(search=="")
	{
	alert("enter the name!!");
	return false;
	}
	$("#my_table").remove();
	document.getElementById("addnew").style.visibility='hidden';
	var xmlhttp;
	if (window.XMLHttpRequest)
	  { 
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("wrapper").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("GET","search.jsp?search="+search+"&type=dept",true);
	xmlhttp.send();
}
function submitListApp() {
    
    var data = BlenderItToAjaxRequest($('#listAppID'));
    $('#messagesID').css('background', 'lightgrey');
    $('#messagesID').html("Loading applications list... please wait...");
    
    $.ajax({
	url : 'loadWLListApps',
	type : 'POST',
	contentType : 'application/json',
        data : data,
	success : function(resp) {
	    if (resp.success == false) {
		$('#messagesID').css('background', '#ff6666');
	    } else {
		$('#messagesID').css('background', 'lightgreen');
		populateAppList(resp);
	    }
	    $('#messagesID').html(resp.message);
	},
	error : function(e) {
	    $('#messagesID').html(e.status + ":" + e.statusText);
	}
    });

}

function populateAppList(resp){
    
    $('#pageBodyID').html("");
    
    for (idx in resp.apps) {
	$('#pageBodyID').append(resp.apps[idx].name+";"+resp.apps[idx].path+";");
	for (targetIdx in resp.apps[idx].targets){
	    $('#pageBodyID').append("["+resp.apps[idx].targets[targetIdx]+"]");
	}
	 $('#pageBodyID').append("<br>");
    }
    
    
}
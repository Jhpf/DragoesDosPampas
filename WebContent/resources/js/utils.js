function ConvertFormToJSON(form) {
	var array = jQuery(form).serializeArray();
	var json = {};

	jQuery.each(array, function() {
		json[this.name] = this.value || '';
	});
	return json;
}

function BlenderItToAjaxRequest(form){
    return  JSON.stringify(ConvertFormToJSON(form));
}
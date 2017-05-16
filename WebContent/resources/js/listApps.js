var ListAppsPage = JClass._extend({
    init: function (main) {
    	var that = this;
        this._parent = main;
        this.container = '#dialog-form';
        this.popup = null;
        this.dialog = null;
        this._page = {
			wrapper: $('#wrapper'),
			container: $('#wrapper').find('#container'),
			form: $('#wrapper').find('#container').find('#listAppID'),
			list: $('#wrapper').find('#container').find('#pageBodyID').find('ul'),
			selectApp: $('#wrapper').find('#container').find('#pageBodyID').find('ul').find('a.selectApp'),
			formContainer: $('#wrapper').find('#container').find('#div_form'),
			// file: $(link).parent().parent().find('.div_right input.listAppsFile'),
			formStructure: {
				user: $('[id$=""]'),
				pass: $('[id$=""]'),
				submit: $('[id$="a_Submit"]')
			}	
        };
		this.initComponents();
    },
    initComponents: function () {
        var that = this;
		this._page.formStructure.submit.click(function(){
			that.submitListApp();
		});
		
		this.enableLinkListClick();
    },
    initComponentsLoaded: function(){
    	var that = this;
    	
    	this._page.formContainer.hide();
    	
    	this.enableLinkListClick();
		
    	$(this._page.list.find('a.selectApp')).on('click', function(){
			that.selectApp($(this));
		});
    	
    	$('.targets_chks').prop('disabled', true);
    	
    	$('.chk_All_Sel').on('click', function(){
    		if(!$(this).is(':checked')) {
    			$(this).siblings('.targets_chks').prop('disabled', false);
    			$(this).siblings('.targets_chks').prop('checked', false);
    		} else {
				$(this).siblings('.targets_chks').prop('disabled', true);
				$(this).siblings('.targets_chks').prop('checked', true	);
			}
    	})
    },
	enableLinkListClick: function(){
		$(this._page.list.find('a.links_list')).on('click', function(){
			if($('#div_' + $(this).data('div')).is(':visible')){
				$('#div_' + $(this).data('div')).hide();
			} else {
				// $('.div_listApps').hide();
				$('#div_' + $(this).data('div')).show();
			}
				
		});
		// this.enableDvListClick();
	},
	enableDvListClick: function(){
		/*
		 * TODO:: NEED TO CHECK THIS FUNCTION TO AVOID RAISE THIS METHOD WHEN CLICK ON THE "SELECT" LINK 
		 */
		$(this._page.list.find('div.div_listApps')).on('click', function(){
			if($(this).is(':visible'))
				$(this).hide();
			else
				$(this).show();
				
		});
	},
	submitListApp: function() {
		var that = this;
		
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
					that.populateAppList(resp);
				}
				$('#messagesID').html(resp.message);
			},
			error : function(e) {
				$('#messagesID').html(e.status + ":" + e.statusText);
			}
		});
	},
	populateAppList: function(resp){
	   var that = this;
	   
	   this.resetPageBody();
	   
	   var element = null;
	   for(var idx=0;idx<resp.apps.length;idx++) {
		   
		   element = that.createListObject(resp.apps[idx]);
		   
		   that._page.list.append(element);
		   
		   //$('#pageBodyID').append(resp.apps[idx].name+";"+resp.apps[idx].path+";"+resp.apps[idx].targets+"<br>");
	   }
	   
	   this.initComponentsLoaded();
	},
	createListObject: function(listItem){
		var strReturn = '';
		
		strReturn = '<li><a href="javascript:void(0);" class="links_list" data-div="' + listItem.name + '">' + listItem.name + '</a>';
			strReturn += '<div id="div_' + listItem.name + '" class="div_listApps">';
				strReturn += '<div class="div_left">'; 
					strReturn += '<a class="selectApp" href="javascript:void(0);" data-name="' + listItem.name + '" data-target="' + listItem.targets + '" data-path="' + listItem.path + '"></a>';
				strReturn += '</div>'; 
				strReturn += '<div class="div_right">'; 
					strReturn += '<p><span class="labelListApps">PATH: </span>' + listItem.path + '</p>';
					
					strReturn += '<div class="div_radios_targets">';
					
						strReturn += '<input id="chk_sl_all_targets_' + listItem.name + '" class="chk_All_Sel" type="checkbox" checked><label for="chk_sl_all_targets_' + listItem.name + '">Select all</label></input>';
					
						var targets = listItem.targets.split(',');
					
						for(var i=0;i<targets.length;i++){
						
							strReturn += '<input id="chk_targets_' + targets[i] + '" type="checkbox" class="targets_chks" checked data-target="' + targets[i] + '"><label for="chk_targets_' + targets[i] + '">' + targets[i] + '</label></input>';
						
						}
						
					strReturn += '</div>';							
					strReturn += '<input type="file" name="listAppsFile" class="listAppsFile">';
				strReturn += '</div>'; 
			strReturn += '</div>';
		strReturn += '</li>';
		
		return strReturn;
	},
	resetPageBody: function(){
		this._page.list.empty();	
	},
	selectApp: function(link){
		var targestArr = new Array();
		
		for(var i=0; i<$(link).data('target').split(',').length;i++){
			var check = $('#chk_targets_' + $(link).data('target').split(',')[i]);
			if(check.is(':checked'))
				targestArr.push(check.data('target'));
		}
		
		$.ajax({
			url : 'DeployApp',
			type : 'POST',
			contentType : 'application/json',
			data : {app: link.data('name'), targets: JSON.stringify(targestArr), path: link.data('path'), file: $(link).parent().parent().find('.div_right input.listAppsFile').val()},
			success : function(resp) {
				console.log(1);
			},
			error : function(e) {
				console.log(2);
			}
		});
	}
});

$( document ).ready(function() {
	var listApps = new Page(1);
});
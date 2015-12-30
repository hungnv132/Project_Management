(function ( $ ) {
    $.fn.mytag = function(options) {
    	
        var settings = $.extend({
        	input_name:"tagtag",
        	btn_name:"Add",
            tag_color: "black",
            tag_bgColor: "#d6e3ef",
            split_string: "#"
        }, options );
    	
    	
    	var new_tag_form_component = '<input type="text" class="form-control new-tag-form" />';
		var btn_add_tag_component = '<input type="button" class="btn  btn-add-tag" value="'+ settings.btn_name +'"/>';
		var tag_input_component  = '<input type="hidden" class="form-control tag-input"  name="'+ settings.input_name +'"/>';
		var tag_area_component  = '<div class="tag-area"></div>';
		var tag_modify_component = '<span class="tag-modify"><i class="icon ion-edit"></i> <i class="icon ion-close-circled"></i> </span>';
		var thisTagBox = $(this);
		var tagArray = new Array();
		var editTag = false;
		var tagToEdit = "";
		
		this.append(btn_add_tag_component).append(new_tag_form_component).append(tag_input_component).append(tag_area_component);
    	this.addClass('tag-box');
    	
    	thisTagBox.find(".new-tag-form").hide().attr("disabled",true);
    	
    	thisTagBox.on("click",".btn-add-tag",function(){
    		thisTagBox.find(".new-tag-form").show().removeAttr("disabled").focus();
    		
		});
    	
    	thisTagBox.find(".new-tag-form").on("blur",function(){
			var newTagValue = $(this).val().trim();
			if (tagArray.indexOf(newTagValue) == -1 && newTagValue.length != 0) {
				if (editTag == true) {// edit tag
					editTagFromArray(tagToEdit.text(),newTagValue);
					tagToEdit.text(newTagValue);
					thisTagBox.find(".tag-input").val(getTagFromArray());
				}else{//create new tag
					thisTagBox.find(".tag-area").append('<span class="tag"><span class="tag-value">'+newTagValue+'</span>' + tag_modify_component +'</span>');
					addTagToArray(newTagValue);
					thisTagBox.find(".tag-input").val(getTagFromArray());
				}
				thisTagBox.find(".tag").css({"background-color":settings.tag_bgColor, "color":settings.tag_color});
			}
			$(this).hide().attr("disabled", true).val("");
			
    		
			
    	});
    	
    	thisTagBox.find(".new-tag-form").keypress(function (e) {
			var key = e.which;
			if(key == 13){  // the enter key code
				e.preventDefault();
				var newTagValue = $(this).val().trim();
				if (tagArray.indexOf(newTagValue) == -1 && newTagValue.length != 0) {
					if (editTag == true) {// edit tag
						editTagFromArray(tagToEdit.text(),newTagValue);
						tagToEdit.text(newTagValue);
						thisTagBox.find(".tag-input").val(getTagFromArray());
					}else{//create new tag
						thisTagBox.find(".tag-area").append('<span class="tag"><span class="tag-value">'+newTagValue+'</span>' + tag_modify_component +'</span>');
						addTagToArray(newTagValue);
						thisTagBox.find(".tag-input").val(getTagFromArray());
					}
					thisTagBox.find(".tag").css({"background-color":settings.tag_bgColor, "color":settings.tag_color});
				}
				$(this).show().focus().val(""); 
				return false;
			  }
			});   
    	
    	
    	
    	thisTagBox.on("click","i.ion-edit", function(){
			editTag = true;
			tagToEdit =$(this).parent().siblings(".tag-value");
			oldTagValue = tagToEdit.text().trim();
			thisTagBox.find('.new-tag-form').show().removeAttr("disabled").focus().val(oldTagValue);
		});
	
    	thisTagBox.on("click","i.ion-close-circled", function(){
			 var removeValue = $(this).parent().siblings(".tag-value").text().trim();
			 removeTagFromArray(removeValue);
			 thisTagBox.find(".tag-input").val(getTagFromArray());
			 $(this).parent().parent().remove();
		});
    	
    	
    /*	var addNewTagFunction = function(tagValue, tagArea, tagInput){
			var newTagValue = tagValue.trim();
			var numberOfTag = tagArray.length +1;
			tagArea.append('<span class="tag"><span class="tag-value">'+newTagValue+'</span>' + tag_modify_component +'</span>');
			addTagToArray(newTagValue, tagArray	);
			tagInput.val(getTagFromArray(tagArray));
			
		}*/
		var editTagFromArray= function(oldTagValue, newTagValue){
			editTag = false;
			addTagToArray(newTagValue,tagArray.indexOf(oldTagValue));
		}
		
		var addTagToArray = function(newTagValue, position){
			if (position === undefined || position === null) {
				tagArray.splice(tagArray.length, 1, newTagValue);
			}else{
				console.log("position: "+ position);
				tagArray.splice(position, 1, newTagValue);
			}
		}
		
		var removeTagFromArray = function(removeValue){
			var removeIndex= tagArray.indexOf(removeValue);
			tagArray.splice(removeIndex, 1);
			
		}
		var getTagFromArray= function(){
			var tagValueString = "";
			 for ( var index in tagArray) {
				 tagValueString= tagValueString + tagArray[index] + settings.split_string;
			}
			return 	tagValueString;
		}
    };
 
}( jQuery ));
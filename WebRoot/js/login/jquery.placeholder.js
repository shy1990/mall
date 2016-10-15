
	$(function() {
			var changeToPInput = function(input) {
			var temp = $('<input type="password" id="password"  name="password" class="password main_logo_input_zi" />');
			var input = $(input);
			var parent = input.parent();
			input.remove();
			parent.append(temp);
			temp.focus().select().on("mouseleave", function(e) {
				changeToTInput(e.target);
			});
		};

		var changeToTInput = function(input) {
			if ($(input).val() == '') {
				var temp = $('<input type="text" value="密码" id="password" name="password" class="password main_logo_input_zi" />');
				var input = $(input);
				var parent = input.parent();
				input.remove();
				parent.append(temp);
				temp.blur().on("mouseenter", function(e) {
					changeToPInput(e.target);
				});
				
			};
		};

		$(".password").on("mouseenter", function(e) {
			changeToPInput(e.target);
		});
			
		$("#username").on("keydown",function(event) {
			     if(event.keyCode == 9){
			    	$(".password").one("focus", function(e){
			    		changeToPInput(e.target);
			    	});
			      }
				});
			
		
  });
	
	

$(function() {

	function loadUsers(query) {
		$.get('search?query=' + query).then(function(data) {
			var result = '';
			for (var index = 0; index < data.length; index++) {
				var user = data[index];
				result += "<option>" + user.firstName;
				+"</option>";
			}
			$('#users_list').html(result);
		});
	}

	$('input').on('keyup', function() {
		var text = this.value;
		loadUsers(text);
	});
	loadUsers('');
});
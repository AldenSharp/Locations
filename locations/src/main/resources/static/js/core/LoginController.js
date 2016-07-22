angular.module('app').controller('LoginController', ['$window', 'LoginService', function($window, LoginService) {
	
	let ctrl = this;
	
	ctrl.output = '';
	
	ctrl.login = function(username, password) {
		LoginService.login(username, password).then(function(response) {
			
			let user = response.data;
			
			LoginService.setUsername(user.name);
			
			if (user.type === "admin" || user.type === "user") {
				LoginService.security = user.type;
				$window.location = "#/" + user.type + "/" + user.name;
			} else {
				LoginService.security = "unregistered";
				ctrl.output = 'Error: incorrect username or password.';
			}
			
		});
	}
	
}]);
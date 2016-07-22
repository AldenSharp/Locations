angular.module('app').controller('LoginHitController', ['LoginHitService', 'LoginService', '$routeParams', '$window', 
function LoginHitController(LoginHitService, LoginService, $routeParams, $window) {
	
	let ctrl = this;
	
	ctrl.output = '';
	ctrl.num = $routeParams.num;
	
	LoginHitService.setNum(ctrl.num);
	
	LoginHitService
		.incrementHit()
		.then(function(response) {
			ctrl.hit = response.data.count;
		});
	
	// Log in.
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
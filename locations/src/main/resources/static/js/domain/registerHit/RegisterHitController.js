angular.module('app').controller('RegisterHitController', ['LoginHitService', 'LoginService', '$routeParams',
function RegisterHitController(LoginHitService, LoginService, $routeParams) {
	
	let ctrl = this;
	
	ctrl.output = '';
	ctrl.link = '';
	ctrl.num = $routeParams.num;
	
	// Register.
	ctrl.register = function(username, password, checkPassword) {
		if(password === checkPassword) {
			LoginService.register(username, password, ctrl.num).then(function(response) {
				if(response.data.error) {
					console.log(response.data.info);
					ctrl.output = response.data.info;
					ctrl.link = '';
				} else {
					console.log(response.data.info);
					ctrl.output = 'User ' + username + ' registered! ';
					ctrl.link = 'Log in.';
				}
			});
		} else {
			console.log('Error: Passwords do not match.');
			ctrl.output = 'Error: Passwords do not match.';
			ctrl.link = '';
		}
	}
	
}]);
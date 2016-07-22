angular.module('app').controller('RegisterController', ['LoginService', 
function RegisterController(LoginService) {
	
	let ctrl = this;
	
	ctrl.output = '';
	ctrl.link = '';
	
	// Register.
	ctrl.register = function(username, password, checkPassword) {
		if(password === checkPassword) {
			LoginService.register(username, password).then(function(response) {
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
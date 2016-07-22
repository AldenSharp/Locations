angular.module('app').controller('UserController', ['UserService', '$routeParams', '$window', 'LoginService',
function UserController(UserService, $routeParams, $window, LoginService) {

	let ctrl = this;
	
	// Path objects.
	ctrl.name = $routeParams.name;
	
	// Security.
	if(LoginService.security !== 'user' || LoginService.username !== ctrl.name) {
		$window.location = '#/login';
	}
	
	// Panels.
	ctrl.tab = 1;
	ctrl.selectTab = function(setTab) {
		ctrl.tab = setTab;
	};
	ctrl.isSelected = function(checkTab) {
		return ctrl.tab === checkTab;
	}
	
	// Log out.
	ctrl.logout = function() {
		UserService.logout(ctrl.name).then(function() {
			$window.location = '#/login';
		});
	}
	
}]);
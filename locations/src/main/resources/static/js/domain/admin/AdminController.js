angular.module('app').controller('AdminController', ['AdminService', 'UserService', '$routeParams', '$location', '$window', 'LoginService',
function AdminController(AdminService, UserService, $routeParams, $location, $window, LoginService) {
	
	let ctrl = this;
	
	// Path objects.
	ctrl.absPath = $location.absUrl();
	ctrl.path = $location.url();
	ctrl.name = $routeParams.name;
	
	// Security.
	if(LoginService.security !== 'admin' || LoginService.username !== ctrl.name) {
		$window.location = '#/login';
	}
	
	// Hit setter.
	ctrl.setCounts = function(data) {
		data.forEach(function(object, index) {
			if(isNaN(object.conversionRate)) {
				object.conversionRate = '(No users)';
			} else {
				object.conversionRate = (object.conversionRate * 100).toString() + '%';
			}
		});
		ctrl.counts = data;
	}
	
	// Get hit object.
	AdminService.getHits().then(function(response) {
		ctrl.setCounts(response.data);
	});
	
	ctrl.getPath = (num) => '/login/' + num;
	
	// Create new hit object.
	ctrl.response = '';
	ctrl.newUrl = function(label) {
		AdminService.newUrl(label)
		.then(function(response) {
			ctrl.setCounts(response.data);
		});
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
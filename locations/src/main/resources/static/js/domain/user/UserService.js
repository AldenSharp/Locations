angular.module('app').service('UserService', function($http){

	let url = 'user/';
	
	let svc = this;
	
	svc.logout = function(username) {
		return $http.post('user/logout/' + username);
	}
	
});
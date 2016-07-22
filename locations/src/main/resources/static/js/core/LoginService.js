angular.module('app').service('LoginService', ['$http', function($http) {
	
	let svc = this;
	
	svc.username = "";
	
	svc.setUsername = function(name) {
		svc.username = name;
	}
	
	svc.login = function(username, password) {
		let appUser = {
				"name": username,
				"password": password
		};
		return $http.post('user/login', appUser);
	}
	
	svc.register = function(username, password, num) {
		let user = {
				"name": username,
				"password": password,
				"num": num,
				"type": 'user'
		};
		return $http.post('user/register', user);
	}
	
	svc.security = 'unregistered';
	
}]);
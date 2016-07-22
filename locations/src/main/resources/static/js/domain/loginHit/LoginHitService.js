angular.module('app').service('LoginHitService', ['$http', function($http) {
	
	let svc = this;
	
	svc.num = 0;
	
	svc.setNum = function(n) {
		svc.num = n;
	}

	svc.incrementHit = () => $http.post('location/increment/' + svc.num)
		.then( () => $http.get('location/anonymous/' + svc.num));
	
}]);
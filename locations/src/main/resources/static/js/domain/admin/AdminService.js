angular.module('app').service('AdminService', ['$http', function($http) {

	let url = 'admin/';
	
	let svc = this;
	
	svc.getHits = function() {
		return $http.get('location/nums');
	}
	
	svc.newUrl = function(label) {
		let appArea = {
				"label": label
		};
		$http.post('location/new', appArea);
		return $http.get('location/nums');
	}

	
}]);
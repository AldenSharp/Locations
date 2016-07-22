angular.module('app').config(['$routeProvider', 'baseRoute', 'homePage',
    function config($routeProvider, baseRoute, homePage) {

      $routeProvider.
      	when('/login', {
          templateUrl: homePage,
          controller: 'LoginController',
          controllerAs: 'ctrl'
        }).
        when('/login/:num', {
          templateUrl: baseRoute + '/loginHit/loginHitTemplate.html',
          controller: 'LoginHitController',
          controllerAs: 'ctrl'
        }).
        when('/register', {
          templateUrl: baseRoute + '/register/registerTemplate.html',
          controller: 'RegisterController',
          controllerAs: 'ctrl'
        }).
        when('/register/:num', {
          templateUrl: baseRoute + '/registerHit/registerHitTemplate.html',
          controller: 'RegisterHitController',
          controllerAs: 'ctrl'
        }).
      	when('/admin/:name', {
          templateUrl: baseRoute + '/admin/adminTemplate.html',
          controller: 'AdminController',
          controllerAs: 'ctrl'
        }).
        when('/user/:name', {
          templateUrl: baseRoute + '/user/userTemplate.html',
          controller: 'UserController',
          controllerAs: 'ctrl'
        }).
        otherwise('/login');
    }
  ]);
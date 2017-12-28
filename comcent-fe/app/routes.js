/**
  Paths
*/

let PATHS = {
  ROOT: '/',
  LOGIN: '/login',
  DASHBOARD: '/dashboard'
}

angular.module('app').constant('PATHS', PATHS)

/**
  Routing configuration
*/

angular.module('app').config(['$routeProvider', 'PATHS', function($routeProvider, PATHS) {
  $routeProvider
    .when(PATHS.LOGIN, {
      template: '<login></login>'
    })
    .when(PATHS.DASHBOARD, {
      template: '<dashboard></dashboard>'
    })
    .otherwise({
      redirectTo: PATHS.DASHBOARD
    });
}]);

/**
  Intercept route change
*/

angular.module('app').run(function($rootScope, GlobalEventService, EVENTS) {

  function manageLogin() {
    $rootScope.$broadcast(EVENTS.CHECK_LOGIN);
  }
  manageLogin();

  $rootScope.$on("$routeChangeStart", function(event, next, current) {
    manageLogin();
  });

});

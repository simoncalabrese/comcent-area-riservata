angular.module('app').service('GlobalEventService', ['$log', '$rootScope', 'AuthService', 'RedirectService', 'ToastService','EVENTS',
  function($log, $rootScope, AuthService, RedirectService, ToastService, EVENTS) {

    $svc = this;

    $rootScope.$on(EVENTS.LOGIN_SUCCESSFUL, function() {
      RedirectService.redirectToHome();
    });

    $rootScope.$on(EVENTS.NOT_LOGGED_IN, function() {
      RedirectService.redirectToLogin();
    });

    $rootScope.$on(EVENTS.CHECK_LOGIN, function() {
      if (!AuthService.isLoggedIn()) $rootScope.$broadcast(EVENTS.NOT_LOGGED_IN);
    });

    $rootScope.$on(EVENTS.LOGOUT_SUCCESSFUL, function() {
      $rootScope.$broadcast(EVENTS.NOT_LOGGED_IN);
    });

    $rootScope.$on(EVENTS.CHECK_HTTP_MESSAGES, function(event, data) {
      if (data && _.isObject(data) && _.isObject(data.data) && _.isArray(data.data.messages)) {
        _.each(data.data.messages, function(message) {
          ToastService.showError(message.codErrore + " - " + message.desErrore);
        });
      }
    });
  }
])
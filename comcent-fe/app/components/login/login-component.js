angular.module('app').component('login', {
  templateUrl: window.PATH_PREFIX + './app/components/login/login-template.html',
  bindings: {},
  controller: ('loginController', ['$log', '$rootScope', 'AuthService', 'ToastService', 'EVENTS', function($log, $rootScope, AuthService, ToastService, EVENTS) {

    var $ctrl = this;

    $ctrl.$onInit = function() {}

    $ctrl.login = function() {
      let loginRequest = new LoginDTO($ctrl.email, $ctrl.password);
      AuthService.login(loginRequest, function(result) {
        // Niente al momento.
      });
    }

  }])
});

angular.module('app').service('RedirectService', ['$location', 'PATHS', function($location, PATHS) {


  $svc = this;

  $svc.redirectToLogin = redirectToLogin;
  $svc.redirectToHome = redirectToHome;


  function redirectToLogin() {
    redirect(PATHS.LOGIN);
  }

  function redirectToHome() {
    redirect(PATHS.ROOT);
  }

  /* Private */

  function redirect(path) {
    $location.path(path);
  }

}])

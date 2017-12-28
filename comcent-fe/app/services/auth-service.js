angular.module('app').service('AuthService', ['_', '$log', '$rootScope', '$cookies', '$http', '$q', 'ApiService', function(_, $log, $rootScope, $cookies, $http, $q, ApiService) {

  const USER_KEY = 'user';
  const EXPIRATION_KEY = 'exp';
  const SESSION_DURATION = 3600; // SECONDS

  $svc = this;

  $svc.login = login;
  $svc.logout = logout;
  $svc.getUser = getUser;
  $svc.isLoggedIn = isLoggedIn;
  $svc.updateExpirationTime = updateExpirationTime;
  $svc.getLinks = getLinks;
  $svc.delDoc = delDoc;
  $svc.addDoc = addDoc;

  function login(user, callback) {

    $http.post(ApiService.getLoginURL(), user).then(function(response) {
      if (response && response.data && _.isNumber(response.data.id)) {
        $log.log('Login successful!');
        $cookies.putObject(USER_KEY, response.data);
        updateExpirationTime();
        $rootScope.$broadcast(EVENTS.LOGIN_SUCCESSFUL);
        (callback || angular.noop)(true);
      } else {
        (callback || angular.noop)(false);
      }
    });

  }

  function logout() {
    // $cookies.remove(USER_KEY);
    // $cookies.remove(EXPIRATION_KEY);
    $cookies.putObject(USER_KEY, null);
    $cookies.put(EXPIRATION_KEY, null);
    $rootScope.$broadcast(EVENTS.LOGOUT_SUCCESSFUL)
  }

  function getUser() {
    return $cookies.getObject(USER_KEY);
  }

  function isLoggedIn() {
    let logged = !!$cookies.get(USER_KEY) && (Date.now() < $cookies.get(EXPIRATION_KEY));
    if(logged) updateExpirationTime();
    return logged;
  }

  function updateExpirationTime() {
    $cookies.put(EXPIRATION_KEY, getUpdatedExpirationTime());
  }

  /* PRIVATE */

  function getUpdatedExpirationTime() {
    return Date.now() + getSessionDurationInMs();
  }

  function getSessionDurationInMs() {
    return SESSION_DURATION * 1000;
  }

  function getLinks(callback) {
    $http.post(ApiService.getLinks(),{}).then(function(response) {
        (callback || angular.noop)(response.data);
    });
  }

  function delDoc(name,callback) {
    $http({
          method: 'GET',
          url: ApiService.delDoc(),
          params: {name: name}
        }).then(function(response){
            (callback)();
        })
  }

  function addDoc(link,callback) {
    $http.post(ApiService.addDoc(),link).then(function(response) {
            (callback || angular.noop)();
        });
  }

}])

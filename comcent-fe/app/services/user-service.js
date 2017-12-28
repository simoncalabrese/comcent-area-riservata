angular.module('app').service('UserService', ['_', '$http', 'ApiService', function(_, $http, ApiService) {

  $svc = this;
  $svc.getUsers = getUsers;

  function getUsers(userId, callback) {
    $http({
      method: 'GET',
      url: ApiService.getUsersURL(),
      params: {userId: userId}
    }).then(function (response) {
      if (_.isObject(response) && _.isObject(response.data)) {
        (callback || angular.noop)(response.data);
      } else {
        (callback || angular.noop)({});
        // TODO fare qualcosa?
      }
    });
  }

  $svc.addUser = function addUser(user,callback) {
    $http.post(ApiService.signUp(),user).then(function(response) {
        (callback || angular.noop)(response.data);
    })
  }

}])

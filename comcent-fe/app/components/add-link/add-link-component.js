angular.module('app').component('addLink', {
  templateUrl: window.PATH_PREFIX + './app/components/add-user/add-link-template.html',
  bindings: {
    resolve: '<',
    close: '&',
    dismiss: '&'
  },
  controller: ('addUserController', ['$scope', '$rootScope', 'AuthService', 'ApiService', function($scope, $rootScope, AuthService, ApiService) {

    var $ctrl = this;
    $ctrl.users = [];
    $ctrl.addDoc = addDoc;
    $ctrl.$onInit = function() {

    }

    $ctrl.addDoc = function() {
        let link = {
         name: $ctrl.name,
         link:$ctrl.link
        };
        AuthService.addDoc(link,$ctrl.close({$value: ApiService.STATUS_OK}););
    }

    /* */

    function manageGetUsers(result) {
      $ctrl.users = result;
    }



  }])
});

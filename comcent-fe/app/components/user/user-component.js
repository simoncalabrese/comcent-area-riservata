angular.module('app').component('user', {
  templateUrl: window.PATH_PREFIX + './app/components/user/user-template.html',
  bindings: {},
  controller: ('loginController', ['$log', '$rootScope', 'AuthService', 'ModalService','$window', function($log, $rootScope, AuthService, ModalService,$window) {

    var $ctrl = this;
    $ctrl.user = {};
    $ctrl.listLink = [];
    $ctrl.name = null;
    $ctrl.link = null;

    $ctrl.$onInit = function () {
      $ctrl.user = AuthService.getUser();
      $ctrl.getAllLink();
    }

    $ctrl.addUser = function () {
      ModalService.openAddUserModal($ctrl.user.id, manageUserAdded);
    }

    $ctrl.logout = function() {
      AuthService.logout();
    };

    $ctrl.delDoc = function(name) {
        AuthService.delDoc(name,$ctrl.getAllLink)
    }

    $ctrl.addDoc = function addDoc() {
        let doc = {
            name: $ctrl.name,
            url: $ctrl.link
        };
        AuthService.addDoc(doc,function(){
            $ctrl.getAllLink();
            $ctrl.name = null;
            $ctrl.link = null;
        });
    }

    function manageUserAdded() {
      // Nope al momento
    }

    $ctrl.getAllLink = function getAllLink() {
        AuthService.getLinks(manageLinks);
    }

    function manageLinks(data) {
        $ctrl.listLink = data;
    }

    $ctrl.redirectUrl = function(url) {
        ModalService.modalLink('http://' + url, manageUserAdded);
    }
  }])
});

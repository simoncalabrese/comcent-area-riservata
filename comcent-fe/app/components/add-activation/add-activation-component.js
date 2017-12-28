angular.module('app').component('addActivation', {
  templateUrl: window.PATH_PREFIX + './app/components/add-activation/add-activation-template.html',
  bindings: {
    resolve: '<',
    close: '&',
    dismiss: '&'
  },
  controller: ('addActivationController', ['$scope', '$rootScope', 'DateService', 'ActivasionsService', 'ApiService', function($scope, $rootScope, DateService, ActivasionsService, ApiService) {

    var $ctrl = this;

    $ctrl.$onInit = function() {
      $ctrl.date = new Date();
    }

    $ctrl.add = function() {
      ActivasionsService.addActivation($ctrl.resolve.userId, $ctrl.date, $ctrl.amount, $ctrl.description,$ctrl.resolve.userInsert, manageAddedActivation);
    }

    $scope.dateOptions = {
      dateDisabled: false,
      maxDate: new Date(),
      startingDay: 1
    };

    $scope.format = DateService.DEFAULT_FORMAT;

    /* */

    function manageAddedActivation(result) {
      if(result.status == 200) {
        console.log("Activation added.");
        $ctrl.close({$value: ApiService.STATUS_OK});
      }
    }

  }])
});

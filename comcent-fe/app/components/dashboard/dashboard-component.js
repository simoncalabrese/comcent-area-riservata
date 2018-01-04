angular.module('app').component('dashboard', {
  templateUrl: window.PATH_PREFIX + './app/components/dashboard/dashboard-template.html',
  bindings: {},
  controller: ('loginController', ['$scope', '$rootScope', 'AuthService', 'ActivasionsService', 'DateService', 'ModalService','_', function($scope, $rootScope, AuthService, ActivasionsService, DateService, ModalService,_) {

    var $ctrl = this;
    $ctrl.user = null;
    $ctrl.getActivations = null;

    $ctrl.$onInit = function() {
      $ctrl.user = AuthService.getUser();
      $ctrl.to = new Date();
      $ctrl.from = new Date();
      $ctrl.from = $ctrl.from.setMonth($ctrl.from.getMonth() - 1);
      $scope.panel = {};

      getActivations();
    };

    $ctrl.logout = function() {
      AuthService.logout();
    };

    /**
    La gestione completa dell'aggiunta del plafont è effettuata nella componente.
    Qui si aggiorna solo la lista alla chiusura del modal.
    */
    $ctrl.addPlafont = function (userId) {
      ModalService.openAddPlafontModal(userId, getActivations);
    }

    /**
    La gestione completa dell'aggiunta dell'attivazione è effettuata nella componente.
    Qui si aggiorna solo la lista alla chiusura del modal.
    */
    $ctrl.addActivation = function (userId) {
      ModalService.openAddActivationModal(userId, $ctrl.user.id, getActivations);
    };

    $ctrl.showPlafontList = function(userId) {
      ModalService.showPlafontList(userId,$ctrl.from,$ctrl.to);
    }

    $ctrl.delActivation = function(id,amount) {
        ActivasionsService.delActivation(id,amount, getActivations);
    }

    /* */

    $scope.dateOptions = {
      dateDisabled: false,
      maxDate: new Date(),
      startingDay: 1
    };

    $scope.format = DateService.DEFAULT_FORMAT;

    $scope.$watch(
      function() {
        return $ctrl.to;
      }, manageDateChange, true
    );

    $scope.$watch(
      function() {
        return $ctrl.from;
      }, manageDateChange, true
    );

    /* */

    function getActivations() {
      let from = DateService.msToDefaultFormat(new Date($ctrl.from));
      let to = DateService.msToDefaultFormat(new Date($ctrl.to));
      ActivasionsService.getActivations($ctrl.user.id, from, to, manageActivationsRetrieve);
    }

    function manageDateChange(oldValue, newValue) {
      if (newValue !== oldValue) {
        getActivations();
      }
    }

    function manageActivationsRetrieve(data) {
      $ctrl.activations = data;
    }

    var recursiveTot = (elem) => {
        if(!(_.isObject(elem))) return null;
        var total = 0;
        for(var i=0; i < elem.wrapper.length; i++) {
            total += _.isObject(elem.wrapper[i].activations) ? elem.wrapper[i].activations.length : recursiveTot(elem.wrapper[i]);
        }
        return total;
    };

    $scope.totHighLevel = function(elem) {
        return recursiveTot(elem);
    }



  }])
});

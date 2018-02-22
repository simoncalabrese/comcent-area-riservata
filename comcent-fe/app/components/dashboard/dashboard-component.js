angular.module('app').component('dashboard', {
    templateUrl: window.PATH_PREFIX + './app/components/dashboard/dashboard-template.html',
    bindings: {},
    controller: ('loginController', ['$scope', '$rootScope', 'AuthService', 'ActivasionsService', 'DateService', 'ModalService', '_', function ($scope, $rootScope, AuthService, ActivasionsService, DateService, ModalService, _) {

        var $ctrl = this;
        $ctrl.user = null;
        $ctrl.getActivations = null;

        $ctrl.$onInit = function () {
            $ctrl.user = AuthService.getUser();
            $ctrl.to = new Date();
            $ctrl.from = new Date();
            $ctrl.from = $ctrl.from.setMonth($ctrl.from.getMonth() - 1);
            $scope.panel = {};
            $ctrl.numAttPadre = null;

            getActivations();
        };

        $ctrl.logout = function () {
            AuthService.logout();
        };

        /**
         La gestione completa dell'aggiunta del plafont è effettuata nella componente.
         Qui si aggiorna solo la lista alla chiusura del modal.
         */
        $ctrl.addPlafont = function (userId) {
            ModalService.openAddPlafontModal(userId, $ctrl.user.id, getActivations);
        }

        /**
         La gestione completa dell'aggiunta dell'attivazione è effettuata nella componente.
         Qui si aggiorna solo la lista alla chiusura del modal.
         */
        $ctrl.addActivation = function (userId) {
            ModalService.openAddActivationModal(userId, $ctrl.user.id, getActivations);
        };

        $ctrl.showPlafontList = function (userId) {
            ModalService.showPlafontList(userId, $ctrl.from, $ctrl.to);
        }

        $ctrl.delActivation = function (id, amount, user) {
            ActivasionsService.delActivation(id, amount, user,$ctrl.user.id, getActivations);
        }

        /* */

        $scope.dateOptions = {
            dateDisabled: false,
            maxDate: new Date(),
            startingDay: 1
        };

        $scope.format = DateService.DEFAULT_FORMAT;

        $scope.$watch(
            function () {
                return $ctrl.to;
            }, manageDateChange, true
        );

        $scope.$watch(
            function () {
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

        function recursiveTot(elem) {
            if (!(_.isObject(elem))) return 0;
            var total = 0;
            for (var i = 0; i < elem.wrapper.length; i++) {
                total += elem.wrapper[i].wrapper != null ? recursiveTot(elem.wrapper[i]) : elem.wrapper[i].activations.length;
            }
            return total;
        }

        function manageActivationsRetrieve(data) {
            $ctrl.activations = data;
            $ctrl.numAttPadre = recursiveTot(data);
        }


        $ctrl.totHighLevel = function (elem) {
            return recursiveTot(elem);
        }


    }])
});

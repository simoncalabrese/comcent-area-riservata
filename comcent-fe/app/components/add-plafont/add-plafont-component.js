angular.module('app').component('addPlafont', {
    templateUrl: window.PATH_PREFIX + './app/components/add-plafont/add-plafont-template.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: ('addPlafontController', ['$scope', '$rootScope', 'DateService', 'PlafontsService', 'ApiService', function ($scope, $rootScope, DateService, PlafontsService, ApiService) {

        var $ctrl = this;

        $ctrl.$onInit = function () {
            $ctrl.date = new Date();
        }

        $ctrl.add = function () {
            PlafontsService.addPlafont($ctrl.resolve.userId, $ctrl.date, $ctrl.amount, $ctrl.resolve.userInsert, manageAddedPlafont);
        }

        $scope.dateOptions = {
            dateDisabled: false,
            maxDate: new Date(),
            startingDay: 1
        };

        $scope.format = DateService.DEFAULT_FORMAT;

        /* */

        function manageAddedPlafont(result) {
            if (result == ApiService.STATUS_OK) {
                console.log("Plafont added.");
                $ctrl.close({$value: ApiService.STATUS_OK});
            }
        }

    }])
});

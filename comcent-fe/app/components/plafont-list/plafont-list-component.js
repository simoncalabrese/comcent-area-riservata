angular.module('app').component('plafontList', {
    templateUrl: window.PATH_PREFIX + './app/components/plafont-list/plafont-list-template.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: ('plafontListController', ['$scope', '$rootScope', 'DateService', 'PlafontsService', function ($scope, $rootScope, DateService, PlafontsService) {

        var $ctrl = this;
        $ctrl.plafonts = [];
        $ctrl.dateStart = null;
        $ctrl.dateEnd = null;
        $ctrl.$onInit = function () {

        }

        function callGetList() {
            $ctrl.dateStart = DateService.msToDefaultFormat($ctrl.resolve.dateFrom);
            $ctrl.dateEnd = DateService.msToDefaultFormat($ctrl.resolve.dateTo);
            let data = {
                userId: $ctrl.resolve.userId,
                dateStart: $ctrl.dateStart,
                dateEnd: $ctrl.dateEnd
            };
            PlafontsService.getPlafontList(data, managePlafontList);
        }

        function managePlafontList(data) {
            $ctrl.plafonts = data;
        }

        $ctrl.delPlafont = function(amount) {
            var newAmount = amount * -1;
            PlafontsService.addPlafont($ctrl.resolve.userId, DateService.msToDefaultFormat(new Date()), newAmount, callGetList);
        }

    }])
});

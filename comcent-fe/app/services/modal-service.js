angular.module('app').service('ModalService', ['$uibModal', '$log', function ($uibModal, $log) {

    $svc = this;
    $svc.openAddPlafontModal = openAddPlafontModal;
    $svc.openAddActivationModal = openAddActivationModal;
    $svc.openAddUserModal = openAddUserModal;
    $svc.modalLink = modalLink;
    $svc.showPlafontList = showPlafontList;

    function openAddPlafontModal(userId, callback) {
        let modalInstance = $uibModal.open({
            animation: true,
            component: 'addPlafont',
            resolve: {
                userId: function () {
                    return userId;
                }
            }
        });

        modalInstance.result.then(function (result) {
            (callback || angular.noop)(result);
        }, function () {
        });
    }

    function openAddActivationModal(userId, userInsert, callback) {
        let modalInstance = $uibModal.open({
            animation: true,
            component: 'addActivation',
            resolve: {
                userId: function () {
                    return userId;
                },
                userInsert: function () {
                    return userInsert;
                }
            }
        });

        modalInstance.result.then(function (result) {
            (callback || angular.noop)(result);
        }, function () {
        });
    }

    function openAddUserModal(userId, callback) {
        let modalInstance = $uibModal.open({
            animation: true,
            component: 'addUser',
            resolve: {
                userId: function () {
                    return userId;
                }
            }
        });

        modalInstance.result.then(function (result) {
            (callback || angular.noop)(result);
        }, function () {
        });
    }

    function modalLink(url) {
        let modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'components/external-link/external-link-template.html',
            component: 'externalLink',
            resolve: {
                url: function () {
                    return url;
                }
            }
        });
        modalInstance.result.then(function (result) {
            angular.noop();
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }

    function showPlafontList(userId, dateFrom, dateTo) {
        let modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'components/plafont-list/plafont-list-template.html',
            component: 'plafontList',
            resolve: {
                userId: function () {
                    return userId;
                },
                dateFrom: function () {
                    return dateFrom;
                },
                dateTo: function () {
                    return dateTo;
                }
            }
        });

        modalInstance.result.then(function (result) {
            angular.noop();
        }, function (result) {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }

}]);

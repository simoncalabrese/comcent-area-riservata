angular.module('app').component('addUser', {
    templateUrl: window.PATH_PREFIX + './app/components/add-user/add-user-template.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: ('addUserController', ['$scope', '$rootScope', 'UserService', 'ApiService', function ($scope, $rootScope, UserService, ApiService) {

        var $ctrl = this;
        $ctrl.users = [];
        $ctrl.name = null;
        $ctrl.surname = null;
        $ctrl.cf = null;
        $ctrl.email = null;
        $ctrl.psw = null;
        $ctrl.phone = null;
        $ctrl.reference = null;
        $ctrl.readPermission = null;
        $ctrl.writePermission = null;

        $ctrl.$onInit = function () {
            UserService.getUsers($ctrl.resolve.userId, manageGetUsers);
        }


        $ctrl.add = function () {
            let user = {
                name: $ctrl.name,
                surname: $ctrl.surname,
                codFisc: $ctrl.cf,
                partIva: $ctrl.partIva,
                email: $ctrl.email,
                psw: $ctrl.psw,
                phone: $ctrl.phone,
                referenceId: $ctrl.reference,
                readPermission: $ctrl.readPermission,
                writePermission: $ctrl.writePermission
            };
            UserService.addUser(user, manageAddUser);
        }

        /* */

        function manageGetUsers(result) {
            $ctrl.users = result;
        }

        function manageAddUser(result) {
            if (result.status == 200) {
                console.log("Activation added.");
                $ctrl.close({$value: ApiService.STATUS_OK});
            }
        }

    }])
});

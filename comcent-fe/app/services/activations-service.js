angular.module('app').service('ActivasionsService', ['_', '$http', 'ApiService', function (_, $http, ApiService) {

    $svc = this;

    $svc.getActivations = getActivations;
    $svc.addActivation = addActivation;
    $svc.delActivation = delActivation;

    function getActivations(userId, dateStart, dateEnd, callback) {
        let data = {
            userId: userId,
            dateStart: dateStart,
            dateEnd
        };
        $http.post(ApiService.getActivasionsURL(), data).then(function (response) {
            if (_.isObject(response) && _.isObject(response.data)) {
                (callback || angular.noop)(response.data);
            } else {
                (callback || angular.noop)({});
                // TODO fare qualcosa?
            }
        });
    }

    function addActivation(userId, date, amount, description, userI, callback) {
        let data = {
            user: userId,
            desActivation: description,
            amntPlafont: amount,
            dateAtt: date,
            userInsert: userI
        };
        $http.post(ApiService.getAddActivasionsURL(), data).then(function (response) {
            if (_.isObject(response) && _.isObject(response.data)) {
                (callback || angular.noop)(response.data);
            } else {
                (callback || angular.noop)({});
                // TODO fare qualcosa?
            }
        });
    }

    function delActivation(id, amount, user, userInser, callback) {
        $http({
            method: 'GET',
            url: ApiService.delActivation(),
            params: {
                id: id,
                amount: amount,
                user: user,
                userInsert: userInser
            }
        }).then(function (response) {
            (callback)();
        })
    }

}]);

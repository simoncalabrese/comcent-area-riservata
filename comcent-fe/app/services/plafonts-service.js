angular.module('app').service('PlafontsService', ['_', '$http', 'ApiService', function (_, $http, ApiService) {

    $svc = this;

    $svc.addPlafont = addPlafont;
    $svc.getPlafontList = getPlafontList;
    $svc.delPlafont = delPlafont;

    function addPlafont(userId, date, amount, userInsert, callback) {
        let data = {
            userId: userId,
            dataMov: date,
            amount: amount,
            userInsert: userInsert
        };
        $http.post(ApiService.getAddPlafontURL(), data).then(function (response) {
            if (_.isObject(response) && _.isObject(response.data) && response.data.status == 200) {
                (callback || angular.noop)(ApiService.STATUS_OK);
            } else {
                (callback || angular.noop)(ApiService.STATUS_KO);
            }
        });
    }

     function getPlafontList(data, callback) {
        $http.post(ApiService.plafontList(), data).then(function (response) {
            if (_.isObject(response) && _.isObject(response.data)) {
                (callback || angular.noop)(response.data);
            } else {
                (callback || angular.noop)({});
            }
        })
    }

    function delPlafont(id, callback) {
        $http({
                      method: 'GET',
                      url: ApiService.delPlafont(),
                      params: {
                        id: id
                        }
                    }).then(function(response){
                        (callback)();
                    })
    }

}]);

angular.module('app').service('DateService', ['moment', function(moment) {

  $svc = this;
  let DEFAULT_FORMAT = 'dd-MM-yyyy';
  let DEFAULT_MOMENT_FORMAT = 'DD-MM-YYYY';

  $svc.DEFAULT_FORMAT = DEFAULT_FORMAT;
  $svc.msToDefaultFormat = msToDefaultFormat;

  function msToDefaultFormat(ms) {
    return moment(ms).format(DEFAULT_MOMENT_FORMAT);
  }

}]);

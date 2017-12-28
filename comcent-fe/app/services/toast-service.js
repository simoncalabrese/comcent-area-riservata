angular.module('app').service('ToastService', ['toastr', function(toastr) {

  $svc = this;
  $svc.show = show;
  $svc.showError = showError;

  function show(text, title) {
    toastr.info(text, title);
  }

  function showError(text, title) {
    toastr.error(text, title);
  }

}]);

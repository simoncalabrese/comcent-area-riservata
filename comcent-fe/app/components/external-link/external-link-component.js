angular.module('app').component('externalLink', {
  templateUrl: window.PATH_PREFIX + './app/components/external-link/external-link-template.html',
  bindings: {
    resolve: '<',
    close: '&',
    dismiss: '&'
  },
  controller: ('externalLinkController', ['$scope','$rootScope', '$sce', function($scope, $rootScope,$sce) {

    var $ctrl = this;
    $ctrl.url = null;

    $ctrl.$onInit = function() {
        $ctrl.url = $sce.trustAsResourceUrl($ctrl.resolve.url);
    }

    /* */

  }])
});

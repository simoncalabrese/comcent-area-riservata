/**
  Security Interceptor
*/

angular.module('app').factory('securityInterceptor', function($rootScope, EVENTS) {
  return {
    response: function(response) {
      return response;
    },
    responseError: function(responseError) {
      console.error("ResponseError", responseError);
      return responseError;
    },
    request: function(request) {
      checkLogin();
      return request;
    },
    requestError: function(requestError) {
      console.error("RequestError", requestError);
      checkLogin();
      return requestError;
    }
  };

  function checkLogin() {
    $rootScope.$broadcast(EVENTS.CHECK_LOGIN);
  }

});

/**
  Messages Interceptor
*/

angular.module('app').factory('messagesInterceptor', function($rootScope) {
  return {
    response: function(response) {
      checkMessages(response);
      return response;
    },
    responseError: function(responseError) {
      return responseError;
    },
    request: function(request) {
      return request;
    },
    requestError: function(requestError) {
      return requestError;
    }
  };

  function checkMessages(data) {
    $rootScope.$broadcast(EVENTS.CHECK_HTTP_MESSAGES, data);
  }

});

angular.module('app').config(function($httpProvider) {
  $httpProvider.interceptors.push('securityInterceptor');
  $httpProvider.interceptors.push('messagesInterceptor');
});

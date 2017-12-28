/**
  Events
*/

let EVENTS = {
  LOGIN_SUCCESSFUL: 'login-successful',
  LOGOUT_SUCCESSFUL: 'logout-successful',
  NOT_LOGGED_IN: 'not-logged-in',
  CHECK_LOGIN: 'check-login',

  CHECK_HTTP_MESSAGES: 'check-http-messages'

}

angular.module('app').constant('EVENTS', EVENTS);

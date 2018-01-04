angular.module('app').service('ApiService', [function () {

    $svc = this;

    $svc.getLoginURL = getLoginURL;
    $svc.getActivasionsURL = getActivasionsURL;
    $svc.getAddPlafontURL = getAddPlafontURL;
    $svc.getAddActivasionsURL = getAddActivasionsURL;
    $svc.delActivation = delActivation;
    $svc.getUsersURL = getUsersURL;
    $svc.getLinks = getLinks;
    $svc.delDoc = delDoc;
    $svc.addDoc = addDoc;
    $svc.signUp = signUp;
    $svc.plafontList = plafontList;

    $svc.STATUS_OK = 'OK';
    $svc.STATUS_KO = 'KO';

    const PATH = 'http://localhost:8080/comunicationcenter/';
    const INDEX = 'index/';
    const ACTIVATIONS = 'activations/';
    const PLAFONT = 'plafont/';
    const UTIL = 'util/'

    function getLoginURL() {
        return PATH + INDEX + 'login';
    }

    /* ACTIVATIONS */

    function getActivasionsURL() {
        return PATH + ACTIVATIONS + 'getActivations';
    }

    function getAddActivasionsURL() {
        return PATH + PLAFONT + 'insertActivation';
    }

    /* PLAFONT */

    function getAddPlafontURL() {
        return PATH + PLAFONT + 'addPlafont';
    }

    function getUsersURL() {
        return PATH + PLAFONT + 'getUsers';
    }

    function getLinks() {
        return PATH + UTIL + 'getDocs';
    }

    function delDoc() {
        return PATH + UTIL + 'delDoc';
    }

    function addDoc() {
        return PATH + UTIL + "addDoc";
    }

    function signUp() {
        return PATH + INDEX + 'signup';
    }

    function plafontList() {
        return PATH + PLAFONT + 'plafontList';
    }

    function delActivation() {
        return PATH + ACTIVATIONS + 'delActivation';
    }

}]);

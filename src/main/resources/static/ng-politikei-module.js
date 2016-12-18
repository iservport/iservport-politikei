angular
.module('app')
.controller('PolitikeiController', ['$log', '$http', function($log, $http) {

    var $ctrl = this;

    $log.info("PolitikeiController is active.");

    $ctrl.listProposition = function() {
        $http.get("proposition")
            .then(function(response) {
                $ctrl.propositionList = response.data;
            }).catch(function(e) { $log.error("listProposition FAILED"+e); })
        }

    $ctrl.listProposition();

    $ctrl.newProposition = function(entityId) {
        $http.post("proposition?_csrf="+$ctrl._csrf, entityId)
            .then(function(response) {
                $ctrl.proposition = response.data;
            }).catch(function(e) { $log.error("newProposition FAILED"+e); })
        }

    $ctrl.getProposition = function(propositionId) {
        $http.get("proposition?propositionId="+propositionId)
            .then(function(response) {
                $ctrl.proposition = response.data;
            }).catch(function(e) { $log.error("getProposition FAILED"+e); })
        }

    $ctrl.updateProposition = function() {
        $http.put("proposition?_csrf="+$ctrl._csrf, $ctrl.proposition)
            .then(function(response) {
                $ctrl.proposition = response.data;
            }).catch(function(e) { $log.error("updateProposition FAILED"+e); })
        }

}])

;

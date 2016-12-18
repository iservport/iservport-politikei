angular
.module('app')
.directive('voteItem', ['$http', function($http){
    return {
        restrict: 'A',
        scope: { 'propositionId': '@voteItem'},
        link: function(scope, element, attr, politikei) {
            $http.post("vote/"+scope.propositionId+"?_csrf="+attr.csrf)
                .then(function(response) {
                    scope.vote = response.data;
                }).catch(function(e) { $log.error("getVote FAILED"+e); })
            scope.voteAs = function(vote) {
                $http.put("vote/"+scope.propositionId+"/"+vote+"?_csrf="+attr.csrf)
                    .then(function(response) {
                        scope.vote = response.data;
                    }).catch(function(e) { $log.error("vote FAILED"+e); })
            }
        },
        templateUrl: 'templates/vote-item.ftl'
    };
}])
;

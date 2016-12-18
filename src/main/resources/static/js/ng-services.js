(function () {
    'use strict';

    function config($mdThemingProvider, $mdIconProvider, $mdAriaProvider) {

        $mdIconProvider
            .defaultIconSet("images/svg/avatars.svg", 128)
            .icon("menu", "images/svg/menu.svg", 24)
            .icon("share", "images/svg/share.svg", 24)
            .icon("google_plus", "images/svg/google_plus.svg", 512)
            .icon("hangouts", "images/svg/hangouts.svg", 512)
            .icon("twitter", "images/svg/twitter.svg", 512)
            .icon("phone", "images/svg/phone.svg", 512);

        var pkGreen = $mdThemingProvider.extendPalette('teal', {
            '400': '#23C9BB',
            '500': '#11ADA0',
            '600': '#11A294',
            'contrastDefaultColor': 'light'
        });
        var pkDarkBlueGrey = $mdThemingProvider.extendPalette('blue-grey', {
            '50': '#E9F2F1',
            '500': '#354C5E',
            '800': '#2E4152',
            'contrastDefaultColor': 'light',
            'contrastDarkColors': ['50']
        });
        $mdThemingProvider.definePalette('pkGreen', pkGreen);
        $mdThemingProvider.definePalette('pkDarkBlueGrey', pkDarkBlueGrey);
        $mdThemingProvider.theme('default')
            .primaryPalette('pkGreen', {
                'default': '400',
                'hue-1': '500',
                'hue-2': '600'
            })
            .accentPalette('pkDarkBlueGrey', {
                'default': '500',
                'hue-1': '800',
                'hue-2': '50'
            })
            .backgroundPalette('pkDarkBlueGrey', {
                'default': '50'
            });

        $mdAriaProvider.disableWarnings();
    }

    config.$inject = ['$mdThemingProvider', '$mdIconProvider', '$mdAriaProvider'];

    angular
    .module('app', ['ngResource','ngMaterial'])
    .config(config)
    .controller('ViewController', ViewController);

    ViewController.$inject = ['$scope', '$http', '$log'];

    function ViewController($scope, $http, $log) {

        var view = this;

        $log.info("View Controller is active.");

        var selected = 0;

        view.select = function(value) { selected = value; }

        view.isSelected = function(value) { return selected == value; }

        view.logout = function(csrf) {
            $http.get("logout?_csrf="+csrf)
                .then(function(response) {}).catch(function(e) { $log.error("logout FAILED"); })
        }

        $http.get("app/entity")
            .then(function(response) {
                $scope.entity = response.data;
            }).catch(function(e) { $log.error("app/entity FAILED"); })

        $http.get("app/me")
            .then(function(response) {
                $scope.me = response.data;
            }).catch(function(e) { $log.error("app/me FAILED"); })

    }

} ());


var app = angular.module('app');

var app = angular.module('app').directive("filesInput", function () {
    return {
        require: "ngModel",
        link: function postLink(scope, elem, attrs, ngModel) {
            elem.on("change", function (e) {
                var files = elem[0].files;
                ngModel.$setViewValue(files);
            });
        }
    };

});


app.controller('myProfile', function ($scope, $localStorage, $http) {
    $scope.updateUser = {};
    $scope.updateUser = $localStorage.userLogged;

    $scope.editUserProfile = function (item) {
        $http({
            method: 'POST',
            url: 'updateUser',
            data: item,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (response) {

        });

    };

});
var app = angular.module('app');

app.controller('profileController', function ($scope, $localStorage, $http) {
    $scope.user = $localStorage.userLogged;
    
    $scope.user = {
        iduser: $localStorage.profileUser
    };

    //Gets user profile.
    $http({
        method: 'PUT',
        url: 'profile',
        data:  $scope.user,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.userprofile = data;
        delete $localStorage.profileUser;

    });

    $scope.postsByUser = [];
    $http({
        method: 'PUT',
        url: 'postsuser',
        data:  $scope.user,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.postsByUser = data;
        delete $localStorage.profileUser;
    });

});
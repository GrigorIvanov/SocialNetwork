var app = angular.module('app');

app.controller('likesController', function ($scope, $localStorage, ngDialog, $http) {
    //Like Post
    $scope.likes = {
        post: {idpost: 0},
        user: {iduser: $scope.user.iduser}
    };
    $scope.likePost = function (idpost) {
        $scope.likes.post.idpost = idpost;
        $http({
            method: 'POST',
            url: 'likePost',
            data: $scope.likes,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            
        });
    };

    //Disike Post
    $scope.dislikePost = function (idpost) {
        $scope.likes.post.idpost = idpost;
        $http({
            method: 'POST',
            url: 'dislikePost',
            data: $scope.likes,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
        });
    };

});
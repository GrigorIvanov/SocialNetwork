var app = angular.module('app');

app.controller('userController', function ($scope, $http, $localStorage, $location, $uibModal, $uibModalStack) {

    $scope.userLo = $localStorage.userLogged;
    $scope.user = {};

    //Something really weird to check set the variables with a null value. It wasn't working out the other way.
    if ($scope.userLo == "" | $scope.userLo == null) {
        //Variable to establish friendship between logged user and user available on a list of users. 
        $scope.newFriendship = {
            userOneId: {iduser: 0},
            userTwoId: {iduser: 0},
            actionUserId: {iduser: 0}
        }
    } else {
        $scope.newFriendship = {
            userOneId: {iduser: $scope.userLo.iduser},
            userTwoId: {iduser: 0},
            actionUserId: {iduser: $scope.userLo.iduser}
        };
    }
    ;

    $scope.confirmPassword = null;

    //Create a new user.
    $scope.saveUser = function () {
        if ($scope.user.name == null || $scope.user.name == "" && $scope.user.email == null || $scope.user.email == "" && $scope.user.password == null || $scope.user.password == "") {
            swal("ERROR!", "Null values aren't permitted", "error");
        } else {
            if ($scope.user.password !== $scope.confirmPassword) {
                swal("ERROR!", "Password typed don't match! Check them out.", "error");

            } else {
                $http({
                    method: 'POST',
                    url: 'signup',
                    data: $scope.user,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (reponse) {
                    if (reponse == '' || reponse == null) {
                        swal("Ops...", "This email is already registrated", "warning");
                        $location.path('signup');
                    } else {
                        swal("All right!", "Welcome " + $scope.user.name + ":)", "success");
                        $location.path('/login');
                    }
                    ;
                });
            }
            ;
        }
        ;


    };

    //List of users
    $scope.listUser = [];
    $http({
        method: 'GET',
        url: 'users',
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.listUser = data;
    });




    //Show profiel
    $scope.getUserProfile = function (item) {
        $scope.userProfile = item;
        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'webapp/resources/static/modal/profileUser.html',
            size: 'md',
            controller: function ($scope) {
                $scope.name = item;

                $scope.Close = function () {
                    $uibModalStack.dismissAll();
                };
            }
        });
    };


    //Make friendship.
    $scope.makeFriend = function (id) {
        $scope.newFriendship.userTwoId.iduser = id;
        $http({
            method: 'POST',
            url: 'makeFriend',
            data: $scope.newFriendship,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            if (data == null || data == "") {
                swal("Ops..!", "Apparently this person is your friend already or an invitation is still on itws way!", "error");
            } else {
                swal("Good job!", "An invitation was succesfully sent!", "success");
            }

        });
    };
});


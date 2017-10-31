var app = angular.module('app');

app.controller('homeController', function ($scope, $localStorage, $state, $http) {
    $scope.user = $localStorage.userLogged;
    
    $scope.date = new Date();

    $scope.logOut = function () {
        swal({
            title: "Are you sure you want to leave?",
            text: "You can go back anytime ;D",
            type: "info",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes",
            cancelButtonText: "No",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        $http({
                            method: 'POST',
                            url: 'logout',
                            data: $scope.user,
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        }).success(function (response) {
                            console.log('logout');
                        });

                        swal("All right!", "See you soon : )", "success");
                        $state.go("login");
                        $localStorage.$reset();
                    } else {
                        swal("All right!", "That's ok :)", "info");
                    }
                });

    };
});

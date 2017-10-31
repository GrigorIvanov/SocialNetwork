app.controller('loginCtrl', function ($scope, $http, $location, $localStorage, ngDialog) {
    $scope.user = {};

    $scope.loginUser = function () {

        if ($scope.user.email == '' || $scope.user.email == null || $scope.user.password == '' || $scope.user.password == null) {
            swal({
                title: "Error!",
                text: "Apparently you didn't type anything!",
                type: "error",
                confirmButtonText: "I see"
            });
        } else {
            $http({
                method: 'POST',
                url: 'login',
                data: $scope.user,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (response) {
                $localStorage.userLogged = response;
                if (response.iduser == '' || response.iduser == null) {
                    swal("Apparently you're not a member", "Sing up below", "info");
                } else {
                    $location.path('/home');
                }
            });

        }
        ;
    };
});
var app = angular.module('app');
app.controller('messageController', function ($scope, $localStorage, ngDialog, $http, $location, $state) {

    $scope.user = $localStorage.userLogged;
    $scope.sent = [];


    //Display conversation between users
    $scope.showConversationContent = function (id, idlogged) {

        $scope.messageSent = {
            userOne: {iduser: id},
            userTwo: {iduser: idlogged}
        };
        $http({
            method: 'POST',
            url: 'displayConversationBetweenUsers',
            data: $scope.messageSent,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.sent = data;
        });

    };

    //Send message
    $scope.message = {
        userOne: {iduser: $scope.user.iduser},
        userTwo: {iduser: 0},
        sent: {iduser: $scope.user.iduser}
    };

    $scope.sendMessage = function (id) {
        $scope.message.userTwo.iduser = id;
        if ($scope.message.userTwo.iduser == $scope.message.userOne.iduser) {
            console.log('NO CAN DO IT');
        } else {
            $http({
                method: 'POST',
                url: 'sendMessage',
                data: $scope.message,
                headers: {
                    'Content-Type': 'application/json'
                }
            }).success(function (data) {
                $scope.sent.push(data);
                $state.go('messages');
            });
        }

    };

    //Display conversations created
    $scope.userId = {
        userOne: {iduser: $scope.user.iduser}
    };

    $scope.conversations = [];

    $http({
        method: 'POST',
        url: 'displayConversation',
        data: $scope.userId,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.conversations = data;
        if ($scope.conversations.length == 0) {
            swal({
                title: "Ops...",
                text: "It seems that you haven't messaged anyone yet. How about start conversing though?!",
                type: "warning",
                showCancelButton: false,
                confirmButtonColor: "#5bc0de",
                closeOnConfirm: true
            },
                    function () {
                        $state.go("friends");
                    });
        } else {
            $scope.showConversationContent(data[0].userTwo.iduser, $scope.user.iduser);
        }
    });

    $scope.noMessagesCloseModal = function () {
        ngDialog.closeAll();
    };

    $scope.moveRight = function (id) {
        if ($scope.user.iduser == id) {
            return 'bubbledRight';
        } else {
            return 'bubbledLeft';
        }
    };
});
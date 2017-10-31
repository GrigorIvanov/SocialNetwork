var app = angular.module('app');

app.controller('friendshipController', function ($scope, $http, $localStorage, ngDialog, $state, $location, $window) {
    //User logged in
    $scope.user = $localStorage.userLogged;

    //Array of friendships.    
    $scope.friendship = [];

    //Preloader
    $scope.showFriendsList = false;

    //Variable make friend
    $scope.makeFriendship = {
        userOneId: {iduser: $scope.user.iduser},
        userTwoId: {iduser: 0},
        actionUserId: {iduser: $scope.user.iduser}
    };

    //Get user logged and set it in an object to check friends that is accepted.                
    $scope.logged = {
        userOneId: {iduser: $scope.user.iduser}
    };

    //Variable to check pending friends.   
    $scope.pendingFriendship = {
        userOneId: {iduser: $scope.user.iduser}
    };

    //Variable to remove friend
    $scope.removeFriendship = {
        userOneId: {iduser: $scope.user.iduser},
        userTwoId: {iduser: 0},
        actionUserId: {iduser: $scope.user.iduser}
    };

    //Variable to block friend
    $scope.blockFriendship = {
        userOneId: {iduser: $scope.user.iduser},
        userTwoId: {iduser: 0},
        actionUserId: {iduser: $scope.user.iduser}
    };

    //Variable to send message modal.
    $scope.message = {
        userOne: {iduser: $scope.user.iduser},
        userTwo: {iduser: 0}
    };

    $scope.CloseModal = function () {
        ngDialog.closeAll();
    };

    //Show friends accepted.;
    $scope.showNoFriendNotification = false;
    $http({
        method: 'POST',
        url: 'getFriends',
        data: $scope.logged,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.friendship = data;

        if ($scope.friendship.length == 0) {
            $scope.showNoFriendNotification = true;
        }else{
            $scope.showNoFriendNotification = false;
        };

    });
    $scope.pendingFriendshipArray = [];

    //Show pending friends
    $http({
        method: 'POST',
        url: 'getFriendsPending',
        data: $scope.pendingFriendship,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.showNoFriendNotification = false;
        $scope.pendingFriendshipArray = data;
        if ($scope.pendingFriendshipArray.length == 0) {
            $scope.CheckIfTheresPendingFriends = true;
        } else {
            $scope.CheckIfTheresPendingFriends = false;
        }
        ;
        if ($scope.pendingFriendshipArray.length >= 1) {
            console.log('pending');

        } else {
            console.log('ok thanks');
        }
    });

    //Accept friendship
    $scope.acceptFriend = function (id) {
        $scope.makeFriendship.userTwoId.iduser = id;
        $http({
            method: 'POST',
            url: 'acceptFriend',
            data: $scope.makeFriendship,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            swal("Good job!", "Yay! You have a new friend!", "success");
            $scope.friendship.push(data);
            $scope.preloaderAfterAcceptFriend = true;
            $scope.showFriendsList = true;
            $scope.CheckIfTheresPendingFriends = true;

            $window.location.reload();
        });
    };

    //Remove friend from accepeted friend list
    $scope.removeFriendFromFriendsAccepted = function (id, index) {
        swal({
            title: "Are you sure?",
            text: "You will not be able to send this person messages!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel please!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        $scope.removeFriendship.userTwoId.iduser = id;
                        $http({
                            method: 'POST',
                            url: 'removeFriend',
                            data: $scope.removeFriendship,
                            headers: {'Content-Type': 'application/json'}
                        });

                        $scope.friendship.splice(index, 1);

                        swal("Deleted!", "This friend was deleted.", "success");
                    } else {
                        swal("Cancelled", "You're still friends :)", "error");
                    }
                });

    };

    //Remove friend from pending friend list
    $scope.removeFriendFromPendingList = function (id, index) {
        swal({
            title: "Are you sure?",
            text: "You will not be able to send this person messages!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel please!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        $scope.removeFriendship.userTwoId.iduser = id;
                        $http({
                            method: 'POST',
                            url: 'removeFriend',
                            data: $scope.removeFriendship,
                            headers: {'Content-Type': 'application/json'}
                        });

                        $scope.pendingFriendshipArray.splice(index, 1);

                        swal("Deleted!", "This friend was deleted.", "success");
                    } else {
                        swal("Cancelled", "You're still friends :)", "error");
                    }
                });
    };

    //Block friend
    $scope.blockFriend = function (id) {
        swal({
            title: "Are you sure?",
            text: "You will not be able to see this person here!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel please!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        $scope.blockFriendship.userTwoId.iduser = id;
                        $http({
                            method: 'POST',
                            url: 'blockFriend',
                            data: $scope.blockFriendship,
                            headers: {'Content-Type': 'application/json'}
                        });
                        var index = $scope.friendship.indexOf(id);
                        $scope.friendship.splice(index, 1);

                        swal("Blocked!", "Tell us if he/she was bothering you.", "success");
                    } else {
                        swal("Cancelled", "You're still friends :)", "error");
                    }
                });
    };

    //Send message to friend to start a conversation. Wish it could be accomplished in the other controller though.
    $scope.getFriendToMessage = function (idToMessage) {
        $localStorage.idToMessageFriend = idToMessage;
        swal({
            title: "An input!",
            text: "Write something interesting:",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: true,
            animation: "slide-from-top",
            inputPlaceholder: "Write something"
        },
                function (textMessage) {
                    if (textMessage === false)
                        return false;

                    if (textMessage === "") {
                        swal.showInputError("You need to write something!");
                        return false
                    }

                    $scope.message.userTwo.iduser = $localStorage.idToMessageFriend;
                    $scope.message.content = textMessage;
                    $http({
                        method: 'POST',
                        url: 'sendMessage',
                        data: $scope.message,
                        headers: {'Content-Type': 'application/json'}
                    }).success(function () {
                        $location.path('/messages');
                    });
                });
    };

});
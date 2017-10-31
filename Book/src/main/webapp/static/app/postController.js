var app = angular.module('app');

app.controller('postController', function ($scope, $localStorage, $http, $uibModal, $uibModalStack) {

    $scope.user = $localStorage.userLogged;
    $scope.postModel = {
        user: {iduser: $scope.user.iduser}
    };
    //Create a post
    $scope.doPost = function () {
        $http({
            method: 'POST',
            url: 'post',
            data: $scope.postModel,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (response) {
            $scope.posts.push(response);
        });
    };

    //Show posts
    $scope.post = {};
    $scope.posts = [];
    $http({
        method: 'GET',
        url: 'showPosts',
        data: $scope.post,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.posts = data;
    });

    //Array comments
    $scope.postComments = [];

    //Hide/Show comments
    $scope.hideComments = true;
    $scope.showComment = true;

    //Show comments when clicked
    $scope.showComments = function (item) {

        $scope.hideComments = false;
        $scope.showComment = false;

        $scope.idPost = item;
        $http({
            method: 'GET',
            url: 'showCommetsByPost?idpost=' + item.idpost,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.totalComments = data.length;
            $scope.postComments = data;
        });
    };

    //Back to initial state
    $scope.back = function () {
        $scope.showComment = true;
        $scope.hideComments = true;
        $scope.idPost = 0;
    };

    //Comment in some post
    $scope.commentThisPost = function (item, content) {
        $scope.comment = {
            user: {iduser: $scope.user.iduser},
            post: {idpost: item.idpost},
            content: content
        };
        $http({
            method: 'POST',
            url: 'comment',
            data: $scope.comment,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (response) {
            $scope.postComments.push(response);
        });
    };

    //List new members
    $scope.newUsers = [];
    $http({
        method: 'GET',
        url: 'newMembers',
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (response) {
        $scope.newUsers = response;
    });

    //List new groups
    $scope.newGroup = [];
    $http({
        method: 'GET',
        url: 'newGroups',
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (response) {
        $scope.newGroup = response;
    });

    //Modal group
    $scope.getGroupById = function (id) {
        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'webapp/resources/static/modal/groupmodal.html',
            size: 'md',
            controller: function ($scope) {
                //Get groups by ID
                $http({
                    method: 'GET',
                    url: 'showGroupsById?id=' + id,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (data) {
                    $scope.groupDetail = data;
                });

                //MEMBERS IN THIS GROUP
                $scope.membersInGroup = [];
                $http({
                    method: 'GET',
                    url: 'getMembersInsideGroup?id=' + id,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (data) {
                    $scope.membersInGroup = data;
                    $scope.totalMembers = $scope.membersInGroup.length;
                });

                //CLOSE MODAL
                $scope.Close = function () {
                    $uibModalStack.dismissAll();
                };
            }
        });
    };

    //Send request to group's owner from wall
    $scope.sendRequestToOwner = function (item, id) {
        var groupRequest = {
            groupId: {idGroup: item},
            creator: {iduser: id},
            member: {iduser: $scope.user.iduser}
        };
        $http({
            method: 'POST',
            url: 'sendRequest',
            data: groupRequest,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (response) {
            if (response == '' || response == null) {
                swal("Info!", "You're either in this group already or an invitation it still on its way!", "info");
            } else {
                swal("Good job!", "Request successfully sent!", "success");
            }
            ;
        });
    };


    //Make friendship from wall.
    $scope.addThisFriend = function (id) {

        var addFriend = {
            userOneId: {iduser: $scope.user.iduser},
            userTwoId: {iduser: id},
            actionUserId: {iduser: $scope.user.iduser}
        };

        $http({
            method: 'POST',
            url: 'makeFriend',
            data: addFriend,
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

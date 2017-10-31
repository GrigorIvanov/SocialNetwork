var app = angular.module('app');

app.controller('groupController', function ($scope, $localStorage, ngDialog, $http, $state, $window, $uibModal, $uibModalStack, $location) {

    //Open modal in order to create a new group.
    $scope.OpenModalToCreateANewGroup = function () {
        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'webapp/resources/static/modal/createGroupModal.html',
            size: 'md',
            controller: function ($scope) {
                $scope.groupModel;
            }
        });

    };

    $scope.showByMember = false;
    $scope.showPendingGroupsByMember = false;
    $scope.showGlobalGroups = true;


    //Preloader
    $scope.preloaderAfterAcceptFriend = false;

    //Show groups
    $scope.group = {};
    $scope.groups = [];
    $http({
        method: 'GET',
        url: 'showGroups',
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.groups = data;
    });



    //Get subjects
    $scope.sub = {};
    $scope.subs = [];
    $http({
        method: 'GET',
        url: 'getSubjects',
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.subs = data;
    });


    //Get user logged in
    $scope.user = $localStorage.userLogged;
    $scope.groupModel = {
        creator: {iduser: $scope.user.iduser}
    };
    //Create a a new group
    $scope.createNewGroup = function () {
        $scope.preloaderAfterAcceptFriend = true;
        $scope.showGlobalGroups = true;
        $http({
            method: 'POST',
            url: 'createGroup',
            data: $scope.groupModel,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $uibModalStack.dismissAll();
            $window.location.reload();
        });
    };

    $scope.cancel = function () {
        $uibModalStack.dismissAll();
    };



    $scope.initialStateGroups = function () {
        $http({
            method: 'GET',
            url: 'showGroups',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.groups = data;
            $window.location.reload();
        });

    };


    //Show groups by creator
    $scope.groupsCreator = [];
    $scope.myCreatedGroups = function (item) {

        $http({
            method: 'GET',
            url: 'showGroupsByCreator?id=' + item,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {

            $localStorage.groupsCreator = data;

            if (data.length == 0) {
                sweetAlert("Oops...", "Something went wrong! It seems that you haven't created any groups", "error");
                $state.go('group');
            } else {
                $state.go('mycreatedgroups');
            }

        });
    };

    //Create object to send a request.
    $scope.groupItem = {
        member: {iduser: $scope.user.iduser},
        groupId: {idGroup: 0},
        creator: {iduser: 0}
    };

    $scope.CloseModal = function () {
        ngDialog.closeAll();
        $window.location.reload();
    };
    //Send request to group's owner
    $scope.sendRequestToOwner = function (item, id) {
        $scope.groupItem.groupId.idGroup = item;
        $scope.groupItem.creator.iduser = id;
        $http({
            method: 'POST',
            url: 'sendRequest',
            data: $scope.groupItem,
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

    //Show groups by member

    $scope.groupsMember = [];
    $scope.showGroupsByMember = function (item) {
        $scope.showByMember = true;
        $scope.showGlobalGroups = false;
        $scope.showPendingGroupsByMember = false;

        $http({
            method: 'GET',
            url: 'showMemberGroups?id=' + item,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.groupsMember = data;
            if ($scope.groupsMember.length == 0) {

                swal({
                    title: "Ops!",
                    text: "It seems that you're not member of any groups yet!",
                    type: "info",
                    showCancelButton: false,
                    confirmButtonColor: "#5bc0de",
                    closeOnConfirm: false
                },
                        function () {
                            $window.location.reload();
                        });
            } else {
                $scope.showGlobalGroups = false;
            }
            ;
        });
    };

    //Show groups by member that is pending
    $scope.groupsMemberPending = [];
    $scope.showGroupsByMemberPending = function (item) {
        $scope.showByMember = false;
        $scope.showGlobalGroups = false;
        $scope.showPendingGroupsByMember = true;
        $http({
            method: 'GET',
            url: 'showMemberGroupsPending?id=' + item,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.groupsMemberPending = data;
            if ($scope.groupsMemberPending.length == 0) {

                swal({
                    title: "All right!",
                    text: "No pending invitations!",
                    type: "success",
                    showCancelButton: false,
                    confirmButtonColor: "#5cb85c",
                    closeOnConfirm: false
                },
                        function () {
                            $window.location.reload();
                        });

            } else {
                $scope.showGlobalGroups = false;
            }
            ;

        });
    };

    //Inside group
    $scope.groupById = {};
    $scope.prepareToCreateTopic = function (idgroup) {
        $http({
            method: 'GET',
            url: 'showGroupsById?id=' + idgroup,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $localStorage.groupById = data;
            $state.go('groupinside');
        });
    };

    //Leave group
    $scope.leaveGroup = function (id, index) {

        swal({
            title: "Are you sure?",
            text: "This group is going to be deleted...!",
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
                        $http({
                            method: 'DELETE',
                            url: 'leaveGroup?id=' + id,
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        });
                        $scope.groupsMember.splice(index, 1);

                        swal("Left!", "You can go send another invitation.", "success");
                    } else {
                        swal("Cancelled", "All right :)", "error");
                    }
                });
    };

    //Decline invitation
    $scope.leaveInvitation = function (id, index) {
        swal({
            title: "Are you sure?",
            text: "This invitation will be...!",
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
                        $http({
                            method: 'DELETE',
                            url: 'leaveInvitation?id=' + id,
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        });
                        $scope.groupsMemberPending.splice(index, 1);

                        swal("Deleted!", "You can go send another invitation.", "success");
                    } else {
                        swal("Cancelled", "All right :)", "error");
                    }
                });
    };

    $scope.NotAMemberYet = function () {
        swal("Not a member yet!", "It's still not possible to check this group's content.", "info");
    };
});


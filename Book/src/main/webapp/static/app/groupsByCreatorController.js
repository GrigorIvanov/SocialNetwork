var app = angular.module('app');

app.controller('groupsByCreatorController', function ($scope, $localStorage, $http, $uibModal, $state) {

    $scope.groups = $localStorage.groupsCreator;
    $scope.user = $localStorage.userLogged;


    $scope.pendingInvitations = true;
    $scope.NotificationInvitations = false;
    $scope.openEditGroup = false;


    $scope.update = {
        creator: {}
    };

    //Hide and show stuff when clicked to either update or go back
    $scope.editGroup = function (item) {
        $scope.pendingInvitations = false;
        $scope.openEditGroup = true;
        $scope.NotificationInvitations = false;


        $scope.update = item;

    };
    //Upadte group
    $scope.updateGroup = function () {
        $http({
            method: 'POST',
            url: 'editGroup',
            data: $scope.update,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            sweetAlert("Good job!", "Group updated!", "success");
        });
    };




//Clearing the model.
    $scope.cancel = function () {
        $scope.update = "";
    };


    //MODAL GROUPS CREATED BY MEMBER
    $scope.groupDetails = function (group) {
        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'webapp/resources/static/modal/groupDetails.html',
            size: 'lg',
            controller: function ($scope) {

                $scope.memberInGroupsPending = [];

                //Get group by id
                $http({

                    method: 'GET',
                    url: 'showGroupsById?id=' + group,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (data) {
                    $scope.groupCreated = data;
                });

                //Show pending member in group. NEED TO CHANGE THIS.
                $http({

                    method: 'GET',
                    url: 'showPendingRequestByCreator?id=' + group,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (data) {
                    $scope.memberInGroupsPending = data;
                    $scope.totalPending = $scope.memberInGroupsPending.length;
                    if ($scope.memberInGroupsPending.length == 0) {
                        $scope.NotificationInvitations = true;
                        $scope.pendingInvitations = false;
                    } else {
                        $scope.pendingInvitations = true;
                        $scope.NotificationInvitations = false;
                    }

                });

                //ACCEPT A MEMBER
                $scope.acceptInvitation = function (item, index) {
                    $http({
                        method: 'POST',
                        url: 'acceptMember?id=' + item,
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    $scope.memberInGroupsPending.splice(index, 1);
                    if ($scope.memberInGroupsPending.length == 0) {
                        $scope.NotificationInvitations = true;
                    } else {
                        $scope.NotificationInvitations = false;
                    }
                };

                //NO NO MEMBER.
                $scope.declineInvitation = function (item, index) {
                    $http({
                        method: 'POST',
                        url: 'declineMember?id=' + item,
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    $scope.memberInGroupsPending.splice(index, 1);
                    if ($scope.memberInGroupsPending.lenght == 0) {
                        $scope.NotificationInvitations = true;
                    } else {
                        $scope.NotificationInvitations = false;
                    }
                };

                //MEMBERS IN THIS GROUP
                $scope.membersInGroup = [];
                $http({
                    method: 'GET',
                    url: 'getMembersInsideGroup?id=' + group,
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }).success(function (data) {
                    $scope.membersInGroup = data;
                    $scope.totalMembers = $scope.membersInGroup.length;
                });


                //Remove group
                $scope.deleteGroup = function (item) {
                    swal({
                        title: "Are you sure?",
                        text: "All the group's content will be removed!",
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
                                        method: 'PUT',
                                        url: 'removeGroup',
                                        data: item,
                                        headers: {
                                            'Content-Type': 'application/json'
                                        }
                                    });
                                    $state.go('mycreatedgroups');
                                    swal("Deleted!", "This group has been deleted.", "success");
                                } else {
                                    swal("Cancelled", "Group is safe :)", "error");
                                }
                            });
                };



            }
        });
    };

});




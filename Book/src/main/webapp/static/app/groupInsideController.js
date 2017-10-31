var app = angular.module('app');

app.controller('groupInsideController', function ($scope, $localStorage, $http, $window, $uibModal, $uibModalStack, $location) {
    $scope.group = $localStorage.groupById;
    $scope.user = $localStorage.userLogged;

    $scope.topic = {
        groupId: {idGroup: $scope.group.idGroup},
        user: {iduser: $scope.user.iduser}
    };

    //Members in groups
    $scope.membersInGroup = [];
    $http({
        method: 'GET',
        url: 'getMembersInsideGroup?id=' + $scope.group.idGroup,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.membersInGroup = data;
        $scope.totalMembers = $scope.membersInGroup.length;
    });

    //Topics by group selected
    $scope.topics = [];
    $http({
        method: 'GET',
        url: 'showTopicsByGroup?id=' + $scope.group.idGroup,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.topics = data;
        $scope.totalTopics = $scope.topics.length;
    });

    //Comment in selected topic
    $scope.commentThisTopic = function (topic, content) {

        $scope.comment = {
            user: {iduser: $scope.user.iduser},
            topic: {idGroupInside: topic.idGroupInside},
            content: content
        };

        $http({
            method: 'POST',
            url: 'commentInTopic',
            data: $scope.comment,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (response) {
            $window.location.reload();
        });
    };

    //Array comments
    $scope.topicComments = [];

    //Hide/Show comments
    $scope.hideComments = true;
    $scope.showComments = true;

    //Show comments by topic when clicked
    $scope.showCommentsInThisTopic = function (item) {
        $scope.hideComments = false;
        $scope.showComments = false;
        $scope.idTopic = item;

        $http({
            method: 'GET',
            url: 'getComments?id=' + item.idGroupInside,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.totalComments = data.length;
            $scope.topicComments = data;
        });
    };

    //Back to initial state
    $scope.back = function () {
        $scope.showComments = true;
        $scope.hideComments = true;
        $scope.idTopic = 0;
    };


    //Answer in selected question
    $scope.answerThisQuestion = function (question, answerContent) {

        $scope.answer = {
            user: {iduser: $scope.user.iduser},
            question: {idQuestion: question.idQuestion},
            answerContent: answerContent
        };

        $http({
            method: 'POST',
            url: 'createAnswer',
            data: $scope.answer,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (response) {
            $window.location.reload();
        });
    };

    //Array comments
    $scope.questionAnswers = [];

    //Hide/Show comments
    $scope.hideAnswers = true;
    $scope.showAnswers = true;

    //Show comments by topic when clicked
    $scope.showAnswersInThisQuestion = function (item) {
        $scope.showAnswers = false;
        $scope.hideAnswers = false;
        $scope.idQuestion = item;

        $http({
            method: 'GET',
            url: 'getAnswers?id=' + item.idQuestion,
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data) {
            $scope.totalAnswers = data.length;
            $scope.questionAnswers = data;
        });
    };
    
        //Back to initial state
    $scope.backAgain = function () {
        $scope.showAnswers = true;
        $scope.hideAnswers = true;
        $scope.idQuestion = 0;
    };

    //Questions by group selected
    $scope.questions = [];
    $http({
        method: 'GET',
        url: 'showQuestionsByGroup?id=' + $scope.group.idGroup,
        headers: {
            'Content-Type': 'application/json'
        }
    }).success(function (data) {
        $scope.questions = data;
        $scope.totalQuestions = $scope.questions.length;
    });

    //Create a new discussion
    $scope.openToCreateTopic = function () {
        var getTopicModel = $scope.topic;
        $scope.content;
        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'webapp/resources/static/modal/createTopic.html',
            size: 'md',
            controller: function ($scope) {
                //Create new topic
                $scope.createTopic = function () {
                    var newTopic = {
                        content: $scope.content,
                        groupId: {idGroup: getTopicModel.groupId.idGroup},
                        user: {iduser: getTopicModel.user.iduser}
                    };
                    $http({
                        method: 'POST',
                        url: 'createNewTopic',
                        data: newTopic,
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).success(function (data) {
                        $window.location.reload();
                    });
                };

                $scope.Close = function () {
                    $uibModalStack.dismissAll();
                };
            }
        });
    };

    //Create a new discussion
    $scope.openToCreateQuestion = function () {
        var getTopicModel = $scope.topic;
        $scope.question;
        $uibModal.open({
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'webapp/resources/static/modal/createQuestion.html',
            size: 'md',
            controller: function ($scope) {
                //Create new topic
                $scope.createQuestion = function () {
                    var newQuestion = {
                        question: $scope.question,
                        groupId: {idGroup: getTopicModel.groupId.idGroup},
                        user: {iduser: getTopicModel.user.iduser}
                    };
                    $http({
                        method: 'POST',
                        url: 'createNewQuestion',
                        data: newQuestion,
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }).success(function (data) {
                        $window.location.reload();
                    });
                };

                $scope.Close = function () {
                    $uibModalStack.dismissAll();
                };
            }
        });
    };
});



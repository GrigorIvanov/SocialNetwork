var app = angular.module('app', ['ui.router'], ['ngDialog'], ['ngStorage'], ['ui.bootstrap'], ['720kb.tooltips']);

angular.module('app', ['ui.router', 'ngDialog', 'ngStorage', 'ui.bootstrap'])
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider',
            function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise('/');

                $stateProvider
                        .state('login', {
                            url: '/',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/login/login.html'
                                }
                            }
                        })
                        .state('signup', {
                            url: '/signup',
                            views: {
                                'main@': {
                                    templateUrl: 'webapp/resources/static/views/login/signup.html'}
                            }
                        })
                        .state('home', {
                            url: '/home',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/wall.html'
                                }
                            }
                        })
                        .state('profile', {
                            url: '/profile',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/profile.html'
                                }
                            }
                        })
                        .state('friends', {
                            url: '/friends',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/friends.html'
                                }
                            }
                        })
                        .state('people', {
                            url: '/people',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/users.html'
                                }
                            }
                        })

                        .state('messages', {
                            url: '/messages',
                            cache: false,
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/messages.html'
                                }

                            }
                        })
                        .state('wall', {
                            url: '/wall',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/wall.html'
                                }
                            }
                        })
                        .state('group', {
                            url: '/group',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/group.html'
                                }
                            }
                        })
                        .state('groupinside', {
                            url: '/groupinside',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/groupinside.html'
                                }
                            }
                        })
                        .state('mycreatedgroups', {
                            url: '/mycreatedgroups',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/mycreatedgroups.html'
                                }
                            }
                        })
                        .state('groupsmember', {
                            url: '/groupsmember',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/groupsmember.html'
                                }
                            }
                        })
                        .state('groupsmemberpending', {
                            url: '/groupsmemberpending',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/groupsmemberpending.html'
                                }
                            }
                        })
                        .state('myprofile', {
                            url: '/myprofile',
                            views: {
                                'sidebar@': {
                                    templateUrl: 'webapp/resources/static/views/home/sidebar.html'
                                },
                                'container@': {
                                    templateUrl: 'webapp/resources/static/views/home/myprofile.html'
                                }
                            }
                        });



            }
        ]);
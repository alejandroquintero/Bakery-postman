/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('bakerModule', ['ngCrud', 'ui.router']);

    mod.constant('bakerModel', {
        name: 'baker',
        displayName: 'Baker',
        url: 'bakers',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/baker/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('baker', {
                url: '/bakers?page&limit',
                abstract: true,
                
                views: {
                     mainView: {
                        templateUrl: basePath + 'baker.tpl.html',
                        controller: 'bakerCtrl'
                    }
                },
                resolve: {
                    model: 'bakerModel',
                    bakers: ['Restangular', 'model', '$stateParams', function (r, model, $params) {
                            return r.all(model.url).getList($params);
                        }]
                }
            });
            $sp.state('bakerList', {
                url: '/list',
                parent: 'baker',
                views: {
                     bakerView: {
                        templateUrl: basePath + 'list/baker.list.tpl.html',
                        controller: 'bakerListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('bakerNew', {
                url: '/new',
                parent: 'baker',
                views: {
                    bakerView: {
                        templateUrl: basePath + 'new/baker.new.tpl.html',
                        controller: 'bakerNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('bakerInstance', {
                url: '/{bakerId:int}',
                abstract: true,
                parent: 'baker',
                views: {
                    bakerView: {
                        template: '<div ui-view="bakerInstanceView"></div>'
                    }
                },
                resolve: {
                    baker: ['bakers', '$stateParams', function (bakers, $params) {
                            return bakers.get($params.bakerId);
                        }]
                }
            });
            $sp.state('bakerDetail', {
                url: '/details',
                parent: 'bakerInstance',
                views: {
                    bakerInstanceView: {
                        templateUrl: baseInstancePath + 'detail/baker.detail.tpl.html',
                        controller: 'bakerDetailCtrl'
                    }
                }
            });
            $sp.state('bakerEdit', {
                url: '/edit',
                sticky: true,
                parent: 'bakerInstance',
                views: {
                    bakerInstanceView: {
                        templateUrl: baseInstancePath + 'edit/baker.edit.tpl.html',
                        controller: 'bakerEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('bakerDelete', {
                url: '/delete',
                parent: 'bakerInstance',
                views: {
                    bakerInstanceView: {
                        templateUrl: baseInstancePath + 'delete/baker.delete.tpl.html',
                        controller: 'bakerDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);

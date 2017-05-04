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
    var mod = ng.module('specialOfferModule', ['ngCrud', 'ui.router']);

    mod.constant('specialOfferModel', {
        name: 'specialOffer',
        displayName: 'Special Offer',
        url: 'specialOffer',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            description: {
                displayName: 'Description',
                type: 'String',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/specialOffer/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('specialOffer', {
                url: '/specialOffer?page&limit',
                abstract: true,
                parent: 'productDetail',
                views: {
                     productChieldView: {
                        templateUrl: basePath + 'specialOffer.tpl.html',
                        controller: 'specialOfferCtrl'
                    }
                },
                resolve: {
                    model: 'specialOfferModel',
                    specialOffers: ['product', '$stateParams', 'model', function (product, $params, model) {
                            return product.getList(model.url, $params);
                        }]                }
            });
            $sp.state('specialOfferList', {
                url: '/list',
                parent: 'specialOffer',
                views: {
                     'productInstanceView@productInstance': {
                        templateUrl: basePath + 'list/specialOffer.list.tpl.html',
                        controller: 'specialOfferListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('specialOfferNew', {
                url: '/new',
                parent: 'specialOffer',
                views: {
                    'productInstanceView@productInstance': {
                        templateUrl: basePath + 'new/specialOffer.new.tpl.html',
                        controller: 'specialOfferNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('specialOfferInstance', {
                url: '/{specialOfferId:int}',
                abstract: true,
                parent: 'specialOffer',
                views: {
                    'productInstanceView@productInstance': {
                        template: '<div ui-view="specialOfferInstanceView"></div>'
                    }
                },
                resolve: {
                    specialOffer: ['specialOffers', '$stateParams', function (specialOffers, $params) {
                            return specialOffers.get($params.specialOfferId);
                        }]
                }
            });
            $sp.state('specialOfferDetail', {
                url: '/details',
                parent: 'specialOfferInstance',
                views: {
                    specialOfferInstanceView: {
                        templateUrl: baseInstancePath + 'detail/specialOffer.detail.tpl.html',
                        controller: 'specialOfferDetailCtrl'
                    }
                }
            });
            $sp.state('specialOfferEdit', {
                url: '/edit',
                sticky: true,
                parent: 'specialOfferInstance',
                views: {
                    specialOfferInstanceView: {
                        templateUrl: baseInstancePath + 'edit/specialOffer.edit.tpl.html',
                        controller: 'specialOfferEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('specialOfferDelete', {
                url: '/delete',
                parent: 'specialOfferInstance',
                views: {
                    specialOfferInstanceView: {
                        templateUrl: baseInstancePath + 'delete/specialOffer.delete.tpl.html',
                        controller: 'specialOfferDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
	}]);
})(window.angular);

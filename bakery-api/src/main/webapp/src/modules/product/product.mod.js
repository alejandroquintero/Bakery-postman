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
    var mod = ng.module('productModule', ['ngCrud', 'ui.router']);

    mod.constant('productModel', {
        name: 'product',
        displayName: 'Product',
        url: 'products',
        fields: {
            name: {
                displayName: 'Name',
                type: 'String',
                required: true
            },
            image: {
                displayName: 'Image',
                type: 'String',
                required: true
            },
            price: {
                displayName: 'Price',
                type: 'Long',
                required: true
            }        }
    });

    mod.config(['$stateProvider',
        function($sp){
            var basePath = 'src/modules/product/';
            var baseInstancePath = basePath + 'instance/';

            $sp.state('product', {
                url: '/products?page&limit',
                abstract: true,
                parent: 'bakerDetail',
                views: {
                     bakerChieldView: {
                        templateUrl: basePath + 'product.tpl.html',
                        controller: 'productCtrl'
                    }
                },
                resolve: {
                    model: 'productModel',
                    products: ['baker', '$stateParams', 'model', function (baker, $params, model) {
                            return baker.getList(model.url, $params);
                        }]                }
            });
            $sp.state('productList', {
                url: '/list',
                parent: 'product',
                views: {
                     'bakerInstanceView@bakerInstance': {
                        templateUrl: basePath + 'list/product.list.tpl.html',
                        controller: 'productListCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('productNew', {
                url: '/new',
                parent: 'product',
                views: {
                    'bakerInstanceView@bakerInstance': {
                        templateUrl: basePath + 'new/product.new.tpl.html',
                        controller: 'productNewCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('productInstance', {
                url: '/{productId:int}',
                abstract: true,
                parent: 'product',
                views: {
                    'bakerInstanceView@bakerInstance': {
                        template: '<div ui-view="productInstanceView"></div>'
                    }
                },
                resolve: {
                    product: ['products', '$stateParams', function (products, $params) {
                            return products.get($params.productId);
                        }]
                }
            });
            $sp.state('productDetail', {
                url: '/details',
                parent: 'productInstance',
                views: {
                    productInstanceView: {
                        templateUrl: baseInstancePath + 'detail/product.detail.tpl.html',
                        controller: 'productDetailCtrl'
                    }
                }
            });
            $sp.state('productEdit', {
                url: '/edit',
                sticky: true,
                parent: 'productInstance',
                views: {
                    productInstanceView: {
                        templateUrl: baseInstancePath + 'edit/product.edit.tpl.html',
                        controller: 'productEditCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('productDelete', {
                url: '/delete',
                parent: 'productInstance',
                views: {
                    productInstanceView: {
                        templateUrl: baseInstancePath + 'delete/product.delete.tpl.html',
                        controller: 'productDeleteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
            $sp.state('productCategory', {
                url: '/category',
                parent: 'productDetail',
                abstract: true,
                views: {
                    productChieldView: {
                        template: '<div ui-view="productCategoryView"></div>'
                    }
                },
                resolve: {
                    category: ['product', function (product) {
                            return product.getList('category');
                        }],
                    model: 'categoryModel'
                }
            });
            $sp.state('productCategoryList', {
                url: '/list',
                parent: 'productCategory',
                views: {
                    productCategoryView: {
                        templateUrl: baseInstancePath + 'category/list/product.category.list.tpl.html',
                        controller: 'productCategoryListCtrl'
                    }
                }
            });
            $sp.state('productCategoryEdit', {
                url: '/edit',
                parent: 'productCategory',
                views: {
                    productCategoryView: {
                        templateUrl: baseInstancePath + 'category/edit/product.category.edit.tpl.html',
                        controller: 'productCategoryEditCtrl',
                        controllerAs: 'ctrl'
                    }
                },
                resolve: {
                    pool: ['Restangular', 'model', function (r, model) {
                            return r.all(model.url).getList();
                        }]
                }
            });
	}]);
})(window.angular);

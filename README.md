# Tabla de contenidos
-  [Introducción](#introducción)
-  [API](#api-de-la-aplicación-bakery)
  - [Recurso Baker](#recurso-baker)
    - [GET /bakers](#GET-/bakers)
    - [GET /bakers/{id}](#GET-/bakers/{id})
    - [POST /bakers](#POST-/bakers)
    - [PUT /bakers/{id}](#PUT-/bakers/{id})
    - [DELETE /bakers/{id}](#DELETE-/bakers/{id})
    - [GET bakers/{bakersid}/products](#GET-bakers/{bakersid}/products)
    - [GET bakers/{bakersid}/products/{productsid}](#GET-bakers/{bakersid}/products/{productsid})
    - [POST bakers/{bakersid}/products/{productsid}](#POST-bakers/{bakersid}/products/{productsid})
    - [PUT bakers/{bakersid}/products](#PUT-bakers/{bakersid}/products)
    - [DELETE bakers/{bakersid}/products/{productsid}](#DELETE-bakers/{bakersid}/products/{productsid}])
  - [Recurso Category](#recurso-category)
    - [GET /categorys](#GET-/categorys)
    - [GET /categorys/{id}](#GET-/categorys/{id})
    - [POST /categorys](#POST-/categorys)
    - [PUT /categorys/{id}](#PUT-/categorys/{id})
    - [DELETE /categorys/{id}](#DELETE-/categorys/{id})

# API Rest
## Introducción
La comunicación entre cliente y servidor se realiza intercambiando objetos JSON. Para cada entidad se hace un mapeo a JSON, donde cada uno de sus atributos se transforma en una propiedad de un objeto JSON. Todos los servicios se generan en la URL /Bakery.api/api/. Por defecto, todas las entidades tienen un atributo `id`, con el cual se identifica cada registro:

```javascript
{
    id: '',
    attribute_1: '',
    attribute_2: '',
    ...
    attribute_n: ''
}
```

Cuando se transmite información sobre un registro específico, se realiza enviando un objeto con la estructura mencionada en la sección anterior.
La única excepción se presenta al solicitar al servidor una lista de los registros en la base de datos, que incluye información adicional para manejar paginación de lado del servidor en el header `X-Total-Count` y los registros se envían en el cuerpo del mensaje como un arreglo.

La respuesta del servidor al solicitar una colección presenta el siguiente formato:

```javascript
[{}, {}, {}, {}, {}, {}]
```

## API de la aplicación Bakery
### Recurso Baker
El objeto Baker tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```




#### GET /bakers

Retorna una colección de objetos Baker en representación Detail.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-baker)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /bakers/{id}

Retorna una colección de objetos Baker en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Baker a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Baker en [representaciones Detail](#recurso-baker)
404|No existe un objeto Baker con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /bakers

Es el encargado de crear objetos Baker.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Baker que será creado|Sí|[Representación Detail](#recurso-baker)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Baker ha sido creado|[Representación Detail](#recurso-baker)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Baker|Mensaje de error

#### PUT /bakers/{id}

Es el encargado de actualizar objetos Baker.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Baker a actualizar|Sí|Integer
body|body|Objeto Baker nuevo|Sí|[Representación Detail](#recurso-baker)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Baker actualizado|[Representación Detail](#recurso-baker)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Baker|Mensaje de error

#### DELETE /bakers/{id}

Elimina un objeto Baker.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Baker a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error


#### GET bakers/{bakersid}/products

Retorna una colección de objetos Product asociados a un objeto Baker en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Baker a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de objetos Product en [representación Detail](#recurso-product)
500|Error consultando products |Mensaje de error

#### GET bakers/{bakersid}/products/{productsid}

Retorna un objeto Product asociados a un objeto Baker en representación Detail.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
bakersid|Path|ID del objeto Baker a consultar|Sí|Integer
productsid|Path|ID del objeto Product a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Product en [representación Detail](#recurso-product)
404|No existe un objeto Product con el ID solicitado asociado al objeto Baker indicado |Mensaje de error
500|Error interno|Mensaje de error

#### POST bakers/{bakersid}/products/{productsid}

Asocia un objeto Product a un objeto Baker.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
bakersid|PathParam|ID del objeto Baker al cual se asociará el objeto Product|Sí|Integer
productsid|PathParam|ID del objeto Product a asociar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Objeto Product asociado|[Representación Detail de Product](#recurso-product)
500|No se pudo asociar el objeto Product|Mensaje de error

#### PUT bakers/{bakersid}/products

Es el encargado de actualizar un objeto Product asociada a un objeto Baker.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
bakersid|Path|ID del objeto Baker cuya colección será remplazada|Sí|Integer
body|body|Colección de objetos Product|Sí|[Representación Detail](#recurso-product)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|Se actualizo el objeto|Objeto Product en [Representación Detail](#recurso-product)
500|No se pudo actualizar|Mensaje de error

#### DELETE bakers/{bakersid}/products/{productsid}

Remueve un objeto Product asociado a un objeto Baker.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
bakersid|Path|ID del objeto Baker asociado al objeto Product|Sí|Integer
productsid|Path|ID del objeto Product a remover|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto removido|N/A
500|Error interno|Mensaje de error


[Volver arriba](#tabla-de-contenidos)
### Recurso Category
El objeto Category tiene 2 representaciones JSON:	

#### Representación Minimum
```javascript
{
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/
}
```

#### Representación Detail
```javascript
{
    // todo lo de la representación Minimum más los objetos Minimum con relación simple.
    parentCategory: {
    id: '' /*Tipo Long*/,
    name: '' /*Tipo String*/    }
}
```



#### GET /categorys

Retorna una colección de objetos Category en representación Detail.
Cada Category en la colección tiene embebidos los siguientes objetos: Category.

#### Parámetros

#### N/A

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Colección de [representaciones Detail](#recurso-category)
409|Un objeto relacionado no existe|Mensaje de error
500|Error interno|Mensaje de error

#### GET /categorys/{id}

Retorna una colección de objetos Category en representación Detail.
Cada Category en la colección tiene los siguientes objetos: Category.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Category a consultar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
200|OK|Objeto Category en [representaciones Detail](#recurso-category)
404|No existe un objeto Category con el ID solicitado|Mensaje de error
500|Error interno|Mensaje de error

#### POST /categorys

Es el encargado de crear objetos Category.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
body|body|Objeto Category que será creado|Sí|[Representación Detail](#recurso-category)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Category ha sido creado|[Representación Detail](#recurso-category)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo crear el objeto Category|Mensaje de error

#### PUT /categorys/{id}

Es el encargado de actualizar objetos Category.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Category a actualizar|Sí|Integer
body|body|Objeto Category nuevo|Sí|[Representación Detail](#recurso-category)

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
201|El objeto Category actualizado|[Representación Detail](#recurso-category)
409|Un objeto relacionado no existe|Mensaje de error
500|No se pudo actualizar el objeto Category|Mensaje de error

#### DELETE /categorys/{id}

Elimina un objeto Category.

#### Parámetros

Nombre|Ubicación|Descripción|Requerido|Esquema
:--|:--|:--|:--|:--
id|Path|ID del objeto Category a eliminar|Sí|Integer

#### Respuesta

Código|Descripción|Cuerpo
:--|:--|:--
204|Objeto eliminado|N/A
500|Error interno|Mensaje de error



[Volver arriba](#tabla-de-contenidos)


## uniandes 2017
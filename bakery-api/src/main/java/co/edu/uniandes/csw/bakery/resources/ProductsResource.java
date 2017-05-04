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
package co.edu.uniandes.csw.bakery.resources;

import co.edu.uniandes.csw.auth.provider.StatusCreated;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.bakery.api.IProductLogic;
import co.edu.uniandes.csw.bakery.dtos.detail.ProductDetailDTO;
import co.edu.uniandes.csw.bakery.entities.ProductEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: bakers/{bakersId: \\d+}/products
 * @generated
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {

    @Inject private IProductLogic productLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;
    @PathParam("bakersId") private Long bakersId;

   
    /**
     * Convierte una lista de ProductEntity a una lista de ProductDetailDTO
     *
     * @param entityList Lista de ProductEntity a convertir
     * @return Lista de ProductDetailDTO convertida
     * @generated
     */
    private List<ProductDetailDTO> listEntity2DTO(List<ProductEntity> entityList){
        List<ProductDetailDTO> list = new ArrayList<>();
        for (ProductEntity entity : entityList) {
            list.add(new ProductDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Product asociados a un Baker
     *
     * @return Colección de objetos de ProductDetailDTO
     * @generated
     */
    @GET
    public List<ProductDetailDTO> getProductss() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", productLogic.countProducts());
            return listEntity2DTO(productLogic.getProducts(page, maxRecords, bakersId));
        }
        return listEntity2DTO(productLogic.getProducts(bakersId));
    }

    /**
     * Obtiene los datos de una instancia de Product a partir de su ID asociado a un Baker
     *
     * @param productsId Identificador de la instancia a consultar
     * @return Instancia de ProductDetailDTO con los datos del Product consultado
     * @generated
     */
    @GET
    @Path("{productsId: \\d+}")
    public ProductDetailDTO getProducts(@PathParam("productsId") Long productsId) {
        ProductEntity entity = productLogic.getProduct(productsId);
        if (entity.getBaker() != null && !bakersId.equals(entity.getBaker().getId())) {
            throw new WebApplicationException(404);
        }
        return new ProductDetailDTO(entity);
    }

    /**
     * Asocia un Product existente a un Baker
     *
     * @param dto Objeto de ProductDetailDTO con los datos nuevos
     * @return Objeto de ProductDetailDTOcon los datos nuevos y su ID.
     * @generated
     */
    @POST
    @StatusCreated
    public ProductDetailDTO createProducts(ProductDetailDTO dto) {
        return new ProductDetailDTO(productLogic.createProduct(bakersId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Product.
     *
     * @param productsId Identificador de la instancia de Product a modificar
     * @param dto Instancia de ProductDetailDTO con los nuevos datos.
     * @return Instancia de ProductDetailDTO con los datos actualizados.
     * @generated
     */
    @PUT
    @Path("{productsId: \\d+}")
    public ProductDetailDTO updateProducts(@PathParam("productsId") Long productsId, ProductDetailDTO dto) {
        ProductEntity entity = dto.toEntity();
        entity.setId(productsId);
        ProductEntity oldEntity = productLogic.getProduct(productsId);
        entity.setCategory(oldEntity.getCategory());
        return new ProductDetailDTO(productLogic.updateProduct(bakersId, entity));
    }

    /**
     * Elimina una instancia de Product de la base de datos.
     *
     * @param productId Identificador de la instancia a eliminar.
     * @generated
     */
    @DELETE
    @Path("productsId: \\d+}")
    public void deleteProducts(@PathParam("productsId") Long productsId) {
        productLogic.deleteProduct(productsId);
    }
    public void existsProduct(Long productsId){
        ProductDetailDTO product = getProducts(productsId);
        if (product== null) {
            throw new WebApplicationException(404);
        }
    }
    
    public void validateProductParent(Long productsId){
        ProductDetailDTO product = getProducts(productsId);
        if (product.getBaker() == null || !bakersId.equals(product.getBaker().getId())) {
            throw new WebApplicationException(404);
        }
    }
    
    @Path("{productsId: \\d+}/specialOffer")
    public Class<SpecialOfferResource> getSpecialOfferResource(@PathParam("productsId") Long productsId){
        existsProduct(productsId);
        validateProductParent(productsId);
        return SpecialOfferResource.class;
    }
    
    @Path("{productsId: \\d+}/category")
    public Class<ProductCategoryResource> getProductCategoryResource(@PathParam("productsId") Long productsId){
        existsProduct(productsId);
        return ProductCategoryResource.class;
    }
    
}

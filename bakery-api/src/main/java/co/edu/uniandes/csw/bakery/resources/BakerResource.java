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
import co.edu.uniandes.csw.bakery.api.IBakerLogic;
import co.edu.uniandes.csw.bakery.dtos.detail.BakerDetailDTO;
import co.edu.uniandes.csw.bakery.entities.BakerEntity;
import java.util.ArrayList;
import javax.ws.rs.WebApplicationException;

/**
 * URI: bakers/
 * @generated
 */
@Path("/bakers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BakerResource {

    @Inject private IBakerLogic bakerLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("limit") private Integer maxRecords;

   
    /**
     * Convierte una lista de BakerEntity a una lista de BakerDetailDTO.
     *
     * @param entityList Lista de BakerEntity a convertir.
     * @return Lista de BakerDetailDTO convertida.
     * @generated
     */
    private List<BakerDetailDTO> listEntity2DTO(List<BakerEntity> entityList){
        List<BakerDetailDTO> list = new ArrayList<>();
        for (BakerEntity entity : entityList) {
            list.add(new BakerDetailDTO(entity));
        }
        return list;
    }


    /**
     * Obtiene la lista de los registros de Baker
     *
     * @return Colección de objetos de BakerDetailDTO
     * @generated
     */
    @GET
    public List<BakerDetailDTO> getBakers() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", bakerLogic.countBakers());
            return listEntity2DTO(bakerLogic.getBakers(page, maxRecords));
        }
        return listEntity2DTO(bakerLogic.getBakers());
    }

    /**
     * Obtiene los datos de una instancia de Baker a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de BakerDetailDTO con los datos del Baker consultado
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public BakerDetailDTO getBaker(@PathParam("id") Long id) {
        return new BakerDetailDTO(bakerLogic.getBaker(id));
    }

    /**
     * Se encarga de crear un Baker en la base de datos
     *
     * @param dto Objeto de BakerDetailDTO con los datos nuevos
     * @return Objeto de BakerDetailDTOcon los datos nuevos y su ID
     * @generated
     */
    @POST
    @StatusCreated
    public BakerDetailDTO createBaker(BakerDetailDTO dto) {
        return new BakerDetailDTO(bakerLogic.createBaker(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Baker
     *
     * @param id Identificador de la instancia de Baker a modificar
     * @param dto Instancia de BakerDetailDTO con los nuevos datos
     * @return Instancia de BakerDetailDTO con los datos actualizados
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public BakerDetailDTO updateBaker(@PathParam("id") Long id, BakerDetailDTO dto) {
        BakerEntity entity = dto.toEntity();
        entity.setId(id);
        return new BakerDetailDTO(bakerLogic.updateBaker(entity));
    }

    /**
     * Elimina una instancia de Baker de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteBaker(@PathParam("id") Long id) {
        bakerLogic.deleteBaker(id);
    }
    public void existsBaker(Long bakersId){
        BakerDetailDTO baker = getBaker(bakersId);
        if (baker== null) {
            throw new WebApplicationException(404);
        }
    }
    
    
    @Path("{bakersId: \\d+}/products")
    public Class<ProductsResource> getProductsResource(@PathParam("bakersId") Long bakersId){
        existsBaker(bakersId);
        return ProductsResource.class;
    }
    
}

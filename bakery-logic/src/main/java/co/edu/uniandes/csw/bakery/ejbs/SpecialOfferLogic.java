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
package co.edu.uniandes.csw.bakery.ejbs;

import co.edu.uniandes.csw.bakery.api.ISpecialOfferLogic;
import co.edu.uniandes.csw.bakery.entities.SpecialOfferEntity;
import co.edu.uniandes.csw.bakery.persistence.SpecialOfferPersistence;
import co.edu.uniandes.csw.bakery.api.IProductLogic;
import co.edu.uniandes.csw.bakery.entities.ProductEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class SpecialOfferLogic implements ISpecialOfferLogic {

    @Inject private SpecialOfferPersistence persistence;

    @Inject
    private IProductLogic productLogic;

    /**
     * Obtiene el número de registros de SpecialOffer.
     *
     * @return Número de registros de SpecialOffer.
     * @generated
     */
    public int countSpecialOffers() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de SpecialOffer que pertenecen a un Product.
     *
     * @param productid id del Product el cual es padre de los SpecialOffers.
     * @return Colección de objetos de SpecialOfferEntity.
     * @generated
     */
    @Override
    public List<SpecialOfferEntity> getSpecialOffers(Long productid) {
        ProductEntity product = productLogic.getProduct(productid);
        return product.getSpecialOffer();
        
    }

    /**
     * Obtiene la lista de los registros de SpecialOffer que pertenecen a un Product indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @param productid id del Product el cual es padre de los SpecialOffers.
     * @return Colección de objetos de SpecialOfferEntity.
     * @generated
     */
    @Override
    public List<SpecialOfferEntity> getSpecialOffers(Integer page, Integer maxRecords, Long productid) {
        return persistence.findAll(page, maxRecords, productid);
    }

    /**
     * Obtiene los datos de una instancia de SpecialOffer a partir de su ID.
     *
     * @pre La existencia del elemento padre Product se debe garantizar.
     * @param specialOfferid) Identificador del SpecialOffer a consultar
     * @return Instancia de SpecialOfferEntity con los datos del SpecialOffer consultado.
     * @generated
     */
    @Override
    public SpecialOfferEntity getSpecialOffer(Long specialOfferid) {
        try {
            return persistence.find(specialOfferid);
        }catch(NoResultException e){
            throw new IllegalArgumentException("El SpecialOffer no existe");
        }
    }

    /**
     * Se encarga de crear un SpecialOffer en la base de datos.
     *
     * @param entity Objeto de SpecialOfferEntity con los datos nuevos
     * @param productid id del Product el cual sera padre del nuevo SpecialOffer.
     * @return Objeto de SpecialOfferEntity con los datos nuevos y su ID.
     * @generated
     */
    @Override
    public SpecialOfferEntity createSpecialOffer(Long productid, SpecialOfferEntity entity) {
        ProductEntity product = productLogic.getProduct(productid);
        entity.setProduct(product);
        entity = persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de SpecialOffer.
     *
     * @param entity Instancia de SpecialOfferEntity con los nuevos datos.
     * @param productid id del Product el cual sera padre del SpecialOffer actualizado.
     * @return Instancia de SpecialOfferEntity con los datos actualizados.
     * @generated
     */
    @Override
    public SpecialOfferEntity updateSpecialOffer(Long productid, SpecialOfferEntity entity) {
        ProductEntity product = productLogic.getProduct(productid);
        entity.setProduct(product);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de SpecialOffer de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param productid id del Product el cual es padre del SpecialOffer.
     * @generated
     */
    @Override
    public void deleteSpecialOffer(Long id) {
        SpecialOfferEntity old = getSpecialOffer(id);
        persistence.delete(old.getId());
    }
  
}

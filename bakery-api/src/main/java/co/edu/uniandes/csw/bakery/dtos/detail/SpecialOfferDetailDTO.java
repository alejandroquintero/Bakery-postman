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
package co.edu.uniandes.csw.bakery.dtos.detail;

import co.edu.uniandes.csw.bakery.dtos.minimum.*;
import co.edu.uniandes.csw.bakery.entities.SpecialOfferEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement
public class SpecialOfferDetailDTO extends SpecialOfferDTO{


    @PodamExclude
    private ProductDTO product;

    /**
     * @generated
     */
    public SpecialOfferDetailDTO() {
        super();
    }

    /**
     * Crea un objeto SpecialOfferDetailDTO a partir de un objeto SpecialOfferEntity incluyendo los atributos de SpecialOfferDTO.
     *
     * @param entity Entidad SpecialOfferEntity desde la cual se va a crear el nuevo objeto.
     * @generated
     */
    public SpecialOfferDetailDTO(SpecialOfferEntity entity) {
        super(entity);
        if (entity.getProduct()!=null){
        this.product = new ProductDTO(entity.getProduct());
        }
        
    }

    /**
     * Convierte un objeto SpecialOfferDetailDTO a SpecialOfferEntity incluyendo los atributos de SpecialOfferDTO.
     *
     * @return Nueva objeto SpecialOfferEntity.
     * @generated
     */
    @Override
    public SpecialOfferEntity toEntity() {
        SpecialOfferEntity entity = super.toEntity();
        if (this.getProduct()!=null){
        entity.setProduct(this.getProduct().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo product.
     *
     * @return atributo product.
     * @generated
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * Establece el valor del atributo product.
     *
     * @param product nuevo valor del atributo
     * @generated
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}

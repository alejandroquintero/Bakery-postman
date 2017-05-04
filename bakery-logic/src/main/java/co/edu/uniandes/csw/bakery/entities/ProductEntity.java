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
package co.edu.uniandes.csw.bakery.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import uk.co.jemos.podam.common.PodamExclude;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


/**
 * @generated
 */
@Entity
public class ProductEntity extends BaseEntity implements Serializable {

    private String image;

    private Long price;

    @PodamExclude
    @ManyToOne
    private BakerEntity baker;

    @PodamExclude
    @OneToMany
    private List<CategoryEntity> category = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<SpecialOfferEntity> specialOffer = new ArrayList<>();

    /**
     * Obtiene el atributo image.
     *
     * @return atributo image.
     * @generated
     */
    public String getImage(){
        return image;
    }

    /**
     * Establece el valor del atributo image.
     *
     * @param image nuevo valor del atributo
     * @generated
     */
    public void setImage(String image){
        this.image = image;
    }

    /**
     * Obtiene el atributo price.
     *
     * @return atributo price.
     * @generated
     */
    public Long getPrice(){
        return price;
    }

    /**
     * Establece el valor del atributo price.
     *
     * @param price nuevo valor del atributo
     * @generated
     */
    public void setPrice(Long price){
        this.price = price;
    }

    /**
     * Obtiene el atributo baker.
     *
     * @return atributo baker.
     * @generated
     */
    public BakerEntity getBaker() {
        return baker;
    }

    /**
     * Establece el valor del atributo baker.
     *
     * @param baker nuevo valor del atributo
     * @generated
     */
    public void setBaker(BakerEntity baker) {
        this.baker = baker;
    }

    /**
     * Obtiene la colección de category.
     *
     * @return colección category.
     * @generated
     */
    public List<CategoryEntity> getCategory() {
        return category;
    }

    /**
     * Establece el valor de la colección de category.
     *
     * @param category nuevo valor de la colección.
     * @generated
     */
    public void setCategory(List<CategoryEntity> category) {
        this.category = category;
    }

    /**
     * Obtiene la colección de specialOffer.
     *
     * @return colección specialOffer.
     * @generated
     */
    public List<SpecialOfferEntity> getSpecialOffer() {
        return specialOffer;
    }

    /**
     * Establece el valor de la colección de specialOffer.
     *
     * @param specialOffer nuevo valor de la colección.
     * @generated
     */
    public void setSpecialOffer(List<SpecialOfferEntity> specialoffer) {
        this.specialOffer = specialoffer;
    }
}

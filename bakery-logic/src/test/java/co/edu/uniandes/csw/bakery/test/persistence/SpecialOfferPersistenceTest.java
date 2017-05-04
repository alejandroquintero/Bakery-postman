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
package co.edu.uniandes.csw.bakery.test.persistence;
import co.edu.uniandes.csw.bakery.entities.ProductEntity;
import co.edu.uniandes.csw.bakery.entities.SpecialOfferEntity;
import co.edu.uniandes.csw.bakery.entities.ItemEntity;
import co.edu.uniandes.csw.bakery.persistence.SpecialOfferPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class SpecialOfferPersistenceTest {

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SpecialOfferEntity.class.getPackage())
                .addPackage(SpecialOfferPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    ProductEntity fatherEntity;

    /**
     * @generated
     */
    @Inject
    private SpecialOfferPersistence specialOfferPersistence;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from ItemEntity").executeUpdate();
        em.createQuery("delete from SpecialOfferEntity").executeUpdate();
        em.createQuery("delete from ProductEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<SpecialOfferEntity> data = new ArrayList<SpecialOfferEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
            fatherEntity = factory.manufacturePojo(ProductEntity.class);
            fatherEntity.setId(1L);
            em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            SpecialOfferEntity entity = factory.manufacturePojo(SpecialOfferEntity.class);
            
            entity.setProduct(fatherEntity);
            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un SpecialOffer.
     *
     * @generated
     */
    @Test
    public void createSpecialOfferTest() {
		PodamFactory factory = new PodamFactoryImpl();
        SpecialOfferEntity newEntity = factory.manufacturePojo(SpecialOfferEntity.class);
        newEntity.setProduct(fatherEntity);
        SpecialOfferEntity result = specialOfferPersistence.create(newEntity);

        Assert.assertNotNull(result);

        SpecialOfferEntity entity = em.find(SpecialOfferEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
    }

    /**
     * Prueba para consultar la lista de SpecialOffers.
     *
     * @generated
     */
    @Test
    public void getSpecialOffersTest() {
        List<SpecialOfferEntity> list = specialOfferPersistence.findAll(null, null, fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (SpecialOfferEntity ent : list) {
            boolean found = false;
            for (SpecialOfferEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un SpecialOffer.
     *
     * @generated
     */
    @Test
    public void getSpecialOfferTest() {
        SpecialOfferEntity entity = data.get(0);
        SpecialOfferEntity newEntity = specialOfferPersistence.find(entity.getProduct().getId(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
    }

    /**
     * Prueba para eliminar un SpecialOffer.
     *
     * @generated
     */
    @Test
    public void deleteSpecialOfferTest() {
        SpecialOfferEntity entity = data.get(0);
        specialOfferPersistence.delete(entity.getId());
        SpecialOfferEntity deleted = em.find(SpecialOfferEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un SpecialOffer.
     *
     * @generated
     */
    @Test
    public void updateSpecialOfferTest() {
        SpecialOfferEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SpecialOfferEntity newEntity = factory.manufacturePojo(SpecialOfferEntity.class);

        newEntity.setId(entity.getId());

        specialOfferPersistence.update(newEntity);

        SpecialOfferEntity resp = em.find(SpecialOfferEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
    }
}

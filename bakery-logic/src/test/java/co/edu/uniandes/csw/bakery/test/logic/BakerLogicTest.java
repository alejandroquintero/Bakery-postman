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
package co.edu.uniandes.csw.bakery.test.logic;

import co.edu.uniandes.csw.bakery.ejbs.BakerLogic;
import co.edu.uniandes.csw.bakery.api.IBakerLogic;
import co.edu.uniandes.csw.bakery.entities.BakerEntity;
import co.edu.uniandes.csw.bakery.persistence.BakerPersistence;
import co.edu.uniandes.csw.bakery.entities.ProductEntity;
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
public class BakerLogicTest {

    /**
     * @generated
     */

    /**
     * @generated
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * @generated
     */
    @Inject
    private IBakerLogic bakerLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    private UserTransaction utx;

    /**
     * @generated
     */
    private List<BakerEntity> data = new ArrayList<BakerEntity>();

    /**
     * @generated
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BakerEntity.class.getPackage())
                .addPackage(BakerLogic.class.getPackage())
                .addPackage(IBakerLogic.class.getPackage())
                .addPackage(BakerPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     * @generated
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from ProductEntity").executeUpdate();
        em.createQuery("delete from BakerEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            BakerEntity entity = factory.manufacturePojo(BakerEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Baker
     *
     * @generated
     */
    @Test
    public void createBakerTest() {
        BakerEntity newEntity = factory.manufacturePojo(BakerEntity.class);
        BakerEntity result = bakerLogic.createBaker(newEntity);
        Assert.assertNotNull(result);
        BakerEntity entity = em.find(BakerEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de Bakers
     *
     * @generated
     */
    @Test
    public void getBakersTest() {
        List<BakerEntity> list = bakerLogic.getBakers();
        Assert.assertEquals(data.size(), list.size());
        for (BakerEntity entity : list) {
            boolean found = false;
            for (BakerEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Baker
     *
     * @generated
     */
    @Test
    public void getBakerTest() {
        BakerEntity entity = data.get(0);
        BakerEntity resultEntity = bakerLogic.getBaker(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getName(), resultEntity.getName());
    }

    /**
     * Prueba para eliminar un Baker
     *
     * @generated
     */
    @Test
    public void deleteBakerTest() {
        BakerEntity entity = data.get(0);
        bakerLogic.deleteBaker(entity.getId());
        BakerEntity deleted = em.find(BakerEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Baker
     *
     * @generated
     */
    @Test
    public void updateBakerTest() {
        BakerEntity entity = data.get(0);
        BakerEntity pojoEntity = factory.manufacturePojo(BakerEntity.class);

        pojoEntity.setId(entity.getId());

        bakerLogic.updateBaker(pojoEntity);

        BakerEntity resp = em.find(BakerEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getName(), resp.getName());
    }
}


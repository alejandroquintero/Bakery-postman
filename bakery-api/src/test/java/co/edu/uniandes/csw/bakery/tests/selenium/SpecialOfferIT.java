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
package co.edu.uniandes.csw.bakery.tests.selenium;

import co.edu.uniandes.csw.bakery.dtos.minimum.SpecialOfferDTO;
import co.edu.uniandes.csw.bakery.resources.SpecialOfferResource;
import co.edu.uniandes.csw.bakery.tests.selenium.pages.specialOffer.SpecialOfferCreatePage;
import co.edu.uniandes.csw.bakery.tests.selenium.pages.specialOffer.SpecialOfferListPage;
import co.edu.uniandes.csw.bakery.tests.selenium.pages.LoginPage;
import co.edu.uniandes.csw.bakery.tests.selenium.pages.specialOffer.SpecialOfferDeletePage;
import co.edu.uniandes.csw.bakery.tests.selenium.pages.specialOffer.SpecialOfferDetailPage;
import co.edu.uniandes.csw.bakery.tests.selenium.pages.specialOffer.SpecialOfferEditPage;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(Arquillian.class)
public class SpecialOfferIT {

    private static PodamFactory factory = new PodamFactoryImpl();

    @ArquillianResource
    private URL deploymentURL;

    @Drone
    private WebDriver browser;

    @Page
    private SpecialOfferCreatePage createPage;

    @Page
    private SpecialOfferDetailPage detailPage;

    @Page
    private SpecialOfferEditPage editPage;

    @Page
    private SpecialOfferDeletePage deletePage;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(SpecialOfferResource.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"))
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory("src/main/webapp").as(GenericArchive.class), "/");
    }

    @Before
    public void setup() {
        browser.manage().window().maximize();
        browser.get(deploymentURL.toExternalForm());
    }

    @Test
    @InSequence(0)
    public void login(@InitialPage LoginPage loginPage) {
        browser.manage().deleteAllCookies();
        loginPage.login();
    }
/*
    @Test
    @InSequence(1)
    public void createSpecialOffer(@InitialPage SpecialOfferListPage listPage) {
        Integer expected = 0;
        Assert.assertEquals(expected, listPage.countSpecialOffers());

        listPage.create();

        SpecialOfferDTO expected_specialOffer = factory.manufacturePojo(SpecialOfferDTO.class);
        createPage.saveSpecialOffer(expected_specialOffer);

        SpecialOfferDTO actual_specialOffer = detailPage.getData();

        Assert.assertEquals(expected_specialOffer.getName(), actual_specialOffer.getName());
    }

    @Test
    @InSequence(2)
    public void editSpecialOffer(@InitialPage SpecialOfferListPage listPage) {
        SpecialOfferDTO expected_specialOffer = factory.manufacturePojo(SpecialOfferDTO.class);

        listPage.editSpecialOffer(0);

        editPage.saveSpecialOffer(expected_specialOffer);

        SpecialOfferDTO actual_specialOffer = detailPage.getData();

        Assert.assertEquals(expected_specialOffer.getName(), actual_specialOffer.getName());
    }

    @Test
    @InSequence(3)
    public void deleteSpecialOffer(@InitialPage SpecialOfferListPage listPage) {
        listPage.deleteSpecialOffer(0);
        deletePage.confirm();
        Integer expected = 0;
        Assert.assertEquals(expected, listPage.countSpecialOffers());
    }
*/
}

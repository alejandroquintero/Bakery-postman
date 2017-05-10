/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bakery.tests.postman;

import co.edu.uniandes.csw.auth.stormpath.ApiKeyProperties;
import co.edu.uniandes.csw.bakery.dtos.detail.BakerDetailDTO;
import co.edu.uniandes.csw.bakery.dtos.minimum.BakerDTO;
import co.edu.uniandes.csw.bakery.resources.BakerResource;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Asistente
 */
@RunWith(Arquillian.class)
public class BakerIT {
    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class,"bakery-api.war")
                // Se agrega las dependencias
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                        .importRuntimeDependencies().resolve()
                        .withTransitivity().asFile())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(BakerResource.class.getPackage())
           //     .addPackage(BakerDTO.class.getPackage())
           //     .addPackage(BakerDetailDTO.class.getPackage())
           //     .addPackage(ApiKeyProperties.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos.
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias.
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini es necesario para injeccion de dependencias
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
                 
    }
    
    @Test 
    public void postman(){
    
        try {              
            Process process = Runtime.getRuntime().exec("/Users/Asistente/Documents/jenkins/workspace/Bakery-head/Bakery/bakery-api/run.bat");
            InputStream inputStream = process.getInputStream();
            BufferedReader bf= new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            String ln;
            Logger log=Logger.getAnonymousLogger();
            
            while ((ln=bf.readLine()) != null) {
               log.info(ln);
                line=line.concat(ln);
            }
                
            inputStream.close();
            bf.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
}

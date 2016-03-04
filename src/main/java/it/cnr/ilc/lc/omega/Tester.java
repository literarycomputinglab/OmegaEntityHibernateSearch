package it.cnr.ilc.lc.omega;

import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.TextContent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author oakgen
 */
public class Tester {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OmegaPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        TextContent content = Content.contentOf(TextContent.class);
//        content.setUri("Urilla");
//        content.setText("Testolone");
//        
//        entityManager.getTransaction().begin();
//        
//        entityManager.persist(content);
//        
//        entityManager.getTransaction().commit();
//        
//        entityManager.close();
        
    }
}

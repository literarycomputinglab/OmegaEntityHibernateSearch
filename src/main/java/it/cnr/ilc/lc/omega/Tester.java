package it.cnr.ilc.lc.omega;

import it.cnr.ilc.lc.omega.entity.Test;
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

        Test test = new Test();
        test.setName("Mamma");
        
        entityManager.getTransaction().begin();
        
        entityManager.persist(test);
        
        entityManager.getTransaction().commit();
        
        entityManager.close();
        
    }
}

package it.cnr.ilc.lc.omega;

import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author oakgen
 */
public class Tester {

    private static final Logger logger = LogManager.getLogger(Tester.class);

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OmegaPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TextContent content = Content.contentOf(TextContent.class);
        content.setUri("Urilla");
        content.setText("Testolone");

        Source<TextContent> source = Source.sourceOf(TextContent.class, URI.create("/uri/of/source/001"));

        source.setContent(content);

        entityManager.getTransaction().begin();

        entityManager.persist(source);

        entityManager.getTransaction().commit();

        entityManager.close();

        logger.info("End.");

    }
}

package it.cnr.ilc.lc.omega;

import it.cnr.ilc.lc.omega.annotation.BaseAnnotation;
import it.cnr.ilc.lc.omega.annotation.BaseAnnotationBuilder;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.Locus;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.entity.TextLocus;
import it.cnr.ilc.lc.omega.exception.InvalidURIException;
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

    public static void main(String[] args) throws InvalidURIException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OmegaPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TextContent content = Content.contentOf(TextContent.class);
        content.setUri("Urilla");
        content.setText("Testolone");

        Source<TextContent> source = Source.sourceOf(TextContent.class, URI.create("/uri/of/source/001"));

        source.setContent(content);

        TextLocus locus = Locus.locusOf(TextLocus.class, URI.create("/uri/of/locus/002"), Locus.PointsTo.CONTENT);
        // TextLocus locus = new TextLocus();
        locus.setSource(source);

        locus.setStart(5);
        locus.setEnd(10);

        Annotation.register("base", BaseAnnotation.class);

        Annotation<TextContent, BaseAnnotation> annotation = Annotation.newAnnotation("base", new BaseAnnotationBuilder().uri(URI.create("/base/annotation/uri/004")).content("Un esempio di annotazione di base"));

        annotation.addLocus(locus);
        annotation.setTesta("testadiminchia");
        entityManager.getTransaction().begin();

        entityManager.persist(annotation);
       // entityManager.persist(source);

        entityManager.getTransaction().commit();

        entityManager.close();

        logger.info("End.");

    }
}

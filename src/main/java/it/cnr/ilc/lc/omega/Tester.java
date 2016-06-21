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
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author oakgen
 */
public class Tester {

    private static final Logger logger = LogManager.getLogger(Tester.class);

    public static void main(String[] args) throws InvalidURIException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("OmegaPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        insert(entityManager);
        // search("base", entityManager);
        // searchTextContent("del", entityManager);
        searchForBaseAnnotation("esempio", entityManager);
        entityManager.close();
    }

    public static void insert(EntityManager entityManager) throws InvalidURIException {

        TextContent content = Content.contentOf(TextContent.class);
        content.setUri("/uri/of/content/008");
        content.setText("Testo del content");

        Source<TextContent> source = Source.sourceOf(TextContent.class, URI.create("/uri/of/source/001"));

        source.setContent(content);

        TextLocus locus = Locus.locusOf(TextLocus.class, URI.create("/uri/of/locus/002"), Locus.PointsTo.CONTENT);
        // TextLocus locus = new TextLocus();
        locus.setSource(source);

        locus.setStartLocus(2);
        locus.setEndLocus(7);

        Annotation.register("base", BaseAnnotation.class);

        Annotation<TextContent, BaseAnnotation> annotation = Annotation.newAnnotation("base", new BaseAnnotationBuilder().uri(URI.create("/base/annotation/uri/004")).content("Un esempio di annotazione di base"));

        annotation.addLocus(locus);

        entityManager.getTransaction().begin();

        entityManager.persist(annotation);
        // entityManager.persist(source);

        entityManager.getTransaction().commit();

        logger.info("End.");

    }

    public static void search(String word, EntityManager entityManager) {

        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Annotation.class).get();

        org.apache.lucene.search.Query query = builder
                .keyword()
                .onField("data.indexField")
                .matching(word)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, Annotation.class);

        List<Annotation> results = persistenceQuery.getResultList();

        logger.info("Result is empty? " + results.isEmpty());

        for (Annotation result : results) {

            logger.info("id: " + result.getId() + " " + result.getId());

        }
        entityManager.getTransaction().commit();

        logger.info("End.");

    }

    public static void searchTextContent(String word, EntityManager entityManager) {

        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(TextContent.class).get();

        org.apache.lucene.search.Query query = builder
                .keyword()
                .onField("text")
                .matching(word)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        logger.info("Searching for TextContent");

        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, TextContent.class);

        List<TextContent> results = persistenceQuery.getResultList();

        logger.info("Result is empty? " + results.isEmpty());

        for (TextContent result : results) {
            Source<TextContent> s = (Source<TextContent>) result.getSource();
            logger.info("TextContent: " + s.getContent().getText());

        }
        entityManager.getTransaction().commit();

        logger.info("End.");

    }

    public static void searchForBaseAnnotation(String word, EntityManager entityManager) {

        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(BaseAnnotation.class).get();

        org.apache.lucene.search.Query query = builder
                .keyword()
                .onField("content")
                .matching(word)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        logger.info("Searching for BaseAnnotation");

        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, BaseAnnotation.class);

        List<BaseAnnotation> results = persistenceQuery.getResultList();

        logger.info("Result is empty? " + results.isEmpty() + " " + results.size());

        for (BaseAnnotation ba : results) {
            Annotation<TextContent, BaseAnnotation> a = ba.getAnnotation();
            logger.info("BaseAnnotation a: " + a.getUri());

            
            Iterator<Locus<TextContent>> it = a.getLoci(TextContent.class);

            while (it.hasNext()) {
                TextLocus locus = it.next().get();

                logger.info("Locus fragment: " + locus.getFragment());
            }

        }
        entityManager.getTransaction().commit();

        logger.info("End.");

    }
}

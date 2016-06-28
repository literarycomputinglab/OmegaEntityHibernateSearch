package it.cnr.ilc.lc.omega;

import it.cnr.ilc.lc.omega.annotation.AdvancedAnnotation;
import it.cnr.ilc.lc.omega.annotation.AdvancedAnnotationBuilder;
import it.cnr.ilc.lc.omega.annotation.SimpleAnnotation;
import it.cnr.ilc.lc.omega.annotation.SimpleAnnotationBuilder;
import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.Content;
import it.cnr.ilc.lc.omega.entity.Locus;
import it.cnr.ilc.lc.omega.entity.Source;
import it.cnr.ilc.lc.omega.entity.TextContent;
import it.cnr.ilc.lc.omega.entity.TextLocus;
import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

        insertBaseAnnotation(entityManager);
        insertAdvancedAnnotation(entityManager);
        // search("esempio", entityManager);
        // searchTextContent("del", entityManager);
        //searchForBaseAnnotation("esempio", entityManager);
        //searchSourceByURI("/uri/of/source/001", entityManager);
        Calendar calendar = new GregorianCalendar(2013, 1, 28, 13, 24, 56);
        searchByDate(calendar.getTime(), entityManager);
        entityManager.close();
    }

    public static void insertBaseAnnotation(EntityManager entityManager) throws InvalidURIException {

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

        Annotation.register("base", SimpleAnnotation.class);

        Annotation<TextContent, SimpleAnnotation> annotation
                = Annotation.newAnnotation("base",
                        new SimpleAnnotationBuilder().uri(URI.create("/base/annotation/uri/004")).content("Un esempio di annotazione di base"));

        annotation.addLocus(locus);

        entityManager.getTransaction().begin();

        entityManager.persist(annotation);
        // entityManager.persist(source);

        entityManager.getTransaction().commit();

        logger.info("End insertBaseAnnotation");

    }

    public static void insertAdvancedAnnotation(EntityManager entityManager) throws InvalidURIException {

        TextContent content = Content.contentOf(TextContent.class);
        content.setUri("/uri/of/content/009");
        content.setText("Testo del content da annotare con una advanced annotation");

        Source<TextContent> source = Source.sourceOf(TextContent.class, URI.create("/uri/of/source/002"));

        source.setContent(content);

        TextLocus locus = Locus.locusOf(TextLocus.class, URI.create("/uri/of/locus/003"), Locus.PointsTo.CONTENT);
        // TextLocus locus = new TextLocus();
        locus.setSource(source);

        locus.setStartLocus(10);
        locus.setEndLocus(18);

        Annotation.register("advanced", AdvancedAnnotation.class);

        Calendar calendar = new GregorianCalendar(2013, 1, 28, 13, 24, 56);

        Annotation<TextContent, AdvancedAnnotation> annotation
                = Annotation.newAnnotation("advanced",
                        new AdvancedAnnotationBuilder()
                        .uri(URI.create("/advanced/annotation/uri/004"))
                        .note("Un esempio di annotazione ADVANCED")
                        .date(calendar.getTime())
                        .author("Bobbe Nero"));

        annotation.addLocus(locus);

        entityManager.getTransaction().begin();

        entityManager.persist(annotation);
        // entityManager.persist(source);

        entityManager.getTransaction().commit();

        logger.info("End insertAdvancedAnnotation");

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
            Annotation.Data b = result.getData().get();
            logger.info("Type      : " + result.getClass().toGenericString() + " " + result.getData().getClass().getTypeName());
            logger.info("Data.get(): " + result.getData().get());

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
                .buildQueryBuilder().forEntity(SimpleAnnotation.class).get();

        org.apache.lucene.search.Query query = builder
                .keyword()
                .onField("content")
                .matching(word)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        logger.info("Searching for BaseAnnotation");

        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, SimpleAnnotation.class);

        List<SimpleAnnotation> results = persistenceQuery.getResultList();

        logger.info("Result is empty? " + results.isEmpty() + " " + results.size());

        for (SimpleAnnotation ba : results) {
            Annotation<TextContent, SimpleAnnotation> a = ba.getAnnotation();
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

    public static void searchSourceByURI(String uri, EntityManager entityManager) {

        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Source.class).get();

        org.apache.lucene.search.Query query = builder
                .phrase()
                .onField("uri")
                .sentence(uri)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        logger.info("Searching for Source");

        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, Source.class);

        List<Source<TextContent>> results = persistenceQuery.getResultList();

        logger.info("Result is empty? " + results.isEmpty() + " " + results.size());

        for (Source<TextContent> source : results) {
            TextContent content = source.getContent();
            logger.info("Source text (" + content.getText() + ")");
        }

        entityManager.getTransaction().commit();

        logger.info("End.");

    }

    public static void searchByDate(Date date, EntityManager entityManager) {

        entityManager.getTransaction().begin();

        FullTextEntityManager fullTextEntityManager
                = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(AdvancedAnnotation.class).get();

        org.apache.lucene.search.Query query = builder
                .range()
                .onField("date")
                .from(date)
                .to(date)
                .createQuery();

        //Query luceneQuery = builder.all().createQuery();
        javax.persistence.Query persistenceQuery
                = fullTextEntityManager.createFullTextQuery(query, AdvancedAnnotation.class);

        List<AdvancedAnnotation> results = persistenceQuery.getResultList();

        logger.info("Result is empty? " + results.isEmpty());

        for (AdvancedAnnotation result : results) {
            Iterator<Locus<TextContent>> it = result.getAnnotation().getLoci(TextContent.class);

            while (it.hasNext()) {
                TextLocus locus = it.next().get();
                logger.info("Locus fragment: " + locus.getFragment() + " " + result.getDate().toString());
            }

        }
        entityManager.getTransaction().commit();

        logger.info("End.");

    }

}

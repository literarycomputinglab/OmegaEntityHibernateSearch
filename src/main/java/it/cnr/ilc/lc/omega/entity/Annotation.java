package it.cnr.ilc.lc.omega.entity;

import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.pattern.PatternReplaceCharFilterFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.CharFilterDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author oakgen
 * @param <T>
 * @param <E>
 */
@Entity
@Indexed(index = "it.cnr.ilc.lc.omega.entity.Annotation")
@AnalyzerDef(name = "dataTextAnalyzer", charFilters
        = {
            @CharFilterDef(factory = PatternReplaceCharFilterFactory.class, params
                    = {
                @Parameter(name = "pattern", value = "[,\\.;:]"),
                @Parameter(name = "replacement", value = " ")
            })
        }, tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class))

public class Annotation<T extends Content, E extends Annotation.Data> extends Source<T> {

    private static final Logger log = LogManager.getLogger(Annotation.class);

    @IndexedEmbedded(targetElement = Annotation.Data.class) // indicazione sulla classe che deve essere indicizzata da lucene
    @ManyToOne(targetEntity = Annotation.Data.class, cascade = CascadeType.ALL)
    private E data;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Locus> loci;

    @ManyToMany
    private List<Relation> relations = new ArrayList<>();

    private Annotation() {
    }

    public <V extends Content> void addLocus(Locus<V> locus) {
        if (null == loci) {
            loci = new ArrayList<>();
        }
        locus.setAnnotation(this);
        loci.add(locus);
    }

    public <V extends Content> boolean removeLocus(Locus<V> locus) {
        return loci.remove(locus);
    }

    public <V extends Content> Iterator<Locus<V>> getLoci(Class<V> clazz) {
        return (Iterator) loci.iterator();
    }

    public E getData() {
        return data;
    }

    private void setData(E data) {
        this.data = data;
    }

    public Iterator<Relation> getRelations() {
        return relations.iterator();
    }

    public void addRelation(Relation relation) {
        relations.add(relation);
    }

    public boolean removeRelation(Relation relation) {
        return relations.remove(relation);
    }

    @Entity
    @Embeddable
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public static abstract class Data extends SuperNode {

        @Field(analyzer = @Analyzer(definition = "dataTextAnalyzer"))
        @Column(length = 4096)
        private String indexField;

        @OneToOne(cascade = CascadeType.ALL)
        private Annotation annotation;

        @Field(analyze = Analyze.NO, index = Index.YES)
        @Column(length = 1024)
        private String annotationAuthor;

        @Field
        private Date creationDate;

        public final void setIndexField(String indexField) {
            this.indexField = indexField;
        }

        protected Data() {
        }

        <E extends Data> E build(AnnotationBuilder<E> builder) {
            return builder.build((E) this);
        }

        final void setAnnotation(Annotation ann) {

            this.annotation = ann;
        }

        public final Annotation getAnnotation() {

            return this.annotation;
        }

        public abstract <E extends Data> E get();

        public String getAnnotationAuthor() {
            return annotationAuthor;
        }

        public final void setAnnotationAuthor(String annotationAuthor) {
            this.annotationAuthor = annotationAuthor;
        }

        public Date getCreationDate() {
            return creationDate;
        }

        public final void setCreationDate(Date creationDate) {
            this.creationDate = creationDate;
        }

    }

    private static final Map<String, Class<? extends Annotation.Data>> LOOKUP_TABLE = new HashMap<>();

    public static void register(String type, Class<? extends Annotation.Data> clazz) {
        LOOKUP_TABLE.put(type, clazz);
    }

    public static <T extends Content, E extends Annotation.Data> Annotation<T, E>
            newAnnotation(String type, AnnotationBuilder<E> builder) throws InvalidURIException {

        try {
            Annotation<T, E> annotation = new Annotation<>();
            Class<?> c = LOOKUP_TABLE.get(type);
            if (null == c) {
                throw new NullPointerException("No implementation found for type " + type);
            }
            E extension = (E) c.newInstance();
            annotation.setData(extension.build(builder));
            annotation.setUri(builder.getUri().toASCIIString()); //puo' sollevare eccezione se URI e' nulla o vuota
            annotation.getData().setAnnotation(annotation);

            return annotation;

        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

    }
}

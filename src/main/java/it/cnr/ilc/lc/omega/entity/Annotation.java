package it.cnr.ilc.lc.omega.entity;

import it.cnr.ilc.lc.omega.exception.InvalidURIException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author oakgen
 * @param <T>
 * @param <E>
 */
@Entity
@Indexed
public class Annotation<T extends Content, E extends Annotation.Data> extends Source<T> {

    @IndexedEmbedded(targetElement = AbstractAnnotationBuilder.class) // indicazione sulla classe che deve essere indicizzata da lucene
    @ManyToOne(targetEntity = Annotation.Data.class)
    private E data;

    @OneToMany  
    private List<Locus> loci = new ArrayList<>();

    @ManyToMany
    private List<Relation> relations = new ArrayList<>();

    private Annotation() {
    }

    public <V extends Content> void addLocus(Locus<V> locus) {
        locus.setAnnotation(this);
        loci.add(locus);
    }

    public <V extends Content> boolean removeLocus(Locus<V> locus) {
        return loci.remove(locus);
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
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public static abstract class Data extends SuperNode {

        protected Data() {
        }

        <E extends Data> E build(AnnotationBuilder<E> builder) {
            return builder.build((E) this);
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

            annotation.setUri(builder.getURI().toASCIIString()); //puo' sollevare eccezione se URI e' nulla o vuota

            return annotation;

        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

    }

}

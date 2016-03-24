package it.cnr.ilc.lc.omega.entity;

import java.net.URI;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author oakgen
 * @param <T>
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Locus<T extends Content> extends SuperNode implements Cloneable {

    public enum PointsTo {

        SOURCE, CONTENT;
    }

    @ManyToOne
    private Annotation annotation;

    @IndexedEmbedded
    @ManyToOne
    private Source<T> source;

    private String pointsTo;

    public Annotation getAnnotation() {
        return annotation;
    }

    void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Source<T> getSource() {
        return source;
    }

    public void setSource(Source<T> source) {
        this.source = source;
    }

    public String getPointsTo() {
        return pointsTo;
    }

    void setPointsTo(String pointsTo) {
        this.pointsTo = pointsTo;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static <T extends Locus> T locusOf(Class<T> clazz, URI uri, PointsTo pointTo) {
        try {
            T locus = clazz.newInstance();
            locus.setPointsTo(pointTo.name());
            locus.setUri(uri.toASCIIString());
            return locus;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}
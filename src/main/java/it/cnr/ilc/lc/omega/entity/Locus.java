package it.cnr.ilc.lc.omega.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.net.URI;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@JsonIgnoreProperties({"annotation", "source"})
public abstract class Locus<T extends Content> extends SuperNode implements Cloneable {

    public enum PointsTo {

        SOURCE, CONTENT;
    }

    @ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Annotation annotation;

    @IndexedEmbedded
    @ManyToOne (cascade = CascadeType.ALL)
    private Source<T> source;

    private String pointsTo;

    public Annotation getAnnotation() {
        return annotation;
    }

    protected void setAnnotation(Annotation annotation)  {
        
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

    public <K extends Locus<T>> K get () {
        return (K) this;
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
    
    
    public String getSourceUri() {
        return source.getUri();
    }

}

package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author oakgen
 */
@Entity
public class Relation extends SuperNode {

    final private String type;

    @ManyToOne
    private Annotation sourceAnnotation;

    @ManyToOne
    private Annotation targetAnnotation;

    private Relation(Enum type) {
        this.type = type.name();
    }

    public Annotation getTargetAnnotation() {
        return targetAnnotation;
    }

    public void setTargetAnnotation(Annotation targetAnnotation) {
        this.targetAnnotation = targetAnnotation;
    }

    public Annotation getSourceAnnotation() {
        return sourceAnnotation;
    }

    public void setSourceAnnotation(Annotation sourceAnnotation) {
        this.sourceAnnotation = sourceAnnotation;
    }

    public String getType() {
        return type;
    }

    public static Relation newInstance(Enum type) {
        Relation r = new Relation(type);
        return r;
    }

}

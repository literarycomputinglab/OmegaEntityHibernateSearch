package it.cnr.ilc.lc.omega.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author oakgen
 */
@Entity
public class AnnotationRelation extends SuperNode {

    final private String type;

    @ManyToOne (cascade = CascadeType.ALL)
    private Annotation sourceAnnotation;

    @ManyToOne (cascade = CascadeType.ALL)
    private Annotation targetAnnotation;

    private AnnotationRelation(Enum type) {
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

    public static AnnotationRelation newInstance(Enum type) {
        AnnotationRelation r = new AnnotationRelation(type);
        return r;
    }

}

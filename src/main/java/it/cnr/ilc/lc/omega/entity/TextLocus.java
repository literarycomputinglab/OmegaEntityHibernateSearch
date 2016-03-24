package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author oakgen
 */
@Entity
@Indexed
public class TextLocus extends Locus<TextContent> implements Cloneable {

    @Field
    private Integer start;

    @Field
    private Integer end;

    @Field
    @Column(columnDefinition = "TEXT")
    private String fragment;

    TextLocus(TextLocus locus) {
        this.start = locus.getStart();
        this.end = locus.getEnd();
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        if (getPointsTo().equals(PointsTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to " + PointsTo.SOURCE.name());
        }
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        if (getPointsTo().equals(PointsTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to" + PointsTo.SOURCE.name());
        }
        this.end = end;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    @Override
    public TextLocus clone() throws CloneNotSupportedException {
        TextLocus l = (TextLocus) super.clone();
        return l;
    }

    public static class InvocationMthodException extends RuntimeException {

        public InvocationMthodException(String message) {
            super(message);
        }

    }
}

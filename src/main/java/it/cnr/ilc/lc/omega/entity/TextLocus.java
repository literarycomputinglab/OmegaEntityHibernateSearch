package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Entity;

/**
 *
 * @author oakgen
 */
@Entity
public class TextLocus extends Locus<TextContent> implements Cloneable {

    private Integer start;
    private Integer end;

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

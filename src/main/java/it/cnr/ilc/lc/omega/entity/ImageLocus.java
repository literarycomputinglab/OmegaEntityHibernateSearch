package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Entity;

/**
 *
 * @author angelo
 */
@Entity
public class ImageLocus extends Locus<ImageContent> implements Cloneable {

    private String WKTstr;

    protected ImageLocus() {

    }

    ImageLocus(ImageLocus locus) {
        this.WKTstr = locus.getWKTstr();
    }

    public String getWKTstr() {
        return WKTstr;
    }

    public void setWKTstr(String WKTstr) {
        if (getPointsTo().equals(Locus.PointsTo.SOURCE.name())) {
            throw new InvocationMthodException("content boundaries cannot be set on locus pointing to " + Locus.PointsTo.SOURCE.name());
        }
        this.WKTstr = WKTstr;
    }

    @Override
    public ImageLocus clone() throws CloneNotSupportedException {
        ImageLocus l = (ImageLocus) super.clone();
        return l;
    }

    public static class InvocationMthodException extends RuntimeException {

        public InvocationMthodException(String message) {
            super(message);
        }

    }
}

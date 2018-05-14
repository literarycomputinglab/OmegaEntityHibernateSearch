package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author oakgen
 */
@Entity
@Indexed
public class TextLocus extends Locus<TextContent> implements Cloneable {

    private static final Logger logger = LogManager.getLogger(TextLocus.class);

    @Field
    private Integer startLocus;

    @Field
    private Integer endLocus;

    @Field
    @Column(columnDefinition = "TEXT")
    private String fragment;

    public TextLocus() {
    }

    TextLocus(TextLocus locus) {
        this.startLocus = locus.getStartLocus();
        this.endLocus = locus.getEndLocus();
    }

    @Override
    public void setAnnotation(Annotation annotation) {

        if (this.getPointsTo().equals(Locus.PointsTo.CONTENT.name())) {
            Source<TextContent> stc = this.getSource();

            try {
                this.setFragment(stc.getContent().getText().substring(this.getStartLocus(), this.getEndLocus()));
            } catch (NullPointerException e) {

                if (null == this.getSource()) {
                    logger.error("Impossible to create fragment! Source is null: " + e.getMessage());
                } else if (null == stc.getContent()) {
                    logger.error("Impossible to create fragment! Content is null: " + e.getMessage());
                } else if (null == stc.getContent().getText()) {
                    logger.error("Impossible to create fragment! Text is null: " + e.getMessage());
                }
            } catch (IndexOutOfBoundsException ee) {
                logger.error("Data lenght: " + stc.getContent().getText().length()
                        + ", start " + this.getStartLocus() + ", end " + this.getEndLocus() + ", ", ee);
            } catch (Exception eee) {
                logger.error("Error ", eee);
            }
        }
        super.setAnnotation(annotation);

    }

    public Integer getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(Integer startLocus) {
        if (getPointsTo().equals(PointsTo.SOURCE.name())) {
            throw new InvocationMethodException("content boundaries cannot be set on locus pointing to " + PointsTo.SOURCE.name());
        }
        this.startLocus = startLocus;
    }

    public Integer getEndLocus() {
        return endLocus;
    }

    public void setEndLocus(Integer endLocus) {
        if (getPointsTo().equals(PointsTo.SOURCE.name())) {
            throw new InvocationMethodException("content boundaries cannot be set on locus pointing to" + PointsTo.SOURCE.name());
        }
        this.endLocus = endLocus;
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

    public static class InvocationMethodException extends RuntimeException {

        public InvocationMethodException(String message) {
            super(message);
        }

    }
}

package it.cnr.ilc.lc.omega.entity;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author simone
 * @param <T>
 */
public abstract class AbstractAnnotationBuilder<T extends Annotation.Data> implements AnnotationBuilder<T> {

    private static Logger log = LogManager.getLogger(AbstractAnnotationBuilder.class);

    protected URI uri;

    protected String annotationAuthor;

    protected Date creationDate;

    /**
     * DA UTILIZZARE NEL BUILDER CONCRETO public BaseAnnotationBuilder URI(URI
     * uri) { setURI(uri); return this; }
     */
    @Override
    final public void setURI(URI uri) {
        this.uri = uri;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    @Override
    public void setAnnotationAuthor(String annotationAuthor) {

        this.annotationAuthor = annotationAuthor;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Field[] fields = FieldUtils.getAllFields(this.getClass());

        for (int i = 0; i < fields.length; i++) {
            if (!Logger.class.equals(fields[i].getType())) {
                try {
                    Object filedValue =  FieldUtils.readField(fields[i], this, true);
                    String fieldName = fields[i].getName();
                    if (i == fields.length - 1) {
                        if (fields[i].getType().isArray()) {
                            sb.append(String.format("%s => %s", fieldName, Arrays.toString((Object [])filedValue )));

                        } else {
                            sb.append(String.format("%s => %s", fieldName, filedValue));
                        }
                    } else {
                        if (fields[i].getType().isArray()) {
                            sb.append(String.format("%s => %s, ", fieldName, Arrays.toString((Object [])filedValue )));

                        } else {
                            sb.append(String.format("%s => %s, ", fieldName,  filedValue));
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    log.error(ex);
                }
            }
        }
        log.info("builder: " + sb.toString());
        return sb.toString();
    }

}

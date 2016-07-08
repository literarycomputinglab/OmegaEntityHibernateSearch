package it.cnr.ilc.lc.omega.entity;

import java.net.URI;
import java.util.Date;

/**
 *
 * @author angelo
 * @param <T>
 */
public interface AnnotationBuilder<T extends Annotation.Data> {

    T build(T extension);

    void setURI(URI uri);

    void setAnnotationAuthor(String annotationAuthor);
    
    void setCreationDate (Date date);
    
    URI getUri();
    
}

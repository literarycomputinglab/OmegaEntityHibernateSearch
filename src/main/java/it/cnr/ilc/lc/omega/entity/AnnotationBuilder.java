package it.cnr.ilc.lc.omega.entity;

import java.net.URI;

/**
 *
 * @author angelo
 * @param <T>
 */
public interface AnnotationBuilder<T extends Annotation.Data> {

    T build(T extension);

    void setURI(URI uri);
    
    URI getURI();

}

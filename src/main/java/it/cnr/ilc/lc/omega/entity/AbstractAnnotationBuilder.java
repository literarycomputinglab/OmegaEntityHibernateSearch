package it.cnr.ilc.lc.omega.entity;

import java.net.URI;

/**
 *
 * @author simone
 * @param <T>
 */
public abstract class AbstractAnnotationBuilder<T extends Annotation.Data> implements AnnotationBuilder<T> {

    URI uri;
    
   // utilizzare un campo per indicizzare
    String indexedLucene

    
    
    /**
     * DA UTILIZZARE NEL BULDER CONCRETO
     * public BaseAnnotationBuilder URI(URI uri) {
     *   setURI(uri);
     *  return this;
    }
    */
    
    @Override
    final public void setURI(URI uri) {
        this.uri = uri;
    }
    
    

    @Override
    final public URI getURI() {
        return uri;
    }

}

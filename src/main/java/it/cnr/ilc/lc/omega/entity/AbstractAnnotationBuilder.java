package it.cnr.ilc.lc.omega.entity;

import java.net.URI;
import java.util.Date;

/**
 *
 * @author simone
 * @param <T>
 */
public abstract class AbstractAnnotationBuilder<T extends Annotation.Data> implements AnnotationBuilder<T> {

    protected URI uri;

    protected String annotationAuthor;

    protected Date creationDate;

    /**
     * DA UTILIZZARE NEL BULDER CONCRETO public BaseAnnotationBuilder URI(URI
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
    
  
}

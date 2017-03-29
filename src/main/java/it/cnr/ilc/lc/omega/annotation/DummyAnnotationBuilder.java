/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation;

import it.cnr.ilc.lc.omega.entity.AbstractAnnotationBuilder;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author simone
 */
public class DummyAnnotationBuilder  extends AbstractAnnotationBuilder<DummyAnnotation>{

    @Override
    public DummyAnnotation build(DummyAnnotation extension) {
        return new DummyAnnotation();
    }
    
        
    public DummyAnnotationBuilder URI(URI uri) {
        setURI(uri);
        return this;
    }

    @Override
    public AbstractAnnotationBuilder<DummyAnnotation> annotationAuthor(String annotationAuthor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractAnnotationBuilder<DummyAnnotation> creationDate(Date creationDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

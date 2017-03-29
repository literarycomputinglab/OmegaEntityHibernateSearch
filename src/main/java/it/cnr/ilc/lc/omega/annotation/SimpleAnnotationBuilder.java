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
public class SimpleAnnotationBuilder extends AbstractAnnotationBuilder<SimpleAnnotation>{

    String content;

    public SimpleAnnotationBuilder content(String content) {
        this.content = content;
        return this;
    }
    
    @Override
    public SimpleAnnotationBuilder URI (URI aUri) {
        
        this.setURI(aUri);
        return this;
    }
    
    @Override
    public SimpleAnnotation build(SimpleAnnotation extension) {

        extension.setContent(content);
        extension.setIndexField(content);
        
        return extension;
        
    }

    @Override
    public AbstractAnnotationBuilder<SimpleAnnotation> annotationAuthor(String annotationAuthor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractAnnotationBuilder<SimpleAnnotation> creationDate(Date creationDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation;

import it.cnr.ilc.lc.omega.entity.AbstractAnnotationBuilder;
import java.net.URI;

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
    
    public SimpleAnnotationBuilder uri (URI aUri) {
        
        this.setURI(aUri);
        return this;
    }
    
    @Override
    public SimpleAnnotation build(SimpleAnnotation extension) {

        extension.setContent(content);
        extension.setIndexField(content);
        
        return extension;
        
    }
    
    
    
}

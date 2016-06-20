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
public class BaseAnnotationBuilder extends AbstractAnnotationBuilder<BaseAnnotation>{

    String content;

    public BaseAnnotationBuilder content(String content) {
        this.content = content;
        return this;
    }
    
    public BaseAnnotationBuilder uri (URI aUri) {
        
        this.setURI(aUri);
        return this;
    }
    
    @Override
    public BaseAnnotation build(BaseAnnotation extension) {

        extension.setContent(content);
        extension.setIndexField(content);
        
        return extension;
        
    }
    
    
    
}

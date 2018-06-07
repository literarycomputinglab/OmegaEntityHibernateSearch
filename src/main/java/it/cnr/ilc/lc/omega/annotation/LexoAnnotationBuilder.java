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
public class LexoAnnotationBuilder extends AbstractAnnotationBuilder<LexoAnnotation>{

    
    String textFragment;
    String leftContext;
    String rightContext;
    URI uriSense;

    public LexoAnnotationBuilder textFragment(String text) {
        textFragment = text;
        return this;
    }
    
    public LexoAnnotationBuilder leftContext(String lctx) {
        leftContext = lctx;
        return this;
    }
    
    public LexoAnnotationBuilder rightContext(String rctx) {
        rightContext = rctx;
        return this;
    }
    
    public LexoAnnotationBuilder uriSense(URI uriSense) {
        this.uriSense = uriSense;
        return this;
    }

    @Override
    public LexoAnnotation build(LexoAnnotation ext) {
            ext.setTextFragment(this.textFragment);
            ext.setLeftContext(this.leftContext);
            ext.setRightContext(this.rightContext);
            ext.setUriSense(this.uriSense.toASCIIString());
            ext.setIndexField(this.leftContext + " " + this.rightContext);
            return ext;
    }    

    
    
    @Override
    public LexoAnnotationBuilder URI(URI uri) {
        this.setURI(uri);
        return this;
    }

    @Override
    public AbstractAnnotationBuilder<LexoAnnotation> annotationAuthor(String annotationAuthor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractAnnotationBuilder<LexoAnnotation> creationDate(Date creationDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

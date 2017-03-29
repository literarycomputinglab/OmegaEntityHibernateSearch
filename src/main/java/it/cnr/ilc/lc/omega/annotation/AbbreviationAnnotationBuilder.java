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
public class AbbreviationAnnotationBuilder extends AbstractAnnotationBuilder<AbbreviationAnnotation> {

    //abbreviazione sciolta
    private String abbrevationExpansion;

    //abbreviazione cosi' come compare nel testo
    private String abbrevation;

    public AbbreviationAnnotationBuilder abbrevationExpansion(String abbrevationExpansion) {
        this.abbrevationExpansion = abbrevationExpansion;
        return this;
    }

    public AbbreviationAnnotationBuilder abbrevation(String abbrevation) {
        this.abbrevation = abbrevation;
        return this;
    }

    public AbbreviationAnnotationBuilder URI(URI uri) {
        setURI(uri);
        return this;
    }

    @Override
    public AbbreviationAnnotation build(AbbreviationAnnotation extension) {

        extension.setAbbrevation(abbrevation);
        extension.setAbbrevationExpansion(abbrevationExpansion);
        extension.setIndexField(abbrevation + " " + abbrevationExpansion);
        return extension;
    }

    @Override
    public AbstractAnnotationBuilder<AbbreviationAnnotation> annotationAuthor(String annotationAuthor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractAnnotationBuilder<AbbreviationAnnotation> creationDate(Date creationDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

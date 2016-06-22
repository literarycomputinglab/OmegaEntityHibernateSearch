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
public class AdvancedAnnotationBuilder extends AbstractAnnotationBuilder<AdvancedAnnotation> {

    String note;
    String author;
    Date date;

    public AdvancedAnnotationBuilder note(String note) {
        this.note = note;
        return this;
    }

    public AdvancedAnnotationBuilder author(String author) {
        this.author = author;
        return this;
    }

    public AdvancedAnnotationBuilder date(Date date) {
        this.date = date;
        return this;
    }

    public AdvancedAnnotationBuilder uri(URI aUri) {

        this.setURI(aUri);
        return this;
    }

    @Override
    public AdvancedAnnotation build(AdvancedAnnotation extension) {

        extension.setNote(note);
        extension.setAuthor(author);
        extension.setDate(date);

        extension.setIndexField(note + " " + author);

        return extension;

    }
}

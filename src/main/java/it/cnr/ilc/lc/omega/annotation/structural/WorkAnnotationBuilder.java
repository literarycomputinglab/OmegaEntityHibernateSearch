/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.structural;

import it.cnr.ilc.lc.omega.entity.AbstractAnnotationBuilder;
import java.net.URI;
import java.util.Date;

/**
 *
 * @author simone
 */
public class WorkAnnotationBuilder extends AbstractAnnotationBuilder<WorkAnnotation> {

    private String title;

    private String[] authors;

    private Date publicationDate;

    private String info;

    public WorkAnnotationBuilder URI(URI uri) {
        setURI(uri);
        return this;
    }

    public WorkAnnotationBuilder annotationAuthor(String annotationAuthor) {
        setAnnotationAuthor(annotationAuthor);
        return this;
    }

    public WorkAnnotationBuilder creationData(Date creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public WorkAnnotationBuilder title(String title) {
        this.title = title;
        return this;

    }

    public WorkAnnotationBuilder authors(String[] authors) {
        this.authors = authors;
        return this;

    }

    public WorkAnnotationBuilder pubblicationDate(Date pubblicationDate) {
        this.publicationDate = pubblicationDate;
        return this;

    }

    public WorkAnnotationBuilder info(String info) {
        this.info = info;
        return this;
    }

    
    
    @Override
    public WorkAnnotation build(WorkAnnotation extension) {
        extension.setAnnotationAuthor(annotationAuthor);
        extension.setAuthors(authors);
        extension.setCreationDate(creationDate);
        extension.setIndexField(title + String.join(" ", authors));
        extension.setInfo(info);
        extension.setPublicationDate(publicationDate);
        extension.setTitle(title);
        
        return extension;
    }
    
    ///////////TODO
    /*
        URI
        AUTHOR
        DATA
     */
}

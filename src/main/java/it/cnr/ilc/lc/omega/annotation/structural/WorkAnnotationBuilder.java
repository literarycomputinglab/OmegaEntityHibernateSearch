/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.structural;

import it.cnr.ilc.lc.omega.entity.AbstractAnnotationBuilder;
import it.cnr.ilc.lc.omega.entity.ext.Person;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author simone
 */
public class WorkAnnotationBuilder extends AbstractAnnotationBuilder<WorkAnnotation> {

    private static final Logger log = LogManager.getLogger(WorkAnnotationBuilder.class);

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
        extension.setAuthors(listOfPersons(authors));
        extension.setCreationDate(creationDate);
        extension.setIndexField(title + " " +  String.join(" ", authors));
        extension.setInfo(info);
        extension.setPublicationDate(publicationDate);
        extension.setTitle(title);

        return extension;
    }

    private List<Person> listOfPersons(String[] authors) {

        List<Person> ret = new ArrayList<>();

        for (String author : authors) {
            log.info("author: " + author);

            String[] str = author.split(",");
            Person p = new Person();
            p.setName(str[0]);
            p.setSurname(str[1]);
            ret.add(p);
        }

        return ret;

    }

    
    
    ///////////TODO
    /*
        URI
        AUTHOR
        DATA
     */

    @Override
    public String toString() {
        return "title=(" + title + "), authors=("+ Arrays.toString(authors) +
                ") pubblicationDate=(" + publicationDate + "), info=(" + info + ")"
                + ", uri=(" + uri + ")";
    }
}

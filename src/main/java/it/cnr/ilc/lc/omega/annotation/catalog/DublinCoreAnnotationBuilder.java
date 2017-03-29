/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.catalog;

import it.cnr.ilc.lc.omega.entity.AbstractAnnotationBuilder;
import it.cnr.ilc.lc.omega.entity.ext.DateEvent;
import it.cnr.ilc.lc.omega.entity.ext.StringValue;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author simone
 */
public class DublinCoreAnnotationBuilder extends AbstractAnnotationBuilder<DublinCoreAnnotation> {

    private static final Logger log = LogManager.getLogger(DublinCoreAnnotationBuilder.class);

    private final String NAN = "NaN property";
    private final String[] NANARRAY = new String[]{"NaN property"};
    private final Date NANDATE = null;
    private final DateEvent NANEVENT = new DateEvent(NANDATE, NAN);

    private String[] contributor;

    private String coverage;

    private String creator;

    private DateEvent dateEvent;

    private String description;

    private String format;

    private String identifier;

    private String language;

    private String publisher;

    private String[] relation; // List<DublinCoreAnnotation.DublinCoreRelation>

    private String rights;

    private String source;

    private String[] subject;

    private String title;

    private String type;

   
    @Override
    public DublinCoreAnnotationBuilder URI(URI uri) {
        setURI(uri);
        return this;
    }

    @Override
    public AbstractAnnotationBuilder<DublinCoreAnnotation> annotationAuthor(String annotationAuthor) {
        setAnnotationAuthor(annotationAuthor);
        return this;
    }

    @Override
    public AbstractAnnotationBuilder<DublinCoreAnnotation> creationDate(Date creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public DublinCoreAnnotationBuilder contributor(String[] contributor) {
        this.contributor = contributor;
        return this;
    }

    public DublinCoreAnnotationBuilder coverage(String coverage) {
        this.coverage = coverage;
        return this;
    }

    public DublinCoreAnnotationBuilder creator(String creator) {
        this.creator = creator;
        return this;
    }

    public DublinCoreAnnotationBuilder dateEvent(DateEvent dateEvent) {
        this.dateEvent = dateEvent;
        return this;
    }

    public DublinCoreAnnotationBuilder description(String description) {
        this.description = description;
        return this;
    }

    public DublinCoreAnnotationBuilder format(String format) {
        this.format = format;
        return this;
    }

    public DublinCoreAnnotationBuilder identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public DublinCoreAnnotationBuilder language(String language) {
        this.language = language;
        return this;
    }

    public DublinCoreAnnotationBuilder publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public DublinCoreAnnotationBuilder relation(String[] relation) {
        this.relation = relation;
        return this;
    }

    public DublinCoreAnnotationBuilder rights(String rights) {
        this.rights = rights;
        return this;
    }

    public DublinCoreAnnotationBuilder source(String source) {
        this.source = source;
        return this;
    }

    public DublinCoreAnnotationBuilder subject(String[] subject) {
        this.subject = subject;
        return this;
    }

    public DublinCoreAnnotationBuilder title(String title) {
        this.title = title;
        return this;
    }

    public DublinCoreAnnotationBuilder type(String type) {
        this.type = type;
        return this;
    }

    @Override
    public DublinCoreAnnotation build(DublinCoreAnnotation extension) {

        extension.setAnnotationAuthor(check(annotationAuthor));
        extension.setContributor(arrayToListStringValue(check(contributor)));
        extension.setCoverage(check(coverage));
        extension.setCreator(check(creator));
        extension.setDateEvent(check(dateEvent));
        extension.setDescription(check(description));
        extension.setFormat(check(format));
        extension.setIdentifier(check(identifier));
        extension.setLanguage(check(language));
        extension.setPublisher(check(publisher));
        extension.setRelation((new RelationCreator()).addInput(check(relation)).addParser(new RelationParser() {
            @Override
            public List<DublinCoreAnnotation.DublinCoreRelation> parse(String[] input) {

                List<DublinCoreAnnotation.DublinCoreRelation> rels = new ArrayList<>();
                if (null != input) {
                    for (int i = 0; i < input.length; i++) {
                        try {
                            int split = input[i].indexOf(":");
                            if (split != -1) {
                                String rel = input[i].substring(0, split);
                                String value = input[i].substring(split + 1, input[i].length());
                                rels.add(new DublinCoreAnnotation.DublinCoreRelation(rel, value));
                            } else {
                                throw new UnsupportedOperationException("Malformed string relation: expected \"predicate : object\", found \"" + input[i] + "\"");
                            }
                        } catch (UnsupportedOperationException e) {
                            log.error(e);
                        }

                    }
                }
                return rels;
            }
        }).create());
        extension.setRights(check(rights));
        extension.setSource(check(source));
        extension.setSubject(arrayToListStringValue(check(subject)));
        extension.setTitle(check(title));
        extension.setType(check(type));

        return extension;
    }

    private <T> T check(T t) {

        T ret = null;
        if (t != null) {
            ret = t;
        } else {
            if (t instanceof String) {
                ret = (T) NAN;
            } else if (t instanceof String[]) {
                ret = (T) NANARRAY;
            } else if (t instanceof Date) {
                ret = (T) NANDATE;
            } else if (t instanceof DateEvent) {
                ret = (T) NANEVENT;
            }
        }
        return ret;
    }

    private List<StringValue> arrayToListStringValue(String[] array) {

        List<StringValue> losv = new ArrayList<>();

        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                losv.add(new StringValue(array[i]));
            }
        }

        return losv;
    }

}

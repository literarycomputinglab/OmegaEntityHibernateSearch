/*
 * To change this license header", "choose License Headers in Project Properties.
 * To change this template file", "choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.catalog;

import it.cnr.ilc.lc.omega.entity.Annotation;
import it.cnr.ilc.lc.omega.entity.ext.DateEvent;
import it.cnr.ilc.lc.omega.entity.ext.StringValue;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author simone
 */
@Entity
@Indexed
public class DublinCoreAnnotation extends Annotation.Data {

    //contributor ", "coverage ", "creator ", "dateEvent ", "description ", "format ", "identifier ", "language ", "publisher ", "relation ", "rights ", "source ", "subject ", "title ", "type 
    @IndexedEmbedded
    @ElementCollection
    private List<StringValue> contributor;

    @Field
    private String coverage;

    @Field
    private String creator;


    @Embedded
    @IndexedEmbedded(includePaths = {"event"})
    private DateEvent dateEvent;

    @Field
    private String description;

    @Field
    private String format;

    @Field
    private String identifier;

    @Field
    private String language;
    
    @Field
    private String publisher;

    @IndexedEmbedded
    @Embedded
    @ElementCollection
    private List<DublinCoreRelation> relation;

    @Field
    private String rights;

    @Field
    private String source;

    @IndexedEmbedded
    @ElementCollection
    private List<StringValue> subject;

    @Field
    private String title;

    @Field
    private String type;

    public List<StringValue> getContributor() {
        return contributor;
    }

    void setContributor(List<StringValue> contributor) {
        this.contributor = contributor;
    }

    public String getCoverage() {
        return coverage;
    }

    void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getCreator() {
        return creator;
    }

    void setCreator(String creator) {
        this.creator = creator;
    }

    public DateEvent getDateEvent() {
        return dateEvent;
    }

    void setDateEvent(DateEvent dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    void setFormat(String format) {
        this.format = format;
    }

    public String getIdentifier() {
        return identifier;
    }

    void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<DublinCoreRelation> getRelation() {
        return relation;
    }

    void setRelation(List<DublinCoreRelation> relation) {
        this.relation = relation;
    }

    public String getRights() {
        return rights;
    }

    void setRights(String rights) {
        this.rights = rights;
    }

    public String getSource() {
        return source;
    }

    void setSource(String source) {
        this.source = source;
    }

    public List<StringValue> getSubject() {
        return subject;
    }

    void setSubject(List<StringValue> subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    @Override
    public <E extends Annotation.Data> E get() {
        return (E) this;
    }

    @Embeddable
    public static class DublinCoreRelation {

        @Field
        private String predicate;

        @Field
        private String object;

        protected DublinCoreRelation() {
            this.predicate = null;
            this.object = null;
        }

        
        public DublinCoreRelation(String p, String o) {
            predicate = p;
            object = o;

        }

        public String getPredicate() {
            return predicate;
        }

        public String getObject() {
            return object;
        }

        public void setPredicate(String predicate) {
            this.predicate = predicate;
        }

        public void setObject(String object) {
            this.object = object;
        }

        
    }

    /**
     *
     */
    public static enum DCTerms {

        ABSTRACT("abstract"), ACCESSRIGHTS("accessRight"), ACCRUALMETHOD("accrualMethod"),
        ACCRUALPERIODICITY("accrualPeriodicity"), ACCRUALPOLICY("accrualPolicy"),
        ALTERNATIVE("alternative"), AUDIENCE("audience"), AVAILABLE("available"),
        BIBLIOGRAPHICCITATION("bibliographicCitation"), CONFORMSTO("conformsTo"),
        CONTRIBUTOR("contributor"), COVERAGE("coverage"), CREATED("created"),
        CREATOR("creator"), DATE("date"), DATEACCEPTED("dateAccepted"),
        DATECOPYRIGHTED("dateCopyrighted"), DATESUBMITTED("dateSubmitted"),
        DESCRIPTION("description"), EDUCATIONLEVEL("educationLevel"), EXTENT("extent"),
        FORMAT("format"), HASFORMAT("hasFormat"), HASPART("hasPart"),
        HASVERSION("hasVersion"), IDENTIFIER("identifier"), INSTRUCTIONALMETHOD("instructionalMethod"),
        ISFORMATOF("isFormatOf"), ISPARTOF("isPartOf"), ISREFERENCEDBY("isReferencedBy"),
        ISREPLACEDBY("isReplacedBy"), ISREQUIREDBY("isRequiredBy"), ISSUED("issued"),
        ISVERSIONOF("isVersionOf"), LANGUAGE("language"), LICENSE("license"), MEDIATOR("mediator"),
        MEDIUM("medium"), MODIFIED("modified"), PROVENANCE("provenance"), PUBLISHER("publisher"),
        REFERENCES("references"), RELATION("relation"), REPLACES("replaces"),
        REQUIRES("requires"), RIGHTS("rights"), RIGHTSHOLDER("rightsHolder"), SOURCE("source"),
        SPATIAL("spatial"), SUBJECT("subject"), TABLEOFCONTENTS("tableOfContents"),
        TEMPORAL("temporal"), TITLE("title"), TYPE("type"), VALID("valid");

        String value;

        DCTerms(String s) {
            value = s;
        }

        @Override
        public String toString() {
            return value;
        }

    }

}

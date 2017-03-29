/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.catalog;

import it.cnr.ilc.lc.omega.entity.AbstractAnnotationBuilder;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author simone
 */
public class ResourceSystemAnnotationBuilder extends AbstractAnnotationBuilder<ResourceSystemAnnotation> {

    private static final Logger log = LogManager.getLogger(ResourceSystemAnnotationBuilder.class);

    private String name;

    private String description;

    private String type;

    private Integer depth;

    @Override
    public ResourceSystemAnnotation build(ResourceSystemAnnotation extension) {

        log.debug(String.format("[%s], [%s], [%s], [%s]", name, description, type, getUri().toASCIIString()));
        extension.setName(name);
        extension.setDescription(description);
        extension.setType(type);
        extension.setDepth(depth);
        extension.setUri(getUri().toASCIIString());
        extension.setAnnotationAuthor(annotationAuthor);
        extension.setCreationDate(creationDate);

        return extension;
    }

    @Override
    public ResourceSystemAnnotationBuilder URI(java.net.URI uri) {

        setURI(uri);
        return this;
    }

    @Override
    public AbstractAnnotationBuilder<ResourceSystemAnnotation> annotationAuthor(String annotationAuthor) {
        setAnnotationAuthor(annotationAuthor);
        return this;
    }

    @Override
    public AbstractAnnotationBuilder<ResourceSystemAnnotation> creationDate(Date creationDate) {
        setCreationDate(creationDate);
        return this;
    }

    public ResourceSystemAnnotationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ResourceSystemAnnotationBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ResourceSystemAnnotationBuilder type(String type) {
        this.type = type;
        return this;
    }

    public ResourceSystemAnnotationBuilder depth(Integer depth) {
        this.depth = depth;
        return this;
    }

}

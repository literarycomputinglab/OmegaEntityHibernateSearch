/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.catalog;

import it.cnr.ilc.lc.omega.entity.Annotation;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author simone
 */

@Entity
@Indexed
public class ResourceSystemAnnotation extends Annotation.Data{

    @Field
    @Column(length = 1024)
    private String name;
    
    @Field
    @Column(length = 8192)
    private String description;
    
    @Field
    private String type;
    
    @Field
    private Integer depth;
    
    @Override
    public <E extends Annotation.Data> E get() {
        return (E) this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
    
    
    
}

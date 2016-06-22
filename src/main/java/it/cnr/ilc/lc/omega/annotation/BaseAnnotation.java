/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation;

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
public class BaseAnnotation extends Annotation.Data {

    @Field
    @Column(length = 4096)
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public <E extends Annotation.Data> E get() {
        return  (E) this;
    }

}

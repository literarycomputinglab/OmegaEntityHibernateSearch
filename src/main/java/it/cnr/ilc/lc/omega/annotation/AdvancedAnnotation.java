/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation;

import it.cnr.ilc.lc.omega.entity.Annotation;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

/**
 *
 * @author simone
 */
@Entity
@Indexed
public class AdvancedAnnotation extends Annotation.Data {

    @Field
    @Column(length = 4096)
    String note;

    @Field
    String author;

    @Field
    @DateBridge(resolution = Resolution.MILLISECOND)
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;

    public String getNote() {
        return note;
    }

    void setNote(String note) {
        this.note = note;
    }

    public String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    void setDate(Date date) {
        this.date = date;
    }

    @Override
    public <E extends Annotation.Data> E get() {
        return (E) this;
    }

}

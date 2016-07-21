/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity.ext;

import it.cnr.ilc.lc.omega.entity.SuperNode;
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
public class Person extends SuperNode {

    @Field
    private String name;

    @Field
    private String surname;

    @Field
    @Column(length = 1024)
    private String note;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}

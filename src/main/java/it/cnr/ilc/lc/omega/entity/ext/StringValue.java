/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity.ext;

import it.cnr.ilc.lc.omega.entity.SuperNode;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author simone
 */
@Embeddable
public class StringValue  extends SuperNode {
    
    
    @Field
    @Column(length = 1024)
    private String stringValue;

    private StringValue() {
    }

    public StringValue(String value) {
        this.stringValue = value;
    }

    public String getStringValue() {
        return stringValue;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation;

import it.cnr.ilc.lc.omega.entity.Annotation;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author simone
 */
@Entity
@Indexed
public class LexoAnnotation extends Annotation.Data {

    @Field //HS
    @Column(length = 1024) //JPA
    private String textFragment;

    @Field(analyzer = @Analyzer(definition = "dataTextAnalyzer"))
    @Column(length = 1024)
    private String leftContext; //contiene il textFragment

    @Field(analyzer = @Analyzer(definition = "dataTextAnalyzer"))
    @Column(length = 1024)
    private String rightContext;//contiene il textFragment

    @Field(analyze = Analyze.NO)
    @Column(length = 1024)
    private String uriSense;

    public String getTextFragment() {
        return textFragment;
    }

    public void setTextFragment(String textFragment) {
        this.textFragment = textFragment;
    }

    public String getLeftContext() {
        return leftContext;
    }

    public void setLeftContext(String leftContext) {
        this.leftContext = leftContext;
    }

    public String getRightContext() {
        return rightContext;
    }

    public void setRightContext(String rightContext) {
        this.rightContext = rightContext;
    }

    public String getUriSense() {
        return uriSense;
    }

    public void setUriSense(String uriSense) {
        this.uriSense = uriSense;
    }

    @Override
    public <E extends Annotation.Data> E get() {
        return (E) this;
    }

}

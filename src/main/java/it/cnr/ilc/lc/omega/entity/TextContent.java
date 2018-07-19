package it.cnr.ilc.lc.omega.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Lob;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

/**
 *
 * @author oakgen
 */
@Entity
@Indexed
@JsonIgnoreProperties("source")
public class TextContent extends Content {

    @Field(termVector = TermVector.WITH_POSITION_OFFSETS, analyzer = @Analyzer(definition = "dataTextAnalyzer"))
    @Lob
    private String text;

    TextContent() {
        super("text/plain");
        this.text = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return super.toString() + " " + text.substring(0, (text.length() < 50)?text.length():50); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}

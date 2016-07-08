package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author oakgen
 */
@Entity
@Indexed
public class TextContent extends Content {

    @Field
    @Lob
    private String text;

    TextContent() {
        super("text/plain");
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

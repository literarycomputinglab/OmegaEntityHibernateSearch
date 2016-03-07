package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Entity;

/**
 *
 * @author oakgen
 */
@Entity
public class TextContent extends Content {

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

}

package it.cnr.ilc.lc.omega.entity;

import java.net.URI;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author simone
 * @param <T>
 */
@Entity
public class Source<T extends Content> extends SuperNode {

    @ManyToOne(targetEntity = Content.class)
    private T content;

    protected Source() {
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public static <T extends Content> Source<T> sourceOf(Class<T> clazz, URI uri) {
        Source newSource = new Source<>();
        newSource.setUri(uri.toASCIIString());
        return newSource;
    }
}

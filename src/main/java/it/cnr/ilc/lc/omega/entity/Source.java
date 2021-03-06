package it.cnr.ilc.lc.omega.entity;

import it.cnr.ilc.lc.omega.utils.Utils;
import java.net.URI;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 *
 * @author simone
 * @param <T>
 */
@Indexed
@Entity
public class Source<T extends Content> extends SuperNode {

    @OneToOne(targetEntity = Content.class, cascade = CascadeType.ALL)
    @IndexedEmbedded(targetElement = Content.class, depth = 1)
    private T content;

    protected Source() {
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
        if (null != content) {
            this.content.setSource(this);//Catturare NullPointerException?????????
        } else {
            throw new UnsupportedOperationException("NullPointerException!!!!!!!!!!!"); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static <T extends Content> Source<T> sourceOf(Class<T> clazz, URI uri) {
        Source<T> newSource = new Source<>();

        newSource.setUri(Utils.trailingSlash(uri));
        return newSource;
    }

    @Override
    public String toString() {
        return super.toString() + " " + ((null == getContent()) ? "No Content!" : getContent().toString());
    }

}

package it.cnr.ilc.lc.omega.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author oakgen
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class Content extends SuperNode {

    @Field(analyze = Analyze.NO)
    private final String mimetype;

    @ContainedIn
    @JsonIgnore
    @OneToOne(mappedBy = "content")
    private Source<?> source;

    protected Content(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMimetype() {
        return mimetype;
    }

    public Source<?> getSource() {
        return source;
    }

    void setSource(Source<?> source) {
        this.source = source;
    }

    public static <T extends Content> T contentOf(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + getMimetype(); //To change body of generated methods, choose Tools | Templates.
    }

}

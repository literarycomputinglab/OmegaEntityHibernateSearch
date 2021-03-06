package it.cnr.ilc.lc.omega.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;

/**
 *
 * @author oakgen
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@FilterDef(name = "status")
@Filter(name = "status", condition = "status = 1")
public abstract class SuperNode implements Serializable {

    public static enum Status {

        HISTORY, VALID, REMOVED
    }

    @Field(analyze = Analyze.NO)
    @Column(unique = true)
    private String uri; // FIXME: valutare se la proprietà URI è da considerarsi come proprietà di superclasse.

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Enumerated
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.ALWAYS)
    private Date temporalMark;

    @ManyToOne(fetch = FetchType.LAZY)
    private SuperNode valid;

    @Version
    private Long lockVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getTemporalMark() {
        return temporalMark;
    }

    public void setTemporalMark(Date temporalMark) {
        this.temporalMark = temporalMark;
    }

    public SuperNode getValid() {
        return valid;
    }

    public void setValid(SuperNode valid) {
        this.valid = valid;
    }

    // vedere se implementare il metodo clone
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        if (null != uri && !"".equals(uri)) {
            this.uri = uri;
        } else {
            throw new NullPointerException("NULL or Empty URI are not permitted");
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uri != null ? uri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final SuperNode other = (SuperNode) obj;

        if (!Objects.equals(this.uri, other.uri)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getUri();
    }

}

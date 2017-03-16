/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.entity.ext;

import it.cnr.ilc.lc.omega.entity.SuperNode;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * Coppia data-event per DublinCore
 *
 * @author simone
 */

@Embeddable
public class DateEvent extends SuperNode {

    @Field
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dcdate")
    Date date;

    @Field
    @Column(length = 1024)
    String event;

}

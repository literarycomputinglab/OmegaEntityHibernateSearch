/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation;

import it.cnr.ilc.lc.omega.entity.Annotation;

/**
 * Ha lo scopo di essere utilizzata con la reflection in modo da poter testare isInstance()
 * 
 * @author simone
 */
public class DummyAnnotation extends Annotation.Data {

    @Override
    public <E extends Annotation.Data> E get() {
        return (E) this;
    }
    
}

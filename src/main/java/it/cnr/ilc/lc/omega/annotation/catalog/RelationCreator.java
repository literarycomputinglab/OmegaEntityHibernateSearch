/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.cnr.ilc.lc.omega.annotation.catalog;

import java.util.List;

/**
 *
 * @author simone
 */
public final class RelationCreator {

    private RelationParser parser;
    private String[] input;
    
    public RelationCreator() {
    
    }

    public RelationCreator addInput(String[] input) {
        this.input = input;
        return this;
    }

    public RelationCreator addParser(RelationParser parser) {
        this.parser = parser;
        return this;
    }

    public List<DublinCoreAnnotation.DublinCoreRelation> create () {
        
        return parser.parse(input);
    }
    
    
}

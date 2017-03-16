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
interface RelationParser {
    
    List<DublinCoreAnnotation.DublinCoreRelation> parse(String[] input);
    
}

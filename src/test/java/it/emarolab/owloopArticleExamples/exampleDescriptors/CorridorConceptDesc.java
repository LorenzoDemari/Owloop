package it.emarolab.owloopArticleExamples.exampleDescriptors;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.SemanticRestriction;

/**
 *  <p>
 *      Extends LocationConceptDesc.
 *  </p>
 *  Adds some-restriction on the concept "CORRIDOR", i.e, CORRIDOR hasDoor some DOOR.
 *  Furthermore, adds min-cardinality-restriction, i.e., CORRIDOR hasDoor min 2 DOOR.
 */
public class CorridorConceptDesc
        extends LocationConceptDesc {

    public CorridorConceptDesc(OWLReferences onto) {

        super("CORRIDOR", onto);
    }

    // overriding with a MinCardinality-restriction
    @Override
    protected SemanticRestriction.ClassRestrictedOnMinObject getRestriction(){
        SemanticRestriction.ClassRestrictedOnMinObject definition = new SemanticRestriction.ClassRestrictedOnMinObject();
        definition.setCardinality( 2);
        return definition;
    }
}

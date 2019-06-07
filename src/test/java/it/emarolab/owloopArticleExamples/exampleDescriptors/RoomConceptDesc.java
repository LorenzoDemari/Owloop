package it.emarolab.owloopArticleExamples.exampleDescriptors;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.amor.owlInterface.SemanticRestriction;

/**
 *  <p>
 *      Extends LocationConceptDesc.
 *  </p>
 *  Adds some-restriction on the concept "ROOM", i.e, ROOM hasDoor some DOOR.
 *  Furthermore, adds max-cardinality-restriction, i.e., ROOM hasDoor max 1 DOOR.
 */
public class RoomConceptDesc
        extends LocationConceptDesc {

    public RoomConceptDesc(OWLReferences onto) {

        super("ROOM", onto);
    }

    // overriding with a MaxCardinality-restriction
    @Override
    protected SemanticRestriction.ApplyingPropertyRestriction getRestriction(){
        SemanticRestriction.ClassRestrictedOnMaxObject definition = new SemanticRestriction.ClassRestrictedOnMaxObject();
        definition.setCardinality( 1);
        return definition;
    }
}

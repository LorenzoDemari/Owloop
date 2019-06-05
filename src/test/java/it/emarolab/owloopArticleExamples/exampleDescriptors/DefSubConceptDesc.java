package it.emarolab.owloopArticleExamples.exampleDescriptors;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.descriptor.construction.descriptorGround.ConceptGround;
import it.emarolab.owloop.descriptor.construction.descriptorExpression.ConceptExpression;
import it.emarolab.owloop.descriptor.construction.descriptorEntitySet.DescriptorEntitySet;
import it.emarolab.owloop.descriptor.utility.conceptDescriptor.FullConceptDesc;
import org.semanticweb.owlapi.model.OWLClass;

import java.util.List;

/**
 * This is an example of a 'compound' Concept Descriptor which implements 2 {@link ConceptExpression}s.
 * <ul>
 * <li><b>{@link ConceptExpression.Definition}</b>:  to describe the definition of a Class..</li>
 * <li><b>{@link ConceptExpression.Sub}</b>:         to describe that a Class subsumes another Class.</li>
 * </ul>
 * <p>
 *     Doing build() with this Descriptor returns the descriptor of type {@link DefSubConceptDesc}.
 * </p>
 * See {@link FullConceptDesc} for an example of a 'compound' Concept Descriptor that implements all ConceptExpressions.
 */
public class DefSubConceptDesc
        extends ConceptGround
        implements ConceptExpression.Definition,
        ConceptExpression.Sub<DefSubConceptDesc>{

    private DescriptorEntitySet.Restrictions restrictions = new DescriptorEntitySet.Restrictions();
    private DescriptorEntitySet.Concepts subConcept = new DescriptorEntitySet.Concepts();

    // constructors for ConceptGround
    public DefSubConceptDesc(OWLClass instance, OWLReferences onto) {
        super(instance, onto);
    }

    // implementations for Axiom.descriptor
    @Override
    public List<MappingIntent> readExpressionAxioms() {
        List<MappingIntent> r = Definition.super.readExpressionAxioms(); // call this before Sub or Super !!!
        r.addAll( Sub.super.readExpressionAxioms());
        return r;
    }

    @Override
    public List<MappingIntent> writeExpressionAxioms() {
        List<MappingIntent> r = Sub.super.writeExpressionAxioms();
        r.addAll( Definition.super.writeExpressionAxioms()); // call this before Sub or Super !!!
        return r;
    }

    // implementations for ConceptExpression.Definition
    @Override
    public DescriptorEntitySet.Restrictions getDefinitionConcepts() {
        return restrictions;
    }

    // you cannot build Definition (based on Restrictions)


    // implementations for ConceptExpression.Super
    @Override // called during build...() you can change the returning type to any implementations of ConceptExpression
    public DefSubConceptDesc getSubConceptDescriptor(OWLClass instance, OWLReferences ontology) {
        return new DefSubConceptDesc( instance, ontology);
    }

    @Override
    public DescriptorEntitySet.Concepts getSubConcepts() {
        return subConcept;
    }


    // implementation for standard object interface
    // equals() and hashCode() is based on DescriptorGround<?> which considers only the ground
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                NL + "\t\t\t" + getGround() +
                "," + NL + "\t= " + restrictions +
                "," + NL + "\t⊃ " + subConcept +
                NL + "}" + NL;
    }
}

// todo: (i) rename entitySet objects properly (ii) rename the methods related to those variables properly (iii) modification in toString() (iv) fix spaces and comments.
// todo: in main javadoc say "implements 2 some expression interfaces
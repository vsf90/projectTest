package com.trans.rdf;


import org.openrdf.model.Namespace;
import org.openrdf.model.impl.NamespaceImpl;


/**
 * Vocabulary constants for the
 * <a href="http://www.w3.org/TR/prov-o/">PROV Ontology (PROV-O)</a>.
 * 
 * @see <a href="http://www.w3.org/TR/prov-o/">The PROV Ontology (PROV-O)</a>
 *
 * @author lbihanic
 */
public class PROV
{
    /** The PROV namespace: <code>http://www.w3.org/ns/prov#</code> */
    public static final String NAMESPACE = "http://www.w3.org/ns/prov#";

    /** The recommended prefix for the PROV namespace: "prov". */
    public static final String PREFIX = "prov";

    /**
     * An immutable {@link Namespace} constant that represents the PROV
     * namespace.
     */
    public static final Namespace NS = new NamespaceImpl(PREFIX, NAMESPACE);

    private PROV() {
        throw new UnsupportedOperationException();
    }
}

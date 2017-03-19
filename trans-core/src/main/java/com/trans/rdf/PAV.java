package com.trans.rdf;


import org.openrdf.model.Namespace;
import org.openrdf.model.impl.NamespaceImpl;


/**
 * Vocabulary constants for the
 * <a href="http://purl.org/pav/">Provenance, Authoring and Versioning (PAV)</a>
 * ontology.
 * 
 * @see <a href="https://github.com/pav-ontology/pav">The PAV ontology project</a>
 *
 * @author lbihanic
 */
public class PAV
{
    /** The PAV namespace: <code>http://purl.org/pav/</code> */
    public static final String NAMESPACE = "http://purl.org/pav/";

    /** The recommended prefix for the PAV namespace: "pav". */
    public static final String PREFIX = "pav";

    /**
     * An immutable {@link Namespace} constant that represents the PAV
     * namespace.
     */
    public static final Namespace NS = new NamespaceImpl(PREFIX, NAMESPACE);

    private PAV() {
        throw new UnsupportedOperationException();
    }
}

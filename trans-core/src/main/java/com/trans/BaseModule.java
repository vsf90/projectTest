package com.trans;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import static org.datalift.fwk.util.StringUtils.isBlank;
import static org.datalift.fwk.MediaTypes.TEXT_HTML_UTF8;

import org.openrdf.model.vocabulary.DC;
import org.openrdf.model.vocabulary.DCTERMS;
import org.openrdf.model.vocabulary.FOAF;
import org.openrdf.model.vocabulary.SKOS;

import com.trans.rdf.PAV;
import com.trans.rdf.PROV;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.datalift.fwk.view.TemplateModel;
import org.datalift.fwk.view.ViewFactory;

/**
 * A <code>BaseModule</code> extends
 * BaseModule of Datalift and add
 * default variables, configuration, methods
 * for RMeS Application.
 * 
 */
public abstract class BaseModule extends org.datalift.fwk.BaseModule
{
	protected BaseModule(String name) {
		super(name);
	}
	
	// ------------------------------------------------------------------------
    // Constants & Configuration properties
    // -----------------------------------------------------------------------
	
	/**
     * Initialize prefix for rdf SPARQL query 
     */
	public static final String DEFAULT_SPARQL_PREFIXES =
    		"PREFIX dc:       <" + DC.NAMESPACE         + "> \n"+
    	    "PREFIX dct:      <" + DCTERMS.NAMESPACE    + "> \n"+
    	    "PREFIX pav:      <" + PAV.NAMESPACE        + "> \n" +
    	    "PREFIX skos:     <" + SKOS.NAMESPACE       + "> \n" +
            "PREFIX prov:     <" + PROV.NAMESPACE       + "> \n" +
            "PREFIX foaf:     <" + FOAF.NAMESPACE       + "> \n" ;
	
    /**
     * local repository for saving docs
     */
    public static final String STORAGE_REPO = "docs/";

    //-------------------------------------------------------------------------
    // Web service utility methods
    //-------------------------------------------------------------------------
	/**
     * Return a model for the specified template view, populated with
     * the specified model object.
     * <p>
     * The template name shall be relative to the module, the module
     * name is automatically prepended.</p>
     * @param  templateName   the relative template name.
     * @param  it             the model object to pass on to the view.
     *
     * @return a populated template model.
     */
    protected final TemplateModel newView(String template, Object it) {
        return ViewFactory.newView("/" + this.getName() + '/' + template, it);
    }
    
    /**
     * <code>buildResponse</code> Build a JAX-RS
     * Response from a velocity template
     * 
     * @param  template                 Template name.
     * @param  variables                Variables to put in the template
     * @param  bindingLayoutVariables   Default variables to binds
     *
     * @return a JAX-RS {@link Response} Response object
     */
    public final Response buildResponse(String template, Map<String,Object> variables,
    		HttpServletRequest request){
    	// Create and populate view template.
		TemplateModel view = this.newView(template, variables);
		this.putDefaultVariables(view);
		// if ( bindings.addConceptVariables() ){
			// this.putDefaultConceptVariables(view);
		// }
		return Response.ok(view, TEXT_HTML_UTF8).build();
    }
    
    //-------------------------------------------------------------------------
    // Put the default variables for items
    //-------------------------------------------------------------------------
    
    /**
     * Put in a template model the global variable
     * <ul>
     * 		<li>first_language put the first language of application</li>
     * 		<li>second_language put the second language of application</li>
     * 		<li>build put the build version</li>
     * </ul>
     * 
     * {@link TemplateModel}
     * 
     * @param  templateName   the relative template name.
     *
     * @return a populated template model.
     */
    protected final void putDefaultVariables(TemplateModel view) {
    	view.put("test", "test");
    }
    
    //-------------------------------------------------------------------------
    // Util for checking parameters
    //-------------------------------------------------------------------------
    /**
     * Check if the parameter is not blank
     * <p>
     * 		Verify if parameter is not blank.
     * 		throwing webApplication
     * </p>
     * 
     * @param   name    the message to join
     * @param   value   the value to check
     * 
     * @return  properties  properties of rmes properties
     */
    protected final void checkParam(String name, String value)
    										throws WebApplicationException {
		if (isBlank(value)) {
			this.invalidParam(name, null);
		}
	}
    
    /**
     * Check if the parameter is not null
     * <p>
     * 		Verify if parameter is not null
     * 		throwing webApplication
     * </p>
     * 
     * @param   name    the message to join
     * @param   value   the value to check
     *
     * @return  properties  properties of rmes properties
     */
    protected final void invalidParam(String name, String value)
            									throws WebApplicationException {
		String message = (value == null)?
			"Mandatory parameter is missing: " + name + '\n':
			"Invalid or unsupported value for parameter \""
			        + name + "\": " + value + '\n';
		throw new WebApplicationException(
						Response.status(BAD_REQUEST).entity(message)
								.type(TEXT_PLAIN_TYPE).build());
	}
	
	/**
	 * Return access controller object of user
	 * 
	 * {@link AccessController}
	 */
	// public static final AccessController getAccessController(HttpServletRequest request){
		// return RmesSecurityManagerFactory
                   // .getSecurityManager().getAccessController(request);
	// }
	
	/**
     * Put in a template model the access controller object
     * <ul>
     * 		<li>accessCtrl AccessController object</li>
     * </ul>
     * 
     * {@link TemplateModel}
     * {@link HttpServletRequest}
     * 
     * @param  templateName         the relative template name.
     * @param  HttpServletRequest   the HTTP servlet request
     */
	// public static final void putAccessControllerOnView(TemplateModel view, HttpServletRequest request) {
		// view.put("accessCtrl", getAccessController(request));
	// }
}

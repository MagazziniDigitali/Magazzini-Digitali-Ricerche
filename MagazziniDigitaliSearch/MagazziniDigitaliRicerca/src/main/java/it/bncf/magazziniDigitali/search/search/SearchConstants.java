/**
 * 
 */
package it.bncf.magazziniDigitali.search.search;

import it.bncf.magazziniDigitali.search.facet.FacetConstants;


/**
 * @author massi
 *
 */
public interface SearchConstants extends FacetConstants {
	@DefaultStringValue("keywords")
	String keywords();
	String keywords_title();

}

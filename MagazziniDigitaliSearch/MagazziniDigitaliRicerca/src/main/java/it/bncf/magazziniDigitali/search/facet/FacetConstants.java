/**
 * 
 */
package it.bncf.magazziniDigitali.search.facet;

import it.bncf.magazziniDigitali.search.tools.graphics.Costanti;


/**
 * @author massi
 *
 */
public interface FacetConstants extends Costanti {

	@DefaultStringValue("mimeType")
	String mimeType();
	@DefaultStringValue("mimeType_fc")
	String mimeType_fc();
	String mimeType_title();

	@DefaultStringValue("promon")
	String promon();
	@DefaultStringValue("promon_fc")
	String promon_fc();
	String promon_title();

	@DefaultStringValue("relationshipType")
	String relationshipType();
	@DefaultStringValue("relationshipType_fc")
	String relationshipType_fc();
	String relationshipType_title();

	@DefaultStringValue("tipoOggetto")
	String tipoOggetto();
	@DefaultStringValue("tipoOggetto_fc")
	String tipoOggetto_fc();
	String tipoOggetto_title();

}

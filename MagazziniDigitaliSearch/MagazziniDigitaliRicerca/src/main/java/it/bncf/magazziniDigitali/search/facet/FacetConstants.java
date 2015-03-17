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

	@DefaultStringValue("tipoDocumento")
	String tipoDocumento();
	@DefaultStringValue("tipoDocumento_fc")
	String tipoDocumento_fc();
	String tipoDocumento_title();

	@DefaultStringValue("autore")
	String autore();
	@DefaultStringValue("autore_fc")
	String autore_fc();
	String autore_title();

	@DefaultStringValue("titolo")
	String titolo();
	@DefaultStringValue("titolo_fc")
	String titolo_fc();
	String titolo_title();

	@DefaultStringValue("data")
	String data();
	@DefaultStringValue("data_fc")
	String data_fc();
	String data_title();

	@DefaultStringValue("tipoContenitore")
	String tipoContenitore();
	@DefaultStringValue("tipoContenitore_fc")
	String tipoContenitore_fc();
	String tipoContenitore_title();

	@DefaultStringValue("fileType")
	String fileType();
	@DefaultStringValue("fileType_fc")
	String fileType_fc();
	String fileType_title();

	@DefaultStringValue("eventType_fc")
	String eventType();
	@DefaultStringValue("eventType_fc")
	String eventType_fc();
	String eventType_title();

	@DefaultStringValue("agentSoftware")
	String agentSoftware();
	@DefaultStringValue("agentSoftware_fc")
	String agentSoftware_fc();
	String agentSoftware_title();

	@DefaultStringValue("agentDepositante")
	String agentDepositante();
	@DefaultStringValue("agentDepositante_fc")
	String agentDepositante_fc();
	String agentDepositante_title();
}

/**
 * 
 */
package it.bncf.magazziniDigitali.search.tools.graphics;

import com.google.gwt.i18n.client.Constants;

/**
 * @author massi
 *
 */
public interface Stato extends Constants {

	@DefaultStringValue("INI")
	String StatoInit();
	String StatoInit_title();

	@DefaultStringValue("ASS")
	String StatoAssegnato();
	String StatoAssegnato_title();

	@DefaultStringValue("ERR1")
	String StatoErrorCreateFolder();
	String StatoErrorCreateFolder_title();

	@DefaultStringValue("$ASS")
	String StatoInDigitalizzazione();
	String StatoInDigitalizzazione_title();
	String StatoInDigitalizzazione_messageConfirm();

	@DefaultStringValue("DIG")
	String StatoDigitalizzato();
	String StatoDigitalizzato_title();

	@DefaultStringValue("ERR2")
	String StatoErrorSendImage();
	String StatoErrorSendImage_title();

	@DefaultStringValue("COP")
	String StatoCopiato();
	String StatoCopiato_title();

	@DefaultStringValue("ERR3")
	String StatoErrorPreNomenclato();
	String StatoErrorPreNomenclato_title();

	@DefaultStringValue("PRE")
	String StatoPreNomenclato();
	String StatoPreNomenclato_title();
}

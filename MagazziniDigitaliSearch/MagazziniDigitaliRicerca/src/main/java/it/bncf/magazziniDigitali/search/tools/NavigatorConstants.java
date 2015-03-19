package it.bncf.magazziniDigitali.search.tools;

import com.google.gwt.i18n.client.Constants;

public interface NavigatorConstants extends Constants {

	String bInizio();
	String bIndietro();
	String bAvanti();
	String bFine();
	String recPag();
	
	@DefaultIntValue(value=1000)
	int maxRecSearch();

	@DefaultIntValue(value=200)
	int maxRecPage();
}

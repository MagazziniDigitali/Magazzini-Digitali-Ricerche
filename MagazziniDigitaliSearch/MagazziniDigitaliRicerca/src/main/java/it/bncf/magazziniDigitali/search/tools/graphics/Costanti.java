package it.bncf.magazziniDigitali.search.tools.graphics;


public interface Costanti extends Stato {

	@DefaultStringValue("bAdd")
	String bAdd();
	String bAdd_title();
	String bAdd_icon();

	@DefaultStringValue("bNew")
	String bNew();
	String bNew_title();
	String bNew_icon();

	@DefaultStringValue("bConfirm")
	String bConfirm();
	String bConfirm_title();
	String bConfirm_icon();

	@DefaultStringValue("bSearch")
	String bSearch();
	String bSearch_title();
	String bSearch_icon();

	@DefaultStringValue("bClear")
	String bClear();
	String bClear_title();
	String bClear_icon();

	@DefaultStringValue("Modify")
	String modifica();
	String modifica_title();
	String modifica_icon();

	@DefaultStringValue("Cancel")
	String cancella();
	String cancella_title();
	String cancella_icon();

	@DefaultStringValue("bSave")
	String bSave();
	String bSave_title();
	String bSave_icon();

	@DefaultStringValue("bReset")
	String bReset();
	String bReset_title();
	String bReset_icon();

	String msgSave_title();
	
	String msgNuovo_title();
	
	String msgModifica_title();
	
	String msgCancel_title();

	String descrizione();

	String cancelNotAbilitata();

	@DefaultStringValue("id")
	String id();
	@DefaultStringValue("id_title")
	String id_title();
}

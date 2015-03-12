/**
 * 
 */
package it.bncf.magazziniDigitali.search.tools.graphics;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.ButtonItem;

/**
 * @author massi
 *
 */
public class ButtonSearch extends ButtonItem {

	private Costanti constants = GWT
			.create(Costanti.class);

	/**
	 * 
	 */
	public ButtonSearch() {
		init(constants.bSearch(), constants.bSearch_title());
	}

	/**
	 * @param jsObj
	 */
	public ButtonSearch(JavaScriptObject jsObj) {
		super(jsObj);
		init(constants.bSearch(), constants.bSearch_title());
	}

	/**
	 * @param title
	 */
	public ButtonSearch(String title) {
		super(title);
		init(constants.bSearch(), title);
	}

	/**
	 * @param name
	 * @param title
	 */
	public ButtonSearch(String name, String title) {
		super(name, title);
		init(name, title);
	}

	private void init(String name, String title){
		setName(name);
		setTitle(title);
		setTitleColSpan(1);
		setValue(title);
//		setShowValueIconOnly(true);
		setIcon(constants.bSearch_icon());
		setIconHeight(24);
//		setShowIcons(true);
//		setDisabled(false);
		setEndRow(false);
		setTabIndex(4);
		setAlign(Alignment.LEFT);
		setStartRow(false);
	}
}

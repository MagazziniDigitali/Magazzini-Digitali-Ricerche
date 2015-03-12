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
public class ButtonClear extends ButtonItem {

	private Costanti constants = GWT
			.create(Costanti.class);

	/**
	 * 
	 */
	public ButtonClear() {
		init(constants.bClear(), constants.bClear_title());
	}

	/**
	 * @param jsObj
	 */
	public ButtonClear(JavaScriptObject jsObj) {
		super(jsObj);
		init(constants.bClear(), constants.bClear_title());
	}

	/**
	 * @param title
	 */
	public ButtonClear(String title) {
		super(title);
		init(constants.bClear(), title);
	}

	/**
	 * @param name
	 * @param title
	 */
	public ButtonClear(String name, String title) {
		super(name, title);
		init(name, title);
	}

	private void init(String name, String title){
		setName(name);
		setTitle(title);
		setTitleColSpan(1);
		setIcon(constants.bClear_icon());
		setIconHeight(24);
		setEndRow(false);
		setTabIndex(5);
		setAlign(Alignment.LEFT);
		setStartRow(false);
	}
}

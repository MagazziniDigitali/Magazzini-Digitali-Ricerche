/**
 * 
 */
package it.bncf.magazziniDigitali.search.menu;

import it.bncf.magazziniDigitali.search.search.SearchPanel;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;

/**
 * @author massi
 *
 */
public class MenuPanel extends MenuBar {

	/**
	 * 
	 */
	public MenuPanel(SearchPanel searchPanel) {
		init(searchPanel);
	}

	private void init(SearchPanel searchPanel){
		MenuPanelConstants constants = null;
		Command cmd = null;

		cmd = new Command() {
		      public void execute() {
		        Window.alert("You selected a menu item!");
		      }
		    };

		this.setStylePrimaryName("gwt-MenuPrinc");

		constants = GWT.create(MenuPanelConstants.class);
		this.addItem(constants.ricercaBase(), new ActionMenu(searchPanel, ActionMenu.BASE));
		this.addItem(constants.ricercaAvanzata(), cmd);
		this.addItem(constants.help(), cmd);
	}
}

class ActionMenu implements Command{

	public static String BASE = "base";

	private SearchPanel searchPanel;

	private String azione;

	public ActionMenu(SearchPanel searchPanel, String azione){
		this.searchPanel = searchPanel;
		this.azione = azione;
	}

	@Override
	public void execute() {
		searchPanel.setVisible(true);
		if (azione.equals(BASE)){
			searchPanel.getRicercaBase();
		}
	}
	
}
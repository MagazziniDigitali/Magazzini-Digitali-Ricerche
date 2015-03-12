package it.bncf.magazziniDigitali.search.client;


import it.bncf.magazziniDigitali.search.facet.FacetPanel;
import it.bncf.magazziniDigitali.search.menu.MenuPanel;
import it.bncf.magazziniDigitali.search.result.ResultPanel;
import it.bncf.magazziniDigitali.search.search.SearchPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class index implements EntryPoint {
	/**
	 * 
	 */
	public index() {
	}

	
//	private RootPanel header;
	private RootPanel center;
//	private RootPanel footer;
	protected index index = null;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		FacetPanel facetPanel = null;
		SearchPanel searchPanel = null;
		ResultPanel resultPanel = null;

		
//		header = RootPanel.get("header");
		center = RootPanel.get("center");
		center.clear();

		facetPanel = new FacetPanel();
		facetPanel.setStylePrimaryName("facetPanel");
		facetPanel.setVisible(false);
		resultPanel = new ResultPanel();
		resultPanel.setStylePrimaryName("resultPanel");
		resultPanel.setVisible(false);
		searchPanel = new SearchPanel(facetPanel, resultPanel);
		searchPanel.setStylePrimaryName("searchPanel");
		searchPanel.setVisible(false);
		center.add(new MenuPanel(searchPanel));
		center.add(searchPanel);
		center.add(facetPanel);
		center.add(resultPanel);
//		footer = RootPanel.get("footer");

		index = this;
	}
}


package it.bncf.magazziniDigitali.search.client;


import it.bncf.magazziniDigitali.search.facet.FacetPanel;
import it.bncf.magazziniDigitali.search.menu.MenuPanel;
import it.bncf.magazziniDigitali.search.result.ResultPanel;
import it.bncf.magazziniDigitali.search.search.SearchPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
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
		IndexServiceAsync services = null;

		Window.addWindowClosingHandler(new Window.ClosingHandler() {
			
			@Override
			public void onWindowClosing(ClosingEvent event) {
				Type<ClosingHandler> ss = event.getAssociatedType();
				ss.hashCode();
				System.out.println("addWindowClosingHandler: "+
						ss.hashCode()+
						" - "+
						event.getMessage()+
						" - "+
						(event.getSource()==null?null:event.getSource().getClass().getName())+
						" - "+
						event.toDebugString()+
						" - "+
						event.toString());
			}
		});

		Window.addCloseHandler(new CloseHandler<Window>() {
			
			@Override
			public void onClose(CloseEvent<Window> event) {
				System.out.println("addCloseHandler "+
						event.hashCode()+
						" - "+
						event.toDebugString()+
						" - "+
						event.getSource()+
						" - "+
						event.getTarget()+
						" - "+
						event.isAutoClosed());
			}
		});
//		header = RootPanel.get("header");
		center = RootPanel.get("center");
		center.clear();

		if (Window.Location.getParameter("id")== null){
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
			searchPanel.setVisible(true);
			searchPanel.getRicercaBase();
			searchPanel.search(0, null, false);
		} else {
			services = GWT.create(IndexService.class);
			try {
				services.getScheda(Window.Location.getParameter("id"), new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						center.add(new HTMLPanel(result));
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}
				});
			} catch (Exception e) {
				Window.alert(e.getMessage());
			}
		}
//		footer = RootPanel.get("footer");

		index = this;
	}
}


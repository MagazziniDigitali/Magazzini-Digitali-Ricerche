/**
 * 
 */
package it.bncf.magazziniDigitali.search.search;

import it.bncf.magazziniDigitali.search.facet.FacetConstants;
import it.bncf.magazziniDigitali.search.facet.FacetPanel;
import it.bncf.magazziniDigitali.search.result.ResultPanel;
import it.bncf.magazziniDigitali.search.tools.NavigatorPanel;
import it.bncf.magazziniDigitali.search.tools.graphics.ButtonClear;
import it.bncf.magazziniDigitali.search.tools.graphics.ButtonSearch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.SearchForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;

/**
 * @author massi
 *
 */
public class SearchPanel extends CaptionPanel {

	private SearchConstants costanti = null;

	private SearchForm searchForm = null;

	private TextItem tKeywords = null;

	private ButtonSearch bSearch = null;

	private ButtonClear bClear = null;

	private FacetPanel facetPanel = null;
	private ResultPanel resultPanel = null;

	/**
	 * 
	 */
	public SearchPanel(FacetPanel facetPanel, ResultPanel resultPanel) {
		costanti = GWT.create(SearchConstants.class);
		this.facetPanel = facetPanel;
		this.resultPanel = resultPanel;
	}

	public void getRicercaBase(){
		FlowPanel fp = null;

		this.clear();

		fp = new FlowPanel();
		fp.add(getSearchForm());
		this.add(fp);
	}


	protected SearchForm getSearchForm() {
		FormItem[] lFields;
		if (searchForm == null) {
			searchForm = new SearchForm();
//			searchForm.setAutoWidth();
//			searchForm.setSize("550px", "30px");
			searchForm.setNumCols(4);
			lFields = new FormItem[3];
			lFields[0] =getTKeywors();
			lFields[1]=getBSearch();
			lFields[2]=getBClear();
			searchForm.setFields(lFields);
		}
		searchForm.redraw();
		return searchForm;
	}

	/**
	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato initTrasf
	 * 
	 * @return
	 */
	public TextItem getTKeywors(){
		if (tKeywords ==null){
			tKeywords = new TextItem(costanti.keywords(), costanti.keywords_title());
			tKeywords.setValue("");
			tKeywords.setRequired(true);
			tKeywords.setWrapTitle(false);
			
			tKeywords.addKeyPressHandler(new KeyPressHandler() {
				
				@Override
				public void onKeyPress(KeyPressEvent event) {
					if (event.getCharacterValue() != null && 
							(event.getCharacterValue()==13 ||
							event.getCharacterValue()==10)){
						event.cancel();
						search(0,null,false);
					}
				}
			});
		}
		return tKeywords;
	}

	private ButtonSearch getBSearch(){
		if (bSearch==null){
			bSearch = new ButtonSearch();
			bSearch.setColSpan(1);
			bSearch.setAlign(Alignment.CENTER);
			bSearch.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					search(0, null, false);
				}
			});
		}
		return bSearch;
	}

	private ButtonClear getBClear(){
		if (bClear==null){
			bClear =new ButtonClear();
			bClear.setColSpan(1);
			bClear.setAlign(Alignment.CENTER);
			bClear.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					getTKeywors().clearValue();
					search(0, null, false);
				}
			});
		}
		return bClear;
	}

	public void search(Integer start, Integer rows, Boolean facetFilter){
		SearchServiceAsync searchService = null;
		TreeMap<String, String[]> queryFacet = null;
		Vector<String> values =null;
		
		bSearch.setDisabled(true);
		bClear.setDisabled(true);
		facetPanel.setVisible(false);
		resultPanel.setVisible(false);

		searchService = GWT.create(SearchService.class);
		if (facetFilter.booleanValue()){
			if (facetPanel.getVerticalPanel() != null &&
					facetPanel.getVerticalPanel().getWidgetCount()>0){
				for (int x=0; x<facetPanel.getVerticalPanel().getWidgetCount(); x++){
					CaptionPanel cp = (CaptionPanel) facetPanel.getVerticalPanel().getWidget(x);
					
					Iterator<Widget> widgets = cp.iterator();
				    while (widgets.hasNext()){
				    	Tree tree = (Tree) widgets.next();
				    	for (int y=0; y<tree.getItemCount(); y++){
				    		TreeItem treeItem = tree.getItem(y);
				    		CheckBox cb = (CheckBox) treeItem.getWidget();
				    		if (cb.getValue().booleanValue()){
				    			if (queryFacet==null){
				    				queryFacet = new TreeMap<String, String[]>();
				    			}
				    			if (queryFacet.get(cb.getName())!= null){
				    				values = new Vector<String>(Arrays.asList(queryFacet.get(cb.getName())));
				    			} else {
				    				values = new Vector<String>();
				    			}
				    			values.add(cb.getTitle());
				    			queryFacet.put(cb.getName(), values.toArray(new String[values.size()]));
				    		}
				    	}
				    }
				}
			}
		}
		searchService.find(getTKeywors().getValueAsString(), queryFacet, start, rows,
				new SearchResult(facetPanel, resultPanel, getBSearch(), getBClear(), searchService, queryFacet, this));
	}
}

class SearchResult implements AsyncCallback<Integer> {

	private FacetPanel facetPanel = null;
	private ResultPanel resultPanel = null;
	private ButtonSearch bSearch = null;
	private ButtonClear bClear = null;
	private SearchServiceAsync searchService = null;
	private TreeMap<String, String[]> queryFacet  = null;
	private SearchPanel searchPanel = null;

	public SearchResult(FacetPanel facetPanel, ResultPanel resultPanel, 
			ButtonSearch bSearch, ButtonClear bClear, SearchServiceAsync searchService, TreeMap<String, String[]> queryFacet,
			SearchPanel searchPanel) {
		this.facetPanel = facetPanel;
		this.resultPanel = resultPanel;
		this.bSearch = bSearch;
		this.bClear = bClear;
		this.searchService = searchService;
		this.queryFacet = queryFacet;
		this.searchPanel = searchPanel;
	}

	@Override
	public void onFailure(Throwable caught) {
		com.google.gwt.user.client.Window.alert(caught.getMessage());
		bSearch.setDisabled(false);
		bClear.setDisabled(false);
	}

	@Override
	public void onSuccess(Integer result) {
		bSearch.setDisabled(false);
		bClear.setDisabled(false);
		searchService.getFacets(new FacetsResult(facetPanel, queryFacet, searchPanel));
		searchService.getResults(new ResultsResult(resultPanel, searchService, searchPanel));
	}
	
}

class ResultsResult implements AsyncCallback<String> {

	private ResultPanel resultPanel = null;
	private SearchServiceAsync searchService = null;
	private SearchPanel searchPanel;

	public ResultsResult(ResultPanel resultPanel, SearchServiceAsync searchService, SearchPanel searchPanel){
		this.resultPanel = resultPanel;
		this.searchService = searchService;
		this.searchPanel = searchPanel;
	}

	@Override
	public void onFailure(Throwable caught) {
		com.google.gwt.user.client.Window.alert(caught.getMessage());
	}

	@Override
	public void onSuccess(String result) {
		HTMLPanel html = null;
		NodeList<Element> anchors = null;
		resultPanel.clear();
		
		html = new HTMLPanel(result);
		anchors = html.getElement().getElementsByTagName("a");
		for ( int i = 0 ; i < anchors.getLength() ; i++ ) {
		    Element a = anchors.getItem(i);
		    Anchor link = new Anchor(a.getInnerHTML(), true);
		    link.addClickHandler(new ClickResult(a.getTitle(),a.getAttribute("href")));
		    html.addAndReplaceElement(link, a);
		}
		
		resultPanel.add(html);
		searchService.getNavigator(new NavigatorResult(resultPanel, searchPanel));
		resultPanel.setVisible(true);
	}
	
}

class ClickResult implements com.google.gwt.event.dom.client.ClickHandler {
	private String title = null;
	private String id = null;

	public ClickResult(String title, String id){
		this.title = title;
		this.id = id;
	}

	@Override
	public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
		String href = null;

		if (title.equalsIgnoreCase("breve")){
			href= Window.Location.getHref();
			href += (href.indexOf("?")>-1?"&":"?");
			href += "id="+id;
			Window.open(href, "_blank", "");
		} else {
			Window.alert("Metodo non implementato: "+title);
		}
	}
	
}

class NavigatorResult implements AsyncCallback<TreeMap<String, String>> {

	private ResultPanel resultPanel = null;
//	private SearchServiceAsync searchService = null;
	private SearchPanel searchPanel = null;

	public NavigatorResult(ResultPanel resultPanel, SearchPanel searchPanel){
		this.resultPanel = resultPanel;
		this.searchPanel = searchPanel;
	}

	@Override
	public void onFailure(Throwable caught) {
		com.google.gwt.user.client.Window.alert(caught.getMessage());
	}

	@Override
	public void onSuccess(TreeMap<String, String> result) {
		resultPanel.add(new NavigatorPanel(searchPanel, result));
	}
	
}

class FacetsResult implements AsyncCallback<TreeMap<String, Vector<String[]>>> {


	private FacetConstants costanti = null;
	
	private FacetPanel facetPanel = null;
	private TreeMap<String, String[]> queryFacet  = null;
	private SearchPanel searchPanel = null;

	public FacetsResult(FacetPanel facetPanel, TreeMap<String, String[]> queryFacet, SearchPanel searchPanel){
		costanti = GWT.create(FacetConstants.class);
		this.facetPanel = facetPanel;
		this.queryFacet = queryFacet;
		this.searchPanel = searchPanel;
	}

	@Override
	public void onFailure(Throwable caught) {
		com.google.gwt.user.client.Window.alert(caught.getMessage());
	}

	@Override
	public void onSuccess(TreeMap<String, Vector<String[]>> result) {
		CaptionPanel cpFacet = null;
		NumberFormat mf = null;
		Tree tree = null;
		String key = null;
		String[] values = null;
		String value = null;
		String[] st = null;

		facetPanel.getVerticalPanel().clear();
		if (result != null && result.size()>0){
			for(Map.Entry<String, Vector<String[]>> entry: result.entrySet()){
				cpFacet = new CaptionPanel();
				cpFacet.setStylePrimaryName("fcTree");
				cpFacet.setCaptionText(getName(entry.getKey()));
				tree = new Tree();
				mf = NumberFormat.getFormat("#,###");
				for ( int x=0; x<entry.getValue().size(); x++){
					values = entry.getValue().get(x);
//				for(Map.Entry<String, Long> entry2: entry.getValue().entrySet()){
					CheckBox cb = new CheckBox();
					key = entry.getKey().split("\t")[1];
					cb.setName(key);
					
					cb.setTitle((String) values[0]);
//					cb.setTitle(entry2.getKey());
					st = ((String) values[0]).split("\n");
//					st = entry2.getKey().split("\n");
					value = "";
					for (int y=0;y <st.length; y++){
						if (st[y].replace("_", " ").trim().length()>0){
							value += (value.equals("")?"":" ")+st[y].replace("_", " ").trim();
						}
					}
					cb.setHTML("<b>"+mf.format(new Long(values[1]))+"</b> : "+value);
//					cb.setHTML("<b>"+mf.format(entry2.getValue())+"</b> : "+value);
					cb.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
						
						@Override
						public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
							searchPanel.search(0,null,true);
						}
					});
					if (queryFacet != null){
						if (queryFacet.get(key)!= null){
							for (int y=0; y<queryFacet.get(key).length; y++){
								if (queryFacet.get(key)[y].equals((String) values[0])){
//							if (queryFacet.get(entry.getKey()).equals(entry2.getKey())){
									cb.setValue(Boolean.TRUE);
									break;
								}
							}
						}
					}
					TreeItem item = new TreeItem(cb);
					tree.addItem(item);
				}
				cpFacet.add(tree);
				facetPanel.getVerticalPanel().add(cpFacet);
			}
			facetPanel.setVisible(true);
		}
	}

	private String getName(String key){
		String value = null;
		value = key.split("\t")[1];
		if (value.equals(costanti.mimeType_fc())){
			value = costanti.mimeType_title();
		} else if (value.equals(costanti.promon_fc())){
			value = costanti.promon_title();
		} else if (value.equals(costanti.relationshipType_fc())){
			value = costanti.relationshipType_title();
		} else if (value.equals(costanti.tipoOggetto_fc())){
			value = costanti.tipoOggetto_title();
		} else if (value.equals(costanti.tipoDocumento_fc())){
			value = costanti.tipoDocumento_title();
		} else if (value.equals(costanti.autore_fc())){
			value = costanti.autore_title();
		} else if (value.equals(costanti.titolo_fc())){
			value = costanti.titolo_title();
		} else if (value.equals(costanti.data_fc())){
			value = costanti.data_title();
		} else if (value.equals(costanti.tipoContenitore_fc())){
			value = costanti.tipoContenitore_title();
		} else if (value.equals(costanti.fileType_fc())){
			value = costanti.fileType_title();
		} else if (value.equals(costanti.eventType_fc())){
			value = costanti.eventType_title();
		} else if (value.equals(costanti.agentSoftware_fc())){
			value = costanti.agentSoftware_title();
		} else if (value.equals(costanti.agentDepositante_fc())){
			value = costanti.agentDepositante_title();
		}
		return value;
	}
}
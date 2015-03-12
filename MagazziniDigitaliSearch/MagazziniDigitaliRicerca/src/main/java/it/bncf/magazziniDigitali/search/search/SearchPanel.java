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

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
						search();
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
					search();
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
				}
			});
		}
		return bClear;
	}

	protected void search(){
		SearchServiceAsync searchService = null;
		TreeMap<String, String> queryFacet = null;
		
		bSearch.setDisabled(true);
		bClear.setDisabled(true);
		facetPanel.setVisible(false);
		resultPanel.setVisible(false);

		searchService = GWT.create(SearchService.class);
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
			    				queryFacet = new TreeMap<String, String>();
			    			}
			    			queryFacet.put(cb.getName(), cb.getTitle());
			    		}
			    	}
			    }
			}
		}
		searchService.find(getTKeywors().getValueAsString(), queryFacet,
				new SearchResult(facetPanel, resultPanel, getBSearch(), getBClear(), searchService, queryFacet, this));
	}
}

class SearchResult implements AsyncCallback<Integer> {

	private FacetPanel facetPanel = null;
	private ResultPanel resultPanel = null;
	private ButtonSearch bSearch = null;
	private ButtonClear bClear = null;
	private SearchServiceAsync searchService = null;
	private TreeMap<String, String> queryFacet  = null;
	private SearchPanel searchPanel = null;

	public SearchResult(FacetPanel facetPanel, ResultPanel resultPanel, 
			ButtonSearch bSearch, ButtonClear bClear, SearchServiceAsync searchService, TreeMap<String, String> queryFacet,
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
		resultPanel.clear();
		resultPanel.add(new HTMLPanel(result));
		searchService.getNavigator(new NavigatorResult(resultPanel, searchService, searchPanel));
		resultPanel.setVisible(true);
	}
	
}

class NavigatorResult implements AsyncCallback<TreeMap<String, Object>> {

	private ResultPanel resultPanel = null;
	private SearchServiceAsync searchService = null;
	private SearchPanel searchPanel = null;

	public NavigatorResult(ResultPanel resultPanel, SearchServiceAsync searchService, SearchPanel searchPanel){
		this.resultPanel = resultPanel;
		this.searchService = searchService;
		this.searchPanel = searchPanel;
	}

	@Override
	public void onFailure(Throwable caught) {
		com.google.gwt.user.client.Window.alert(caught.getMessage());
	}

	@Override
	public void onSuccess(TreeMap<String, Object> result) {
		resultPanel.add(new NavigatorPanel(searchService, searchPanel, result));
	}
	
}

class FacetsResult implements AsyncCallback<TreeMap<String, TreeMap<String, Long>>> {


	private FacetConstants costanti = null;
	
	private FacetPanel facetPanel = null;
	private TreeMap<String, String> queryFacet  = null;
	private SearchPanel searchPanel = null;

	public FacetsResult(FacetPanel facetPanel, TreeMap<String, String> queryFacet, SearchPanel searchPanel){
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
	public void onSuccess(TreeMap<String, TreeMap<String, Long>> result) {
		CaptionPanel cpFacet = null;
		NumberFormat mf = null;
		Tree tree = null;

		facetPanel.getVerticalPanel().clear();
		if (result != null && result.size()>0){
			for(Map.Entry<String, TreeMap<String, Long>> entry: result.entrySet()){
				cpFacet = new CaptionPanel();
				cpFacet.setStylePrimaryName("fcTree");
				cpFacet.setCaptionText(getName(entry.getKey()));
				tree = new Tree();
				mf = NumberFormat.getFormat("#,###");
				for(Map.Entry<String, Long> entry2: entry.getValue().entrySet()){
					CheckBox cb = new CheckBox();
					cb.setName(entry.getKey());
					cb.setTitle(entry2.getKey());
					cb.setHTML(entry2.getKey()+" : <b>"+mf.format(entry2.getValue())+"</b>");
					cb.addClickHandler(new com.google.gwt.event.dom.client.ClickHandler() {
						
						@Override
						public void onClick(com.google.gwt.event.dom.client.ClickEvent event) {
							searchPanel.search();
						}
					});
					if (queryFacet != null){
						if (queryFacet.get(entry.getKey())!= null){
							if (queryFacet.get(entry.getKey()).equals(entry2.getKey())){
								cb.setValue(Boolean.TRUE);
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
		value = key;
		if (key.equals(costanti.mimeType_fc())){
			value = costanti.mimeType_title();
		} else if (key.equals(costanti.promon_fc())){
			value = costanti.promon_title();
		} else if (key.equals(costanti.relationshipType_fc())){
			value = costanti.relationshipType_title();
		} else if (key.equals(costanti.tipoOggetto_fc())){
			value = costanti.tipoOggetto_title();
		}
		return value;
	}
}
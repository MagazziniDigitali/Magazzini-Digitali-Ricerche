/**
 * 
 */
package it.bncf.magazziniDigitali.search.result;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author massi
 *
 */
public class ResultPanel extends VerticalPanel {

//	private SearchConstants costanti = null;
//
//	private SearchForm searchForm = null;
//
//	private TextItem tKeywords = null;
//
//	private ButtonSearch bSearch = null;
//
//	private ButtonClear bClear = null;

	/**
	 * 
	 */
	public ResultPanel() {
//		costanti = GWT.create(SearchConstants.class);
	}
//
//	public void getRicercaBase(){
//		FlowPanel fp = null;
//
//		this.clear();
//
//		fp = new FlowPanel();
////		fp.add(getSearchForm());
//		this.add(fp);
//	}


//	protected SearchForm getSearchForm() {
//		FormItem[] lFields;
//		if (searchForm == null) {
//			searchForm = new SearchForm();
////			searchForm.setAutoWidth();
////			searchForm.setSize("550px", "30px");
//			searchForm.setNumCols(4);
//			lFields = new FormItem[3];
//			lFields[0] =getTKeywors();
//			lFields[1]=getBSearch();
//			lFields[2]=getBClear();
//			searchForm.setFields(lFields);
//		}
//		searchForm.redraw();
//		return searchForm;
//	}

//	/**
//	 * Disegno il campo per la visualizzazione del numero di oggetti nello stato initTrasf
//	 * 
//	 * @return
//	 */
//	public TextItem getTKeywors(){
//		if (tKeywords ==null){
//			tKeywords = new TextItem(costanti.keywords(), costanti.keywords_title());
//			tKeywords.setValue("");
//			tKeywords.setRequired(true);
//			tKeywords.setWrapTitle(false);
//			
//			tKeywords.addKeyPressHandler(new KeyPressHandler() {
//				
//				@Override
//				public void onKeyPress(KeyPressEvent event) {
//					if (event.getCharacterValue() != null && 
//							(event.getCharacterValue()==13 ||
//							event.getCharacterValue()==10)){
//						event.cancel();
////						search();
//					}
//				}
//			});
//		}
//		return tKeywords;
//	}
//
//	private ButtonSearch getBSearch(){
//		if (bSearch==null){
//			bSearch = new ButtonSearch();
//			bSearch.setColSpan(1);
//			bSearch.setAlign(Alignment.CENTER);
//			bSearch.addClickHandler(new ClickHandler() {
//				
//				@Override
//				public void onClick(ClickEvent event) {
////					search();
//				}
//			});
//		}
//		return bSearch;
//	}
//
//	private ButtonClear getBClear(){
//		if (bClear==null){
//			bClear =new ButtonClear();
//			bClear.setColSpan(1);
//			bClear.setAlign(Alignment.CENTER);
//			bClear.addClickHandler(new ClickHandler() {
//				
//				@Override
//				public void onClick(ClickEvent event) {
//					getTKeywors().clearValue();
//				}
//			});
//		}
//		return bClear;
//	}
}

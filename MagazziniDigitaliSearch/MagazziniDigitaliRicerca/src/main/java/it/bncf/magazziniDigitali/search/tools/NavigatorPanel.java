/**
 * 
 */
package it.bncf.magazziniDigitali.search.tools;

import it.bncf.magazziniDigitali.search.search.SearchPanel;
import it.bncf.magazziniDigitali.search.search.SearchServiceAsync;
//import it.bncf.magazziniDigitali.utils.Record;




import java.util.TreeMap;
import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * Classe utilizzata per la gestione della Navigazione del risultato di Ricerca
 * 
 * @author massi
 *
 */
public class NavigatorPanel extends HorizontalPanel {

	private NavigatorConstants constants;
	private NavigatorMessages messages;
	private Label lStatus;
	private PushButton pshbtnInizio;
	private PushButton pshbtnIndietro;
	private PushButton pshbtnAvanti;
	private PushButton pshbtnFine;
	private Label lRecPag;
	private IntegerBox ibRecPag;
	private ListGrid gCenter;
//	private Vector<Record> records;
//	private int page;
//	private int totPage;
	private Window window;
//	private int recPage=0;
	private SearchServiceAsync searchService = null;
	private SearchPanel searchPanel = null;

	/**
	 * Indica il primo record del risultato della ricerca (valore inizia da 0)
	 */
	private long start = 0;

	/**
	 * Indica il numero di record visualizzato per pagina
	 */
	private long rows = 0;

	/**
	 * Indica il numero totale dei record trovati nella ricerca
	 */
	private long numFound = 0;

	/**
	 * Tempo in millisecondi eseguiti per la ricerca
	 */
	private long qTime = 0;

	/**
	 * Costruttore
	 */
	public NavigatorPanel(SearchServiceAsync searchService, SearchPanel searchPanel, TreeMap<String, Object> result) {
//	public Navigator(ListGrid gCenter) {
		this.setStylePrimaryName("pNavigator");
		init(result);
//		disableNavigator();
		this.searchService = searchService;
		this.searchPanel = searchPanel;
		show();
//		this.gCenter=gCenter;
	}
	
	/**
	 * Disabilita i bottoni di navigazione
	 */
	private void disableNavigator(){
		pshbtnInizio.setEnabled(false);
		pshbtnIndietro.setEnabled(false);
		pshbtnAvanti.setEnabled(false);
		pshbtnFine.setEnabled(false);
	}
	
	/**
	 * Metodo utilizzato per inizializzare la visualizzazione dei bottoni per la navigazione
	 */
	private void init(TreeMap<String, Object> result){
		constants = GWT.create(NavigatorConstants.class);
		messages = GWT.create(NavigatorMessages.class);
		setHeight("26px");
		
		start = 0;
		rows = 0;
		numFound = 0;
		qTime = 0;
		if (result != null){
			if (result.get("start") != null){
				start = (Long)result.get("start");
			}
			if (result.get("rows") != null){
				rows = (Long)result.get("rows");
			}
			if (result.get("numFound") != null){
				numFound = (Long)result.get("numFound");
			}
			if (result.get("qTime") != null){
				qTime = (Long)result.get("qTime");
			}
		}
		
		add(getLStatus());
		add(getPshbtnInizio());
		add(getPshbtnIndietro());
		add(getPshbtnAvanti());
		add(getPshbtnFine());
		add(getLRecPag());
		add(getIbRecPag());
	}

	private SimplePanel getLStatus(){
		SimplePanel sp = null;
		if (lStatus==null){
			lStatus = new Label();
			lStatus.setText("");
		}
		sp = new SimplePanel();
		sp.setStyleName("lStatus");
		sp.add(lStatus);
		return sp;
	}

	/**
	 * Disegna il 
	 * @return
	 */
	private SimplePanel getLRecPag(){
		SimplePanel sp = null;
		if (lRecPag==null){
			lRecPag = new Label();
			lRecPag.setText(constants.recPag()+" :");
		}
		sp = new SimplePanel();
		sp.setStyleName("lRecPag");
		sp.add(lRecPag);
		return sp;
	}

	private SimplePanel getIbRecPag(){
		SimplePanel sp = null;
		if (ibRecPag==null){
			ibRecPag = new IntegerBox();
			ibRecPag.setValue(10);
			ibRecPag.setWidth("30px");
			ibRecPag.setTitle(constants.recPag());
			ibRecPag.addBlurHandler(new BlurHandler() {
				
				@Override
				public void onBlur(BlurEvent event) {
//					if (recPage != ibRecPag.getValue()){
//						page=1;
//						show();
//						recPage = ibRecPag.getValue();
//						
//					}
				}
			});
			ibRecPag.addKeyPressHandler(new KeyPressHandler() {
				
				@Override
				public void onKeyPress(KeyPressEvent event) {
					if (event.getCharCode()==((char)13) ||
							event.getCharCode()==((char)10)){
//						if (recPage != ibRecPag.getValue()){
//							page=1;
//							show();
//							recPage = ibRecPag.getValue();
//						}
					}
				}
			});
		}
		sp = new SimplePanel();
		sp.setWidth("40px");
		sp.add(ibRecPag);
		return sp;
	}

	private SimplePanel getPshbtnInizio() {
		if (pshbtnInizio == null) {
			pshbtnInizio = new PushButton(constants.bInizio());
			pshbtnInizio.setSize("20px", "20px");
			Image image = new Image("./images/navigator/start.ico");
			image.setSize("20px", "20px");
			image.setAltText(constants.bInizio());
			image.setTitle(constants.bInizio());
			pshbtnInizio.getUpFace().setImage(image);
			pshbtnInizio.setStyleName("navigator");
			pshbtnInizio.addClickHandler(new NavigatorClick(NavigatorClick.INIZIO));
		}
		return pButton(pshbtnInizio);
	}

	private SimplePanel getPshbtnIndietro() {
		if (pshbtnIndietro == null) {
			pshbtnIndietro = new PushButton(constants.bIndietro());
			pshbtnIndietro.setSize("20px", "20px");
			Image image = new Image("./images/navigator/left.ico");
			image.setSize("20px", "20px");
			image.setAltText(constants.bIndietro());
			image.setTitle(constants.bIndietro());
			pshbtnIndietro.getUpFace().setImage(image);
			pshbtnIndietro.setStyleName("navigator");
			pshbtnIndietro.addClickHandler(new NavigatorClick(NavigatorClick.INDIETRO));
		}
		return pButton(pshbtnIndietro);
	}

	private SimplePanel getPshbtnAvanti() {
		if (pshbtnAvanti == null) {
			pshbtnAvanti = new PushButton(constants.bAvanti());
			pshbtnAvanti.setSize("20px", "20px");
			Image image = new Image("./images/navigator/right.ico");
			image.setSize("20px", "20px");
			image.setAltText(constants.bAvanti());
			image.setTitle(constants.bAvanti());
			pshbtnAvanti.getUpFace().setImage(image);
			pshbtnAvanti.setStyleName("navigator");
			pshbtnAvanti.addClickHandler(new NavigatorClick(NavigatorClick.AVANTI));
		}
		return pButton(pshbtnAvanti);
	}

	private SimplePanel getPshbtnFine() {
		if (pshbtnFine == null) {
			pshbtnFine = new PushButton(constants.bFine());
			pshbtnFine.setSize("20px", "20px");
			Image image = new Image("./images/navigator/stop.ico");
			image.setSize("20px", "20px");
			image.setAltText(constants.bFine());
			image.setTitle(constants.bFine());
			pshbtnFine.getUpFace().setImage(image);
			pshbtnFine.setStyleName("navigator");
			pshbtnFine.addClickHandler(new NavigatorClick(NavigatorClick.FINE));
		}
		return pButton(pshbtnFine);
	}
	
	private SimplePanel pButton(PushButton button){
		SimplePanel sp = null;
		sp = new SimplePanel();
		sp.setWidth("20px");
		sp.add(button);
		return sp;
	}

	private void show(){
//		int recTot=0;
//		int recStart=0;
		long recStop=0;
		long page = 0;
		long totPage =0;
		NumberFormat nf = null;

		disableNavigator();
		if (rows>0){
			recStop = start+rows;
			if (recStop>numFound){
				recStop = numFound;
			}
			if (numFound>0){
				if (start==0){
					page = 1;
				} else {
					page = (start/rows)+1;
				}
				totPage =numFound/rows;
			}
			nf = NumberFormat.getFormat("#,###");
			lStatus.setText(
					messages.msgPageStatus(
							nf.format((start+1)), 
							nf.format(recStop), 
							nf.format(numFound), 
							nf.format(page), 
							nf.format(totPage), 
							nf.format(qTime)));
			enableNavigator(recStop);
		} else {
			lStatus.setText("");
		}
	}
	
	private void enableNavigator(long recStop){
		if (rows>0){
			pshbtnInizio.setEnabled(true);
			pshbtnIndietro.setEnabled(true);
		}
		if (recStop<numFound){
			pshbtnAvanti.setEnabled(true);
			pshbtnFine.setEnabled(true);
		}
	}

	class NavigatorClick implements ClickHandler{

		public static final String INIZIO = "inizio";
		public static final String INDIETRO = "indietro";
		public static final String AVANTI = "avanti";
		public static final String FINE = "fine";

		private String stato;

		public NavigatorClick(String stato){
			this.stato = stato;
		}

		@Override
		public void onClick(ClickEvent event) {
//			if (stato.equals(INIZIO)) {
//				page=1;
//			} else if (stato.equals(INDIETRO)) {
//				page--;
//			} else if (stato.equals(AVANTI)) {
//				page++;
//			} else if (stato.equals(FINE)) {
//				page=totPage;
//			}
//			show();
		}
		
	}
}

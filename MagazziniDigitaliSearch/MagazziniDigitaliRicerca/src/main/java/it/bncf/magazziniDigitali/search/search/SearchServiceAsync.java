/**
 * 
 */
package it.bncf.magazziniDigitali.search.search;

import java.util.TreeMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author massi
 *
 */
public interface SearchServiceAsync {

	/**
	 * 
	 * @see it.bncf.magazziniDigitali.search.search.SearchService#find(java.lang.String, java.lang.String)
	 */
	void find(String keywords, TreeMap<String, String> queryFacet,
			AsyncCallback<Integer> callback);

	void getStart(AsyncCallback<Integer> callback);

	void setStart(Integer start, AsyncCallback<Void> callback);

	void getFacets(
			AsyncCallback<TreeMap<String, TreeMap<String, Long>>> callback);

	void getResults(AsyncCallback<String> callback);

	void getRows(AsyncCallback<Integer> callback);

	void setRows(Integer start, AsyncCallback<Void> callback);

	void getNavigator(AsyncCallback<TreeMap<String, Object>> callback);

}

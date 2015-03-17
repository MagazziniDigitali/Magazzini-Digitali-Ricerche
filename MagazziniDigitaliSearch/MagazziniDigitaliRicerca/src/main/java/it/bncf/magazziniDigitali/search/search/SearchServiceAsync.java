/**
 * 
 */
package it.bncf.magazziniDigitali.search.search;

import java.util.TreeMap;
import java.util.Vector;

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
	void find(String keywords, TreeMap<String, String[]> queryFacet, Integer start, Integer rows,
			AsyncCallback<Integer> callback);

	void getFacets(
			AsyncCallback<TreeMap<String, Vector<String[]>>> callback);

	void getResults(AsyncCallback<String> callback);

	void getNavigator(AsyncCallback<TreeMap<String, String>> callback);

}

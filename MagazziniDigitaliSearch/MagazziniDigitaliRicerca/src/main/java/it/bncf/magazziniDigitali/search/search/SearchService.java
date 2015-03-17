package it.bncf.magazziniDigitali.search.search;

import java.util.TreeMap;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author massi
 *
 */
@RemoteServiceRelativePath("servlet/search")
public interface SearchService extends RemoteService {

	Integer find(String keywords, TreeMap<String, String[]> queryFacet, Integer start, Integer rows) throws Exception;

	TreeMap<String, Vector<String[]>> getFacets();
	
	String getResults();
	
	TreeMap<String, String> getNavigator();
}

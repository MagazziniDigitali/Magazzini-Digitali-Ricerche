package it.bncf.magazziniDigitali.search.search;

import java.util.TreeMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author massi
 *
 */
@RemoteServiceRelativePath("servlet/search")
public interface SearchService extends RemoteService {

	Integer find(String keywords, TreeMap<String, String> queryFacet) throws Exception;

	Integer getRows();

	void setRows(Integer start);

	Integer getStart();

	void setStart(Integer start);

	TreeMap<String, TreeMap<String, Long>> getFacets();
	
	String getResults();
	
	TreeMap<String, Object> getNavigator();
}

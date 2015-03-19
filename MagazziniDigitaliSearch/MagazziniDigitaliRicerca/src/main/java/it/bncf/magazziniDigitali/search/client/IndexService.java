package it.bncf.magazziniDigitali.search.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author massi
 *
 */
@RemoteServiceRelativePath("servlet/index")
public interface IndexService extends RemoteService {
	
	String getScheda(String id) throws Exception;
}

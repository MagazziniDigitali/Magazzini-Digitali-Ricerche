package it.bncf.magazziniDigitali.search.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IndexServiceAsync {

	void getScheda(String id, AsyncCallback<String> callback) throws Exception;

}

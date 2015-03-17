package it.bncf.magazziniDigitali.search.tools;

import com.google.gwt.i18n.client.Messages;

public interface NavigatorMessages extends Messages {

	String msgPageStatus(String recStart, String recStop, String recTot, String pageAtt, String pageTot, String qTime);
	
	String msgLimitResult(Integer maxRecSearch);

	String msgLimitPage(Integer maxRecPage);
}

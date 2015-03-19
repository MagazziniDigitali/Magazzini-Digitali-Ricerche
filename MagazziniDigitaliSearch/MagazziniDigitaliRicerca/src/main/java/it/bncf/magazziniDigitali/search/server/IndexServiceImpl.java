/**
 * 
 */
package it.bncf.magazziniDigitali.search.server;

import it.bncf.magazziniDigitali.search.business.search.SearchServiceBusiness;
import it.bncf.magazziniDigitali.search.client.IndexService;
import it.bncf.magazziniDigitali.solr.ItemMD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.converter.xsl.ConverterXsl;
import mx.randalf.converter.xsl.exception.ConvertXslException;
import mx.randalf.solr.exception.SolrException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.request.LocalSolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.response.XMLResponseWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author massi
 *
 */
@Service("index")
@Transactional
public class IndexServiceImpl extends RemoteServiceServlet implements
		IndexService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7438946762020036470L;

	/**
	 * 
	 */
	public IndexServiceImpl() {
	}

	/**
	 * @param delegate
	 */
	public IndexServiceImpl(Object delegate) {
		super(delegate);
	}

	@Override
	public String getScheda(String id) throws Exception {
		String query = "*:*";
		SearchServiceBusiness ssb = null;
		QueryResponse response = null;
		ByteArrayOutputStream baos = null;
		String result = null;

		try {
			if (id != null && !id.trim().equals("")){
				ssb = new SearchServiceBusiness();

				query = ItemMD.ID+":"+id;
				response = ssb.find(query, null, 0, 10);
				if (response.getResults() != null){
					
					baos = new ByteArrayOutputStream();
					
					ConverterXsl.convertXsl(Configuration.getValue("web.scheda.xslt"), 
							toXML(ssb.getSolrQuery(), response), baos);
					
					baos.flush();
					
					result =new String(baos.toByteArray());
				}
			}
		} catch (NumberFormatException e) {
			throw new Exception(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new Exception(e.getMessage(), e);
		} catch (SolrException e) {
			throw new Exception(e.getMessage(), e);
		} catch (SolrServerException e) {
			throw new Exception(e.getMessage(), e);
		} catch (ConvertXslException e) {
			throw new Exception(e.getMessage(), e);
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return result;
	}	

	private InputStream toXML(SolrParams request, QueryResponse response) {
	    XMLResponseWriter xmlWriter = new XMLResponseWriter();
	    Writer w = new StringWriter();
	    SolrQueryResponse sResponse = new SolrQueryResponse();
	    ByteArrayInputStream bais = null;

	    sResponse.setAllValues(response.getResponse());
	    try {
	        xmlWriter.write(w, new LocalSolrQueryRequest(null, request), sResponse);
		    w.flush();
	    } catch (IOException e) {
	        throw new RuntimeException("Unable to convert Solr response into XML", e);
	    }
	    
	    bais = new ByteArrayInputStream(w.toString().getBytes());
	    return bais;
	}
}

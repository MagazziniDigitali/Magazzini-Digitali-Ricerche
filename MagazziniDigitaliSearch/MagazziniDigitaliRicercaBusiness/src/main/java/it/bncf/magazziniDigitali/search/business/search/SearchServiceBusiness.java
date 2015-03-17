/**
 * 
 */
package it.bncf.magazziniDigitali.search.business.search;

import java.util.TreeMap;
import java.util.Vector;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.solr.FindDocument;
import mx.randalf.solr.exception.SolrException;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;

/**
 * @author massi
 *
 */
public class SearchServiceBusiness {

	private Logger log = Logger.getLogger(SearchServiceBusiness.class);

	private SolrParams solrQuery = null;

	/**
	 * 
	 */
	public SearchServiceBusiness() {
	}

	@SuppressWarnings("unchecked")
	public QueryResponse find(String query, TreeMap<String, String[]> queryFacet, int start, int rows) throws 
			NumberFormatException,ConfigurationException, SolrException, 
			SolrServerException{
		FindDocument find = null;
		QueryResponse response = null;
		Vector<String> vFacet = null;

		try {
			vFacet = (Vector<String>) Configuration.getValues("web.search.solr.facet");
			
			find = new FindDocument(Configuration.getValue("demoni.Publish.solr.URL"),
					Boolean.parseBoolean(Configuration
							.getValue("demoni.Publish.solr.Cloud")),
					Configuration
							.getValue("demoni.Publish.solr.collection"),
					Integer.parseInt(Configuration
							.getValue("demoni.Publish.solr.connectionTimeOut")),
					Integer.parseInt(Configuration
							.getValue("demoni.Publish.solr.clientTimeOut")));
			find.enableFacet(
					Integer.parseInt(Configuration.getValue("web.search.solr.facetMinCount")), 
					Integer.parseInt(Configuration.getValue("web.search.solr.facetLimit")), 
					vFacet.toArray(new String[vFacet.size()]), queryFacet);

			if (query ==  null){
				query = "*:*";
			}
			response = find.find(query, 
					start, 
					rows);
			
			solrQuery = find.getSolrQuery();
		} catch (NumberFormatException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (ConfigurationException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (SolrException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (SolrServerException e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			if (find != null){
				find.close();
			}
		}
		
		
		
		return response;
	}

	/**
	 * @return the solrParams
	 */
	public SolrParams getSolrQuery() {
		return solrQuery;
	}
}

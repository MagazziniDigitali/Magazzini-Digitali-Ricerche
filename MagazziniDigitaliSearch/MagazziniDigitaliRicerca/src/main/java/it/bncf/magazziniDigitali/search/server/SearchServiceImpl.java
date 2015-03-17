/**
 * 
 */
package it.bncf.magazziniDigitali.search.server;

import it.bncf.magazziniDigitali.search.business.search.SearchServiceBusiness;
import it.bncf.magazziniDigitali.search.search.SearchService;
import it.bncf.magazziniDigitali.solr.ItemMD;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import mx.randalf.configuration.Configuration;
import mx.randalf.configuration.exception.ConfigurationException;
import mx.randalf.converter.xsl.ConverterXsl;
import mx.randalf.converter.xsl.exception.ConvertXslException;
import mx.randalf.solr.exception.SolrException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.PivotField;
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
@Service("search")
@Transactional
public class SearchServiceImpl extends RemoteServiceServlet implements
		SearchService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1382983111844153860L;

	private int start = 0;

	private int rows = 0;

	private QueryResponse response = null;

	private SolrParams solrQuery = null;

	/**
	 * 
	 */
	public SearchServiceImpl() {
		readConf();
	}

	/**
	 * @param delegate
	 */
	public SearchServiceImpl(Object delegate) {
		super(delegate);
		readConf();
	}

	private void readConf(){
		
		try {
			rows = Integer.parseInt(Configuration.getValue("web.search.solr.rows"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @see it.bncf.magazziniDigitali.search.search.SearchService#find(java.lang.String, java.lang.String)
	 */
	@Override
	public Integer find(String keywords, TreeMap<String, String[]> queryFacet, Integer start, Integer rows)
			throws Exception 
		{
		SearchServiceBusiness ssb = null;
		String query = "*:*";
		Integer result = -1;
		
		try {
			ssb = new SearchServiceBusiness();
			
			if (keywords!= null && !keywords.trim().equals("")){
				query = ItemMD.KEYWORDS+":"+keywords;
			}
			if (start != null){
				this.start = start;
			}
			if (rows != null){
				this.rows = rows;
			}
			response = ssb.find(query, queryFacet, this.start, this.rows);
			solrQuery = ssb.getSolrQuery();
			result = response.getStatus();
		} catch (NumberFormatException e) {
			throw new Exception(e.getMessage(), e);
		} catch (ConfigurationException e) {
			throw new Exception(e.getMessage(), e);
		} catch (SolrException e) {
			throw new Exception(e.getMessage(), e);
		} catch (SolrServerException e) {
			throw new Exception(e.getMessage(), e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeMap<String, Vector<String[]>> getFacets() {
		TreeMap<String, Vector<String[]>> result = null;
		Vector<String[]> field = null;
		FacetField facetField = null;
		String[] value = null;
		Vector<String> vFacet = null;
		
		result = new TreeMap<String, Vector<String[]>>();
		if (response.getFacetQuery()!= null){
			if (response.getFacetDates()!= null && !response.getFacetDates().isEmpty()){
				System.out.println("FacetDates");
				for (int x=0; x<response.getFacetDates().size(); x++){
					System.out.println("\tName:"+response.getFacetDates().get(x).getName());
					for (int y=0; y<response.getFacetDates().get(x).getValueCount(); y++){
						System.out.print("\t\tAsFilterQuery:"+response.getFacetDates().get(x).getValues().get(y).getAsFilterQuery());
						System.out.print("\tCount:"+response.getFacetDates().get(x).getValues().get(y).getCount());
						System.out.print("\tName:"+response.getFacetDates().get(x).getValues().get(y).getName());
						System.out.println("\tFacetField:"+response.getFacetDates().get(x).getValues().get(y).getFacetField());
					}
				}
			}
			if (response.getFacetFields()!= null && !response.getFacetFields().isEmpty()){
				try {
					vFacet = (Vector<String>) Configuration.getValues("web.search.solr.facet");
				} catch (ConfigurationException e) {
					e.printStackTrace();
				}

				for (int x=0; x<vFacet.size(); x++){
					facetField = response.getFacetField(vFacet.get(x));
//				for (int x=0; x<response.getFacetFields().size(); x++){
					field = new Vector<String[]>();
//					System.out.println("response.getFacetFields: "+response.getFacetFields().get(x).getName());
					for (int y=0; y<facetField.getValueCount(); y++){
//					for (int y=0; y<response.getFacetFields().get(x).getValueCount(); y++){
						if (facetField.getValues().get(y).getCount()>0){
//						if (response.getFacetFields().get(x).getValues().get(y).getCount()>0){
//							System.out.println("\t"+response.getFacetFields().get(x).getValues().get(y).getName()+" : "+response.getFacetFields().get(x).getValues().get(y).getCount());
							value = new String[2];
							value[0] = facetField.getValues().get(y).getName(); 
							value[1] = ""+facetField.getValues().get(y).getCount(); 
//							value[0] = response.getFacetFields().get(x).getValues().get(y).getName(); 
//							value[1] = ""+response.getFacetFields().get(x).getValues().get(y).getCount(); 
							field.add(value);
						}
					}
					if (field.size()>0){
						result.put(x+"\t"+facetField.getName(), field);
//						result.put(response.getFacetFields().get(x).getName(), field);
					}
				}
			}
			if (response.getFacetPivot()!= null && response.getFacetPivot().size()>0){
				System.out.println("FacetPivot");
				for (int x=0; x<response.getFacetPivot().size(); x++){
					String name = response.getFacetPivot().getName(x);
					System.out.println("\tName:"+name);
					System.out.println("\tBooleanArg:"+response.getFacetPivot().getBooleanArg(name).booleanValue());
					List<PivotField> list =response.getFacetPivot().get(name);
					for (int y=0; y<list.size(); y++){
						System.out.print("\t\tCount:"+list.get(y).getCount());
						System.out.print("\tField:"+list.get(y).getField());
						System.out.print("\tFieldStatsInfo:"+list.get(y).getFieldStatsInfo());
						System.out.print("\tPivot:"+list.get(y).getPivot());
						System.out.println("\tValue:"+list.get(y).getValue());
					}
				}
			}
			if (response.getFacetRanges()!= null && !response.getFacetRanges().isEmpty()){
				System.out.println("FacetRanges");
				for (int x=0; x<response.getFacetRanges().size(); x++){
					System.out.println("\tName:"+response.getFacetRanges().get(x).getName());
					System.out.print("\t\tAfter:"+response.getFacetRanges().get(x).getAfter());
					System.out.print("\tBefore:"+response.getFacetRanges().get(x).getBefore());
					System.out.print("\tBetween:"+response.getFacetRanges().get(x).getBetween());
					System.out.print("\tCounts:"+response.getFacetRanges().get(x).getCounts());
					System.out.print("\tGap:"+response.getFacetRanges().get(x).getGap());
					System.out.print("\tEnd:"+response.getFacetRanges().get(x).getEnd());
					System.out.print("\tStart:"+response.getFacetRanges().get(x).getStart());
				}
			}
		}
		return result;
	}

	@Override
	public String getResults() {
		ByteArrayOutputStream baos = null;
		String result = null;

		try {
			if (response.getResults() != null){
				
				baos = new ByteArrayOutputStream();
				
				ConverterXsl.convertXsl(Configuration.getValue("web.search.result.xslt"), 
						toXML(solrQuery, response), baos);
				
				baos.flush();
				
				result =new String(baos.toByteArray());
			}
		} catch (ConvertXslException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

	@Override
	public TreeMap<String, String> getNavigator() {
		TreeMap<String, String> result = null;

		result = new TreeMap<String, String>();
		
		result.put("rows", ""+rows);
		result.put("qTime", ""+response.getQTime());

		if (response.getResults() != null){
			result.put("start", ""+response.getResults().getStart());
			result.put("numFound", ""+response.getResults().getNumFound());
		}
		return result;
	}	
}

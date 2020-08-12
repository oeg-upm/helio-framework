package helio.framework.objects;



public enum SparqlResultsFormat {
	
	// -- RDF formats
	RDF_TURTLE ("TURTLE"),
	RDF_TTL ("TTL"),
	RDF_XML ("RDF/XML"),
	RDF_N3 ("N3"),
	N_TRIPLES ("N-TRIPLES"),
	N_TRIPLE ("N-TRIPLE"),
	RDF_NT ("NT"),
	JSON_LD ("JSON-LD"), 
	THRIFT ("THRIFT"),
	// -- NON RDF
	SSE ("SSE"),
	XML ("XML"),
	JSON ("JSON"),
	CSV ("CSV"),
	TSV ("TSV"),
	TUPLES ("TUPLES"),
	TRIG ("TRIG"),
	BIO ("BIO"),
	TEXT ("TEXT"),
	COUNT ("COUNT"),
	HTML ("HTML");
	
 	private String format;
	SparqlResultsFormat(String format){
		this.format = format;
	}
	
	public String getFormat() {
		return format;
	}
	
	

	
}

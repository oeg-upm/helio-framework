package helio.framework.objects;

import java.util.List;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

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
	
	private static final String HTML_PREAMBLE = "<!DOCTYPE html>\n" + 
			"<html lang=\"en\">\n" + 
			"  <head>\n" + 
			"    <meta charset=\"utf-8\">\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + 
			"    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\n" + 
			"    <title>Helio Gateway</title>\n" + 
			"\n" + 
			"    <!-- Bootstrap Latest compiled and minified CSS -->\n" + 
			"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n" + 
			"	<!-- Optional theme -->\n" + 
			"	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css\" integrity=\"sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp\" crossorigin=\"anonymous\">\n" + 
			"\n" + 
			"    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->\n" + 
			"    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->\n" + 
			"    <!--[if lt IE 9]>\n" + 
			"      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" + 
			"      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n" + 
			"    <![endif]-->\n" + 
			"  </head>\n" + 
			"  <body>\n" + 
			"    <table class=\"table\">\n";
	
	private static final String HTML_POSTAMBLE = "</table>\n" + 
			"\n" + 
			"    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->\n" + 
			"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>\n" + 
			"    <!-- Include all compiled plugins (below), or include individual files as needed -->\n" + 
			"    <script src=\"js/bootstrap.min.js\"></script>\n" + 
			"	<!-- Latest compiled and minified JavaScript -->\n" + 
			"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\" integrity=\"sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa\" crossorigin=\"anonymous\"></script>\n" + 
			"  </body>\n" + 
			"</html>";
	
 	private String format;
	SparqlResultsFormat(String format){
		this.format = format;
	}
	
	public String getFormat() {
		return format;
	}
	
	
	// -- Auxiliary methods for format changing
	
	
	public static String fromRSToHTML(ResultSet results) {
		// 0. Initialize variables
		StringBuilder result = new StringBuilder(HTML_PREAMBLE);
		
		List<String> variables = results.getResultVars();
		int variablesSize = variables.size();
		// 1. Prepare table header
		String tableHeader = fillHTMLTableHeader(variables, variablesSize);
		result.append(tableHeader);
		// 2. Prepare table body
		String tableBody = fillHTMLTableBody(results, variables, variablesSize);
		result.append(tableBody);
		
		result.append(HTML_POSTAMBLE);
		return result.toString();
	}

	private static String fillHTMLTableHeader(List<String> variables, int variablesSize) {
		StringBuilder htmlHeader = new StringBuilder("<thead>\n\t<tr>");
		String variable = null;
		// 1. Pour table header
		for (int index = 0; index < variablesSize; index++) {
			variable = variables.get(index);
			htmlHeader.append("<th scope=\"col\">");
			htmlHeader.append(variable.trim());
			htmlHeader.append("</th>");
		}
		htmlHeader.append("\t</tr>\n</thead>");
		return htmlHeader.toString();
	}

	private static String fillHTMLTableBody(ResultSet results, List<String> variables, int variablesSize) {
		StringBuilder result = new StringBuilder("<tbody>");
		String variable = null;
		String variableSolution = null;
		QuerySolution solution = null;
		// 2. Pour table content
		while (results.hasNext()) {
			StringBuilder partialResult = new StringBuilder("<tr>\n\t");
			solution = results.next();
			for (int index = 0; index < variablesSize; index++) {
				variable = variables.get(index);
				variableSolution = solution.get(variable).toString();
				partialResult.append("<td>").append(variableSolution).append("</td>");
			}
			partialResult.append("</tr>");
			result.append("\n").append(partialResult);
		}
		result.append("</tbody>");
		return result.toString();
	}
	
}

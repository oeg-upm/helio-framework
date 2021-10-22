package helio.framework.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import helio.framework.materialiser.mappings.DataHandler;
import helio.framework.objects.Utils;


public class Expressions {

	private static Logger logger = LogManager.getLogger(Expressions.class);
	
	private Expressions() {
		super();
	}
	
	/**
	 * This method instantiate creates an {@link EvaluableExpression} from a String.
	 * @param expressionStr the {@link String} representation of a n{@link EvaluableExpression}
	 * @return an {@link EvaluableExpression} 
	 */
	public static EvaluableExpression create(String expressionStr) throws EvaluableExpressionException {
		if(expressionStr==null || expressionStr.isEmpty())
			throw new EvaluableExpressionException(Utils.buildMessage("The provided expression is null or empty"));
		return new EvaluableExpression(expressionStr);
	}
	
	/**
	 * This method instantiate uniquely the data references contained in a {@link String} that is an evaluable expression.
	 * @param expression the {@link EvaluableExpression} to be  instantiated
	 * @param referencesValues a {@link Map} containing as keys the data references and as values the fetched data
	 * @return an {@link EvaluableExpression} already initialized
	 * @throws EvaluableExpressionException is thrown if an error occurs when the expression is parsed
	 */
	public static EvaluableExpression instantiateDataReferences(String expressionStr, Map<String,String> referencesValues) throws EvaluableExpressionException {
		return Expressions.create(Expressions.create(expressionStr).instantiateExpression(referencesValues));
	}
	
	/**
	 * This method instantiate uniquely the data references contained in an {@link EvaluableExpression}.
	 * @param expression the {@link EvaluableExpression} to be  instantiated
	 * @param referencesValues a {@link Map} containing as keys the data references and as values the fetched data
	 * @return an {@link EvaluableExpression} already initialized
	 * @throws EvaluableExpressionException is thrown if an error occurs when the expression is parsed
	 */
	public static EvaluableExpression instantiateDataReferences(EvaluableExpression expression, Map<String,String> referencesValues) throws EvaluableExpressionException {
		return Expressions.create(Expressions.create(expression.getExpression()).instantiateExpression(referencesValues));
	}
	
	/**
	 * This method instantiates an {@link EvaluableExpression} from a fragment of raw data transparently using a {@link DataHandler}. Notice that this method not only instantiates the data references, but will also evaluate the expression if it has function references.
	 * @param expression the {@link EvaluableExpression} to be  instantiated
	 * @param dataChunk the fragment of raw data
	 * @param handler a {@link DataHandler} compatible with the format of the fragment that will be used to extract the data
	 * @param evaluator a {@link Evaluator} to evaluate the function references that the  {@link EvaluableExpression} may have
	 * @return a {@link List} containing the result of instantiating the {@link EvaluableExpression}. Notice that the result could be more than one, this is due to the fact that a function could return different results, e.g., split.
	 * @throws EvaluableExpressionException is thrown when an error occurs if the expression is initialized with the data values retrieved from the raw fragment, i.e., the data chunk.
	 */
	public static List<String> instantiate(EvaluableExpression expression, String dataChunk, DataHandler handler, ExpressionsEvaluator evaluator) throws EvaluableExpressionException {
		List<String> instantiatedEEs = new ArrayList<>();
		if(expression==null)
			return instantiatedEEs;
		
		if (expression.hasDataReferences()) { // data reference case
			Map<String,String> dataReferences = fetchDataReferences( expression.getDataReferences(), dataChunk, handler);
			expression = Expressions.instantiateDataReferences(expression, dataReferences);
		}// if the conditional is skipped we are in a constant case, no data references
		
		if(expression.hasFunctionReferences()) {
			instantiatedEEs.addAll(evaluator.evaluateExpresions(expression));
		}else {
			instantiatedEEs.add(expression.getExpression());
		}
		
		return instantiatedEEs;
	}
	
	/**
	 * This method retrieves the values of the dataReferences provided from a chunk of data using a {@link DataHandler}
	 * @param dataReferences the set of data references which values must be retrieved
	 * @param dataChunk a chunk of data from which the values must be retrieved
	 * @param handler a {@link DataHandler} compatible with the format of the fragment that will be used to extract the data
	 * @return a {@link Map} containing as keys the data references and as values the fetched data
	 */
	public static Map<String,String> fetchDataReferences(List<String> dataReferences, String dataChunk, DataHandler handler){
		Map<String,String> dataReferencesSolvedList = new HashMap<>();
		for(int index=0;index < dataReferences.size(); index++) {
			String reference = dataReferences.get(index);
			List<String> setFilteredData = handler.filter(reference, dataChunk);
			for(int counter=0; counter<setFilteredData.size(); counter++) {
				String filteredData = setFilteredData.get(counter);
				if(filteredData==null) {
					logger.warn("The reference '"+reference+"' that was provided has no data in the fetched document "+dataChunk);
				}else {
					dataReferencesSolvedList.put(reference, filteredData);
				}
			}
		}
		return dataReferencesSolvedList;
	}

	
	
}

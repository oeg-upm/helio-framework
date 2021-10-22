package helio.framework.expressions;

import java.util.List;

/**
 * Evaluator allows to evaluate expressions, a composite of functions that can return a {@link List} of values as result or a {@link Boolean} value (a predicate expression).
 * 
 * @author Andrea Cimmino
 *
 */
public interface ExpressionsEvaluator {


	/**
	 * This method evaluates an expression that returns a {@link String} as output
	 * @param expression A {@link String} expression
	 * @return The {@link List} of values obtained as result of evaluating the input expression
	 */
	List<String> evaluateExpresions(EvaluableExpression expression);

	/**
	 * This method evaluates a predicate expression that returns a {@link Boolean} as output
	 * @param predicate A {@link Boolean} predicate expression
	 * @return The {@link Boolean} value obtained as result of evaluating the input predicate expression
	 */
	Boolean evaluatePredicate(String predicate);

}

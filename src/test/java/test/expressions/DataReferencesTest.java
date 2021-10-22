package test.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import helio.framework.expressions.EvaluableExpression;
import helio.framework.expressions.EvaluableExpressionException;

public class DataReferencesTest {

	
	
	/**
	 * Check that an {@link EvaluableExpression} with one data reference is correctly initialized
	 * @throws EvaluableExpressionException if a data reference has not a related value
	 */
	@Test
	public void Test01() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		String expresionExpected = "http://distrito.example.com/district/Arganzuela";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		String expression = "http://distrito.example.com/district/{distrito}";
		EvaluableExpression ee = new EvaluableExpression(expression);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(ee.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(ee.instantiateExpression(values)));
		
	}
	
	@Test
	public void Test02() throws EvaluableExpressionException {
		Map<String,String> maps = new HashMap<>();
		maps.put("$.name", "Andrea");
		EvaluableExpression expression = new EvaluableExpression("The text is {$.name}"); 
		String evaluatedExpression = expression.instantiateExpression(maps);
		
		Assert.assertTrue(evaluatedExpression.equals("The text is Andrea"));
	}
	
	
	/**
	 * Check that an {@link EvaluableExpression} with more than one data reference is correctly initialized
	 * @throws EvaluableExpressionException if a data reference has not a related value
	 */
	@Test
	public void Test03() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		dataReferencesExpected.add("no-distrinto");
		dataReferencesExpected.add("something");
		String expresionExpected = "http://distrito.example.com/district/[REGEX(CONCAT('Arganzuela','example'), '\\{ || \\}', '')]/something";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("no-distrinto", "example");
		values.put("something", "something");
		String expression = "http://distrito.example.com/district/[REGEX(CONCAT({distrito},{no-distrinto}), '\\{ || \\}', '')]/{something}";
		
		EvaluableExpression ee = new EvaluableExpression(expression);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(ee.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(ee.instantiateExpression(values)));
	}
	
	@Test
	public void Test04() throws EvaluableExpressionException {
		Map<String,String> maps = new HashMap<>();
		maps.put("$.name", "Andrea");
		maps.put("$.surname", "Cimmino");
		EvaluableExpression expression = new EvaluableExpression("The text is {$.name}, {$.surname} "); 
		String evaluatedExpression = expression.instantiateExpression(maps);
		Assert.assertTrue(evaluatedExpression.equals("The text is Andrea, Cimmino "));
	}
	

	@Test
	public void Test05() throws EvaluableExpressionException {
		Map<String,String> maps = new HashMap<>();
		maps.put("$.name", "Andrea");
		maps.put("$.surname", "Cimmino");
		maps.put("$.adr", "Madrid");
		EvaluableExpression expression = new EvaluableExpression("The text is {$.name}, {$.surname} - Address: {$.adr} "); 
		String evaluatedExpression = expression.instantiateExpression(maps);
		Assert.assertTrue(evaluatedExpression.equals("The text is Andrea, Cimmino - Address: Madrid "));
	}
	
	/**
	 * Check that an {@link EvaluableExpression} throws the exception if one reference has not a value
	 * @throws EvaluableExpressionException if a data reference has not a related value
	 */
	@Test
	public void Test06() throws EvaluableExpressionException {
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		String expression = "http://distrito.example.com/district/[REGEX(CONCAT({distrito},{no-distrinto}), '\\{', '')]/{something}";
		EvaluableExpression ee = new EvaluableExpression(expression);
		Assert.assertTrue(ee.getDataReferences().containsAll(values.keySet()));
		String instantiatedExpression = null;
		try{
			instantiatedExpression = ee.instantiateExpression(values);
		} catch (Exception e) {
			// pass
		}
		Assert.assertTrue(instantiatedExpression==null);
	}
	

	/**
	 * Check that an {@link EvaluableExpression} with that has an opening '{' alone is parsed correctly
	 * @throws EvaluableExpressionException if a data reference has not a related value
	 */
	@Test
	public void Test07() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		dataReferencesExpected.add("no-distrinto");
		dataReferencesExpected.add("something");
		String expresionExpected = "http://distrito.example.com/district/[REGEX(CONCAT('Arganzuela','example'), '\\{', '')]/something";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("no-distrinto", "example");
		values.put("something", "something");
		String expression = "http://distrito.example.com/district/[REGEX(CONCAT({distrito},{no-distrinto}), '\\{', '')]/{something}";
		
		EvaluableExpression ee = new EvaluableExpression(expression);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(ee.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(ee.instantiateExpression(values)));
	}
	
	@Test
	public void Test08() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		dataReferencesExpected.add("no-distrinto");
		dataReferencesExpected.add("something");
		String expresionExpected = "http://distrito.example.com/district/[REGEX(CONCAT('Arganzuela','example'), '{', '')]/something";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("no-distrinto", "example");
		values.put("something", "something");
		String expression = "http://distrito.example.com/district/[REGEX(CONCAT({distrito},{no-distrinto}), '{', '')]/{something}";
		
		EvaluableExpression ee = new EvaluableExpression(expression);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(ee.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(ee.instantiateExpression(values)));
	}
	
	@Test
	public void Test09() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		dataReferencesExpected.add("no-distrinto");
		dataReferencesExpected.add("something");
		String expresionExpected = "http://distrito.example.com/district/[REGEX(CONCAT('Arganzuela','example'), '\\}', '')]/something";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("no-distrinto", "example");
		values.put("something", "something");
		String expression = "http://distrito.example.com/district/[REGEX(CONCAT({distrito},{no-distrinto}), '\\}', '')]/{something}";
		
		EvaluableExpression ee = new EvaluableExpression(expression);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(ee.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(ee.instantiateExpression(values)));
	}
	
	@Test
	public void Test10() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		dataReferencesExpected.add("no-distrinto");
		dataReferencesExpected.add("something");
		String expresionExpected = "http://distrito.example.com/district/[REGEX(CONCAT('Arganzuela','example'), '}', '')]/something";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("no-distrinto", "example");
		values.put("something", "something");
		String expression = "http://distrito.example.com/district/[REGEX(CONCAT({distrito},{no-distrinto}), '}', '')]/{something}";
		
		EvaluableExpression ee = new EvaluableExpression(expression);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(ee.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(ee.instantiateExpression(values)));
	}
	
	
}

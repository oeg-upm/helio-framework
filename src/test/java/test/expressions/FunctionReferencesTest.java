package test.expressions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import helio.framework.expressions.EvaluableExpression;
import helio.framework.expressions.EvaluableExpressionException;
import helio.framework.expressions.Expressions;

public class FunctionReferencesTest {

	@Test
	public void Test01() throws EvaluableExpressionException {
		List<String> dataReferencesExpected = new ArrayList<>();
		dataReferencesExpected.add("distrito");
		String expresionExpected = "http://distrito.example.com/district/Arganzuela";
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		String expressionStr = "http://distrito.example.com/district/{distrito}";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		
		Assert.assertTrue(dataReferencesExpected.containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));
		Assert.assertTrue(expression.getFunctionReferences().isEmpty());
	}
	

	@Test
	public void Test02() throws EvaluableExpressionException {
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));

		expression = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());

	}
	
	@Test
	public void Test03() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/Distrito";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Distrito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/{sample}";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));

		expression = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());

	}
	
	@Test
	public void Test04() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[REPLACE('Distrito', '\\S+','')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("REPLACE('Distrito', '\\S+','')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Distrito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[REPLACE({sample}, '\\S+','')]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));

		expression = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());

	}
	
	/**
	 * This method tests that an {@link EvaluableExpression} can use the characters '[' and ']' inside the functions
	 * @throws EvaluableExpressionException
	 */
	@Test
	public void Test05() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[REPLACE('Distrito', '\\[^\\s\\]+','')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("REPLACE('Distrito', '\\[^\\s\\]+','')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Distrito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[REPLACE({sample}, '\\[^\\s\\]+','')]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));

		expression = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());
	}
	

	
	
	@Test
	public void Test06() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[LOCATE('Dist[]rito'), '[')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("LOCATE('Dist[]rito'), '[')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Dist[]rito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[LOCATE({sample}), '[')]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));
		EvaluableExpression expressionEv = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expressionEv.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expressionEv.getFunctionReferences().size());
	}
	
	@Test
	public void Test07() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[LOCATE('Dist[]rito'), ']')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("LOCATE('Dist[]rito'), ']')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Dist[]rito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[LOCATE({sample}), ']')]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));
		expression = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());

	}
	
	@Test
	public void Test08() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[REPEAT('Dist[]rito', 10)]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("REPEAT('Dist[]rito', 10)");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Dist[]rito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[REPEAT({sample}, 10)]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));
		expression = new EvaluableExpression(expression.instantiateExpression(values));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());

	}
	
	@Test
	public void Test09() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[REGEX('Dist[]rito', '\\'\\[^\\'\\]+\\'', '')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("REGEX('Dist[]rito', '\\'\\[^\\'\\]+\\'', '')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Dist[]rito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[REGEX({sample}, '\\'\\[^\\'\\]+\\'', '')]";
		
		EvaluableExpression expression = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression.getDataReferences()));	
		Assert.assertTrue(expresionExpected.equals(expression.instantiateExpression(values)));
		expression = new EvaluableExpression(expression.instantiateExpression(values));
		
		Assert.assertTrue(functionReferencesExpected.containsAll(expression.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression.getFunctionReferences().size());

	}
	
	/**
	 * Testing the compacted method to create the {@link EvaluableExpressions}
	 * @throws EvaluableExpressionException
	 */
	@Test
	public void Test10() throws EvaluableExpressionException {
		
		String expresionExpected = "http://distrito.example.com/district/[TRIM('Arganzuela')]/[REGEX('Dist[]rito', '\\'\\[^\\'\\]+\\'', '')]";
		List<String> functionReferencesExpected = new ArrayList<>();
		functionReferencesExpected.add("TRIM('Arganzuela')");
		functionReferencesExpected.add("REGEX('Dist[]rito', '\\'\\[^\\'\\]+\\'', '')");
		
		Map<String,String> values = new HashMap<>();
		values.put("distrito", "Arganzuela");
		values.put("sample", "Dist[]rito");
		String expressionStr = "http://distrito.example.com/district/[TRIM({distrito})]/[REGEX({sample}, '\\'\\[^\\'\\]+\\'', '')]";
		
		EvaluableExpression expression1 = new EvaluableExpression(expressionStr);
		Assert.assertTrue(values.keySet().containsAll(expression1.getDataReferences()));	
		Assert.assertTrue(expresionExpected.equals(expression1.instantiateExpression(values)));
		expression1 = new EvaluableExpression(expression1.instantiateExpression(values));
		
		Assert.assertTrue(functionReferencesExpected.containsAll(expression1.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression1.getFunctionReferences().size());
		
		EvaluableExpression expression2 = Expressions.instantiateDataReferences(expressionStr, values);
		
		Assert.assertTrue(expresionExpected.equals(expression2.getExpression()));
		Assert.assertTrue(functionReferencesExpected.containsAll(expression2.getFunctionReferences()));
		Assert.assertTrue(functionReferencesExpected.size()==expression2.getFunctionReferences().size());
		Assert.assertTrue(expression2.equals(expression1));
	}
	
}

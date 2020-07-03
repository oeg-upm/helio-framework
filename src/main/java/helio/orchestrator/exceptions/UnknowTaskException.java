package helio.orchestrator.exceptions;

public class UnknowTaskException extends Exception{

	private static final long serialVersionUID = -5273900671557139462L;

	public UnknowTaskException(String error) { 
		super(error); 
	}
	
	public static String formatErrorMessage(String clazz, String task, String method) {
		StringBuilder message = new StringBuilder();
		message.append("The task '").append(task).append("' requested in the class ").append(clazz).append(" is unknown, check method ").append(method);
		return message.toString();
	}

}

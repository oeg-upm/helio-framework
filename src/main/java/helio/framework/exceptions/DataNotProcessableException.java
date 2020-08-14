package helio.framework.exceptions;

public class DataNotProcessableException extends Exception{

	private static final long serialVersionUID = -5273900671557139462L;

	public DataNotProcessableException(String conectorClass, String datasourceClass) { 
		super("Data from connector not processable using "+conectorClass+" in datasource "+datasourceClass); 
	}

}

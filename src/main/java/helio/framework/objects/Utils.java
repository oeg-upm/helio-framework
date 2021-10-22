package helio.framework.objects;

public class Utils {

	
	public static String buildMessage(String ... values) {
		StringBuilder message = new StringBuilder();
		for(int index=0; index < values.length; index++) {
			message.append(values[index]);
		}
		return message.toString();
	}
}

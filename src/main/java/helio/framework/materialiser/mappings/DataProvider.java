package helio.framework.materialiser.mappings;

import java.io.InputStream;
import java.io.Serializable;

import com.google.gson.JsonObject;

public interface DataProvider extends Serializable {

	public InputStream getData();
	public void configure(JsonObject configuration);
}

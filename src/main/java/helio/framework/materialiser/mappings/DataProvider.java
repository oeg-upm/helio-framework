package helio.framework.materialiser.mappings;

import java.io.InputStream;
import java.io.Serializable;

public interface DataProvider extends Serializable {

	public InputStream getData();
}

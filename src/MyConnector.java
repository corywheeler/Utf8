import java.net.MalformedURLException;

import com.versionone.apiclient.V1Connector;
import com.versionone.apiclient.exceptions.V1Exception;

public class MyConnector extends V1Connector {
	public MyConnector(String url) throws MalformedURLException, V1Exception {
		super(url);
	}
}
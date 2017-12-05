import java.net.MalformedURLException;

import com.versionone.Oid;
import com.versionone.apiclient.Asset;
import com.versionone.apiclient.Query;
import com.versionone.apiclient.Services;
import com.versionone.apiclient.V1Connector;
import com.versionone.apiclient.exceptions.V1Exception;
import com.versionone.apiclient.interfaces.IAssetType;
import com.versionone.apiclient.interfaces.IAttributeDefinition;
import com.versionone.apiclient.interfaces.IServices;
import com.versionone.apiclient.services.QueryResult;

public class Run {

	public static void main(String[] args) throws MalformedURLException, V1Exception {
		
		System.out.println("Up and running");
		
		V1Connector connector = V1Connector
				    .withInstanceUrl("http://localhost/VersionOne.Web")
				    .withUserAgentHeader("AppName", "1.0")
				    .withUsernameAndPassword("admin", "admin")
				    .build();
		
		IServices services = new Services(connector);

		 IAssetType assetType = services.getAssetType("Story");
		 Oid storyId = services.getOid("Story:2011");
		 Query query = new Query(storyId);
		 IAttributeDefinition descriptionAttribute = assetType.getAttributeDefinition("Description");
		 query.getSelection().add(descriptionAttribute);
		 
		 QueryResult result = services.retrieve(query);
		 String story1Description = (String) result.getAssets()[0].getAttribute(descriptionAttribute).getValue();
		 System.out.println(story1Description);
		
		 
		 Oid updateStoryId = services.getOid("Story:2012");
		 query = new Query(updateStoryId);
		 query.getSelection().add(descriptionAttribute);
		 
		 QueryResult resultForStoryToUpdate = services.retrieve(query);
		 System.out.println(resultForStoryToUpdate.getAssets()[0].getAttribute(descriptionAttribute).getValue());
		 
		 Asset retrievedUpdateStory = resultForStoryToUpdate.getAssets()[0];
		 retrievedUpdateStory.setAttributeValue(descriptionAttribute, story1Description);
		 services.save(retrievedUpdateStory);


	}
}
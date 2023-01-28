package PlaceHolderAPI;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class PlaceHolderAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			getApi();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
	public static void getApi() throws URISyntaxException, ClientProtocolException, IOException, APINOTFOUNDEXCEPTION {
		URIBuilder url = new URIBuilder("https://jsonplaceholder.typicode.com/albums");
		HttpGet getdata = new HttpGet(url.build());
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = httpclient.execute(getdata);
		if(response.getStatusLine().getStatusCode()==200) {
			HttpEntity enti = response.getEntity();
			String s = EntityUtils.toString(enti);
			JSONArray jsonarray = new JSONArray(s);
			int n = jsonarray.length();
			System.out.printf("USERID \t ID \t TITLE \n");
			for(int i=0;i<n;i++) {
				JSONObject tempobj = jsonarray.getJSONObject(i);
				System.out.printf("%s \t %s \t %s \n",tempobj.get("userId"),tempobj.get("id"),tempobj.get("title"));
			}
		}else {
			throw new APINOTFOUNDEXCEPTION("NOT FOUND");
		}
	}
}

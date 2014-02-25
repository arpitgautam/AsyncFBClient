package org.async.fbclient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.async.fbclient.beans.user.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class UserBeanSerializationTest {

	private static CompletionNotifier classUT;
	private static String jsonText;
	private static User userBean;
	private static JSONObject expectedJson;

	@Before
	public void setup() {
		classUT = new CompletionNotifier();
	}

	@Test
	public void testUserBean() throws JSONException, UnsupportedEncodingException, URISyntaxException, IOException{
		jsonBeanTester("UserData.txt");
	}

	private void jsonBeanTester(String fileName) throws URISyntaxException,
			UnsupportedEncodingException, IOException, JSONException {
		readResource(fileName);
		parseJson();
		Gson gson = CustomGsonBuilder.create();
		JSONObject actualJson = new JSONObject( gson.toJson(userBean));
		assertThat(actualJson.toString(),equalTo(expectedJson.toString()));
	}
	
	private static void parseJson() throws JSONException {
		userBean = classUT.<User> deserialize(jsonText, User.class);
		expectedJson = new JSONObject(jsonText);
	}

	private static void readResource(String fileName)
			throws URISyntaxException, UnsupportedEncodingException,
			IOException {
		java.net.URL url = CompletionNotifierTest.class.getResource(fileName);
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		jsonText = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
	}

}

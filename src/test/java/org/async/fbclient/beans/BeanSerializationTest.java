package org.async.fbclient.beans;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import org.async.fbclient.CompletionNotifier;
import org.async.fbclient.CompletionNotifierTest;
import org.async.fbclient.CustomGsonBuilder;
import org.async.fbclient.beans.friends.Friends;
import org.async.fbclient.beans.user.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.gson.Gson;

@RunWith(Parameterized.class)
public class BeanSerializationTest {

	private CompletionNotifier classUT;
	private String jsonText;
	private Class<?> beanClass;
	private JSONObject expectedJson;
	private String fileName;

	@Before
	public void setup() {
		classUT = new CompletionNotifier();
	}

	public BeanSerializationTest(String fileName,Class<?> classs) {
		this.fileName = fileName;
		this.beanClass = classs;
	}

	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection fileNames() {
		return Arrays.asList(new Object[][] {
		 {"UserData.txt", User.class},
		{ "FriendsData.txt", Friends.class } });

	}

	@Test
	public void testUserBean() throws JSONException,
			UnsupportedEncodingException, URISyntaxException, IOException {
		jsonBeanTester(fileName);
	}

	private void jsonBeanTester(String fileName) throws URISyntaxException,
			UnsupportedEncodingException, IOException, JSONException {
		readResource(fileName);
		expectedJson = new JSONObject(jsonText);
		Gson gson = CustomGsonBuilder.create();
		JSONObject actualJson = new JSONObject(gson.toJson(classUT.deserialize(
				jsonText, beanClass)));
		//System.out.println(actualJson.toString());
		//System.out.println(expectedJson.toString());
		assertThat(actualJson.toString(), equalTo(expectedJson.toString()));
	}


	private void readResource(String fileName)
			throws URISyntaxException, UnsupportedEncodingException,
			IOException {
		java.net.URL url = CompletionNotifierTest.class.getResource(fileName);
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		jsonText = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
	}

}

package org.async.fbclient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.async.fbclient.beans.UserBean;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserBeanSerializationTest {

	private static CompletionNotifier classUT;
	private static String jsonText;
	private static UserBean userBean;
	private static JSONObject expectedJson;

	@BeforeClass
	public static void readJsonDataAndParse()
			throws UnsupportedEncodingException, IOException,
			URISyntaxException, JSONException {
		String fileName = "UserData.txt";
		readResource(fileName);
		classUT = new CompletionNotifier();
		parseJson();
	}

	private static void parseJson() throws JSONException {
		userBean = classUT.<UserBean> deserialize(jsonText, UserBean.class);
		expectedJson = new JSONObject(jsonText);
	}

	private static void readResource(String fileName)
			throws URISyntaxException, UnsupportedEncodingException,
			IOException {
		java.net.URL url = CompletionNotifierTest.class.getResource(fileName);
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		jsonText = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
	}

	@Test
	public void nameDetailsTest() throws JSONException {
		assertThat(expectedJson.getString("first_name"),
				equalTo(userBean.getFirstName()));
		assertThat(expectedJson.getString("last_name"),
				equalTo(userBean.getLastName()));
		assertThat(expectedJson.getString("id"), equalTo(userBean.getId()));
		assertThat(expectedJson.getString("link"),
				equalTo(userBean.getProfileLink()));
		assertThat(expectedJson.getString("username"),
				equalTo(userBean.getUserName()));
	
	}
	
	@Test
	public void trivialDetails() throws JSONException {
		assertThat(expectedJson.getString("birthday"),
				equalTo(userBean.getBirthday()));
		assertThat(expectedJson.getString("bio"),
				equalTo(userBean.getBio()));
		assertThat(expectedJson.getString("quotes"),
				equalTo(userBean.getQuotes()));
		assertThat(expectedJson.getString("gender"),
				equalTo(userBean.getGender()));
		assertThat(expectedJson.getString("religion"),
				equalTo(userBean.getReligion()));
		assertThat(expectedJson.getString("political"),
				equalTo(userBean.getPoliticalInclination()));
		assertThat(expectedJson.getString("website"),
				equalTo(userBean.getWebsite()));
		assertThat(expectedJson.getString("timezone"),
				equalTo(userBean.getTimeZone()));
		assertThat(expectedJson.getString("locale"),
				equalTo(userBean.getLocale()));
		assertThat(expectedJson.getString("verified"),
				equalTo(userBean.getVerified()));
	}

}

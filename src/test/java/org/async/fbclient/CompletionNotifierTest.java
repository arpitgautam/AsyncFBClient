package org.async.fbclient;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.async.fbclient.NotificationCallBack.Status;
import org.hamcrest.core.IsNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CompletionNotifierTest {

	private CompletionNotifier classUT;
	@Mock
	private HttpResponse<JsonNode> mockedResponse;
	@Mock
	private JsonNode mockedJsonNode;
	@Mock
	private UnirestException mockedExpcetion;

	@Before
	public void setup() {
		classUT = new CompletionNotifier();
		when(mockedResponse.getBody()).thenReturn(mockedJsonNode);
		when(mockedResponse.getCode()).thenReturn(200);
	}

	@Test
	public void onGoingTest() {
		assertThat(classUT.status(),
				equalTo(NotificationCallBack.Status.OnGoing));
	}

	@Test
	public void onCompleteTest() {
		classUT.completed(mockedResponse);
		assertThat(classUT.status(),
				equalTo(NotificationCallBack.Status.Completed));
	}

	@Test
	public void onFailedTest() {
		classUT.failed(mockedExpcetion);
		assertThat(classUT.status(),
				equalTo(NotificationCallBack.Status.Failed));
	}

	@Test
	public void onCancelledTest() {
		classUT.cancelled();
		assertThat(classUT.status(),
				equalTo(NotificationCallBack.Status.Canceled));
	}

	@Test
	public void isDoneOnCompleteTest() {
		assertFalse(classUT.isDone());
		classUT.completed(mockedResponse);
		assertTrue(classUT.isDone());
	}

	@Test
	public void isDoneOnFailedTest() {
		assertFalse(classUT.isDone());
		classUT.failed(mockedExpcetion);
		assertTrue(classUT.isDone());
	}

	@Test
	public void isDoneOnCancelledTest() {
		assertFalse(classUT.isDone());
		classUT.cancelled();
		assertTrue(classUT.isDone());
	}

	@Test
	public void checkStatusOnHttpError() {
		when(mockedResponse.getCode()).thenReturn(401);
		classUT.completed(mockedResponse);
		assertThat(classUT.status(), equalTo(NotificationCallBack.Status.Error));
	}

	@Test
	public void hasNextNegative() throws UnsupportedEncodingException,
			URISyntaxException, IOException, JSONException {
		String json = readResource("UserData.txt");
		classUT.setJsonObject(new JSONObject(json));
		assertFalse(classUT.hasNext());
	}

	@Test
	public void hasNextPositive() throws UnsupportedEncodingException,
			URISyntaxException, IOException, JSONException {
		String json = readResource("FriendsData.txt");
		classUT.setJsonObject(new JSONObject(json));
		assertTrue(classUT.hasNext());
	}

	@Test
	public void nextURLNegative() throws UnsupportedEncodingException,
			URISyntaxException, IOException, JSONException {
		String json = readResource("UserData.txt");
		classUT.setJsonObject(new JSONObject(json));
		assertNull(classUT.nextURL());
	}

	@Test
	public void nextURLPositive() throws UnsupportedEncodingException,
			URISyntaxException, IOException, JSONException {
		String json = readResource("FriendsData.txt");
		classUT.setJsonObject(new JSONObject(json));
		assertThat(
				classUT.nextURL(),
				equalTo("https://graph.facebook.com/12312132311/friends?limit=5000&offset=5000&__after_id=0987654"));
	}
	
	@Test
	public void testInit(){
		classUT.completed(mockedResponse);
		assertThat(classUT.status(),equalTo(Status.Completed));
		classUT.init();
		assertThat(classUT.status(),equalTo(Status.OnGoing));
	}

	// TODO- move this to a helper class
	private static String readResource(String fileName)
			throws URISyntaxException, UnsupportedEncodingException,
			IOException {
		java.net.URL url = CompletionNotifierTest.class.getResource(fileName);
		java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
		return new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
	}

}

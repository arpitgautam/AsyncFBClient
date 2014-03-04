package org.async.fbclient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.async.fbclient.beans.user.User;
import org.hamcrest.core.IsSame;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;

@RunWith(MockitoJUnitRunner.class)
public class OAuth2AsyncFBClientTest {

	private OAuth2AsyncFBClient classUT;
	@Mock
	private UniRestWrapper mockedWrapper;
	@Mock
	private GetRequest mockedGetRequest;
	@Mock
	private HttpRequest mockedHttpRequest;
	@Mock
	private CompletionNotifier mockedCallback;
	@Mock
	private HttpResponse<JsonNode> mockedResponse;
	@Mock
	private JsonNode mockedResponseNode;
	@Mock
	private JSONObject mockedObject;

	@Before
	public void setup() {

		classUT = new OAuth2AsyncFBClient("dummy", mockedWrapper);
		mockDependecyMethodCalls();
		classUT.setCallBack(mockedCallback);
	}

	private void mockDependecyMethodCalls() {
		Mockito.when(mockedWrapper.get(anyString(), anyString())).thenReturn(
				mockedHttpRequest);
		Mockito.when(mockedResponse.getCode()).thenReturn(200);
		Mockito.when(mockedResponse.getBody()).thenReturn(mockedResponseNode);
		Mockito.when(mockedResponseNode.getObject()).thenReturn(mockedObject);
		try {
			Mockito.when(mockedHttpRequest.asJson()).thenReturn(mockedResponse);
		} catch (Exception e) {
		}
	}

	@Test
	public void getUserDetailsTest() {
		classUT.getMyDetails();
		Mockito.verify(mockedHttpRequest).asJsonAsync(mockedCallback);
		Mockito.verify(mockedWrapper).get(
				org.mockito.Matchers.eq(Endpoints.get("me")), anyString());

	}

	@Test
	public void hasNextTest() {
		classUT.getMyDetails();
		classUT.hasNext();
		Mockito.verify(mockedCallback).hasNext();
		Mockito.verify(mockedCallback).nextURL();

	}

	@Test
	public void myFriendListPaged() throws InterruptedException {
		String nextURL = "dummyURL";
		classUT.getFriendList();
		Mockito.verify(mockedWrapper.get(Endpoints.get("friends"), "dummy"));
		Mockito.when(mockedCallback.isDone()).thenReturn(true);
		Mockito.when(mockedCallback.hasNext()).thenReturn(true);
		Mockito.when(mockedCallback.nextURL()).thenReturn(nextURL);

		while (true) {
			if (!mockedCallback.isDone()) {
				Thread.sleep(1000);
				// do more processing here
			} else {
				// process last call payload here
				if (classUT.hasNext()) {
					classUT.getNext();
					Mockito.verify(mockedWrapper).get(nextURL, "dummy");
					Mockito.verify(mockedCallback).init();
					Mockito.when(mockedCallback.hasNext()).thenReturn(false);
				} else {
					break;
				}
			}
		}
		Mockito.verify(mockedHttpRequest, Mockito.times(2)).asJsonAsync(
				mockedCallback);
	}

	@Test
	public void userDetailsById() {
		classUT.getUserDetails("1234");
		String expectedURL = String.format(Endpoints.get("user-with-id"),
				"1234");
		Mockito.verify(mockedWrapper).get(expectedURL, "dummy");
		Mockito.verify(mockedHttpRequest).asJsonAsync(mockedCallback);

	}

	@Test(expected = IllegalStateException.class)
	public void nextAsFirstCall() {
		classUT.getNext();
	}

	@Test
	public void asyncModeByDefaaut() {
		assertThat(classUT.getSyncMode(), equalTo(false));
	}

	@Test
	public void setAsync() {
		classUT.setSyncMode(true);
		assertThat(classUT.getSyncMode(), equalTo(true));
	}

	@Test
	public void verifyMethodCallOnSync() throws UnirestException {
		classUT.setSyncMode(true);
		classUT.getMyDetails();
		Mockito.verify(mockedWrapper).get(anyString(), anyString());
		Mockito.verify(mockedHttpRequest).asJson();
		JSONObject ss = classUT.getResponse();
		assertThat(classUT.getResponse(),sameInstance(mockedObject));
	}

	@Test
	public void onSyncException() throws UnirestException {
		classUT.setSyncMode(true);
		UnirestException ex = new UnirestException(null);
		Mockito.when(mockedHttpRequest.asJson()).thenThrow(ex);
		classUT.getMyDetails();
		Mockito.verify(mockedCallback).failed(ex);
	}

	@Test
	public void onErrorCodeWhileSyncFromServer() {
		classUT.setSyncMode(true);
		Mockito.when(mockedResponse.getCode()).thenReturn(404);
		classUT.getMyDetails();
		Mockito.verify(mockedCallback).failed(
				org.mockito.Matchers.any(UnirestException.class));
	}

}

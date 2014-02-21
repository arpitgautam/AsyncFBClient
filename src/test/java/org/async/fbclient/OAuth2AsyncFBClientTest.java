package org.async.fbclient;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;

import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;

public class OAuth2AsyncFBClientTest {

	private OAuth2AsyncFBClient classUT;
	private UniRestWrapper mockedWrapper;
	private GetRequest mockedGetRequest;
	private HttpRequest mockedHttpRequest;
	private CompletionNotifier mockedCallback;

	@Before
	public void setup() {

		mockDependencies();
		classUT = new OAuth2AsyncFBClient("dummy", mockedWrapper);
		mockDependecyMethodCalls();
	}

	private void mockDependecyMethodCalls() {
		Mockito.when(mockedWrapper.get("https://graph.facebook.com/me"))
				.thenReturn(mockedGetRequest);
		Mockito.when(mockedGetRequest.header(anyString(), anyString()))
				.thenReturn(mockedHttpRequest);
		Mockito.when(mockedHttpRequest.header(anyString(), anyString()))
		.thenReturn(mockedHttpRequest);
	}

	private void mockDependencies() {
		mockedWrapper = Mockito.mock(UniRestWrapper.class);
		mockedGetRequest = Mockito.mock(GetRequest.class);
		mockedHttpRequest = Mockito.mock(HttpRequest.class);
		mockedCallback = Mockito.mock(CompletionNotifier.class);
	}

	@Test
	public void getUserDetailsTest() {
		classUT.getUserDetails(mockedCallback);
		Mockito.verify(mockedHttpRequest).asJsonAsync(mockedCallback);
	}

}

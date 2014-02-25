package org.async.fbclient;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;

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

	@Before
	public void setup() {

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

	@Test
	public void getUserDetailsTest() {
		classUT.getUserDetails(mockedCallback);
		Mockito.verify(mockedHttpRequest).asJsonAsync(mockedCallback);
	}
}

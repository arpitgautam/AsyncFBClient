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
		Mockito.when(mockedWrapper.get(anyString())).thenReturn(
				mockedGetRequest);
		Mockito.when(mockedGetRequest.header(anyString(), anyString()))
				.thenReturn(mockedHttpRequest);
		Mockito.when(mockedHttpRequest.header(anyString(), anyString()))
				.thenReturn(mockedHttpRequest);
	}

	@Test
	public void getUserDetailsTest() {
		classUT.getMyDetails(mockedCallback);
		Mockito.verify(mockedHttpRequest).asJsonAsync(mockedCallback);
		Mockito.verify(mockedWrapper).get(Endpoints.get("me"));
		
	}

	@Test
	public void hasNextTest() {
		classUT.getMyDetails(mockedCallback);
		classUT.hasNext();
		Mockito.verify(mockedCallback).hasNext();
		Mockito.verify(mockedCallback).nextURL();

	}

	@Test
	public void myFriendListPaged() throws InterruptedException {
		String nextURL = "dummyURL";
		classUT.getFriendList(mockedCallback);
		Mockito.verify(mockedWrapper
				.get(Endpoints.get("friends")));
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
					classUT.getNext(mockedCallback);
					Mockito.verify(mockedWrapper).get(nextURL);
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
	public void userDetailsById(){
		classUT.getUserDetails("1234",mockedCallback);
		String expectedURL = String.format(Endpoints.get("user-with-id"),"1234");
		Mockito.verify(mockedWrapper).get(expectedURL);
		Mockito.verify(mockedHttpRequest).asJsonAsync(mockedCallback);
		
	}
}

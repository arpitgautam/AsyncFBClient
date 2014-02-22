package org.async.fbclient;

import static org.junit.Assert.*;

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
	public void setup(){
		classUT = new CompletionNotifier();
		when(mockedResponse.getBody()).thenReturn(mockedJsonNode);
		when(mockedResponse.getCode()).thenReturn(200);
	}
	
	@Test
	public void onGoingTest() {
		assertThat(classUT.status(),equalTo(NotificationCallBack.Status.OnGoing));
	}
	
	@Test
	public void onCompleteTest(){
		classUT.completed(mockedResponse);
		assertThat(classUT.status(),equalTo(NotificationCallBack.Status.Completed));
	}
	
	@Test
	public void onFailedTest(){
		classUT.failed(mockedExpcetion);
		assertThat(classUT.status(),equalTo(NotificationCallBack.Status.Failed));
	}
	
	@Test
	public void onCancelledTest(){
		classUT.cancelled();
		assertThat(classUT.status(),equalTo(NotificationCallBack.Status.Canceled));
	}
	
	@Test
	public void isDoneOnCompleteTest(){
		assertFalse(classUT.isDone());
		classUT.completed(mockedResponse);
		assertTrue(classUT.isDone());
	}
	
	@Test
	public void isDoneOnFailedTest(){
		assertFalse(classUT.isDone());
		classUT.failed(mockedExpcetion);
		assertTrue(classUT.isDone());
	}
	
	@Test
	public void isDoneOnCancelledTest(){
		assertFalse(classUT.isDone());
		classUT.cancelled();
		assertTrue(classUT.isDone());
	}
	
	@Test
	public void checkStatusOnHttpError(){
		when(mockedResponse.getCode()).thenReturn(401);
		classUT.completed(mockedResponse);
		assertThat(classUT.status(),equalTo(NotificationCallBack.Status.Error));
	}
}

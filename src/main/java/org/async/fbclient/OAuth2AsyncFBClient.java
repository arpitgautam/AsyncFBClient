package org.async.fbclient;


import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;

public class OAuth2AsyncFBClient implements AsyncFBClient {

	private String access_token;
	private UniRestWrapper wrapper;
	
	public OAuth2AsyncFBClient(String token,UniRestWrapper wrapper) {
		access_token = token;
		this.wrapper = wrapper;
	}
	

	public void getUserDetails(Callback<JsonNode> callBack) {
		wrapper.get("https://graph.facebook.com/me")
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + access_token)
				.asJsonAsync(callBack);
	}


	public void getFriendList(CompletionNotifier friendListCallBack) {
		
		
	}

}

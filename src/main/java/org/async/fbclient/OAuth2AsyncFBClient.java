package org.async.fbclient;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;

public class OAuth2AsyncFBClient implements AsyncFBClient {

	private String access_token;
	private UniRestWrapper wrapper;
	private static final String meURL = "https://graph.facebook.com/me";
	private static final String friendURL = "https://graph.facebook.com/me/friends";
	private String nextURL;
	private NotificationCallBack callBack;

	public OAuth2AsyncFBClient(String token, UniRestWrapper wrapper) {
		access_token = token;
		this.wrapper = wrapper;
	}

	public void getMyDetails(Callback<JsonNode> usercallBack) {
		this.callBack = (NotificationCallBack) usercallBack;
		wrapper.get(meURL).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + access_token)
				.asJsonAsync(usercallBack);
	}
	
	public void getUserDetails(String id,Callback<JsonNode> usercallBack) {
		//TODO- implement this
	}

	public void getFriendList(Callback<JsonNode> friendListCallBack) {
		this.callBack = (NotificationCallBack) friendListCallBack;
		wrapper.get(friendURL).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + access_token)
				.asJsonAsync(friendListCallBack);
	}

	public boolean hasNext() {
		boolean next = callBack.hasNext();
		nextURL = callBack.nextURL();
		return next;
	}

	public void getNext(Callback<JsonNode> callBack) {
		this.callBack = (NotificationCallBack) callBack;
		this.callBack.init();
		wrapper.get(nextURL).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + access_token)
				.asJsonAsync(callBack);
		return;
	}
}

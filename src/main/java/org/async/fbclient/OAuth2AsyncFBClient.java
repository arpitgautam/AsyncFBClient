package org.async.fbclient;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;

public class OAuth2AsyncFBClient implements AsyncFBClient {

	private String access_token;
	private UniRestWrapper wrapper;
	private String nextURL;
	private NotificationCallBack callBack;

	public OAuth2AsyncFBClient(String token, UniRestWrapper wrapper) {
		access_token = token;
		this.wrapper = wrapper;
	}

	public void getMyDetails(Callback<JsonNode> usercallBack) {
		this.callBack = (NotificationCallBack) usercallBack;
		wrapper.get(Endpoints.get("me")).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + access_token)
				.asJsonAsync(usercallBack);
	}
	
	public void getUserDetails(String id,Callback<JsonNode> callback) {
		String url = Endpoints.get("user-with-id");
		url = String.format(url, id);
		this.callBack = (NotificationCallBack) callback;
		wrapper.get(url).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + access_token)
				.asJsonAsync(callback);
	}

	public void getFriendList(Callback<JsonNode> friendListCallBack) {
		this.callBack = (NotificationCallBack) friendListCallBack;
		wrapper.get(Endpoints.get("friends")).header("Content-Type", "application/json")
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

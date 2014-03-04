package org.async.fbclient;

import org.async.fbclient.NotificationCallBack.Status;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class OAuth2AsyncFBClient implements FBClient {

	private String access_token;
	private UniRestWrapper wrapper;
	private String nextURL;
	private Callback<JsonNode> callBack;
	private boolean syncMode;
	private JSONObject jsonObject;

	public OAuth2AsyncFBClient(String token, UniRestWrapper wrapper) {
		access_token = token;
		this.wrapper = wrapper;
		syncMode = false;
	}

	public void setCallBack(Callback<JsonNode> usercallBack) {
		this.callBack = usercallBack;
	}

	public void getMyDetails() {
		callServer(Endpoints.get("me"));
	}

	public void getUserDetails(String id) {
		String url = Endpoints.get("user-with-id");
		url = String.format(url, id);
		callServer(url);
	}

	public void getFriendList() {
		callServer(Endpoints.get("friends"));
	}

	public boolean hasNext() {
		NotificationCallBack notifier = ((NotificationCallBack) callBack);
		boolean next = notifier.hasNext();
		nextURL = notifier.nextURL();
		return next;
	}

	public void getNext() {
		if (nextURL == null || nextURL.isEmpty())
			throw new IllegalStateException("Has Next should be called first");
		((NotificationCallBack) this.callBack).init();
		callServer(nextURL);
	}

	public <T> T deserialize(String json, Class<T> c) {
		Gson gson = CustomGsonBuilder.create();
		T deserializedObject = gson.fromJson(json, c);
		return deserializedObject;
	}

	public <T> T deserialize(Class<T> c) {
		Gson gson = CustomGsonBuilder.create();
		T deserializedObject = null;
		if (!syncMode) {
			deserializedObject = gson.fromJson(
					((NotificationCallBack) callBack).getJsonObject()
							.toString(), c);
		} else {
			deserializedObject = gson.fromJson(jsonObject.toString(), c);
		}
		return deserializedObject;
	}

	private void callServer(String url) {

		if (!syncMode) {
			wrapper.get(url, access_token).asJsonAsync(
					(Callback<JsonNode>) this.callBack);
		} else {
			try {
				HttpResponse<JsonNode> response = wrapper
						.get(url, access_token).asJson();
				if (response.getCode() != 200) {
					throw new UnirestException(new Exception(new Integer(
							response.getCode()).toString()));
				}
				jsonObject = response.getBody().getObject();
				callBack.completed(response);

			} catch (UnirestException e) {
				callBack.failed(e);
			}
		}
	}

	public void setSyncMode(boolean sync) {
		syncMode = sync;
	}

	public boolean getSyncMode() {
		return syncMode;
	}

	public JSONObject getResponse() {
		return jsonObject;
	}
}

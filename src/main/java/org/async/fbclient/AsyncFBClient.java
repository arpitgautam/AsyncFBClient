package org.async.fbclient;


import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;

public interface AsyncFBClient {

	void getMyDetails(Callback<JsonNode> callBack);

	void getFriendList(Callback<JsonNode> friendListCallBack);
	
	boolean hasNext();
	void getNext(Callback<JsonNode> usercallBack);
}

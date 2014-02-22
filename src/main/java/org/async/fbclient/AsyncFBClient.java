package org.async.fbclient;

import java.util.concurrent.Future;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;

public interface AsyncFBClient {

	void getUserDetails(Callback<JsonNode> callBack);

	void getFriendList(CompletionNotifier friendListCallBack);

}

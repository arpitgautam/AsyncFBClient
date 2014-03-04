package org.async.fbclient;


import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;

public interface FBClient {

	void getMyDetails();
	void getFriendList();
	public void getUserDetails(String id);
	boolean hasNext();
	void setCallBack(Callback<JsonNode> usercallBack);
	void getNext();
	<T> T deserialize(String json,Class<T> c);
	public <T> T deserialize(Class<T> c);
	public void setSyncMode(boolean sync);
	public boolean getSyncMode();
	
}

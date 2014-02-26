package org.async.fbclient;

import org.json.JSONObject;

public interface NotificationCallBack {

	Status status();
	enum Status{
		Completed,OnGoing,Canceled,Failed,Error
	}
	boolean isDone();
	JSONObject getJsonObject();
	<T> T deserialize(String json,Class<T> c);
	public <T> T deserialize(Class<T> c);
	boolean hasNext();
	String nextURL();
	void init();
}

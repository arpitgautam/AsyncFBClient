package org.async.fbclient;

import org.json.JSONObject;

public interface NotificationCallBack {

	Status status();
	enum Status{
		Completed,OnGoing,Canceled,Failed,Error
	}
	boolean isDone();
	JSONObject getJsonObject();
	boolean hasNext();
	String nextURL();
	void init();
}

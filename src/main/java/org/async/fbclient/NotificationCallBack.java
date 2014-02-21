package org.async.fbclient;

public interface NotificationCallBack {

	Status status();
	enum Status{
		Completed,OnGoing,Canceled,Failed
	}
	boolean isDone();
}

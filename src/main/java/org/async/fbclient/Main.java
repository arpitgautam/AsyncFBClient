package org.async.fbclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.async.fbclient.NotificationCallBack.Status;
import org.async.fbclient.beans.friends.Datum;
import org.async.fbclient.beans.friends.Friends;
import org.json.JSONException;

public class Main {

	public static void main(String[] s) throws FileNotFoundException,
			IOException, InterruptedException, ExecutionException,
			JSONException {
		Properties props = new Properties();
		props.load(new FileInputStream("properties/app.properties"));
		String ACCESS_TOKEN = props.getProperty("ACCESS_TOKEN");

		FBClient fbClient = new OAuth2AsyncFBClient(ACCESS_TOKEN,
				new UniRestWrapper());
		CompletionNotifier notifier = new CompletionNotifier();
		fbClient.setCallBack(notifier);
		fbClient.setSyncMode(true);
		fbClient.getFriendList();
		while(true && notifier.status() == Status.Completed){
			Friends friends = fbClient.deserialize(Friends.class);
			//process result from last req here
			for (Datum data : friends.getData()) {
				System.out.println(data.getName());
			}
			if (fbClient.hasNext()) {
				fbClient.getNext();

			} else {
				break;
			}

		}
		System.out.println(notifier.status());
	}
}

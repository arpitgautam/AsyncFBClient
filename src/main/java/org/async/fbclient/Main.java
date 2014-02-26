package org.async.fbclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

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

		AsyncFBClient fbClient = new OAuth2AsyncFBClient(ACCESS_TOKEN,
				new UniRestWrapper());
		CompletionNotifier notifier = new CompletionNotifier();
		fbClient.getFriendList(notifier);
		// TODO- find a way to encapsulate this pattern
		while (true) {
			if (!notifier.isDone()) {
				Thread.sleep(1000);
				// do processing here in the meanwhile
			} else {
				Friends friends = notifier.deserialize(Friends.class);
				if (fbClient.hasNext()) {
					fbClient.getNext(notifier);

				} else {
					break;
				}
				//process result from last req here
				for (Datum data : friends.getData()) {
					System.out.println(data.getName());
				}
			}
		}
		System.out.println("Done");
	}
}

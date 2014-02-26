package org.async.fbclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.async.fbclient.NotificationCallBack.Status;
import org.async.fbclient.beans.user.User;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static void main(String[] s) throws FileNotFoundException, IOException, InterruptedException, ExecutionException, JSONException{
		Properties props = new Properties();
		props.load(new FileInputStream("properties/app.properties"));
		String ACCESS_TOKEN = props.getProperty("ACCESS_TOKEN");
	
		AsyncFBClient fbC = new OAuth2AsyncFBClient(ACCESS_TOKEN,new UniRestWrapper());
		CompletionNotifier userCompletionNotifier = new CompletionNotifier();
		fbC.getUserDetails(userCompletionNotifier);
		while(!userCompletionNotifier.isDone()){
			//DO other processing here
			Thread.sleep(1000);
		}
		
		if(userCompletionNotifier.status() == Status.Completed){
			User user = userCompletionNotifier.deserialize(User.class);
			System.out.println(user.getFirst_name());
			System.out.println(user.getLast_name());
		}
		
	}
}



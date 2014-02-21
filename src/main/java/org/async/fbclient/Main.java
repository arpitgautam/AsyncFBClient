package org.async.fbclient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.async.fbclient.NotificationCallBack.Status;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static void main(String[] s) throws FileNotFoundException, IOException, InterruptedException, ExecutionException, JSONException{
		Properties props = new Properties();
		props.load(new FileInputStream("properties/app.properties"));
		String ACCESS_TOKEN = props.getProperty("ACCESS_TOKEN");
	
		AsyncFBClient fbC = new OAuth2AsyncFBClient(ACCESS_TOKEN,new UniRestWrapper());
		CompletionNotifier callback = new CompletionNotifier();
		fbC.getUserDetails(callback);
		while(!callback.isDone()){
			Thread.sleep(100);
		}
		if(callback.status() == Status.Completed){
			JSONObject object = callback.getJsonObject();
			System.out.println(object.get("first_name"));
		}
		System.out.println("Done");
	}
}



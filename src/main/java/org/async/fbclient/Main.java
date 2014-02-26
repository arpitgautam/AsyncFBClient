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
import org.mockito.Mockito;

public class Main {

	public static void main(String[] s) throws FileNotFoundException, IOException, InterruptedException, ExecutionException, JSONException{
		Properties props = new Properties();
		props.load(new FileInputStream("properties/app.properties"));
		String ACCESS_TOKEN = props.getProperty("ACCESS_TOKEN");
	
		AsyncFBClient fbC = new OAuth2AsyncFBClient(ACCESS_TOKEN,new UniRestWrapper());
		CompletionNotifier notifier = new CompletionNotifier();
		fbC.getFriendList(notifier);
		while(true){
			if(!notifier.isDone()){
				Thread.sleep(1000);
				//do more processing here
			}else{
				System.out.println(notifier.getJsonObject().get("data"));
				 if(fbC.hasNext()){
					 fbC.getNext(notifier);	
					
				 }else{
					 break;
				 }
			}
		}
		System.out.println("Done");
	}
}



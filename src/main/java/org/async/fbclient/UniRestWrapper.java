package org.async.fbclient;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequest;

/**
 * As UniRest contains a lot of static methods
 * which will be untestable par se. Wrapping it in a 
 * mockable class
 * @author Arpit.Gautam
 *
 */
public class UniRestWrapper {
	
	public HttpRequest get(String url,String access_token){
		return Unirest.get(url).header("Content-Type", "application/json")
		.header("Authorization", "Bearer " + access_token);
	}

}

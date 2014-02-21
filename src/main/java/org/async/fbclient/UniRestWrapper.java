package org.async.fbclient;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

/**
 * As UniRest contains a lot of static methods
 * which will be untestable par se. Wrapping it in a 
 * mockable class
 * @author Arpit.Gautam
 *
 */
public class UniRestWrapper {
	
	public GetRequest get(String url){
		return Unirest.get(url);
	}

}

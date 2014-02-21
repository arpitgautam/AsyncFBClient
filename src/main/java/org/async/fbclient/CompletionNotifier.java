package org.async.fbclient;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CompletionNotifier implements Callback<JsonNode>,NotificationCallBack{

	public CompletionNotifier(){
		status = Status.OnGoing;
	}
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public void completed(HttpResponse<JsonNode> response) {
		//handle 400 response here
		jsonObject = response.getBody().getObject();
		status = Status.Completed;
	}

	public void failed(UnirestException e) {
		System.out.println(e.getMessage());
		status = Status.Failed;
	}

	public void cancelled() {
		System.out.println("Canecel");
		status = Status.Canceled;
	}

	public Status status() {
		return status;
	}
	
	public boolean isDone(){
		if(status == Status.OnGoing)return false;
		return true;
	}

	private Status status;
	private JSONObject jsonObject;
}

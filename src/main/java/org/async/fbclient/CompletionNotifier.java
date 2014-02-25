package org.async.fbclient;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CompletionNotifier implements Callback<JsonNode>,
		NotificationCallBack {

	public CompletionNotifier() {
		status = Status.OnGoing;
	}
	
	//TODO- add init here, that will be called before next

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public void completed(HttpResponse<JsonNode> response) {
		if (response.getCode() != 200) {
			status = Status.Error;
		} else {
			jsonObject = response.getBody().getObject();
			status = Status.Completed;
		}
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

	public boolean isDone() {
		return status != Status.OnGoing;
	}

	public <T> T deserialize(String json, Class<T> c) {
		Gson gson = CustomGsonBuilder.create();
		T deserializedObject = gson.fromJson(json, c);
		return deserializedObject;
	}

	public <T> T deserialize(Class<T> c) {
		Gson gson = CustomGsonBuilder.create();
		T deserializedObject = gson.fromJson(jsonObject.toString(), c);
		return deserializedObject;
	}

	private Status status;
	private JSONObject jsonObject;

	public boolean hasNext(){
		boolean ret = false;;
		try{
		if (jsonObject != null && jsonObject.has("paging")
				&& jsonObject.getJSONObject("paging").has("next")) {
			ret = true;
		}
		}catch(JSONException e){
			ret  = false;
		}
		return ret;
	}

	public String nextURL() {
		String ret = null;
		if(hasNext()){
			try{
			ret = jsonObject.getJSONObject("paging").getString("next");
			}catch(JSONException e){
				//ignoring exception here as hasNext took care of it for now
				ret = null;
			}
		}
		return ret;
	}
}

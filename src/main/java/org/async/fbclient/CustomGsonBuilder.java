package org.async.fbclient;

import java.util.Collection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomGsonBuilder {

	public static Gson create() {
		Gson gson = new GsonBuilder()
				.registerTypeHierarchyAdapter(Collection.class,
						new CollectionAdapter())
				.setFieldNamingPolicy(
						FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return gson;
	}

}

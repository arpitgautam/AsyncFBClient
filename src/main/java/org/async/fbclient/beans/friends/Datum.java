
package org.async.fbclient.beans.friends;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("com.googlecode.jsonschema2pojo")
public class Datum {

    @Expose
    private String name;
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

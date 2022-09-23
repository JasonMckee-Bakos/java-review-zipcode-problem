package com.kenzie.app.zipcode.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {
    @JsonProperty(value = "place name")
    private String place_name;

    @JsonProperty(value = "post code")
    private String post_code;

    //declare methods - getters/setters
    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }
}

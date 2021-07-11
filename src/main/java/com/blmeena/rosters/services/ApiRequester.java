package com.blmeena.rosters.services;



import com.blmeena.rosters.exceptions.BadRequestException;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiRequester {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private HashMap<String, String> defaultHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        return headers;
    }
    public JSONObject postRequest(String url, JSONObject params , HashMap<String, String> customHeaders) {
        HashMap<String, String> headers =  defaultHeaders();
        if(!customHeaders.isEmpty()){
            for (Map.Entry mapElement : customHeaders.entrySet()) {
                headers.put(mapElement.getKey().toString(), mapElement.getValue().toString());
            }
        }
        Headers headerBuild = Headers.of(headers);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, String.valueOf(params));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(headerBuild)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException | JSONException | NullPointerException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public JSONObject putRequest(String url, JSONObject params,  HashMap<String, String> customHeaders) {
        HashMap<String, String> headers =  defaultHeaders();
        if(!customHeaders.isEmpty()){
            for (Map.Entry mapElement : customHeaders.entrySet()) {
                headers.put(mapElement.getKey().toString(), mapElement.getValue().toString());
            }
        }
        Headers headerBuild = Headers.of(headers);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), String.valueOf(params));
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .headers(headerBuild)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONObject(response.body() != null ? response.body().string() : null);
        } catch (IOException | JSONException | NullPointerException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public String getRequest(String url, HashMap<String, String> customHeaders) {
        HashMap<String, String> headers =  defaultHeaders();
        if(!customHeaders.isEmpty()){
            for (Map.Entry mapElement : customHeaders.entrySet()) {
                headers.put(mapElement.getKey().toString(), mapElement.getValue().toString());
            }
        }
        Headers headerBuild = Headers.of(headers);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .headers(headerBuild)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException | JSONException | NullPointerException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}

package com.blmeena.rosters.services;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class G2CrowdCommunicator extends ApiRequester {

    @Value("${g2_crowd.base_url}")
    String baseUrl;

    private String getBaseUrl() {
        return baseUrl;
    }

    private HashMap<String, String> getDefaultHeaders() {
        HashMap<String, String> headers = new HashMap<>();
        //headers.put("Con", "application/json");
        return headers;
    }

    @SneakyThrows
    public JSONArray fetchEmployList() {
        String url = getBaseUrl();
        String response = getRequest(url, getDefaultHeaders());

        if(response.length() > 0){
            return new JSONArray(response);
        }
        return null;
    }
}


package com.example.sevak1994.qrcodescanner.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by Sevak1994 on 10/23/2017.
 */

public class RestRepository {

    private static RestRepository restRepository;
    private RequestQueue requestQueue;

    private RestRepository(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static RestRepository getInstance(Context context) {
        if (restRepository == null) {
            synchronized (RestRepository.class) {
                if (restRepository == null) {
                    restRepository = new RestRepository(context);
                }
            }
        }
        return restRepository;
    }

    public void UserSignUp(String url, String email, String password, String confirmPassword,
                           HttpResponse.Listener<Object> listener,
                           HttpResponse.ErrorListener errorListener) {

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("confirmPassword", confirmPassword);

        HttpPostRequest httpPostRequest = new HttpPostRequest(url, jsonObject.toString(), null, Object.class, gson, listener, errorListener);

        requestQueue.add(httpPostRequest);
    }
}

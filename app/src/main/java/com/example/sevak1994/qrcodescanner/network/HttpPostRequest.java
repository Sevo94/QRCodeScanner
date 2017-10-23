package com.example.sevak1994.qrcodescanner.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Sevak1994 on 10/23/2017.
 */

public class HttpPostRequest<T> extends JsonRequest<T> {
    private final Gson gson;
    private final Type type;
    private final Map<String, String> headers;


    private final HttpResponse.Listener<T> listener;
    private final HttpResponse.ErrorListener errorListener;

    private Map<String, String> responseHeaders;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url           URL of the request to make
     * @param type          is the type of the object to be returned
     * @param listener      is the listener for unsuccessful request
     * @param errorListener is the listener for the unsuccessful request
     */
    public HttpPostRequest(final String url, final String body, Map<String, String> headers, final Type type, final Gson gson, final HttpResponse.Listener<T> listener, final HttpResponse.ErrorListener errorListener) {
        super(Method.POST, url, body, null, null);

        this.gson = gson;
        this.type = type;
        this.headers = (headers != null) ? headers : Collections.<String, String>emptyMap();
        this.listener = listener;
        this.errorListener = errorListener;
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public void deliverError(VolleyError error) {
        if (errorListener != null) {
            errorListener.onErrorResponse((HttpErrorResponse) error);
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response, responseHeaders);
    }

    @Override
    protected HttpErrorResponse parseNetworkError(VolleyError error) {
        return new HttpErrorResponse(error);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.d("gg", new String(response.data, Charset.forName("UTF8")));
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            responseHeaders = response.headers;

            return (Response<T>) Response.success(gson.fromJson(json, type), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}

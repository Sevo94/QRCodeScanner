package com.example.sevak1994.qrcodescanner.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Sevak1994 on 10/23/2017.
 */

public class HttpErrorResponse extends VolleyError {
    private boolean timeoutError;
    private boolean authError;
    private boolean serverError;
    private boolean networkError;
    private boolean parseError;

    private String message;

    public HttpErrorResponse(VolleyError error) {
        super(error.networkResponse);

        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            this.timeoutError = true;
        } else if (error instanceof AuthFailureError) {
            this.authError = true;
        } else if (error instanceof ServerError) {
            this.serverError = true;
        } else if (error instanceof NetworkError) {
            this.networkError= true;
        } else if (error instanceof ParseError) {
            this.parseError = true;
        }

        if (this.serverError) {
            try {
                JSONObject errorObj = new JSONObject(new String(error.networkResponse.data, "UTF-8"));
                message = errorObj.getString("error_msg");
            } catch (Throwable t) {
                parseError = true;
                serverError = false;
                message = null;
            }
        }
    }

    public boolean didTimeout() {
        return timeoutError;
    }

    public boolean didGetAuthError() {
        return authError;
    }

    public boolean didGetServerError() {
        return serverError;
    }

    public boolean didGetNetworkError() {
        return networkError;
    }

    public boolean didGetParseError() {
        return parseError;
    }

    public String getErrorMessage() {
        return message;
    }
}

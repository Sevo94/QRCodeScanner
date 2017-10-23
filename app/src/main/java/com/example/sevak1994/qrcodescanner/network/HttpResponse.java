package com.example.sevak1994.qrcodescanner.network;

import java.util.Map;

/**
 * Created by Sevak1994 on 10/23/2017.
 */

public class HttpResponse {

    /** Callback interface for delivering error responses. */
    public interface ErrorListener {
        /**
         * Callback method that an error has been occurred with the
         * provided error code and optional user-readable message.
         */
        public void onErrorResponse(HttpErrorResponse error);
    }

    /** Callback interface for delivering parsed responses. */
    public interface Listener<T> {
        /** Called when a response is received. */
        public void onResponse(T response, Map<String, String> headers);
    }

    public interface AuthListener {
        /** Called when a response is received. */
        public void onResponse(Boolean success, String message);
    }
}

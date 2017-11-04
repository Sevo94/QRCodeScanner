package com.example.sevak1994.qrcodescanner;

/**
 * Created by Admin on 10/19/2017.
 */

public class Constants {

    public static final String COGNITO_POOL_ID     = "eu-central-1:3a8bcac5-1337-417a-9794-491ae2bafa19";

    public static final String COGNITO_POOL_REGION = "eu-central-1";

    public static final String BUCKET_NAME         = "biss-file";

    public static final String BUCKET_REGION       = "eu-central-1";

    public static final String UPLOAD_KEY          = "upload_key";

    public static final String PROFILE_PATH        = "data/data/com.example.sevak1994.qrcodescanner";

    public static final String USER_LOGIN          = "user_login";

    public static final String USER_ID             = "user_id";

    //PERMISSIONS
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    public static final int REQUEST_READ_EXTERNAL_STORAGE = 3;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 4;


    //API END POINTS
    public static final String AUTHENTICATE_URL    = "http://18.194.74.98";
    public static final String SIGN_UP             = AUTHENTICATE_URL + "/signup";
    public static final String LOG_IN              = AUTHENTICATE_URL + "/login";
}
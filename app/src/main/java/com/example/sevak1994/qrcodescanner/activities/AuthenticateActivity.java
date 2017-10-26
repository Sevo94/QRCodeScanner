package com.example.sevak1994.qrcodescanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sevak1994.qrcodescanner.BissApplication;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.fragments.HomeFragment;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;
import com.example.sevak1994.qrcodescanner.models.UserModelRoot;
import com.example.sevak1994.qrcodescanner.network.HttpErrorResponse;
import com.example.sevak1994.qrcodescanner.network.HttpResponse;
import com.example.sevak1994.qrcodescanner.network.RestRepository;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Sevak1994 on 10/23/2017.
 */

public class AuthenticateActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private TextInputLayout emailTextLayout;
    private TextInputLayout passwordTextLayout;
    private TextInputLayout confirmPasswordTextLayout;

    private Button signUpBtn;
    private Button signInBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autheticate);

        initUI();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestRepository.getInstance(BissApplication.getInstance()).UserSignUp("http://18.194.74.98/signup", "sevakprogrammer94@gmail.com", "Sevak_a26w00_k14", "Sevak_a26w00_k14", new HttpResponse.Listener<Object>() {
                            @Override
                            public void onResponse(Object response, Map<String, String> headers) {

                            }
                        },
                        new HttpResponse.ErrorListener() {
                            @Override
                            public void onErrorResponse(HttpErrorResponse error) {
                                signInBtn.setVisibility(View.GONE);
                                emailTextLayout.setVisibility(View.VISIBLE);
                                passwordTextLayout.setVisibility(View.VISIBLE);
                                confirmPasswordTextLayout.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                RestRepository.getInstance(BissApplication.getInstance()).UserSignIn("http://18.194.74.98/login", "sevakprogrammer94@gmail.com", "Sevak_a26w00_k14", deviceID, new HttpResponse.Listener<UserModelRoot>() {
                    @Override
                    public void onResponse(UserModelRoot response, Map<String, String> headers) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                        SharedPreferenceHelper.storeStringInPreference("signin", "ddd");
                    }
                }, new HttpResponse.ErrorListener() {
                    @Override
                    public void onErrorResponse(HttpErrorResponse error) {

                    }
                });
            }
        });
    }

    private void initUI() {
        emailEditText = (EditText) findViewById(R.id.email_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirm_password_edit_text);

        emailTextLayout = (TextInputLayout) findViewById(R.id.email_input_layout);
        passwordTextLayout = (TextInputLayout) findViewById(R.id.password_input_layout);
        confirmPasswordTextLayout = (TextInputLayout) findViewById(R.id.confirm_password_input_layout);

        signUpBtn = (Button) findViewById(R.id.sign_up_btn);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);

        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        signUpBtn.setVisibility(View.VISIBLE);
        signInBtn.setVisibility(View.VISIBLE);

        emailTextLayout.setVisibility(View.GONE);
        passwordTextLayout.setVisibility(View.GONE);
        confirmPasswordTextLayout.setVisibility(View.GONE);
    }
}

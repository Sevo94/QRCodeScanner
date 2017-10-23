package com.example.sevak1994.qrcodescanner.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sevak1994.qrcodescanner.BissApplication;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.network.HttpErrorResponse;
import com.example.sevak1994.qrcodescanner.network.HttpResponse;
import com.example.sevak1994.qrcodescanner.network.RestRepository;

import java.util.Map;

/**
 * Created by Sevak1994 on 10/23/2017.
 */

public class AuthenticateActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private Button signUpBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autheticate);

        initUI();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestRepository.getInstance(BissApplication.getInstance()).UserSignUp("18.194.74.98/signup", "sevakprogrammer94@gmail.com", "Sevak_a26w00_k14", "Sevak_a26w00_k14", new HttpResponse.Listener<Object>() {
                            @Override
                            public void onResponse(Object response, Map<String, String> headers) {

                            }
                        },
                        new HttpResponse.ErrorListener() {
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
        signUpBtn = (Button) findViewById(R.id.sign_up_btn);
    }

}

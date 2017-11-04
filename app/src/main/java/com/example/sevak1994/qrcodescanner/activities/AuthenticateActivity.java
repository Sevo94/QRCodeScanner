package com.example.sevak1994.qrcodescanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sevak1994.qrcodescanner.BissApplication;
import com.example.sevak1994.qrcodescanner.Constants;
import com.example.sevak1994.qrcodescanner.R;
import com.example.sevak1994.qrcodescanner.UserInfo;
import com.example.sevak1994.qrcodescanner.helper.CommonHelper;
import com.example.sevak1994.qrcodescanner.helper.SharedPreferenceHelper;
import com.example.sevak1994.qrcodescanner.models.UserModelRoot;
import com.example.sevak1994.qrcodescanner.network.HttpErrorResponse;
import com.example.sevak1994.qrcodescanner.network.HttpResponse;
import com.example.sevak1994.qrcodescanner.network.RestRepository;

import java.util.Map;

import static com.example.sevak1994.qrcodescanner.Constants.USER_ID;

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

    private boolean signIn;
    private boolean signUp;

    private TextView singInText;
    private TextView singUpText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autheticate);

        initUI();

        signInClick();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!signUp) {
                    signUpClick();
                } else {
                    //TODO Validate email password and confirmPassword

                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    String confirmPassword = confirmPasswordEditText.getText().toString();

                    if (email.isEmpty()) {
                        CommonHelper.shakeAnimation(emailTextLayout);
                        return;
                    }

                    if (password.isEmpty()) {
                        CommonHelper.shakeAnimation(passwordTextLayout);
                        return;
                    }

                    if (confirmPassword.isEmpty()) {
                        CommonHelper.shakeAnimation(confirmPasswordTextLayout);
                        return;
                    }


                    RestRepository.getInstance(BissApplication.getInstance()).UserSignUp(Constants.SIGN_UP, email, password, confirmPassword, new HttpResponse.Listener<UserModelRoot>() {
                                @Override
                                public void onResponse(UserModelRoot response, Map<String, String> headers) {
                                    signInClick();
                                }
                            },
                            new HttpResponse.ErrorListener() {
                                @Override
                                public void onErrorResponse(HttpErrorResponse error) {
                                    Toast.makeText(getApplicationContext(), "sign up failure", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!signIn) {
                    signInClick();
                } else {
                    //TODO Validate email and password
                    String deviceID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    if (email.isEmpty()) {
                        CommonHelper.shakeAnimation(emailTextLayout);
                        return;
                    }

                    if (password.isEmpty()) {
                        CommonHelper.shakeAnimation(passwordTextLayout);
                        return;
                    }

                    RestRepository.getInstance(BissApplication.getInstance()).UserSignIn(Constants.LOG_IN, email, password, deviceID, new HttpResponse.Listener<UserModelRoot>() {
                        @Override
                        public void onResponse(UserModelRoot response, Map<String, String> headers) {
                            if (!response.isError()) {
                                SharedPreferenceHelper.storeStringInPreference(USER_ID, response.getUser().get_ID());

                                UserInfo userInfo = UserInfo.getInstance();
                                userInfo.setUserID(response.getUser().get_ID());
                                userInfo.setEmail(response.getUser().getEmail());

                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }, new HttpResponse.ErrorListener() {
                        @Override
                        public void onErrorResponse(HttpErrorResponse error) {
                            Toast.makeText(getApplicationContext(), "sign in failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
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

        singInText = signInBtn;
        singUpText = signUpBtn;

        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    private void signInClick() {
        signIn = true;
        signUp = false;
        signInBtn.setBackgroundResource(R.drawable.auth_btn_bg_shape_select);
        signUpBtn.setBackgroundResource(R.drawable.auth_btn_bg_shape_deselect);
        emailTextLayout.setVisibility(View.VISIBLE);
        passwordTextLayout.setVisibility(View.VISIBLE);
        confirmPasswordTextLayout.setVisibility(View.GONE);

        singInText.setTextColor(getResources().getColor(R.color.black));
        singUpText.setTextColor(getResources().getColor(R.color.white));

    }

    private void signUpClick() {
        signUp = true;
        signIn = false;
        signUpBtn.setBackgroundResource(R.drawable.auth_btn_bg_shape_select);
        signInBtn.setBackgroundResource(R.drawable.auth_btn_bg_shape_deselect);
        emailTextLayout.setVisibility(View.VISIBLE);
        passwordTextLayout.setVisibility(View.VISIBLE);
        confirmPasswordTextLayout.setVisibility(View.VISIBLE);

        singUpText.setTextColor(getResources().getColor(R.color.black));
        singInText.setTextColor(getResources().getColor(R.color.white));
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

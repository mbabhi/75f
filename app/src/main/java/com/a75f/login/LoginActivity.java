package com.a75f.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a75f.home.HomeActivity;
import com.a75f.R;
import com.kinvey.android.Client;
import app.BaseApplication;
import utils.ProgressUtils;

/**
 * Created by abhil on 13-10-2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private Client mKinveyClient;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mKinveyClient = ((BaseApplication) getApplication()).getKinveyClient();
        mEditTextUserName = (EditText) findViewById(R.id.tv_user_name);
        mEditTextPassword = (EditText) findViewById(R.id.tv_password);
        Button mButton = (Button) findViewById(R.id.btn_login);
        loginPresenter = new LoginPresenterImpl(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.showProgress(LoginActivity.this, getString(R.string.loading));
                loginPresenter.kinveyLogin(LoginActivity.this,mKinveyClient, mEditTextUserName.getText().toString(), mEditTextPassword.getText().toString());
               // loginPresenter.performSignUp(LoginActivity.this,mKinveyClient, mEditTextUserName.getText().toString(), mEditTextPassword.getText().toString());
            }
        });
    }

    @Override
    public void onError(String error) {
        ProgressUtils.hideProgress();
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String success) {
        ProgressUtils.hideProgress();
        Toast.makeText(LoginActivity.this, success, Toast.LENGTH_SHORT).show();
        goToHome();
    }

    private void goToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}

package com.a75f.login;


import android.app.Activity;
import android.util.Log;

import com.a75f.R;
import com.kinvey.android.Client;
import com.kinvey.android.model.User;
import com.kinvey.android.store.UserStore;
import com.kinvey.java.core.KinveyClientCallback;

import java.io.IOException;


/**
 * Created by abhil on 14-10-2017.
 */

class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    //In case user wants to sign up
    @Override
    public void performSignUp(final Activity activity, final Client mKinveyClient, final String username, final String password) {
        if (!mKinveyClient.isUserLoggedIn()) {
            UserStore.signUp(username, password, mKinveyClient, new KinveyClientCallback<User>() {
                @Override
                public void onFailure(Throwable t) {
                    CharSequence text = "Could not sign up.";
                    loginView.onError(text.toString());
                }

                @Override
                public void onSuccess(User u) {
                    //CharSequence text = u.getUsername() + ", your account has been created.";
                    performLogin(activity,mKinveyClient, username, password);
                }
            });

        } else {

        }
    }

    @Override
    public void kinveyLogin(Activity activity, Client mKinveyClient, String username, String password) {
        if (!mKinveyClient.isUserLoggedIn()) {
            performLogin(activity,mKinveyClient, username, password);
        } else {
            loginView.onSuccess(activity.getString(R.string.welcome));
        }
    }

    private void performLogin(final Activity activity, Client mKinveyClient, String username, String password) {
        try {
            UserStore.login(username, password, mKinveyClient, new KinveyClientCallback<User>() {
                @Override
                public void onFailure(Throwable t) {
                    Log.d("Login error",t.getLocalizedMessage());
                    CharSequence text = activity.getString(R.string.wrong_user_name);
                    loginView.onError(text.toString());
                }

                @Override
                public void onSuccess(User u) {
                    Log.d("login success",u.getUsername());
                    CharSequence text = activity.getString(R.string.welcome_back) + u.getUsername() + ".";
                    loginView.onSuccess(text.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

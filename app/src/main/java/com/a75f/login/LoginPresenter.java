package com.a75f.login;

import android.app.Activity;

import com.kinvey.android.Client;

/**
 * Created by abhil on 14-10-2017.
 */

public interface LoginPresenter {
    void performSignUp(Activity activity, Client mKinveyClient, String username, String password);

    void kinveyLogin(Activity activity, Client mKinveyClient, String username, String password);
}

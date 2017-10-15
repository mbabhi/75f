package com.a75f.login;

/**
 * Created by abhil on 14-10-2017.
 */

public interface LoginView {
    void onError(String error);

    void onSuccess(String success);
}

package com.a75f.home;

import java.util.List;

import data.HomeResponse;

/**
 * Created by abhil on 13-10-2017.
 */

public interface HomeView {
    void onHomeDataSuccess(List<HomeResponse> result);

    void onError(String error);

    void onLogoutSuccess();
}

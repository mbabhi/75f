package com.a75f.home;

import android.app.Activity;

import com.kinvey.android.Client;
import com.kinvey.android.store.DataStore;

import data.HomeResponse;

/**
 * Created by abhil on 13-10-2017.
 */

public interface HomePresenter {
    void getHomeData(Activity activity, Client mKinveyClient, DataStore<HomeResponse> homeStore);

    void logOutKinvey(Activity activity, Client mKinveyClient);
}

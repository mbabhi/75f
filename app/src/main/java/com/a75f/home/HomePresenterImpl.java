package com.a75f.home;

import android.app.Activity;
import android.util.Log;

import com.a75f.R;
import com.kinvey.android.Client;
import com.kinvey.android.callback.KinveyListCallback;
import com.kinvey.android.store.DataStore;
import com.kinvey.android.store.UserStore;
import com.kinvey.java.core.KinveyClientCallback;

import java.util.List;

import data.HomeResponse;

/**
 * Created by abhil on 13-10-2017.
 */

public class HomePresenterImpl implements HomePresenter {
    HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getHomeData(final Activity activity, Client mKinveyClient, DataStore<HomeResponse> homeStore) {
        homeStore.find(new KinveyListCallback<HomeResponse>() {
            @Override
            public void onSuccess(List<HomeResponse> result) {
                Log.d("Collection success", String.valueOf(result));
                homeView.onHomeDataSuccess(result);
            }

            @Override
            public void onFailure(Throwable error) {
                Log.d("Collection error", error.getLocalizedMessage());
                homeView.onError(activity.getString(R.string.somethig_went_wrong));
            }
        });
    }

    @Override
    public void logOutKinvey(final Activity activity, Client mKinveyClient) {
        UserStore.logout(mKinveyClient, new KinveyClientCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                Log.d("Logout error", throwable.getLocalizedMessage());
                homeView.onError(activity.getString(R.string.somethig_went_wrong));
            }

            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Logout success", String.valueOf(aVoid));
                homeView.onLogoutSuccess();
            }
        });
    }
}

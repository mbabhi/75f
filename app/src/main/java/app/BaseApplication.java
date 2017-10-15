package app;

import android.app.Application;

import com.kinvey.android.Client;

/**
 * Created by abhil on 12-10-2017.
 */

public class BaseApplication extends Application {
    private Client mKinveyClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mKinveyClient = new Client.Builder(this).build();
    }

    public Client getKinveyClient() {
        return mKinveyClient;
    }

}

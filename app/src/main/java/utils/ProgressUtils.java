package utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by nikhil.v on 6/16/2017.
 */

public class ProgressUtils {

    public static ProgressDialog mProgressDialog =  null;

    public static void showProgress(Activity activity, String message) {
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setCancelable(false);

        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    public static void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

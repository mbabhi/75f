package com.a75f.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.a75f.R;
import com.a75f.login.LoginActivity;
import com.kinvey.android.Client;
import com.kinvey.android.store.DataStore;
import com.kinvey.java.store.StoreType;

import java.util.List;

import app.BaseApplication;
import constants.Constants;
import data.HomeResponse;
import data.Position;
import utils.ProgressUtils;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private Client mKinveyClient;
    private DataStore<HomeResponse> homeStore;
    List<HomeResponse> homeResponseList;
    private ImageView mImageView;
    HomePresenter homePresenter;
    LinearLayout layout;
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageView = (ImageView) findViewById(R.id.home_background);
        layout = (LinearLayout) findViewById(R.id.homeLayout);
        setSupportActionBar(toolbar);
        mKinveyClient = ((BaseApplication) getApplication()).getKinveyClient();
        homePresenter = new HomePresenterImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ProgressUtils.showProgress(HomeActivity.this, getString(R.string.please_wait));
        homeStore = DataStore.collection(Constants.KINVEY_COLLECTION, HomeResponse.class, StoreType.CACHE, mKinveyClient);
        homePresenter.getHomeData(HomeActivity.this, mKinveyClient, homeStore);
    }

    @Override
    public void onHomeDataSuccess(List<HomeResponse> result) {
        homeResponseList = result;
        drawImage(homeResponseList);
    }

    @Override
    public void onError(String error) {
        ProgressUtils.hideProgress();
        Toast.makeText(HomeActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    private void drawImage(List<HomeResponse> homeResponseList) {
        //Load image onto bitmap
        bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.background_balcony);
        //Create paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.YELLOW);
        //temp bitmap
        Bitmap tempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        //create new canvas and add bitmap
        Canvas tempCanvas = new Canvas(tempBitmap);
        //Draw image bitmap on canvas
        tempCanvas.drawBitmap(bitmap, 0, 0, null);

        //Draw rectangles
        for (int i = 0; i < homeResponseList.size(); i++) {
            Position position = homeResponseList.get(i).getPosition();

            double x = Math.round(position.getX());
            double y = Math.round(position.getY());
            double width = Math.round(position.getX()) + Math.round(position.getWidth());
            double height = Math.round(position.getY()) + Math.round(position.getHeight());
            // tempCanvas.drawRect(new RectF((float) x, (float) y, (float) width, (float) height), paint);
            tempCanvas.drawRoundRect(new RectF((float) x, (float) y, (float) width, (float) height), 2, 2, paint);
            mImageView.setImageBitmap(tempBitmap);
        }

        //Without the background image.andr
//        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
//                .getDefaultDisplay().getWidth(), (int) getWindowManager()
//                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        mImageView.setImageBitmap(bitmap);
//      //
//        //  mImageView.setBackgroundColor(getResources().getColor(R.color.orrange));
//
//        // Rectangle
//
//        Paint paint = new Paint();
//        paint.setColor(Color.GREEN);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(10);
////           float x = 22;
////           float top = 207;
////           float right = 369;
////           float bottom = 388;
//        for (int i = 0; i < homeResponseList.size(); i++) {
//            Position position = homeResponseList.get(i).getPosition();
//
//            double x = Math.round(position.getX());
//            double y = Math.round(position.getY());
//            double width = Math.round(position.getX()) + Math.round(position.getWidth());
//            double height = Math.round(position.getY()) + Math.round(position.getHeight());
//            canvas.drawRect((float) x, (float) y, (float) width, (float) height, paint);
//
//        }
        ProgressUtils.hideProgress();


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        showLogOutDialog();
    }

    private void showLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.logout_message)
                .setTitle(R.string.logout_confirm);
        builder.setPositiveButton(R.string.logout_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ProgressUtils.showProgress(HomeActivity.this, getString(R.string.logging_out));
                homePresenter.logOutKinvey(HomeActivity.this, mKinveyClient);
            }
        });

        builder.setNegativeButton(R.string.logout_no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });


        builder.show();
    }

    @Override
    public void onLogoutSuccess() {
        ProgressUtils.hideProgress();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(HomeActivity.this, R.string.logout_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }
}

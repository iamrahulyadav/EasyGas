package easygas.app.com.easygas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import easygas.app.com.easygas.Widget.BaseActivity;

import static easygas.app.com.easygas.Utils.Constant.SPLASH_TIME;


public class SplashActivity extends BaseActivity {
    private Handler handler;
    private Runnable runnable;
    public static int READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) /*+ ContextCompat
                    .checkSelfPermission(SplashActivity.this,
                            Manifest.permission.READ_CONTACTS)*/
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_EXTERNAL_STORAGE);
            } else {
                startTimer();
            }
        } else {
            startTimer();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    private void startTimer() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(Dashboard.class);
            }
        };
        handler.postDelayed(runnable, SPLASH_TIME);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == READ_EXTERNAL_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startTimer();
            } else {
                toast(getString(R.string.permission_denied));
                startTimer();
            }
            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }
}

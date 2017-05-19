package easygas.app.com.easygas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import easygas.app.com.easygas.Retrofit.ApiClient;
import easygas.app.com.easygas.Retrofit.ApiInterface;
import easygas.app.com.easygas.Retrofit.LoginMainStatus;
import easygas.app.com.easygas.Retrofit.Model.UserDetails;
import easygas.app.com.easygas.Utils.Constant;
import easygas.app.com.easygas.Utils.Utils;
import easygas.app.com.easygas.Widget.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static easygas.app.com.easygas.Utils.Constant.USERTYPE;

/**
 * Created by archirayan on 24-Apr-17.
 */

public class Login extends BaseActivity implements View.OnClickListener {

    public String userRole;
    public EditText emailEdt, passwordEdt;
    public TextView submitTv, signUpTv, forgotTv, signotherTv;
    public String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submitTv = (TextView) findViewById(R.id.activity_login_submit);
        emailEdt = (EditText) findViewById(R.id.activity_login_email);
        passwordEdt = (EditText) findViewById(R.id.activity_login_password);
        signUpTv = (TextView) findViewById(R.id.activity_login_signup);
        forgotTv = (TextView) findViewById(R.id.activity_login_forgotpassword);
        signotherTv = (TextView) findViewById(R.id.activity_login_signwithother);
        init();
    }

    private void init() {
        submitTv.setOnClickListener(this);
        signUpTv.setOnClickListener(this);
        forgotTv.setOnClickListener(this);
        signotherTv.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getExtras() != null) {
            userRole = getIntent().getExtras().getString(USERTYPE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_login_submit:
                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
                email = emailEdt.getText().toString();
                password = passwordEdt.getText().toString();

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email_phone", email);
                hashMap.put("password", password);
                hashMap.put("role", userRole);

                ApiInterface apiService =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<LoginMainStatus> call = apiService.getLogin(hashMap);
                call.enqueue(new Callback<LoginMainStatus>() {
                    @Override
                    public void onResponse(Call<LoginMainStatus> call, Response<LoginMainStatus> response) {
                        Log.d("RESPONSE", "" + response.toString());
                        if (response.body().getStatus().equalsIgnoreCase("true")) {
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            intent.putExtra("user", userRole);
                            Log.d("ROLE", "" + userRole);
                            Toast.makeText(Login.this, userRole + "login sucessfull.", Toast.LENGTH_SHORT).show();

                            UserDetails userDetails = response.body().getUserDetails();


                            Utils.WriteSharePrefrence(Login.this, Constant.USER_ID, userDetails.getUserId());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERNAME, userDetails.getUserName());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERPHONE, userDetails.getUserPhone());
                            Utils.WriteSharePrefrence(Login.this, Constant.USEREMAIL, userDetails.getUserEmail());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERSTOCK, userDetails.getStock());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERADDRESS, userDetails.getUserAddress());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERAREANAME, userDetails.getUserAreaName());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERLATITUDE, userDetails.getUserLatitude());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERLONGTITUDE, userDetails.getUserLongitude());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERDATETIME, userDetails.getUserDateTime());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERIMAGE, userDetails.getUserImg());
                            Utils.WriteSharePrefrence(Login.this, Constant.USERBALANCE, userDetails.getUserbalance());
                            Log.d("USERID", "" + userDetails.getUserId());

                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginMainStatus> call, Throwable t) {
                        Toast.makeText(Login.this, "fail", Toast.LENGTH_SHORT).show();
                        Log.d("NEWLOg", "" + t.toString());
                    }
                });
                Log.d("CALL", "" + call);
                Log.d("HASHMAP", "" + hashMap);
                break;
            case R.id.activity_login_signup:
                HashMap<String, String> hashMapSingup = new HashMap<>();
                hashMapSingup.put(USERTYPE, userRole);
                startActivityData(SignUp.class, hashMapSingup);
                break;
            case R.id.activity_login_forgotpassword:
                Intent intent = new Intent(Login.this, forgot_password.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.activity_login_signwithother:
                Intent signother = new Intent(Login.this, Dashboard.class);
                signother.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(signother);
        }
    }
}

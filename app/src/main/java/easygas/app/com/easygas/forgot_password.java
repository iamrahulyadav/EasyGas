package easygas.app.com.easygas;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import easygas.app.com.easygas.Retrofit.ApiClient;
import easygas.app.com.easygas.Retrofit.ApiInterface;
import easygas.app.com.easygas.Retrofit.ChangePasswordResponse;
import easygas.app.com.easygas.Retrofit.DataForgotPassword;
import easygas.app.com.easygas.Retrofit.ForgotPassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class forgot_password extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivCancel;
    public static List<DataForgotPassword> dataForgotPasswords;
    private EditText edEmail, etOtp, etNewPass, etConPass;
    private TextView tvSubmit, tvSubmitCode, tvResetPass;
    public String strapiOtp, strEtOtp;
    private Dialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
    }

    public void init() {
        ivCancel = (ImageView) findViewById(R.id.ivcancel);
        edEmail = (EditText) findViewById(R.id.activity_forgotpass_email);
        etOtp = (EditText) findViewById(R.id.activity_forgotpass_otp);
        etNewPass = (EditText) findViewById(R.id.activity_forgotpass_newpassword);
        etConPass = (EditText) findViewById(R.id.activity_forgotpass_confirmpasspassword);
        tvSubmit = (TextView) findViewById(R.id.activity_forgotpass_resetpass);
        tvSubmitCode = (TextView) findViewById(R.id.activity_forgotpass_submitcode);
        tvResetPass = (TextView) findViewById(R.id.activity_forgotpass_submitpass);

        edEmail.setVisibility(View.VISIBLE);
        tvSubmit.setVisibility(View.VISIBLE);
        etOtp.setVisibility(View.GONE);
        etNewPass.setVisibility(View.GONE);
        etConPass.setVisibility(View.GONE);
        tvSubmitCode.setVisibility(View.GONE);
        tvResetPass.setVisibility(View.GONE);

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgot_password.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        tvSubmit.setOnClickListener(this);
        tvSubmitCode.setOnClickListener(this);
        tvResetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_forgotpass_resetpass:
                dataForgotPasswords = new ArrayList<>();
                String email = edEmail.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("email", email);

                pd = new Dialog(this);
                pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                pd.setContentView(R.layout.dialog_loading);
                pd.setCancelable(false);
                pd.show();

                ApiInterface apiSerApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ForgotPassword> call = apiSerApiInterface.forgotpassword(hashMap);
                call.enqueue(new Callback<ForgotPassword>() {

                    @Override
                    public void onResponse(Call<ForgotPassword> call, Response<ForgotPassword> response) {
                        pd.dismiss();
                        if (response.body().getStatus().equalsIgnoreCase("true")) {
                            Toast.makeText(forgot_password.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            DataForgotPassword data = response.body().getDataForgotPassword();

                            strapiOtp = data.getCode();
                            Log.d("DATA", "" + strapiOtp);

                            edEmail.setVisibility(View.GONE);
                            tvSubmit.setVisibility(View.GONE);
                            etOtp.setVisibility(View.VISIBLE);
                            etNewPass.setVisibility(View.GONE);
                            etConPass.setVisibility(View.GONE);
                            tvSubmitCode.setVisibility(View.VISIBLE);
                            tvResetPass.setVisibility(View.GONE);

                        } else {
                            final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(forgot_password.this);

                            dialogBuilder
                                    .withTitle("Wrong Email")
                                    .withMessage(response.body().getMsg())
                                    .withButton1Text("OK")
                                    .withEffect(Effectstype.Fall)
                                    .setButton1Click(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogBuilder.dismiss();
                                        }
                                    })
                                    .show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ForgotPassword> call, Throwable t) {
                        Log.d("ERROR", "" + t.toString());
                    }
                });
                break;
            case R.id.activity_forgotpass_submitcode:
                strEtOtp = etOtp.getText().toString();
                Log.d("DATA1", "" + strapiOtp);

                pd = new Dialog(this);
                pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                pd.setContentView(R.layout.dialog_loading);
                pd.setCancelable(false);
                pd.show();

                if (strEtOtp.equalsIgnoreCase(strapiOtp)) {
                    pd.dismiss();

                    edEmail.setVisibility(View.GONE);
                    tvSubmit.setVisibility(View.GONE);
                    etOtp.setVisibility(View.GONE);
                    etNewPass.setVisibility(View.VISIBLE);
                    etConPass.setVisibility(View.VISIBLE);
                    tvSubmitCode.setVisibility(View.GONE);
                    tvResetPass.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(this, "Wrong Otp..", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.activity_forgotpass_submitpass:
                pd = new Dialog(this);
                pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                pd.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                pd.setContentView(R.layout.dialog_loading);
                pd.setCancelable(false);
                pd.show();

                String changepassemail = etNewPass.getText().toString();
                String newpass = etConPass.getText().toString();

                HashMap<String, String> mapChangePass = new HashMap<>();
                mapChangePass.put("email",changepassemail);
                mapChangePass.put("new_password",newpass);

                Log.d("email",""+changepassemail);
                Log.d("new_password",""+newpass);

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<ChangePasswordResponse> callchangepass = apiInterface.changepassword(mapChangePass);

                callchangepass.enqueue(new Callback<ChangePasswordResponse>() {
                    @Override
                    public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                        pd.dismiss();
                        Log.d("RESPONSE",""+response.body().getStatus());
                        if (response.body().getStatus().equalsIgnoreCase("true"))
                        {
                            final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(forgot_password.this);
                            dialogBuilder
                                    .withTitle("Password Change")
                                    .withMessage(response.body().getMsg())
                                    .withButton1Text("OK")
                                    .withEffect(Effectstype.Fall)
                                    .setButton1Click(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogBuilder.dismiss();
                                            Intent intent = new Intent(forgot_password.this,Login.class);
                                            startActivity(intent);

                                        }
                                    })
                                    .show();
                        }
                        else
                        {
                            final NiftyDialogBuilder dialogBuilder= NiftyDialogBuilder.getInstance(forgot_password.this);
                            dialogBuilder
                                    .withTitle("Password Change")
                                    .withMessage(response.body().getMsg())
                                    .withButton1Text("OK")
                                    .withEffect(Effectstype.Fall)
                                    .setButton1Click(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialogBuilder.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                        Log.d("ERRROR",""+t.toString());
                    }
                });
                break;
        }
    }
}

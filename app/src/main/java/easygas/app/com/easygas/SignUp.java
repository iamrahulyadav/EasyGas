package easygas.app.com.easygas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import easygas.app.com.easygas.Retrofit.ApiClient;
import easygas.app.com.easygas.Retrofit.ApiInterface;
import easygas.app.com.easygas.Retrofit.Model.ResiterMainStatus;
import easygas.app.com.easygas.Retrofit.Model.SignUp.AreaDetails;
import easygas.app.com.easygas.Retrofit.Model.SignUp.AreaMainStatus;
import easygas.app.com.easygas.Retrofit.Model.SignUp.Area_List;
import easygas.app.com.easygas.Utils.GetFilePathFromDevice;
import easygas.app.com.easygas.Widget.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static easygas.app.com.easygas.Utils.Constant.USERTYPE;
import static easygas.app.com.easygas.Utils.Utils.getFileToBase64;
import static easygas.app.com.easygas.Utils.Utils.isValidEmail;

/**
 * Created by archirayan on 22-Apr-17.
 */

public class SignUp extends BaseActivity implements View.OnClickListener {
    private String userRole;
    private Spinner spinner, spArea;
    private List<AreaDetails> areaDetails;
    private List<Area_List> areadetailsinfo;
    private List<String> cityNameArray;
    private List<String> cityIdArray;
    private List<String> areanameArray;
    private CircleImageView userProfileIv;
    private static final int SELECT_PICTURE = 100;
    private String userImgPath;
    public EditText nameEdt, emailEdt, addressEdt, phEdt, pwdEdt, rePwdEdt;
    public String name, email, address, phNo, pwd, rePwd, area_id, city_id;
    public TextView submitTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        spinner = (Spinner) findViewById(R.id.activity_signup_spCity);
        spArea = (Spinner) findViewById(R.id.sparea);
        userProfileIv = (CircleImageView) findViewById(R.id.activity_signup_image);
        nameEdt = (EditText) findViewById(R.id.activity_signup_name);
        emailEdt = (EditText) findViewById(R.id.activity_signup_email);
        addressEdt = (EditText) findViewById(R.id.activity_signup_address);
        phEdt = (EditText) findViewById(R.id.activity_signup_phno);
        pwdEdt = (EditText) findViewById(R.id.activity_signup_pwd);
        rePwdEdt = (EditText) findViewById(R.id.activity_signup_repwd);
        submitTv = (TextView) findViewById(R.id.activity_signup_submit);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_id = areaDetails.get(position).getCityId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        userProfileIv.setOnClickListener(this);
        submitTv.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                userImgPath = GetFilePathFromDevice.getPath(this, data.getData());
                userProfileIv.setImageURI(Uri.fromFile(new File(userImgPath)));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getExtras() != null) {
            userRole = getIntent().getExtras().getString(USERTYPE);
        }
        areaDetails = new ArrayList<>();
        areadetailsinfo = new ArrayList<>();
        cityNameArray = new ArrayList<>();
        cityIdArray = new ArrayList<>();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AreaMainStatus> call = apiService.getArea();
        call.enqueue(new Callback<AreaMainStatus>() {
            @Override
            public void onResponse(Call<AreaMainStatus> call, Response<AreaMainStatus> response) {
                areaDetails = response.body().getData();
                areanameArray  = new ArrayList<>();
                for (AreaDetails details : response.body().getData()) {
                    cityNameArray.add(details.getCityName());
                    cityIdArray.add(details.getCityId());
                    areadetailsinfo = details.getArea_list();
                    for (Area_List arealist : areadetailsinfo) {
                        areanameArray.add(arealist.getAreaName());
                    }
                }
                Log.d("AREa", "" + areanameArray);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUp.this, android.R.layout.simple_spinner_item, cityNameArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                ArrayAdapter<String> adapterarea = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_item, areanameArray);
                adapterarea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spArea.setAdapter(adapterarea);
            }

            @Override
            public void onFailure(Call<AreaMainStatus> call, Throwable t) {
                Log.d("Throwable", "" + t);
            }
        });
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_signup_image:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                break;
            case R.id.activity_signup_submit:
                name = nameEdt.getText().toString();
                email = emailEdt.getText().toString();
                address = addressEdt.getText().toString();
                phNo = phEdt.getText().toString();
                pwd = pwdEdt.getText().toString();
                rePwd = rePwdEdt.getText().toString();

                if (pwd.equals(rePwd) && isValidEmail(email)) {

                    String base64Str = getFileToBase64(userImgPath);

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("name", name);
                    hashMap.put("phone", phNo);
                    hashMap.put("email", email);
                    hashMap.put("password", pwd);
                    hashMap.put("address", address);
                    hashMap.put("area_id", name);
                    hashMap.put("role", userRole);
                    hashMap.put("img", base64Str);

                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);
                    Call<ResiterMainStatus> call = apiService.getregister(hashMap);
                    call.enqueue(new Callback<ResiterMainStatus>() {
                        @Override
                        public void onResponse(Call<ResiterMainStatus> call, Response<ResiterMainStatus> response) {
                            toast(response.body().getStatus());
                        }

                        @Override
                        public void onFailure(Call<ResiterMainStatus> call, Throwable t) {

                        }
                    });

                } else {
                    toast(getString(R.string.error_invalid_user));
                }
                break;
        }
    }
}

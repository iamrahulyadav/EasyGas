package easygas.app.com.easygas;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import easygas.app.com.easygas.Widget.BaseActivity;

import static easygas.app.com.easygas.Utils.Constant.DELIVERY_ROLE;
import static easygas.app.com.easygas.Utils.Constant.USERTYPE;
import static easygas.app.com.easygas.Utils.Constant.USER_ROLE;
import static easygas.app.com.easygas.Utils.Constant.VENDOR_ROLE;

/**
 * Created by archirayan on 21-Apr-17.
 */

public class Dashboard extends BaseActivity implements View.OnClickListener {

    TextView findAGasTv,vendorTv,deliveryBoyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        findAGasTv = (TextView) findViewById(R.id.activity_dashboard_find_a_gas);
        vendorTv = (TextView)findViewById(R.id.activity_dashboard_vendor);
        deliveryBoyTv = (TextView)findViewById(R.id.activity_dashboard_delivery_boy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        findAGasTv.setOnClickListener(this);
        vendorTv.setOnClickListener(this);
        deliveryBoyTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_dashboard_find_a_gas:

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(USERTYPE, USER_ROLE);
                startActivityData(Login.class, hashMap);
                break;
            case R.id.activity_dashboard_delivery_boy:

                HashMap<String, String> hashMapDeliverty = new HashMap<>();
                hashMapDeliverty.put(USERTYPE, DELIVERY_ROLE);
                startActivityData(Login.class, hashMapDeliverty);
                break;
            case R.id.activity_dashboard_vendor:

                HashMap<String, String> hashMapVendor = new HashMap<>();
                hashMapVendor.put(USERTYPE, VENDOR_ROLE);
                startActivityData(Login.class, hashMapVendor);
                break;
        }
    }
}

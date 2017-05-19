package easygas.app.com.easygas.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import easygas.app.com.easygas.R;

public class fragment_mywallet extends Fragment {

    private TextView balancetv,addmoneyTv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_mywallet, container, false);

        balancetv = (TextView)view.findViewById(R.id.fragment_mywallet_balancetv);
        addmoneyTv = (TextView)view.findViewById(R.id.fragment_mywallet_addmoneytv);
        init();
        return view;
    }

    public void init()
    {
        balancetv.setText("CFA:2000");
    }
}

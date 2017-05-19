package easygas.app.com.easygas.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import easygas.app.com.easygas.R;

public class fragment_map extends Fragment implements View.OnClickListener {
    private LinearLayout layoutselectgas,layoutlist;
    private TextView tvMap,tvList,tvSelectGas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        layoutselectgas = (LinearLayout)view.findViewById(R.id.layout_selectgas);
        layoutlist = (LinearLayout)view.findViewById(R.id.layout_list);
        tvMap = (TextView) view.findViewById(R.id.fragment_map_tvmap);
        tvList = (TextView) view.findViewById(R.id.fragment_map_tvlist);
        tvSelectGas = (TextView) view.findViewById(R.id.fragment_map_tvselectgas);
        init();
        return view;
    }

    public void init()
    {
        tvMap.setOnClickListener(this);
        tvList.setOnClickListener(this);
        tvSelectGas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.fragment_map_tvmap:
                layoutselectgas.setVisibility(View.GONE);
                layoutlist.setVisibility(View.GONE);
                break;
            case R.id.fragment_map_tvlist:
                layoutselectgas.setVisibility(View.GONE);
                layoutlist.setVisibility(View.VISIBLE);
                break;
            case R.id.fragment_map_tvselectgas:
                layoutlist.setVisibility(View.GONE);
                layoutselectgas.setVisibility(View.VISIBLE);
        }
    }
}

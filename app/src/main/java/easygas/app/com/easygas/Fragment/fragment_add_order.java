package easygas.app.com.easygas.Fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import easygas.app.com.easygas.R;
import easygas.app.com.easygas.Retrofit.ApiClient;
import easygas.app.com.easygas.Retrofit.ApiInterface;
import easygas.app.com.easygas.Retrofit.GasDetail;
import easygas.app.com.easygas.Retrofit.GasDetailMedium;
import easygas.app.com.easygas.Retrofit.GasDetailSmall;
import easygas.app.com.easygas.Retrofit.Model.GetOrderDetailMain;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class fragment_add_order extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener {
    public Toolbar toolbar;
    public ImageView imageView1;
    public TextView tvTitle;
    public Spinner spSelectProvider, spSelectGasKg;
    public List<String> gasCategoryName;
    public List<GasDetail> gasBigSize;
    public List<GasDetailMedium> gasMediumSize;
    public List<GasDetailSmall> gasSmallSize;
    public List<String> gasSmallSizeNew, gasMediumSizeNew, gasBigSizeNew;
    RadioGroup rgGroup;
    public int pos, pos1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_add_order, container, false);

        spSelectProvider = (Spinner) view.findViewById(R.id.fragment_add_oreder_spselectprovider);
        spSelectGasKg = (Spinner) view.findViewById(R.id.fragment_add_order_spselectgas);
        rgGroup = (RadioGroup) view.findViewById(R.id.radiogroup);

        init();
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                pos = rgGroup.indexOfChild(view.findViewById(i));
                pos1 = rgGroup.indexOfChild(view.findViewById(rgGroup.getCheckedRadioButtonId()));
            }
        });
        return view;
    }

    public View init() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_main_toolbar);
        imageView1 = (ImageView) toolbar.findViewById(R.id.imageView1);
        tvTitle = (TextView) toolbar.findViewById(R.id.tvTitle);
        spSelectProvider.setOnItemSelectedListener(this);
        spSelectGasKg.setOnItemSelectedListener(this);

        return null;
    }


    @Override
    public void onStart() {
        super.onStart();

        gasCategoryName = new ArrayList<>();
        gasSmallSize = new ArrayList<>();
        gasMediumSize = new ArrayList<>();
        gasBigSize = new ArrayList<>();
        gasBigSizeNew = new ArrayList<>();
        gasMediumSizeNew = new ArrayList<>();
        gasSmallSizeNew = new ArrayList<>();


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetOrderDetailMain> call = apiService.getOrderDetail();
        call.enqueue(new Callback<GetOrderDetailMain>() {
            @Override
            public void onResponse(Call<GetOrderDetailMain> call, Response<GetOrderDetailMain> response) {
                Log.d("RESPONSE", "" + response.body().getMsg());

                gasSmallSize = response.body().getSmallGasDetails();
                gasBigSize = response.body().getBigGasDetails();
                gasMediumSize = response.body().getMediumGasDetails();
                for (GasDetailSmall gasSmallDetail : response.body().getSmallGasDetails()) {
                    gasCategoryName.add(gasSmallDetail.getGascategoryName());
                }
                Log.d("GASDETAIL", "" + gasSmallSize);
                ArrayAdapter<String> adapterGasCategoryName = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasCategoryName);
                adapterGasCategoryName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectProvider.setAdapter(adapterGasCategoryName);
            }

            @Override
            public void onFailure(Call<GetOrderDetailMain> call, Throwable t) {
                Log.d("ERROR", "" + t.toString());
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String strselectedprovider = String.valueOf(spSelectProvider.getSelectedItem());
        if (strselectedprovider.contentEquals("FOURNISSEURS")) {
            if (pos1 == 0) {
                for (int i1 = 0; i1 < gasBigSize.size(); i1++) {
                    if (gasBigSize.get(i).getGascategoryName().equalsIgnoreCase("FOURNISSEURS")) {
                        gasBigSizeNew.add(gasBigSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasBigSize);
                }
                ArrayAdapter<String> adapterBig = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasBigSizeNew);
                adapterBig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adapterBig);
            } else if (pos1 == 1) {

                for (int i1 = 0; i1 < gasMediumSize.size(); i1++) {
                    if (gasMediumSize.get(i1).getGascategoryName().equalsIgnoreCase("FOURNISSEURS")) {
                        gasMediumSizeNew.add(gasMediumSize.get(i1).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasMediumSizeNew);
                }
                ArrayAdapter<String> adapterMedium = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasMediumSizeNew);
                adapterMedium.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adapterMedium);
            } else if (pos1 == 2) {
                for (int i1 = 0; i1 < gasSmallSize.size(); i1++) {
                    if (gasSmallSize.get(i1).getGascategoryName().equalsIgnoreCase("FOURNISSEURS")) {
                        gasSmallSizeNew.add(gasSmallSize.get(i1).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasSmallSizeNew);
                }
                ArrayAdapter<String> adaptersmall = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasSmallSizeNew);
                adaptersmall.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adaptersmall);
            }
        }

        if (strselectedprovider.contentEquals("SCTM")) {
            if (pos1 == 0) {
                for (int i1 = 0; i1 < gasBigSize.size(); i1++) {
                    if (gasBigSize.get(i).getGascategoryName().equalsIgnoreCase("SCTM")) {
                        gasBigSizeNew.add(gasBigSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasBigSize);
                }
                ArrayAdapter<String> adapterBig = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasBigSizeNew);
                adapterBig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adapterBig);
            } else if (pos1 == 1) {

                for (int i1 = 0; i1 < gasMediumSize.size(); i1++) {
                    if (gasMediumSize.get(i).getGascategoryName().equalsIgnoreCase("SCTM")) {
                        gasMediumSizeNew.add(gasMediumSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasMediumSizeNew);
                }
                ArrayAdapter<String> adapterMedium = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasMediumSizeNew);
                adapterMedium.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adapterMedium);
            } else if (pos1 == 2) {
                for (int i1 = 0; i1 < gasSmallSize.size(); i1++) {
                    if (gasSmallSize.get(i).getGascategoryName().equalsIgnoreCase("SCTM")) {
                        gasSmallSizeNew.add(gasSmallSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasSmallSizeNew);
                }
                ArrayAdapter<String> adaptersmall = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasSmallSizeNew);
                adaptersmall.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adaptersmall);
            }
        }

        if (strselectedprovider.contentEquals("Tex gaz")) {
            if (pos1 == 0) {
                for (int i1 = 0; i1 < gasBigSize.size(); i1++) {
                    if (gasBigSize.get(i).getGascategoryName().equalsIgnoreCase("Tex gaz")) {
                        gasBigSizeNew.add(gasBigSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasBigSize);
                }
                ArrayAdapter<String> adapterBig = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasBigSizeNew);
                adapterBig.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adapterBig);
            } else if (pos1 == 1) {

                for (int i1 = 0; i1 < gasMediumSize.size(); i1++) {
                    if (gasMediumSize.get(i).getGascategoryName().equalsIgnoreCase("Tex gaz")) {
                        gasMediumSizeNew.add(gasMediumSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasMediumSizeNew);
                }
                ArrayAdapter<String> adapterMedium = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasMediumSizeNew);
                adapterMedium.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adapterMedium);
            } else if (pos1 == 2) {
                for (int i1 = 0; i1 < gasSmallSize.size(); i1++) {
                    if (gasSmallSize.get(i).getGascategoryName().equalsIgnoreCase("Tex gaz")) {
                        gasSmallSizeNew.add(gasSmallSize.get(i).getGascategorySize());
                    }
                    Log.d("gasSmallSizeNew", "" + gasSmallSizeNew);
                }
                ArrayAdapter<String> adaptersmall = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, gasSmallSizeNew);
                adaptersmall.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSelectGasKg.setAdapter(adaptersmall);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}

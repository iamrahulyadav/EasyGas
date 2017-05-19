package easygas.app.com.easygas.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import easygas.app.com.easygas.R;

public class aboutus extends Fragment {

    public Toolbar toolbar;
    public ImageView imageView1;
    public TextView tvTitle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_aboutus, container, false);
        return view;
    }

    public View init()
    {
        toolbar = (Toolbar) getActivity().findViewById(R.id.activity_main_toolbar);
        imageView1 = (ImageView) toolbar.findViewById(R.id.imageView1);
        tvTitle = (TextView)toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("About Us");
        return null;
    }

}

package easygas.app.com.easygas.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import easygas.app.com.easygas.R;

public class fragment_order_list extends Fragment {

    private RecyclerView ordershowRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_order_list, container, false);
        ordershowRecyclerView = (RecyclerView)view.findViewById(R.id.fragment_order_list_recycler);
        init();
        return view;
    }

    public View init()
    {

        return null;
    }

}

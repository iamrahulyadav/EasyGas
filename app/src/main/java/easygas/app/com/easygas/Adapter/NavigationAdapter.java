package easygas.app.com.easygas.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import easygas.app.com.easygas.Model.NavigationDetails;
import easygas.app.com.easygas.R;

/**
 * Created by archirayan on 26-Apr-17.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {
    ArrayList<NavigationDetails> navigationArray;

    public NavigationAdapter(ArrayList<NavigationDetails> navigationArray) {
        this.navigationArray = navigationArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_navigation, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NavigationDetails movie = navigationArray.get(position);
        holder.titleTv.setText(movie.getName());
    }

    @Override
    public int getItemCount() {
        return navigationArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.adapter_navigation_title);
        }
    }
}

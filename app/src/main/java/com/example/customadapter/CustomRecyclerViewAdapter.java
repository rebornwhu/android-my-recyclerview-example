package com.example.customadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chinesechairmen.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Note: before you extend RecyclerView.Adapter, first construct the ViewHolder class.
 * Then you can implement the override methods.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private List<Leader> leaders;

    public CustomRecyclerViewAdapter(ArrayList<Leader> leaders) {
        this.leaders = leaders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * xl The difference between listview view holder is that: here inflated root custom view
         * becomes a parameter of the view holder's constructor, child view lookup is conducted
         * inside view holder's constructor.
         */
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTv.setText(leaders.get(position).getName());
        holder.imageView.setImageResource(leaders.get(position).getImage());
        holder.commentTv.setText(leaders.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textView1) TextView nameTv;
        @Bind(R.id.imageView1) ImageView imageView;
        @Bind(R.id.textView2) TextView commentTv;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public List<Leader> getLeaders() {
        return leaders;
    }
}
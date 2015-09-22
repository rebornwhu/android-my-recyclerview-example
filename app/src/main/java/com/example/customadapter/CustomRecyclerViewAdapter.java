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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getNameTv().setText(leaders.get(position).getName());
        holder.getImageView().setImageResource(leaders.get(position).getImage());
        holder.getCommentTv().setText(leaders.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTv;
        private final ImageView imageView;
        private final TextView commentTv;

        public ViewHolder(View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.textView1);
            imageView = (ImageView) itemView.findViewById(R.id.imageView1);
            commentTv = (TextView) itemView.findViewById(R.id.textView2);
        }

        public TextView getNameTv() {
            return nameTv;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getCommentTv() {
            return commentTv;
        }
    }

}
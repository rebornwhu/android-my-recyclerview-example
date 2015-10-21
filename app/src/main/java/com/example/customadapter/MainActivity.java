package com.example.customadapter;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.chinesechairmen.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    private CustomRecyclerViewAdapter adapter;
    private ArrayList<Leader> leaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        leaders = createDummyData();
        adapter = new CustomRecyclerViewAdapter(leaders);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private ArrayList<Leader> createDummyData() {
        ArrayList<Leader> leaders = new ArrayList<>();

        Resources res = getResources();
        String[] names = res.getStringArray(R.array.names);
        TypedArray images = res.obtainTypedArray(R.array.images);
        String[] comments = res.getStringArray(R.array.comments);
        for (int i = 0; i < 5; i++) {
            leaders.add(new Leader(names[i], images.getResourceId(i, -1), comments[i]));
            /**"-1" is defValue - Value to return if the attribute is not defined or not a
             resource. Possibly it will set the imageview blank if it has to use the default -1*/

            /**The following codes won't work
             images[i] = imagesArray.getResourceId(i, -1);
             leaders.add(new Leader(names[i], images[i], comments[i]) );*/
        }

        return leaders;
    }

    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getLayoutPosition();
            adapter.getLeaders().remove(position);
            adapter.notifyDataSetChanged();
        }
    };

}
package com.example.customadapter;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.View;

import com.example.chinesechairmen.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    private CustomRecyclerViewAdapter adapter;

    private Paint bgPaint;
    private Paint textPaint;
    private float swipeHintTextSize;
    private float swipeTextUnitMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CustomRecyclerViewAdapter(createDummyData());
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        bgPaint = new Paint();
        bgPaint.setColor(ContextCompat.getColor(getBaseContext(), R.color.green));
        textPaint = new Paint();

        Resources res = getResources();
        swipeHintTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, res.getDisplayMetrics());
        textPaint.setTextSize(swipeHintTextSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(ContextCompat.getColor(getBaseContext(), R.color.white));

        swipeTextUnitMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, res.getDisplayMetrics());
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
            if (direction == ItemTouchHelper.RIGHT) {
                int position = viewHolder.getLayoutPosition();
                adapter.getLeaders().remove(position);
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                View view = viewHolder.itemView;

                if (dX > 0) {
                    c.drawRect((float) view.getLeft(), (float) view.getTop(), dX, (float) view.getBottom(), bgPaint);
                    float textYPosition = (float)(view.getTop() + view.getBottom()) / 2 + swipeHintTextSize / 2;
                    c.drawText("Yes It's Me", swipeTextUnitMargin, textYPosition, textPaint);
                }
                else {
                    c.drawRect((float)view.getRight() + dX, (float)view.getTop(), (float)view.getRight(), (float)view.getBottom(), bgPaint);
                    float textYPosition = (float)(view.getTop() + view.getBottom()) / 2 + swipeHintTextSize / 2;
                    c.drawText("More Info", (float) view.getRight() + dX + swipeTextUnitMargin, textYPosition, textPaint);
                }
            }

            // xl: Must have the following line, otherwise it won't execute
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

}
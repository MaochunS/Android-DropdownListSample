package com.maochun.dropdownlistsample;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends AppCompatActivity {

    boolean mShowCoinTokenSelList = false;
    private ListView mListViewItems;
    private Context mContext = this;

    private ImageView mImageViewArrow;
    private ArrayList<Pair<Integer, String>> mDropdownListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewArrow = (ImageView) findViewById(R.id.imageView_cointokensel_downarrow);

        mListViewItems = findViewById(R.id.listview_items);
        mListViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"click item " + position);

                view.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

                Pair<Integer, String>item = mDropdownListItems.get(position);
                TextView tvItemTitle = findViewById(R.id.textView_cointokensel_txt);
                tvItemTitle.setText(item.second);

                ImageView imgView = findViewById(R.id.imageView_cointokensel_coinlogo);
                imgView.setImageResource(item.first);

                onItemSelClick(null);
            }
        });

        for(int i=0; i<10; i++){
            mDropdownListItems.add(new Pair<Integer, String>(R.drawable.dct, "Item " + i));
        }
    }

    public void rotate_Clockwise(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 180f, 0f);
//        rotate.setRepeatCount(10);
        rotate.setDuration(500);
        rotate.start();
    }

    public void rotate_AntiClockwise(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f);
//        rotate.setRepeatCount(10);
        rotate.setDuration(500);
        rotate.start();
    }

    public void onItemSelClick(View v){

        //int listHeight = 5*160;
        //if (Setting.getInstance().mCoinTokenArray.size() * 60 < listHeight)
        //    listHeight = Setting.getInstance().mCoinTokenArray.size() * 160;


        ViewGroup.LayoutParams params = mListViewItems.getLayoutParams();
        params.height = 500;


        ExpandCollapseAnimation animation = null;
        if (mShowCoinTokenSelList){
            Log.i(TAG, "close list");
            rotate_Clockwise(this.mImageViewArrow);
            animation = new ExpandCollapseAnimation(mListViewItems, 500, 1);
            mShowCoinTokenSelList = false;
        }else {
            Log.i(TAG, "show list");
            rotate_AntiClockwise(this.mImageViewArrow);
            animation = new ExpandCollapseAnimation(mListViewItems, 500, 0);
            mShowCoinTokenSelList = true;

        }
        mListViewItems.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mShowCoinTokenSelList){
                    final DropdownListItemAdapter adapter = new DropdownListItemAdapter(mContext, mDropdownListItems);
                    mListViewItems.setAdapter(adapter);
                }else{
                    mListViewItems.setAdapter(null);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}

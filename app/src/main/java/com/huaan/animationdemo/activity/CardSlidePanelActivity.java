package com.huaan.animationdemo.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.huaan.animationdemo.R;
import com.huaan.animationdemo.javabean.PersonBean;

import java.util.ArrayList;
import java.util.List;

public class CardSlidePanelActivity extends AppCompatActivity {

    private List<PersonBean> personList = new ArrayList<>();
    private ImageView mImgTest;
    private float downX,downY; //按下位置的x,y
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_slide_panel);
        initView();
    }


    private void initView() {
        mImgTest = (ImageView) findViewById(R.id.img_test);

        dragView(mImgTest);

        //点击放大
        mImgTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickToEnlarge(mImgTest);
            }
        });
    }

    /**
     * 放大View
     */
    private void clickToEnlarge(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"scaleY",1f,2f).setDuration(10000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view,"scaleX",1f,2f).setDuration(10000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f).setDuration(10000);
        animatorSet.playTogether(animator,animator2,animator3);
        animatorSet.start();
    }

    /**
     * 实现View可拖动
     * @param view 需要拖动的View
     */
    private void dragView(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getX(); //原始位置x
                        downY = motionEvent.getY(); //原始位置y
//                        Log.d("motionEvent", "onTouch: "+" downX" + downX + "downY" + downY);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int distanceX = (int) (motionEvent.getX() - downX);
                        int distanceY = (int) (motionEvent.getY() - downY);
//                        Log.d("motionEvent", "onTouch: "+" moveX" + distanceX + "moveY" + distanceY);
                        if(distanceX !=0 || distanceY!=0){
                            int l = view.getLeft() + distanceX;
                            int t = view.getTop() + distanceY;
                            int r = l+view.getWidth();
                            int b = t+view.getHeight();
                            view.layout(l,t,r,b);//l,t,r,b
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
}

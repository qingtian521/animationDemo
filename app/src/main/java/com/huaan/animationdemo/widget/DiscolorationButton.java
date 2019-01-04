package com.huaan.animationdemo.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * 点击变色的按钮
 */
public class DiscolorationButton extends AppCompatButton {

    /**
     * 动画集合类
     */
    private AnimatorSet animatorSet = new AnimatorSet();

    private ObjectAnimator animator;

    public DiscolorationButton(Context context) {
        super(context);
        init();
    }

    public DiscolorationButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiscolorationButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        set_anim_button_change();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim();
            }
        });
    }

    private void set_anim_button_change(){
        animator = ObjectAnimator.ofFloat(this,"alpha",1f,0.5f,1f).setDuration(200);
        animatorSet.play(animator);
    }

    private void startAnim(){
        animatorSet.start();
    }
}

package com.huaan.animationdemo.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 动画提交按钮
 */
public class AnimationButton extends View {
    private static String TAG = "AnimationButton";

    private Paint paint;

    private Paint textPaint;

    /**
     * view的宽度
     */
    private int mWidth;

    /**
     * view的高度
     */
    private int mHeight;

    /**
     * 根据view的大小设置成矩形
     */

    /**
     * 圆角半径
     */
    private int circleAngle;

    /**
     * 圆角矩形框
     */
    private RectF rectf = new RectF();

    /**
     * 显示文字
     */
    private RectF textRect = new RectF();

    /**
     * 矩形变成圆需要移动的距离
     */
    private int default_two_circle_distance;

    /**
     * 两圆圆心之间的距离
     */
    private int two_circle_distance;

    private ValueAnimator rect_to_circle_anim; //矩形变圆动画

    /**
     * 动画集
     */
    private AnimatorSet animatorSet = new AnimatorSet();

    /**
     * 路径--用来获取对勾的路径
     */
    private Path path = new Path();

    /**
     * 取路径的长度
     */
    private PathMeasure pathMeasure;

    /**
     * 是否绘制对勾
     */
    private boolean isOk = false;

    /**
     * 对勾（√）画笔
     */
    private Paint okPaint;

    private ValueAnimator animator_draw_ok; //矩形变圆动画

    /**
     * 对路径处理实现绘制动画效果
     */
    private PathEffect effect;

    /**
     * 向上平移的距离
     */
    private int move_distance = 400;

    /**
     * 向上平移的动画
     */
    private ObjectAnimator translation_up_amin;

    /**
     * 动画时间
     */
    private long duration = 5000;

    public AnimationButton(Context context) {
        super(context);
    }

    public AnimationButton(Context context,@Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AnimationButton(Context context,@Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setColor(Color.parseColor("#00caca"));
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(40);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        okPaint = new Paint();
        okPaint.setStrokeWidth(10);
        okPaint.setStyle(Paint.Style.STROKE);
        okPaint.setAntiAlias(true);
        okPaint.setColor(Color.WHITE);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                animatorSet.start();
            }
        });

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                reset();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        circleAngle = (int) (mHeight * 0.5); //矩形圆角半径
        default_two_circle_distance = (w - h) / 2;
        initOk();
        initAmin();
    }

    private void initAmin() {
        set_rect_to_circle_animation();
        set_draw_ok_animation();
        set_move_to_up_animation();
        animatorSet.play(translation_up_amin).after(rect_to_circle_anim).before(animator_draw_ok);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        set_rect(canvas);
        drawText(canvas);
        if(isOk) canvas.drawPath(path,okPaint);
    }

    /**
     * 绘制圆角矩形
     * @param canvas
     */
    private void set_rect(Canvas canvas){
        rectf.top = 0;
        rectf.left = two_circle_distance;
        rectf.right = mWidth - two_circle_distance;
        rectf.bottom = mHeight;
        canvas.drawRoundRect(rectf,circleAngle,circleAngle,paint);
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        textRect.left = 0;
        textRect.top = 0;
        textRect.right = mWidth;
        textRect.bottom = mHeight;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (int) ((textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2);
        //文字绘制到整个布局的中心位置
        canvas.drawText("完成", textRect.centerX(), baseline, textPaint);
    }

    /**
     * 矩形变圆动画
     */
    private void set_rect_to_circle_animation(){
        rect_to_circle_anim = ValueAnimator.ofInt(0,default_two_circle_distance).setDuration(duration);
        rect_to_circle_anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.i(TAG, "onAnimationUpdate: " + valueAnimator.getAnimatedValue());
                two_circle_distance = (int) valueAnimator.getAnimatedValue();
                int alpha = 255 - (two_circle_distance * 255) / default_two_circle_distance;
                textPaint.setAlpha(alpha);
                invalidate();
            }
        });
    }

    /**
     * 绘制对勾动画
     */
    private void set_draw_ok_animation(){
        animator_draw_ok = ValueAnimator.ofFloat(1,0).setDuration(duration);
        animator_draw_ok.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                isOk = true;
                float value = (Float) valueAnimator.getAnimatedValue();

                effect = new DashPathEffect(new float[]{pathMeasure.getLength(), pathMeasure.getLength()}, value * pathMeasure.getLength());
                okPaint.setPathEffect(effect);
                invalidate();
            }
        });
    }

    /**
     * 绘制对勾
     */
    private void initOk() {
        //对勾的路径
        path.moveTo(default_two_circle_distance + mHeight / 8 * 3, mHeight / 2);
        path.lineTo(default_two_circle_distance + mHeight / 2, mHeight / 5 * 3);
        path.lineTo(default_two_circle_distance + mHeight / 3 * 2, mHeight / 5 * 2);

        pathMeasure = new PathMeasure(path, true);
    }

    /**
     * 设置view上移的动画
     */
    private void set_move_to_up_animation() {
       translation_up_amin = ObjectAnimator.ofFloat(this,"translationY",
               getTranslationY(),getTranslationY() - move_distance).setDuration(duration);
        translation_up_amin.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    /**
     * 动画还原
     */
    public void reset() {
        isOk = false;
        two_circle_distance = 0;
        default_two_circle_distance = (mWidth - mHeight) / 2;
        textPaint.setAlpha(255);
        setTranslationY(getTranslationY() + move_distance);
        invalidate();
    }
}

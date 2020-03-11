package com.haanhgs.app.animatordemo.controller;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.haanhgs.app.animatordemo.R;
import static com.haanhgs.app.animatordemo.controller.CardState.Back;
import static com.haanhgs.app.animatordemo.controller.CardState.Front;

public class Controller {

    private Animation animFadeIn;
    private Animation animFadeOut;
    private Animation animRotateLeft;
    private Animation animRotateRight;
    private Animator flipLeftIn;
    private Animator flipLeftOut;
    private Animator flipRightIn;
    private Animator flipRightOut;

    private final Activity activity;
    private CardState cardState = Front;
    private final OnAnimation onAnimation;

    private void requestPortraitMode(){
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void hideStatuBar(){
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initAnimation(){
        animFadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        animRotateLeft = AnimationUtils.loadAnimation(activity, R.anim.rotate_left);
        animRotateRight = AnimationUtils.loadAnimation(activity, R.anim.rotate_right);
    }

    private void initAnimator(){
        flipLeftIn = AnimatorInflater.loadAnimator(activity, R.animator.flip_left_in);
        flipLeftOut = AnimatorInflater.loadAnimator(activity, R.animator.flip_left_out);
        flipRightIn = AnimatorInflater.loadAnimator(activity, R.animator.flip_right_in);
        flipRightOut = AnimatorInflater.loadAnimator(activity, R.animator.flip_right_out);
    }

    public Controller(Activity activity, OnAnimation onAnimation) {
        this.activity = activity;
        this.onAnimation = onAnimation;
        requestPortraitMode();
        hideStatuBar();
        initAnimation();
        initAnimator();
    }

    private void renderImage(ImageView imageView){
        imageView.setImageResource(cardState == Front ? R.drawable.ace : R.drawable.ace_back);
    }

    private void flipCard(){
        cardState = cardState == Front ? Back : Front;
    }

    public void handleFadeIn(ImageView imageView){
        onAnimation.animationStart();
        animFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                animFadeIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        onAnimation.animationEnd();
                    }
                });
                renderImage(imageView);
                animFadeIn.setDuration(2000);
                imageView.startAnimation(animFadeIn);
            }
        });
        flipCard();
        animFadeOut.setDuration(2000);
        imageView.startAnimation(animFadeOut);
    }

    public void handleRotate(ImageView imageView){
        onAnimation.animationStart();
        animRotateLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                animRotateRight.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        onAnimation.animationEnd();
                    }
                });
                renderImage(imageView);
                animFadeIn.setDuration(2000);
                imageView.startAnimation(animRotateRight);
            }
        });
        cardState = cardState == Front ? Back : Front;
        animFadeOut.setDuration(2000);
        imageView.startAnimation(animRotateLeft);
    }

    private void setupViewport(ImageView imageView){
        float scale = activity.getResources().getDisplayMetrics().density;
        imageView.setCameraDistance(6500 * scale);
    }

    public void handleFlipLeft(ImageView imageView){
        setupViewport(imageView);
        onAnimation.animationStart();
        flipLeftOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                renderImage(imageView);
                flipLeftIn.setTarget(imageView);
                flipLeftIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        onAnimation.animationEnd();
                    }
                });
                flipLeftIn.start();
            }
        });
        flipCard();
        flipLeftOut.setTarget(imageView);
        flipLeftOut.start();
    }

    public void handleFlipRight(ImageView imageView){
        setupViewport(imageView);
        onAnimation.animationStart();
        flipRightOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                renderImage(imageView);
                flipRightIn.setTarget(imageView);
                flipRightIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        onAnimation.animationEnd();
                    }
                });
                flipRightIn.start();
            }
        });
        flipCard();
        flipRightOut.setTarget(imageView);
        flipRightOut.start();
    }

}

package com.haanhgs.app.animatordemo.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.haanhgs.app.animatordemo.R;

public class Repo {

    private Animation animFadeIn;
    private Animation animFadeOut;
    private Animation animRotateLeft;
    private Animation animRotateRight;
    private Animator flipLeftIn;
    private Animator flipLeftOut;
    private Animator flipRightIn;
    private Animator flipRightOut;
    private final Context context;
    private final OnAnimation onAnimation;

    private void initAnimation(){
        animFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        animRotateLeft = AnimationUtils.loadAnimation(context, R.anim.rotate_left);
        animRotateRight = AnimationUtils.loadAnimation(context, R.anim.rotate_right);
    }

    private void initAnimator(){
        flipLeftIn = AnimatorInflater.loadAnimator(context, R.animator.flip_left_in);
        flipLeftOut = AnimatorInflater.loadAnimator(context, R.animator.flip_left_out);
        flipRightIn = AnimatorInflater.loadAnimator(context, R.animator.flip_right_in);
        flipRightOut = AnimatorInflater.loadAnimator(context, R.animator.flip_right_out);
    }

    public Repo(Context context) {
        this.context = context;
        onAnimation = (OnAnimation)context;
        initAnimation();
        initAnimator();
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
                onAnimation.animationBetween();
                animFadeIn.setDuration(2000);
                imageView.startAnimation(animFadeIn);
            }
        });
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
                onAnimation.animationBetween();
                animFadeIn.setDuration(2000);
                imageView.startAnimation(animRotateRight);
            }
        });
        animFadeOut.setDuration(2000);
        imageView.startAnimation(animRotateLeft);
    }

    public void setupViewport(ImageView imageView){
        float scale = context.getResources().getDisplayMetrics().density;
        imageView.setCameraDistance(6500 * scale);
    }

    //remove animator listener when animator ends or else it will mix up with later animator
    public void handleFlipLeft(ImageView imageView){
        onAnimation.animationStart();
        flipLeftOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation.removeAllListeners();
                flipLeftIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        onAnimation.animationEnd();
                        animation.removeAllListeners();
                    }
                });
                onAnimation.animationBetween();
                flipLeftIn.setTarget(imageView);
                flipLeftIn.start();
            }
        });
        flipLeftOut.setTarget(imageView);
        flipLeftOut.start();
    }

    //remove animator listener when animator ends or else it will mix up with later animator
    public void handleFlipRight(ImageView imageView){
        onAnimation.animationStart();
        flipRightOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animation.removeAllListeners();
                flipRightIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        animation.removeAllListeners();
                        onAnimation.animationEnd();
                    }
                });
                onAnimation.animationBetween();
                flipRightIn.setTarget(imageView);
                flipRightIn.start();
            }
        });
        flipRightOut.setTarget(imageView);
        flipRightOut.start();
    }

}

package com.haanhgs.app.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.bnAnimatorLeft)
    Button bnAnimatorLeft;
    @BindView(R.id.bnAnimatorRight)
    Button bnAnimatorRight;
    @BindView(R.id.bnAnimationFade)
    Button bnAnimationFade;
    @BindView(R.id.bnAnimationRotate)
    Button bnAnimationRotate;

    private Animation animFadeIn;
    private Animation animFadeOut;
    private Animation animRotateLeft;
    private Animation animRotateRight;

    private Animator flipLeftIn;
    private Animator flipLeftOut;
    private Animator flipRightIn;
    private Animator flipRightOut;

    private void requestPortraitMode(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private void initAnimation(){
        animFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        animRotateLeft = AnimationUtils.loadAnimation(this, R.anim.rotate_left);
        animRotateRight = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
    }

    private void initAnimator(){
        flipLeftIn = AnimatorInflater.loadAnimator(this, R.animator.flip_left_in);
        flipLeftOut = AnimatorInflater.loadAnimator(this, R.animator.flip_left_out);
        flipRightIn = AnimatorInflater.loadAnimator(this, R.animator.flip_right_in);
        flipRightOut = AnimatorInflater.loadAnimator(this, R.animator.flip_right_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestPortraitMode();
        initAnimation();
        initAnimator();
    }

    @OnClick({R.id.bnAnimatorLeft, R.id.bnAnimatorRight, R.id.bnAnimationFade, R.id.bnAnimationRotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnAnimatorLeft:
                break;
            case R.id.bnAnimatorRight:
                break;
            case R.id.bnAnimationFade:
                break;
            case R.id.bnAnimationRotate:
                break;
        }
    }
}

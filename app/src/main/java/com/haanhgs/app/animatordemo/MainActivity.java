package com.haanhgs.app.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
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
import static com.haanhgs.app.animatordemo.CardState.Back;
import static com.haanhgs.app.animatordemo.CardState.Face;

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

    private CardState cardState = Face;


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

    //Glide not work well with animation pivot, need checking
    private void flipImage(){
        if (cardState == Face){
           imageView.setImageResource(R.drawable.ace);
        }else {
            imageView.setImageResource(R.drawable.ace_back);
        }
    }

    private void disableButtons(){
        bnAnimationFade.setEnabled(false);
        bnAnimationRotate.setEnabled(false);
        bnAnimatorLeft.setEnabled(false);
        bnAnimatorRight.setEnabled(false);
    }

    private void enableButtons(){
        bnAnimationFade.setEnabled(true);
        bnAnimationRotate.setEnabled(true);
        bnAnimatorLeft.setEnabled(true);
        bnAnimatorRight.setEnabled(true);
    }

    private void handleFade(){
        disableButtons();
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
                        enableButtons();
                    }
                });
                flipImage();
                animFadeIn.setDuration(2000);
                imageView.startAnimation(animFadeIn);
            }
        });
        cardState = cardState == Face? Back : Face;
        animFadeOut.setDuration(2000);
        imageView.startAnimation(animFadeOut);
    }

    private void handleRotate(){
        disableButtons();
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
                        enableButtons();
                    }
                });
                flipImage();
                imageView.startAnimation(animRotateRight);
            }
        });
        cardState = cardState == Face? Back : Face;
        imageView.startAnimation(animRotateLeft);
    }

    private void setupViewport(){
        float scale = getResources().getDisplayMetrics().density;
        imageView.setCameraDistance(6500 * scale);
    }

    private void handleOpenLeft(){
        setupViewport();
        disableButtons();
        flipLeftOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flipImage();
                flipLeftIn.setTarget(imageView);
                flipLeftIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        enableButtons();
                    }
                });
                flipLeftIn.start();
            }
        });
        flipLeftOut.setTarget(imageView);
        cardState = cardState == Face ? Back : Face;
        flipLeftOut.start();
    }

    private void handleOpenRight(){
        setupViewport();
        disableButtons();
        flipRightOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                flipImage();
                flipRightIn.setTarget(imageView);
                flipRightIn.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        enableButtons();
                    }
                });
                flipRightIn.start();
            }
        });
        flipRightOut.setTarget(imageView);
        cardState = cardState == Face ? Back : Face;
        flipRightOut.start();
    }


    @OnClick({R.id.bnAnimatorLeft, R.id.bnAnimatorRight,
            R.id.bnAnimationFade, R.id.bnAnimationRotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnAnimatorLeft:
                handleOpenLeft();
                break;
            case R.id.bnAnimatorRight:
                handleOpenRight();
                break;
            case R.id.bnAnimationFade:
                handleFade();
                break;
            case R.id.bnAnimationRotate:
                handleRotate();
                break;
        }
    }
}

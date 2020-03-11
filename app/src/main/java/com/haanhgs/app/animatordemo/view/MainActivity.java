package com.haanhgs.app.animatordemo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.haanhgs.app.animatordemo.R;
import com.haanhgs.app.animatordemo.controller.Controller;
import com.haanhgs.app.animatordemo.controller.OnAnimation;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnAnimation {

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

    private Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        controller = new Controller(this, this);
    }

    @OnClick({R.id.bnAnimatorLeft, R.id.bnAnimatorRight,
            R.id.bnAnimationFade, R.id.bnAnimationRotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnAnimatorLeft:
                break;
            case R.id.bnAnimatorRight:
                break;
            case R.id.bnAnimationFade:
                controller.handleFadeIn(imageView);
                break;
            case R.id.bnAnimationRotate:
                controller.handleRotate(imageView);
                break;
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

    @Override
    public void animationStart() {
         disableButtons();
    }

    @Override
    public void animationEnd() {
        enableButtons();
    }
}

package com.haanhgs.app.animatordemo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
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

    private void requestPortraitMode(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestPortraitMode();
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

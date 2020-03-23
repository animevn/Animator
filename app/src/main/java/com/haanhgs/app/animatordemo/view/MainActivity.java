package com.haanhgs.app.animatordemo.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.haanhgs.app.animatordemo.R;
import com.haanhgs.app.animatordemo.model.Card;
import com.haanhgs.app.animatordemo.model.CardState;
import com.haanhgs.app.animatordemo.viewmodel.MyViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

    private Repo repo;
    private MyViewModel viewModel;

    private void hideStatuBar(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideStatuBar();
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getLiveData().observe(this, card -> {
            int img = card.getState() == CardState.Front ? R.drawable.ace : R.drawable.ace_back;
            imageView.setImageResource(img);
        });
        repo = new Repo(this);
    }

    @OnClick({R.id.bnAnimatorLeft, R.id.bnAnimatorRight,
            R.id.bnAnimationFade, R.id.bnAnimationRotate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnAnimatorLeft:
                repo.handleFlipLeft(imageView);
                break;
            case R.id.bnAnimatorRight:
                repo.handleFlipRight(imageView);
                break;
            case R.id.bnAnimationFade:
                repo.handleFadeIn(imageView);
                break;
            case R.id.bnAnimationRotate:
                repo.handleRotate(imageView);
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
    public void animationBetween() {
        viewModel.flipCard();
    }

    @Override
    public void animationEnd() {
        enableButtons();
    }
}

package com.haanhgs.app.animator.view;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.haanhgs.app.animator.R;
import com.haanhgs.app.animator.viewmodel.MyViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.haanhgs.app.animator.model.CardState.Back;

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
    int imgResource;

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
            imgResource = card.getState() == Back ? R.drawable.ace_back : R.drawable.ace;
            imageView.setImageResource(imgResource);
        });
        repo = new Repo(this);
        repo.setupViewport(imageView);
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

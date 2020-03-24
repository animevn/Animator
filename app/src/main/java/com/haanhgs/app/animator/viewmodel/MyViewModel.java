package com.haanhgs.app.animator.viewmodel;

import android.app.Application;
import com.haanhgs.app.animator.model.Card;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MyViewModel extends AndroidViewModel {

    private final MutableLiveData<Card>liveData = new MutableLiveData<>();

    public MyViewModel(@NonNull Application application) {
        super(application);
        liveData.setValue(new Card());
    }

    public MutableLiveData<Card> getLiveData() {
        return liveData;
    }

    public void setLiveData(Card card) {
        liveData.setValue(card);
    }

    public void flipCard(){
        if (liveData.getValue() != null){
            Card card = liveData.getValue();
            card.flipCard();
            liveData.setValue(card);
        }
    }
}

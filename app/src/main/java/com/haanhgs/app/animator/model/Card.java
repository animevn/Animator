package com.haanhgs.app.animator.model;

import static com.haanhgs.app.animator.model.CardState.Back;
import static com.haanhgs.app.animator.model.CardState.Front;

public class Card {

    private CardState state = Front;

    public CardState getState() {
        return state;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public void flipCard(){
        state = (state == Front) ? Back : Front;
    }
}

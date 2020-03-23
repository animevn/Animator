package com.haanhgs.app.animatordemo.model;

public class Card {

    private CardState state = CardState.Front;

    public CardState getState() {
        return state;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public void flipCard(){
        state = state == CardState.Front ? CardState.Back : CardState.Front;
    }
}

package com.s4game.server.public_.card.model.group;

import java.util.Collection;
import java.util.List;

import com.s4game.server.public_.card.model.card.Card;

public abstract class AbstractCardGroup implements ICardGroup {

    protected CardGroupType type;

    protected List<Card> cards;
    
    public AbstractCardGroup(CardGroupType type, List<Card> cards) {
        this.type = type;
        this.cards = cards;
    }
    
    @Override
    public CardGroupType getType() {
        return type;
    }
    
    @Override
    public void addCard(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public Collection<Card> getCards() {
        return cards;
    }

    @Override
    public void changeType(CardGroupType type) {
        this.type = type;
    }

}

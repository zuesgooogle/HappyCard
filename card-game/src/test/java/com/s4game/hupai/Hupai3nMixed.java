package com.s4game.hupai;

import java.util.ArrayList;

import org.junit.Test;

import com.s4game.server.public_.card.model.CardType;
import com.s4game.server.public_.room.model.CardData;

/**
 * 
 * 混合大小牌，进行胡牌计算
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年9月27日 下午5:31:25
 */
public class Hupai3nMixed extends BaseHupai {

    @Test
    public void hupai() {
        ArrayList<CardData> cards = new ArrayList<>();
        
        cards.add(new CardData(nextCardId(), 1));
        cards.add(new CardData(nextCardId(), 1, CardType.BIG));
        cards.add(new CardData(nextCardId(), 1, CardType.BIG));
        
        cards.add(new CardData(nextCardId(), 3));
        cards.add(new CardData(nextCardId(), 4));
        cards.add(new CardData(nextCardId(), 5));
        
        cards.add(new CardData(nextCardId(), 2, CardType.BIG));
        cards.add(new CardData(nextCardId(), 3, CardType.BIG));
        cards.add(new CardData(nextCardId(), 4, CardType.BIG));
        
        cards.add(new CardData(nextCardId(), 4, CardType.BIG));
        cards.add(new CardData(nextCardId(), 5, CardType.BIG));
        cards.add(new CardData(nextCardId(), 6, CardType.BIG));
        
        cards.add(new CardData(nextCardId(), 7, CardType.BIG));
        cards.add(new CardData(nextCardId(), 8, CardType.BIG));
        cards.add(new CardData(nextCardId(), 9, CardType.BIG));
        
        for (CardData d : cards) {
            boolean match111 = canMatch111(cards, d);
            boolean match123 = canMatch123(cards, d);
            boolean match2710 = canMatch2710(cards, d);
            
            LOG.info("card: {}, 111: {}, 123: {}, 2710: {}", d, match111, match123, match2710);
        }
        
    }

}

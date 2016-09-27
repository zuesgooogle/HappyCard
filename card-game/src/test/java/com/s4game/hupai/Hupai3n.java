package com.s4game.hupai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.MathUtils;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月24日 下午11:00:49
 *
 */
public class Hupai3n extends BaseHupai {

    @Test
    public void performance() {
        int count = 1000000;
        
        long start = System.currentTimeMillis();
        int i = 0;
        while(i++ < count) {
            hupai();
        }
        
        long end = System.currentTimeMillis();
        LOG.info(" i : {} use time: {}", i, (end - start));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void hupai() {
        ArrayList<CardData> cards = new ArrayList<>();
        
        //随机 15张牌
        for (int i = 0; i < 15; i++) {
            int index = MathUtils.random(0, RoomConstants.CARD_VALUE.length - 1);
            int value = RoomConstants.CARD_VALUE[index];
            
            String id = nextCardId();
            cards.add(new CardData(id, value));
        }

        ArrayList<CardData> remainCards = new ArrayList<>();
        match123((List<CardData>) cards.clone(), remainCards);
        
        match2710(remainCards);
        
        if (remainCards.isEmpty()) {
            LOG.info("hupai. cards: {}", cards);
        } else {
            //LOG.error("can't hupai. cards: {}", cards);
        }
    }



}

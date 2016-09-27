package com.s4game.hupai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.id.IdUtil;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月24日 下午11:00:49
 *
 */
public class Hupai3n {

    public static final Logger LOG = LoggerFactory.getLogger(Hupai3n.class);

    public static final String stageId = "0";

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
    
    @Test
    public void hupai() {
        CopyOnWriteArrayList<CardData> cards = new CopyOnWriteArrayList<>();
        
        for (int v : RoomConstants.CARD_VALUE) {
            String id = nextCardId(stageId);
            cards.add(new CardData(id, v));
        }
        
        int[] add = new int[] {2, 7, 10, 8, 9};
        for (int v : add) {
            String id = nextCardId(stageId);
            cards.add(new CardData(id, v));
        }

        CopyOnWriteArrayList<CardData> remainCards = new CopyOnWriteArrayList<>();
        for(int i = 0; i < 10; i++) {
            match123(cards, remainCards);
        }
        
        if (remainCards.isEmpty()) {
            LOG.info("hu pai.");
        } else {
            this.match2710(remainCards);
        }
    }

    /**
     * 取任意一张牌，匹配顺子
     * 
     * 去 N +- 2 位置的牌
     * 
     * @param cardMap
     */
    public void match123(CopyOnWriteArrayList<CardData> cards, CopyOnWriteArrayList<CardData> remainCards) {
        if (cards.isEmpty()) {
            return;
        }
        
        CardData card = cards.get(0);

        List<CardData> tmp = new ArrayList<>();

        int serial = 0; // 连续两次
        int value = card.getValue();
        for (int i = value - 2; i <= value + 2; i++) {
            if (i == value) {
                continue;
            }

            CardData matchCard = findValue(cards, i);
            if (matchCard != null) {
                serial++;
                tmp.add(matchCard);

                if (serial >= 2) { // 匹配成功
                    break;
                }
            } else {
                serial = 0;
                tmp.clear();
            }
        }

        if (serial >= 2) {
            tmp.add(0, card);

            LOG.info("match success. cards: {}", tmp);

            // 移除
            for (CardData d : tmp) {
                cards.remove(d);
            }
        } else {
            cards.remove(card);
            
            remainCards.add(card);
        }
    }

    public void match2710(CopyOnWriteArrayList<CardData> cards) {
        if (cards.isEmpty()) {
            return;
        }
        
        int[] values = new int[]{2, 7 ,10};
        List<CardData> tmp = new ArrayList<>();
        for (int v : values) {
            CardData card = findValue(cards, v);
            if (card != null) {
                tmp.add(card);
            }
        }
        
        if (tmp.size() < values.length) {
            LOG.info("match failure. cards: {}", tmp);
            return;
        } else {
            LOG.info("match success. cards: {}", tmp);
            
            //删除
            for (CardData c : tmp) {
                cards.remove(c);
            }
            
            match2710(cards);
        }
    }
    
    public CardData findValue(CopyOnWriteArrayList<CardData> cards, int value) {
        for (CardData d : cards) {
            if (d.getValue() == value) {
                return d;
            }
        }
        return null;
    }
    
    private String nextCardId(String stageId) {
        return IdUtil.nextString(stageId);
    }

}

package com.s4game.hupai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.tuple.Tuple;
import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.card.model.CardType;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.id.IdUtil;

public abstract class BaseHupai {

    public final Logger LOG = LoggerFactory.getLogger(getClass());
    
    public static final String stageId = "0";
    
    /**
     * 取任意一张牌，匹配顺子
     * 
     * 去 N +- 2 位置的牌
     * 
     * @param cardMap
     */
    public void match123(List<CardData> cards, List<CardData> remainCards) {
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

            CardData matchCard = findValue(cards, i, card.getType());
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
            LOG.info("match 123 failed. card: {}", card);
        }
        
        match123(cards, remainCards);
    }

    /**
     * 匹配 2, 7, 10
     * 
     * @param cards
     */
    public void match2710(List<CardData> cards) {
        if (cards.isEmpty()) {
            return;
        }
        
        int[] values = new int[]{2, 7 ,10};
        List<CardData> tmp = new ArrayList<>();
        for (int v : values) {
            CardData card = findValue(cards, v, CardType.SMALL);
            if (card != null) {
                tmp.add(card);
            }
        }
        
        if (tmp.size() < values.length) {
            LOG.info("match 2710 failed. cards: {}", tmp);
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
    
    /**
     * 匹配相同牌面值(大，小)，形成一句话
     * 
     * @param cards
     */
    public boolean canMatch111(List<CardData> cards, CardData source) {
        int count = 0;
        
        for (CardData d : cards) {
            if (d.getValue() == source.getValue()) {
                count++;
            }
        }
        
        return count >= 3;
    }
    
    public boolean canMatch123(List<CardData> cards, CardData source) {
        boolean matched = false;
        
        List<CardData> tmp = new ArrayList<>();

        int serial = 0; // 连续两次
        int value = source.getValue();
        for (int i = value - 2; i <= value + 2; i++) {
            if (i == value) {
                continue;
            }

            CardData matchCard = findValue(cards, i, source.getType());
            if (matchCard != null) {
                serial++;
                tmp.add(matchCard);

                if (serial >= 2) { // 匹配成功
                    matched = true;
                    break;
                }
            }
        }
        
        return matched;
    }
    
    public boolean canMatch2710(List<CardData> cards, CardData source) {
        int[] values = new int[]{2, 7 ,10};
        
        boolean exist = false;
        for (int v : values) {
            if (v == source.getValue()) {
                exist = true;
            }
        }
        
        if (!exist) {
            return false;
        }
        
        List<CardData> tmp = new ArrayList<>();
        for (int v : values) {
            CardData card = findValue(cards, v, source.getType());
            if (card != null) {
                tmp.add(card);
            }
        }
        
        return tmp.size() == values.length;
    }
    
    public CardData findValue(List<CardData> cards, int value) {
        for (CardData d : cards) {
            if (d.getValue() == value) {
                return d;
            }
        }
        return null;
    }
    
    public CardData findValue(List<CardData> cards, int value, int type) {
        for (CardData d : cards) {
            if (d.getValue() == value && d.getType() == type) {
                return d;
            }
        }
        return null;
    }
    
    /**
     * 查找 一对 的数量
     * 
     * @param cards
     * @return
     */
    public List<TwoTuple<CardData, CardData>> findPairs(List<CardData> cards) {
        List<TwoTuple<CardData, CardData>> paris = new ArrayList<>();
        
        Map<String, CardData> map = new HashMap<>();
        
        TwoTuple<CardData, CardData> tuple = null;
        for (CardData d : cards) {
            if (map.containsKey(d.getKey())) {
                tuple = Tuple.tuple(map.get(d.getKey()), d);
                paris.add(tuple);
            }
            
            map.put(d.getKey(), d);
        }
        
        return paris;
    }
    
    public String nextCardId() {
        return IdUtil.nextString(stageId);
    }
}

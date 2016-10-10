package com.s4game.hupai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.tuple.Tuple;
import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.card.model.card.CardType;
import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.id.IdUtil;

public abstract class BaseHupai {

    public final Logger LOG = LoggerFactory.getLogger(getClass());
    
    public static final String stageId = "0";
    
    public ArrayList<CardData> initCards() {
        ArrayList<CardData> cards = new ArrayList<>();

        for (int v : RoomConstants.CARD_VALUE) {
            cards.add(new CardData(nextCardId(), v, CardType.SMALL));
            cards.add(new CardData(nextCardId(), v, CardType.SMALL));
            cards.add(new CardData(nextCardId(), v, CardType.SMALL));
            cards.add(new CardData(nextCardId(), v, CardType.SMALL));
        }

        for (int v : RoomConstants.CARD_VALUE) {
            cards.add(new CardData(nextCardId(), v, CardType.BIG));
            cards.add(new CardData(nextCardId(), v, CardType.BIG));
            cards.add(new CardData(nextCardId(), v, CardType.BIG));
            cards.add(new CardData(nextCardId(), v, CardType.BIG));
        }

        Collections.shuffle(cards);

        return cards;
    }
    
    /**
     * 取任意一张牌，匹配顺子
     * 
     * 去 N +- 2 位置的牌
     * 
     * @param cardMap
     */
    public boolean match123(List<CardData> cards, CardData curData) {
        List<CardData> tmp = new ArrayList<>();

        int serial = 0; // 连续两次
        int value = curData.getValue();
        for (int i = value - 2; i <= value + 2; i++) {
            if (i == value) {
                continue;
            }

            CardData matchCard = findValue(cards, i, curData.getType());
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
            tmp.add(0, curData);

            //LOG.info("match 123 success. cards: {}", tmp);
            
            for (CardData d : tmp) {
                cards.remove(d);
            }
            return true;
        }
        
        return false;
    }

    /**
     * 匹配 2, 7, 10
     * 
     * @param cards
     */
    public boolean match2710(List<CardData> cards, CardData curCard) {
        int[] values = new int[]{2, 7 ,10};
        List<CardData> tmp = new ArrayList<>();
        tmp.add(curCard);
        
        for (int v : values) {
            CardData card = findValue(cards, v, curCard.getType());
            if (card != null) {
                tmp.add(card);
            }
        }
        
        if (tmp.size() < values.length) {
            //LOG.info("match 2710 failed. cards: {}", tmp);
            return false;
        } else {
            //LOG.info("match 2710 success. cards: {}", tmp);
            
            for (CardData c : tmp) {
                cards.remove(c);
            }
            
            return true;
        }
    }
    
    public boolean match111(List<CardData> cards, CardData curCard) {
        List<CardData> tmp = new ArrayList<>();
        //tmp.add(curCard);
        
        int count = 0;
        for (CardData d : cards) {
            if (count >= 3) {
                break;
            }
            
            if (d.getValue() == curCard.getValue()) {
                tmp.add(d);
                count++;
            }
        }
        
        if (tmp.size() >= 3) {
            //LOG.info("match 111 success. cards: {}", tmp);
            
            for (CardData c : tmp) {
                cards.remove(c);
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean matchPair(List<CardData> cards, TwoTuple<CardData, CardData> pair) {
        cards.remove(pair.getFirst());
        cards.remove(pair.getSecond());
        
        return true;
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
            } else {
                serial = 0;
                tmp.clear();
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
    
    /**
     * 匹配对子
     * 
     * @param cards
     * @param source
     * @return
     */
    public boolean canMatchPair(List<CardData> cards, CardData source) {
        int count = 0;
        
        for (CardData d : cards) {
            if (source.isSame(d)) {
                count++;
            }
        }
        
        return count >= 2;
    }
    
    public CardData findValue(List<CardData> cards, int value) {
        for (CardData d : cards) {
            if (d.getValue() == value) {
                return d;
            }
        }
        return null;
    }
    
    public CardData findValue(List<CardData> cards, int value, CardType type) {
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

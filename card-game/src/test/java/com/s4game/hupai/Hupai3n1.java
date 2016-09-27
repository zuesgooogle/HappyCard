package com.s4game.hupai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.id.IdUtil;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月24日 下午11:00:49
 *
 */
public class Hupai3n1 extends BaseHupai {

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
    
    @SuppressWarnings("unchecked")
    @Test
    public void hupai() {
        ArrayList<CardData> cards = new ArrayList<>();

        int[] add = new int[] {1, 2, 3, 4, 2, 7, 10, 1};
        for (int v : add) {
            String id = nextCardId(stageId);
            cards.add(new CardData(id, v));
        }

        List<TwoTuple<CardData, CardData>> pairs = findPairs(cards);
        if (pairs.isEmpty()) {
            LOG.info("not match pair.");
            return;
        }
        
        boolean hupai = false;
        for (TwoTuple<CardData, CardData> tuple : pairs) {
            ArrayList<CardData> tmp = (ArrayList<CardData>) cards.clone();
            List<CardData> remainCards = new ArrayList<>();
            
            tmp.remove(tuple.getFirst());
            tmp.remove(tuple.getSecond());
            LOG.info("match pair success. card: {}, {}", tuple.getFirst(), tuple.getSecond());
            
            match123(tmp, remainCards);
            
            match2710(remainCards);
            
            if (remainCards.isEmpty()) {
                hupai = true;
            }
        }

        if (hupai) {
            LOG.info("hupai.");
        }
    }
    
    private String nextCardId(String stageId) {
        return IdUtil.nextString(stageId);
    }

}

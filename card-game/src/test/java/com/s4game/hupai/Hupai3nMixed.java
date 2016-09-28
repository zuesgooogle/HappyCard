package com.s4game.hupai;

import java.util.ArrayList;

import org.junit.Test;

import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.MathUtils;

/**
 * 
 * 混合大小牌，进行胡牌计算
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年9月27日 下午5:31:25
 */
public class Hupai3nMixed extends BaseHupai {

    private ArrayList<CardData> sourceCards;
    
    private int hupaiCount = 0;
    
    @Test
    public void performance() {
        int count = 1000000;
        
        long start = System.currentTimeMillis();
        int i = 0;
        while(i++ < count) {
            hupai();
        }
        
        long end = System.currentTimeMillis();
        LOG.info(" i : {} use time: {}, hupai: {}", i, (end - start), hupaiCount);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void hupai() {
        sourceCards = new ArrayList<>();
        ArrayList<CardData> tmp = initCards();

        //随机获取 15张
        for(int i = 0; i < 15; i++) {
            int index = MathUtils.random(0, tmp.size() - 1);
            sourceCards.add(tmp.remove(index));
        }
        
        match((ArrayList<CardData>) sourceCards.clone());
    }
    
    @SuppressWarnings("unchecked")
    public void match(ArrayList<CardData> cards) {
        if (cards.isEmpty()) {
            hupaiCount++;
            //LOG.info("hupai. cards: {}", sourceCards);
            return;
        }
        
        CardData curCard = cards.get(0);
        boolean match111 = canMatch111(sourceCards, curCard);
        boolean match123 = canMatch123(sourceCards, curCard);
        boolean match2710 = canMatch2710(sourceCards, curCard);
        
        if (match111) {
            ArrayList<CardData> copy = (ArrayList<CardData>) cards.clone();
            if (match111(copy, curCard)) {
                match(copy);
            }
        }
        
        if (match123) {
            ArrayList<CardData> copy = (ArrayList<CardData>) cards.clone();
            if (match123(copy, curCard)) {
                match(copy);
            }
        }
        
        if (match2710) {
            ArrayList<CardData> copy = (ArrayList<CardData>) cards.clone();
            if (match2710(copy, curCard)) {
                match(copy);
            }
        }
        
        return;
    }
}

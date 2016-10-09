package com.s4game.hupai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.utils.MathUtils;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月24日 下午11:00:49
 *
 */
public class Hupai3n1 extends BaseHupai {

    private ArrayList<CardData> sourceCards;

    private int hupaiCount = 0;

    @Test
    public void performance() {
        int count = 1000000;

        long start = System.currentTimeMillis();
        int i = 0;
        while (i++ < count) {
            hupai();
        }

        long end = System.currentTimeMillis();
        LOG.info(" i : {} use time: {}, hupai: {}", i, (end - start), hupaiCount);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void hupai() {
        sourceCards = new ArrayList<>();
        ArrayList<CardData> initCards = initCards();

        //随机获取 15张
        for(int i = 0; i < 14; i++) {
            int index = MathUtils.random(0, initCards.size() - 1);
            sourceCards.add(initCards.remove(index));
        }

        List<TwoTuple<CardData, CardData>> pairs = findPairs(sourceCards);
        if (pairs.isEmpty()) {
            LOG.info("not match pair.");
            return;
        }

        for (TwoTuple<CardData, CardData> tuple : pairs) {
            ArrayList<CardData> tmp = (ArrayList<CardData>) sourceCards.clone();
            
            matchPair(tmp, tuple);
            
            match(tmp);
        }

        match((ArrayList<CardData>) sourceCards.clone());
    }

    public void match(ArrayList<CardData> cards) {
        if (cards.isEmpty()) {
            hupaiCount++;
            LOG.info("hupai. cards: {}", sourceCards);
            return;
        }

        CardData curCard = cards.get(0);
        boolean match111 = canMatch111(sourceCards, curCard);
        boolean match123 = canMatch123(sourceCards, curCard);
        boolean match2710 = canMatch2710(sourceCards, curCard);

        if (match111) {
            if (match111(cards, curCard)) {
                match(cards);
            }
        }

        if (match123) {
            if (match123(cards, curCard)) {
                match(cards);
            }
        }

        if (match2710) {
            if (match2710(cards, curCard)) {
                match(cards);
            }
        }

        return;
    }

}

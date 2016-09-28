package com.s4game.hupai;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.room.model.CardData;

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
        LOG.info(" i : {} use time: {}", i, (end - start));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void hupai() {
        sourceCards = new ArrayList<>();

        int[] add = new int[] { 1, 2, 3, 4, 4, 5, 6, 2, 7, 10, 1 };
        for (int v : add) {
            String id = nextCardId();
            sourceCards.add(new CardData(id, v));
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

        // boolean hupai = false;
        // for (TwoTuple<CardData, CardData> tuple : pairs) {
        // ArrayList<CardData> tmp = (ArrayList<CardData>) sourceCards.clone();
        // List<CardData> remainCards = new ArrayList<>();
        //
        // tmp.remove(tuple.getFirst());
        // tmp.remove(tuple.getSecond());
        // LOG.info("match pair success. card: {}, {}", tuple.getFirst(),
        // tuple.getSecond());
        //
        // match123(tmp, remainCards);
        //
        // match2710(remainCards);
        //
        // if (remainCards.isEmpty()) {
        // hupai = true;
        // }
        // }

        match((ArrayList<CardData>) sourceCards.clone());
    }

    public void match(ArrayList<CardData> cards) {
        if (cards.isEmpty()) {
            hupaiCount++;
            LOG.info("hupai. cards: {}", sourceCards);
            return;
        } else {
            // LOG.info("failed. cards: {}", sourceCards);
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

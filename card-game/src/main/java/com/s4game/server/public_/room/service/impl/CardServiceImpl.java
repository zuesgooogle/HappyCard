package com.s4game.server.public_.room.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.public_.room.model.RoomBusinessData;
import com.s4game.server.public_.room.model.RoomMemberData;
import com.s4game.server.public_.room.service.ICardService;
import com.s4game.server.stage.room.RoomStage;
import com.s4game.server.utils.MathUtils;
import com.s4game.server.utils.id.IdUtil;

@Service
public class CardServiceImpl implements ICardService {

    @Override
    public void deal(RoomStage stage) {
        RoomBusinessData businessData = stage.getRoomBusinessData();
        
        List<CardData> initCards = initCards(stage.getId());

        //初始化牌
        businessData.setInitCards(initCards);
        
        //随机庄家
        RoomMemberData dealer = randomDealer(businessData);
        
        //发牌
        int count = 0;
        int index = 0;
        while (count < RoomConstants.INIT_CARD_SIZE) {
            RoomMemberData member = businessData.getMembers().get(index);
            
            CardData card = initCards.remove(0);
            member.getHandCard().add(card);
            
            count++;
            index++;
            
            if (index % RoomConstants.MEMBER_SIZE == 0) {
                index = 0;
            }
        }
        
        CardData lastCard = initCards.remove(0);
        dealer.getHandCard().add(lastCard);
        
        
    }
    
    /**
     * 1. 开始游戏，随机一个
     * 2. 一局打完，胡牌人庄
     *            ，没人胡牌，随机一个
     * 
     * @param businessData
     */
    private RoomMemberData randomDealer(RoomBusinessData businessData) {
        List<RoomMemberData> members = businessData.getMembers();
        
        int index = MathUtils.random(0, members.size() - 1);
        RoomMemberData member = members.get(index);
        member.setDealer(true);
        
        return member;
    }
    
    /**
     * 创建一副牌
     * @return
     */
    private List<CardData> initCards(String stageId) {
        List<CardData> cards = new ArrayList<>();
        
        for (int v : RoomConstants.SMALL) {
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
        }
        
        for (int v : RoomConstants.BIG) {
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
        }
        
        Collections.shuffle(cards);
        
        return cards;
    }

    private String nextCardId(String stageId) {
        return IdUtil.nextString(stageId);
    }
}

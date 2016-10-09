package com.s4game.server.public_.card.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.core.container.DataContainer;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.stagecontroll.RoleState;
import com.s4game.server.public_.card.model.CardType;
import com.s4game.server.public_.card.service.ICardService;
import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.public_.room.model.RoomBusinessData;
import com.s4game.server.public_.room.model.RoomMemberData;
import com.s4game.server.share.log.Log;
import com.s4game.server.stage.room.RoomStage;
import com.s4game.server.stage.service.IRoleStageService;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.utils.MathUtils;
import com.s4game.server.utils.id.IdUtil;

@Service
public class CardServiceImpl implements ICardService {

    public static final Logger LOG = Log.CARD;

    @Autowired
    private IRoleStageService roleStageService;

    @Autowired
    private IStageService stageService;

    @Autowired
    private DataContainer dataContainer;

    @Override
    public void deal(RoomStage stage) {
        RoomBusinessData businessData = stage.getRoomBusinessData();

        List<CardData> initCards = initCards(stage.getId());

        // 初始化牌
        businessData.setInitCards(initCards);

        // 随机庄家
        RoomMemberData dealer = randomDealer(businessData);

        // 发牌
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
     * 1. 开始游戏，随机一个 2. 一局打完，胡牌人庄 ，没人胡牌，随机一个
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
     * 
     * @return
     */
    private List<CardData> initCards(String stageId) {
        List<CardData> cards = new ArrayList<>();

        for (int v : RoomConstants.CARD_VALUE) {
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
        }

        for (int v : RoomConstants.CARD_VALUE) {
            cards.add(new CardData(nextCardId(stageId), v, CardType.BIG));
            cards.add(new CardData(nextCardId(stageId), v, CardType.BIG));
            cards.add(new CardData(nextCardId(stageId), v, CardType.BIG));
            cards.add(new CardData(nextCardId(stageId), v, CardType.BIG));
        }

        Collections.shuffle(cards);

        return cards;
    }

    private String nextCardId(String stageId) {
        return IdUtil.nextString(stageId);
    }

    @Override
    public void play(String roleId, String cardId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return;
        }

        String stageId = roleState.getCurPosition().getStageId();
        RoomStage stage = stageService.getStage(stageId);
        if (null == stage) {
            LOG.error("role: {} can't play card. not in stage.", roleId);
            return;
        }
        
        

    }
}

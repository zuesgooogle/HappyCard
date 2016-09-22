package com.s4game.server.bus.card.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.s4game.server.bus.card.CardConstants;
import com.s4game.server.bus.card.service.ICardService;
import com.s4game.server.public_.room.model.CardData;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.model.element.role.Role;
import com.s4game.server.utils.id.IdUtil;

@Service
public class CardServiceImpl implements ICardService {

    
    
    @Override
    public void deal(IStage stage) {
        Collection<Role> roles = stage.getElementsByType(ElementType.ROLE);
        
        List<CardData> cards = createCardGroup(stage.getId());
        
//        int count = 0;
//        int index = 0;
//        while(count < CardConstants.INIT_CARD_SIZE) {
//            Player player = room.getPlayers().get(index);
//            Card card = result[count];
//            player.addCard(card);
//            
//            count++;
//            index++;
//            
//            if (index % playerSize == 0) {
//                index = 0;
//            }
//        }
    }
    
    /**
     * 创建一副牌
     * @return
     */
    private List<CardData> createCardGroup(String stageId) {
        List<CardData> cards = new ArrayList<>();
        
        for (int v : CardConstants.SMALL) {
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
            cards.add(new CardData(nextCardId(stageId), v));
        }
        
        for (int v : CardConstants.BIG) {
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

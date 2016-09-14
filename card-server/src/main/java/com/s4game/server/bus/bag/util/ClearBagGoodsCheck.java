package com.s4game.server.bus.bag.util;

import java.util.ArrayList;
import java.util.List;

/**
*
* @Author zeusgooogle@gmail.com
* @sine   2015年8月11日 下午3:08:57
*
*/
public class ClearBagGoodsCheck {
    
    private List<String> delId = new ArrayList();

    public void addDelId(String id) {
        this.delId.add(id);
    }

    public List<String> getDelIds() {
        return this.delId;
    }
}

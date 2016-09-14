package com.s4game.server.stage.model.core.element.impl;

import com.s4game.server.stage.model.core.element.BattleMode;
import com.s4game.server.stage.model.core.element.IFighter;

public abstract class AbsFighter extends AbsElement implements IFighter {
    
    private BattleMode battleMode;

    public AbsFighter(String id, String name, String teamId, String camp) {
        super(id, name, teamId, camp);
    }

    public void setBattleMode(BattleMode battleMode) {
        this.battleMode = battleMode;
    }

    public BattleMode getBattleMode() {
        return this.battleMode;
    }

    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        getEventManager().fireMoveEvent();
    }
}

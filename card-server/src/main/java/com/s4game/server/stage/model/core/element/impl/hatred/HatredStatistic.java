package com.s4game.server.stage.model.core.element.impl.hatred;

public class HatredStatistic {
    
    private boolean firstHatred;
    
    private boolean firstTargetHatred;

    public boolean isFirstHatred() {
        return this.firstHatred;
    }

    void setFirstHatred(boolean firstHatred) {
        this.firstHatred = firstHatred;
    }

    public boolean isFirstTargetHatred() {
        return this.firstTargetHatred;
    }

    void setFirstTargetHatred(boolean firstTargetHatred) {
        this.firstTargetHatred = firstTargetHatred;
    }

    public void clear() {
        this.firstHatred = false;
        this.firstTargetHatred = false;
    }
}

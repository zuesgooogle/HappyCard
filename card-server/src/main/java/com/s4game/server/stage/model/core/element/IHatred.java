package com.s4game.server.stage.model.core.element;

import com.s4game.server.stage.model.core.stage.ElementType;

public interface IHatred {
    
    public String getId();

    public ElementType getElementType();

    public void add(int hatred);

    public int getVal();

    public boolean expired(int expired);

    public long getlasttime();
}
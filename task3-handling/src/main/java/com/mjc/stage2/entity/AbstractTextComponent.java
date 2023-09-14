package com.mjc.stage2.entity;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class AbstractTextComponent {
    @Getter(AccessLevel.PUBLIC)
    protected final TextComponentType componentType;

    protected AbstractTextComponent(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public abstract String operation();

    public abstract void add(AbstractTextComponent textComponent);

    public abstract void remove(AbstractTextComponent textComponent);

    public abstract int getSize();
}

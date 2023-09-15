package com.mjc.stage2.entity;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractTextComponent {
    @Getter(AccessLevel.PUBLIC)
    protected final TextComponentType componentType;

    protected AbstractTextComponent(TextComponentType componentType) {
        this.componentType = componentType;
    }

    public String operation() {
        return streamForJoining().collect(Collectors.joining());
    }

    protected abstract Stream<String> streamForJoining();

    public abstract void add(AbstractTextComponent textComponent);

    public abstract void remove(AbstractTextComponent textComponent);

    public abstract int getSize();
}

package com.mjc.stage2.entity;

import com.mjc.stage2.exception.HandlingException;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.stream.Stream;

public class SymbolLeaf extends AbstractTextComponent {
    @Getter(AccessLevel.PUBLIC)
    private final char value;

    public SymbolLeaf(char value) {
        super(TextComponentType.SYMBOL);
        this.value = value;
    }

    @Override
    public String operation() {
        return String.valueOf(value);
    }

    @Override
    protected Stream<String> streamForJoining() {
        return Stream.of(operation());
    }

    @Override
    public void add(AbstractTextComponent textComponent) {
        throw new HandlingException("Adding an AbstractTextComponent is not supported by leaf.");
    }

    @Override
    public void remove(AbstractTextComponent textComponent) {
        throw new HandlingException("Removing an AbstractTextComponent is not supported by leaf.");
    }

    @Override
    public int getSize() {
        return 1;
    }
}

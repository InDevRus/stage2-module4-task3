package com.mjc.stage2.entity;

import com.mjc.stage2.exception.HandlingException;
import lombok.AccessLevel;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComponent extends AbstractTextComponent {
    protected final List<AbstractTextComponent> subcomponents = new ArrayList<>();

    @Getter(value = AccessLevel.PUBLIC)
    private int size = 0;

    public TextComponent(TextComponentType componentType) {
        super(componentType);
    }

    private void requireAllowedType(AbstractTextComponent textComponent) {
        if (!this.getComponentType().allowsDirectSubtype(textComponent.getComponentType())) {
            throw new HandlingException(MessageFormat.format(
                    "Subcomponent of type {0} is not allowed for component of type {1}.",
                    textComponent.getComponentType(),
                    this.getComponentType()));
        }
    }

    //TODO: Insanely inefficient
    @Override
    public String operation() {
        return subcomponents.stream()
                .map(AbstractTextComponent::operation)
                .collect(Collectors.joining(getComponentType().getDelimiter()));
    }

    @Override
    public void add(AbstractTextComponent textComponent) {
        requireAllowedType(textComponent);
        subcomponents.add(textComponent);
        size += textComponent.getSize();
    }

    @Override
    public void remove(AbstractTextComponent textComponent) {
        requireAllowedType(textComponent);
        var wasRemoved = subcomponents.remove(textComponent);
        if (wasRemoved) {
            size -= textComponent.getSize();
        }
    }
}

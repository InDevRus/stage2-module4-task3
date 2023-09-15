package com.mjc.stage2.entity;

import com.mjc.stage2.exception.HandlingException;
import lombok.AccessLevel;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    @Override
    protected Stream<String> streamForJoining() {
        if (getComponentType() == TextComponentType.WORD) {
            return Stream.of(joinWord());
        }

        var delimiter = getComponentType().getDelimiter();
        return subcomponents.stream()
                .map(AbstractTextComponent::streamForJoining)
                .flatMap(substream -> Stream.concat(Stream.of(delimiter), substream))
                .skip(1);
    }

    private String joinWord() {
        var builder = new StringBuilder();
        subcomponents.forEach(subcomponent -> {
            if (!(subcomponent instanceof SymbolLeaf)) {
                throw new HandlingException("Error has happened while joining word.");
            }

            var leaf = (SymbolLeaf) subcomponent;
            builder.append(leaf.getValue());
        });
        return builder.toString();
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

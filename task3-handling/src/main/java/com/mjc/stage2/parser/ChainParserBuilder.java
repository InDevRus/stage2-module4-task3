package com.mjc.stage2.parser;

import com.mjc.stage2.exception.ParserBuildingException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ChainParserBuilder {
    private final Deque<AbstractTextParser> parsers = new ArrayDeque<>();

    public ChainParserBuilder setParser(AbstractTextParser successor) {
        Optional.ofNullable(parsers.peekLast())
                .ifPresent(lastAddedParser -> lastAddedParser.setNextParser(successor));
        parsers.addLast(successor);
        return this;
    }

    public AbstractTextParser build() {
        if (parsers.isEmpty()) {
            throw new ParserBuildingException("None of the parsers were attached.");
        }
        return parsers.getFirst();
    }
}

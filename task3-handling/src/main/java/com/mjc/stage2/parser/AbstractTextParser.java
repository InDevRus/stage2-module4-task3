package com.mjc.stage2.parser;

import com.mjc.stage2.entity.AbstractTextComponent;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;

    public AbstractTextParser() {
    }

    public AbstractTextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract void parse(AbstractTextComponent abstractTextComponent, String income);

    protected void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }
}

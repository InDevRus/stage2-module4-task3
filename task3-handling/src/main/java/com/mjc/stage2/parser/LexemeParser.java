package com.mjc.stage2.parser;

import com.mjc.stage2.entity.AbstractTextComponent;
import com.mjc.stage2.entity.TextComponent;
import com.mjc.stage2.entity.TextComponentType;

import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private static final String SENTENCE_REGEX = "((?:(?:[\\w-'=():]|[.!?]\\S)+,?\\s?)+[.!?]?)";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGEX,
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);

    @Override
    public void parse(AbstractTextComponent abstractTextComponent, String income) {
        var matcher = SENTENCE_PATTERN.matcher(income);
        while (matcher.find()) {
            var sentence = matcher.group();
            var sentenceComponent = new TextComponent(TextComponentType.SENTENCE);
            nextParser.parse(sentenceComponent, sentence);
            abstractTextComponent.add(sentenceComponent);
        }
    }
}

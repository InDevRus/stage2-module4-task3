package com.mjc.stage2.parser;


import com.mjc.stage2.entity.AbstractTextComponent;
import com.mjc.stage2.entity.SymbolLeaf;
import com.mjc.stage2.entity.TextComponent;
import com.mjc.stage2.entity.TextComponentType;

import java.util.regex.Pattern;

public class WordParser extends AbstractTextParser {
    private static final String WORD_REGEX = "([\\w-'=():.!?]+,?)\\s?";
    public static final Pattern WORD_PATTERN = Pattern.compile(WORD_REGEX, Pattern.MULTILINE);

    @Override
    public void parse(AbstractTextComponent sentenceComponent, String income) {
        var matcher = WORD_PATTERN.matcher(income);
        while (matcher.find()) {
            var word = matcher.group(1);
            var wordComponent = new TextComponent(TextComponentType.WORD);
            sentenceComponent.add(wordComponent);
            for (var character : word.toCharArray()) {
                wordComponent.add(new SymbolLeaf(character));
            }
        }
    }
}

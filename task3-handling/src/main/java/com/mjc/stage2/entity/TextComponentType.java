package com.mjc.stage2.entity;

import com.mjc.stage2.exception.SymbolDivisionAttemptException;

import java.util.Collection;
import java.util.Set;

public enum TextComponentType {
    SYMBOL(null, null, false),
    WORD("", Set.of(SYMBOL), false),
    SENTENCE(" ", Set.of(WORD), true);

    private final String delimiter;
    private final Collection<TextComponentType> allowedSubtypes;
    private final boolean canBeSubtypeOfItself;

    TextComponentType(String delimiter, Collection<TextComponentType> allowedSubtypes, boolean canBeSubtypeOfItself) {
        this.delimiter = delimiter;
        this.allowedSubtypes = allowedSubtypes;
        this.canBeSubtypeOfItself = canBeSubtypeOfItself;
    }

    private void throwOnSymbolDivisionAttempt() {
        if (this.equals(SYMBOL)) {
            throw new SymbolDivisionAttemptException("Symbol cannot have any delimiter within itself.");
        }
    }

    public boolean allowsDirectSubtype(TextComponentType subtype) {
        throwOnSymbolDivisionAttempt();
        return this.equals(subtype) ? canBeSubtypeOfItself : this.allowedSubtypes.contains(subtype);
    }

    public String getDelimiter() {
        throwOnSymbolDivisionAttempt();
        return delimiter;
    }
}

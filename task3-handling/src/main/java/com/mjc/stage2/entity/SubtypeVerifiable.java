package com.mjc.stage2.entity;

public interface SubtypeVerifiable<T extends SubtypeVerifiable<T>> {
    boolean allowsDirectSubtype(T subtype);
}

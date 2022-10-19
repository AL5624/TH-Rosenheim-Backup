package de.thro.inf.prg3.a04.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface SimpleList<T> extends Iterable<T> {

    int size();

    void add(T item);

    T get (int index);

    void set (int index, T item);

    void addDefault(Class<T> clazz);

    SimpleList<T> makeInstance();

    SimpleList<T> filter(Predicate<T> predicate);

    void forEach(Consumer<? super T> consumer);
}


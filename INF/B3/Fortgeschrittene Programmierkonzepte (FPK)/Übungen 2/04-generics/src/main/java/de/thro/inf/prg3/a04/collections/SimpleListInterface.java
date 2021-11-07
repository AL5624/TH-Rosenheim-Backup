package de.thro.inf.prg3.a04.collections;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface SimpleListInterface<T> extends Iterable<T> {
    void add(T item);

    T get(int index);

    void set(int index, T item);

    default void addDefault(Class<T> className)
    {
        try
        {
            this.add(className.getConstructor().newInstance());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    default SimpleListInterface<T> makeInstance()
    {
        try
        {
            return this.getClass().getConstructor().newInstance();
        }
        catch (Exception e)
        {
            return new SimpleList<>();
        }
    }

    int size();

    SimpleListInterface<T> filter(Predicate<T> predicate);

    default void forEach(Consumer<? super T> consumer)
    {
        for(T item: this)
        {
            consumer.accept(item);
        }
    }
}


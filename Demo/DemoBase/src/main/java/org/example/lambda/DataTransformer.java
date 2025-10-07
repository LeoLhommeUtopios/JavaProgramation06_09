package org.example.lambda;

@FunctionalInterface
public interface DataTransformer<T> {
    T transform (T input );
}

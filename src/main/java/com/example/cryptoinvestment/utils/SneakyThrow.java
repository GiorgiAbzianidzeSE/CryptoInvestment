package com.example.cryptoinvestment.utils;

public class SneakyThrow {

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e; // This trick works because the cast is erased at runtime
    }

    public static <T> T sneakyGet(SneakySupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Throwable throwable) {
            sneakyThrow(throwable);
            return null; // This line will never be executed, it's just for compilation
        }
    }

    @FunctionalInterface
    public interface SneakySupplier<T> {
        T get() throws Throwable;
    }
}


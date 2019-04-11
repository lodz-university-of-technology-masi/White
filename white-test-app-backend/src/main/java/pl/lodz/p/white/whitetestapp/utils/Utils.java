package pl.lodz.p.white.whitetestapp.utils;

import java.util.function.Function;

public class Utils<T, S> {
    public static <T, S> S optGet(T t, Function<T, S> function) {
        if (t == null) return null;
        else {
            return function.apply(t);
        }

    }
}

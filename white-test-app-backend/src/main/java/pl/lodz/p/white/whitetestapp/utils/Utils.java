package pl.lodz.p.white.whitetestapp.utils;

import java.util.function.Function;

public class Utils<T, S> {
    public static <T, S> S optGet(T t, Function<T, S> function) {
        return t == null ? null : function.apply(t);
    }
}

package com.maxzxwd.fitsvegetationonly;

import java.util.Objects;

public final class Utils {
    private Utils() {
    }

    public static <T> boolean contains(T[] array, T v) {
        for (T e : array) {
            if (Objects.equals(v, e)) {
                return true;
            }
        }

        return false;
    }
}

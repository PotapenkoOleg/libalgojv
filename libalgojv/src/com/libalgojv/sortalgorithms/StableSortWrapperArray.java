package com.libalgojv.sortalgorithms;

public final class StableSortWrapperArray {
    private StableSortWrapperArray() {
    }

    public static <E extends Comparable<E>> StableSortKeyWrapper<E>[] toStableSortWrapperArray(E[] input) {
        StableSortKeyWrapper<E>[] result = new StableSortKeyWrapper[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = new StableSortKeyWrapper<>(input[i], i);
        }
        return result;
    }

    public static <E extends Comparable<E>> Object[] toObjectTypeArray(StableSortKeyWrapper<E>[] input) {
        Object[] result = new Object[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = input[i].getKey();
        }
        return result;
    }
}

package com.s4game.core.tuple;

public class Tuple {

    public static <A, B> TwoTuple<A, B> tuple(A first, B second) {
        return new TwoTuple<A, B>(first, second);
    }

}

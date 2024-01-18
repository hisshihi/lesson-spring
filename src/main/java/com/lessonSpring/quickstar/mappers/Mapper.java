// Интерфейс создания картогрофа

package com.lessonSpring.quickstar.mappers;

public interface Mapper<A, B> {

    B mapTo(A a);

    A mapFrom(B b);

}

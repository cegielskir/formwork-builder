package com.cegielskir.formwork.builder.computing;

import java.util.Objects;

public class Girder {

    public final float length;

    public Girder(float length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Girder girder = (Girder) o;
        return Objects.equals(length, girder.length);
    }

    @Override
    public int hashCode() {

        return Objects.hash(length);
    }
}


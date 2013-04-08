package controllers;

import java.io.Serializable;

public enum Phase implements Serializable {
    Day, Night;

    public boolean isNight() {
        return this == Night;
    }
}

package com.trainer_service.trainer_service.enums;

public enum BadgeType {
    BOULDER("Boulder Badge"),
    CASCADE("Cascade Badge"),
    THUNDER("Thunder Badge"),
    RAINBOW("Rainbow Badge"),
    SOUL("Soul Badge"),
    MARSH("Marsh Badge"),
    VOLCANO("Volcano Badge"),
    EARTH("Earth Badge");

    private String value;

    BadgeType(String value) {
        this.value = value;
    }
}

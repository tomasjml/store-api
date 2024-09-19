package com.dialexa.storeapi.entities.enums;

import lombok.Getter;

@Getter
public enum ProductCategoryEnum {
    ELECTRONICS("ELECTRONICS"),
    CLOTHING("CLOTHING"),
    FOOD("FOOD"),
    BOOKS("BOOKS"),
    TOYS("TOYS"),
    SPORTS("SPORTS"),
    HOME_GOODS("HOME GOODS"),
    BEAUTY("BEAUTY"),
    TOOLS("TOOLS"),
    AUTOMOTIVE("AUTOMOTIVE"),
    GARDEN("GARDEN"),
    PETS("PETS"),
    OFFICE_SUPPLIES("OFFICE SUPPLIES"),
    JEWELRY("JEWELRY"),
    MUSIC("MUSIC"),
    MOVIES("MOVIES"),
    VIDEO_GAMES("VIDEO GAMES");

    private final String value;

    ProductCategoryEnum(String value) {
        this.value = value;
    }
}

package com.blissstock.mappingSite.enums;
import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClassType {

    LIVE("LIVE"),
    VIDEO("VIDEO");

    @Getter private String value;
}
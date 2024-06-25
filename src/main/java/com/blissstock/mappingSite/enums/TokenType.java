package com.blissstock.mappingSite.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TokenType {
    PASSWORD_RESET("PASSWORD_RESET"),
    VERIFICATION("VERIFICATION");

    
    @Getter private String value;


}

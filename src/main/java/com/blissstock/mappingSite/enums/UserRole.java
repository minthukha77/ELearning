package com.blissstock.mappingSite.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    SUPER_ADMIN("ROLE_SUPER_ADMIN"), // all these enums give error, for no constructor
    ADMIN("ROLE_ADMIN"),
    STUDENT("ROLE_STUDENT"),
    TEACHER("ROLE_TEACHER"),
    GUEST_USER("ROLE_GUEST_USER");

    @Getter private String value;

    public static UserRole strToUserRole(String str){
        for(UserRole userRole: UserRole.values()){
            if(str.equalsIgnoreCase(userRole.value)){
                return userRole;
            }
        }
        return UserRole.GUEST_USER;
    }


  }
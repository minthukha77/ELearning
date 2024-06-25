package com.blissstock.mappingSite.enums;
import lombok.Getter;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Days_of_the_week {
  
    Monday("Monday"),
    Tuesday("Tuesday"),
    Wednesday("Wednesday"),
    Thursday("Thursday"),
    Friday("Friday"),
    Saturday("Saturday"),
    Sunday("Sunday"),
    ;
    
    @Getter private String value;

   

}

package com.blissstock.mappingSite.dto;

import javax.validation.constraints.NotBlank;

import com.blissstock.mappingSite.validation.constrains.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    @ValidEmail 
    @NotBlank
    private String email;


}

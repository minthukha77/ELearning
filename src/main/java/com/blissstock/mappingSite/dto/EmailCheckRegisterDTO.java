package com.blissstock.mappingSite.dto;

import javax.validation.constraints.NotBlank;

import com.blissstock.mappingSite.validation.constrains.ValidEmail;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmailCheckRegisterDTO {
    @ValidEmail
    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank
    private String role;

    //Constructors

    public EmailCheckRegisterDTO() {
    }

    public EmailCheckRegisterDTO(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    


}

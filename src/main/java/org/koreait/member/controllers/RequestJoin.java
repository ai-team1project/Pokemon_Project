package org.koreait.member.controllers;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.koreait.member.constants.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class RequestJoin extends RequestAgree {



    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String nickName;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDt;
    @NotNull
    private Gender gender;


    @NotBlank
    private String zipCode;
    @NotBlank
    private String address;
    @NotBlank
    private String addressSub;
}

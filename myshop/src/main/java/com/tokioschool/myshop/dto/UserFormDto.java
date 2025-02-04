package com.tokioschool.myshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserFormDto {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nif;
    @NotBlank
    private String name;
    private String surname;
    @NotBlank
    private String email;
    private String address;
    private String city;
    @PositiveOrZero @Size(min = 4, max = 4)
    private String postalCode;
    private String province;
    private String country;
    private String image;
    private LocalDate creationDate;
    private LocalDateTime lastLogin;
    @Builder.Default
    private boolean active = Boolean.TRUE;
}

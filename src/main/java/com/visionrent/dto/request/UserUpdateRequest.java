package com.visionrent.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

    @Size(max = 50)
    @NotBlank(message = "Please provide your First name")
    private String firstName;

    @Size(max = 50)
    @NotBlank(message = "Please provide your Last name")
    private String lastName;

    @Size(min =5, max = 80)
    @Email(message = "Please provide valid e-mail")
    @NotBlank(message = "Please provide your e-mail")
    private String email;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please provide valid phone number")
    @Size(min = 14, max = 14)
    @NotBlank(message = "Please provide your phone number")
    private String phoneNumber;

    @Size(max = 150)
    @NotBlank(message = "Please provide your Address")
    private String address;

    @Size(max = 15)
    @NotBlank(message = "Please provide your Zip code")
    private String zipCode;

}

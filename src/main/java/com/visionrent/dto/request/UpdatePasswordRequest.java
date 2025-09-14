package com.visionrent.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest{

    @Size(min = 4, max = 20, message = "Please provide Correct Size of Password")
    @NotBlank(message = "Please Provide Old Password")
    private String oldPassword;

    @Size(min = 4, max = 20, message = "Please provide Correct Size of Password")
    @NotBlank(message = "Please Provide New Password")
    private String newPassword;

}

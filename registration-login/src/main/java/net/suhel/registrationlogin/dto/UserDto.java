package net.suhel.registrationlogin.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message="FirstName should not be empty")
    private String firstName;
    @NotEmpty(message="LastName should not be empty")
    private String lastName;
    @NotEmpty(message="Password should not be empty")
    private String password;
    @NotEmpty(message="Email should not be empty")
    @Email
    private String email;

    private String phonenumber;


}

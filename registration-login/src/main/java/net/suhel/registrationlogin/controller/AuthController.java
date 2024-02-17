package net.suhel.registrationlogin.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.suhel.registrationlogin.dto.UserDto;
import net.suhel.registrationlogin.entity.User;
import net.suhel.registrationlogin.service.SaveUser;
import net.suhel.registrationlogin.service.SaveUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {
    @Autowired
    SaveUser saveUser;
    @GetMapping("/index")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserDto userDto=new UserDto();
        model.addAttribute("user",userDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto,  BindingResult result,Model model){
        User existing=saveUser.findByEmail(userDto.getEmail());

        if(existing !=null){
            result.rejectValue("email",null,
                    "User already exist with same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user",userDto);
            return "/register";
        }

        saveUser.saveUser(userDto);

        return "redirect:/register?success";
    }

    @GetMapping("/allusers")
    public String registeredUsers (Model model){
       List<UserDto> allusers= saveUser.findAll();
       model.addAttribute("allusers",allusers);
        return "allUser";
    }

    @GetMapping("/login")
    public String LoginDetail (){
        return "login";
    }
}

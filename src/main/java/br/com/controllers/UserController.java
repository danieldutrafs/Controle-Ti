package br.com.controllers;

import br.com.models.User;
import br.com.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository repository;

    @GetMapping ("/novo")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "user/form";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute User user, BindingResult result){

        if (result.hasErrors()){
            return "user/form";
        }

        if (user.getId() == null && repository.existsByLogin(user.getLogin())){
            result.rejectValue("user", "duplicado", "Usuário já cadastrado");
            return "user/form";
        }

        repository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/excluir")
    public String ecluir (@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("{id}")
    public String detalhar(Model model){
        List<User> users =repository.findAll();
        model.addAttribute("users", users);
        return "user/lista";
    }
}

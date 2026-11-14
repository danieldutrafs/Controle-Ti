package br.com.controllers;

import br.com.models.Filial;
import br.com.repositories.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filiais")
@RequiredArgsConstructor
public class FilialController {

    private final FilialRepository repository;

    @GetMapping
    public String listar(Model model){
    model.addAttribute("filiais", repository.findAll());
    return "filial/lista";
    }

    @GetMapping("/nova")
    public String novaFilial(Model model){
        model.addAttribute("filial", new Filial());
        return "filial/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Filial filial){
        repository.save(filial);
        return "redirect:/filiais";
    }
}

package br.com.controllers;

import br.com.models.Filial;
import br.com.repositories.FilialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        repository.deleteById(id);
        return "redirect:/filiais";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model){
        Filial filial = repository.findById(id).orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        model.addAttribute("filial", filial);
        return "filial/form";
    }
}

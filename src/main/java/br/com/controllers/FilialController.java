package br.com.controllers;

import br.com.models.Filial;
import br.com.models.Impressora;
import br.com.repositories.FilialRepository;
import br.com.repositories.ImpressoraRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filiais")
@RequiredArgsConstructor
public class FilialController {

    private final FilialRepository repository;
    private final ImpressoraRepository impressoraRepository;

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

    @PostMapping // Post por causa do formulario
    public String salvar(@Valid @ModelAttribute Filial filial, BindingResult result){ //@vallid para validar campos vazios
        // 1. verificação de cmapo vazio
        if (result.hasErrors()) {
            return "filial/form";
        }

        // 2. verificação de filial existente
        if (filial.getId() == null && repository.existsByCodigoFilial(filial.getCodigoFilial())) {
            result.rejectValue("codigoFilial", "duplicado", "Código já cadastrado");
            return "filial/form";
        }

        // 3. se passou, salva
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

    //detalhar filial
    @GetMapping("{id}")
    public String detalhar(@PathVariable Long id, Model model){
        Filial filial = repository.findById(id).orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        List<Impressora> impressoras = impressoraRepository.findByFilial_Id(id);
        model.addAttribute("filial", filial);
        model.addAttribute("impressoras", impressoras);
        return "filial/detalhe";
    }
}

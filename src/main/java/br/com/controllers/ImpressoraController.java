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

@Controller
@RequestMapping("/impressoras")
@RequiredArgsConstructor
public class ImpressoraController {

    private final ImpressoraRepository repository;
    private final FilialRepository filialRepository;

    @GetMapping("/nova")
    public String novaImpressora(Model model, @RequestParam Long filialId) {
        Filial filial = filialRepository.findById(filialId).orElseThrow(() -> new RuntimeException("Filial não encontrada"));
        Impressora impressora = new Impressora();
        impressora.setFilial(filial);
        model.addAttribute("impressora", impressora);
        return "impressora/form-impressora";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute Impressora impressora, BindingResult result){
        if (result.hasErrors()){
            return "impressora/form-impressora";
        }

        if(impressora.getId() == null && repository.existsByIp(impressora.getIp())){
            result.rejectValue("ip", "duplicado", "Ip ja cadastrado");
            return "impressora/form-impressora";
        }

        if(impressora.getId() == null && repository.existsByPatrimonio(impressora.getPatrimonio())){
            result.rejectValue("patrimonio", "duplicado", "patrimonio ja cadastrado");
            return "impressora/form-impressora";
        }
        repository.save(impressora);
        return "redirect:/filiais/" + impressora.getFilial().getId();
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id){
        Impressora impressora = repository.findById(id).orElseThrow(() -> new RuntimeException("Impressora nao encontrada"));
        Long filialId = impressora.getFilial().getId();
        repository.deleteById(id);
        return "redirect:/filiais/" + filialId;
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model){
        Impressora impressora = repository.findById(id).orElseThrow(() -> new RuntimeException("Impressora nao encontrada"));
        model.addAttribute("impressora", impressora);
        return "impressora/form-impressora";
    }
}

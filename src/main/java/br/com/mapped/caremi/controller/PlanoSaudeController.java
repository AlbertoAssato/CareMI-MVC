package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.PlanoSaude;
import br.com.mapped.caremi.repository.ExameRepository;
import br.com.mapped.caremi.repository.PlanoSaudeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("plano-saude")
public class PlanoSaudeController {

    @Autowired
    private PlanoSaudeRepository planoSaudeRepository;


    @GetMapping("cadastrar")
    public String cadastrar(PlanoSaude planoSaude, Model model){
        return "plano-saude/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid PlanoSaude planoSaude, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "plano-saude/cadastrar";
        }
        planoSaudeRepository.save(planoSaude);
        redirectAttributes.addFlashAttribute("mensagem", "plano de saúde cadastrado com sucesso!");
        return "redirect:/plano-saude/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("planos-saude", planoSaudeRepository.findAll());
        return "plano-saude/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesPlanoSaude(@PathVariable Long id, Model model) {
        Optional<PlanoSaude> optionalPlanoSaude = planoSaudeRepository.findById(id);
        if (optionalPlanoSaude.isPresent()) {
            model.addAttribute("plano-saude", optionalPlanoSaude.get());
        } else {
            model.addAttribute("erro", "plano de saude não encontrado");
            return "error";
        }
        return "plano/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarPlanoSaude(@RequestParam String query, Model model) {
        List<PlanoSaude> planosSaude = planoSaudeRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("planos-saude", planosSaude);
        return "plano-saude/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("plano-saude", planoSaudeRepository.findById(id));
        return "plano-saude/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid PlanoSaude planoSaude, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "plano-saude/editar";
        }
        planoSaudeRepository.save(planoSaude);
        redirectAttributes.addFlashAttribute("mensagem", "o plano de saude foi atualizado!");
        return "redirect:/plano-saude/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        planoSaudeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "plano de saude removido com sucesso");
        return "redirect:/plano-saude/listar";
    }

}
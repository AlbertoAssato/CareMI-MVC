package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.PacientePlanoSaude;
import br.com.mapped.caremi.repository.ExameRepository;
import br.com.mapped.caremi.repository.PacientePlanoSaudeRepository;
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
@RequestMapping("paciente-plano-saude")
public class PacientePlanoSaudeController {

    @Autowired
    private PacientePlanoSaudeRepository pacientePlanoSaudeRepository;


    @GetMapping("cadastrar")
    public String cadastrar(PacientePlanoSaude pacientePlanoSaude, Model model){
        return "paciente-plano-saude/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid PacientePlanoSaude pacientePlanoSaude, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "paciente-plano-saude/cadastrar";
        }
        pacientePlanoSaudeRepository.save(pacientePlanoSaude);
        redirectAttributes.addFlashAttribute("mensagem", "paciente cadastrado com sucesso!");
        return "redirect:/paciente-plano-saude/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("paciente-plano-saudes", pacientePlanoSaudeRepository.findAll());
        return "exame/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesPacientePlanoSaude(@PathVariable Long id, Model model) {
        Optional<PacientePlanoSaude> optionalPacientePlanoSaude = pacientePlanoSaudeRepository.findById(id);
        if (optionalPacientePlanoSaude.isPresent()) {
            model.addAttribute("paciente-plano-saude", optionalPacientePlanoSaude.get());
        } else {
            model.addAttribute("erro", "paciente não encontrado");
            return "error";
        }
        return "paciente-plano-saude/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarPacientesPlanoSaude(@RequestParam String query, Model model) {
        List<PacientePlanoSaude> pacientePlanoSaudes = pacientePlanoSaudeRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("paciente-plano-saudes", pacientePlanoSaudes);
        return "paciente-plano-saude/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("paciente-plano-saude", pacientePlanoSaudeRepository.findById(id));
        return "paciente-plano-saude/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid PacientePlanoSaude pacientePlanoSaude, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "paciente-plano-saude/editar";
        }
        pacientePlanoSaudeRepository.save(pacientePlanoSaude);
        redirectAttributes.addFlashAttribute("mensagem", "o paciente foi atualizado!");
        return "redirect:/paciente-plano-saude/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        pacientePlanoSaudeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "paciente removido com sucesso");
        return "redirect:/paciente-plano-saude/listar";
    }

}
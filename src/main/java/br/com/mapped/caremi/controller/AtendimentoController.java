package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Atendimento;
import br.com.mapped.caremi.repository.AtendimentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Atendimento atendimento, Model model){
        return "atendimento/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Atendimento atendimento, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "atendimento/cadastrar";
        }
        atendimentoRepository.save(atendimento);
        redirectAttributes.addFlashAttribute("mensagem", "atendimento cadastrado com sucesso!");
        return "redirect:/atendimento/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("atendimentos", atendimentoRepository.findAll());
        return "atendimento/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesAtendimento(@PathVariable Long id, Model model) {
        Optional<Atendimento> optionalAtendimento = atendimentoRepository.findById(id);
        if (optionalAtendimento.isPresent()) {
            model.addAttribute("atendimento", optionalAtendimento.get());
        } else {
            model.addAttribute("erro", "atendimento não encontrado");
            return "error";
        }
        return "atendimento/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarAtendimentos(@RequestParam LocalDate query, Model model) {
        List<Atendimento> atendimentos = atendimentoRepository.findByDataEnvio(query);
        model.addAttribute("atendimentos", atendimentos);
        return "atendimento/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("atendimento", atendimentoRepository.findById(id));
        return "atendimento/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Atendimento atendimento, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "atendimento/editar";
        }
        atendimentoRepository.save(atendimento);
        redirectAttributes.addFlashAttribute("mensagem", "o atendimento foi atualizado!");
        return "redirect:/atendimento/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        atendimentoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "atendimento removido com sucesso");
        return "redirect:/atendimento/listar";
    }

}
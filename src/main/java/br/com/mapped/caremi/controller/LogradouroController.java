package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Cidade;
import br.com.mapped.caremi.model.Logradouro;
import br.com.mapped.caremi.repository.CidadeRepository;
import br.com.mapped.caremi.repository.LogradouroRepository;
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
@RequestMapping("logradouro")
public class LogradouroController {

    @Autowired
    private LogradouroRepository logradouroRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Logradouro logradouro, Model model){
        return "logradouro/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Logradouro logradouro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "logradouro/cadastrar";
        }
        logradouroRepository.save(logradouro);
        redirectAttributes.addFlashAttribute("mensagem", "logradouro cadastrado com sucesso!");
        return "redirect:/logradouro/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("logradouros", logradouroRepository.findAll());
        return "logradouro/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesLogradouro(@PathVariable Long id, Model model) {
        Optional<Logradouro> optionalLogradouro = logradouroRepository.findById(id);
        if (optionalLogradouro.isPresent()) {
            model.addAttribute("logradouro", optionalLogradouro.get());
        } else {
            model.addAttribute("erro", "logradouro não encontrado");
            return "error";
        }
        return "logradouro/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarLogradouro(@RequestParam String query, Model model) {
        List<Logradouro> logradouros = logradouroRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("logradouros", logradouros);
        return "logradouro/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("logradouro", logradouroRepository.findById(id));
        return "logradouro/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Logradouro logradouro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "logradouro/editar";
        }
        logradouroRepository.save(logradouro);
        redirectAttributes.addFlashAttribute("mensagem", "o logradouro foi atualizado!");
        return "redirect:/logradouro/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        logradouroRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "logradouro removido com sucesso");
        return "redirect:/logradouro/listar";
    }

}
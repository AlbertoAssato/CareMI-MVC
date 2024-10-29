package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Estado;
import br.com.mapped.caremi.repository.EstadoRepository;
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
@RequestMapping("estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Estado estado, Model model){
        return "estado/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Estado estado, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "estado/cadastrar";
        }
        estadoRepository.save(estado);
        redirectAttributes.addFlashAttribute("mensagem", "estado cadastrado com sucesso!");
        return "redirect:/estado/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("estados", estadoRepository.findAll());
        return "estado/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesEstado(@PathVariable Long id, Model model) {
        Optional<Estado> optionalEstado = estadoRepository.findById(id);
        if (optionalEstado.isPresent()) {
            model.addAttribute("estado", optionalEstado.get());
        } else {
            model.addAttribute("erro", "estado não encontrado");
            return "error";
        }
        return "estado/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarEstados(@RequestParam String query, Model model) {
        List<Estado> estados = estadoRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("estados", estados);
        return "estado/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("estado", estadoRepository.findById(id));
        return "estado/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Estado estado, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "estado/editar";
        }
        estadoRepository.save(estado);
        redirectAttributes.addFlashAttribute("mensagem", "o estado foi atualizado!");
        return "redirect:/estado/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        estadoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "estado removido com sucesso");
        return "redirect:/estado/listar";
    }

}
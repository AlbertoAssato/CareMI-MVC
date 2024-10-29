package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.repository.ExameRepository;
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
@RequestMapping("exame")
public class ExameController {

    @Autowired
    private ExameRepository exameRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Exame exame, Model model){
        return "exame/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Exame exame, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "exame/cadastrar";
        }
        exameRepository.save(exame);
        redirectAttributes.addFlashAttribute("mensagem", "exame cadastrado com sucesso!");
        return "redirect:/exame/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("exames", exameRepository.findAll());
        return "exame/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesExame(@PathVariable Long id, Model model) {
        Optional<Exame> optionalExame = exameRepository.findById(id);
        if (optionalExame.isPresent()) {
            model.addAttribute("exame", optionalExame.get());
        } else {
            model.addAttribute("erro", "exame não encontrado");
            return "error";
        }
        return "exame/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarExames(@RequestParam String query, Model model) {
        List<Exame> exames = exameRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("exames", exames);
        return "exame/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("exame", exameRepository.findById(id));
        return "exame/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Exame exame, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "exame/editar";
        }
        exameRepository.save(exame);
        redirectAttributes.addFlashAttribute("mensagem", "o exame foi atualizado!");
        return "redirect:/exame/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        exameRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "exame removido com sucesso");
        return "redirect:/exame/listar";
    }

}
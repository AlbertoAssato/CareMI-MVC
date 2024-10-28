package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Atendimento;
import br.com.mapped.caremi.model.Bairro;
import br.com.mapped.caremi.repository.AtendimentoRepository;
import br.com.mapped.caremi.repository.BairroRepository;
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
@RequestMapping("bairro")
public class BairroController {

    @Autowired
    private BairroRepository bairroRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Bairro bairro, Model model){
        return "bairro/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Bairro bairro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "bairro/cadastrar";
        }
        bairroRepository.save(bairro);
        redirectAttributes.addFlashAttribute("mensagem", "bairro cadastrado com sucesso!");
        return "redirect:/bairro/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("bairros", bairroRepository.findAll());
        return "bairro/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesBairro(@PathVariable Long id, Model model) {
        Optional<Bairro> optionalBairro = bairroRepository.findById(id);
        if (optionalBairro.isPresent()) {
            model.addAttribute("bairro", optionalBairro.get());
        } else {
            model.addAttribute("erro", "bairro não encontrado");
            return "error";
        }
        return "bairro/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarBairros(@RequestParam String query, Model model) {
        List<Bairro> bairros = bairroRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("bairros", bairros);
        return "bairro/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bairro", bairroRepository.findById(id));
        return "bairro/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Bairro bairro, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "bairro/editar";
        }
        bairroRepository.save(bairro);
        redirectAttributes.addFlashAttribute("mensagem", "o bairro foi atualizado!");
        return "redirect:/bairro/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        bairroRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "bairro removido com sucesso");
        return "redirect:/bairro/listar";
    }

}
package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Carteirinha;
import br.com.mapped.caremi.repository.CarteirinhaRepository;
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
@RequestMapping("carteirinha")
public class CarteirinhaController {

    @Autowired
    private CarteirinhaRepository carteirinhaRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Carteirinha carteirinha, Model model){
        return "carteirinha/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Carteirinha carteirinha, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "carteirinha/cadastrar";
        }
        carteirinhaRepository.save(carteirinha);
        redirectAttributes.addFlashAttribute("mensagem", "carteirinha cadastrado com sucesso!");
        return "redirect:/carteirinha/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("carteirinhas", carteirinhaRepository.findAll());
        return "carteirinha/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesCarteirinha(@PathVariable Long id, Model model) {
        Optional<Carteirinha> optionalCarteirinha = carteirinhaRepository.findById(id);
        if (optionalCarteirinha.isPresent()) {
            model.addAttribute("carteirinha", optionalCarteirinha.get());
        } else {
            model.addAttribute("erro", "carteirinha não encontrado");
            return "error";
        }
        return "carteirinha/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarCarteirinha(@RequestParam String query, Model model) {
        List<Carteirinha> carteirinhas = carteirinhaRepository.findByNomePacienteContainingIgnoreCase(query);
        model.addAttribute("carteirinhas", carteirinhas);
        return "carteirinhas/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("carteirinha", carteirinhaRepository.findById(id));
        return "carteirinha/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Carteirinha carteirinha, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "carteirinha/editar";
        }
        carteirinhaRepository.save(carteirinha);
        redirectAttributes.addFlashAttribute("mensagem", "a carteirinha foi atualizado!");
        return "redirect:/carteirinha/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        carteirinhaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "carteirinha removido com sucesso");
        return "redirect:/carteirinha/listar";
    }

}
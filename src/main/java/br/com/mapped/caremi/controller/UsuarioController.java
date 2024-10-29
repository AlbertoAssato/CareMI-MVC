package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Usuario;
import br.com.mapped.caremi.repository.UsuarioRepository;
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
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Usuario usuario, Model model){
        return "usuario/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "usuario/cadastrar";
        }
        usuarioRepository.save(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "usuario cadastrado com sucesso!");
        return "redirect:/usuario/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuario/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            model.addAttribute("usuario", optionalUsuario.get());
        } else {
            model.addAttribute("erro", "usuario não encontrado");
            return "error";
        }
        return "usuario/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarUsuarios(@RequestParam String query, Model model) {
        List<Usuario> usuarios = usuarioRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("usuarios", usuarios);
        return "usuario/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id));
        return "usuario/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "usuario/editar";
        }
        usuarioRepository.save(usuario);
        redirectAttributes.addFlashAttribute("mensagem", "o usuario foi atualizado!");
        return "redirect:/usuario/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        usuarioRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "usuario removido com sucesso");
        return "redirect:/usuario/listar";
    }

}
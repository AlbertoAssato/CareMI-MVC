package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Cidade;
import br.com.mapped.caremi.repository.CidadeRepository;
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
@RequestMapping("cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Cidade cidade, Model model){
        return "cidade/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Cidade cidade, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "cidade/cadastrar";
        }
        cidadeRepository.save(cidade);
        redirectAttributes.addFlashAttribute("mensagem", "cidade cadastrado com sucesso!");
        return "redirect:/cidade/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("cidades", cidadeRepository.findAll());
        return "cidade/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesCidade(@PathVariable Long id, Model model) {
        Optional<Cidade> optionalCidade = cidadeRepository.findById(id);
        if (optionalCidade.isPresent()) {
            model.addAttribute("cidade", optionalCidade.get());
        } else {
            model.addAttribute("erro", "cidade não encontrado");
            return "error";
        }
        return "cidade/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarCidades(@RequestParam String query, Model model) {
        List<Cidade> cidades = cidadeRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("cidades", cidades);
        return "cidade/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("cidade", cidadeRepository.findById(id));
        return "cidade/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Cidade cidade, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "cidade/editar";
        }
        cidadeRepository.save(cidade);
        redirectAttributes.addFlashAttribute("mensagem", "a cidade foi atualizado!");
        return "redirect:/cidade/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        cidadeRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "cidade removido com sucesso");
        return "redirect:/cidade/listar";
    }

}

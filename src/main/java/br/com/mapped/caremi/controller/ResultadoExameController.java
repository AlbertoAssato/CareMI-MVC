package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.ResultadoExame;
import br.com.mapped.caremi.repository.ExameRepository;
import br.com.mapped.caremi.repository.ResultadoExameRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("resultado-exame")
public class ResultadoExameController {

    @Autowired
    private ResultadoExameRepository resultadoExameRepository;


    @GetMapping("cadastrar")
    public String cadastrar(ResultadoExame resultadoExame, Model model){
        return "resultado-exame/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid ResultadoExame resultadoExame, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "resultado-exame/cadastrar";
        }
        resultadoExameRepository.save(resultadoExame);
        redirectAttributes.addFlashAttribute("mensagem", "resultado do exame cadastrado com sucesso!");
        return "redirect:/resultado-exame/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("resultados-exames", resultadoExameRepository.findAll());
        return "resultado-exame/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesResultadosExames(@PathVariable Long id, Model model) {
        Optional<ResultadoExame> optionalResultadoExame = resultadoExameRepository.findById(id);
        if (optionalResultadoExame.isPresent()) {
            model.addAttribute("resultado-exame", optionalResultadoExame.get());
        } else {
            model.addAttribute("erro", "resultado do exame não encontrado");
            return "error";
        }
        return "resultado-exame/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarResultadosExames(@RequestParam String query, Model model) {
        List<ResultadoExame> resultadoExames = resultadoExameRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("resultados-exames", resultadoExames);
        return "resultado-exame/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resultado-exame", resultadoExameRepository.findById(id));
        return "resultado-exame/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid ResultadoExame resultadoExame, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "resultado-exame/editar";
        }
        resultadoExameRepository.save(resultadoExame);
        redirectAttributes.addFlashAttribute("mensagem", "o resultado do exame foi atualizado!");
        return "redirect:/resultado-exame/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        resultadoExameRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "o resultado do exame foi removido com sucesso");
        return "redirect:/resultado-exame/listar";
    }

}
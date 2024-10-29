package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.Medico;
import br.com.mapped.caremi.repository.ExameRepository;
import br.com.mapped.caremi.repository.MedicoRepository;
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
@RequestMapping("medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Medico medico, Model model){
        return "medico/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Medico medico, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "medico/cadastrar";
        }
        medicoRepository.save(medico);
        redirectAttributes.addFlashAttribute("mensagem", "medico cadastrado com sucesso!");
        return "redirect:/medico/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("medicos", medicoRepository.findAll());
        return "medico/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesMedico(@PathVariable Long id, Model model) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isPresent()) {
            model.addAttribute("medico", optionalMedico.get());
        } else {
            model.addAttribute("erro", "medico não encontrado");
            return "error";
        }
        return "medico/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarMedicos(@RequestParam String query, Model model) {
        List<Medico> medicos = medicoRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("medicos", medicos);
        return "medico/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("medico", medicoRepository.findById(id));
        return "medico/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Medico medico, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "medico/editar";
        }
        medicoRepository.save(medico);
        redirectAttributes.addFlashAttribute("mensagem", "o medico foi atualizado!");
        return "redirect:/medico/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        medicoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "medico removido com sucesso");
        return "redirect:/medico/listar";
    }

}

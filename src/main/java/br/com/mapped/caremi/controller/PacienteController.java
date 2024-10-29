package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Exame;
import br.com.mapped.caremi.model.Paciente;
import br.com.mapped.caremi.repository.ExameRepository;
import br.com.mapped.caremi.repository.PacienteRepository;
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
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;


    @GetMapping("cadastrar")
    public String cadastrar(Paciente paciente, Model model){
        return "paciente/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid Paciente paciente, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "paciente/cadastrar";
        }
        pacienteRepository.save(paciente);
        redirectAttributes.addFlashAttribute("mensagem", "paciente cadastrado com sucesso!");
        return "redirect:/paciente/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "paciente/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesPaciente(@PathVariable Long id, Model model) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            model.addAttribute("paciente", optionalPaciente.get());
        } else {
            model.addAttribute("erro", "paciente não encontrado");
            return "error";
        }
        return "paciente/detalhes";
    }

    @GetMapping("pesquisar")
    public String pesquisarPacientes(@RequestParam String query, Model model) {
        List<Paciente> pacientes = pacienteRepository.findByNomeContainingIgnoreCase(query);
        model.addAttribute("pacientes", pacientes);
        return "pacientes/pesquisar";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("paciente", pacienteRepository.findById(id));
        return "paciente/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid Paciente paciente, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "paciente/editar";
        }
        pacienteRepository.save(paciente);
        redirectAttributes.addFlashAttribute("mensagem", "o paciente foi atualizado!");
        return "redirect:/paciente/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        pacienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("paciente", "paciente removido com sucesso");
        return "redirect:/paciente/listar";
    }

}
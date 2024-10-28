package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Cidade;
import br.com.mapped.caremi.model.EnderecoPaciente;
import br.com.mapped.caremi.repository.CidadeRepository;
import br.com.mapped.caremi.repository.EnderecoPacienteRepository;
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
@RequestMapping("endereco-paciente")
public class EnderecoPacienteController {

    @Autowired
    private EnderecoPacienteRepository enderecoPacienteRepository;


    @GetMapping("cadastrar")
    public String cadastrar(EnderecoPaciente enderecoPaciente, Model model){
        return "endereco-paciente/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid EnderecoPaciente enderecoPaciente, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "endereco-paciente/cadastrar";
        }
        enderecoPacienteRepository.save(enderecoPaciente);
        redirectAttributes.addFlashAttribute("mensagem", "endereço do paciente cadastrado com sucesso!");
        return "redirect:/cidade/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("enderecos-pacientes", enderecoPacienteRepository.findAll());
        return "endereco-paciente/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesEnderecoPaciente(@PathVariable Long id, Model model) {
        Optional<EnderecoPaciente> optionalEnderecoPaciente = enderecoPacienteRepository.findById(id);
        if (optionalEnderecoPaciente.isPresent()) {
            model.addAttribute("endereco-paciente", optionalEnderecoPaciente.get());
        } else {
            model.addAttribute("erro", "o endereço do paciente não foi encontrado");
            return "error";
        }
        return "endereco-paciente/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("endereco-paciente", enderecoPacienteRepository.findById(id));
        return "endereco-paciente/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid EnderecoPaciente enderecoPaciente, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "endereco-paciente/editar";
        }
        enderecoPacienteRepository.save(enderecoPaciente);
        redirectAttributes.addFlashAttribute("mensagem", "o endereço do paciente foi atualizado!");
        return "redirect:/endereco-paciente/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        enderecoPacienteRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "o endereço do paciente foi removido com sucesso");
        return "redirect:/endereco-paciente/listar";
    }

}
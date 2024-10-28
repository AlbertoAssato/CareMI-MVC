package br.com.mapped.caremi.controller;

import br.com.mapped.caremi.model.Cidade;
import br.com.mapped.caremi.model.EnderecoHospital;
import br.com.mapped.caremi.repository.CidadeRepository;
import br.com.mapped.caremi.repository.EnderecoHospitalRepository;
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
@RequestMapping("endereco-hospital")
public class EnderecoHospitalController {

    @Autowired
    private EnderecoHospitalRepository enderecoHospitalRepository;


    @GetMapping("cadastrar")
    public String cadastrar(EnderecoHospital enderecoHospital, Model model){
        return "endereco-hospital/cadastrar";
    }


    @PostMapping("cadastrar")
    @Transactional
    public String cadastrar(@Valid EnderecoHospital enderecoHospital, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "endereco-hospital/cadastrar";
        }
        enderecoHospitalRepository.save(enderecoHospital);
        redirectAttributes.addFlashAttribute("mensagem", "endereço do hospital cadastrado com sucesso!");
        return "redirect:/endereco-hospital/cadastrar";
    }


    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("enderecos-hospitais", enderecoHospitalRepository.findAll());
        return "endereco-hospital/listar";
    }


    @GetMapping("detalhes/{id}")
    public String detalhesEnderecoHospital(@PathVariable Long id, Model model) {
        Optional<EnderecoHospital> optionalEnderecoHospital = enderecoHospitalRepository.findById(id);
        if (optionalEnderecoHospital.isPresent()) {
            model.addAttribute("endereco-hospital", optionalEnderecoHospital.get());
        } else {
            model.addAttribute("erro", "endereco do hospital não encontrado");
            return "error";
        }
        return "endereco-hospital/detalhes";
    }


    @GetMapping("editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("endereco-hospital", enderecoHospitalRepository.findById(id));
        return "endereco-hospital/editar";
    }


    @PostMapping("editar")
    public String editar(@Valid EnderecoHospital enderecoHospital, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()) {
            return "endereco-hospital/editar";
        }
        enderecoHospitalRepository.save(enderecoHospital);
        redirectAttributes.addFlashAttribute("mensagem", "o endereço do hospital foi atualizado!");
        return "redirect:/endereco-hospital/listar";
    }


    @PostMapping("remover")
    @Transactional
    public String remover(Long id, RedirectAttributes redirectAttributes) {
        enderecoHospitalRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem", "o endereco do hospital foi removido com sucesso");
        return "redirect:/endereco-hospital/listar";
    }

}

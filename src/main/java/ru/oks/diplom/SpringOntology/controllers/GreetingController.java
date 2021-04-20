package ru.oks.diplom.SpringOntology.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.oks.diplom.SpringOntology.dto.GreetingDto;

@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new GreetingDto());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute GreetingDto greeting, Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }

//    @GetMapping("/onto")
//    public String index(Model model) {
//        model.addAttribute("onto", new OntoDto());
//
//        return "onto";
//    }
//    @PostMapping("/onto")
//    public String greetingSubmit(@ModelAttribute OntoDto onto, Model model) {
//        model.addAttribute("onto", onto);
//        return "ontoResult";
//    }
}

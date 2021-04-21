package ru.oks.diplom.SpringOntology.controllers;

import org.apache.jena.sparql.lang.sparql_11.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.oks.diplom.SpringOntology.dto.OntoDto;
import ru.oks.diplom.SpringOntology.dto.RequestDto;
import ru.oks.diplom.SpringOntology.dto.ResultDto;
import ru.oks.diplom.SpringOntology.services.HandlingService;
import ru.oks.diplom.SpringOntology.services.OntoService;

import java.util.List;

@Controller
public class IndexController {

    private final HandlingService handlingService;

    @Autowired
    public IndexController(HandlingService handlingService) {
        this.handlingService = handlingService;
    }

//    @GetMapping("/onto")
//    public ModelAndView index() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("request", new RequestDto());
//        modelAndView.setViewName("onto");
//
//        return modelAndView;
//    }

//    @PostMapping("/onto")
//    public ModelAndView result(@ModelAttribute RequestDto request) throws ParseException {
//        ModelAndView modelAndView = new ModelAndView();
//        handlingService.setRequest(request);
//
//        List<ResultDto> projects = handlingService.execute();
//
//        modelAndView.setViewName("ontoResult");
//        modelAndView.addObject("projects", projects);
//
//        return modelAndView;
//    }

    @GetMapping("/onto")
    public String index(Model model) {
        model.addAttribute("onto", new OntoDto());

        return "onto";
    }

    @PostMapping("/onto")
    public String greetingSubmit(@ModelAttribute OntoDto onto, Model model) throws ParseException {
        OntoService ontoService = new OntoService();
        RequestDto request = ontoService.replaceDto(onto);
        handlingService.setRequest(request);

        List<ResultDto> projects = handlingService.execute();
        model.addAttribute("projects", projects);
        return "projects";
    }


}
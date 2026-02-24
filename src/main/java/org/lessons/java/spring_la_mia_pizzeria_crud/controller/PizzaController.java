package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model){
        List<Pizza> result = repository.findAll();
        model.addAttribute("pizzas", result);
        return("pizzas/index");

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
    Pizza pizza = repository.findById(id).orElseThrow();
    model.addAttribute("pizza", pizza);
    return "pizzas/pizza-by-id";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());

        return"pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/pizzas/create";
        }
        repository.save(formPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "pizzas/update";
        }

        formPizza.setId(id);

        repository.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

	repository.deleteById(id);
	
	return "redirect:/pizzas";
    }
}

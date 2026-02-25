package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Deal;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.DealRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/deals")
public class DealsController {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/show")
    public String show(Model model) {
        List<Deal> result = dealRepository.findAll();
        model.addAttribute("deals", result);
        return "deals/index";
    }
    
    @GetMapping("/create/{pizzaId}")
    public String create(@PathVariable Integer pizzaId, Model model) {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow();
        
        Deal deal = new Deal();
        deal.setPizza(pizza);

        model.addAttribute("deal" , deal);
        return "deals/create";
    }
    
}

package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Deal;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.DealRepository;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/deals")
public class DealsController {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
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

        model.addAttribute("deal", deal);
        return "deals/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("deal") Deal formDeal, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "deals/create";
        }
        dealRepository.save(formDeal);

        return "redirect:/deals";
    }

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("deal", dealRepository.findById(id).orElseThrow());
        return "deals/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("deal") Deal formDeal,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "deals/update";
        }

        formDeal.setId(id);
        dealRepository.save(formDeal);
        return "redirect:/deals";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {

        dealRepository.deleteById(id);

        return "redirect:/pizzas";
    }
}

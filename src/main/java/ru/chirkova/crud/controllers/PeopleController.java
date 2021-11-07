package ru.chirkova.crud.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chirkova.crud.dao.PersonDAO;
import ru.chirkova.crud.models.Person;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) throws SQLException {
        model.addAttribute("people",personDAO.index());
        return "index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("show",personDAO.show(id));

        return "show";
    }

    @GetMapping("/new")
    public  String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "person_form";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) throws SQLException {
        if(bindingResult.hasErrors()) {return "person_form";}
        personDAO.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") int id) throws SQLException {
        model.addAttribute("person",personDAO.show(id));
        return "person_edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                         @PathVariable("id") int id) throws SQLException {
        if(bindingResult.hasErrors()) {return "person_edit";}
        personDAO.updatePerson(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) throws SQLException {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }

}

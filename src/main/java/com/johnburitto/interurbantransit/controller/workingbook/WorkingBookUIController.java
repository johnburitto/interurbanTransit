package com.johnburitto.interurbantransit.controller.workingbook;

/*
 * @author JohnBuritto
 * @project interurbanTransit
 * @class WorkingBookUIController
 * @version 1.0.0
 * @since 08.06.2022, 23:37
 * Software Engineering Department
 *
 * Copyright (c) 1993-1996 Sun Microsystems, Inc. All Rights Reserved.
 */

import com.johnburitto.interurbantransit.form.PlaceOfWorkForm;
import com.johnburitto.interurbantransit.model.WorkingBook;
import com.johnburitto.interurbantransit.service.impls.WorkingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui/v1/working-books")
public class WorkingBookUIController {
    @Autowired
    WorkingBookService service;

    //todo - create widget for all places of work

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("workingBooks", service.getAll());

        return "working-books-all";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);

        return "redirect:/ui/v1/working-books/";
    }

    @RequestMapping(value = "/{numberOfWorkingBook}/add/place-of-work", method = RequestMethod.GET)
    public String addPlaceOfWork(Model model, @PathVariable("numberOfWorkingBook") String id) {
        model.addAttribute("form", new PlaceOfWorkForm());

        return "place-of-work-add";
    }

    @RequestMapping(value = "/{numberOfWorkingBook}/add/place-of-work", method = RequestMethod.POST)
    public String addPlaceOfWork(@ModelAttribute("form") PlaceOfWorkForm form, @PathVariable("numberOfWorkingBook") String id) {
        WorkingBook workingBookToEdit = service.get(id);

        workingBookToEdit.addPlaceOfWork(form);
        service.update(workingBookToEdit);

        return "redirect:/ui/v1/working-books/";
    }
}

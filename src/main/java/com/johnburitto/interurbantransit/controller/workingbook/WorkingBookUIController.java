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

import com.johnburitto.interurbantransit.form.DeletePlaceOfWorkForm;
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

import java.util.Collections;

@Controller
@RequestMapping("/ui/v1/working-books")
public class WorkingBookUIController {
    @Autowired
    WorkingBookService service;

    @RequestMapping("/")
    public String showAll(Model model) {
        model.addAttribute("workingBooks", service.getAll());

        return "working-books-all";
    }

    @RequestMapping("/{numberOfWorkingBook}")
    public String showOne(Model model, @PathVariable String numberOfWorkingBook) {
        model.addAttribute("workingBooks", service.getOneAsList(numberOfWorkingBook));

        return "working-books-all";
    }

    @RequestMapping(value = "/{numberOfWorkingBook}/add/place-of-work", method = RequestMethod.GET)
    public String addPlaceOfWork(Model model, @PathVariable("numberOfWorkingBook") String numberOfWorkingBook) {
        model.addAttribute("form", new PlaceOfWorkForm());

        return "place-of-work-add";
    }

    @RequestMapping(value = "/{numberOfWorkingBook}/add/place-of-work", method = RequestMethod.POST)
    public String addPlaceOfWork(@ModelAttribute("form") PlaceOfWorkForm form,
                                 @PathVariable("numberOfWorkingBook") String numberOfWorkingBook) {
        WorkingBook workingBookToEdit = service.get(numberOfWorkingBook);

        workingBookToEdit.addPlaceOfWork(form);
        service.update(workingBookToEdit);

        return "redirect:/ui/v1/working-books/";
    }

    @RequestMapping(value = "/{numberOfWorkingBook}/delete/place-of-work", method = RequestMethod.GET)
    public String deletePlaceOfWork(Model model, @PathVariable("numberOfWorkingBook") String numberOfWorkingBook) {
        model.addAttribute("form", new DeletePlaceOfWorkForm());
        model.addAttribute("SPLITTER", DeletePlaceOfWorkForm.SPLITTER);
        model.addAttribute("placesOfWork", service.getPlacesOfWorkFromWorkingBook(numberOfWorkingBook));

        return "place-of-work-delete";
    }

    @RequestMapping(value = "/{numberOfWorkingBook}/delete/place-of-work", method = RequestMethod.POST)
    public String deletePlaceOfWork(@ModelAttribute("form") DeletePlaceOfWorkForm form,
                                    @PathVariable("numberOfWorkingBook") String numberOfWorkingBook) {
        WorkingBook workingBookToEdit = service.get(numberOfWorkingBook);

        workingBookToEdit.deletePlaceOfWor(form.generatePlaceOfWork());
        service.update(workingBookToEdit);

        return "redirect:/ui/v1/working-books/";
    }
}

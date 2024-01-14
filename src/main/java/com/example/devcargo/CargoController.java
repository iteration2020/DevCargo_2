package com.example.devcargo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CargoController {
    private final CargoService cargoService;

    @RequestMapping("/")
    public String viewHomePage(
            Model model,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(value = "date", required = false) LocalDate date,
            @RequestParam(value = "sorting", required = false) String sorting
    ) {
        var listCargo = new ArrayList<>();
        if (date != null) {
            listCargo.addAll(cargoService.searchByDepartureDate(date));
        } else if (sorting != null) {
            listCargo.addAll(cargoService.listAll(sorting));
        } else {
            listCargo.addAll(cargoService.search(keyword));
        }
        var cargoCountByDate = cargoService.getCargoCountByDate();
        model.addAttribute("listCargo", listCargo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("date", date);
        model.addAttribute("cargoCountByDate", cargoCountByDate);
        model.addAttribute("chartCounts", cargoCountByDate.stream()
                .map(it -> it.getCount().toString())
                .collect(Collectors.joining(",")));
        model.addAttribute("chartDates", cargoCountByDate.stream()
                .map(it -> it.getDepartureDate().toString())
                .collect(Collectors.joining(",", "'", "'")));
        return "index";
    }

    @GetMapping("/new")
    public String showNewCargoForm(Model model) {
        Cargo cargo = new Cargo();
        model.addAttribute("cargo", cargo);
        return "new_cargo";
    }

    @PostMapping("/save")
    public String saveCargo(@ModelAttribute("cargo") Cargo cargo) {
        cargoService.save(cargo);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditCargoForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_cargo");
        Cargo cargo = cargoService.get(id);
        mav.addObject("cargo", cargo);
        return mav;
    }

    @PostMapping("/delete/{id}")
    public String deleteCargo(@PathVariable(name = "id") Long id) {
        cargoService.delete(id);
        return "redirect:/";
    }
}

package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.responses.ApiResponse;
import com.workintech.s18d2.services.VegetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vegetable")
@RequiredArgsConstructor
public class VegetableController {

    private final VegetableService vegetableService;

    @GetMapping
    public List<Vegetable> getVegetablesAsc() {
        return vegetableService.getByPriceAsc();
    }

    @GetMapping("/desc")
    public List<Vegetable> getVegetablesDesc() {
        return vegetableService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public Vegetable getVegetable(@PathVariable Long id) {
        return vegetableService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Vegetable>> save(@RequestBody Vegetable vegetable) {
        Vegetable saved = vegetableService.save(vegetable);
        return ResponseEntity.ok(new ApiResponse<>("Vegetable saved or updated successfully", saved));
    }

    @PostMapping("/name/{name}")
    public List<Vegetable> searchByName(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }

    @DeleteMapping("/{id}")
    public Vegetable delete(@PathVariable Long id) {
        return vegetableService.delete(id);
    }
}

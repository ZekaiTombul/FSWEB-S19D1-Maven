package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.responses.ApiResponse;
import com.workintech.s18d2.services.FruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruit")
@RequiredArgsConstructor
public class FruitController {

    private final FruitService fruitService;

    @GetMapping
    public List<Fruit> getFruitsAsc() {
        return fruitService.getByPriceAsc();
    }

    @GetMapping("/desc")
    public List<Fruit> getFruitsDesc() {
        return fruitService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public Fruit getFruit(@PathVariable Long id) {
        return fruitService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Fruit>> save(@RequestBody Fruit fruit) {
        Fruit saved = fruitService.save(fruit);
        return ResponseEntity.ok(new ApiResponse<>("Fruit saved or updated successfully", saved));
    }

    @PostMapping("/name/{name}")
    public List<Fruit> searchByName(@PathVariable String name) {
        return fruitService.searchByName(name);
    }

    @DeleteMapping("/{id}")
    public Fruit delete(@PathVariable Long id) {
        return fruitService.delete(id);
    }
}

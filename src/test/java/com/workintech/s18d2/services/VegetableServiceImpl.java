package com.workintech.s18d2.services;

import com.workintech.s18d2.dao.VegetableRepository;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VegetableServiceImpl implements VegetableService {

    private final VegetableRepository vegetableRepository;

    @Override
    public List<Vegetable> getByPriceAsc() {
        return vegetableRepository.getByPriceAsc();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        return vegetableRepository.getByPriceDesc();
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        return vegetableRepository.searchByName(name);
    }

    @Override
    public Vegetable getById(Long id) {
        if (id < 0) {
            throw new PlantException("ID must be greater than 0");
        }
        return vegetableRepository.findById(id)
                .orElseThrow(() -> new PlantException("Vegetable not found with ID: " + id));
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        if (vegetable.getName() == null || vegetable.getPrice() == null) {
            throw new PlantException("Missing required vegetable fields");
        }
        return vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable delete(Long id) {
        Vegetable found = getById(id);
        vegetableRepository.delete(found);
        return found;
    }
}

package com.workintech.s18d2.services;

import com.workintech.s18d2.dao.FruitRepository;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    @Override
    public List<Fruit> getByPriceAsc() {
        return fruitRepository.getByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return fruitRepository.getByPriceDesc();
    }

    @Override
    public List<Fruit> searchByName(String name) {
        return fruitRepository.searchByName(name);
    }

    @Override
    public Fruit getById(Long id) {
        if (id < 0) {
            throw new PlantException("ID must be greater than 0");
        }
        return fruitRepository.findById(id)
                .orElseThrow(() -> new PlantException("Fruit not found with ID: " + id));
    }

    @Override
    public Fruit save(Fruit fruit) {
        if (fruit.getName() == null || fruit.getPrice() == null || fruit.getFruitType() == null) {
            throw new PlantException("Missing required fruit fields");
        }
        return fruitRepository.save(fruit);
    }

    @Override
    public Fruit delete(Long id) {
        Fruit found = getById(id);
        fruitRepository.delete(found);
        return found;
    }
}

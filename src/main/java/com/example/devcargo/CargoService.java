package com.example.devcargo;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public List<Cargo> search(String keyword) {
        return cargoRepository.search(keyword);
    }

    public List<Cargo> searchByDepartureDate(LocalDate departureDate) {
        return cargoRepository.findByDepartureDate(departureDate);
    }

    public List<Cargo> listAll(String sorting) {
        Sort.Direction direction;
        if ("asc".equals(sorting)) {
            direction = Sort.Direction.ASC;
        } else {
            direction = Sort.Direction.DESC;
        }
        return cargoRepository.findAll(Sort.by(direction, "departureDate"));
    }

    public Cargo get(Long id) {
        return cargoRepository.findById(id).orElse(null);
    }

    public void save(Cargo cargo) {
        cargoRepository.save(cargo);
    }

    public void delete(Long id) {
        cargoRepository.deleteById(id);
    }

    public List<CargoCountByDate> getCargoCountByDate() {
        return cargoRepository.countCargoByDepartureDate();
    }
}
